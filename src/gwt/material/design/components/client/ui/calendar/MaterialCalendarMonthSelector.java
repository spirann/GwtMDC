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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.form.MaterialValuedField;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCalendarMonthSelector extends MaterialValuedField<Integer> {

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
		drawMonths();
	}

	protected void drawMonths() {

		clear();

		final String name = getId();
		final Integer value = getValue();

		for (int m = 1; m <= 12; m++) {

			final Integer month = m;
			final MaterialCalendarItem monthLabel = new MaterialCalendarItem();
			monthLabel.setText(IMessages.INSTANCE.mdc_calendar_short_month(month));
			monthLabel.setName(name);
			monthLabel.addClickHandler(event -> super.setValue(month, true));

			if (value != null && m == value)
				monthLabel.setSelected(true, true);

			add(monthLabel);
		}

	}

	@Override
	public void setValue(Integer value, boolean fireEvents) {
		final Integer oldValue = getValue();
		super.setValue(value, fireEvents);
		if (initialized && getWidgetCount() > 0) {
			if (value == null && oldValue != null)
				((MaterialCalendarItem) getWidget(oldValue - 1)).setSelected(false, false);
			else if (value != null)
				((MaterialCalendarItem) getWidget(value - 1)).setSelected(true, false);

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

}
