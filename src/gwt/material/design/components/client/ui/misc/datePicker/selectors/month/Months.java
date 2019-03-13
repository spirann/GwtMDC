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
package gwt.material.design.components.client.ui.misc.datePicker.selectors.month;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.ui.misc.datePicker.selectors.AbstractSelector;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Months extends AbstractSelector<MdcMonth[], MonthsItem> {

	public Months() {
		super(CssName.MDC_DATEPICKER__MONTHS);
	}

	@Override
	protected MdcMonth[] getInitialValues() {
		return getValues(new MdcMonth().getYear());
	}

	@Override
	protected <V> long toNumber(final V value) {
		final MdcMonth month = (MdcMonth) value;
		return (month.getYear() * 12) + month.getMonth();
	}

	@Override
	protected <V> MonthsItem drawItem(V value) {
		final MdcMonth month = (MdcMonth) value;
		return new MonthsItem(month);
	}

	protected MdcMonth[] getValues(final int year) {
		final MdcMonth initial = new MdcMonth(year, 1);

		final MdcMonth[] initialValues = new MdcMonth[12];
		initialValues[0] = initial;

		for (int index = 1; index < initialValues.length; index++)
			initialValues[index] = initialValues[index - 1].next();

		return initialValues;
	}

	public void draw(final int year) {
		draw(getValues(year));
	}
}
