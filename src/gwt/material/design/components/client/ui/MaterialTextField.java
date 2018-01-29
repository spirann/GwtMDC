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
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.HasHelpText;
import gwt.material.design.components.client.base.HasInputMask;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.base.HasPattern;
import gwt.material.design.components.client.base.HasPlaceholder;
import gwt.material.design.components.client.base.HasRequired;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.InputMaskMixin;
import gwt.material.design.components.client.base.mixin.PatternMixin;
import gwt.material.design.components.client.base.mixin.PlaceholderMixin;
import gwt.material.design.components.client.base.mixin.RequiredMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FlexDirection;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTextField extends MaterialFormField<String> implements HasText, HasLabel, HasDense, HasRequired,
		HasPattern, HasHelpText, HasPlaceholder, HasType<InputType>, HasInputMask {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var textField = this.@gwt.material.design.components.client.ui.MaterialTextField::textField;
		var element = textField.@gwt.material.design.components.client.ui.html.Div::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialTextField::jsElement = $wnd.mdc.textField.MDCTextField.attachTo(element);
	}-*/;

	// /////////////////////////////////////////////////////////////
	// Textfield
	// /////////////////////////////////////////////////////////////
	protected Div textField = new Div(CssName.MDC_TEXT_FIELD);
	protected Input input = new Input(InputType.TEXT, CssName.MDC_TEXT_FIELD_INPUT);

	// /////////////////////////////////////////////////////////////
	// Label
	// /////////////////////////////////////////////////////////////
	protected Label label = new Label(CssName.MDC_TEXT_FIELD_LABEL);
	protected Label helper = new Label() {
		@Override
		public void setId(String id) {
			super.setId(id);
			input.getElement().setAttribute("aria-controls", id);
		}
	};

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final RequiredMixin<Input> requeridMixin = new RequiredMixin<>(input);
	protected final PatternMixin<Input> patternMixin = new PatternMixin<>(input);
	protected final PlaceholderMixin<Input> placeholderMixin = new PlaceholderMixin<>(input);
	protected final InputMaskMixin<Input> inputMaskMixin = new InputMaskMixin<>(input);

	protected final ApplyStyleMixin<MaterialTextField> denseMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_TEXT_FIELD_DENSE);
	protected final ApplyStyleMixin<Label> helpPersistentMixin = new ApplyStyleMixin<>(helper,
			CssName.MDC_TEXT_FIELD_HELPTEXT_PERSISTENT);
	protected final ApplyStyleMixin<Label> helpValidationMixin = new ApplyStyleMixin<>(helper,
			CssName.MDC_TEXT_FIELD_HELPTEXT_VALIDATION_MSG);

	public MaterialTextField() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		input.setId("x");
		//label.addStyleName("mdc-text-field__label--float-above");
		label.setTextColor(Color.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND);
		label.getElement().setAttribute("for", "x");

		setFlexDirection(FlexDirection.COLUMN);

		helper.addStyleName(CssName.MDC_TEXT_FIELD_HELPTEXT);

		textField.setDisabledClass(CssName.MDC_TEXT_FIELD_DISABLED);
		textField.add(input);
		textField.add(label);
		textField.add(new Div("mdc-text-field__bottom-line"));

		add(textField);
		add(helper);

		jsInit();
	}

	@Override
	public void setId(String id) {
		input.setId(id);
		label.setFor(input.getId());
	}

	@Override
	public String getId() {
		return input.getId();
	}

	@Override
	public native String getValue()/*-{
		var textfield = this.@gwt.material.design.components.client.ui.MaterialTextField::input;
		var element = textfield.@gwt.material.design.components.client.ui.html.Div::getElement()();
		return element.value;
	}-*/;

	@Override
	public native void setValue(String value, boolean fireEvents)/*-{

		var textfield = this.@gwt.material.design.components.client.ui.MaterialTextField::input;
		var element = textfield.@gwt.material.design.components.client.ui.html.Div::getElement()();
		element.value = value;

		if (fireEvents) {
			this.@gwt.material.design.components.client.ui.MaterialTextField::fireChangeEvent()();
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
	public void setRipple(Ripple ripple) {
		textField.setRipple(ripple);
	}

	@Override
	public Ripple getRipple() {
		return textField.getRipple();
	}

	@Override
	public void setTextColor(Color color) {
		input.setTextColor(color);
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

	@Override
	public void setPattern(String pattern) {
		patternMixin.setPattern(pattern);
	}

	@Override
	public String getPattern() {
		return patternMixin.getPattern();
	}

	public void setHelpText(final String text) {
		helper.setText(text);
	}

	@Override
	public String getHelpText() {
		return helper.getText();
	}

	@Override
	public void setHelpTextPersistent(boolean persistent) {
		helpPersistentMixin.setApply(persistent);
	}

	@Override
	public boolean isHelpTextPersistent() {
		return helpPersistentMixin.isApplied();
	}

	@Override
	public void setHelpTextValidation(boolean validation) {
		helpValidationMixin.setApply(validation);
	}

	@Override
	public boolean isHelpTextValidation() {
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

	@Override
	public void setType(InputType type) {
		input.setType(type);
	}

	@Override
	public InputType getType() {
		return input.getType();
	}

	@Override
	public void setInputMask(String inputMask) {
		inputMaskMixin.setInputMask(inputMask);
	}

	@Override
	public String getInputMask() {
		return inputMaskMixin.getInputMask();
	}
}
