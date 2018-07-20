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
package gwt.material.design.components.client.ui.misc;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.HasHelperText;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.base.HasPlaceholder;
import gwt.material.design.components.client.base.HasState;
import gwt.material.design.components.client.base.HasTextFieldValidation;
import gwt.material.design.components.client.base.HasValidationHandlers;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.constants.TextFieldIconPosition;
import gwt.material.design.components.client.events.ValidationEvent.ValidationHandler;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.validation.Validation.Result;
import gwt.material.design.components.client.validation.ui.TextFieldValidation;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialInputBox extends Div implements HasHelperText, HasText, HasLabel, HasDense, HasPlaceholder,
		HasState, HasTextFieldValidation, HasValidationHandlers<Result> {

	protected final MaterialInput field = contructInput();
	protected final MaterialTextFieldHelper helper = new MaterialTextFieldHelper();

	public MaterialInputBox() {
		super(CssName.MDC_TEXT_FIELD_CONTAINER);
	}

	protected MaterialInput contructInput() {
		return new MaterialInput();
	}

	@Override
	protected void onInitialize() {

		field.setAriaControls(helper.getId());
		field.setAriaDescribedBy(helper.getId());

		addValidationHandler(event -> setHelperText(event.getResult().getMessage()));

		add(field);
		add(helper);

		super.onInitialize();
	}

	@Override
	public HandlerRegistration addValidationHandler(ValidationHandler<Result> handler) {
		return field.addValidationHandler(handler);
	}

	@Override
	public void setHelperText(String text) {
		helper.setHelperText(text);
	}

	@Override
	public String getHelperText() {
		return helper.getHelperText();
	}

	@Override
	public void setTextColor(Color color) {
		field.setTextColor(color);
	}

	@Override
	public void setColor(Color color) {
		field.setColor(color);
	}

	public void setFocusedColor(Color color) {
		field.setFocusedColor(color);
	}

	@Override
	public void setHelperTextPersistent(boolean persistent) {
		helper.setHelperTextPersistent(persistent);
	}

	@Override
	public boolean isHelperTextPersistent() {
		return helper.isHelperTextPersistent();
	}

	@Override
	public void setHelperTextValidation(boolean validation) {
		helper.setHelperTextValidation(validation);
	}

	@Override
	public boolean isHelperTextValidation() {
		return helper.isHelperTextValidation();
	}

	@Override
	public void setPlaceholder(String placeholder) {
		field.setPlaceholder(placeholder);
	}

	@Override
	public String getPlaceholder() {
		return field.getPlaceholder();
	}

	@Override
	public void setDense(boolean dense) {
		field.setDense(dense);
	}

	@Override
	public boolean isDense() {
		return field.isDense();
	}

	@Override
	public void setLabel(String label) {
		field.setLabel(label);
	}

	@Override
	public String getLabel() {
		return field.getLabel();
	}

	@Override
	public String getText() {
		return field.getText();
	}

	@Override
	public void setText(String text) {
		field.setText(text);
	}

	public void setMinLength(final int length) {
		field.setMinLength(length);
	}

	public int getMinLength() {
		return field.getMinLength();
	}

	public void setMaxLength(final int length) {
		field.setMaxLength(length);
	}

	public int getMaxLength() {
		return field.getMaxLength();
	}

	@Override
	public void setState(State state) {
		field.setState(state);
	}

	@Override
	public State getState() {
		return field.getState();
	}

	public TextFieldIconPosition getIconPosition() {
		return field.getIconPosition();
	}

	public void setIconPosition(TextFieldIconPosition iconPosition) {
		field.setIconPosition(iconPosition);
	}

	public TextFieldValidation getValidation() {
		return field.getValidation();
	}

	public void setValidation(TextFieldValidation validation) {
		field.setValidation(validation);
	}
}
