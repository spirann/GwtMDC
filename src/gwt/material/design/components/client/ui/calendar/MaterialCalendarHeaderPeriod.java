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
package gwt.material.design.components.client.ui.calendar;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialListDivider;
import gwt.material.design.components.client.ui.form.MaterialValuedField;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendarHeaderPeriod extends MaterialValuedField<Date[]> {

	protected Label minDate = new Label(CssName.MDC_CALENDAR__HEADER__DATE, CssName.MDC_TYPOGRAPHY__CAPTION);
	protected Label maxDate = new Label(CssName.MDC_CALENDAR__HEADER__DATE, CssName.MDC_TYPOGRAPHY__CAPTION);

	public MaterialCalendarHeaderPeriod() {
		super(CssName.MDC_CALENDAR__HEADER, CssName.MDC_CALENDAR__HEADER__PERIOD);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		final Div firstColumn = new Div();
		firstColumn.add(new Div("mdc-calendar__header_period--circle"));
		firstColumn.add(new Div("mdc-calendar__header_period--line"));
		firstColumn.add(new Div("mdc-calendar__header_period--circle"));
		
		final Div lastColumn = new Div();		
		lastColumn.add(minDate);
		lastColumn.add(new MaterialListDivider());
		lastColumn.add(maxDate);
		
		add(firstColumn);
		add(lastColumn);
		
		drawHeader();
	}

	protected void drawHeader() {

		final Date[] date;

		if (getValue() == null)
			date = new Date[2];
		else
			date = getValue();

		setText(minDate, date[0]);
		setText(maxDate, date[1]);
	}

	protected void setText(final Label label, final Date date) {
		if (date == null) {
			label.setText("");
		} else {
			final String week = IMessages.INSTANCE.mdc_calendar_full_week(date.getDay() + 1);
			final int day = date.getDate();
			final String month = IMessages.INSTANCE.mdc_calendar_full_month(date.getMonth() + 1);
			final int year = date.getYear() + 1900;
			label.setText(IMessages.INSTANCE.mdc_calendar_header_full_day(week, day, month, year));
		}
	}

	@Override
	public void setValue(Date[] value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized)
			drawHeader();
	}

	public HandlerRegistration addStartDateClickHandler(ClickHandler handler) {
		return minDate.addClickHandler(handler);
	}

	public HandlerRegistration addEndDateClickHandler(ClickHandler handler) {
		return maxDate.addClickHandler(handler);
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_CALENDAR__HEADER_INK, color.getCssName());
	}

	@Override
	public void setBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_CALENDAR__HEADER_FILL, color.getCssName());
	}
}
