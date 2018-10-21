/*
 * #%L
 * Gwt Material Design Components
 * %%
 * Copyright (C) 2017 - 2017 Gwt Material Design Components
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.components.client.base.mixin;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasTooltip;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.TooltipPosition;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * @author Richeli Vargas
 */
public class TooltipMixin<T extends Widget> extends AbstractMixin<T> implements HasTooltip {

	private Element tooltip;
	private HandlerRegistration mouseMove;
	private HandlerRegistration mouseOut;
	private HandlerRegistration mouseOver;
	private HandlerRegistration mouseUp;
	private HandlerRegistration touchStart;
	private HandlerRegistration touchEnd;
	private Timer longPressTimer;

	private TooltipPosition position = TooltipPosition.AUTO;

	public TooltipMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setTooltip(String tooltip) {
		if (tooltip == null || tooltip.isEmpty()) {
			uiObject.getElement().removeAttribute("tooltip");
			unbind();
		} else {
			uiObject.getElement().setAttribute("tooltip", tooltip);
			bind();
			this.tooltip.setInnerHTML(tooltip);
		}
	}

	@Override
	public String getTooltip() {
		return tooltip == null ? null : tooltip.getInnerText();
	}

	protected void bind() {
		unbind();

		if (tooltip == null)
			this.tooltip = HtmlElements.LABEL.createElement(CssName.MDC_TOOLTIP, CssName.MDC_TYPOGRAPHY__CAPTION);

		// mouseMove = uiObject.addDomHandler(event -> changePosition(event),
		// MouseMoveEvent.getType());
		mouseOver = uiObject.addDomHandler(event -> attach(event), MouseOverEvent.getType());
		mouseOut = uiObject.addDomHandler(event -> unAttach(), MouseOutEvent.getType());
		mouseUp = uiObject.addDomHandler(event -> unAttach(), MouseUpEvent.getType());
		touchStart = uiObject.addDomHandler(event -> cancelLongPressTimer(), TouchEndEvent.getType());
		touchEnd = uiObject.addDomHandler(event -> startLongPressTimer(null), TouchStartEvent.getType());

	}

	protected void unbind() {
		if (mouseMove != null)
			mouseMove.removeHandler();
		if (mouseOut != null)
			mouseOut.removeHandler();
		if (mouseOver != null)
			mouseOver.removeHandler();
		if (mouseUp != null)
			mouseUp.removeHandler();
		if (touchStart != null)
			touchStart.removeHandler();
		if (touchEnd != null)
			touchEnd.removeHandler();

		cancelLongPressTimer();
	}

	protected void startLongPressTimer(final MouseEvent<?> event) {
		cancelLongPressTimer();
		longPressTimer = TimerHelper.schedule(1500, () -> attach(event));
	}

	protected void cancelLongPressTimer() {
		if (longPressTimer != null)
			longPressTimer.cancel();
	}

	protected native void setPosition(final Element tooltip, final Element target,
			final TooltipPosition position)/*-{

		var POSITION = position.toString();
		var AUTO = 'AUTO';
		var BOTTOM = 'BOTTOM';
		var TOP = 'TOP';
		var LEFT = 'LEFT';
		var RIGHT = 'RIGHT';

		var targetWidth = $wnd.jQuery(target).outerWidth();
		var targetHeight = $wnd.jQuery(target).outerHeight();
		var tooltipWidth = $wnd.jQuery(tooltip).outerWidth();
		var tooltipHeight = $wnd.jQuery(tooltip).outerHeight();
		var screenWidth = $wnd.jQuery($wnd).outerWidth();
		var screenHeight = $wnd.jQuery($wnd).outerHeight();
		var targetTop = $wnd.jQuery(target).offset().top
				- $wnd.jQuery($wnd).scrollTop();
		var targetLeft = $wnd.jQuery(target).offset().left
				- $wnd.jQuery($wnd).scrollLeft();
		var tooltipMarginTop = ($wnd.jQuery(tooltip).outerWidth(true) - $wnd
				.jQuery(tooltip).outerWidth()) / 2;
		var tooltipMarginLeft = ($wnd.jQuery(tooltip).outerHeight(true) - $wnd
				.jQuery(tooltip).outerHeight()) / 2;

		if (POSITION === AUTO) {
			var isTop = screenHeight / 2 > targetTop;
			if (isTop)
				POSITION = BOTTOM;
			else
				POSITION = TOP;
		}

		var top;
		var left;

		switch (POSITION) {
		case LEFT:
			top = targetTop + ((targetHeight - tooltipHeight) / 2)
					- tooltipMarginTop;
			left = targetLeft - tooltipWidth - (tooltipMarginLeft * 2);
			break;
		case RIGHT:
			top = targetTop + ((targetHeight - tooltipHeight) / 2)
					- tooltipMarginTop;
			left = targetLeft + targetWidth;
			break;
		case TOP:
			top = targetTop - tooltipHeight - (tooltipMarginTop * 2);
			left = targetLeft + ((targetWidth - tooltipWidth) / 2)
					- tooltipMarginLeft;
			break;
		case BOTTOM:
		default:
			top = targetTop + targetHeight;
			left = targetLeft + ((targetWidth - tooltipWidth) / 2)
					- tooltipMarginLeft;
			break;
		}

		$wnd.jQuery(tooltip).css('top', top + 'px');
		$wnd.jQuery(tooltip).css('left', left + 'px');

	}-*/;

	protected void changePosition(final MouseEvent<?> event) {

		final int cursorSize = 16;
		final int margin = 8;
		final int tooltipWidth = tooltip.getClientWidth();
		final int tooltipHeight = tooltip.getClientHeight();
		final int screenWidth = Window.getClientWidth();
		final int screenHeight = Window.getClientHeight();

		int tooltipX = event == null ? uiObject.getElement().getAbsoluteLeft() + (margin * 2) : event.getClientX();
		int tooltipY = event == null ? uiObject.getElement().getAbsoluteTop() + (margin * 2) : event.getClientY();

		if (tooltipX > screenWidth / 2)
			tooltipX -= (tooltipWidth + cursorSize);
		else
			tooltipX -= (cursorSize - margin);

		if (tooltipY > screenHeight / 2)
			tooltipY -= (tooltipHeight + margin + cursorSize);
		else
			tooltipY += margin;

		tooltip.getStyle().setProperty("left", tooltipX + "px");
		tooltip.getStyle().setProperty("top", tooltipY + "px");
	}

	protected void attach(final MouseEvent<?> event) {
		unAttach();
		attach(tooltip);
		// changePosition(event);
		// staticPosition();
		setPosition(tooltip, uiObject.getElement(), position);
	}

	protected void unAttach() {
		if (tooltip != null)
			unAttach(tooltip);
	}

	protected native void attach(final Element element)/*-{
		$doc.body.appendChild(element);
	}-*/;

	protected native void unAttach(final Element element)/*-{
		$wnd.jQuery(element).remove();
	}-*/;

	public TooltipPosition getTooltipPosition() {
		return position;
	}

	public void setTooltipPosition(TooltipPosition position) {
		this.position = position;
	}
}
