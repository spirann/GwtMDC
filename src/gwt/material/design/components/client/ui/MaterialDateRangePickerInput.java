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
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.input.MaterialInput;
import gwt.material.design.components.client.ui.misc.input.MaterialTextFieldHelper;
import gwt.material.design.components.client.validation.Validation;
import gwt.material.design.components.client.validation.Validation.Result;
import gwt.material.design.components.client.validation.ValidationRegistration;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDateRangePickerInput extends Div implements HasHelperText, HasText, HasLabel, HasDense, HasUnbordered, HasPlaceholder,
		HasState, HasValidation<MaterialInput, Validation<MaterialInput>>, HasValidationHandlers<Result>, HasTypingHandlers, HasValue<String>, HasReadOnly {

	protected final MaterialInput startDate = new MaterialInput();
	protected final MaterialInput endDate = new MaterialInput();
	protected final MaterialIcon icon = new MaterialIcon(CssName.MDC_TEXT_FIELD__ICON);
	protected final Div inputs = new Div();
	protected final Div content = new Div();
	protected final MaterialTextFieldHelper helper = new MaterialTextFieldHelper();

	public MaterialDateRangePickerInput() {
		super(CssName.MDC_TEXT_FIELD_CONTAINER);
	}

	protected MaterialInput contructInput() {
		return new MaterialInput();
	}

	@Override
	protected void onInitialize() {

		startDate.setAriaControls(helper.getId());
		startDate.setAriaDescribedBy(helper.getId());
		startDate.setPlaceholder(IMessages.INSTANCE.mdc_calendar_initial_date());
		
		endDate.setAriaControls(helper.getId());
		endDate.setAriaDescribedBy(helper.getId());
		endDate.setPlaceholder(IMessages.INSTANCE.mdc_calendar_final_date());

		addValidationHandler(event -> setHelperText(event.getResult().getMessage()));

		inputs.add(startDate);
		inputs.add(endDate);
		content.add(inputs);
		content.add(icon);
		
		add(content);
		add(helper);

		super.onInitialize();
	}

	@Override
	public HandlerRegistration addValidationHandler(ValidationHandler<Result> handler) {
		return startDate.addValidationHandler(handler);
	}

	@Override
	public void setBackgroundColor(Color color) {
		startDate.setBackgroundColor(color);
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
		startDate.setTextColor(color);
	}

	@Override
	public void setColor(Color color) {
		startDate.setColor(color);
	}

	public void setFocusedColor(Color color) {
		startDate.setFocusedColor(color);
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
		startDate.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return startDate.getPlaceholder();
	}

	@Override
	public void setPlaceholderColor(Color color) {
		startDate.setPlaceholderColor(color);
	}

	@Override
	public void setDense(boolean dense) {
		startDate.setDense(dense);
	}

	@Override
	public boolean isDense() {
		return startDate.isDense();
	}

	@Override
	public void setLabel(String label) {
		startDate.setLabel(label);
	}

	@Override
	public String getLabel() {
		return startDate.getLabel();
	}

	@Override
	public String getText() {
		return startDate.getText();
	}

	@Override
	public void setText(String text) {
		startDate.setText(text);
	}

	public void setMinLength(final Integer length) {
		startDate.setMinLength(length);
	}

	public int getMinLength() {
		return startDate.getMinLength();
	}

	public void setMaxLength(final Integer length) {
		startDate.setMaxLength(length);
	}

	public int getMaxLength() {
		return startDate.getMaxLength();
	}

	@Override
	public void setState(State state) {
		startDate.setState(state);
	}

	@Override
	public State getState() {
		return startDate.getState();
	}

	@Override
	public ValidationRegistration addValidation(final Validation<MaterialInput> validation) {
		return startDate.addValidation(validation);
	}
	
	@Override
	public Collection<Result> validate() {
		return startDate.validate();
	}

	@Override
	public void setWidth(String width) {
		super.setWidth(width);
		startDate.setWidth(width);
		helper.setWidth(width);
	}

	@Override
	public void setMaxWidth(String maxWidth) {
		super.setMaxWidth(maxWidth);
		startDate.setMaxWidth(maxWidth);
		helper.setMaxWidth(maxWidth);
	}

	@Override
	public void setMinHeight(String minHeight) {
		super.setMinHeight(minHeight);
		startDate.setMinHeight(minHeight);
		helper.setMinHeight(minHeight);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return startDate.addValueChangeHandler(handler);
	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return startDate.addKeyDownHandler(handler);
	}
	
	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return startDate.addKeyUpHandler(handler);
	}
	
	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return startDate.addKeyPressHandler(handler);
	}
	
	@Override
	public String getValue() {
		return startDate.getValue();
	}

	@Override
	public void setValue(String value) {
		startDate.setValue(value);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		startDate.setValue(value, fireEvents);
	}

	protected MaterialInput getInput() {
		return startDate;
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		startDate.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() {
		return startDate.isReadOnly();
	}
	
	@Override
	public void setUnbordered(boolean unbordered) {
		startDate.setUnbordered(unbordered);
	}

	@Override
	public boolean isUnbordered() {
		return startDate.isUnbordered();
	}

	@Override
	public HandlerRegistration addTypingHandler(TypingHandler handler) {
		return startDate.addTypingHandler(handler);
	}

	@Override
	public void setTypeingDelay(int typingDelay) {
		startDate.setTypeingDelay(typingDelay);
	}
}
