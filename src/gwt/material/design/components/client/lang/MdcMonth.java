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

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import gwt.material.design.components.client.base.interfaces.HasNext;
import gwt.material.design.components.client.base.interfaces.HasPrevious;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.utils.helper.DateTimeHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MdcMonth implements HasPrevious<MdcMonth>, HasNext<MdcMonth>, Comparable<MdcMonth> {

	private MdcYear year;
	private int month;

	public MdcMonth() {
		this(new Date());
	}

	public MdcMonth(final long timestamp) {
		this(new Date(timestamp));
	}

	@SuppressWarnings("deprecation")
	public MdcMonth(final Date date) {
		this(date.getYear() + 1900, date.getMonth() + 1);
	}

	public MdcMonth(final int year, final int month) {
		super();

		this.year = new MdcYear(year);
		this.month = month;

		setYear(year);
		setMonth(this.month);
	}

	public int getYear() {
		return year.getYear();
	}

	public void setYear(final int year) {
		this.year.setYear(year);
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(final int month) {

		if (month < 1 || month > 12)
			throw new IllegalArgumentException(IMessages.INSTANCE.mdc_date__err__month_out_of_range(month));

		this.month = month;

	}

	public native int getLastDayOfMonth()/*-{
		var year = this.@gwt.material.design.components.client.lang.MdcMonth::year;
		var month = this.@gwt.material.design.components.client.lang.MdcMonth::month;
		return new Date(year, month, 0).getDate();
	}-*/;

	public MdcMonth next() {

		final boolean isLastMonthOfYear = month == 12;
		final int year = this.year.getYear();

		final int nextMonth = isLastMonthOfYear ? 1 : month + 1;
		final int nextYear = isLastMonthOfYear ? year + 1 : year;

		return new MdcMonth(nextYear, nextMonth);

	}

	public MdcMonth previous() {

		final boolean isFirstMonthOfYear = month == 1;
		final int year = this.year.getYear();

		final int previousYear = isFirstMonthOfYear ? year - 1 : year;
		final int previousMonth = isFirstMonthOfYear ? 12 : month - 1;

		return new MdcMonth(previousYear, previousMonth);
	}

	public String getMonthFullName() {
		return IMessages.INSTANCE.mdc_calendar_full_month(month);
	}

	public String getMonthShortName() {
		return IMessages.INSTANCE.mdc_calendar_short_month(month);
	}

	@Override
	public String toString() {
		return IMessages.INSTANCE.mdc_calendar_body_month(getMonthFullName(), year.getYear());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + month;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		MdcMonth other = (MdcMonth) obj;
		if (month != other.month)
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@SuppressWarnings("deprecation")
	public static Collection<MdcDate> daysOfMonth(final boolean is42Format) {
		final Date date = new Date();
		final int year = date.getYear() + 1900;
		final int month = date.getMonth() + 1;
		return daysOfMonth(year, month, is42Format);
	}

	public static Collection<MdcDate> daysOfMonth(final int year, final int month, final boolean is42Format) {

		final Collection<MdcDate> result = new LinkedList<>();
		final MdcDate firstDayOfMonth = new MdcDate(year, month, 1);

		final int countDays;
		MdcDate lastDayAdded;

		if (is42Format) {
			final int countPreviousDays = firstDayOfMonth.getDayOfWeek();
			final long previousDays = firstDayOfMonth.getTimestamp() - DateTimeHelper.daysInMillis(countPreviousDays);
			final MdcDate firstDayOfPreviousMonth = new MdcDate(previousDays);
			countDays = 42;
			lastDayAdded = firstDayOfPreviousMonth;
		} else {
			countDays = firstDayOfMonth.getLastDayOfMonth();
			lastDayAdded = firstDayOfMonth;
		}

		result.add(lastDayAdded);
		for (int i = result.size(); i < countDays; i++)
			result.add(lastDayAdded = lastDayAdded.next());

		return result;

	}

	@Override
	public int compareTo(MdcMonth month) {
		if (month == null)
			return -1;
		return Integer.compare((getYear() * 12) + getMonth(), (month.getYear() * 12) + month.getMonth());
	}

}
