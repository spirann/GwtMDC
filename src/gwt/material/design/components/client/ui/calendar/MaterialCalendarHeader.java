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

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.form.MaterialValuedField;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendarHeader extends MaterialValuedField<Date> {

	protected Label headerYear = new Label(CssName.MDC_CALENDAR__HEADER__YEAR);
	protected Label headerDate = new Label(CssName.MDC_CALENDAR__HEADER__DATE, CssName.MDC_TYPOGRAPHY__HEADLINE_5);

	public MaterialCalendarHeader() {
		super(CssName.MDC_CALENDAR__HEADER__CONTENT);
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
		
		if(getValue() == null)
			return;
		
		final int year = getValue().getYear() + 1900;
		final int month = getValue().getMonth() + 1;
		final String week = IMessages.INSTANCE.mdc_calendar_short_week(getValue().getDay() + 1);
		final String shortMonth = IMessages.INSTANCE.mdc_calendar_short_month(month);
		final int day = getValue().getDate();
		headerYear.setText(String.valueOf(year));
		headerDate.setText(IMessages.INSTANCE.mdc_calendar_header_day(week, shortMonth, day));
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized)
			drawHeader();
	}

}
