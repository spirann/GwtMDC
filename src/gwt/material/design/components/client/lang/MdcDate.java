package gwt.material.design.components.client.lang;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.utils.helper.DateTimeHelper;

public class MdcDate {

	private int year;
	private int month;
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

		this.year = year;
		this.month = month;
		this.day = day;

		setYear(this.year);
		setMonth(this.month);
		setDay(this.day);
	}

	public int getYear() {
		return year;
	}

	public void setYear(final int year) {

		if (year < 0)
			throw new IllegalArgumentException(IMessages.INSTANCE.mdc_date__err__year_out_of_range(year));

		this.year = year;

		setMonth(month);

	}

	public int getMonth() {
		return month;
	}

	public void setMonth(final int month) {

		if (month < 1 || month > 12)
			throw new IllegalArgumentException(IMessages.INSTANCE.mdc_date__err__month_out_of_range(month));

		this.month = month;

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
			throw new IllegalArgumentException(IMessages.INSTANCE.mdc_date__err__day_bigger_than_last_day_of_month(year,
					month, lastDayOfMonth, day));

		this.day = day;
	}

	public native int getLastDayOfMonth()/*-{
		var year = this.@gwt.material.design.components.client.lang.MdcDate::year;
		var month = this.@gwt.material.design.components.client.lang.MdcDate::month;
		return new Date(year, month, 0).getDate();
	}-*/;

	public native int getDayOfWeek()/*-{
		var year = this.@gwt.material.design.components.client.lang.MdcDate::year;
		var month = this.@gwt.material.design.components.client.lang.MdcDate::month;
		var day = this.@gwt.material.design.components.client.lang.MdcDate::day;
		return new Date(year, month - 1, day).getDay();
	}-*/;

	public native int getDayOfYear()/*-{
		var year = this.@gwt.material.design.components.client.lang.MdcDate::year;
		var month = this.@gwt.material.design.components.client.lang.MdcDate::month;
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
		var year = this.@gwt.material.design.components.client.lang.MdcDate::year;
		var month = this.@gwt.material.design.components.client.lang.MdcDate::month;
		var day = this.@gwt.material.design.components.client.lang.MdcDate::day;
		return new Date(year, month - 1, day).getTime();
	}-*/;

	public MdcDate next() {

		final boolean isLastDayOfMonth = day == getLastDayOfMonth();
		final boolean isLastMonthOfYear = month == 12;

		final int nextDay = isLastDayOfMonth ? 1 : day + 1;
		final int nextMonth = isLastDayOfMonth ? isLastMonthOfYear ? 1 : month + 1 : month;
		final int nextYear = isLastDayOfMonth && isLastMonthOfYear ? year + 1 : year;

		return new MdcDate(nextYear, nextMonth, nextDay);

	}

	public MdcDate previous() {

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
		return IMessages.INSTANCE.mdc_calendar_full_month(month);
	}

	public String getMonthShortName() {
		return IMessages.INSTANCE.mdc_calendar_short_month(month);
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
		return IMessages.INSTANCE.mdc_date__to_string(getDayOfWeekFullName(), day, getMonthFullName(), year);
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
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
		if (month != other.month)
			return false;
		if (year != other.year)
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
}
