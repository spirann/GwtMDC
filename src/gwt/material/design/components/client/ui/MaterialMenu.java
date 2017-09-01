package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasOpen;
import gwt.material.design.components.client.base.HasRole;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Role;

public class MaterialMenu extends MaterialWidget implements HasOpen {

	protected JavaScriptObject menu;

	public static native JavaScriptObject menuInit(Element element)/*-{
		return  $wnd.mdc.menu.MDCSimpleMenu.attachTo(element);
	}-*/;

	private MaterialList items = new MaterialList();

	private HandlerRegistration handler;

	private boolean initialized = false;

	public MaterialMenu() {
		super(HtmlElements.DIV.createElement(), CssName.MDC_MENU);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			items.addStyleName(CssName.MDC_MENU_ITEMS);
			items.setRole(Role.MENU);
			items.getElement().setAttribute("aria-hidden", "true");
			super.add(items);

			menu = menuInit(getElement());

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
					setOpen(menu, open);
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}

			});

		} else {
			setOpen(menu, open);
		}
	}

	@Override
	public boolean isOpen() {
		return menu != null && isOpen(menu);
	}

	@Override
	public void open() {
		setOpen(true);
	}

	@Override
	public void close() {
		setOpen(false);
	}

	protected native void setOpen(final JavaScriptObject menu, boolean open)/*-{
		menu.open = open;
	}-*/;

	protected native boolean isOpen(final JavaScriptObject menu)/*-{
		return menu.open;
	}-*/;
}
