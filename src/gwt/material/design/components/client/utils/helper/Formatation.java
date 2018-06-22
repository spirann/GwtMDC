/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gwt.material.design.components.client.utils.helper;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Richeli Vargas
 */
public class Formatation {

	public static String document(final String document) {

		if (document == null) {
			return "";
		}

		final StringBuilder formatedDocument = new StringBuilder();

		// cnpj
		if (document.length() == 14) {
			formatedDocument.append(document.substring(0, 2));
			formatedDocument.append(".");
			formatedDocument.append(document.substring(2, 5));
			formatedDocument.append(".");
			formatedDocument.append(document.substring(5, 8));
			formatedDocument.append("/");
			formatedDocument.append(document.substring(8, 12));
			formatedDocument.append("-");
			formatedDocument.append(document.substring(12, 14));
		} // cpf
		else if (document.length() == 11) {
			formatedDocument.append(document.substring(0, 3));
			formatedDocument.append(".");
			formatedDocument.append(document.substring(3, 6));
			formatedDocument.append(".");
			formatedDocument.append(document.substring(6, 9));
			formatedDocument.append("-");
			formatedDocument.append(document.substring(9, 11));
		}

		return formatedDocument.toString();

	}

	/**
	 *
	 * @param seg
	 * @return HH:mm:ss
	 */
	public static String seconds(final int seg) {

		if (seg < 0) {
			return "??:??:??";
		}

		final int segundos = (seg % 60);
		final int minutos = (seg / 60) % 60;
		final int horas = (seg / 60) / 60;

		final StringBuffer builder = new StringBuffer();

		if (horas < 10) {
			builder.append("0");
		}
		builder.append(horas);

		builder.append(":");

		if (minutos < 10) {
			builder.append("0");
		}
		builder.append(minutos);

		builder.append(":");

		if (segundos < 10) {
			builder.append("0");
		}
		builder.append(segundos);

		return builder.toString();
	}

	/**
	 *
	 * @param seg
	 * @return
	 */
	public static String secondsII(final int seg) {

		if (seg < 0) {
			return "??h ??min";
		}

		// final int segundos = (seg % 60);
		final int minutos = (seg / 60) % 60;
		final int horas = (seg / 60) / 60;

		final StringBuffer builder = new StringBuffer();

		builder.append(horas);
		builder.append("h ");
		builder.append(minutos);
		builder.append("min");

		return builder.toString();
	}

	public static String secondsIII(final int seg) {

		if (seg < 0) {
			return "??:??";
		}

		final int segundos = (seg % 60);
		final int minutos = (seg / 60) % 60;

		final StringBuffer builder = new StringBuffer();

		if (minutos < 10) {
			builder.append("0");
		}
		builder.append(minutos);

		builder.append(":");

		if (segundos < 10) {
			builder.append("0");
		}
		builder.append(segundos);

		return builder.toString();
	}

	public static String phoneNumber(final String phone) {

		if (phone == null) {
			return "";
		}

		final StringBuilder formated = new StringBuilder();

		formated.append("(");
		formated.append(phone.substring(0, 2));
		formated.append(") ");
		formated.append(phone.substring(2, phone.length() - 4));
		formated.append("-");
		formated.append(phone.substring(phone.length() - 4));

		return formated.toString();
	}

	public static String currency(final double value) {

		final BigDecimal bigDecimal = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR);
		final String[] values = bigDecimal.toString().split("\\.");

		final StringBuilder builder = new StringBuilder(values[0]);

		final int countDot = (builder.toString().length() / 3) - (builder.toString().length() % 3 == 0 ? 1 : 0);

		for (int i = 0, p = 1; i < countDot; i++, p++) {
			builder.insert(builder.toString().length() - ((3 * p) + i), ".");
		}

		builder.append(",");
		builder.append(values[1]);

		return builder.toString().replace("-.", "-");
	}

	public static String integer(final double value) {

		final BigDecimal bigDecimal = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_FLOOR);
		final String[] values = bigDecimal.toString().split("\\.");

		final StringBuilder builder = new StringBuilder(values[0]);

		final int countDot = (builder.toString().length() / 3) - (builder.toString().length() % 3 == 0 ? 1 : 0);

		for (int i = 0, p = 1; i < countDot; i++, p++) {
			builder.insert(builder.toString().length() - ((3 * p) + i), ".");
		}

		return builder.toString();
	}

	public static String bytes(final long size) {

		final String value = String.valueOf(size);
		final int length = value.length();

		if (length > 15) {
			return ((int) (size / (Math.pow(1000, 5)))) + " HB";
		} else if (length > 12) {
			return ((int) (size / (Math.pow(1000, 4)))) + " TB";
		} else if (length > 9) {
			return ((int) (size / (Math.pow(1000, 3)))) + " GB";
		} else if (length > 6) {
			return ((int) (size / (Math.pow(1000, 2)))) + " MB";
		} else if (length > 3) {
			return ((int) (size / (Math.pow(1000, 1)))) + " KB";
		} else {
			return size + " Bytes";
		}
	}

	public static String barcode(final String barcodeNumber) {

		if (barcodeNumber == null) {
			return "";
		}

		final StringBuilder builder = new StringBuilder();

		final String[] chars = barcodeNumber.split("");

		int count = 0;
		for (String character : chars) {
			builder.append(character);
			count++;

			switch (count) {
			case 5:
			case 15:
			case 26:
				builder.append(".");
				break;
			case 10:
			case 21:
			case 32:
			case 33:
				builder.append(" ");
				break;
			}
		}

		return builder.toString();
	}

	public static String dateTime(final Date date) {
		return date(date) + " " + time(date);
	}

	@SuppressWarnings("deprecation")
	public static String date(final Date date) {

		if (date == null) {
			return "??/??/????";
		}

		final StringBuilder formated = new StringBuilder();

		final int day = date.getDate();
		final int month = date.getMonth() + 1;
		final int year = date.getYear() + 1900;

		if (day < 10) {
			formated.append("0");
		}
		formated.append(String.valueOf(day));
		formated.append("/");
		if (month < 10) {
			formated.append("0");
		}
		formated.append(String.valueOf(month));
		formated.append("/");
		formated.append(String.valueOf(year));

		return formated.toString();
	}

	@SuppressWarnings("deprecation")
	public static String time(final Date date) {

		if (date == null) {
			return "??:??:??";
		}

		final StringBuilder formated = new StringBuilder();

		final int hours = date.getHours();
		final int minutes = date.getMinutes();
		final int seconds = date.getSeconds();

		if (hours < 10) {
			formated.append("0");
		}
		formated.append(String.valueOf(hours));
		formated.append(":");

		if (minutes < 10) {
			formated.append("0");
		}
		formated.append(String.valueOf(minutes));
		formated.append(":");

		if (seconds < 10) {
			formated.append("0");
		}
		formated.append(String.valueOf(seconds));

		return formated.toString();
	}
}
