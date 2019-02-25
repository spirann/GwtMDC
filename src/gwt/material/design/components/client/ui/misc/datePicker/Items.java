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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.DatePickerType;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Items extends Div implements HasType<DatePickerType> {

	protected final TypeMixin<Items, DatePickerType> typeMixin = new TypeMixin<>(this, DatePickerType.RANGE);

	private MdcDate[] selectedItems;
	private final Map<MdcDate, ItemsItem> items = new LinkedHashMap<>();

	public Items() {
		super(CssName.MDC_DATEPICKER__ITEMS);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		drawDates();

		select(new MdcDate(2019, 2, 15), new MdcDate(2019, 2, 16), new MdcDate(2019, 2, 17), new MdcDate(2019, 2, 25));
	}

	@Override
	public void setType(DatePickerType type) {
		typeMixin.setType(type);
	}

	@Override
	public DatePickerType getType() {
		return typeMixin.getType();
	}

	public void drawDates() {
		drawDates(MdcDate.daysOfMonth(true));
	}

	public void drawDates(final int year, final int month) {
		drawDates(MdcDate.daysOfMonth(year, month, true));
	}

	protected void drawDates(final Collection<MdcDate> dates) {

		clear();

		for (int weekDay = 0; weekDay < 7; weekDay++)
			add(new ItemsHeader(weekDay));

		dates.forEach(date -> {
			final ItemsItem item = new ItemsItem(date);
			item.addClickHandler(event -> {

				final List<MdcDate> selectedDates = new LinkedList<>(
						selectedItems == null ? Arrays.asList() : Arrays.asList(selectedItems));

				final boolean isSelected = selectedDates.contains(item.getDate());

				switch (getType()) {
				case RANGE:
					if (selectedDates.size() > 1) {
						selectedDates.clear();
						selectedDates.add(getTheFarthestFrom(date, selectedItems));
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

				select(selectedDates.stream().toArray(MdcDate[]::new));
			});
			add(item);
			items.put(date, item);
		});

		select(selectedItems);
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

	public void select(final MdcDate... selectedItems) {
		unSelectAll();
		this.selectedItems = null;

		if (selectedItems == null || selectedItems.length == 0)
			return;

		switch (getType()) {
		case RANGE:
			this.selectedItems = rangeSelect(selectedItems);
			break;
		case MULTIPLE:
			this.selectedItems = multipleSelect(selectedItems);
			break;
		case SINGLE:
		default:
			this.selectedItems = singleSelect(selectedItems);
			break;
		}
	}

	protected final native void unSelectAll()/*-{
		var itemClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ITEMS__ITEM;
		var activeClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ITEMS__ACTIVE;
		var activeFirstClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ITEMS__ACTIVE_FIRST;
		var activeLastClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ITEMS__ACTIVE_LAST;
		$wnd.jQuery('.' + itemClass).removeClass(activeClass);
		$wnd.jQuery('.' + itemClass).removeClass(activeFirstClass);
		$wnd.jQuery('.' + itemClass).removeClass(activeLastClass);
	}-*/;

	protected MdcDate[] singleSelect(final MdcDate... selectedItems) {

		final ItemsItem item = Arrays.asList(selectedItems).stream().filter(date -> items.containsKey(date)).findAny()
				.map(date -> items.get(date)).orElse(null);

		if (item != null) {
			item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE);
			item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE_FIRST);
			item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE_LAST);
		}

		return selectedItems;
	}

	protected MdcDate[] multipleSelect(final MdcDate... selectedItems) {

		final List<MdcDate> collection = Arrays.asList(selectedItems);

		collection.stream().filter(date -> items.containsKey(date)).forEach(date -> {

			final ItemsItem item = items.get(date);
			item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE);

			if (!collection.contains(date.previous()))
				item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE_FIRST);

			if (!collection.contains(date.next()))
				item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE_LAST);

		});

		return selectedItems;
	}

	protected MdcDate[] rangeSelect(MdcDate... selectedItems) {

		selectedItems = Arrays.asList(selectedItems).stream()
				.sorted((d1, d2) -> Long.compare(d1.getTimestamp(), d2.getTimestamp())).toArray(MdcDate[]::new);

		final MdcDate start = selectedItems[0];
		final MdcDate end = selectedItems[selectedItems.length - 1];

		items.values().stream().forEach(item -> {

			final MdcDate date = item.getDate();
			final int timestamp = date.getTimestamp();

			if (timestamp >= start.getTimestamp() && timestamp <= end.getTimestamp()) {
				item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE);
				if (item.getDate().equals(start))
					item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE_FIRST);
				if (item.getDate().equals(end))
					item.addStyleName(CssName.MDC_DATEPICKER__ITEMS__ACTIVE_LAST);
			}
		});

		return selectedItems;
	}

}
