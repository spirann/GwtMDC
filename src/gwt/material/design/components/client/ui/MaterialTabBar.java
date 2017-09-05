package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialTabBar extends MaterialWidget {

	protected JavaScriptObject javaScriptComponent;

	protected static native JavaScriptObject jsInit(final Element element)/*-{
		return $wnd.mdc.tabs.MDCTabBar.attachTo(element);
	}-*/;

	private Span indicator = new Span(CssName.MDC_TAB_BAR_INDICATOR);

	private boolean initialized = false;

	public MaterialTabBar() {
		super(HtmlElements.NAV.createElement(), CssName.MDC_TAB_BAR, CssName.MDC_TAB_BAR_INDICATOR_ACCENT);
		setRole(Role.TAB_BAR);
		addChangeEvent(getElement());
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			super.add(indicator);
			
			javaScriptComponent = jsInit(getElement());
			preventDefaultClick(javaScriptComponent, true);		
						
			initialized = true;

		}
	}
	
	protected native void addChangeEvent(Element element)/*-{
		
		var _this = this;
		element.addEventListener('MDCTabBar:change', function(tabs) {
			
			var dynamicTabBar = tabs.detail;
            var nthChildIndex = dynamicTabBar.activeTabIndex;
			
			_this.@gwt.material.design.components.client.ui.MaterialTabBar::select(I)(nthChildIndex);
			
			
		});
		
	}-*/;

	protected void select(int index) {
		
		for(int i = 0; i < getWidgetCount(); i++) {
			
			final Widget child = getWidget(i);
			
			if(child instanceof MaterialTab) {				
				final MaterialTab tab = (MaterialTab) child;
				tab.setActive(i == index);				
			}
			
		}
		
	}
	
	@Override
	public void add(Widget child) {
		super.add(child);
		super.add(indicator);
	}

	protected native void preventDefaultClick(final JavaScriptObject tabBar, final boolean prevent)/*-{
		tabBar.preventDefaultOnClick = prevent;
	}-*/;

	public int getActiveTabIndex() {
		return javaScriptComponent == null ? -1 : getActiveTabIndex(javaScriptComponent);
	}
	
	protected native int getActiveTabIndex(final JavaScriptObject tabBar)/*-{
		return tabBar.activeTabIndex;
	}-*/;

	public void setActiveTabIndex(final int activeTabIndex) {
		setActiveTabIndex(javaScriptComponent, activeTabIndex);	
	}
	
	protected native void setActiveTabIndex(final JavaScriptObject tabBar, int activeTabIndex)/*-{
		tabBar.activeTabIndex = activeTabIndex;
	}-*/;
}
