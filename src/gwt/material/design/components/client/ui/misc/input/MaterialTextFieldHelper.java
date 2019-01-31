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
package gwt.material.design.components.client.ui.misc.input;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.interfaces.HasHelperText;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.P;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTextFieldHelper extends P implements HasHelperText {

	protected final ToggleStyleMixin<MaterialTextFieldHelper> helpPersistentMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_TEXT_FIELD__HELPER_TEXT_PERSISTENT);
	protected final ToggleStyleMixin<MaterialTextFieldHelper> helpValidationMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_TEXT_FIELD__HELPER_TEXT_VALIDATION_MSG);
	protected final AttributeMixin<MaterialTextFieldHelper, Boolean> ariaHiddenMixin = new AttributeMixin<>(this,
			CssAttribute.ARIA_HIDDEN, true, FromString.TO_BOOLEAN);

	public MaterialTextFieldHelper() {
		super(CssName.MDC_TEXT_FIELD__HELPER_TEXT);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.textField.MDCTextFieldHelperText(element);
	}-*/;

	@Override
	public void setHelperText(final String text) {
		setText(text);
	}

	@Override
	public String getHelperText() {
		return getText();
	}

	@Override
	public void setHelperTextPersistent(boolean persistent) {
		helpPersistentMixin.toggle(persistent);
	}

	@Override
	public boolean isHelperTextPersistent() {
		return helpPersistentMixin.isApplied();
	}

	@Override
	public void setHelperTextValidation(boolean validation) {
		helpValidationMixin.toggle(!validation);
	}

	@Override
	public boolean isHelperTextValidation() {
		return !helpValidationMixin.isApplied();
	}

}
