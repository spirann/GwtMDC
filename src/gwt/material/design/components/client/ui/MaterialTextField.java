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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.interfaces.HasDense;
import gwt.material.design.components.client.base.interfaces.HasHelperText;
import gwt.material.design.components.client.base.interfaces.HasIcon;
import gwt.material.design.components.client.base.interfaces.HasIconClickHandlers;
import gwt.material.design.components.client.base.interfaces.HasInputMask;
import gwt.material.design.components.client.base.interfaces.HasLabel;
import gwt.material.design.components.client.base.interfaces.HasPlaceholder;
import gwt.material.design.components.client.base.interfaces.HasRequired;
import gwt.material.design.components.client.base.interfaces.HasState;
import gwt.material.design.components.client.base.interfaces.HasTextFieldValidation;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.interfaces.HasValidationHandlers;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.TextFieldIconPosition;
import gwt.material.design.components.client.constants.TextFieldType;
import gwt.material.design.components.client.events.IconClickEvent.IconClickHandler;
import gwt.material.design.components.client.ui.misc.input.MaterialInputBox;
import gwt.material.design.components.client.validation.Validation.Result;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTextField extends MaterialInputBox implements HasHelperText, HasText, HasLabel, HasDense, HasRequired,
		HasPlaceholder, HasType<TextFieldType>, HasInputMask, HasState, HasIcon, HasIconClickHandlers,
		HasTextFieldValidation, HasValidationHandlers<Result> {

	@Override
	public IconType getIcon() {
		return input.getIcon();
	}

	@Override
	public void setIcon(IconType iconType) {
		input.setIcon(iconType);
	}
	
	@Override
	public void setIcon(IconType iconType, boolean animate) {
		input.setIcon(iconType, animate);
	}

	@Override
	public void setIconColor(Color color) {
		input.setIconColor(color);
	}
	
	public TextFieldIconPosition getIconPosition() {
		return input.getIconPosition();
	}

	public void setIconPosition(TextFieldIconPosition iconPosition) {
		input.setIconPosition(iconPosition);
	}
	
	@Override
	public HandlerRegistration addIconClickHandler(IconClickHandler handler) {
		return input.addIconClickHandler(handler);
	}

	@Override
	public void setType(TextFieldType type) {
		input.setType(type);
	}

	@Override
	public TextFieldType getType() {
		return input.getType();
	}

	public void setInputType(InputType type) {
		input.setInputType(type);
	}

	public InputType getInputType() {
		return input.getInputType();
	}
	
	@Override
	public void setRequired(boolean required) {
		input.setRequired(required);
	}

	@Override
	public boolean isRequired() {
		return input.isRequired();
	}

	@Override
	public void setInputMask(String inputMask) {
		input.setInputMask(inputMask);
	}

	@Override
	public String getInputMask() {
		return input.getInputMask();
	}

}
