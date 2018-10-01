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

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.misc.calendar.MaterialCalendarBase;
import gwt.material.design.components.client.ui.misc.calendar.MaterialCalendarDaySelector;
import gwt.material.design.components.client.ui.misc.calendar.MaterialCalendarHeader;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendar extends MaterialCalendarBase<Date, MaterialCalendarHeader, MaterialCalendarDaySelector> {

	protected Widget todayAction;
	
	public MaterialCalendar() {
		super(new MaterialCalendarHeader(), new MaterialCalendarDaySelector());
	}

	public void setShowTodayAction(final boolean show) {
		if (show && todayAction == null)
			todayAction = addAction(IMessages.INSTANCE.mdc_calendar_today(), event -> setValue(today()));
		else if (!show && todayAction != null && todayAction.getParent() != null)
			todayAction.removeFromParent();
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

		header.setValue(getValue());
		header.addYearClickHandler(event -> {
			if (daySelector.isChangeYear())
				toggleSelector(yearSelector);
		});
		header.addDateClickHandler(event -> {
			yearSelector.setValue(getValue().getYear() + 1900);
			monthSelector.setValue(getValue().getMonth() + 1);
		});
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized) {
			header.setValue(value, false);
			if (value == null || daySelector.getValue() == null || value.getTime() != daySelector.getValue().getTime())
				daySelector.setValue(value, false);
			monthSelector.setValue(value == null ? null : value.getMonth() + 1, false);
			yearSelector.setValue(value == null ? null : value.getYear() + 1900, false);
		}
	}
}
