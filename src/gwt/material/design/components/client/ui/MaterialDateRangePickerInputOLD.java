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

import java.util.Date;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.interfaces.HasDense;
import gwt.material.design.components.client.base.interfaces.HasUnbordered;
import gwt.material.design.components.client.base.interfaces.Converter;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconPosition;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.events.TypingEvent.HasTypingHandlers;
import gwt.material.design.components.client.events.TypingEvent.TypingHandler;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.calendar.DatePickerHelper;
import gwt.material.design.components.client.ui.misc.input.MaterialInput;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.validation.ValidationForTextField;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDateRangePickerInputOLD extends Div implements HasDense, HasUnbordered, HasTypingHandlers,
		HasValue<Date[]>, HasValueChangeHandlers<Date[]> {

	protected final MaterialTextField startDate = newInput();
	protected final MaterialTextField endDate = newInput();
	protected final MaterialDateRangePickerDialog dialog = new MaterialDateRangePickerDialog();

	private Converter<MaterialTextField, Date, String> converter = DatePickerHelper.getConverter();

	public MaterialDateRangePickerInputOLD() {
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
		add(startDate);
		add(endDate);
		super.onInitialize();
	}

	protected MaterialTextField newInput() {
		return new MaterialTextField() {

			@Override
			protected void onLoad() {				
				super.onLoad();			
				addValidation(ValidationForTextField.date());
				setIcon(IconType.EVENT);
				setIconPosition(IconPosition.TRAILING);
				setMaxLength(10);
				addIconClickHandler(event -> openDatePicker());
			}
			
			@Override
			protected void onInitialize() {
				setInputMask(Masker.Defaults.INSTANCE.date__mask());
				super.onInitialize();
				setInputMask(Masker.Defaults.INSTANCE.date__mask());
				JsHelper.allowNumbersOnly(getInput().getElement());	
			}

			@Override
			public void setInputMask(String inputMask) {
				super.setInputMask(inputMask);
				DatePickerHelper.formatPlaceholder(this);
			}
			
		};
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialDateRangePickerInputOLD.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date[]> handler) {
		final HandlerRegistration startHandlerRegistration = startDate
				.addValueChangeHandler(event -> fireChangeEvent());
		final HandlerRegistration endHandlerRegistration = endDate
				.addValueChangeHandler(event -> fireChangeEvent());
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
	public HandlerRegistration addTypingHandler(TypingHandler handler) {
		final HandlerRegistration startHandlerRegistration = startDate.addTypingHandler(handler);
		final HandlerRegistration endHandlerRegistration = endDate.addTypingHandler(handler);
		return () -> {
			startHandlerRegistration.removeHandler();
			endHandlerRegistration.removeHandler();
		};
	}

	public Date getInitialDate() {
		return getValue()[0];
	}

	public void setInitialDate(final Date date) {
		final Date[] values = getValue();
		values[0] = date;
		setValue(values);
	}

	public Date getFinalDate() {
		return getValue()[1];
	}

	public void setFinalDate(final Date date) {
		final Date[] values = getValue();
		values[1] = date;
		setValue(values);
	}

	@Override
	public Date[] getValue() {
		return new Date[] {
				converter.convert(startDate,
						Masker.toPattern(startDate.getValue(), startDate.getInputMask())),
				converter.convert(endDate,
						Masker.toPattern(endDate.getValue(), endDate.getInputMask())) };
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
			startDate.setValue(converter.undo(startDate, value[0]));
			endDate.setValue("");
		} else if (value.length > 1) {
			startDate.setValue(converter.undo(startDate, value[0]));
			endDate.setValue(converter.undo(endDate, value[1]));
		}

		if (fireEvents)
			fireChangeEvent();
	}

	public void openDatePicker() {
		dialog.setValue(getValue(), false);
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

	public void setPlaceholderColor(Color color) {
		startDate.setPlaceholderColor(color);
		endDate.setPlaceholderColor(color);
	}

	public void setHelperTextValidation(boolean validation) {
		startDate.setHelperTextValidation(validation);
		endDate.setHelperTextValidation(validation);
	}

	public void setHelperTextPersistent(boolean persistent) {
		startDate.setHelperTextPersistent(persistent);
		endDate.setHelperTextPersistent(persistent);
	}

	public void setInitialHelperText(String text) {
		startDate.setHelperText(text);
	}

	public void setEndHelperText(String text) {
		endDate.setHelperText(text);
	}

	public String getInitialHelperText() {
		return startDate.getHelperText();
	}

	public String getEndHelperText() {
		return endDate.getHelperText();
	}

	public void setInitialLabel(String label) {
		startDate.setLabel(label);
	}

	public String getInitialLabel() {
		return startDate.getLabel();
	}

	public void setEndLabel(String label) {
		endDate.setLabel(label);
	}

	public String getEndLabel() {
		return endDate.getLabel();
	}

	public void setInitialState(State state) {
		startDate.setState(state);
	}

	public State getInitialState() {
		return startDate.getState();
	}

	public void setEndState(State state) {
		endDate.setState(state);
	}

	public State getEndState() {
		return endDate.getState();
	}

	public void setInitialReadOnly(boolean readOnly) {
		startDate.setReadOnly(readOnly);
	}

	public boolean isInitialReadOnly() {
		return startDate.isReadOnly();
	}
	
	public void setEndReadOnly(boolean readOnly) {
		startDate.setReadOnly(readOnly);
	}

	public boolean isEndReadOnly() {
		return startDate.isReadOnly();
	}
}
