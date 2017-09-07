package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.TabBarType;
import gwt.material.design.components.client.ui.html.Div;

public class MaterialTabBarScroller extends MaterialWidget implements HasType<TabBarType>, HasChangeHandlers {

	protected JavaScriptObject javaScriptComponent;

	protected static native JavaScriptObject jsInit(final Element element)/*-{
		return $wnd.mdc.tabs.MDCTabBarScroller.attachTo(element);
	}-*/;
	
	protected Div scrollerBackIndicator = new Div(CssName.MDC_TAB_BAR_SCROLLER_INDICATOR + " " + CssName.MDC_TAB_BAR_SCROLLER_INDICATOR_BACK);
	protected MaterialWidget backIndicatorInner = new MaterialWidget(HtmlElements.A.createElement(), CssName.MDC_TAB_BAR_SCROLLER_INDICATOR_INNER, CssName.MATERIAL_ICONS);
	
	protected Div scrollerFrame = new Div(CssName.MDC_TAB_BAR_SCROLLER_SCROLL_FRAME);
	protected MaterialTabBar tabBar = new MaterialTabBar();
	
	protected Div scrollerForwardIndicator = new Div(CssName.MDC_TAB_BAR_SCROLLER_INDICATOR + " " + CssName.MDC_TAB_BAR_SCROLLER_INDICATOR_FORWARD);
	protected MaterialWidget forwardIndicatorInner = new MaterialWidget(HtmlElements.A.createElement(), CssName.MDC_TAB_BAR_SCROLLER_INDICATOR_INNER, CssName.MATERIAL_ICONS);
	
	private boolean initialized = false;
	
	public MaterialTabBarScroller(){
		super(HtmlElements.DIV.createElement(), CssName.MDC_TAB_BAR_SCROLLER);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		
		if(!initialized){
			
			backIndicatorInner.getElement().setAttribute("aria-label", "scroll back button");
			backIndicatorInner.getElement().setInnerText(IconType.NAVIGATE_BEFORE.getCssName());
			scrollerBackIndicator.add(backIndicatorInner);
			
			tabBar.addStyleName(CssName.MDC_TAB_BAR_SCROLLER_SCROLL_FRAME_TABS);
			scrollerFrame.add(tabBar);
			
			forwardIndicatorInner.getElement().setAttribute("aria-label", "scroll forward button");
			forwardIndicatorInner.getElement().setInnerText(IconType.NAVIGATE_NEXT.getCssName());
			scrollerForwardIndicator.add(forwardIndicatorInner);
			
			super.add(scrollerBackIndicator);
			super.add(scrollerFrame);
			super.add(scrollerForwardIndicator);
			
			javaScriptComponent = jsInit(getElement());
			
			initialized = true;
		}
	}
	
	@Override
	public void add(Widget child) {
		tabBar.add(child);
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return tabBar.addChangeHandler(handler);
	}

	@Override
	public void setType(TabBarType type) {
		tabBar.setType(type);
	}

	@Override
	public TabBarType getType() {
		return tabBar.getType();
	}
	
	public int getActiveTabIndex() {
		return tabBar.getActiveTabIndex();
	}

	public void setActiveTabIndex(int activeTabIndex) {
		tabBar.setActiveTabIndex(activeTabIndex);
	}
	
	@Override
	public void setTextColor(Color color) {
		super.setTextColor(color);
		backIndicatorInner.setTextColor(color);
		forwardIndicatorInner.setTextColor(color);
	}
}
