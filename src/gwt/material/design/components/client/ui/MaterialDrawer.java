package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.html.Nav;

public class MaterialDrawer extends MaterialWidget {

	// /////////////////////////////////////////////////////////////
	// Initialize Drawer
	// /////////////////////////////////////////////////////////////
	public static native void drawerInit(Element element)/*-{
		$wnd.mdc.drawer.MDCPersistentDrawer.attachTo(element);
	}-*/;

	protected final TextMixin<MaterialDrawer> textMixin = new TextMixin<>(this);

	protected Nav nav = new Nav(CssName.MDC_PERSISTENT_DRAWER_DRAWER);	

	private HandlerRegistration handler;
	
	public MaterialDrawer() {
		super(HtmlElements.ASIDE.createElement(), CssName.MDC_PERSISTENT_DRAWER);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		add(nav);

		drawerInit(getElement());
	}

	public void open() {
		
		if (!isAttached() && handler == null) {
			handler = addAttachHandler(event -> {
				if (event.isAttached()) {
					open(getElement());
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}
			});
		} else {
			open(getElement());
		}
		
		
	}

	@UiChild(tagname = "group")
	public void addGroup(Widget child) {
		
		final Group group = new Group();
		group.add(child);
		
		add(group);
		
	}
	
	protected native void open(Element element)/*-{
		var drawer = new $wnd.mdc.drawer.MDCPersistentDrawer(element);
		console.log(drawer.open);
		drawer.open = !drawer.open;
	}-*/;
	
	@Override
	public void setBackgroundColor(Color color) {
		super.setBackgroundColor(color);
		nav.setBackgroundColor(color);
	}
	
	public class Group extends MaterialWidget {
		
		public Group() {
			super(HtmlElements.DIV.createElement(), CssName.MDC_LIST_GROUP);
		}
		
	}
}
