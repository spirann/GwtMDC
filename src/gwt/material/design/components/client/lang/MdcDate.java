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
package gwt.material.design.components.client.lang;

import java.util.Date;

import gwt.material.design.components.client.resources.message.IMessages;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MdcDate {

	private MdcMonth month;
	private int day;

	public MdcDate() {
		this(new Date());
	}

	public MdcDate(final long timestamp) {
		this(new Date(timestamp));
	}

	@SuppressWarnings("deprecation")
	public MdcDate(final Date date) {
		this(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
	}

	public MdcDate(final int year, final int month, final int day) {
		super();

		this.month = new MdcMonth(year, month);
		this.day = day;

		setYear(year);
		setMonth(month);
		setDay(this.day);
	}

	public int getYear() {
		return month.getYear();
	}

	public void setYear(final int year) {

		if (year < 0)
			throw new IllegalArgumentException(IMessages.INSTANCE.mdc_date__err__year_out_of_range(year));

		month.setYear(year);
		
		setMonth(month.getMonth());

	}

	public int getMonth() {
		return month.getMonth();
	}

	public void setMonth(final int month) {

		if (month < 1 || month > 12)
			throw new IllegalArgumentException(IMessages.INSTANCE.mdc_date__err__month_out_of_range(month));

		this.month.setMonth(month);		

		final int lastDayOfMonth = getLastDayOfMonth();

		if (lastDayOfMonth < day)
			setDay(lastDayOfMonth);

	}

	public int getDay() {
		return day;
	}

	public void setDay(final int day) {

		if (day < 1)
			throw new IllegalArgumentException(
					IMessages.INSTANCE.mdc_date__err__day_smaller_than_first_day_of_month(day));

		final int lastDayOfMonth = getLastDayOfMonth();

		if (day > lastDayOfMonth)
			throw new IllegalArgumentException(IMessages.INSTANCE.mdc_date__err__day_bigger_than_last_day_of_month(month.getYear(),
					month.getYear(), lastDayOfMonth, day));

		this.day = day;
	}

	public int getLastDayOfMonth() {
		return month.getLastDayOfMonth();
	}

	public native int getDayOfWeek()/*-{
		var mdcMonth = this.@gwt.material.design.components.client.lang.MdcDate::month;
		var year = mdcMonth.@gwt.material.design.components.client.lang.MdcMonth::getYear()();
		var month = mdcMonth.@gwt.material.design.components.client.lang.MdcMonth::getMonth()();
		var day = this.@gwt.material.design.components.client.lang.MdcDate::day;
		return new Date(year, month - 1, day).getDay();
	}-*/;

	public native int getDayOfYear()/*-{
		var mdcMonth = this.@gwt.material.design.components.client.lang.MdcDate::month;
		var year = mdcMonth.@gwt.material.design.components.client.lang.MdcMonth::getYear()();
		var month = mdcMonth.@gwt.material.design.components.client.lang.MdcMonth::getMonth()();
		var day = this.@gwt.material.design.components.client.lang.MdcDate::day;

		return --month >= 0
				&& month < 12
				&& day > 0
				&& day < 29 + (4 * (year = year & 3 || !(year % 25) && year
						& 15 ? 0 : 1) + 15662003 >> month * 2 & 3) && month
				* 31
				- (month > 1 ? (1054267675 >> month * 3 - 6 & 7) - year : 0)
				+ day;

	}-*/;

	public native int getTimestamp()/*-{
		var mdcMonth = this.@gwt.material.design.components.client.lang.MdcDate::month;
		var year = mdcMonth.@gwt.material.design.components.client.lang.MdcMonth::getYear()();
		var month = mdcMonth.@gwt.material.design.components.client.lang.MdcMonth::getMonth()();
		var day = this.@gwt.material.design.components.client.lang.MdcDate::day;
		return new Date(year, month - 1, day).getTime();
	}-*/;

	public MdcDate next() {

		final int month = this.month.getMonth();
		final int year = this.month.getYear();
		
		final boolean isLastDayOfMonth = day == getLastDayOfMonth();
		final boolean isLastMonthOfYear = month == 12;

		final int nextDay = isLastDayOfMonth ? 1 : day + 1;
		final int nextMonth = isLastDayOfMonth ? isLastMonthOfYear ? 1 : month + 1 : month;
		final int nextYear = isLastDayOfMonth && isLastMonthOfYear ? year + 1 : year;

		return new MdcDate(nextYear, nextMonth, nextDay);

	}

	public MdcDate previous() {

		final int month = this.month.getMonth();
		final int year = this.month.getYear();
		
		final boolean isFirstDayOfMonth = day == 1;
		final boolean isFirstMonthOfYear = month == 1;

		final int previousYear = isFirstDayOfMonth && isFirstMonthOfYear ? year - 1 : year;
		final int previousMonth = isFirstDayOfMonth ? isFirstMonthOfYear ? 12 : month - 1 : month;
		final int previousDay = isFirstDayOfMonth ? 1 /* First day because I will calculate it before */ : day - 1;

		final MdcDate mdcDate = new MdcDate(previousYear, previousMonth, previousDay);
		/* Update day if It is the first day of month */
		if (isFirstDayOfMonth)
			mdcDate.setDay(mdcDate.getLastDayOfMonth());

		return mdcDate;

	}

	public Date getDate() {
		return new Date(getTimestamp());
	}

	public String getMonthFullName() {
		return month.getMonthFullName();
	}

	public String getMonthShortName() {
		return month.getMonthShortName();
	}

	public String getDayOfWeekFullName() {
		return IMessages.INSTANCE.mdc_calendar_full_week(getDayOfWeek() + 1);
	}

	public String getDayOfWeekShortName() {
		return IMessages.INSTANCE.mdc_calendar_short_week(getDayOfWeek() + 1);
	}

	public String getDayOfWeeLetter() {
		return IMessages.INSTANCE.mdc_calendar_letter_week(getDayOfWeek() + 1);
	}

	@Override
	public String toString() {
		return IMessages.INSTANCE.mdc_date__to_string(getDayOfWeekFullName(), day, getMonthFullName(), month.getYear());
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MdcDate other = (MdcDate) obj;
		if (day != other.day)
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		return true;
	}

}
