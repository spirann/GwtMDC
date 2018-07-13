/*
 * #%L
 * Gwt Material Design Components
 * %%
 * Copyright (C) 2017 - 2017 Gwt Material Design Components
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.CssProperty;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.TabBarType;
import gwt.material.design.components.client.ui.html.Anchor;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTabBarScroller extends Div implements HasType<TabBarType>, HasChangeHandlers {

	protected Div scrollerBackIndicator = new Div(CssName.MDC_TAB_BAR_SCROLLER__INDICATOR,
			CssName.MDC_TAB_BAR_SCROLLER__INDICATOR__BACK);
	protected Anchor backIndicatorInner = new Anchor(CssName.MDC_TAB_BAR_SCROLLER__INDICATOR__INNER,
			CssName.MATERIAL_ICONS);

	protected Div scrollerFrame = new Div(CssName.MDC_TAB_BAR_SCROLLER__SCROLL_FRAME);
	protected MaterialTabBar tabBar = new MaterialTabBar() {
		@Override
		protected native JavaScriptObject jsInit(final Element element)/*-{
			return new $wnd.mdc.tabs.MDCTabBarFoundation(element);
		}-*/;
	};

	protected Div scrollerForwardIndicator = new Div(CssName.MDC_TAB_BAR_SCROLLER__INDICATOR,
			CssName.MDC_TAB_BAR_SCROLLER__INDICATOR__FORWARD);
	protected Anchor forwardIndicatorInner = new Anchor(CssName.MDC_TAB_BAR_SCROLLER__INDICATOR__INNER,
			CssName.MATERIAL_ICONS);

	private boolean initialized = false;

	public MaterialTabBarScroller() {
		super(CssName.MDC_TAB_BAR_SCROLLER);
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.tabs.MDCTabBarScroller(element);
	}-*/;

	@Override
	protected void onInitialize() {

		backIndicatorInner.getElement().setAttribute("aria-label", "scroll back button");
		backIndicatorInner.getElement().setInnerText(IconType.NAVIGATE_BEFORE.getCssName());
		scrollerBackIndicator.add(backIndicatorInner);

		tabBar.addStyleName(CssName.MDC_TAB_BAR_SCROLLER__SCROLL_FRAME__TABS);
		scrollerFrame.add(tabBar);

		forwardIndicatorInner.getElement().setAttribute("aria-label", "scroll forward button");
		forwardIndicatorInner.getElement().setInnerText(IconType.NAVIGATE_NEXT.getCssName());
		scrollerForwardIndicator.add(forwardIndicatorInner);

		super.add(scrollerBackIndicator);
		super.add(scrollerFrame);
		super.add(scrollerForwardIndicator);
		
		super.onInitialize();
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

	public void setActiveColor(Color color) {
		tabBar.setActiveColor(color);
		setStyleProperty(CssProperty.MDC_TAB_BAR__ACTIVE_COLOR, color.getCssName());
		setStyleProperty(CssProperty.MDC_TAB_BAR__ACTIVE_RIPPLE_COLOR, color.getCssName());	
	}
	
	@Override
	public void setTextColor(Color color) {
		super.setTextColor(color);
		tabBar.setTextColor(color);
		setStyleProperty(CssProperty.MDC_TAB_BAR__COLOR, color.getCssName());
		setStyleProperty(CssProperty.MDC_TAB_BAR__RIPPLE_COLOR, color.getCssName());	
	}
}
