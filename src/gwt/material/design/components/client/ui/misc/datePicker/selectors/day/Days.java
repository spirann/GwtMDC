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
package gwt.material.design.components.client.ui.misc.datePicker.selectors.day;

import java.util.Date;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.ui.misc.datePicker.selectors.AbstractSelector;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Days extends AbstractSelector<MdcDate[], DaysItem> {

	public Days() {
		super(CssName.MDC_DATEPICKER__DAYS);
	}

	@Override
	protected MdcDate[] getInitialValues() {
		return getValues(new MdcMonth());
	}

	@Override
	protected <V> long toNumber(final V value) {
		return ((MdcDate) value).getTimestamp();
	}

	@Override
	protected <V> DaysItem drawItem(V value) {
		final MdcDate month = (MdcDate) value;
		return new DaysItem(month);
	}

	protected MdcDate[] getValues(final MdcMonth month) {
		return MdcMonth.daysOfMonth(month.getYear(), month.getMonth(), true).stream().toArray(MdcDate[]::new);
	}
	
	@Override
	public void draw(final MdcDate[] values) {
		super.draw(values);
		
		for (int weekDay = 0; weekDay < 7; weekDay++)
			insert(new DaysHeader(weekDay), weekDay);
	}
	
	@SuppressWarnings("deprecation")
	public void draw() {
		final Date date = new Date();
		final int year = date.getYear() + 1900;
		final int month = date.getMonth() + 1;
		draw(year, month);
	}

	public void draw(final int year, final int month) {
		draw(getValues(new MdcMonth(year, month)));
	}
	
	public void draw(final MdcMonth mdcMonth) {
		draw(getValues(mdcMonth));
	}
}
