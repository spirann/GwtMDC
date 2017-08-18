package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasOpen;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.html.Nav;

public class MaterialDrawerTemporary extends MaterialWidget implements HasOpen {

	// /////////////////////////////////////////////////////////////
	// Initialize Drawer
	// /////////////////////////////////////////////////////////////
	public static native void drawerInit(Element element)/*-{
		$wnd.mdc.drawer.MDCTemporaryDrawer.attachTo(element);
	}-*/;

	protected Nav nav = new Nav(CssName.MDC_TEMPORARY_DRAWER_DRAWER);
	
	protected MaterialListGroup content = new MaterialListGroup();

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	private HandlerRegistration handler;

	private boolean initialized = false;

	public MaterialDrawerTemporary() {
		super(HtmlElements.ASIDE.createElement(), CssName.MDC_TEMPORARY_DRAWER);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			
			nav.add(content);
			super.add(nav);
			
			drawerInit(getElement());

		}
	}
	
	@Override
	public void add(Widget child) {
		
		if(child.getElement().getTagName().equals(HtmlElements.HEADER.getTag())) {
			nav.add(child);
			nav.add(content);
		} else {
			content.add(child);	
		}
		
		
	}

	public void open() {
		setOpen(true);	
	}

	public void close() {
		setOpen(false);
	}
	
	@Override
	public void setOpen(final boolean open) {

		if (!isAttached() && handler == null) {

			handler = addAttachHandler(event -> {

				if (event.isAttached()) {
					setOpen(getElement(), open);
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}

			});

		} else {
			setOpen(getElement(), open);
		}

	}

	@Override
	public boolean isOpen() {
		return isOpen(getElement());
	}

	protected native boolean isOpen(Element element)/*-{
		var drawer = new $wnd.mdc.drawer.MDCTemporaryDrawer(element);
		return drawer.open;
	}-*/;

	protected native void setOpen(Element element, boolean open)/*-{
		var drawer = new $wnd.mdc.drawer.MDCTemporaryDrawer(element);
		drawer.open = open;
	}-*/;

	@Override
	public void setBackgroundColor(Color color) {
		super.setBackgroundColor(color);
		nav.setBackgroundColor(color);
	}

	public static class Spacer extends MaterialWidget {

		public Spacer() {
			super(HtmlElements.DIV.createElement(), CssName.MDC_TEMPORARY_DRAWER_TOOLBAR_SPACER);
		}

	}
}
