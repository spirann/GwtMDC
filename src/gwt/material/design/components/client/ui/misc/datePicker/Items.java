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
import java.util.LinkedHashMap;
import java.util.Map;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Items extends Div {

	private MdcDate[] selectedItems;
	private Map<MdcDate, ItemsItem> items = new LinkedHashMap<>();

	public Items() {
		super(CssName.MDC_DATEPICKER__ITEMS);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		for (int weekDay = 0; weekDay < 7; weekDay++)
			add(new ItemsHeader(weekDay));
		MdcDate.daysOfMonth(true).forEach(date -> {
			final ItemsItem item = new ItemsItem(date);
			add(item);
			items.put(date, item);
		});

		select(new MdcDate(2019, 2, 15), new MdcDate(2019, 2, 25));
	}

	public void select(final MdcDate... selectedItems) {
		unSelectAll();
		this.selectedItems = Arrays.asList(selectedItems).stream()
				.sorted((d1, d2) -> Long.compare(d1.getTimestamp(), d2.getTimestamp())).toArray(MdcDate[]::new);

		final MdcDate start = this.selectedItems[0];
		final MdcDate end = this.selectedItems[this.selectedItems.length - 1];

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
}
