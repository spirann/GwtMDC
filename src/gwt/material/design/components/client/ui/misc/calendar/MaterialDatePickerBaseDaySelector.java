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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialIconButton;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.utils.helper.DateTimeHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public abstract class MaterialDatePickerBaseDaySelector<T> extends MaterialValuedField<T> {

	protected final Div actions = new Div(CssName.MDC_DATEPICKER__ACTIONS);
	protected final MaterialIconButton previousMonth = new MaterialIconButton(IconType.CHEVRON_LEFT);
	protected final MaterialIconButton nextMonth = new MaterialIconButton(IconType.CHEVRON_RIGHT);
	protected final Label monthLabel = new Label(CssName.MDC_DATEPICKER__DAY_SELECTOR__MONTH__LABEL);
	protected final Div contentWeek = new Div(CssName.MDC_DATEPICKER__DAY_SELECTOR__WEEK__CONTENT);
	protected final Div items = new Div(CssName.MDC_DATEPICKER__ITEMS);

	protected final Map<Long, MaterialDatePickerItem> mapedItems = new LinkedHashMap<>();

	protected final Date today = adjustDate(new Date());
	protected Date auxDate = new Date(today.getTime());

	private boolean changeMonth = true;
	private boolean changeYear = true;

	private Date minDate;
	private Date maxDate;

	// Helper to draw days if necessary
	private int currentMonth;
	private int currentYear;

	public MaterialDatePickerBaseDaySelector() {
		super(CssName.MDC_DATEPICKER__DAY_SELECTOR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		previousMonth.addStyleName(CssName.MDC_DATEPICKER__ACTION);
		previousMonth.addClickHandler(event -> decreaseMonth());

		nextMonth.addStyleName(CssName.MDC_DATEPICKER__ACTION);
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

	public int getVisibleYear() {
		return auxDate.getYear() + 1900;
	}

	public int getVisibleMonth() {
		return auxDate.getMonth() + 1;
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
	
	public void setYearAndMonth(final int year, final int month) {
		auxDate.setDate(1);
		auxDate.setMonth(month);
		auxDate.setYear(year);
		drawDays();
		drawMonth();
	}

	public boolean isChangeMonth() {
		return changeMonth;
	}

	public void setChangeMonth(final boolean change) {
		this.changeMonth = change;
		drawMonth();
	}

	public boolean isChangeYear() {
		return changeYear;
	}

	public void setChangeYear(final boolean change) {
		this.changeYear = change;
		drawMonth();
	}

	protected void drawDays() {

		if (currentMonth == this.auxDate.getMonth() && currentYear == this.auxDate.getYear())
			return;

		mapedItems.clear();
		items.clear();

		final String name = getId();

		final Long minDateAsTime = getMinDate() == null ? null
				: adjustDate(getMinDate()).getTime() - DateTimeHelper.hoursInMillis(6);
		final Long maxDateAsTime = getMaxDate() == null ? null
				: adjustDate(getMaxDate()).getTime() + DateTimeHelper.hoursInMillis(6);

		final Date date = new Date(this.auxDate.getTime());
		final int month = date.getMonth();
		final int year = date.getYear();
		final int firstDay = 1;
		final int lastDay = DateTimeHelper.lastDayOfMonth(date);

		date.setDate(firstDay);
		final long minus = DateTimeHelper.daysInMillis(date.getDay());
		final long firtsDayToDraw = date.getTime() - minus;

		date.setDate(lastDay);
		final long plus = DateTimeHelper.daysInMillis(6 - date.getDay());
		final long lastDayToDraw = date.getTime() + plus
		// Add 12 hours because of summer hour
				+ DateTimeHelper.hoursInMillis(12);

		for (long d = firtsDayToDraw; d <= lastDayToDraw; d += DateTimeHelper.daysInMillis(1)) {
			final Date adjustedDate = new Date(d);
			final boolean visible = adjustedDate.getMonth() == month && adjustedDate.getYear() == year;
			final boolean enabled = (minDateAsTime == null || minDateAsTime < adjustedDate.getTime())
					&& (maxDateAsTime == null || maxDateAsTime > adjustedDate.getTime());
			final MaterialDatePickerItem item = drawItem(adjustedDate, name, visible, enabled);
			mapedItems.put(adjustedDate.getTime(), item);
			items.add(item);
		}

		currentMonth = this.auxDate.getMonth();
		currentYear = this.auxDate.getYear();
	}

	protected MaterialDatePickerItem drawItem(final Date date, final String name, final boolean visible,
			final boolean enabled) {

		final MaterialDatePickerItem item = new MaterialDatePickerItem();
		item.setEnabled(enabled);

		if (today.getTime() == date.getTime())
			item.addStyleName(CssName.MDC_DATEPICKER__ITEM_TODAY);
		item.setText(String.valueOf(date.getDate()));
		item.setName(name);

		if (!visible)
			item.setVisibility(Visibility.HIDDEN);

		drawTooltip(date, item);

		return item;
	}

	protected void drawTooltip(final Date date, final MaterialDatePickerItem item) {
		item.setTooltip(getDateTooltip(date));
		item.clear();
		if (item.getTooltip() != null)
			item.add(new Div(CssName.MDC_DATEPICKER__ITEM__TOOLTIP_INDICATOR));
	}

	protected void drawMonth() {

		final int year = auxDate.getYear() + 1900;
		final int month = auxDate.getMonth() + 1;
		final String fullMonth = IMessages.INSTANCE.mdc_calendar_full_month(month);
		monthLabel.setText(IMessages.INSTANCE.mdc_calendar_body_month(fullMonth, year));

		if (changeYear && changeMonth) {
			previousMonth.setVisibility(Visibility.VISIBLE);
			nextMonth.setVisibility(Visibility.VISIBLE);
		} else if (!changeYear && changeMonth) {
			if (month > 1)
				previousMonth.setVisibility(Visibility.VISIBLE);
			else
				previousMonth.setVisibility(Visibility.HIDDEN);

			if (month < 12)
				nextMonth.setVisibility(Visibility.VISIBLE);
			else
				nextMonth.setVisibility(Visibility.HIDDEN);

		} else {
			previousMonth.setVisibility(Visibility.HIDDEN);
			nextMonth.setVisibility(Visibility.HIDDEN);
		}
	}

	protected void drawWeeks() {
		contentWeek.clear();
		for (int i = 1; i < 8; i++)
			contentWeek.add(new WeekLabel(i));
	}

	protected Date adjustDate(Date date) {
		if (date == null)
			date = new Date();
		date.setHours(12);
		date.setMinutes(0);
		date.setSeconds(0);

		date = new Date(DateTimeHelper.removeTimeIgnoringGMT(date));
		date.setHours(12);
		date.setMinutes(0);
		date.setSeconds(0);

		return date;
	}

	protected Date[] adjustDate(Date[] dates) {
		if (dates == null)
			return null;

		final Date[] adjustedDates = new Date[dates.length];
		for (int i = 0; i < adjustedDates.length; i++)
			adjustedDates[i] = dates[i] == null ? null : adjustDate(dates[i]);

		return adjustedDates;
	}

	protected class WeekLabel extends Label {
		protected WeekLabel(final int date) {
			super(CssName.MDC_DATEPICKER__DAY_SELECTOR__WEEK__LABEL, CssName.MDC_TYPOGRAPHY__CAPTION);
			setText(IMessages.INSTANCE.mdc_calendar_letter_week(date));
		}
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	private Map<Long, String> tooltips = new LinkedHashMap<>();

	public void setDateTooltip(final Date date, final String tooltip) {
		final Date adjustDate = adjustDate(date);
		final long key = adjustDate.getTime();
		tooltips.put(key, tooltip);

		final MaterialDatePickerItem item = mapedItems.get(key);
		if (item != null)
			drawTooltip(adjustDate, item);
	}

	public String getDateTooltip(final Date date) {
		return tooltips.get(adjustDate(date).getTime());
	}

	public String removeDateTooltip(final Date date) {
		final Date adjustDate = adjustDate(date);
		final long key = adjustDate.getTime();
		final String tooltip = tooltips.remove(key);

		final MaterialDatePickerItem item = mapedItems.get(key);
		if (item != null)
			drawTooltip(adjustDate, item);

		return tooltip;
	}
}
