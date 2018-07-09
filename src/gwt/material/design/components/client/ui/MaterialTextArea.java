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
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.HasHelperText;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.base.HasPlaceholder;
import gwt.material.design.components.client.base.HasRequired;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.PlaceholderMixin;
import gwt.material.design.components.client.base.mixin.RequiredMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FlexDirection;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.ui.html.Textarea;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTextArea extends MaterialFormField<String>
		implements HasText, HasLabel, HasDense, HasRequired, HasHelperText, HasPlaceholder {

	// /////////////////////////////////////////////////////////////
	// Textarea
	// /////////////////////////////////////////////////////////////
	protected Div textField = new Div(CssName.MDC_TEXT_FIELD, CssName.MDC_TEXT_FIELD_MULTILINE);
	protected Textarea textarea = new Textarea(CssName.MDC_TEXT_FIELD__INPUT);

	// /////////////////////////////////////////////////////////////
	// Label
	// /////////////////////////////////////////////////////////////
	protected Label label = new Label();
	protected Label helper = new Label() {
		@Override
		public void setId(String id) {
			super.setId(id);
			textarea.getElement().setAttribute("aria-controls", id);
		}
	};

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final RequiredMixin<Textarea> requeridMixin = new RequiredMixin<>(textarea);
	protected final PlaceholderMixin<Textarea> placeholderMixin = new PlaceholderMixin<>(textarea);

	protected final ApplyStyleMixin<MaterialTextArea> denseMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_TEXT_FIELD__DENSE);
	protected final ApplyStyleMixin<Label> helpPersistentMixin = new ApplyStyleMixin<>(helper,
			CssName.MDC_TEXT_FIELD__HELPER_TEXT_PERSISTENT);
	protected final ApplyStyleMixin<Label> helpValidationMixin = new ApplyStyleMixin<>(helper,
			CssName.MDC_TEXT_FIELD__HELPER_TEXT_VALIDATION_MSG);

	public MaterialTextArea() {
		super();
	}
	
	@Override
	protected void jsInit() {
		jsElement = jsInit(textField.getElement());
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.textfield.MDCTextfield(element);
	}-*/;

	@Override
	protected void onInitialize() {

		label.addStyleName(CssName.MDC_TEXT_FIELD__LABEL);
		label.setTextColor(Color.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND);

		setFlexDirection(FlexDirection.COLUMN);

		helper.addStyleName(CssName.MDC_TEXT_FIELD__HELPER_TEXT);

		//textField.setDisabledClass(CssName.MDC_TEXT_FIELD_DISABLED);
		textField.add(textarea);
		textField.add(label);

		add(textField);
		add(helper);
		
		super.onInitialize();
	}

	@Override
	public void setId(String id) {
		textarea.setId(id);
		label.setFor(textarea.getId());
	}

	@Override
	public String getId() {
		return textarea.getId();
	}

	@Override
	public native String getValue()/*-{

		var textarea = this.@gwt.material.design.components.client.ui.MaterialTextArea::textarea;
		var element = textarea.@gwt.material.design.components.client.ui.html.Div::getElement()();
		return element.value;

	}-*/;

	@Override
	public native void setValue(String value, boolean fireEvents)/*-{

		var textarea = this.@gwt.material.design.components.client.ui.MaterialTextArea::textarea;
		var element = textarea.@gwt.material.design.components.client.ui.html.Div::getElement()();
		element.value = value;

		if (fireEvents) {
			this.@gwt.material.design.components.client.ui.MaterialTextArea::fireChangeEvent()();
		}

	}-*/;

	@Override
	public String getText() {
		return getValue();
	}

	@Override
	public void setText(String text) {
		setValue(text);
	}

	@Override
	public void setRipple(Color color) {
		textField.setRipple(color);
	}

	@Override
	public Color getRipple() {
		return textField.getRipple();
	}

	@Override
	public void setTextColor(Color color) {
		textarea.setTextColor(color);
	}

	@Override
	public void setLabel(String label) {
		this.label.setText(label);
	}

	@Override
	public String getLabel() {
		return label.getText();
	}

	@Override
	public void setDense(boolean dense) {
		denseMixin.setApply(dense);
	}

	@Override
	public boolean isDense() {
		return denseMixin.isApplied();
	}

	@Override
	public boolean isRequired() {
		return requeridMixin.isRequired();
	}

	@Override
	public void setRequired(boolean value) {
		requeridMixin.setRequired(value);
	};

	public void setHelperText(final String text) {
		helper.setText(text);
	}

	@Override
	public String getHelperText() {
		return helper.getText();
	}

	@Override
	public void setHelperTextPersistent(boolean persistent) {
		helpPersistentMixin.setApply(persistent);
	}

	@Override
	public boolean isHelperTextPersistent() {
		return helpPersistentMixin.isApplied();
	}

	@Override
	public void setHelperTextValidation(boolean validation) {
		helpValidationMixin.setApply(validation);
	}

	@Override
	public boolean isHelperTextValidation() {
		return helpValidationMixin.isApplied();
	}

	@Override
	public void setPlaceholder(String placeholder) {
		placeholderMixin.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return placeholderMixin.getPlaceholder();
	}

	public void setRows(int rows) {
		textarea.setRows(rows);
	}

	public void setCols(int cols) {
		textarea.setCols(cols);
	}

}
