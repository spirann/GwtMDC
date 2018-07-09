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
package gwt.material.design.components.client.ui.misc;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.HasInputMask;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.base.HasPattern;
import gwt.material.design.components.client.base.HasPlaceholder;
import gwt.material.design.components.client.base.HasRequired;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.InputMaskMixin;
import gwt.material.design.components.client.base.mixin.PatternMixin;
import gwt.material.design.components.client.base.mixin.PlaceholderMixin;
import gwt.material.design.components.client.base.mixin.RequiredMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.CssProperty;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.TextFieldType;
import gwt.material.design.components.client.ui.html.Input;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTextFieldBase extends MaterialFormField<String> implements HasText, HasLabel, HasDense, HasRequired,
		HasPattern, HasPlaceholder, HasType<TextFieldType>, HasInputMask {

	// /////////////////////////////////////////////////////////////
	// Textfield
	// /////////////////////////////////////////////////////////////
	protected Input input = new Input(InputType.TEXT, CssName.MDC_TEXT_FIELD__INPUT);
	protected MaterialFloatLabel label = new MaterialFloatLabel();
	protected MaterialLineRipple lineRipple = new MaterialLineRipple();
	protected MaterialNotchedOutline notchedOutline = new MaterialNotchedOutline();
	
	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final RequiredMixin<Input> requeridMixin = new RequiredMixin<>(input);
	protected final PatternMixin<Input> patternMixin = new PatternMixin<>(input);
	protected final PlaceholderMixin<Input> placeholderMixin = new PlaceholderMixin<>(input);
	protected final InputMaskMixin<Input> inputMaskMixin = new InputMaskMixin<>(input);
	protected final ApplyStyleMixin<MaterialTextFieldBase> denseMixin = new ApplyStyleMixin<>(this, CssName.MDC_TEXT_FIELD__DENSE);
	protected final AttributeMixin<MaterialTextFieldBase> ariaControlsMixin = new AttributeMixin<>(this, "aria-controls");
	protected final AttributeMixin<MaterialTextFieldBase> ariaDescribedByMixin = new AttributeMixin<>(this, "aria-describedby");
	protected final TypeMixin<MaterialTextFieldBase, TextFieldType> typeMixin = new TypeMixin<>(this);

	public MaterialTextFieldBase() {
		super(CssName.MDC_TEXT_FIELD);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.textField.MDCTextField(element);
	}-*/;

	@Override
	protected void onInitialize() {

		label.setFor(input.getId());

		add(input);
		add(label);
		add(lineRipple);
		add(notchedOutline);
		
		super.onInitialize();
	}

	@Override
	public native String getValue()/*-{
		var textfield = this.@gwt.material.design.components.client.ui.misc.MaterialTextFieldBase::input;
		var element = textfield.@gwt.material.design.components.client.ui.html.Div::getElement()();
		return element.value;
	}-*/;

	@Override
	public native void setValue(String value, boolean fireEvents)/*-{

		var textfield = this.@gwt.material.design.components.client.ui.misc.MaterialTextFieldBase::input;
		var element = textfield.@gwt.material.design.components.client.ui.html.Div::getElement()();
		element.value = value;

		if (fireEvents) {
			this.@gwt.material.design.components.client.ui.misc.MaterialTextFieldBase::fireChangeEvent()();
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
	public void setTextColor(Color color) {
		input.setTextColor(color);
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssProperty.MDC_TEXTFIELD__COLOR, color.getCssName());
	}
	
	public void setFocusedColor(Color color) {
		setStyleProperty(CssProperty.MDC_TEXTFIELD__FOCUSED_COLOR, color.getCssName());
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
	
	public void setAriaControls(String target) {
		ariaControlsMixin.setAttribute(target);
	}

	public String getAriaControls() {
		return ariaControlsMixin.getAttribute();
	}
	
	public void setAriaDescribedBy(String target) {
		ariaDescribedByMixin.setAttribute(target);
	}

	public String getAriaDescribedBy() {
		return ariaDescribedByMixin.getAttribute();
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
	
	@Override
	public void setPlaceholder(String placeholder) {
		placeholderMixin.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return placeholderMixin.getPlaceholder();
	}


	public void setInputType(InputType type) {
		input.setType(type);
	}

	public InputType getInputType() {
		return input.getType();
	}
	
	
	@Override
	public void setType(TextFieldType type) {
		typeMixin.setType(type);
	}

	@Override
	public TextFieldType getType() {
		return typeMixin.getType();
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
