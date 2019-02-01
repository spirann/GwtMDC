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

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDatePickerDaySelector extends MaterialDatePickerBaseDaySelector<Date> {

	@Override
	protected MaterialDatePickerItem drawItem(Date date, String name, boolean visible, final boolean enabled) {
		final MaterialDatePickerItem item = super.drawItem(date, name, visible, enabled);
		item.addSelectionHandler(event -> {
			if (event.getValue())
				super.setValue(date, true);
		});
		final Date value = getValue();
		final Long valueAsTime = value == null ? null : value.getTime();
		if (value != null && date.getTime() == valueAsTime)
			item.setSelected(true, true);
		return item;
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		// Unselect old
		if (value == null) {
			final Long oldValue = getValue() == null ? null : getValue().getTime();
			if (oldValue != null) {
				final MaterialDatePickerItem oldItem = mapedItems.get(oldValue);
				if (oldItem != null)
					oldItem.setSelected(false);
			}
		}

		super.setValue(value == null ? null : adjustDate(value), fireEvents);
		this.auxDate = adjustDate(value);

		final long key = this.auxDate.getTime();
		final MaterialDatePickerItem item = mapedItems.get(key);

		if (initialized && item == null && value != null) {
			drawDays();
			drawMonth();
		} else if (initialized && item != null && value != null)
			item.setSelected(true);
	}
}
