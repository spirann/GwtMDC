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

import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.i18n.client.LocaleInfo;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.utils.helper.DateTimeHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialCalendar extends Div {

	protected Div header = new Div(CssName.MDC_CALENDAR__HEADER__CONTENT);
	protected Label headerYear = new Label(CssName.MDC_CALENDAR__HEADER__YEAR);
	protected Label headerDate = new Label(CssName.MDC_CALENDAR__HEADER__DATE, CssName.MDC_TYPOGRAPHY__HEADLINE_5);
	
	protected Div body = new Div(CssName.MDC_CALENDAR__BODY__CONTENT);
	protected Div bodyMonth = new Div(CssName.MDC_CALENDAR__BODY__MONTH__CONTENT);
	protected MaterialIconButton previousMonth = new MaterialIconButton(IconType.CHEVRON_LEFT);
	protected MaterialIconButton nextMonth = new MaterialIconButton(IconType.CHEVRON_RIGHT);
	protected Label bodyMonthLabel = new Label(CssName.MDC_CALENDAR__BODY__MONTH__LABEL);
	
	protected Div bodyWeek = new Div(CssName.MDC_CALENDAR__BODY__WEEK__CONTENT);
	protected Div bodyDays = new Div(CssName.MDC_CALENDAR__BODY__DAYS__CONTENT);
	protected Div bodyMonths = new Div(CssName.MDC_CALENDAR__BODY__MONTHS__CONTENT);

	private String locale = LocaleInfo.getCurrentLocale().getLocaleName();

	private MaterialIconButton selectedElement;
	private Date selectedDate = adjustDate(new Date());
	private Date tempDate = adjustDate(new Date());
	
	public MaterialCalendar() {
		super(CssName.MDC_CALENDAR);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		setValues();
				
		header.add(headerYear);
		header.add(headerDate);
		
		previousMonth.addClickHandler(event -> decreaseMonth());
		nextMonth.addClickHandler(event -> increaseMonth());
		
		bodyMonth.add(previousMonth);
		bodyMonth.add(bodyMonthLabel);
		bodyMonth.add(nextMonth);
		
		bodyMonthLabel.addClickHandler(event -> {
			body.setHeight("0");
			bodyMonths.setHeight("auto");
		});
		
		for(int i = 1; i < 8; i++) {
			final Label label = new Label(CssName.MDC_CALENDAR__BODY__WEEK__LABEL, CssName.MDC_TYPOGRAPHY__CAPTION);
			label.setText(IMessages.INSTANCE.mdc_calendar_letter_week(i));
			bodyWeek.add(label);
		}
		
		for(int i = 1; i < 13; i++) {
			final int month = i - 1;
			final Label chip = new Label(CssName.MDC_CALENDAR__BODY__MONTHS__LABEL);
			chip.setText(IMessages.INSTANCE.mdc_calendar_short_month(i));
			//chip.setSelected(i == (selectedDate.getMonth() + 1));
			//chip.setType(ChipType.OUTLINE);
			//chip.setShowCheckmark(false);
			//chip.setSelectColor(Color.MDC_THEME_PRIMARY);
			chip.addClickHandler(event -> {
				
					setMonth(month);
					bodyMonths.setHeight("0");
					body.setHeight("auto");
				
				
			});
			bodyMonths.add(chip);
		}
		
		body.add(bodyMonth);
		body.add(bodyWeek);
		body.add(bodyDays);
		
		
		add(header);
		add(body);
		add(bodyMonths);
	}
	
	protected Date adjustDate(final Date date) {
		return new Date(DateTimeHelper.fromTheDate(date.getTime()));
	}

	protected void setValues() {
		
		drawHeader();
		
		final int year = tempDate.getYear() + 1900;
		final int month = tempDate.getMonth() + 1;
		final String fullMonth = IMessages.INSTANCE.mdc_calendar_full_month(month);
		bodyMonthLabel.setText(IMessages.INSTANCE.mdc_calendar_body_month(fullMonth, year));
		
		drawDays();
	}
	
	protected void drawHeader() {
		final int year = selectedDate.getYear() + 1900;
		final int month = selectedDate.getMonth() + 1;
		final String week = IMessages.INSTANCE.mdc_calendar_short_week(selectedDate.getDay() + 1);
		final String shortMonth = IMessages.INSTANCE.mdc_calendar_short_month(month);
		final int day = selectedDate.getDate();
		headerYear.setText(String.valueOf(year));
		headerDate.setText(IMessages.INSTANCE.mdc_calendar_header_day(week, shortMonth, day));
	}
	
	protected void drawDays() {
		
		bodyDays.clear();
		
		final int firstDay = 1;
		final int lastDay = DateTimeHelper.lastDayOfMonth(this.tempDate);
		
		final Date date = adjustDate(this.tempDate);		
		
		for(int i = firstDay, w = 0; i <= lastDay; i++, w++) {
			date.setDate(i);

			if(w == 7)
				w = 0; 
			
			final Date d = adjustDate(date);
			final MaterialIconButton dayButton = new MaterialIconButton();
			dayButton.addStyleName(CssName.MDC_CALENDAR__BODY__DAYS__LABEL);
			dayButton.addStyleName(CssName.MDC_TYPOGRAPHY__CAPTION);
			dayButton.addClickHandler(event -> select(d, dayButton));
			
			if(selectedDate != null && d.getTime() == selectedDate.getTime()) 
				select(d, dayButton);
			
			if(w == date.getDay()) {
				dayButton.getElement().setInnerText(String.valueOf(i));				
			} else {
				i--;
				dayButton.getElement().setInnerText("");
				dayButton.setVisibility(Visibility.HIDDEN);
			}
			
			bodyDays.add(dayButton);
		}
		
	}
	
	protected void select(final Date date, final MaterialIconButton element) {
		
		if(selectedElement != null) {
			selectedElement.removeStyleName(CssName.MDC_CALENDAR__BODY__DAYS__LABEL_ACTIVE);
		}
		
		selectedElement = element;
		selectedElement.addStyleName(CssName.MDC_CALENDAR__BODY__DAYS__LABEL_ACTIVE);
		
		selectedDate = date;
		
		drawHeader();
	}

 	protected void increaseMonth() {
 		setMonth(tempDate.getMonth() + 1);
		setValues();
	}
	
	protected void decreaseMonth() {
		setMonth(tempDate.getMonth() - 1);
		setValues();
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
		
		setValues();
	}
	
	public Date getDate() {
		return selectedDate;
	}

	public void setDate(Date date) {
		this.selectedDate = date;
		setValues();
	}

	public String getLocale() {
		return locale;
	}

	/**
	 * Default get by: <br/>
	 * 
	 * LocaleInfo.getCurrentLocale().getLocaleName() <br/>
	 * 
	 * Supported locale <br/>
	 * 
	 * ar<br/>
	 * bg_BG<br/>
	 * bs_BA<br/>
	 * ca_ES<br/>
	 * cs_CZ<br/>
	 * da_DK<br/>
	 * de_DE<br/>
	 * el_GR<br/>
	 * en<br/>
	 * es_ES<br/>
	 * et_EE<br/>
	 * eu_ES<br/>
	 * fa_ir<br/>
	 * fi_FI<br/>
	 * fr_FR<br/>
	 * gl_ES<br/>
	 * he_IL<br/>
	 * hi_IN<br/>
	 * hr_HR<br/>
	 * hu_HU<br/>
	 * id_ID<br/>
	 * is_IS<br/>
	 * it_IT<br/>
	 * ja_JP<br/>
	 * ko_KR<br/>
	 * lt_LT<br/>
	 * lv_LV<br/>
	 * nb_NO<br/>
	 * ne_NP<br/>
	 * nl_NL<br/>
	 * pl_PL<br/>
	 * pt_BR<br/>
	 * pt_PT<br/>
	 * ro_RO<br/>
	 * ru_RU<br/>
	 * sk_SK<br/>
	 * sl_SI<br/>
	 * sv_SE<br/>
	 * th_TH<br/>
	 * tr_TR<br/>
	 * uk_UA<br/>
	 * vi_VN<br/>
	 * zh_CN<br/>
	 * zh_TW<br/>
	 * 
	 * @param locale
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
}
