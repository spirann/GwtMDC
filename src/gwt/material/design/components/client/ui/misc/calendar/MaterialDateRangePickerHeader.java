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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialDivider;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialDateRangePickerHeader extends MaterialValuedField<Date[]> {

	protected Label minDate = new Label(CssName.MDC_DATEPICKER__HEADER__DATE, CssName.MDC_TYPOGRAPHY__CAPTION);
	protected Label maxDate = new Label(CssName.MDC_DATEPICKER__HEADER__DATE, CssName.MDC_TYPOGRAPHY__CAPTION);

	public MaterialDateRangePickerHeader() {
		super(CssName.MDC_DATEPICKER__HEADER, CssName.MDC_DATEPICKER__HEADER_PERIOD);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		final Div firstColumn = new Div();
		firstColumn.add(new Div(CssName.MDC_DATEPICKER__HEADER_PERIOD__CIRCLE));
		firstColumn.add(new Div(CssName.MDC_DATEPICKER__HEADER_PERIOD__LINE));
		firstColumn.add(new Div(CssName.MDC_DATEPICKER__HEADER_PERIOD__CIRCLE));
		
		final Div lastColumn = new Div();		
		lastColumn.add(minDate);
		lastColumn.add(new MaterialDivider());
		lastColumn.add(maxDate);
		
		add(firstColumn);
		add(lastColumn);
		
		drawHeader();
	}

	protected void drawHeader() {
		final Date[] date = getValue();
		setText(minDate, date[0], IMessages.INSTANCE.mdc_calendar_initial_date());
		setText(maxDate, date[1], IMessages.INSTANCE.mdc_calendar_final_date());
	}

	protected void setText(final Label label, final Date date, final String emptyText) {
		label.removeStyleName(CssName.MDC_DATEPICKER__HEADER_PERIOD__PLACEHOLDER);
		if (date == null) {
			label.setText(emptyText);
			label.addStyleName(CssName.MDC_DATEPICKER__HEADER_PERIOD__PLACEHOLDER);
		} else {
			final String week = IMessages.INSTANCE.mdc_calendar_short_week(date.getDay() + 1);
			final int day = date.getDate();
			final String month = IMessages.INSTANCE.mdc_calendar_short_month(date.getMonth() + 1);
			final int year = date.getYear() + 1900;
			label.setText(IMessages.INSTANCE.mdc_calendar_header_full_day(week, day, month, year));
		}
	}

	public void setInitialDate(final Date date) {
		final Date[] values = getValue();
		values[0] = date;
		setValue(values);
	}
	
	public void setFinalDate(final Date date) {
		final Date[] values = getValue();
		values[1] = date;
		setValue(values);
	}
	
	@Override
	public Date[] getValue() {
		final Date[] value = super.getValue();		
		return value == null ? new Date[2] : value;
	}
	
	@Override
	public void setValue(Date[] value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized)
			drawHeader();
	}

	public HandlerRegistration addInitialDateClickHandler(ClickHandler handler) {
		return minDate.addClickHandler(handler);
	}

	public HandlerRegistration addFinalDateClickHandler(ClickHandler handler) {
		return maxDate.addClickHandler(handler);
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_DATEPICKER__HEADER_INK, color.getCssName());
	}

	@Override
	public void setBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_DATEPICKER__HEADER_FILL, color.getCssName());
	}
}
