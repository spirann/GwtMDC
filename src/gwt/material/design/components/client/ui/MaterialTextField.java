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

import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.HasHelperText;
import gwt.material.design.components.client.base.HasInputMask;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.base.HasPattern;
import gwt.material.design.components.client.base.HasPlaceholder;
import gwt.material.design.components.client.base.HasRequired;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.TextFieldType;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.MaterialTextFieldBase;
import gwt.material.design.components.client.ui.misc.MaterialTextFieldHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTextField extends Div implements HasHelperText, HasText, HasLabel, HasDense, HasRequired,
HasPattern, HasPlaceholder, HasType<TextFieldType>, HasInputMask {

	protected final MaterialTextFieldBase textFieldBase = new MaterialTextFieldBase();
	protected final MaterialTextFieldHelper helper = new MaterialTextFieldHelper();
	
	public MaterialTextField() {
		super(CssName.MDC_TEXT_FIELD_CONTAINER);
	}
	
	@Override
	protected void onInitialize() {
	
		textFieldBase.setAriaControls(helper.getId());
		textFieldBase.setAriaDescribedBy(helper.getId());
		
		add(textFieldBase);
		add(helper);
		
		super.onInitialize();
	}

	@Override
	public void setHelperText(String text) {
		helper.setHelperText(text);
	}

	@Override
	public String getHelperText() {
		return helper.getHelperText();
	}
	
	@Override
	public void setTextColor(Color color) {
		textFieldBase.setTextColor(color);
	}

	@Override
	public void setColor(Color color) {
		textFieldBase.setColor(color);
	}
	
	public void setFocusedColor(Color color) {
		textFieldBase.setFocusedColor(color);
	}	

	@Override
	public void setHelperTextPersistent(boolean persistent) {
		helper.setHelperTextPersistent(persistent);
	}

	@Override
	public boolean isHelperTextPersistent() {
		return helper.isHelperTextPersistent();
	}

	@Override
	public void setHelperTextValidation(boolean validation) {
		helper.setHelperTextValidation(validation);
	}

	@Override
	public boolean isHelperTextValidation() {
		return helper.isHelperTextValidation();
	}

	@Override
	public void setInputMask(String inputMask) {
		textFieldBase.setInputMask(inputMask);
	}

	@Override
	public String getInputMask() {
		return textFieldBase.getInputMask();
	}

	public void setInputType(TextFieldType type) {
		textFieldBase.setType(type);
	}

	public TextFieldType getInputType() {
		return textFieldBase.getType();
	}
	
	@Override
	public void setType(TextFieldType type) {
		textFieldBase.setType(type);
	}

	@Override
	public TextFieldType getType() {
		return textFieldBase.getType();
	}

	@Override
	public void setPlaceholder(String placeholder) {
		textFieldBase.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return textFieldBase.getPlaceholder();
	}

	@Override
	public void setPattern(String pattern) {
		textFieldBase.setPattern(pattern);
	}

	@Override
	public String getPattern() {
		return textFieldBase.getPattern();
	}

	@Override
	public void setRequired(boolean required) {
		textFieldBase.setRequired(required);
	}

	@Override
	public boolean isRequired() {
		return textFieldBase.isRequired();
	}

	@Override
	public void setDense(boolean dense) {
		textFieldBase.setDense(dense);
	}

	@Override
	public boolean isDense() {
		return textFieldBase.isDense();
	}

	@Override
	public void setLabel(String label) {
		textFieldBase.setLabel(label);
	}

	@Override
	public String getLabel() {
		return textFieldBase.getLabel();
	}

	@Override
	public String getText() {
		return textFieldBase.getText();
	}

	@Override
	public void setText(String text) {
		textFieldBase.setText(text);
	}
}
