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
package gwt.material.design.components.client.ui.misc.datePicker.selectors.year;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.lang.MdcYear;
import gwt.material.design.components.client.ui.misc.datePicker.selectors.AbstractSelector;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Years extends AbstractSelector<MdcYear[], YearsItem> {

	public Years() {
		super(CssName.MDC_DATEPICKER__YEARS);
	}

	@Override
	protected MdcYear[] getInitialValues() {
		return getValues(new MdcMonth().getYear());
	}

	@Override
	protected <V> long toNumber(final V value) {
		return ((MdcYear) value).getYear();
	}

	@Override
	protected <V> YearsItem drawItem(V value) {
		final MdcYear month = (MdcYear) value;
		return new YearsItem(month);
	}

	protected MdcYear[] getValues(final int year) {

		final MdcYear[] initialValues = new MdcYear[25];

		for (int index = 0, auxYear = year - 12; index < initialValues.length; index++, auxYear++)
			initialValues[index] = new MdcYear(auxYear);

		return initialValues;
	}

	public void draw(final int year) {
		draw(getValues(year));
	}
}
