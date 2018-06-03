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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasCloseHandlers;
import gwt.material.design.components.client.base.HasOpen;
import gwt.material.design.components.client.base.HasOpenHandlers;
import gwt.material.design.components.client.base.HasRole;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.CloseEvent;
import gwt.material.design.components.client.events.OpenEvent;
import gwt.material.design.components.client.handlers.CloseHandler;
import gwt.material.design.components.client.handlers.OpenHandler;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialMenu extends Div implements HasOpen, HasOpenHandlers, HasCloseHandlers {

	@Override
	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialMenu::getElement()();
		this.@gwt.material.design.components.client.base.MaterialWidget::jsElement = new $wnd.mdc.menu.MDCMenu(
				element);
	}-*/;

	protected MaterialList items = new MaterialList();

	private boolean quickOpen = false;
	
	private HandlerRegistration handler;

	public MaterialMenu() {
		super(CssName.MDC_MENU);
	}

	@Override
	protected void onInitialize() {
		items.addStyleName(CssName.MDC_MENU_ITEMS);
		items.setRole(Role.MENU);
		items.getElement().setAttribute("aria-hidden", "true");		
		super.add(items);
		super.onInitialize();
	}
	
	@Override
	protected void onUnload() {
		super.onUnload();

		if (handler != null) {
			handler.removeHandler();
		}
	}

	@Override
	public void add(Widget child) {

		if (child instanceof HasRole && !(child instanceof MaterialListDivider)) {
			((HasRole) child).setRole(Role.MENU_ITEM);
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

				if (event.isAttached()) {
					setNativeOpen(open);
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}

			});

		} else {
			setNativeOpen(open);
		}
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
		var menu = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		var quickOpen = this.@gwt.material.design.components.client.ui.MaterialMenu::quickOpen;
		var oldOpen = menu.open;
		
		menu.quickOpen = quickOpen;
		menu.open = open;

		if (oldOpen != open) {
			if (open) {
				_this.@gwt.material.design.components.client.ui.MaterialMenu::fireOpenEvent()();
			} else {
				_this.@gwt.material.design.components.client.ui.MaterialMenu::fireCloseEvent()();
			}
		}

	}-*/;

	@Override
	public native boolean isOpen()/*-{
		var menu = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		return menu && menu.open;
	}-*/;

	protected void fireCloseEvent() {
		CloseEvent.fire(MaterialMenu.this);
	}

	@Override
	public HandlerRegistration addCloseHandler(CloseHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onClose(event);
			}
		}, CloseEvent.getType());
	}

	protected void fireOpenEvent() {
		OpenEvent.fire(MaterialMenu.this);
	}

	@Override
	public HandlerRegistration addOpenHandler(OpenHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onOpen(event);
			}
		}, OpenEvent.getType());
	}

	public boolean isQuickOpen(){
		return quickOpen;
	}

	public void setQuickOpen(final boolean quickOpen) {
		this.quickOpen = quickOpen;
	}
}
