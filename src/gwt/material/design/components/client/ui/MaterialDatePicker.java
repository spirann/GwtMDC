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

import java.util.Arrays;
import java.util.Collection;

import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasSelection;
import gwt.material.design.components.client.base.mixin.HasSelectionMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.lang.MdcYear;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.datePicker.headers.SingleDateHeader;
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
public class MaterialDatePicker extends Div implements HasSelection<MdcDate>, HasSelectionHandlers<MdcDate> {

	protected final SingleDateHeader header = new SingleDateHeader();
	protected final MonthYear monthYear = new MonthYear();
	protected final YearRange yearRange = new YearRange();
	protected final Days days = new Days();
	protected final Months months = new Months();
	protected final Years years = new Years();

	protected final HasSelectionMixin<MaterialDatePicker, MdcDate> selectionMixin = new HasSelectionMixin<>(this);

	public MaterialDatePicker() {
		super(CssName.MDC_DATEPICKER);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		header.addDayClickHandler(event -> {
			final MdcDate date = header.getValue();
			if (date != null)
				monthYear.setSelection(date.getMdcMonth());
		});
		header.addYearClickHandler(event -> {
		});

		monthYear.addSelectionHandler(event -> {
			days.draw(event.getValue());
			months.setSelection(Arrays.asList(new MdcMonth(event.getValue().getMonth())), false);
			years.setSelection(Arrays.asList(new MdcYear(event.getValue().getYear())), false);
		});

		yearRange.addSelectionHandler(event -> years.draw(event.getValue()));

		days.addSelectionHandler(event -> {
			final Collection<MdcDate> collection = event.getValue();
			final MdcDate date = collection == null || collection.isEmpty() ? new MdcDate()
					: collection.iterator().next();
			header.setValue(date);
		});

		months.addSelectionHandler(event -> {

			final Collection<MdcMonth> collection = event.getValue();
			final MdcMonth month = collection == null || collection.isEmpty() ? new MdcMonth()
					: collection.iterator().next();

			final MdcMonth visibleMonth = monthYear.getSelection() == null ? new MdcMonth() : monthYear.getSelection();
			visibleMonth.setMonth(month.getMonth());
			monthYear.setSelection(visibleMonth);
		});

		years.addSelectionHandler(event -> {

			final Collection<MdcYear> collection = event.getValue();
			final MdcYear year = collection == null || collection.isEmpty() ? new MdcYear()
					: collection.iterator().next();

			yearRange.setSelection(year.getYear(), false);
			
			final MdcMonth visibleMonth = monthYear.getSelection() == null ? new MdcMonth() : monthYear.getSelection();
			visibleMonth.setYear(year.getYear());
			monthYear.setSelection(visibleMonth);

		});

		add(header);
		add(monthYear);
		add(days);
		add(months);
		add(yearRange);
		add(years);

		final MdcDate date = getSelection() == null ? new MdcDate() : getSelection();
		header.setValue(date);
		monthYear.setSelection(date.getMdcMonth());
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<MdcDate> handler) {
		return selectionMixin.addSelectionHandler(handler);
	}

	@Override
	public void setSelection(MdcDate selected) {
		selectionMixin.setSelection(selected);
	}

	@Override
	public void setSelection(MdcDate selected, boolean fireEvents) {
		selectionMixin.setSelection(selected, fireEvents);
	}

	@Override
	public MdcDate getSelection() {
		return selectionMixin.getSelection();
	}
}
