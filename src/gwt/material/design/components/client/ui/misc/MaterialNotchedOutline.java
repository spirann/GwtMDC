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
package gwt.material.design.components.client.ui.misc;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.MaterialSvg;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialNotchedOutline extends Div {

	protected MaterialSvg notchedOutline = new MaterialSvg();
	protected Div notchedOutlineIdle = new Div(CssName.MDC_NOTCHED_OUTLINE__IDLE);
	
	public MaterialNotchedOutline() {
		super(CssName.MDC_NOTCHED_OUTLINE);
	}
	
	@Override
	protected void onInitialize() {

		notchedOutline.setResource(MaterialResources.INSTANCE.mdcNotchedOutline());
		add(notchedOutline);

		final MaterialWidget parent = (MaterialWidget) getParent();
		parent.insert(notchedOutlineIdle, parent.getWidgetIndex(this) + 1);
		
		super.onInitialize();
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.notchedOutline.MDCNotchedOutline(element);
	}-*/;
}
