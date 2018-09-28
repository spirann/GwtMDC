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
package gwt.material.design.components.client.ui;

import java.util.Date;

import gwt.material.design.components.client.ui.misc.calendar.MaterialCalendarBase;
import gwt.material.design.components.client.ui.misc.calendar.MaterialCalendarDaysSelector;
import gwt.material.design.components.client.ui.misc.calendar.MaterialCalendarHeaderPeriod;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendarPeriod
		extends MaterialCalendarBase<Date[], MaterialCalendarHeaderPeriod, MaterialCalendarDaysSelector> {

	@Override
	protected void onInitialize() {

		header = new MaterialCalendarHeaderPeriod();
		daySelector = new MaterialCalendarDaysSelector();

		super.onInitialize();

		header.setValue(getValue());
		header.addInitialDateClickHandler(event -> {
			if (getValue()[0] != null) {
				monthSelector.setValue(getValue()[0].getMonth() + 1);
			}
		});
		header.addFinalDateClickHandler(event -> {
			if (getValue()[1] != null) {
				monthSelector.setValue(getValue()[1].getMonth() + 1);
			}
		});

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

	@Override
	public void setValue(Date[] value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized) {
			final Date maxDate = getValue()[1];
			header.setValue(value, false);
			daySelector.setValue(value, false);
			monthSelector.setValue(maxDate == null ? null : maxDate.getMonth() + 1, false);
			yearSelector.setValue(maxDate == null ? null : maxDate.getYear() + 1900, false);
		}
	}
}