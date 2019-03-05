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

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class DatePickerContent extends Div {
	
	protected final Days days = new Days();
	protected final Months months = new Months();
	protected final Years years = new Years();
	protected final MonthYear monthYear = new MonthYear();
	
	public DatePickerContent() {
		super(CssName.MDC_DATEPICKER);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		monthYear.addSelectionHandler(event -> {
			final int month = monthYear.getSelection().getMonth();
			final int year = monthYear.getSelection().getYear();			
			months.setSelection(month, false);
			years.setSelection(year, false);
			days.drawDates(year, month);
		});
		months.addSelectionHandler(event -> {			
			final MdcMonth mdcMonth = monthYear.getSelection();
			mdcMonth.setMonth(months.getSelection());
			monthYear.setSelection(mdcMonth, false);	
			days.drawDates(mdcMonth.getYear(), mdcMonth.getMonth());		
		});
		years.addSelectionHandler(event -> {		
			final MdcMonth mdcMonth = monthYear.getSelection();
			mdcMonth.setYear(years.getSelection());
			monthYear.setSelection(mdcMonth, false);
			days.drawDates(mdcMonth.getYear(), mdcMonth.getMonth());
		});

		days.addSelectionHandler(event -> {});
		
		add(monthYear);
		add(days);
		add(months);
		add(years);
	}
}
