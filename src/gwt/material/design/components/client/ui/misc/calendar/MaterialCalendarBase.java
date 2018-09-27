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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public abstract class MaterialCalendarBase<T, H extends MaterialValuedField<T>, S extends MaterialCalendarBaseDaySelector<T>>
		extends MaterialValuedField<T> {

	protected H header;
	protected S daySelector;
	protected MaterialCalendarMonthSelector monthSelector = new MaterialCalendarMonthSelector();
	protected MaterialCalendarYearSelector yearSelector = new MaterialCalendarYearSelector();

	protected Widget visibleSelector = daySelector;

	public MaterialCalendarBase() {
		super(CssName.MDC_CALENDAR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final Date date;
		final T value = getValue();
		if (value == null) {
			date = null;
		} else if (value instanceof Date) {
			date = (Date) value;
		} else if (value instanceof Date[]) {
			final Date[] values = (Date[]) value;
			date = values.length == 0 ? null : values[values.length - 1];
		} else {
			date = null;
		}

		header.setValue(getValue());
		
		daySelector.addValueChangeHandler(event -> setValue(event.getValue(), true));
		daySelector.addMonthClickHandler(event -> toggleSelector(monthSelector));
		daySelector.setValue(getValue());

		monthSelector.setValue(date == null ? null : date.getMonth() + 1);
		monthSelector.addValueChangeHandler(event -> {
			daySelector.setMonth(event.getValue() - 1);
			toggleSelector(daySelector);
		});

		yearSelector.setValue(date == null ? null : date.getYear() + 1900);
		yearSelector.addValueChangeHandler(event -> {
			daySelector.setYear(event.getValue() - 1900);
			toggleSelector(daySelector);
		});

		visibleSelector = daySelector;
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
