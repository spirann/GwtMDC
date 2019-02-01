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

import gwt.material.design.components.client.base.interfaces.HasHelperText;
import gwt.material.design.components.client.base.interfaces.HasLabel;
import gwt.material.design.components.client.base.interfaces.HasPlaceholder;
import gwt.material.design.components.client.base.interfaces.HasReadOnly;
import gwt.material.design.components.client.base.interfaces.HasState;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.ui.misc.calendar.MaterialDatePickerInputBase;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDatePickerInput extends MaterialDatePickerInputBase<Date, MaterialDatePickerDialog> implements HasLabel, HasPlaceholder, HasHelperText, HasReadOnly, HasState {

	protected MaterialTextField input = getInputs()[0];

	@Override
	protected MaterialDatePickerDialog getDialog() {
		return dialog == null ?  new MaterialDatePickerDialog() : dialog;
	}

	@Override
	protected MaterialTextField[] getInputs() {		
		return inputs == null ? new MaterialTextField[] { newInput() } : inputs;
	}

	@Override
	public void setValue(Date value) {
	
		if (value == null)
			input.setValue("");
		else
			input.setValue(converter.undo(input, value));

		super.setValue(value);

	}
	
	@Override
	protected void setValueChangeOnTyping() {
		super.setValue(converter.convert(input, input.getValue()));
	}

	@Override
	public void setPlaceholder(String placeholder) {
		input.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return input.getPlaceholder();
	}

	@Override
	public void setLabel(String label) {
		input.setLabel(label);
	}

	@Override
	public String getLabel() {
		return input.getLabel();
	}

	@Override
	public void setHelperText(String text) {
		input.setHelperText(text);
	}

	@Override
	public String getHelperText() {
		return input.getHelperText();
	}

	@Override
	public void setState(State state) {
		input.setState(state);
	}

	@Override
	public State getState() {
		return input.getState();
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		input.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() {
		return input.isReadOnly();
	}

}
