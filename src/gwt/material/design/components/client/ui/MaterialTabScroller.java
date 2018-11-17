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

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasAlign;
import gwt.material.design.components.client.base.interfaces.HasSelectionHandlers;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.TabScrollerAlign;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.utils.helper.JsHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTabScroller extends Div implements HasAlign<TabScrollerAlign>, HasSelectionHandlers<MaterialTab> {

	protected Div scrollArea = new Div(CssName.MDC_TAB_SCROLLER__SCROLL_AREA);
	protected Div scrollContent = new Div(CssName.MDC_TAB_SCROLLER__SCROLL_CONTENT);

	protected final TypeMixin<MaterialTabScroller, TabScrollerAlign> alignMixin = new TypeMixin<>(this);

	private Integer selectedTabIndex;
	private MaterialTab selectedTab;

	protected MaterialTabScroller() {
		super(CssName.MDC_TAB_SCROLLER);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.tabScroller.MDCTabScroller(element);
	}-*/;

	@Override
	protected void onInitialize() {
		scrollArea.add(scrollContent);
		super.add(scrollArea);
		super.onInitialize();
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		setSelectedTab(selectedTabIndex == null ? 0 : selectedTabIndex);
	}

	public void setSelectedTab(final int index) {

		selectedTabIndex = index;

		if (!initialized) {
			return;
		}

		final MaterialTab tab = (MaterialTab) scrollContent.getWidget(index);
		if (selectedTab != null && tab == selectedTab) {
			return;
		}

		setSelectedTab(tab);
	}

	public void setSelectedTab(final MaterialTab tab) {
		final boolean isFirst = selectedTab == null;
		selectedTab = tab;
		if (isFirst) {
			selectedTab.setSelected(true, false);
		} else {
			JsHelper.doClick(selectedTab.getElement());
		}
	}

	public MaterialTab getSelectedTab() {
		return selectedTab;
	}

	@Override
	public void add(Widget child) {

		if (child instanceof MaterialTab) {
			((MaterialTab) child).addSelectionHandler(event -> {
				if (event.getValue()) {
					selectedTab = (MaterialTab) child;
					fireSelectionEvent(selectedTab);
				}
			});
		}

		scrollContent.add(child);
	}

	protected void fireSelectionEvent(final MaterialTab tab) {
		SelectionEvent.fire(this, tab);
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<MaterialTab> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

	public List<MaterialTab> getTabs() {

		final List<MaterialTab> tabs = new LinkedList<>();

		if (onLoadAdd != null)
			for (Appender item : onLoadAdd) {
				if (item.widget instanceof MaterialTab)
					tabs.add((MaterialTab) item.widget);
			}

		for (int i = 0; i < scrollContent.getWidgetCount(); i++) {
			final Widget widget = scrollContent.getWidget(i);
			if (widget instanceof MaterialTab)
				tabs.add((MaterialTab) widget);
		}

		return tabs;
	}

	@Override
	public void setAlign(final TabScrollerAlign align) {
		alignMixin.setType(align);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public TabScrollerAlign getAlign() {
		return alignMixin.getType();
	}

	@Override
	public void setColor(Color color) {
		setTextColor(color);
	}

	@Override
	public void setTextColor(Color color) {
		setCssProperty(CssMixin.MDC_TAB__COLOR, color.getCssName());
	}

	public void setSelectedColor(Color color) {
		setCssProperty(CssMixin.MDC_TAB__ACTIVED_COLOR, color.getCssName());
	}
}
