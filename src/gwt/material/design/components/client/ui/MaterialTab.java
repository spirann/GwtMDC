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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasActive;
import gwt.material.design.components.client.base.HasSelectionHandlers;
import gwt.material.design.components.client.base.mixin.ActiveMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTab extends Button implements HasText, HasActive, HasSelectionHandlers<MaterialTab> {

	protected Span content = new Span(CssName.MDC_TAB__CONTENT);
	protected Span label = new Span(CssName.MDC_TAB__TEXT_LABEL);
	
	protected MaterialTabIndicator indicator = new MaterialTabIndicator();
	
	protected Span ripple = new Span(CssName.MDC_TAB__RIPPLE);
	
	protected ActiveMixin<MaterialTab> activeMixin = new ActiveMixin<>(this, CssName.MDC_TAB__ACTIVE);
	
	public MaterialTab() {
		super(CssName.MDC_TAB);
		setRole(Role.TAB);
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.tab.MDCTab(element);
	}-*/;	
	
	@Override
	protected void onInitialize() {

		setTabindex(-1);
		setAriaSelected(false);

		content.add(label);
		
		add(content);		
		add(indicator);		
		add(ripple);
		
		addNativeSelectEvent(getElement());
		
		super.onInitialize();
	}

	protected native void addNativeSelectEvent(Element element)/*-{

		var _this = this;
		element.addEventListener('MDCTab:interacted', function(event) {
			_this.@gwt.material.design.components.client.ui.MaterialTab::fireSelectionEvent()();
		});
	
	}-*/;
	
	protected void fireSelectionEvent() {
		SelectionEvent.fire(this, this);
	}	
	
	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<MaterialTab> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}
	
	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}
	
	@Override
	public void setActive(boolean active) {
		super.setActive(active);
		activeMixin.setActive(active);
		indicator.setActive(active);
		setAriaSelected(active);		
		setTabindex(active ? 0 : -1);
	}
	
	@Override
	public boolean isActive() {
		return activeMixin.isActive();
	}
}
