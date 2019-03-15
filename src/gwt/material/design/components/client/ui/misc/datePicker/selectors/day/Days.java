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

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.ui.misc.datePicker.selectors.AbstractSelector;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Days extends AbstractSelector<MdcDate, DaysItem> {

	protected MdcMonth visibleMonth;
	
	public Days() {
		super(CssName.MDC_DATEPICKER__DAYS);
	}

	@Override
	protected Collection<MdcDate> getInitialValues() {
		return getValues(new MdcMonth());
	}

	@Override
	protected long toNumber(final MdcDate value) {
		return value.getTimestamp();
	}

	@Override
	protected DaysItem drawItem(final MdcDate value) {
		final DaysItem item = new DaysItem(value);
		if(!value.getMdcMonth().equals(visibleMonth))
			item.addStyleName(CssName.MDC_DATEPICKER__DAYS__OUT_OF_MONTH);
		return item;
	}
	
	@Override
	public void draw(final Collection<MdcDate> values) {
		
		visibleMonth = new LinkedList<>(values).get(values.size() / 2).getMdcMonth();
		
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

	protected Collection<MdcDate> getValues(final MdcMonth month) {
		return MdcMonth.daysOfMonth(month.getYear(), month.getMonth(), true);
	}
}
