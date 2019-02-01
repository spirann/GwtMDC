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
package gwt.material.design.components.client.utils.helper;

import java.util.Date;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class DateTimeHelper {

	private static final int[] KNOW_LAST_DAYS = { 31, 30, 29, 28 };

	/**
	 * Calculate the number of days between two dates
	 * 
	 * @param d1
	 *            Start date
	 * @param d2
	 *            End date
	 * @return Number of days between d1 and d2
	 */
	public static int daysBetween(Date d1, Date d2) {
		return countDays(removeTimeIgnoringGMT(d2) - removeTimeIgnoringGMT(d1));
	}

	/**
	 * Calculate the number of hours between two dates
	 * 
	 * @param d1
	 *            Start date
	 * @param d2
	 *            End date
	 * @return Number of hours between d1 and d2
	 */
	public static int hoursBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / hoursInMillis(1));
	}

	/**
	 * Calculate the number of minutes between two dates
	 * 
	 * @param d1
	 *            Start date
	 * @param d2
	 *            End date
	 * @return Number of minutes between d1 and d2
	 */
	public static int minutesBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / minutesInMillis(1));
	}

	/**
	 * Calculate the number of seconds between two dates
	 * 
	 * @param d1
	 *            Start date
	 * @param d2
	 *            End date
	 * @return Number of seconds between d1 and d2
	 */
	public static int secondsBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / secondsInMillis(1));
	}

	/**
	 * Remove the hours, minutes and seconds without GMT correction
	 * 
	 * @param date
	 * @return Date in milliseconds without hours, minutes and seconds
	 */
	public static long removeTimeIgnoringGMT(final Date date) {
		final long time = date.getTime();
		final long fromTheDate = time - (time % daysInMillis(1));
		return fromTheDate;
	}

	/**
	 * Remove the hours, minutes and seconds with GMT correction
	 * 
	 * @param date
	 * @return Date in milliseconds without hours, minutes and seconds
	 */
	public static long removeTime(final Date date) {
		final long fromTheDate = removeTimeIgnoringGMT(date);
		final int timezone = date.getTimezoneOffset() == 0 ? 0 : date.getTimezoneOffset() / 60;
		return fromTheDate + hoursInMillis(timezone);
	}

	/**
	 * Remove the hours, minutes and seconds with GMT correction
	 * 
	 * @param time
	 * @return Date in milliseconds without hours, minutes and seconds
	 */
	public static long removeTime(final long time) {
		final Date date = new Date();
		date.setTime(time);
		return removeTime(date);
	}

	/**
	 * Calculate the number of days
	 * 
	 * @param time
	 *            in milliseconds
	 * @return Number of days
	 */
	public static int countDays(long time) {
		return (int) (time / daysInMillis(1));
	}

	/**
	 * Calculate the number of hours
	 * 
	 * @param time
	 *            in milliseconds
	 * @return Number of hours
	 */
	public static int countHours(long time) {
		return (int) (time / hoursInMillis(1));
	}

	/**
	 * Calculate the number of minutes
	 * 
	 * @param time
	 *            in milliseconds
	 * @return Number of minutes
	 */
	public static int countMinutes(long time) {
		return (int) (time / minutesInMillis(1));
	}

	/**
	 * Calculate the number of seconds
	 * 
	 * @param time
	 *            in milliseconds
	 * @return Number of seconds
	 */
	public static int countSeconds(long time) {
		return (int) (time / secondsInMillis(1));
	}

	/**
	 * Return last day of the date month
	 * 
	 * @param date
	 * @return The day of the month
	 */
	public static int lastDayOfMonth(final Date date) {

		final long time = date.getTime();

		for (int lastDay : KNOW_LAST_DAYS) {
			final Date calc = new Date(time);
			calc.setDate(lastDay);
			final int calculatedLastDay = calc.getDate();
			if (calculatedLastDay == lastDay)
				return calculatedLastDay;
		}

		return -1;
	}

	/**
	 * Compare two dates, it ignore hours, minutes and seconds
	 * 
	 * @param d1
	 *            First date
	 * @param d2
	 *            Second date
	 * @return If d1 < d2 returns is -1, else if d1 > d2 returns 1, else return 0;
	 */
	public static int compareToDate(final Date d1, final Date d2) {
		final long fromTheDate1 = removeTime(d1);
		final long fromTheDate2 = removeTime(d2);

		if (fromTheDate1 < fromTheDate2)
			return -1;
		else if (fromTheDate1 > fromTheDate2)
			return 1;
		else
			return 0;
	}

	/**
	 * Compare two dates, it ignore hours, minutes and seconds
	 * 
	 * @param d1
	 *            First date
	 * @param d2
	 *            Second date
	 * @return If d1 < d2 returns is -1, else if d1 > d2 returns 1, else return 0;
	 */
	public static int compareToDate(final long t1, final long t2) {
		return compareToDate(new Date(t1), new Date(t2));
	}

	/**
	 * Transform the number of day in milliseconds
	 * 
	 * @param days
	 * @return Number of days in milliseconds
	 */
	public static long daysInMillis(final int days) {
		return days * 24L * 60L * 60L * 1000L;
	}

	/**
	 * Transform the number of hours in milliseconds
	 * 
	 * @param hours
	 * @return Number of hours in milliseconds
	 */
	public static long hoursInMillis(final int hours) {
		return hours * 60L * 60L * 1000L;
	}

	/**
	 * Transform the number of minutes in milliseconds
	 * 
	 * @param minutes
	 * @return Number of minutes in milliseconds
	 */
	public static long minutesInMillis(final int minutes) {
		return minutes * 60L * 1000L;
	}

	/**
	 * Retorna o número de segundos informado em milisegundos
	 * 
	 * @param seconds
	 * @return
	 */
	public static long secondsInMillis(final int seconds) {
		return seconds * 1000L;
	}

	/**
	 * Verify if the date is Easter
	 *
	 * @param date
	 * @return True if the date is Easter, else if not false
	 */
	public static boolean isEaster(final Date date) {

		if (date == null)
			return false;

		final Date easterDate = getEaster(date.getYear() + 1900);

		return easterDate.getYear() == date.getYear() && easterDate.getMonth() == date.getMonth() && easterDate.getDate() == date.getDate();
	}

	/**
	 * Verify if the date is Holy Friday
	 *
	 * @param date
	 * @return True if the date is Holy Friday, else if not false
	 */
	public static boolean isHolyFriday(final Date date) {

		if (date == null)
			return false;

		final Date holyFridayDate = getHolyFriday(date.getYear() + 1900);

		return holyFridayDate.getYear() == date.getYear() && holyFridayDate.getMonth() == date.getMonth() && holyFridayDate.getDate() == date.getDate();
	}

	/**
	 * Verify if the date is Corpus Christi
	 *
	 * @param date
	 * @return True if the date is Corpus Christi, else if not false
	 */
	public static boolean isCorpusChristi(final Date date) {

		if (date == null)
			return false;

		final Date corpusChristiDate = getCorpusChristi(date.getYear() + 1900);

		return corpusChristiDate.getYear() == date.getYear() && corpusChristiDate.getMonth() == date.getMonth() && corpusChristiDate.getDate() == date.getDate();
	}

	/**
	 * Verify if the date is Carnival
	 *
	 * @param date
	 * @return True if the date is Carnival, else if not false
	 */
	public static boolean isCarnival(final Date date) {

		if (date == null)
			return false;

		final Date carnivalDate = getCarnival(date.getYear() + 1900);

		return carnivalDate.getYear() == date.getYear() && carnivalDate.getMonth() == date.getMonth() && carnivalDate.getDate() == date.getDate();
	}

	/**
	 * Calculate the Easter day of a specific year
	 *
	 * @param year
	 * @return Easter date
	 */
	public static Date getEaster(final int year) {

		final Date easterDate = new Date(removeTime(new Date().getTime()));

		int a = year % 19;
		int b = year / 100;
		int c = year % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int l = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * l) / 451;
		int mes = (h + l - 7 * m + 114) / 31;
		int dia = ((h + l - 7 * m + 114) % 31) + 1;

		easterDate.setYear(year - 1900);
		easterDate.setMonth(mes - 1);
		easterDate.setDate(dia);

		return easterDate;
	}

	/**
	 * Calculate the Holy Friday day of a specific year.
	 * <p>
	 * Holy Friday: 2 days before Easter
	 * </p>
	 *
	 * @param year
	 * @return Holy Friday date
	 */
	public static Date getHolyFriday(final int year) {
		final Date easterDate = getEaster(year);
		return new Date(easterDate.getTime() - daysInMillis(2));
	}

	/**
	 * Calculate the Corpus Christi day of a specific year.
	 * <p>
	 * Corpus Christi: 60 days after Easter
	 * </p>
	 *
	 * @param year
	 * @return Corpus Christi date
	 */
	public static Date getCorpusChristi(final int year) {
		final Date easterDate = getEaster(year);
		return new Date(easterDate.getTime() + daysInMillis(60));
	}

	/**
	 * Calculate the Carnival day of a specific year.
	 * <p>
	 * Carnival: 47 days before Easter
	 * </p>
	 *
	 * @param year
	 * @return Carnival date
	 */
	public static Date getCarnival(final int year) {
		final Date easterDate = getEaster(year);
		return new Date(easterDate.getTime() - daysInMillis(47));
	}

}
