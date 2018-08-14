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

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasAlign;
import gwt.material.design.components.client.base.HasSelectionHandlers;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.constants.TabScrollerAlign;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTabBar extends Div implements HasAlign<TabScrollerAlign>, HasSelectionHandlers<MaterialTab> {

	protected MaterialTabScroller scrollArea = new MaterialTabScroller();

	public MaterialTabBar() {
		super(CssName.MDC_TAB_BAR);
		setRole(Role.TAB_BAR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.tabBar.MDCTabBar(element);
	}-*/;

	@Override
	protected void onInitialize() {
		super.add(scrollArea);
		super.onInitialize();
	}

	@Override
	public void add(Widget child) {
		scrollArea.add(child);
	}
	
	public void setSelectedTab(final int index) {
		scrollArea.setSelectedTab(index);
	}
	
	public void setSelectedTab(final MaterialTab tab) {
		scrollArea.setSelectedTab(tab);
	}

	public MaterialTab getSelectedTab() {
		return scrollArea.getSelectedTab();
	}
	
	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<MaterialTab> handler) {
		return scrollArea.addSelectionHandler(handler);
	}
	
	public List<MaterialTab> getTabs(){
		return scrollArea.getTabs();
	}
	
	@Override
	public void setAlign(final TabScrollerAlign align) {
		scrollArea.setAlign(align);
	}

	@Override
	public TabScrollerAlign getAlign() {
		return scrollArea.getAlign();
	}
	
	@Override
	public void setColor(Color color) {
		scrollArea.setColor(color);
	}
	
	@Override
	public void setTextColor(Color color) {
		scrollArea.setTextColor(color);
	}

	public void setSelectedColor(Color color) {
		scrollArea.setSelectedColor(color);
	}
}
