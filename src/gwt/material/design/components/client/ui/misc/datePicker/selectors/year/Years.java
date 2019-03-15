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

import java.util.Collection;
import java.util.LinkedList;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.lang.MdcYear;
import gwt.material.design.components.client.ui.misc.datePicker.selectors.AbstractSelector;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Years extends AbstractSelector<MdcYear, YearsItem> {

	public Years() {
		super(CssName.MDC_DATEPICKER__YEARS);
	}

	@Override
	protected Collection<MdcYear> getInitialValues() {
		return getValues(new MdcMonth().getYear());
	}

	@Override
	protected long toNumber(final MdcYear value) {
		return value.getYear();
	}

	@Override
	protected YearsItem drawItem(final MdcYear value) {
		return new YearsItem(value);
	}

	public void draw(final int year) {
		draw(getValues(year));
	}

	protected Collection<MdcYear> getValues(final int year) {

		final Collection<MdcYear> initialValues = new LinkedList<>();
		
		MdcYear next = new MdcYear(year - 12);
		do {
			initialValues.add(next);
			next = next.next();
		} while (initialValues.size() < 25);
		
		return initialValues;
	}
}
