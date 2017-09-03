package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.MaterialDrawerBase;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Nav;

public class MaterialDrawerPersistent extends MaterialDrawerBase {

	// /////////////////////////////////////////////////////////////
	// Initialize Drawer
	// /////////////////////////////////////////////////////////////
	public static native JavaScriptObject jsInit(Element element)/*-{
		return $wnd.mdc.drawer.MDCPersistentDrawer.attachTo(element);
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
			
			drawer = jsInit(getElement());

			initialized = true;
		}
	}

	@Override
	protected native boolean isOpen(final JavaScriptObject drawer)/*-{
		return drawer.open;
	}-*/;

	@Override
	protected native void setOpen(final JavaScriptObject drawer, boolean open)/*-{
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
