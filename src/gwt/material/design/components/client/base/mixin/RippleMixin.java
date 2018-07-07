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
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasRipple;
import gwt.material.design.components.client.constants.AutoInitData;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.CssProperty;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class RippleMixin<W extends Widget> extends StyleMixin<W> implements HasRipple {

	private Color color;

	private JavaScriptObject jsElement;

	private String rippleClass = CssName.MDC_RIPPLE_SURFACE + " " + CssName.MDC_RIPPLE_UPGRADED + " "
			+ CssName.MDC_RIPPLE_UPGRADED__UNBOUNDED + " " + CssName.MDC_RIPPLE_SURFACE__COLOR;

	public RippleMixin(final W widget) {
		super(widget);
		
	}

	@Override
	public void setRipple(Color color) {
		
		this.color = color;

		if (color == null) {

			if (jsElement != null) {
				uninitialize();
			}

		} else {
			
			if (jsElement == null) {
				initialize();
			}

			StyleHelper.setStyleProperty(uiObject.getElement(), CssProperty.MDC_RIPPLE__COLOR, color.getCssName());
		}

	}

	@Override
	public Color getRipple() {
		return color;
	}
	
	public void initialize() {
		setStyle(rippleClass);
		uiObject.getElement().setAttribute("data-mdc-auto-init", AutoInitData.MDC_RIPPLE.getCssName());
		jsInit();
	}
	
	public void uninitialize() {
		deactivate(jsElement);
		jsElement = null;
		setStyle(null);
	}

	private void jsInit() {
		jsElement = jsInit(uiObject.getElement());
	}

	private native JavaScriptObject jsInit(final Element element)/*-{
		var jsElement = new $wnd.mdc.ripple.MDCRipple(element);
		//jsElement.unbounded = true;
		return jsElement;
	}-*/;

	private native void deactivate(final JavaScriptObject element)/*-{
		if (element) {
			jsElement.deactivate();
		}
	}-*/;

}
