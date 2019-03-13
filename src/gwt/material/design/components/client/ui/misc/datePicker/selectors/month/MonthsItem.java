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
import gwt.material.design.components.client.ui.misc.datePicker.selectors.AbstractItem;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MonthsItem extends AbstractItem<MdcMonth> {
	
	public MonthsItem(final MdcMonth month) {
		super(month, CssName.MDC_DATEPICKER__MONTHS__ITEM);
	}
	
	@Override
	protected String dataToString(MdcMonth data) {
		return data.getMonthShortName();
	}
}
