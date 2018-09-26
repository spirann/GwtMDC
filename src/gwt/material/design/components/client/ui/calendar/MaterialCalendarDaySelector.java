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
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialIconButton;
import gwt.material.design.components.client.ui.form.MaterialValuedField;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.utils.helper.DateTimeHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendarDaySelector extends MaterialValuedField<Date> {

	protected Div actions = new Div(CssName.MDC_CALENDAR__ACTIONS);
	protected MaterialIconButton previousMonth = new MaterialIconButton(IconType.CHEVRON_LEFT);
	protected MaterialIconButton nextMonth = new MaterialIconButton(IconType.CHEVRON_RIGHT);
	protected Label monthLabel = new Label(CssName.MDC_CALENDAR__DAY_SELECTOR__MONTH__LABEL);
	protected Div contentWeek = new Div(CssName.MDC_CALENDAR__DAY_SELECTOR__WEEK__CONTENT);
	protected Div items = new Div(CssName.MDC_CALENDAR__ITEMS);

	private Date auxDate = adjustDate(new Date());

	public MaterialCalendarDaySelector() {
		super(CssName.MDC_CALENDAR__DAY_SELECTOR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		previousMonth.addStyleName(CssName.MDC_CALENDAR__ACTION);
		previousMonth.addClickHandler(event -> decreaseMonth());

		nextMonth.addStyleName(CssName.MDC_CALENDAR__ACTION);
		nextMonth.addClickHandler(event -> increaseMonth());

		actions.add(previousMonth);
		actions.add(monthLabel);
		actions.add(nextMonth);

		add(actions);
		add(contentWeek);
		add(items);

		drawDays();
		drawWeeks();
		drawMonth();
	}

	public HandlerRegistration addClickMonthHandler(ClickHandler handler) {
		return monthLabel.addClickHandler(handler);
	}

	protected void increaseMonth() {
		setMonth(auxDate.getMonth() + 1);
	}

	protected void decreaseMonth() {
		setMonth(auxDate.getMonth() - 1);
	}

	public void setYear(final int year) {
		auxDate.setYear(year);
		drawDays();
		drawMonth();
	}

	public void setMonth(final int month) {

		final int newMonth;
		final int newYear;

		if (month < 0) {
			newMonth = 11;
			newYear = auxDate.getYear() - 1;
		} else if (month > 11) {
			newMonth = 0;
			newYear = auxDate.getYear() + 1;
		} else {
			newMonth = month;
			newYear = auxDate.getYear();
		}

		auxDate.setDate(1);
		auxDate.setMonth(newMonth);
		auxDate.setYear(newYear);
		drawDays();
		drawMonth();
	}

	protected void drawMonth() {
		final int year = auxDate.getYear() + 1900;
		final int month = auxDate.getMonth() + 1;
		final String fullMonth = IMessages.INSTANCE.mdc_calendar_full_month(month);
		monthLabel.setText(IMessages.INSTANCE.mdc_calendar_body_month(fullMonth, year));
	}

	protected void drawWeeks() {
		contentWeek.clear();
		for (int i = 1; i < 8; i++)
			contentWeek.add(new WeekLabel(i));
	}

	protected void drawDays() {

		items.clear();

		final int firstDay = 1;
		final int lastDay = DateTimeHelper.lastDayOfMonth(this.auxDate);

		final Date date = adjustDate(this.auxDate);
		final String name = getId();
		final Date value = getValue();
		final Long valueAsTime = value == null ? null : value.getTime();

		for (int d = firstDay, w = 0; d <= lastDay; d++, w++) {
			date.setDate(d);

			if (w == 7)
				w = 0;

			final Date adjustedDate = adjustDate(date);
			final MaterialCalendarItem dayButton = new MaterialCalendarItem();
			dayButton.setText(String.valueOf(d));
			dayButton.setName(name);
			dayButton.addSelectionHandler(event -> {
				if (event.getValue())
					super.setValue(adjustedDate, true);
			});

			if (value != null && adjustedDate.getTime() == valueAsTime)
				dayButton.setSelected(true, true);

			if (w != date.getDay()) {
				d--;
				dayButton.setText("");
				dayButton.setVisibility(Visibility.HIDDEN);
			}

			items.add(dayButton);
		}

	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		super.setValue(adjustDate(value), fireEvents);

		this.auxDate = getValue();

		if (initialized) {
			drawDays();
			drawMonth();
		}
	}

	protected Date adjustDate(final Date date) {
		return new Date(DateTimeHelper.fromTheDate(date.getTime()));
	}

	protected class WeekLabel extends Label {
		protected WeekLabel(final int date) {
			super(CssName.MDC_CALENDAR__DAY_SELECTOR__WEEK__LABEL, CssName.MDC_TYPOGRAPHY__CAPTION);
			setText(IMessages.INSTANCE.mdc_calendar_letter_week(date));
		}
	}
}
