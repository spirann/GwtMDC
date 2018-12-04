package gwt.material.design.components.client.utils.helper;

import java.util.Date;

@SuppressWarnings("deprecation")
public class DateTimeHelper {

	private static final int[] KNOW_LAST_DAYS = { 31, 30, 29, 28 };

	/**
	 * Retorna o número de dias entre as datas
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / daysInMillis(1));
	}

	/**
	 * Retorna o número de horas entre as datas
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int hoursBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / hoursInMillis(1));
	}

	/**
	 * Retorna o número de minutos entre as datas
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int minutesBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / minutesInMillis(1));
	}

	/**
	 * Retorna o número de segundos entre as datas
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int secondsBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / secondsInMillis(1));
	}

	/**
	 * Retorna o número de dias
	 * 
	 * @param time
	 * @return
	 */
	public static int countDays(long time) {
		return (int) (time / daysInMillis(1));
	}

	/**
	 * Retorna o número de horas
	 * 
	 * @param time
	 * @return
	 */
	public static int countHours(long time) {
		return (int) (time / hoursInMillis(1));
	}

	/**
	 * Retorna o número de minutos
	 * 
	 * @param time
	 * @return
	 */
	public static int countMinutes(long time) {
		return (int) (time / minutesInMillis(1));
	}

	/**
	 * Retorna o número de segundos
	 * 
	 * @param time
	 * @return
	 */
	public static int countSeconds(long time) {
		return (int) (time / secondsInMillis(1));
	}

	/**
	 * Retorna o último dia do mês
	 * 
	 * @param date
	 * @return
	 */
	public static int lastDayOfMonth(final Date date) {

		final long time = date.getTime();

		for (int lastDay : KNOW_LAST_DAYS) {
			final Date calc = new Date(time);
			calc.setDate(lastDay);
			final int calculatedLastDay = calc.getDate();
			if (calculatedLastDay == lastDay) {
				return calculatedLastDay;
			}
		}

		return -1;
	}

	/**
	 * Recebe a data em milisegundos e remove a hora, os minutos, os segundos e os
	 * milisegudos
	 * 
	 * @param date
	 * @return
	 */
	public static long fromTheDate(final Date date) {
		final long time = date.getTime();
		final long fromTheDate = time - (time % daysInMillis(1));
		final int timezone = date.getTimezoneOffset() == 0 ? 0 : date.getTimezoneOffset() / 60;
		return fromTheDate + hoursInMillis(timezone);
	}

	/**
	 * Recebe a data em milisegundos e remove a hora, os minutos, os segundos e os
	 * milisegudos
	 * 
	 * @param time
	 * @return
	 */
	public static long fromTheDate(final long time) {
		final Date date = new Date();
		date.setTime(time);
		return fromTheDate(date);
	}

	/**
	 * Recebe a data em milisegundos e remove os minutos, os segundos e os
	 * milisegudos
	 * 
	 * @param time
	 * @return
	 */
	public static long fromTheHours(final long time) {
		return time - (time % hoursInMillis(1));
	}

	/**
	 * Recebe a data em milisegundos e remove os segundos e os milisegudos
	 * 
	 * @param time
	 * @return
	 */
	public static long fromTheMinutes(final long time) {
		return time - (time % minutesInMillis(1));
	}

	/**
	 * Recebe a data em milisegundos e remove os milisegudos
	 * 
	 * @param time
	 * @return
	 */
	public static long fromTheSeconds(final long time) {
		return time - (time % secondsInMillis(1));
	}

	/**
	 * Retorna o número de dias informado em milisegundos
	 * 
	 * @param days
	 * @return
	 */
	public static long daysInMillis(final int days) {
		return days * 24L * 60L * 60L * 1000L;
	}

	/**
	 * Retorna o número de horas informado em milisegundos
	 * 
	 * @param days
	 * @return
	 */
	public static long hoursInMillis(final int days) {
		return days * 60L * 60L * 1000L;
	}

	/**
	 * Retorna o número de minutos informado em milisegundos
	 * 
	 * @param days
	 * @return
	 */
	public static long minutesInMillis(final int days) {
		return days * 60L * 1000L;
	}

	/**
	 * Retorna o número de segundos informado em milisegundos
	 * 
	 * @param days
	 * @return
	 */
	public static long secondsInMillis(final int days) {
		return days * 1000L;
	}

	/**
	 * Verifica se a data passada é domingo de páscoa
	 *
	 * @param date
	 * @return
	 */
	public static boolean isEaster(final Date date) {

		if (date == null) {
			return false;
		}

		final Date easterDate = getEaster(date.getYear() + 1900);

		return easterDate.getYear() == date.getYear() && easterDate.getMonth() == date.getMonth() && easterDate.getDate() == date.getDate();
	}

	/**
	 * Verifica se a data passada é Sexta-feira Santa
	 *
	 * @param date
	 * @return
	 */
	public static boolean isHolyFriday(final Date date) {

		if (date == null) {
			return false;
		}

		final Date holyFridayDate = getHolyFriday(date.getYear() + 1900);

		return holyFridayDate.getYear() == date.getYear() && holyFridayDate.getMonth() == date.getMonth() && holyFridayDate.getDate() == date.getDate();
	}

	/**
	 * Verifica se a data passada é Corpus Christi
	 *
	 * @param date
	 * @return
	 */
	public static boolean isCorpusChristi(final Date date) {

		if (date == null) {
			return false;
		}

		final Date corpusChristiDate = getCorpusChristi(date.getYear() + 1900);

		return corpusChristiDate.getYear() == date.getYear() && corpusChristiDate.getMonth() == date.getMonth() && corpusChristiDate.getDate() == date.getDate();
	}

	/**
	 * Verifica se a data passada é Carnaval
	 *
	 * @param date
	 * @return
	 */
	public static boolean isCarnival(final Date date) {

		if (date == null) {
			return false;
		}

		final Date carnivalDate = getCarnival(date.getYear() + 1900);

		return carnivalDate.getYear() == date.getYear() && carnivalDate.getMonth() == date.getMonth() && carnivalDate.getDate() == date.getDate();
	}

	/**
	 * Recebe o ano e retorna o dia em que a Páscoa vai cair
	 *
	 * @param year
	 * @return
	 */
	public static Date getEaster(final int year) {

		final Date easterDate = new Date(fromTheDate(new Date().getTime()));

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
	 * Sexta-feira Santa dois dias antes da Páscoa
	 *
	 * @param year
	 * @return
	 */
	public static Date getHolyFriday(final int year) {
		final Date easterDate = getEaster(year);
		return new Date(easterDate.getTime() - daysInMillis(2));
	}

	/**
	 * Corpus Christi 60 dias apos a Páscoa
	 *
	 * @param year
	 * @return
	 */
	public static Date getCorpusChristi(final int year) {
		final Date easterDate = getEaster(year);
		return new Date(easterDate.getTime() + daysInMillis(60));
	}

	/**
	 * Carnaval 47 dias antes da Páscoa
	 *
	 * @param year
	 * @return
	 */
	public static Date getCarnival(final int year) {
		final Date easterDate = getEaster(year);
		return new Date(easterDate.getTime() - daysInMillis(47));
	}

}
