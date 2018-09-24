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
import com.google.gwt.dom.client.Style.Visibility;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialIconButton;
import gwt.material.design.components.client.ui.form.MaterialValuedField;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.utils.helper.DateTimeHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDaySelector extends MaterialValuedField<Date> {

	protected Div bodyMonth = new Div(CssName.MDC_CALENDAR__DAY_SELECTOR__MONTH__CONTENT);
	protected MaterialIconButton previousMonth = new MaterialIconButton(IconType.CHEVRON_LEFT);
	protected MaterialIconButton nextMonth = new MaterialIconButton(IconType.CHEVRON_RIGHT);
	protected Label bodyMonthLabel = new Label(CssName.MDC_CALENDAR__DAY_SELECTOR__MONTH__LABEL);
	
	protected Div bodyWeek = new Div(CssName.MDC_CALENDAR__DAY_SELECTOR__WEEK__CONTENT);
	protected Div bodyDays = new Div(CssName.MDC_CALENDAR__DAY_SELECTOR__DAYS__CONTENT);
	

	private Date tempDate = adjustDate(new Date());
	
	public MaterialDaySelector() {
		super(CssName.MDC_CALENDAR__DAY_SELECTOR);
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;
	
	@Override
	protected void onInitialize() {	
		super.onInitialize();
		
		previousMonth.addClickHandler(event -> decreaseMonth());
		nextMonth.addClickHandler(event -> increaseMonth());
		
		bodyMonth.add(previousMonth);
		bodyMonth.add(bodyMonthLabel);
		bodyMonth.add(nextMonth);
		
		bodyMonthLabel.addClickHandler(event -> {
			
		});
		
		for(int i = 1; i < 8; i++) {
			final Label label = new Label(CssName.MDC_CALENDAR__DAY_SELECTOR__WEEK__LABEL, CssName.MDC_TYPOGRAPHY__CAPTION);
			label.setText(IMessages.INSTANCE.mdc_calendar_letter_week(i));
			bodyWeek.add(label);
		}
		
		add(bodyMonth);
		add(bodyWeek);
		add(bodyDays);
	}
	
	protected void increaseMonth() {
 		setMonth(tempDate.getMonth() + 1);
 		drawDays();
	}
	
	protected void decreaseMonth() {
		setMonth(tempDate.getMonth() - 1);
		drawDays();
	}
	
	protected void setMonth(final int month) {
		
		final int newMonth;
		final int newYear;
		
		if(month < 0) {
			newMonth = 11;
			newYear = tempDate.getYear() - 1;
		} else if(month > 11) {
			newMonth = 0;
			newYear = tempDate.getYear() + 1;
		} else {
			newMonth = month;
			newYear = tempDate.getYear();
		}
		
		tempDate.setDate(1);
		tempDate.setMonth(newMonth);
		tempDate.setYear(newYear);
		
		drawDays();
	}
	
	protected void drawDays() {
		
		bodyDays.clear();
		
		final int firstDay = 1;
		final int lastDay = DateTimeHelper.lastDayOfMonth(this.tempDate);
		
		final Date date = adjustDate(this.tempDate);		
		final String name = getId();
		
		for(int i = firstDay, w = 0; i <= lastDay; i++, w++) {
			date.setDate(i);

			if(w == 7)
				w = 0; 
			
			final Date d = adjustDate(date);
			final MaterialCalendarItem dayButton = new MaterialCalendarItem();
			dayButton.setText(String.valueOf(i));
			dayButton.setName(name);
			dayButton.addSelectionHandler(event -> {
				tempDate = date;
				setValue(date);
				drawDays();
			});
			
			if(getValue() != null && d.getTime() == getValue().getTime()) 
				dayButton.setSelected(true, true);
			
			if(w != date.getDay()) {
				i--;
				dayButton.setText("");
				dayButton.setVisibility(Visibility.HIDDEN);
			}
			
			bodyDays.add(dayButton);
		}
		
	}
	
	@Override
	public void setValue(Date value) {
		super.setValue(value);
	}
	
	protected Date adjustDate(final Date date) {
		return new Date(DateTimeHelper.fromTheDate(date.getTime()));
	}
}
