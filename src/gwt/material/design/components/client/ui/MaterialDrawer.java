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

import gwt.material.design.components.client.base.HasOpen;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.DrawerType;
import gwt.material.design.components.client.ui.html.Aside;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.H3;
import gwt.material.design.components.client.ui.html.H6;
import gwt.material.design.components.client.ui.html.Nav;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDrawer extends Aside implements HasType<DrawerType>, HasOpen{

	protected final TypeMixin<MaterialDrawer, DrawerType> typeMixin = new TypeMixin<>(this);
	
	public MaterialDrawer() {
		super(CssName.MDC_DRAWER);
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		
		var className = element.className;
		
		if(className.indexOf('mdc-drawer--modal') == -1 && className.indexOf('mdc-drawer--dismissible') == -1){
			return element;
		}	
				
		return new $wnd.mdc.drawer.MDCDrawer(element);
		
	}-*/;
	
	@Override
	public native boolean isOpen()/*-{
		var drawer = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		return drawer && drawer.open;
	}-*/;
	
	@Override
	public native void setOpen(boolean open)/*-{
		var drawer = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		drawer.open = open;
	}-*/;

	@Override
	public void open() {
		setOpen(true);
	}

	@Override
	public void close() {
		setOpen(false);
	}
	
	@Override
	public void setType(DrawerType type) {
		typeMixin.setType(type);
	}
	
	@Override
	public DrawerType getType() {
		return typeMixin.getType();
	}
	
	@Override
	public void setBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_DRAWER__FILL_COLOR, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_DRAWER__INK_COLOR, color.getCssName());
	}
	
	
	// ///////////////////////////////////////////
	// Sub classes
	// ///////////////////////////////////////////
	
	public static class Header extends Div {
		public Header() {
			super(CssName.MDC_DRAWER__HEADER);
		}
	}
	
	public static class Title extends H3 {
		public Title() {
			super(CssName.MDC_DRAWER__TITLE);
		}
	}
	
	public static class Subtitle extends H6 {
		public Subtitle() {
			super(CssName.MDC_DRAWER__SUBTITLE);
		}
	}
	
	public static class Content extends Nav {

		public Content() {
			super(CssName.MDC_DRAWER__CONTENT);
		}
		
	}
}
