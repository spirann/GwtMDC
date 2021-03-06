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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasRipple;
import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.constants.AutoInitData;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class RippleMixin<UIO extends MaterialUIObject & HasRipple> extends AbstractMixin<UIO> implements HasRipple {

	private Color color;

	private JavaScriptObject jsElement;

	private HandlerRegistration handler;

	private static final String rippleClass = CssName.MDC_RIPPLE_SURFACE + " " + CssName.MDC_RIPPLE_UPGRADED + " "
			+ CssName.MDC_RIPPLE_UPGRADED__UNBOUNDED + " " + CssName.MDC_RIPPLE_SURFACE__COLOR;

	public RippleMixin(final UIO widget) {
		super(widget);
	}

	void clearHandler() {
		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}

	@Override
	public void setRipple(Color color) {
		this.color = color;
		if (color == null && jsElement != null)
			uninitialize();
		else if (color != null && jsElement == null)
			initialize();
	}

	@Override
	public Color getRipple() {
		return color;
	}

	public void initialize() {
		uiObject.addStyleName(rippleClass);
		StyleHelper.setAttribute(uiObject, CssAttribute.DATA_MDC_AUTO_INIT, AutoInitData.MDC_RIPPLE);
		jsInit();
	}

	public void uninitialize() {
		clearHandler();
		deactivate(jsElement);
		jsElement = null;
		uiObject.removeStyleName(rippleClass);
	}

	private void jsInit() {

		if (!uiObject.isAttached() && handler == null)
			handler = uiObject.addAttachHandler(event -> {
				clearHandler();
				if (event.isAttached())
					jsElement = jsInit(uiObject.getElement());
			});
		else
			jsElement = jsInit(uiObject.getElement());

	}

	private native JavaScriptObject jsInit(final Element element)/*-{
		var jsElement = new $wnd.mdc.ripple.MDCRipple(element);
		return jsElement;
	}-*/;

	private native void deactivate(final JavaScriptObject element)/*-{
		if (element)
			jsElement.deactivate();
	}-*/;

	private native void updateColor(final JavaScriptObject element, final String color)/*-{
		if (element){
			$jQuery(jsElement).find(".mdc-ripple-surface--color::before").css("background-color", color);
			$jQuery(jsElement).find(".mdc-ripple-surface--color::after").css("background-color", color);
		}
	}-*/;

}
