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
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Nav;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDrawerTemporary extends MaterialDrawerBase {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialDrawerTemporary::getElement()();
		this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement = $wnd.mdc.drawer.MDCTemporaryDrawer.attachTo(element);
	}-*/;
	
	protected Nav nav = new Nav(CssName.MDC_TEMPORARY_DRAWER_DRAWER);

	private boolean initialized = false;

	public MaterialDrawerTemporary() {
		super(CssName.MDC_TEMPORARY_DRAWER);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			
			nav.add(content);
			super.add(nav, getElement());
			
			jsInit();

			initialized = true;
		}
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

	@Override
	public void setBackgroundColor(Color color) {
		super.setBackgroundColor(color);
		nav.setBackgroundColor(color);
	}

	public static class Spacer extends MaterialDrawerBase.Spacer {

		public Spacer() {
			super(CssName.MDC_TEMPORARY_DRAWER_TOOLBAR_SPACER);
		}

	}
}
