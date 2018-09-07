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
package gwt.material.design.components.client.ui.form;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.HasSelected;
import gwt.material.design.components.client.base.HasSelectionHandlers;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.CheckedMixin;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSelectedField extends MaterialWidget implements HasSelected, HasSelectionHandlers<Boolean> {

	protected ApplyStyleMixin<MaterialWidget> selectedMixin;
	protected CheckedMixin<Input> checkedMixin;
	protected boolean fireChangeOnClick = false;
	private boolean valueChangeHandlerInitialized = false;

	public MaterialSelectedField(final Element element, final String... initialClasses) {
		super(element, initialClasses);
		initializeSelectedMixin();
	}
	
	public MaterialSelectedField(final String... initialClasses) {
		this(HtmlElements.DIV.createElement(), initialClasses);
	}
	
	protected void initializeSelectedMixin(MaterialWidget widget, String cssClass, Input checkedInput) {		
		selectedMixin = new ApplyStyleMixin<>(widget, cssClass);
		if(checkedInput == null) {
			checkedMixin = null;
		} else {
			checkedMixin = new CheckedMixin<>(checkedInput);
		}
	}
	
	protected void initializeSelectedMixin(MaterialWidget widget, String cssClass) {
		initializeSelectedMixin(widget, cssClass, null);
	}
	
	protected void initializeSelectedMixin(String cssClass) {
		initializeSelectedMixin(this, cssClass, null);
	}
	
	protected void initializeSelectedMixin(Input checkedInput) {
		initializeSelectedMixin(this, "selected", checkedInput);
	}
	
	private void defaultSelectedMixin() {
		selectedMixin = new ApplyStyleMixin<>(this, "selected");
	}
	
	protected void initializeSelectedMixin() {
		initializeSelectedMixin(this, "selected", null);
	}
		
	@Override
	protected void onInitialize() {
		addClickHandler(event -> fireAutoSelect(event));
		super.onInitialize();
	}
	
	protected void fireAutoSelect(ClickEvent event) {

		if(fireChangeOnClick && (event.getSource() == null || event.getSource() == this || !(event.getSource() instanceof HasSelectionHandlers))) {			
			TimerHelper.schedule(1, () -> fireChangeEvent());
		}		

		JsHelper.clearFocus();
	}	
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.formField.MDCFormField(element);
	}-*/;

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void fireChangeEvent() {
		setSelected(isSelected(), false);
		SelectionEvent.fire(MaterialSelectedField.this, isSelected());
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Boolean> handler) {
		if (!valueChangeHandlerInitialized) {
			valueChangeHandlerInitialized = true;
			addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					event.preventDefault();
					event.stopPropagation();
					fireChangeEvent();
				}
			});
		}
		return addHandler(handler, SelectionEvent.getType());
	}

	protected ApplyStyleMixin<MaterialWidget> getSelectedMixin(){		
		if(selectedMixin == null) {
			defaultSelectedMixin();
		}		
		return selectedMixin;
	}
	
	@Override
	public void setSelected(boolean selected) {
		setSelected(selected, true);
	}
	
	@Override
	public void setSelected(boolean selected, boolean fireEvents) {
		
		if(checkedMixin != null) {
			checkedMixin.setChecked(selected);
		}
		
		getSelectedMixin().setApply(selected);
		
		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public boolean isSelected() {
		return checkedMixin == null ? getSelectedMixin().isApplied() : checkedMixin.isChecked();
	}

}
