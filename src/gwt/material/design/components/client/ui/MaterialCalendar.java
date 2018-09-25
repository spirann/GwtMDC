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
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.calendar.MaterialCalendarDaySelector;
import gwt.material.design.components.client.ui.calendar.MaterialCalendarHeader;
import gwt.material.design.components.client.ui.calendar.MaterialCalendarMonthSelector;
import gwt.material.design.components.client.ui.form.MaterialValuedField;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCalendar extends MaterialValuedField<Date> {

	protected MaterialCalendarHeader header = new MaterialCalendarHeader();
	protected MaterialCalendarDaySelector daySelector = new MaterialCalendarDaySelector();
	protected MaterialCalendarMonthSelector monthSelector = new MaterialCalendarMonthSelector();

	public MaterialCalendar() {
		super(CssName.MDC_CALENDAR);
		setValue(new Date(), false);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@SuppressWarnings("deprecation")
	@Override
	protected void onInitialize() {
		super.onInitialize();

		header.setValue(getValue());

		daySelector.addValueChangeHandler(event -> setValue(event.getValue(), true));
		daySelector.addClickMonthHandler(event -> showMonthSelector());
		daySelector.setValue(getValue());
		monthSelector.setValue(getValue().getMonth() + 1);
		monthSelector.addValueChangeHandler(event -> showDaySelector());

		add(header);
		add(daySelector);
		add(monthSelector);
	}

	protected void showDaySelector() {
		/*
		monthSelector.setMaxHeight("0");
		TimerHelper.schedule(250, () -> {
			monthSelector.setVisibility(Visibility.HIDDEN);
			daySelector.setVisibility(Visibility.VISIBLE);
			daySelector.setMaxHeight("999px");
		});*/
		hideContent(monthSelector);
		showContent(daySelector);
	}

	protected void showMonthSelector() {
		
		hideContent(daySelector);
		showContent(monthSelector);
		
		/*
		daySelector.setMaxHeight("0");
		TimerHelper.schedule(250, () -> {
			daySelector.setVisibility(Visibility.HIDDEN);
			monthSelector.setVisibility(Visibility.VISIBLE);
			monthSelector.setMaxHeight("999px");
		});*/
	}

	private void showContent(Widget widget) {
		widget.removeStyleName("mdc-calendar__show_content");
		widget.removeStyleName("mdc-calendar__hidden_content");
		widget.addStyleName("mdc-calendar__show_content");
	}
	
	private void hideContent(Widget widget) {
		widget.removeStyleName("mdc-calendar__show_content");
		widget.removeStyleName("mdc-calendar__hidden_content");
		widget.addStyleName("mdc-calendar__hidden_content");
	}
	
	@Override
	public void setValue(Date value, boolean fireEvents) {
		super.setValue(value, fireEvents);
		if (initialized) {

			header.setValue(value, false);

			if (value.getTime() != daySelector.getValue().getTime())
				daySelector.setValue(value, false);
		}
	}
}
