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
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialDatePickerHeader extends MaterialValuedField<Date> {

	protected Label headerYear = new Label(CssName.MDC_DATEPICKER__HEADER__YEAR) {
		@Override
		protected void onInitialize() {
			ripleMixin.initialize();
			super.onInitialize();
		}
	};

	protected Label headerDate = new Label(CssName.MDC_DATEPICKER__HEADER__DATE,
			CssName.MDC_TYPOGRAPHY__HEADLINE_5);

	public MaterialDatePickerHeader() {
		super(CssName.MDC_DATEPICKER__HEADER__SINGLE_DATE);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(headerYear);
		add(headerDate);
		drawHeader();
	}

	protected void drawHeader() {

		final Date date;

		if (getValue() == null)
			date = new Date();
		else
			date = getValue();

		final int year = date.getYear() + 1900;
		final int month = date.getMonth() + 1;
		final String week = IMessages.INSTANCE.mdc_calendar_short_week(date.getDay() + 1);
		final String shortMonth = IMessages.INSTANCE.mdc_calendar_short_month(month);
		final int day = date.getDate();
		headerYear.setText(DatePickerHelper.completeWithZero(String.valueOf(year), 4));
		headerDate.setText(IMessages.INSTANCE.mdc_calendar_header_day(week, shortMonth, day));
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized)
			drawHeader();
	}

	public HandlerRegistration addYearClickHandler(ClickHandler handler) {
		return headerYear.addClickHandler(handler);
	}

	public HandlerRegistration addDateClickHandler(ClickHandler handler) {
		return headerDate.addClickHandler(handler);
	}

	@Override
	public void setColor(Color color) {
		setCssProperty(CssMixin.MDC_DATEPICKER__HEADER_INK, color.getCssName());
	}

	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_DATEPICKER__HEADER_FILL, color.getCssName());
	}
}
