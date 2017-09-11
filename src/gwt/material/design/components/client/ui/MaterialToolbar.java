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
import com.google.gwt.core.client.Scheduler;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.ToolbarType;
import gwt.material.design.components.client.ui.html.Header;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialToolbar extends Header implements HasType<ToolbarType> {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialToolbar::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialToolbar::jsElement = $wnd.mdc.toolbar.MDCToolbar.attachTo(element);
	}-*/;
		
	protected final TypeMixin<MaterialToolbar, ToolbarType> typeMixin = new TypeMixin<>(this);

	private boolean initialize = false;

	public MaterialToolbar() {
		super(CssName.MDC_TOOLBAR);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialize) {			
			
			jsInit();			
			
			initialize = true;
			
			Scheduler.get().scheduleFixedDelay(() -> {				
				fixedAdjustElement();				
				return false;				
			}, 100);
		}
	}
	
	protected native void fixedAdjustElement()/*-{
		var toolbar = this.@gwt.material.design.components.client.ui.MaterialToolbar::jsElement
		toolbar.fixedAdjustElement = $doc.querySelector('.mdc-toolbar-fixed-adjust');
	}-*/;
	
	@Override
	public void setType(ToolbarType type) {
		typeMixin.setType(type);
	}

	@Override
	public ToolbarType getType() {
		return typeMixin.getType();
	}
}
