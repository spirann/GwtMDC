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
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.CssName;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCalendarDaysSelector extends MaterialCalendarBaseDaySelector<Date[]> {

	protected Map<Long, MaterialCalendarItem> mapedItems = new LinkedHashMap<>();

	@Override
	protected void drawDays() {
		mapedItems.clear();
		super.drawDays();
		updateStyles();
	}
	
	@Override
	protected MaterialCalendarItem drawItem(Date date, String name, boolean visible) {
		
		final MaterialCalendarItem item = super.drawItem(date, name, visible);
		item.setName(String.valueOf(date.getTime()));
		item.addSelectionHandler(event -> {
			if (event.getValue())
				updateValue(date);
		});

		final Date minValue = getValue()[0];
		final Date maxValue = getValue()[1];
		final Long minValueAsTime = minValue == null ? null : minValue.getTime();
		final Long maxValueAsTime = maxValue == null ? null : maxValue.getTime();
		if ((minValueAsTime != null && date.getTime() == minValueAsTime)
				|| (maxValueAsTime != null && date.getTime() == maxValueAsTime))
			item.setSelected(true, false);

		mapedItems.put(date.getTime(), item);
		return item;
	}

	protected void updateValue(final Date date) {

		final Date[] values = getValue();

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

	protected void updateStyles() {

		if (getValue()[0] == null || getValue()[1] == null) {
			mapedItems.entrySet().forEach(entry -> clearStyles(entry.getValue()));
			return;
		}

		final Date[] values = getValue();

		final Date minDate = values[0];
		final Date maxDate = values[1];

		final Long minDateAsTime = minDate.getTime();
		final Long maxDateAsTime = maxDate.getTime();

		mapedItems.entrySet().parallelStream().forEach(entry -> {
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

	protected void clearStyles(final Widget widget) {
		widget.removeStyleName(CssName.MDC_CALENDAR__MULT_DAYS__MIN);
		widget.removeStyleName(CssName.MDC_CALENDAR__MULT_DAYS__MAX);
		widget.removeStyleName(CssName.MDC_CALENDAR__MULT_DAYS__BETWEEN);
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

	/**
	 * The value must has two dates, the first is the smaller and the last is the
	 * bigger.
	 */
	@Override
	public void setValue(Date[] value, boolean fireEvents) {

		if (value.length > 2)
			throw new IllegalArgumentException(
					"The value must has two dates, the first is the smaller and the last is the bigger.");

		super.setValue(value, fireEvents);
		if (initialized) {
			drawDays();
			drawMonth();
		}
	}
}
