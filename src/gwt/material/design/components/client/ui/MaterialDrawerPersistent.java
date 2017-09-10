package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.MaterialDrawerBase;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Nav;

public class MaterialDrawerPersistent extends MaterialDrawerBase {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialDrawerPersistent::getElement()();
		this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement = $wnd.mdc.drawer.MDCPersistentDrawer.attachTo(element);
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

			jsInit();

			initialized = true;
		}
	}

	@Override
	public native boolean isOpen()/*-{
		var drawer = this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement;
		return drawer && drawer.open;
	}-*/;

	@Override
	protected native void setNativeOpen(boolean open)/*-{
		var drawer = this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement;
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
