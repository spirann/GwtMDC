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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.form.MaterialValuedField;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.utils.helper.DateTimeHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendarMonthSelector extends MaterialValuedField<Integer> {
	
	private Date auxDate = adjustDate(new Date());

	public MaterialCalendarMonthSelector() {
		super(CssName.MDC_CALENDAR__MONTH_SELECTOR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setMonth(getValue());
	}

	protected void setMonth(final int month) {

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

		drawMonths();
	}

	protected void drawMonths() {

		clear();

		final String name = getId();

		for (int m = 1; m <= 12; m++) {
			
			final Integer month = m;
			final MaterialCalendarItem monthLabel = new MaterialCalendarItem();
			monthLabel.setText(IMessages.INSTANCE.mdc_calendar_short_month(month));
			monthLabel.setName(name);
			monthLabel.addSelectionHandler(event -> {
				if (event.getValue() && month != getValue())
					super.setValue(month, true);
			});

			if (getValue() != null && m == getValue())
				monthLabel.setSelected(true, true);

			add(monthLabel);
		}

	}

	@Override
	public void setValue(Integer value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized && getWidgetCount() > 0) {
			((MaterialCalendarItem) getWidget(value - 1)).setSelected(true, false);
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
