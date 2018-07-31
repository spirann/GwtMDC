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

import com.gargoylesoftware.htmlunit.javascript.host.event.Event;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTabScroller extends Div {

	protected Div scrollArea = new Div(CssName.MDC_TAB_SCROLLER__SCROLL_AREA);
	protected Div scrollContent = new Div(CssName.MDC_TAB_SCROLLER__SCROLL_CONTENT);
	
	private MaterialTab selectedTab;
	
	public MaterialTabScroller() {
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
	public void add(Widget child) {
		
		if(child instanceof MaterialTab) {
			((MaterialTab) child).addSelectionHandler(Event -> {
				
				if(selectedTab != null) {
					selectedTab.setActive(false);
				}
				
				selectedTab = (MaterialTab) child;
				selectedTab.setActive(true);
				
			});
		}
		
		scrollContent.add(child);
	}
}
