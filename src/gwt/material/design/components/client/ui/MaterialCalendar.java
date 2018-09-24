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

import com.google.gwt.i18n.client.LocaleInfo;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.calendar.MaterialDaySelector;
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

	protected MaterialDaySelector daySelector = new MaterialDaySelector();

	protected Div bodyMonths = new Div(CssName.MDC_CALENDAR__BODY__MONTHS__CONTENT);

	private String locale = LocaleInfo.getCurrentLocale().getLocaleName();

	private MaterialIconButton selectedElement;
	private Date tempDate = adjustDate(new Date());

	public MaterialCalendar() {
		super(CssName.MDC_CALENDAR);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		header.add(headerYear);
		header.add(headerDate);
		
		daySelector.addValueChangeHandler(event -> setValues());
		daySelector.setValue(new Date());

		for (int i = 1; i < 13; i++) {
			final int month = i - 1;
			final Label chip = new Label(CssName.MDC_CALENDAR__BODY__MONTHS__LABEL);
			chip.setText(IMessages.INSTANCE.mdc_calendar_short_month(i));
			chip.addClickHandler(event -> {

				setMonth(month);
				bodyMonths.setHeight("0");
				daySelector.setHeight("auto");

			});
			bodyMonths.add(chip);
		}
		

		add(header);
		add(daySelector);
		add(bodyMonths);
	}

	protected Date adjustDate(final Date date) {
		return new Date(DateTimeHelper.fromTheDate(date.getTime()));
	}

	protected void setValues() {
		tempDate = daySelector.getValue();		
		drawHeader();
	}

	protected void drawHeader() {
		final int year = daySelector.getValue().getYear() + 1900;
		final int month = daySelector.getValue().getMonth() + 1;
		final String week = IMessages.INSTANCE.mdc_calendar_short_week(daySelector.getValue().getDay() + 1);
		final String shortMonth = IMessages.INSTANCE.mdc_calendar_short_month(month);
		final int day = daySelector.getValue().getDate();
		headerYear.setText(String.valueOf(year));
		headerDate.setText(IMessages.INSTANCE.mdc_calendar_header_day(week, shortMonth, day));
	}

	protected void select(final Date date, final MaterialIconButton element) {

		if (selectedElement != null) {
			selectedElement.removeStyleName(CssName.MDC_CALENDAR__DAY_SELECTOR__DAYS__LABEL_ACTIVE);
		}

		selectedElement = element;
		selectedElement.addStyleName(CssName.MDC_CALENDAR__DAY_SELECTOR__DAYS__LABEL_ACTIVE);

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

		if (month < 0) {
			newMonth = 11;
			newYear = tempDate.getYear() - 1;
		} else if (month > 11) {
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
		return daySelector.getValue();
	}

	public void setDate(Date date) {
		daySelector.setValue(date);
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
