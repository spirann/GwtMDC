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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.interfaces.HasRole;
import gwt.material.design.components.client.base.interfaces.HasToggler;
import gwt.material.design.components.client.base.mixin.TogglerMixin;
import gwt.material.design.components.client.constants.CloseAction;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.CloseEvent;
import gwt.material.design.components.client.events.CloseEvent.CloseHandler;
import gwt.material.design.components.client.events.CloseEvent.HasCloseHandlers;
import gwt.material.design.components.client.events.OpenEvent;
import gwt.material.design.components.client.events.OpenEvent.HasOpenHandlers;
import gwt.material.design.components.client.events.OpenEvent.OpenHandler;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialMenu extends Div implements HasOpen, HasOpenHandlers, HasCloseHandlers, HasToggler {

	protected final MaterialList items = new MaterialList();

	protected final TogglerMixin<MaterialMenu> togglerMixin = new TogglerMixin<>(this);

	private boolean quickOpen = false;
	private boolean closeOnClick = false;

	private HandlerRegistration handler;

	public MaterialMenu() {
		super(CssName.MDC_MENU);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.menuSurface.MDCMenuSurface(element);
	}-*/;

	@Override
	protected void onInitialize() {

		items.addStyleName(CssName.MDC_MENU__ITEMS);
		items.setRole(Role.MENU);
		items.getElement().setAttribute(CssAttribute.ARIA_HIDDEN, Boolean.TRUE.toString().toLowerCase());

		super.add(items);
		super.onInitialize();

		addNativeCloseEvent(getElement());

	}

	protected native void addNativeCloseEvent(Element element)/*-{
		var _this = this;
		var handler = function(event) {
			_this.@gwt.material.design.components.client.ui.MaterialMenu::fireCloseEvent()();
		};
		element.addEventListener('MDCMenu:cancel', handler);
	}-*/;

	@Override
	protected void onUnload() {
		super.onUnload();
		if (handler != null)
			handler.removeHandler();
	}

	@Override
	public void add(Widget child) {

		if (child instanceof HasRole && !(child instanceof MaterialDivider))
			((HasRole) child).setRole(Role.MENU_ITEM);

		if (!(child instanceof MaterialDivider)) {

			child.addDomHandler(event -> {

				final String innerHtml = child.getElement().getInnerHTML();
				final boolean prevent = innerHtml.contains("prevent=\"true\"");

				if (((child instanceof HasEnabled) && !((HasEnabled) child).isEnabled()) || prevent)
					return;

				if (closeOnClick)
					close();

			}, ClickEvent.getType());

		}

		items.add(child);
	}

	@Override
	public Widget getWidget(int index) {
		return items.getWidget(index);
	}

	@Override
	public int getWidgetCount() {
		return items.getWidgetCount();
	}

	@Override
	public int getWidgetIndex(Widget child) {
		return items.getWidgetIndex(child);
	}

	@Override
	public void setOpen(boolean open) {

		if (!isAttached() && handler == null) {

			handler = addAttachHandler(event -> {

				if (event.isAttached())
					setNativeOpen(open);
				else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}

			});

		} else
			setNativeOpen(open);
	}

	@Override
	public void open() {
		setOpen(true);
	}

	@Override
	public void close() {
		setOpen(false);
	}

	protected native void setNativeOpen(boolean open)/*-{

		var _this = this;
		var menu = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		var quickOpen = this.@gwt.material.design.components.client.ui.MaterialMenu::quickOpen;
		var oldOpen = menu.open;

		menu.quickOpen = quickOpen;
		menu.open = open;

		if (oldOpen != open) {
			if (open)
				_this.@gwt.material.design.components.client.ui.MaterialMenu::fireOpenEvent()();
			else
				_this.@gwt.material.design.components.client.ui.MaterialMenu::fireCloseEvent()();
		}

	}-*/;

	@Override
	public native boolean isOpen()/*-{
		var menu = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		return menu && menu.open;
	}-*/;

	protected void fireCloseEvent() {
		CloseEvent.fire(MaterialMenu.this, CloseAction.NONE);
	}

	@Override
	public HandlerRegistration addCloseHandler(CloseHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onClose(event);
		}, CloseEvent.getType());
	}

	protected void fireOpenEvent() {
		OpenEvent.fire(MaterialMenu.this);
	}

	@Override
	public HandlerRegistration addOpenHandler(OpenHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onOpen(event);
		}, OpenEvent.getType());
	}

	@Override
	public void setToggler(String togglerId) {
		togglerMixin.setToggler(togglerId);
	}

	@Override
	public String getToggler() {
		return togglerMixin.getToggler();
	}

	public boolean isQuickOpen() {
		return quickOpen;
	}

	public void setQuickOpen(final boolean quickOpen) {
		this.quickOpen = quickOpen;
	}

	public boolean isCloseOnClick() {
		return closeOnClick;
	}

	public void setCloseOnClick(boolean closeOnClick) {
		this.closeOnClick = closeOnClick;
	}

}
