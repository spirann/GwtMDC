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
import java.util.LinkedList;
import java.util.List;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.lang.MdcRange;
import gwt.material.design.components.client.lang.MdcYear;
import gwt.material.design.components.client.ui.MaterialDivider;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.datePicker.headers.RangeDateHeader;
import gwt.material.design.components.client.ui.misc.datePicker.headers.SingleDateHeader;
import gwt.material.design.components.client.ui.misc.datePicker.inlines.DayMonthYear;
import gwt.material.design.components.client.ui.misc.datePicker.inlines.MonthYear;
import gwt.material.design.components.client.ui.misc.datePicker.inlines.YearRange;
import gwt.material.design.components.client.ui.misc.datePicker.selectors.day.Days;
import gwt.material.design.components.client.ui.misc.datePicker.selectors.month.Months;
import gwt.material.design.components.client.ui.misc.datePicker.selectors.year.Years;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class DatePickerContent extends Div {

	protected final SingleDateHeader singleDateHeader = new SingleDateHeader();
	protected final RangeDateHeader rangeDateHeader = new RangeDateHeader();

	protected final DayMonthYear dayMonthYear = new DayMonthYear();
	protected final YearRange yearRange = new YearRange();

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

		days.setMinValue(new MdcDate());

		monthYear.addSelectionHandler(event -> {
			final int month = monthYear.getSelection().getMonth();
			final int year = monthYear.getSelection().getYear();
			// months.setSelection(month, false);
			years.setSelection(Arrays.asList(new MdcYear(year)), false);
			days.draw(year, month);
		});
		months.addSelectionHandler(event -> {
			final MdcMonth mdcMonth = monthYear.getSelection();
			// mdcMonth.setMonth(months.getSelection());
			monthYear.setSelection(mdcMonth, false);
			days.draw(mdcMonth.getYear(), mdcMonth.getMonth());
		});
		years.addSelectionHandler(event -> {
			// final MdcMonth mdcMonth = monthYear.getSelection();
			// mdcMonth.setYear(years.getSelection()[0]);
			// monthYear.setSelection(mdcMonth, false);
			// days.drawDates(mdcMonth.getYear(), mdcMonth.getMonth());
		});

		days.addSelectionHandler(event -> {

			final MdcDate start;
			final MdcDate end;
			if (event.getValue() == null || event.getValue().isEmpty()) {
				start = null;
				end = null;
			} else {
				final List<MdcDate> values = new LinkedList<>(event.getValue());
				start = values.get(0);
				
				if (values.size() == 1)
					end = null;
				else
					end = values.get(values.size() - 1);
			}

			final MdcRange<MdcDate> range = new MdcRange<>(start, end);
			
			singleDateHeader.setValue(start);
			rangeDateHeader.setValue(range);

		});
		yearRange.addSelectionHandler(event -> years.draw(yearRange.getSelection()));

		//singleDateHeader.setCompact(true);
		
		add(singleDateHeader);
		add(new MaterialDivider());
		add(rangeDateHeader);
		add(new MaterialDivider());
		add(dayMonthYear);
		add(new MaterialDivider());
		add(yearRange);
		add(new MaterialDivider());

		add(monthYear);
		add(days);
		add(months);
		add(years);
	}

}
