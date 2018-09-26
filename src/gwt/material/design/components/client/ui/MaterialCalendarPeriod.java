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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.calendar.MaterialCalendarDaysSelector;
import gwt.material.design.components.client.ui.calendar.MaterialCalendarHeaderPeriod;
import gwt.material.design.components.client.ui.calendar.MaterialCalendarMonthSelector;
import gwt.material.design.components.client.ui.calendar.MaterialCalendarYearSelector;
import gwt.material.design.components.client.ui.form.MaterialValuedField;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendarPeriod extends MaterialValuedField<Date[]> {

	protected MaterialCalendarHeaderPeriod header = new MaterialCalendarHeaderPeriod();
	protected MaterialCalendarDaysSelector daySelector = new MaterialCalendarDaysSelector();
	protected MaterialCalendarMonthSelector monthSelector = new MaterialCalendarMonthSelector();
	protected MaterialCalendarYearSelector yearSelector = new MaterialCalendarYearSelector();

	private Widget visibleSelector = daySelector;

	public MaterialCalendarPeriod() {
		super(CssName.MDC_CALENDAR);
		setValue(new Date[2], false);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final Date minDate = getValue() != null && getValue().length == 2 ? getValue()[0] : null;
		final Date maxDate = getValue() != null && getValue().length == 2 ? getValue()[1] : null;

		header.setValue(getValue());

		daySelector.addValueChangeHandler(event -> setValue(event.getValue(), true));
		daySelector.addMonthClickHandler(event -> toggleSelector(monthSelector));
		daySelector.setValue(getValue());

		monthSelector.setValue(maxDate == null ? null : maxDate.getMonth() + 1);
		monthSelector.addValueChangeHandler(event -> {
			daySelector.setMonth(event.getValue() - 1);
			toggleSelector(daySelector);
		});

		yearSelector.setValue(maxDate == null ? null : getValue()[1].getYear() + 1900);
		yearSelector.addValueChangeHandler(event -> {
			daySelector.setYear(event.getValue() - 1900);
			toggleSelector(daySelector);
		});

		toggle(monthSelector.getElement());
		toggle(yearSelector.getElement());

		add(header);
		add(daySelector);
		add(monthSelector);
		add(yearSelector);
	}

	protected native void toggle(final Element element)/*-{
		$wnd.jQuery(element).slideToggle(250, function(event) {
			if ($wnd.jQuery(element).css('display') != 'none') {
				$wnd.jQuery(element).css('display', 'flex');
			}
		});
	}-*/;

	protected void toggleSelector(final Widget selector) {

		if (selector == visibleSelector)
			return;

		toggle(visibleSelector.getElement());
		toggle(selector.getElement());

		visibleSelector = selector;
	}

	@Override
	public void setValue(Date[] value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized) {
			final Date maxDate = value != null && value.length == 2 ? value[1] : null;
			header.setValue(value, false);
			daySelector.setValue(value, false);
			monthSelector.setValue(maxDate == null ? null : maxDate.getMonth() + 1, false);
			yearSelector.setValue(maxDate == null ? null : maxDate.getYear() + 1900, false);
		}
	}

	@Override
	public void setBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_CALENDAR__FILL, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_CALENDAR__INK, color.getCssName());
	}

	public void setActiveColor(Color color) {
		setStyleProperty(CssMixin.MDC_CALENDAR__ACTIVE_INK, color.getCssName());
	}

	public void setActiveBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_CALENDAR__ACTIVE_FILL, color.getCssName());
	}

	public void setHeaderColor(Color color) {
		setStyleProperty(CssMixin.MDC_CALENDAR__HEADER_INK, color.getCssName());
	}

	public void setHeaderBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_CALENDAR__HEADER_FILL, color.getCssName());
	}
}
