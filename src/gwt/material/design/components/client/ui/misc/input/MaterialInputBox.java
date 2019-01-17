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
package gwt.material.design.components.client.ui.misc.input;

import java.util.Collection;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.interfaces.HasDense;
import gwt.material.design.components.client.base.interfaces.HasHelperText;
import gwt.material.design.components.client.base.interfaces.HasLabel;
import gwt.material.design.components.client.base.interfaces.HasPlaceholder;
import gwt.material.design.components.client.base.interfaces.HasReadOnly;
import gwt.material.design.components.client.base.interfaces.HasState;
import gwt.material.design.components.client.base.interfaces.HasUnbordered;
import gwt.material.design.components.client.base.interfaces.HasValidation;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.events.TypingEvent.HasTypingHandlers;
import gwt.material.design.components.client.events.TypingEvent.TypingHandler;
import gwt.material.design.components.client.events.ValidationEvent.HasValidationHandlers;
import gwt.material.design.components.client.events.ValidationEvent.ValidationHandler;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.validation.Validation;
import gwt.material.design.components.client.validation.Validation.Result;
import gwt.material.design.components.client.validation.ValidationRegistration;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialInputBox extends Div implements HasHelperText, HasText, HasLabel, HasDense, HasUnbordered, HasPlaceholder,
		HasState, HasValidation<MaterialInput, Validation<MaterialInput>>, HasValidationHandlers<Result>, HasTypingHandlers, HasValue<String>, HasReadOnly {

	protected final MaterialInput input = contructInput();
	protected final MaterialTextFieldHelper helper = new MaterialTextFieldHelper();

	public MaterialInputBox() {
		super(CssName.MDC_TEXT_FIELD_CONTAINER);
	}

	protected MaterialInput contructInput() {
		return new MaterialInput();
	}

	@Override
	protected void onInitialize() {

		input.setAriaControls(helper.getId());
		input.setAriaDescribedBy(helper.getId());

		addValidationHandler(event -> setHelperText(event.getResult().getMessage()));

		add(input);
		add(helper);

		super.onInitialize();
	}

	@Override
	public HandlerRegistration addValidationHandler(ValidationHandler<Result> handler) {
		return input.addValidationHandler(handler);
	}

	@Override
	public void setBackgroundColor(Color color) {
		input.setBackgroundColor(color);
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
		input.setTextColor(color);
	}

	@Override
	public void setColor(Color color) {
		input.setColor(color);
	}

	public void setFocusedColor(Color color) {
		input.setFocusedColor(color);
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
	public void setPlaceholder(String placeholder) {
		input.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return input.getPlaceholder();
	}

	@Override
	public void setPlaceholderColor(Color color) {
		input.setPlaceholderColor(color);
	}

	@Override
	public void setDense(boolean dense) {
		input.setDense(dense);
	}

	@Override
	public boolean isDense() {
		return input.isDense();
	}

	@Override
	public void setLabel(String label) {
		input.setLabel(label);
	}

	@Override
	public String getLabel() {
		return input.getLabel();
	}

	@Override
	public String getText() {
		return input.getText();
	}

	@Override
	public void setText(String text) {
		input.setText(text);
	}

	public void setMinLength(final Integer length) {
		input.setMinLength(length);
	}

	public int getMinLength() {
		return input.getMinLength();
	}

	public void setMaxLength(final Integer length) {
		input.setMaxLength(length);
	}

	public int getMaxLength() {
		return input.getMaxLength();
	}

	@Override
	public void setState(State state) {
		input.setState(state);
	}

	@Override
	public State getState() {
		return input.getState();
	}

	@Override
	public ValidationRegistration addValidation(final Validation<MaterialInput> validation) {
		return input.addValidation(validation);
	}
	
	@Override
	public Collection<Result> validate() {
		return input.validate();
	}

	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		input.setWidth(width);
		helper.setWidth(width);
	}

	@Override
	public void setMaxWidth(String maxWidth) {
		super.setMaxWidth(maxWidth);
		input.setMaxWidth(maxWidth);
		helper.setMaxWidth(maxWidth);
	}

	@Override
	public void setMinHeight(String minHeight) {
		super.setMinHeight(minHeight);
		input.setMinHeight(minHeight);
		helper.setMinHeight(minHeight);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return input.addValueChangeHandler(handler);
	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return input.addKeyDownHandler(handler);
	}
	
	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return input.addKeyUpHandler(handler);
	}
	
	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return input.addKeyPressHandler(handler);
	}
	
	@Override
	public String getValue() {
		return input.getValue();
	}

	@Override
	public void setValue(String value) {
		input.setValue(value);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		input.setValue(value, fireEvents);
	}

	protected MaterialInput getInput() {
		return input;
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		input.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() {
		return input.isReadOnly();
	}
	
	@Override
	public void setUnbordered(boolean unbordered) {
		input.setUnbordered(unbordered);
	}

	@Override
	public boolean isUnbordered() {
		return input.isUnbordered();
	}

	@Override
	public HandlerRegistration addTypingHandler(TypingHandler handler) {
		return input.addTypingHandler(handler);
	}

	@Override
	public void setTypeingDelay(int typingDelay) {
		input.setTypeingDelay(typingDelay);
	}
}
