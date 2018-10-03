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
import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.interfaces.HasTooltip;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

/**
 * @author Richeli Vargas
 */
public class TooltipMixin<T extends UIObject> extends AbstractMixin<T> implements HasTooltip {

	private Element tooltip;

	public TooltipMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setTooltip(String tooltip) {
		if (this.tooltip == null) {
			this.tooltip = HtmlElements.LABEL.createElement(CssName.MDC_TOOLTIP);
			initialize(this.tooltip, uiObject.getElement());
		}		
		this.tooltip.setInnerHTML(tooltip);
	}

	@Override
	public String getTooltip() {
		return tooltip.getInnerText();
	}

	protected native void initialize(final Element element, final Element target)/*-{

		var changeTooltipPosition = function(event) {
			var tooltipX = event.pageX - 8;
			var tooltipY = event.pageY + 8;
			$wnd.jQuery(element).css({
				top : tooltipY,
				left : tooltipX
			});
		};

		var showTooltip = function(event) {
			$wnd.jQuery(element).remove();
			$doc.body.appendChild(element);
			changeTooltipPosition(event);
		};

		var hideTooltip = function() {
			$wnd.jQuery(element).remove();
		};

		$wnd.jQuery(target).bind({
			mousemove : changeTooltipPosition,
			mouseenter : showTooltip,
			mouseleave : hideTooltip
		});

	}-*/;
}
