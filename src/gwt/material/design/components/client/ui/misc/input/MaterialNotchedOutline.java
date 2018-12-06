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
package gwt.material.design.components.client.ui.misc.input;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialNotchedOutline extends Div {

	protected final Div leading = new Div(CssName.MDC_NOTCHED_OUTLINE__LEADING);
	protected final Div notch = new Div(CssName.MDC_NOTCHED_OUTLINE__NOTCH);
	protected final Div trailing = new Div(CssName.MDC_NOTCHED_OUTLINE__TRAILING);
	
	public MaterialNotchedOutline() {
		super(CssName.MDC_NOTCHED_OUTLINE);
	}
	
	@Override
	protected void onInitialize() {
		super.add(leading);
		super.add(notch);
		super.add(trailing);		
		super.onInitialize();
	}
	
	@Override
	public void add(Widget child) {
		notch.add(child);
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.notchedOutline.MDCNotchedOutline(element);
	}-*/;
}
