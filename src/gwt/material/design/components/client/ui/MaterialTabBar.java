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
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.constants.TabBarTextColor;
import gwt.material.design.components.client.constants.TabBarType;
import gwt.material.design.components.client.ui.html.Nav;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTabBar extends Nav implements HasType<TabBarType>, HasChangeHandlers {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{

		var element = this.@gwt.material.design.components.client.ui.MaterialTabBar::getElement()();

		if (element.parentNode.className
				.indexOf('mdc-tab-bar-scroller__scroll-frame') > -1) {
			return this.@gwt.material.design.components.client.ui.MaterialTabBar::jsElement = new $wnd.mdc.tabs.MDCTabBarFoundation(
					element);
		} else {
			return this.@gwt.material.design.components.client.ui.MaterialTabBar::jsElement = $wnd.mdc.tabs.MDCTabBar
					.attachTo(element);
		}

	}-*/;

	protected Span indicator = new Span(CssName.MDC_TAB_BAR_INDICATOR);
	protected final TypeMixin<MaterialTabBar, TabBarType> typeMixin = new TypeMixin<>(this);
	protected final TypeMixin<MaterialTabBar, TabBarTextColor> colorMixin = new TypeMixin<>(this);
	protected int activeTabIndex = -1;

	private boolean initialized = false;

	public MaterialTabBar() {
		super(CssName.MDC_TAB_BAR);

		setRole(Role.TAB_BAR);
		setType(TabBarType.ICON_WITH_TEXT);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			super.add(indicator);

			addChangeEvent(getElement());

			jsInit();
			preventDefaultClick(true);
			loadFirstSelected();

			initialized = true;

		}
	}

	protected native void addChangeEvent(Element element)/*-{

		var _this = this;
		element.addEventListener('MDCTabBar:change', function(tabs) {
			var dynamicTabBar = tabs.detail;
			var nthChildIndex = dynamicTabBar.activeTabIndex;
			_this.@gwt.material.design.components.client.ui.MaterialTabBar::setActiveTabIndex(I)(nthChildIndex);
		});

	}-*/;

	protected void fireChangeEvent() {
		ChangeEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
	}

	protected void loadFirstSelected() {

		for (int i = 0; i < getWidgetCount(); i++) {

			final Widget child = getWidget(i);

			if (child instanceof MaterialTab) {
				final MaterialTab tab = (MaterialTab) child;				
				if (tab.isActive()) {
					setActiveTabIndex(i);
				}
			}

		}
	}

	protected void updateTabs(final int oldIndex, final int newIndex) {

		if (oldIndex > -1) {
			final Widget oldChild = getWidget(oldIndex);

			if (oldChild instanceof MaterialTab) {
				final MaterialTab tab = (MaterialTab) oldChild;
				tab.setActive(false, false);
			}
		}

		if (newIndex > -1) {
			final Widget newChild = getWidget(newIndex);

			if (newChild instanceof MaterialTab) {
				final MaterialTab tab = (MaterialTab) newChild;
				tab.setActive(true, false);
			}
		}
	}

	@Override
	public void add(Widget child) {
		super.add(child);
		super.add(indicator);
	}

	protected native void preventDefaultClick(final boolean prevent)/*-{

		var tabBar = this.@gwt.material.design.components.client.ui.MaterialTabBar::jsElement;
		if (tabBar) {
			tabBar.preventDefaultOnClick = prevent;
		}

	}-*/;

	public int getActiveTabIndex() {
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {

		if (activeTabIndex < 0) {
			throw new IllegalArgumentException("Invalid value: " + activeTabIndex);
		}

		final int oldActiveTabIndex = this.activeTabIndex;
		this.activeTabIndex = activeTabIndex;

		if (isAttached() && this.activeTabIndex != oldActiveTabIndex && oldActiveTabIndex != -1) {
			this.activeTabIndex = activeTabIndex;

			updateTabs(oldActiveTabIndex, activeTabIndex);
			setNativeActiveTabIndex(activeTabIndex);
			fireChangeEvent();
		}
	}

	protected native void setNativeActiveTabIndex(final int activeTabIndex)/*-{
		var tabBar = this.@gwt.material.design.components.client.ui.MaterialTabBar::jsElement;
		tabBar.activeTabIndex = activeTabIndex;
	}-*/;

	@Override
	public void setType(TabBarType type) {
		typeMixin.setType(type);
	}

	@Override
	public TabBarType getType() {
		return typeMixin.getType();
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onChange(event);
			}
		}, ChangeEvent.getType());
	}

	public void setColorScheme(TabBarTextColor barColor) {
		colorMixin.setType(barColor);
	}
}
