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

import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.interfaces.HasType;
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
public class MaterialDrawer extends Aside implements HasType<DrawerType>, HasOpen {

	protected final TypeMixin<MaterialDrawer, DrawerType> typeMixin = new TypeMixin<>(this);

	public MaterialDrawer() {
		super(CssName.MDC_DRAWER);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{

		var modalClass = @gwt.material.design.components.client.constants.CssName::MDC_DRAWER__MODAL;
		var dismissibleClass = @gwt.material.design.components.client.constants.CssName::MDC_DRAWER__DISMISSIBLE;
		var scrimClass = @gwt.material.design.components.client.constants.CssName::MDC_DRAWER_SCRIM;
		var parent = element.parentElement;
		var className = element.className;
		var isModal = className.indexOf(modalClass) != -1;
		var isDismissible = className.indexOf(dismissibleClass) != -1;
		var containsModal = @gwt.material.design.components.client.utils.helper.JsHelper::containsElementByClassName(Ljava/lang/String;Lcom/google/gwt/dom/client/Element;)(modalClass, parent);		

		@gwt.material.design.components.client.utils.helper.JsHelper::removeAllElementsByClassName(Ljava/lang/String;Lcom/google/gwt/dom/client/Element;)(scrimClass, parent);
		if (containsModal) {
			var div = $doc.createElement("div");
			div.className = scrimClass;
			parent.appendChild(div);
		}
		
		if (!isModal && !isDismissible)
			return element;

		var jsElement = new $wnd.mdc.drawer.MDCDrawer(element);
		jsElement.open = element.getAttribute("open");
		element.removeAttribute("open");

		return jsElement;

	}-*/;

	@Override
	public native boolean isOpen()/*-{
		var drawer = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		return drawer && drawer.open;
	}-*/;

	@Override
	public native void setOpen(boolean open)/*-{
		var drawer = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (drawer) {
			drawer.open = open;
		} else {
			var element = this.@gwt.material.design.components.client.ui.MaterialDrawer::getElement()();
			element.setAttribute("open", open);
		}
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
