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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.interfaces.HasIndeterminate;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialSelectedField;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCheckbox extends MaterialSelectedField implements HasText, HasIndeterminate {

	// /////////////////////////////////////////////////////////////
	// Checkbox
	// /////////////////////////////////////////////////////////////
	protected final Div checkbox = new Div(CssName.MDC_CHECKBOX);
	protected final Input input = new Input(InputType.CHECKBOX, CssName.MDC_CHECKBOX__NATIVE_CONTROL);
	protected final Div background = new Div(CssName.MDC_CHECKBOX__BACKGROUND);
	protected final MaterialSvg checkmark = new MaterialSvg(CssName.MDC_CHECKBOX__CHECKMARK_PATH);
	protected final Div mixedmark = new Div(CssName.MDC_CHECKBOX__MIXEDMARK);
	protected final Label label = new Label(CssName.MDC_CHECKBOX__LABEL);

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final AttributeMixin<Input, Boolean> indeterminateMixin = new AttributeMixin<>(input,
			CssAttribute.INDETERMINATE, false);

	public MaterialCheckbox() {
		super(CssName.MDC_FORM_FIELD);
		super.initializeSelectedMixin(checkbox, CssName.MDC_CHECKBOX__SELECTED, input);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		var _this = this;
		var checkbox = this.@gwt.material.design.components.client.ui.MaterialCheckbox::checkbox;
		var checkbox_element = checkbox.@gwt.material.design.components.client.ui.html.Div::getElement()();
		element.input = new $wnd.mdc.checkbox.MDCCheckbox(checkbox_element);
		return element;
	}-*/;
	
	@Override
	protected void initializeSelectedMixin(MaterialWidget widget, String cssClass, Input checkedInput) {
		GWT.log(input + " -- checkedInput: " + checkedInput);
		super.initializeSelectedMixin(widget, cssClass, input);
	}

	@Override
	protected void onInitialize() {
		label.setFor(input.getId());

		setCircle(true);

		checkmark.setResource(MaterialResources.INSTANCE.mdcCheckboxCheckmark());

		background.add(checkmark);
		background.add(mixedmark);

		checkbox.add(input);
		checkbox.add(background);

		add(checkbox);
		add(label);

		checkmark.setFillColor(Color.MDC_THEME_SECONDARY);

		super.onInitialize();
	}

	@Override
	public boolean isIndeterminate() {
		return indeterminateMixin.getValue();
	}

	@Override
	public void setIndeterminate(boolean value) {
		indeterminateMixin.setValue(value);
	};

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public void setRipple(Color color) {
		checkbox.setRipple(color);
	}

	@Override
	public Color getRipple() {
		return checkbox.getRipple();
	}

	@Override
	public void setCircle(boolean circle) {
		checkbox.setCircle(circle);
	}

	@Override
	public void setTextColor(Color color) {
		label.setTextColor(color);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return checkbox.addClickHandler(handler);
	}

	public void setSelectedColor(final Color color) {
		setCssProperty(CssMixin.MDC_CHECKBOX__CHECKED_COLOR, color.getCssName());
	}

	public void setUnselectedColor(final Color color) {
		setCssProperty(CssMixin.MDC_CHECKBOX__UNCHECKED_COLOR, color.getCssName());
	}
}
