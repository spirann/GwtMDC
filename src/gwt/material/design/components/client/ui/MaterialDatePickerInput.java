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

import gwt.material.design.components.client.constants.IconPosition;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.validation.TextFieldValidation;
import gwt.material.design.components.client.validation.Validation.Result;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDatePickerInput extends MaterialTextField {

	public static interface StringToDate {
		public Date convert(final String value);

		public String convert(final Date value);
	}

	protected final MaterialDatePickerDialog dialog = new MaterialDatePickerDialog();

	private StringToDate stringToDate = new StringToDate() {

		@Override
		public String convert(Date value) {
			return dateToString(value);
		}

		@Override
		public Date convert(String value) {
			return valueToDate(value);
		}
	};

	@Override
	protected void onInitialize() {
		super.onInitialize();

		dialog.addAcceptHandler(event -> setValue(stringToDate.convert(dialog.getValue())));

		add(dialog);

		setInputMask(Masker.Defaults.INSTANCE.date__mask());
		setValidation(TextFieldValidation.Defaults.date());
		if (getPlaceholder() == null || getPlaceholder().isEmpty())
			setPlaceholder(getInputMask().replace("/99/", "/" + IMessages.INSTANCE.mdc_calendar_mm() + "/")
					.replace("-99-", "-" + IMessages.INSTANCE.mdc_calendar_mm() + "-")
					.replace("9999", IMessages.INSTANCE.mdc_calendar_yyyy())
					.replace("99", IMessages.INSTANCE.mdc_calendar_dd()));
		setIcon(IconType.EVENT);
		setIconPosition(IconPosition.TRAILING);
		setMaxLength(10);
		addIconClickHandler(event -> openDatePicker());

		JsHelper.allowNumbersOnly(getInput().getElement());

	}

	public StringToDate getStringToDate() {
		return stringToDate;
	}

	public void setStringToDate(StringToDate stringToDate) {
		this.stringToDate = stringToDate;
	}

	@SuppressWarnings("deprecation")
	protected String dateToString(final Date date) {

		if (date == null)
			return "";

		final String mask = getInputMask();

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
	protected Date valueToDate(final String maskedValue) {

		if(maskedValue.isEmpty())
			return null;
		
		final Result result = getValidation().validate(getValue(), getInputMask(), isRequired(), getMinLength(), getMaxLength());
		switch (result.getState()) {
		case SUCCESS:
		case NONE:
			break;
		default:
			return null;
		}

		final String mask = getInputMask();

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

	public void openDatePicker() {
		if (getValue().isEmpty())
			dialog.setValue(stringToDate.convert(getValue()));
		else
			dialog.setValue(stringToDate.convert(Masker.toPattern(getValue(), getInputMask())));
		dialog.open();
	}

	public Date getMinDate() {
		return dialog.getMinDate();
	}

	public void setMinDate(Date minDate) {
		dialog.setMinDate(minDate);
	}
	
	public void setMinDate(String minDate) {
		dialog.setMinDate(minDate);
	}

	public Date getMaxDate() {
		return dialog.getMaxDate();
	}

	public void setMaxDate(Date maxDate) {
		dialog.setMaxDate(maxDate);
	}
	
	public void setMaxDate(String maxDate) {
		dialog.setMaxDate(maxDate);
	}
	
	public void setDateTooltip(final Date date, final String tooltip) {
		dialog.setDateTooltip(date, tooltip);
	}
	
	public String getDateTooltip(final Date date) {
		return dialog.getDateTooltip(date);
	}
	
	public String removeDateTooltip(final Date date) {
		return dialog.removeDateTooltip(date); 
	}
}
