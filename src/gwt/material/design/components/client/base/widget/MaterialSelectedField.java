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
package gwt.material.design.components.client.base.widget;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.interfaces.HasSelected;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.mixin.base.PropertyMixin;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
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

	private static final String DEFAULT_CSS_CLASS = "selected";
	protected final AttributeMixin<MaterialWidget, Boolean> selectMixin = new AttributeMixin<>(this, CssAttribute.SELECTED, false, FromString.TO_BOOLEAN);
	protected PropertyMixin<Input, Boolean> checkedMixin;
	protected ToggleStyleMixin<MaterialWidget> selectedMixin;
	protected boolean fireChangeOnClick = false;
	protected boolean valueChangeHandlerInitialized = false;

	public MaterialSelectedField(final Element element, final String... initialClasses) {
		super(element, initialClasses);
		setAttribute(CssAttribute.DATA_MDC_DIALOG_ACTION, getId());
		initializeSelectedMixin();
	}

	public MaterialSelectedField(final String... initialClasses) {
		super(HtmlElements.DIV.createElement(), initialClasses);
	}

	protected final void initializeSelectedMixin(final MaterialWidget widget, final String cssClass, final Input checkedInput) {

		selectedMixin = new ToggleStyleMixin<>(widget, cssClass);
		if (checkedInput == null)
			checkedMixin = null;
		else
			checkedMixin = new PropertyMixin<>(checkedInput, CssAttribute.CHECKED, false, FromString.TO_BOOLEAN);
	}

	protected final void initializeSelectedMixin(MaterialWidget widget, String cssClass) {
		initializeSelectedMixin(widget, cssClass, null);
	}

	protected final void initializeSelectedMixin(String cssClass) {
		initializeSelectedMixin(this, cssClass, null);
	}

	protected final void initializeSelectedMixin(Input checkedInput) {
		initializeSelectedMixin(this, DEFAULT_CSS_CLASS, checkedInput);
	}

	protected final void initializeSelectedMixin() {
		initializeSelectedMixin(this, DEFAULT_CSS_CLASS, null);
	}

	private final void defaultSelectedMixin() {
		selectedMixin = new ToggleStyleMixin<>(this, DEFAULT_CSS_CLASS);
	}

	@Override
	protected void onInitialize() {
		addClickHandler(event -> {
			if (isEnabled())
				fireAutoSelect(event);
		});
		super.onInitialize();
	}

	protected void fireAutoSelect(ClickEvent event) {
		if (fireChangeOnClick && (event.getSource() == null || event.getSource() == this || !(event.getSource() instanceof HasSelectionHandlers)))
			TimerHelper.schedule(1, () -> fireSelectEvent());

		JsHelper.clearFocus();
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.formField.MDCFormField(element);
	}-*/;

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void fireSelectEvent() {
		setSelected(isSelected(), false);
		SelectionEvent.fire(MaterialSelectedField.this, isSelected());
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Boolean> handler) {
		if (!valueChangeHandlerInitialized) {
			valueChangeHandlerInitialized = true;
			addChangeHandler(event -> {
				event.preventDefault();
				event.stopPropagation();
				fireSelectEvent();
			});
		}
		return addHandler(handler, SelectionEvent.getType());
	}

	protected ToggleStyleMixin<MaterialWidget> getSelectedMixin() {
		if (selectedMixin == null)
			defaultSelectedMixin();
		return selectedMixin;
	}

	@Override
	public void setSelected(boolean selected) {
		setSelected(selected, true);
	}

	@Override
	public void setSelected(boolean selected, boolean fireEvents) {
		if (checkedMixin != null)
			checkedMixin.setValue(selected);

		getSelectedMixin().toggle(selected);

		selectMixin.setValue(selected);

		if (fireEvents && isAttached())
			fireSelectEvent();
	}

	@Override
	public boolean isSelected() {
		return checkedMixin == null ? getSelectedMixin().isApplied() : checkedMixin.getValue();
	}

}
