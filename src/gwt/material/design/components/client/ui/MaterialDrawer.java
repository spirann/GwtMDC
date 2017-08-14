package gwt.material.design.components.client.ui;


import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.html.Nav;

public class MaterialDrawer extends MaterialWidget {

	protected final TextMixin<MaterialDrawer> textMixin = new TextMixin<>(this);

	protected Nav nav = new Nav(CssName.MDC_PERSISTENT_DRAWER_DRAWER);
	
	public MaterialDrawer() {
		super(HtmlElements.ASIDE.createElement(), CssName.MDC_PERSISTENT_DRAWER);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();		
		add(nav);
	}
	
	
	public void open() {
		open(getElement());
	}
	
	protected native void open(Element element)/*-{
		var drawer = new $wnd.mdc.drawer.MDCPersistentDrawer(element);
		drawer.open();
	}-*/;
}
