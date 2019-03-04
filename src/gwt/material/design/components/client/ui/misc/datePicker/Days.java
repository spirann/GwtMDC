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
package gwt.material.design.components.client.ui.misc.datePicker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasSelection;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.HasSelectionMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.DatePickerType;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Days extends Div
		implements HasType<DatePickerType>, HasSelection<MdcDate[]>, HasSelectionHandlers<MdcDate[]> {

	protected final TypeMixin<Days, DatePickerType> typeMixin = new TypeMixin<>(this, DatePickerType.RANGE);
	protected final HasSelectionMixin<Days, MdcDate[]> selectionMixin = new HasSelectionMixin<>(this);
	protected final Map<MdcDate, DaysItem> items = new LinkedHashMap<>();
	
	private int year;
	private int month;
	

	public Days() {
		super(CssName.MDC_DATEPICKER__DAYS);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		drawDates();

		setSelection(new MdcDate[] { new MdcDate(2019, 2, 15), new MdcDate(2019, 2, 16), new MdcDate(2019, 2, 17),
				new MdcDate(2019, 2, 25) });
	}

	@Override
	public void setType(DatePickerType type) {
		typeMixin.setType(type);
	}

	@Override
	public DatePickerType getType() {
		return typeMixin.getType();
	}

	@SuppressWarnings("deprecation")
	public void drawDates() {
		final Date date = new Date();
		year = date.getYear() + 1900;
		month = date.getMonth() + 1;
		drawDates(year, month);
	}

	public void drawDates(final int year, final int month) {
		this.year = year;
		this.month = month;
		drawDates(MdcMonth.daysOfMonth(year, month, true));
	}

	protected void drawDates(final Collection<MdcDate> dates) {

		clear();

		for (int weekDay = 0; weekDay < 7; weekDay++)
			add(new DaysHeader(weekDay));

		dates.forEach(date -> {
			final DaysItem item = new DaysItem(date);
			if (date.getMonth() != month)
				item.addStyleName(CssName.MDC_DATEPICKER__DAYS__OUT_OF_MONTH);

			item.addClickHandler(event -> {

				final List<MdcDate> selectedDates = new LinkedList<>(
						getSelection() == null ? Arrays.asList() : Arrays.asList(getSelection()));

				final boolean isSelected = selectedDates.contains(item.getDate());

				switch (getType()) {
				case RANGE:
					if (selectedDates.size() > 1) {
						selectedDates.clear();
						selectedDates.add(getTheFarthestFrom(date, getSelection()));
					}
					break;
				case SINGLE:
					selectedDates.clear();
					break;
				default:
				case MULTIPLE:
					break;
				}

				if (isSelected)
					selectedDates.remove(date);
				else
					selectedDates.add(date);

				setSelection(selectedDates.stream().toArray(MdcDate[]::new));
			});
			add(item);
			items.put(date, item);
		});

		drawSelection(getSelection());
	}

	protected MdcDate getTheFarthestFrom(final MdcDate date, final MdcDate... dates) {

		if (dates == null || dates.length == 0)
			return null;

		if (dates.length == 1)
			return dates[1];

		return Arrays.asList(dates).stream().max(Comparator.comparing(d -> calcDifference(d, date))).orElse(null);

	}

	protected long calcDifference(final MdcDate dateOne, final MdcDate dateTwo) {

		final long timestampOne = dateOne.getTimestamp();
		final long timestampTwo = dateTwo.getTimestamp();

		return timestampOne > timestampTwo ? timestampOne - timestampTwo : timestampTwo - timestampOne;

	}

	protected final native void unSelectAll()/*-{
		var itemClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__DAYS__ITEM;
		var activeClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__DAYS__ACTIVE;
		var activeFirstClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__DAYS__ACTIVE_FIRST;
		var activeLastClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__DAYS__ACTIVE_LAST;
		$wnd.jQuery('.' + itemClass).removeClass(activeClass);
		$wnd.jQuery('.' + itemClass).removeClass(activeFirstClass);
		$wnd.jQuery('.' + itemClass).removeClass(activeLastClass);
	}-*/;

	protected void drawSelection(final MdcDate[] values) {
		unSelectAll();

		if (values != null)
			switch (getType()) {
			case RANGE:
				drawRangeSelect(values);
				break;
			case MULTIPLE:
				drawMultipleSelect(values);
				break;
			case SINGLE:
			default:
				drawSingleSelect(values);
				break;
			}
	}

	protected void drawSingleSelect(final MdcDate... selectedItems) {

		final DaysItem item = Arrays.asList(selectedItems).stream().filter(date -> items.containsKey(date)).findAny()
				.map(date -> items.get(date)).orElse(null);

		if (item != null) {
			item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE);
			item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE_FIRST);
			item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE_LAST);
		}
	}

	protected void drawMultipleSelect(final MdcDate... selectedItems) {
		final List<MdcDate> collection = Arrays.asList(selectedItems);
		collection.stream().filter(date -> items.containsKey(date)).forEach(date -> {

			final DaysItem item = items.get(date);
			item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE);

			if (!collection.contains(date.previous()))
				item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE_FIRST);

			if (!collection.contains(date.next()))
				item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE_LAST);

		});
	}

	protected void drawRangeSelect(MdcDate... selectedItems) {

		selectedItems = Arrays.asList(selectedItems).stream()
				.sorted((d1, d2) -> Long.compare(d1.getTimestamp(), d2.getTimestamp())).toArray(MdcDate[]::new);

		final MdcDate start = selectedItems[0];
		final MdcDate end = selectedItems[selectedItems.length - 1];

		items.values().stream().forEach(item -> {

			final MdcDate date = item.getDate();
			final int timestamp = date.getTimestamp();

			if (timestamp >= start.getTimestamp() && timestamp <= end.getTimestamp()) {
				item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE);
				if (item.getDate().equals(start))
					item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE_FIRST);
				if (item.getDate().equals(end))
					item.addStyleName(CssName.MDC_DATEPICKER__DAYS__ACTIVE_LAST);
			}
		});
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<MdcDate[]> handler) {
		return selectionMixin.addSelectionHandler(handler);
	}

	@Override
	public void setSelection(MdcDate[] selected) {
		drawSelection(selected);
		selectionMixin.setSelection(selected);
	}

	@Override
	public void setSelection(MdcDate[] selected, boolean fireEvents) {
		drawSelection(selected);
		selectionMixin.setSelection(selected, fireEvents);
	}

	@Override
	public MdcDate[] getSelection() {
		return selectionMixin.getSelection();
	}
}
