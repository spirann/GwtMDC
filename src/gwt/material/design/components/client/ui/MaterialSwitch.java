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

import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.CheckedMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSwitch extends MaterialFormField<Boolean> implements HasText {

	// /////////////////////////////////////////////////////////////
	// Switch
	// /////////////////////////////////////////////////////////////
	protected Div switch_ = new Div(CssName.MDC_SWITCH);
	protected Input input = new Input(InputType.CHECKBOX, CssName.MDC_SWITCH_NATIVE_CONTROL);
	protected Div divBackground = new Div(CssName.MDC_SWITCH_BACKGROUND);
	protected Div divKnob = new Div(CssName.MDC_SWITCH_KNOB);

	// /////////////////////////////////////////////////////////////
	// Label
	// /////////////////////////////////////////////////////////////
	protected Label label = new Label();

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final CheckedMixin<Input> checkedMixin = new CheckedMixin<>(input);

	public MaterialSwitch() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		divBackground.add(divKnob);

		switch_.setPadding(8);
		switch_.setDisabledClass(CssName.MDC_SWITCH_DISABLED);
		switch_.add(input);
		switch_.add(divBackground);

		label.addStyleName(CssName.MDC_SWITCH_LABEL);

		add(switch_);
		add(label);
	}

	@Override
	public void setId(String id) {
		input.setId(id);
		label.setFor(input.getId());
	}

	@Override
	public String getId() {
		return input.getId();
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		checkedMixin.setChecked(value);
		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public Boolean getValue() {
		return checkedMixin.isChecked();
	}

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public void setRipple(Ripple ripple) {
		switch_.setRipple(ripple);
	}

	@Override
	public Ripple getRipple() {
		return switch_.getRipple();
	}

	@Override
	public void setCircle(boolean circle) {
		switch_.setCircle(circle);
	}

	@Override
	public void setTextColor(Color color) {
		label.setTextColor(color);
	}
}
