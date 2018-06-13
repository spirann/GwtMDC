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

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasName;
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
import gwt.material.design.components.client.utils.JsUtils;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialRadioButton extends MaterialFormField<Boolean> implements HasName, HasText {

	// ////////////////////////////////////////////////////////////////
	// Control for change value in RadioButton group
	// Because the event is not trigger when another radio is selected
	// ////////////////////////////////////////////////////////////////
	protected final static Map<String, MaterialRadioButton> history = new HashMap<>();

	protected void putInHistory() {
		putInHistory(true);
	}

	protected void putInHistory(final boolean fireEvent) {

		final MaterialRadioButton old = history.get(getName());

		if (fireEvent && old != null && old != this) {
			old.fireChangeEvent();
		}

		history.put(getName(), this);

	}

	protected void removeFromHistory() {

		final MaterialRadioButton old = history.get(getName());

		if (old == this) {
			history.remove(getName());
		}

	}

	// /////////////////////////////////////////////////////////////
	// Radio
	// /////////////////////////////////////////////////////////////
	private Div radio = new Div(CssName.MDC_RADIO);
	private Input input = new Input(InputType.RADIO, CssName.MDC_RADIO_NATIVE_CONTROL);
	private Div divBackground = new Div(CssName.MDC_RADIO_BACKGROUND);
	private Div divOuterCircle = new Div(CssName.MDC_RADIO_OUTER_CIRCLE);
	private Div divInnerCircle = new Div(CssName.MDC_RADIO_INNER_CIRCLE);

	// /////////////////////////////////////////////////////////////
	// Label
	// /////////////////////////////////////////////////////////////
	private Label label = new Label();

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	protected final CheckedMixin<Input> checkedMixin = new CheckedMixin<>(input);

	public MaterialRadioButton() {
		super();
	}
	
	@Override
	protected void jsInit() {
		jsElement = jsInit(radio.getElement());
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.radio.MDCRadio(element);
	}-*/;

	@Override
	protected void onInitialize() {

		divBackground.add(divOuterCircle);
		divBackground.add(divInnerCircle);

		radio.setDisabledClass(CssName.MDC_RADIO_DISABLED);
		radio.add(input);
		radio.add(divBackground);

		add(radio);
		add(label);

		if (getValue()) {
			putInHistory();
		}

		setCircle(true);
		
		addValueChangeListener(input.getElement());
		
		addClickHandler(event -> JsUtils.clearFocus());

		super.onInitialize();
	}

	protected native void addValueChangeListener(Element element)/*-{
		
		var _this = this;
		element.addEventListener('change', function(event) {
			if (element.checked) {
				_this.@gwt.material.design.components.client.ui.MaterialRadioButton::putInHistory()();
			}
		});
		
	}-*/;

	@Override
	public void setId(String id) {
		input.setId(id);
		label.getElement().setAttribute("for", input.getId());
	}

	@Override
	public String getId() {
		return input.getId();
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		checkedMixin.setChecked(value);
		putInHistory(fireEvents);

		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public Boolean getValue() {
		return checkedMixin.isChecked();
	}

	@Override
	public void setName(String name) {
		input.setName(name);
	}

	@Override
	public String getName() {
		return input.getName();
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
		radio.setRipple(ripple);
	}

	@Override
	public Ripple getRipple() {
		return radio.getRipple();
	}

	@Override
	public void setCircle(boolean circle) {
		radio.setCircle(circle);
	}

	@Override
	public void setTextColor(Color color) {
		label.setTextColor(color);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return radio.addClickHandler(handler);
	}
}
