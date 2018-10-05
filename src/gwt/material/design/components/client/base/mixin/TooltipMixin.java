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
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasTooltip;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

/**
 * @author Richeli Vargas
 */
public class TooltipMixin<T extends Widget> extends AbstractMixin<T> implements HasTooltip {

	private Element tooltip;
	private HandlerRegistration mouseMove;
	private HandlerRegistration mouseOut;
	private HandlerRegistration mouseOver;

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

		mouseMove = uiObject.addDomHandler(event -> changePosition(event), MouseMoveEvent.getType());
		mouseOver = uiObject.addDomHandler(event -> attach(event), MouseOverEvent.getType());
		mouseOut = uiObject.addDomHandler(event -> unAttach(), MouseOutEvent.getType());

	}

	protected void unbind() {
		if (mouseMove != null)
			mouseMove.removeHandler();
		if (mouseOut != null)
			mouseOut.removeHandler();
		if (mouseOver != null)
			mouseOver.removeHandler();
	}

	protected void changePosition(final MouseEvent<?> event) {

		final int cursorSize = 16;
		final int margin = 8;
		final int tooltipWidth = tooltip.getClientWidth();
		final int tooltipHeight = tooltip.getClientHeight();
		final int screenWidth = Window.getClientWidth();
		final int screenHeight = Window.getClientHeight();

		int tooltipX = event.getClientX();
		int tooltipY = event.getClientY();

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
		changePosition(event);
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

}
