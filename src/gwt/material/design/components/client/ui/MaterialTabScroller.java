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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasSelectionHandlers;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTabScroller extends Div implements HasSelectionHandlers<MaterialTab> {

	protected Div scrollArea = new Div(CssName.MDC_TAB_SCROLLER__SCROLL_AREA);
	protected Div scrollContent = new Div(CssName.MDC_TAB_SCROLLER__SCROLL_CONTENT);
	
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
		
	protected native void scrollTo(final int tab)/*-{
		var scroller = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		scroller.scrollTo(tab);		
	}-*/;
	
	@Override
	public void add(Widget child) {
		
		if(child instanceof MaterialTab) {
			
			final boolean isEmpty = scrollContent.getChildrenList().isEmpty();
			GWT.log("isEmpty: " + isEmpty);
			((MaterialTab) child).setActive(isEmpty);			
			((MaterialTab) child).addSelectionHandler(event -> {
				/*
				if(selectedTab != null) {
					selectedTab.setActive(false);
				}
				
				selectedTab = (MaterialTab) child;
				selectedTab.setActive(true);				
				*/
				selectedTab = (MaterialTab) child;
				fireSelectionEvent(selectedTab);
				
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
}
