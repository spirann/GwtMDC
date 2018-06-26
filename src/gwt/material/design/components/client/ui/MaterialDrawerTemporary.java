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
package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.MaterialDrawerBase;
import gwt.material.design.components.client.constants.CssName;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDrawerTemporary extends MaterialDrawerBase {

	public MaterialDrawerTemporary() {
		super(CssName.MDC_DRAWER__TEMPORARY);
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.drawer.MDCTemporaryDrawer(element);
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}

	@Override
	public native boolean isOpen()/*-{
		var drawer = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		return drawer && drawer.open;
	}-*/;

	@Override
	protected native void setNativeOpen(boolean open)/*-{
		var drawer = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		drawer.open = open;
	}-*/;

}
