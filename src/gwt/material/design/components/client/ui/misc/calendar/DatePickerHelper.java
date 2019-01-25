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
package gwt.material.design.components.client.ui.misc.calendar;

import java.util.Date;

import gwt.material.design.components.client.base.interfaces.HasInputMask;
import gwt.material.design.components.client.base.interfaces.HasPlaceholder;
import gwt.material.design.components.client.base.interfaces.HasValidation;
import gwt.material.design.components.client.base.interfaces.StringToDate;
import gwt.material.design.components.client.base.mixin.ValidationMixin;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.validation.Validation.Result;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class DatePickerHelper {

	public static final <W extends HasPlaceholder & HasInputMask> void formatPlaceholder(final W widget) {
		if (widget.getPlaceholder() == null || widget.getPlaceholder().trim().isEmpty())
			widget.setPlaceholder(
					widget.getInputMask().replace("/99/", "/" + IMessages.INSTANCE.mdc_calendar_mm() + "/")
							.replace("-99-", "-" + IMessages.INSTANCE.mdc_calendar_mm() + "-")
							.replace("9999", IMessages.INSTANCE.mdc_calendar_yyyy())
							.replace("99", IMessages.INSTANCE.mdc_calendar_dd()));

	}

	public static final <W extends HasInputMask & HasValidation<?,?>> StringToDate defaultStringToDate(final W widget) {
		return new StringToDate() {

			@Override
			public String convert(Date value) {
				return dateToString(widget, value);
			}

			@Override
			public Date convert(String value) {
				return stringToDate(widget, value);
			}
		};
	}
	
	@SuppressWarnings("deprecation")
	protected static final <W extends HasInputMask & HasValidation<?,?>> String dateToString(final W widget, final Date date) {

		if (date == null)
			return "";

		final String mask = widget.getInputMask();

		final String day = date.getDate() < 9 ? "0" + date.getDate() : String.valueOf(date.getDate());
		final String month = date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : String.valueOf((date.getMonth() + 1));
		final String year = String.valueOf(date.getYear() + 1900);

		switch (mask) {
		case "99/99/9999":
		case "99-99-9999":
			return Masker.toPattern(day + month + year, mask);
		case "9999/99/99":
		case "9999-99-99":
			return year + month + day;
		default:
			return "";
		}
	}

	@SuppressWarnings("deprecation")
	protected static final <W extends HasInputMask & HasValidation<?,?>> Date stringToDate(final W widget, final String maskedValue) {

		if (maskedValue.isEmpty())
			return null;

		final Result result = ValidationMixin.toResult(widget.validate());
		switch (result.getState()) {
		case ERROR:
			return null;
		default:
		}

		final String mask = widget.getInputMask();

		final int day;
		final int month;
		final int year;

		switch (mask) {
		case "99/99/9999":
		case "99-99-9999":
			day = Integer.parseInt(maskedValue.substring(0, 2));
			month = Integer.parseInt(maskedValue.substring(3, 5));
			year = Integer.parseInt(maskedValue.substring(6));
			break;
		case "9999/99/99":
		case "9999-99-99":
			day = Integer.parseInt(maskedValue.substring(9));
			month = Integer.parseInt(maskedValue.substring(6, 8));
			year = Integer.parseInt(maskedValue.substring(0, 5));
			break;
		default:
			return null;
		}
		
		return new Date(year - 1900, month - 1, day);
	}
}