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
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

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
public class MaterialCalendarDaysSelector extends MaterialValuedField<Date[]> {

	protected Div actions = new Div(CssName.MDC_CALENDAR__ACTIONS);
	protected MaterialIconButton previousMonth = new MaterialIconButton(IconType.CHEVRON_LEFT);
	protected MaterialIconButton nextMonth = new MaterialIconButton(IconType.CHEVRON_RIGHT);
	protected Label monthLabel = new Label(CssName.MDC_CALENDAR__DAY_SELECTOR__MONTH__LABEL);
	protected Div contentWeek = new Div(CssName.MDC_CALENDAR__DAY_SELECTOR__WEEK__CONTENT);
	protected Div items = new Div(CssName.MDC_CALENDAR__ITEMS);

	private Date auxDate = adjustDate(new Date());

	private Map<Long, MaterialCalendarItem> mapedItems = new LinkedHashMap<>();

	public MaterialCalendarDaysSelector() {
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

	public HandlerRegistration addMonthClickHandler(ClickHandler handler) {
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
		mapedItems.clear();

		final int firstDay = 1;
		final int lastDay = DateTimeHelper.lastDayOfMonth(this.auxDate);

		final Date date = adjustDate(this.auxDate);

		final Date minValue;
		final Date maxValue;

		if (getValue() == null || getValue().length != 2) {
			minValue = null;
			maxValue = null;
		} else {
			minValue = getValue()[0];
			maxValue = getValue()[1];
		}

		final Long minValueAsTime = minValue == null ? null : minValue.getTime();
		final Long maxValueAsTime = maxValue == null ? null : maxValue.getTime();

		for (int d = firstDay, w = 0; d <= lastDay; d++, w++) {
			date.setDate(d);

			if (w == 7)
				w = 0;

			final Date adjustedDate = adjustDate(date);
			final MaterialCalendarItem dayButton = new MaterialCalendarItem();
			dayButton.setText(String.valueOf(d));
			dayButton.setName(String.valueOf(d));
			dayButton.addSelectionHandler(event -> {
				if (event.getValue())
					updateValue(adjustedDate);

			});

			if ((minValueAsTime != null && adjustedDate.getTime() == minValueAsTime)
					|| (maxValueAsTime != null && adjustedDate.getTime() == maxValueAsTime))
				dayButton.setSelected(true, false);

			if (w != date.getDay()) {
				d--;
				dayButton.setText("");
				dayButton.setVisibility(Visibility.HIDDEN);
			}

			items.add(dayButton);
			mapedItems.put(adjustedDate.getTime(), dayButton);
		}
		updateStyles();
	}

	private void updateValue(final Date date) {

		final Date[] values = getValue() == null ? new Date[2] : getValue();

		final Date minDate = values[0];
		final Date maxDate = values[1];

		final Long dateAsTime = date.getTime();
		final Long minDateAsTime = minDate == null ? null : minDate.getTime();
		final Long maxDateAsTime = maxDate == null ? null : maxDate.getTime();

		if (minDateAsTime != null) {
			final MaterialCalendarItem item = mapedItems.get(minDateAsTime);
			if (item != null)
				item.setSelected(false, false);
		}

		if (maxDateAsTime != null) {
			final MaterialCalendarItem item = mapedItems.get(maxDateAsTime);
			if (item != null)
				item.setSelected(false, false);
		}

		if (minDateAsTime == null && maxDateAsTime == null) {
			values[0] = date;
		} else if (minDateAsTime == null && maxDateAsTime != null) {
			if (maxDateAsTime > dateAsTime) {
				values[0] = date;
				values[1] = maxDate;
			} else {
				values[0] = maxDate;
				values[1] = date;
			}
		} else if (minDateAsTime != null && maxDateAsTime == null) {
			if (minDateAsTime > dateAsTime) {
				values[0] = date;
				values[1] = minDate;
			} else {
				values[0] = minDate;
				values[1] = date;
			}
		} else {

			// Date is selected already
			if (minDateAsTime.equals(dateAsTime) || maxDateAsTime.equals(dateAsTime)) {
				values[0] = minDate;
				values[1] = maxDate;
			}
			// Date is smaller than min date
			else if (minDateAsTime > dateAsTime) {
				values[0] = date;
				values[1] = maxDate;
			}
			// Date between select min and max dates
			else if (minDateAsTime < dateAsTime && dateAsTime < maxDateAsTime) {

				final long differenceMin = dateAsTime - minDateAsTime;
				final long differenceMax = maxDateAsTime - dateAsTime;

				// Most near the min date
				if (differenceMin < differenceMax) {
					values[0] = date;
					values[1] = maxDate;
				}
				// Most near the max date
				else if (differenceMin > differenceMax) {
					values[0] = minDate;
					values[1] = date;
				}
				// Right in the center
				else {
					values[0] = date;
					values[1] = null;
				}
			}
			// Date is bigger than max date
			else {
				values[0] = minDate;
				values[1] = date;
			}
		}

		if (values[0] != null) {
			final MaterialCalendarItem item = mapedItems.get(values[0].getTime());
			if (item != null)
				item.setSelected(true, false);
		}

		if (values[1] != null) {
			final MaterialCalendarItem item = mapedItems.get(values[1].getTime());
			if (item != null)
				item.setSelected(true, false);
		}

		super.setValue(values, true);

		updateStyles();
	}

	private void updateStyles() {

		if (getValue() == null || getValue().length != 2 || getValue()[0] == null || getValue()[1] == null) {
			mapedItems.entrySet().forEach(entry -> clearStyles(entry.getValue()));
			return;
		}

		final Date[] values = getValue();

		final Date minDate = values[0];
		final Date maxDate = values[1];

		final Long minDateAsTime = minDate.getTime();
		final Long maxDateAsTime = maxDate.getTime();

		mapedItems.entrySet().forEach(entry -> {
			final Long time = entry.getKey();
			final MaterialCalendarItem item = entry.getValue();
			
			clearStyles(item);
			
			if (time.equals(minDateAsTime))
				item.addStyleName(CssName.MDC_CALENDAR__MULT_DAYS__MIN);
			else if (time.equals(maxDateAsTime))
				item.addStyleName(CssName.MDC_CALENDAR__MULT_DAYS__MAX);
			else if (time > minDateAsTime && time < maxDateAsTime)
				item.addStyleName(CssName.MDC_CALENDAR__MULT_DAYS__BETWEEN);
		});
	}

	private void clearStyles(final Widget widget) {
		widget.removeStyleName(CssName.MDC_CALENDAR__MULT_DAYS__MIN);
		widget.removeStyleName(CssName.MDC_CALENDAR__MULT_DAYS__MAX);
		widget.removeStyleName(CssName.MDC_CALENDAR__MULT_DAYS__BETWEEN);
	}
	
	public void setStartDate(final Date date) {
		final Date[] values = getValue() == null ? new Date[2] : getValue();
		values[0] = date;
		setValue(values);
	}
	
	public void setEndDate(final Date date) {
		final Date[] values = getValue() == null ? new Date[2] : getValue();
		values[1] = date;
		setValue(values);
	}

	/**
	 * The value must has two dates, the first is the smaller and the last is the bigger.
	 */
	@Override
	public void setValue(Date[] value, boolean fireEvents) {
		
		if(value.length > 2)
			throw new IllegalArgumentException("The value must has two dates, the first is the smaller and the last is the bigger.");
		
		super.setValue(value, fireEvents);
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
