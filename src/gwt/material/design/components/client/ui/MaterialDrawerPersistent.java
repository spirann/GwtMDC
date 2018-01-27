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

import gwt.material.design.components.client.base.MaterialDrawerBase;
import gwt.material.design.components.client.constants.CssName;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDrawerPersistent extends MaterialDrawerBase {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialDrawerPersistent::getElement()();
		this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement = $wnd.mdc.drawer.MDCPersistentDrawer
				.attachTo(element);
	}-*/;

	public MaterialDrawerPersistent() {
		super(CssName.MDC_DRAWER_PERSISTENT);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		jsInit();
	}

	@Override
	public native boolean isOpen()/*-{
		var drawer = this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement;
		return drawer && drawer.open;
	}-*/;

	@Override
	protected native void setNativeOpen(boolean open)/*-{
		var drawer = this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement;
		drawer.open = open;
	}-*/;

}
