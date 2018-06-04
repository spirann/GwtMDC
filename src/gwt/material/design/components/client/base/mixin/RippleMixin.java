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
import gwt.material.design.components.client.constants.Ripple;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class RippleMixin<W extends Widget> extends StyleMixin<W> implements HasRipple {

	private Ripple type;
	
	private JavaScriptObject jsElement;
	
	public RippleMixin(final W widget) {
		super(widget);
	}

	@Override
	public void setRipple(Ripple type) {
		this.type = type;
		
		if (type == null) {
			setStyle(null);
		} else {
			setStyle(type.getCssName());
		}
		
		if(jsElement == null) {
			jsInit();
		}				
		
	}

	@Override
	public Ripple getRipple() {
		return type;
	}
	
	private void jsInit() {
		jsElement = jsInit(uiObject.getElement());
	}

	private native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.ripple.MDCRipple(element);
	}-*/;
}
