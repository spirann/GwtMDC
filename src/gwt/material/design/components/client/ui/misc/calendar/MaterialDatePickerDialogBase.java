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
package gwt.material.design.components.client.ui.misc.calendar;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.CloseAction;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialDialog;

/**
 * 
 * @author Richeli Vargas
 *
 */
public abstract class MaterialDatePickerDialogBase<T, D extends MaterialDatePickerBase<T, ?, ?>> extends MaterialDialog
		implements HasValue<T> {

	private boolean valueChangeHandlerInitialized;

	private T value;

	protected D datePicker;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		addStyleName(CssName.MDC_DATEPICKER__DIALOG);
		initializeDatePicker();
		addContent(datePicker);
		setAcceptText(IMessages.INSTANCE.mdc_calendar_ok());
		setCancelText(IMessages.INSTANCE.mdc_calendar_cancel());		
		addClosingHandler(event -> {
			if(event.getAction().equals(CloseAction.ACCEPT))
				setValue(datePicker.getValue());
		});
		addOpeningHandler(event -> datePicker.setValue(getValue(), false));
	}

	protected abstract void initializeDatePicker();

	protected HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialDatePickerDialogBase.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		if (!valueChangeHandlerInitialized)
			valueChangeHandlerInitialized = addChangeHandler(event -> fireChangeEvent()) != null;
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void open() {		
		datePicker.setValue(getValue(), false);		
		super.open();
	}
	
	@Override
	public void setValue(T value) {
		setValue(value, true);
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		this.value = value;

		if (datePicker != null)
			datePicker.setValue(value, false);

		if (fireEvents)
			fireChangeEvent();
	}

	@Override
	public void setHeaderBackgroundColor(Color color) {
		super.setHeaderBackgroundColor(color);
		datePicker.setHeaderBackgroundColor(color);
	}

	@Override
	public void setHeaderTextColor(Color color) {
		super.setHeaderTextColor(color);
		datePicker.setHeaderColor(color);
	}

	@Override
	public void setBackgroundColor(Color color) {
		super.setBackgroundColor(color);
		datePicker.setBackgroundColor(color);
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
		datePicker.setColor(color);
	}

	public void setActiveColor(Color color) {
		datePicker.setActiveColor(color);
	}

	public void setActiveBackgroundColor(Color color) {
		datePicker.setActiveBackgroundColor(color);
	}

	@UiChild(tagname = "action")
	public void addAction(final Widget action) {
		datePicker.addAction(action);
	}

	public boolean isChangeMonth() {
		return datePicker.isChangeMonth();
	}

	public void setChangeMonth(final boolean change) {
		datePicker.setChangeMonth(change);
	}

	public boolean isChangeYear() {
		return datePicker.isChangeYear();
	}

	public void setChangeYear(final boolean change) {
		datePicker.setChangeYear(change);
	}

	public void setShowClearAction(final boolean show) {
		datePicker.setShowClearAction(show);
	}
	
	public Date getMinDate() {
		return datePicker.getMinDate();
	}

	public void setMinDate(Date minDate) {
		datePicker.setMinDate(minDate);
	}
	
	public void setMinDate(String minDate) {
		datePicker.setMinDate(minDate);
	}

	public Date getMaxDate() {
		return datePicker.getMaxDate();
	}

	public void setMaxDate(Date maxDate) {
		datePicker.setMaxDate(maxDate);
	}
	
	public void setMaxDate(String maxDate) {
		datePicker.setMaxDate(maxDate);
	}

	public void setDateTooltip(final Date date, final String tooltip) {
		datePicker.setDateTooltip(date, tooltip);
	}
	
	public String getDateTooltip(final Date date) {
		return datePicker.getDateTooltip(date);
	}
	
	public String removeDateTooltip(final Date date) {
		return datePicker.removeDateTooltip(date); 
	}
}
