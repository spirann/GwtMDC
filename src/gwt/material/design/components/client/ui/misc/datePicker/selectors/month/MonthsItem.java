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
import gwt.material.design.components.client.ui.MaterialLabel;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MonthsItem extends Div {
	
	protected final MaterialLabel label = new MaterialLabel(CssName.MDC_TYPOGRAPHY__CAPTION);
	
	public MonthsItem(final MdcMonth month) {
		super(CssName.MDC_DATEPICKER__MONTHS__ITEM);
		setDataObject(month);
	}
	
	@Override
	protected void onInitialize() {
		ripleMixin.initialize();
		super.onInitialize();		
		final MdcMonth month = (MdcMonth) getDataObject();
		label.setText(month.getMonthShortName());		
		StyleHelper.setProperty(getElement(), "month", month.getMonth());
		add(label);		
	}
}
