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
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.MaterialIconButton;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDatePickerYearSelector extends MaterialValuedField<Integer> {

	protected final static int NUMBER_OF_YERAS = 25;

	protected Div actions = new Div(CssName.MDC_DATEPICKER__ACTIONS);
	protected MaterialIconButton previousMonth = new MaterialIconButton(IconType.CHEVRON_LEFT);
	protected MaterialIconButton nextMonth = new MaterialIconButton(IconType.CHEVRON_RIGHT);
	protected Div items = new Div(CssName.MDC_DATEPICKER__ITEMS);

	protected Map<Integer, MaterialDatePickerItem> years = new LinkedHashMap<>();
	private int minValue;
	private int maxValue;

	public MaterialDatePickerYearSelector() {
		super(CssName.MDC_DATEPICKER__YEAR_SELECTOR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		previousMonth.addStyleName(CssName.MDC_DATEPICKER__ACTION);
		previousMonth.addClickHandler(
				event -> drawYears(minValue - NUMBER_OF_YERAS, maxValue - NUMBER_OF_YERAS));

		nextMonth.addStyleName(CssName.MDC_DATEPICKER__ACTION);
		nextMonth.addClickHandler(event -> drawYears(minValue + NUMBER_OF_YERAS, maxValue + NUMBER_OF_YERAS));

		actions.add(previousMonth);
		actions.add(nextMonth);

		add(actions);
		add(items);

		drawYears();
	}

	@SuppressWarnings("deprecation")
	protected void drawYears() {
		final Date date = new Date();
		final Integer value = getValue();
		final int auxYear = value == null ? date.getYear() + 1900 : value;
		drawYears(auxYear - (NUMBER_OF_YERAS / 2), auxYear + (NUMBER_OF_YERAS / 2));
	}

	protected void drawYears(final int minValue, final int maxValue) {

		this.minValue = minValue;
		this.maxValue = maxValue;

		items.clear();
		years.clear();

		final String name = getId();
		final Integer value = getValue();

		for (int y = minValue; y <= maxValue; y++) {

			final Integer year = y;
			final MaterialDatePickerItem yearLabel = new MaterialDatePickerItem();
			yearLabel.setText(String.valueOf(year));
			yearLabel.setName(name);
			yearLabel.addClickHandler(event -> super.setValue(year, true));

			if (value != null && y == value)
				yearLabel.setSelected(true, true);

			items.add(yearLabel);
			years.put(year, yearLabel);

		}

	}

	@Override
	public void setValue(Integer value, boolean fireEvents) {
		final Integer oldValue = getValue();
		super.setValue(value, fireEvents);
		if (initialized) {
			if (value == null && oldValue != null)
				years.get(oldValue).setSelected(false, false);
			else if (value != null && years.containsKey(value))
				years.get(value).setSelected(true, false);
			else
				drawYears();
		}
	}

	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_DATEPICKER__FILL, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setCssProperty(CssMixin.MDC_DATEPICKER__INK, color.getCssName());
	}

	public void setActiveColor(Color color) {
		setCssProperty(CssMixin.MDC_DATEPICKER__ACTIVE_INK, color.getCssName());
	}

	public void setActiveBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_DATEPICKER__ACTIVE_FILL, color.getCssName());
	}

}
