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

import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.misc.calendar.MaterialDatePickerInputBase;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDateRangePickerInput extends MaterialDatePickerInputBase<Date[], MaterialDateRangePickerDialog> {

	protected final MaterialTextField startDate = getInputs()[0];
	protected final MaterialTextField endDate = getInputs()[1];	
		
	@Override
	protected void onInitialize() {
		startDate.setLabel(IMessages.INSTANCE.mdc_calendar_initial_date());
		endDate.setLabel(IMessages.INSTANCE.mdc_calendar_final_date());		
		super.onInitialize();
	}
	
	@Override
	protected MaterialDateRangePickerDialog getDialog() {
		return dialog == null ?  new MaterialDateRangePickerDialog() : dialog;
	}	

	@Override
	protected MaterialTextField[] getInputs() {
		return inputs == null ? new MaterialTextField[] { newInput(), newInput() } : inputs;
	}
	
	@Override
	public void setValue(Date[] value) {
		
		if (value == null || value.length == 0) {
			startDate.setValue("");
			endDate.setValue("");
		} else if (value.length == 1) {
			startDate.setValue(converter.undo(startDate, value[0]));
			endDate.setValue("");
		} else if (value.length > 1) {
			startDate.setValue(converter.undo(startDate, value[0]));
			endDate.setValue(converter.undo(endDate, value[1]));
		}
		
		super.setValue(value);
		
	}
}
