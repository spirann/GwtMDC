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
import java.util.Date;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
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
import gwt.material.design.components.client.base.interfaces.StringToDate;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconPosition;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.events.TypingEvent.HasTypingHandlers;
import gwt.material.design.components.client.events.TypingEvent.TypingHandler;
import gwt.material.design.components.client.events.ValidationEvent.HasValidationHandlers;
import gwt.material.design.components.client.events.ValidationEvent.ValidationHandler;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.calendar.DatePickerHelper;
import gwt.material.design.components.client.ui.misc.input.MaterialInput;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.validation.Validation;
import gwt.material.design.components.client.validation.Validation.Result;
import gwt.material.design.components.client.validation.ValidationForTextField;
import gwt.material.design.components.client.validation.ValidationRegistration;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDateRangePickerInput extends Div
		implements HasHelperText, HasText, HasLabel, HasDense, HasUnbordered, HasPlaceholder, HasState,
		HasValidation<MaterialInput, Validation<MaterialInput>>, HasValidationHandlers<Result>, HasTypingHandlers,
		HasValue<Date[]>, HasValueChangeHandlers<Date[]>, HasReadOnly {

	protected final MaterialTextField startDate = new MaterialTextField();
	protected final MaterialTextField endDate = new MaterialTextField();
	protected final MaterialDateRangePickerDialog dialog = new MaterialDateRangePickerDialog();

	private StringToDate stringToDateToStart = DatePickerHelper.defaultStringToDate(startDate);
	private StringToDate stringToDateToEnd = DatePickerHelper.defaultStringToDate(endDate);

	public MaterialDateRangePickerInput() {
		super(CssName.MDC_DATEPICKER__RANGE__INPUTS);
	}

	protected MaterialInput contructInput() {
		return new MaterialInput();
	}

	@Override
	protected void onInitialize() {

		startDate.setLabel(IMessages.INSTANCE.mdc_calendar_initial_date());
		endDate.setLabel(IMessages.INSTANCE.mdc_calendar_final_date());

		dialog.addAcceptHandler(event -> setValue(dialog.getValue()));
		add(dialog);

		startDate.setInputMask(Masker.Defaults.INSTANCE.date__mask());
		endDate.setInputMask(Masker.Defaults.INSTANCE.date__mask());

		addValidation(ValidationForTextField.date());

		DatePickerHelper.formatPlaceholder(startDate);
		DatePickerHelper.formatPlaceholder(endDate);

		add(startDate);
		add(endDate);

		super.onInitialize();
		
		startDate.setIcon(IconType.EVENT);
		startDate.setIconPosition(IconPosition.TRAILING);
		startDate.setMaxLength(10);
		startDate.addIconClickHandler(event -> openDatePicker());
		
		endDate.setIcon(IconType.EVENT);
		endDate.setIconPosition(IconPosition.TRAILING);
		endDate.setMaxLength(10);
		endDate.addIconClickHandler(event -> openDatePicker());

		JsHelper.allowNumbersOnly(startDate.getInput().getElement());
		JsHelper.allowNumbersOnly(endDate.getInput().getElement());


	}

	@Override
	public HandlerRegistration addValidationHandler(ValidationHandler<Result> handler) {
		final HandlerRegistration startHandlerRegistration = startDate.addValidationHandler(handler);
		final HandlerRegistration endHandlerRegistration = endDate.addValidationHandler(handler);
		return () -> {
			startHandlerRegistration.removeHandler();
			endHandlerRegistration.removeHandler();
		};
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialDateRangePickerInput.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date[]> handler) {
		final HandlerRegistration startHandlerRegistration = startDate
				.addValueChangeHandler(event -> fireChangeEvent());
		final HandlerRegistration endHandlerRegistration = endDate.addValueChangeHandler(event -> fireChangeEvent());
		return () -> {
			startHandlerRegistration.removeHandler();
			endHandlerRegistration.removeHandler();
		};
	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		final HandlerRegistration startHandlerRegistration = startDate.addKeyDownHandler(handler);
		final HandlerRegistration endHandlerRegistration = endDate.addKeyDownHandler(handler);
		return () -> {
			startHandlerRegistration.removeHandler();
			endHandlerRegistration.removeHandler();
		};
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		final HandlerRegistration startHandlerRegistration = startDate.addKeyUpHandler(handler);
		final HandlerRegistration endHandlerRegistration = endDate.addKeyUpHandler(handler);
		return () -> {
			startHandlerRegistration.removeHandler();
			endHandlerRegistration.removeHandler();
		};
	}

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		final HandlerRegistration startHandlerRegistration = startDate.addKeyPressHandler(handler);
		final HandlerRegistration endHandlerRegistration = endDate.addKeyPressHandler(handler);
		return () -> {
			startHandlerRegistration.removeHandler();
			endHandlerRegistration.removeHandler();
		};
	}

	@Override
	public ValidationRegistration addValidation(final Validation<MaterialInput> validation) {
		final ValidationRegistration startHandlerRegistration = startDate.addValidation(validation);
		final ValidationRegistration endHandlerRegistration = endDate.addValidation(validation);
		return () -> {
			startHandlerRegistration.removeValidation();
			endHandlerRegistration.removeValidation();
		};
	}

	@Override
	public HandlerRegistration addTypingHandler(TypingHandler handler) {
		final HandlerRegistration startHandlerRegistration = startDate.addTypingHandler(handler);
		final HandlerRegistration endHandlerRegistration = endDate.addTypingHandler(handler);
		return () -> {
			startHandlerRegistration.removeHandler();
			endHandlerRegistration.removeHandler();
		};
	}

	@Override
	public Date[] getValue() {
		return new Date[] { stringToDateToStart.convert(Masker.toPattern(startDate.getValue(), startDate.getInputMask())),
				stringToDateToEnd.convert(Masker.toPattern(endDate.getValue(), endDate.getInputMask())) };
	}

	@Override
	public void setValue(Date[] value) {
		setValue(value, true);
	}

	@Override
	public void setValue(Date[] value, boolean fireEvents) {
		if (value == null || value.length == 0) {
			startDate.setValue("");
			endDate.setValue("");
		} else if (value.length == 1) {
			startDate.setValue(stringToDateToStart.convert(value[0]));
			endDate.setValue("");
		} else if (value.length > 1) {
			startDate.setValue(stringToDateToStart.convert(value[0]));
			endDate.setValue(stringToDateToEnd.convert(value[1]));
		}

		if (fireEvents)
			fireChangeEvent();
	}

	public void openDatePicker() {
		dialog.setValue(getValue());
		dialog.open();
	}

	@Override
	public void setTypeingDelay(int typingDelay) {
		startDate.setTypeingDelay(typingDelay);
		endDate.setTypeingDelay(typingDelay);
	}

	@Override
	public void setBackgroundColor(Color color) {
		startDate.setBackgroundColor(color);
		endDate.setBackgroundColor(color);
	}

	@Override
	public void setTextColor(Color color) {
		startDate.setTextColor(color);
		endDate.setTextColor(color);
	}

	@Override
	public void setColor(Color color) {
		startDate.setColor(color);
		endDate.setColor(color);
	}

	public void setFocusedColor(Color color) {
		startDate.setFocusedColor(color);
		endDate.setFocusedColor(color);
	}

	@Override
	public void setDense(boolean dense) {
		startDate.setDense(dense);
		endDate.setDense(dense);
	}

	@Override
	public boolean isDense() {
		return startDate.isDense() || endDate.isDense();
	}

	@Override
	public void setUnbordered(boolean unbordered) {
		startDate.setUnbordered(unbordered);
		endDate.setUnbordered(unbordered);
	}

	@Override
	public boolean isUnbordered() {
		return startDate.isUnbordered() || endDate.isUnbordered();
	}

	@Override
	public Collection<Result> validate() {
		return startDate.validate();
	}

	@Override
	public void setHelperText(String text) {
		startDate.setHelperText(text);
		endDate.setHelperText(text);
	}

	@Override
	public String getHelperText() {
		return startDate.getHelperText();
	}

	@Override
	public void setHelperTextValidation(boolean validation) {
		startDate.setHelperTextValidation(validation);
		endDate.setHelperTextValidation(validation);
	}

	@Override
	public void setHelperTextPersistent(boolean persistent) {
		startDate.setHelperTextPersistent(persistent);
		endDate.setHelperTextPersistent(persistent);
	}

	@Override
	public boolean isHelperTextPersistent() {
		return startDate.isHelperTextPersistent();
	}

	@Override
	public boolean isHelperTextValidation() {
		return startDate.isHelperTextValidation();
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
		endDate.setPlaceholderColor(color);
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

	@Override
	public void setState(State state) {
		startDate.setState(state);
	}

	@Override
	public State getState() {
		return startDate.getState();
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		startDate.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() {
		return startDate.isReadOnly();
	}
}
