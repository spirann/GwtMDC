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

import com.google.gwt.dom.client.Style.Visibility;

import gwt.material.design.components.client.utils.helper.DateTimeHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendarDaySelector extends MaterialCalendarBaseDaySelector<Date> {

	@Override
	protected void drawDays() {

		items.clear();

		final int firstDay = 1;
		final int lastDay = DateTimeHelper.lastDayOfMonth(this.auxDate == null ? new Date() : this.auxDate);

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
		super.setValue(value == null ? null : adjustDate(value), fireEvents);
		this.auxDate = adjustDate(value);
		if (initialized) {
			drawDays();
			drawMonth();
		}
	}
}
