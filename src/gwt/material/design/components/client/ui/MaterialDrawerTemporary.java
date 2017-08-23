package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.MaterialDrawerBase;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Nav;

public class MaterialDrawerTemporary extends MaterialDrawerBase {

	// /////////////////////////////////////////////////////////////
	// Initialize Drawer
	// /////////////////////////////////////////////////////////////
	public static native JavaScriptObject drawerInit(Element element)/*-{
		$wnd.mdc.drawer.MDCTemporaryDrawer.attachTo(element);
		var drawer = new $wnd.mdc.drawer.MDCTemporaryDrawer(element);
		return drawer;
	}-*/;
	
	protected Nav nav = new Nav(CssName.MDC_TEMPORARY_DRAWER_DRAWER);
	
	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////

	private boolean initialized = false;

	public MaterialDrawerTemporary() {
		super(CssName.MDC_TEMPORARY_DRAWER);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			
			nav.add(content);
			super.add(nav, getElement());
			
			drawer = drawerInit(getElement());

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
			super(CssName.MDC_TEMPORARY_DRAWER_TOOLBAR_SPACER);
		}

	}
}
