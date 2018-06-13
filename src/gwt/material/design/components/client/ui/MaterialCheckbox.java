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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasIndeterminate;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.CheckedMixin;
import gwt.material.design.components.client.base.mixin.IndeterminateMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.utils.JsUtils;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCheckbox extends MaterialFormField<Boolean> implements HasText, HasIndeterminate {

	// /////////////////////////////////////////////////////////////
	// Checkbox
	// /////////////////////////////////////////////////////////////
	protected Div checkbox = new Div(CssName.MDC_CHECKBOX);
	protected Input input = new Input(InputType.CHECKBOX, CssName.MDC_CHECKBOX__NATIVE_CONTROL);
	protected Div background = new Div(CssName.MDC_CHECKBOX__BACKGROUND);
	protected MaterialSvg checkmark = new MaterialSvg();
	protected Div mixedmark = new Div(CssName.MDC_CHECKBOX__MIXEDMARK);
	protected Label label = new Label();

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final CheckedMixin<Input> checkedMixin = new CheckedMixin<>(input);
	protected final IndeterminateMixin<Input> indeterminateMixin = new IndeterminateMixin<>(input);

	public MaterialCheckbox() {
		super();
	}

	@Override
	protected void jsInit() {
		super.jsInit();
		final JavaScriptObject inputElement = getInputElemente(checkbox.getElement());
		setInputElemente(jsElement, inputElement);
	}

	protected native JavaScriptObject getInputElemente(final Element element)/*-{
		return new $wnd.mdc.checkbox.MDCCheckbox(element);
	}-*/;
	
	protected native void setInputElemente(final JavaScriptObject element, final JavaScriptObject input)/*-{
		element.input = input;		
	}-*/;

	@Override
	protected void onInitialize() {
		
		setCircle(true);
		
		checkmark.addStyleName(CssName.MDC_CHECKBOX__CHECKMARK_PATH);
		checkmark.setResource(MaterialResources.INSTANCE.mdcCheckboxCheckmark());
				
		background.add(checkmark);
		background.add(mixedmark);
				
		checkbox.setDisabledClass(CssName.MDC_CHECKBOX__DISABLED);
		checkbox.add(input);
		checkbox.add(background);
			
		add(checkbox);
		add(label);
		
		checkmark.setFillColor(Color.MDC_THEME_SECONDARY);
		
		addClickHandler(event -> JsUtils.clearFocus());
		
		super.onInitialize();
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
	public boolean isIndeterminate() {
		return indeterminateMixin.isIndeterminate();
	}

	@Override
	public void setIndeterminate(boolean value) {
		indeterminateMixin.setIndeterminate(value);
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
	public void setRipple(Ripple ripple) {
		checkbox.setRipple(ripple);
	}

	@Override
	public Ripple getRipple() {
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
}
