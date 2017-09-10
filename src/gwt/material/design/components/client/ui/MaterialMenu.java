package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
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

public class MaterialMenu extends Div implements HasOpen, HasOpenHandlers, HasCloseHandlers {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialMenu::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialMenu::jsElement = $wnd.mdc.menu.MDCSimpleMenu.attachTo(element);
	}-*/;

	protected MaterialList items = new MaterialList();

	private HandlerRegistration handler;

	private boolean initialized = false;

	public MaterialMenu() {
		super(CssName.MDC_MENU);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			items.addStyleName(CssName.MDC_MENU_ITEMS);
			items.setRole(Role.MENU);
			items.getElement().setAttribute("aria-hidden", "true");
			super.add(items);

			jsInit();

			initialized = true;
		}
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
		var menu = this.@gwt.material.design.components.client.ui.MaterialMenu::jsElement;
		var oldOpen = menu.open;		
		menu.open = open;
		
		if(oldOpen != open){
			if(open){
				_this.@gwt.material.design.components.client.ui.MaterialMenu::fireOpenEvent()();
			} else {	
				_this.@gwt.material.design.components.client.ui.MaterialMenu::fireCloseEvent()();
			}
		}
		
	}-*/;

	@Override
	public native boolean isOpen()/*-{		
		var menu = this.@gwt.material.design.components.client.ui.MaterialMenu::jsElement;		
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

}
