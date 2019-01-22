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

import gwt.material.design.components.client.base.interfaces.StringToDate;
import gwt.material.design.components.client.constants.IconPosition;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.ui.misc.calendar.DatePickerHelper;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.validation.ValidationForTextField;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDatePickerInput extends MaterialTextField {

	protected final MaterialDatePickerDialog dialog = new MaterialDatePickerDialog();

	private StringToDate stringToDate = DatePickerHelper.defaultStringToDate(input);

	@Override
	protected void onInitialize() {
		super.onInitialize();

		dialog.addAcceptHandler(event -> setValue(stringToDate.convert(dialog.getValue())));

		add(dialog);
		
		setInputMask(Masker.Defaults.INSTANCE.date__mask());;
		addValidation(ValidationForTextField.date());
		
		DatePickerHelper.formatPlaceholder(this);
		
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
