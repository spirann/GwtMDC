package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.MaterialDrawerBase;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Nav;

public class MaterialDrawerPersistent extends MaterialDrawerBase {

	// /////////////////////////////////////////////////////////////
	// Initialize Drawer
	// /////////////////////////////////////////////////////////////
	public static native void drawerInit(Element element)/*-{
		$wnd.mdc.drawer.MDCPersistentDrawer.attachTo(element);
	}-*/;

	protected Nav nav = new Nav(CssName.MDC_PERSISTENT_DRAWER_DRAWER);

	private boolean initialized = false;

	public MaterialDrawerPersistent() {
		super(CssName.MDC_PERSISTENT_DRAWER);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			nav.add(content);
			super.add(nav, getElement());

		}
	}

	protected native boolean isOpen(Element element)/*-{
		var drawer = new $wnd.mdc.drawer.MDCPersistentDrawer(element);
		return drawer.open;
	}-*/;

	protected native void setOpen(Element element, boolean open)/*-{
		var drawer = new $wnd.mdc.drawer.MDCPersistentDrawer(element);
		drawer.open = open;
	}-*/;

	@Override
	public void setBackgroundColor(Color color) {
		super.setBackgroundColor(color);
		nav.setBackgroundColor(color);
	}

	public static class Spacer extends MaterialDrawerBase.Spacer {

		public Spacer() {
			super(CssName.MDC_PERSISTENT_DRAWER_TOOLBAR_SPACER);
		}

	}

}
