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

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.ui.form.MaterialSelectedField;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialRadioButton extends MaterialSelectedField implements HasName, HasText {

	// ////////////////////////////////////////////////////////////////
	// Control for change value in RadioButton group
	// Because the event is not trigger when another radio is selected
	// ////////////////////////////////////////////////////////////////
	protected final static Map<String, MaterialRadioButton> history = new HashMap<>();

	protected void updateHistory() {
		if (isSelected()) {
			putInHistory(true);
		} else {
			removeFromHistory();
		}
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
	private Input input = new Input(InputType.RADIO, CssName.MDC_RADIO__NATIVE_CONTROL);
	private Div divBackground = new Div(CssName.MDC_RADIO__BACKGROUND);
	private Div divOuterCircle = new Div(CssName.MDC_RADIO__OUTER_CIRCLE);
	private Div divInnerCircle = new Div(CssName.MDC_RADIO__INNER_CIRCLE);
	private Label label = new Label(CssName.MDC_RADIO__LABEL);

	public MaterialRadioButton() {
		super(CssName.MDC_FORM_FIELD);
		super.initializeSelectedMixin(radio, CssName.MDC_RADIO__SELECTED, input);
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
		label.setFor(input.getId());
		
		divBackground.add(divOuterCircle);
		divBackground.add(divInnerCircle);

		radio.add(input);
		radio.add(divBackground);

		add(radio);
		add(label);

		if (isSelected()) {
			updateHistory();
		}

		setCircle(true);

		super.onInitialize();
	}
	
	@Override
	protected void fireChangeEvent() {
		updateHistory();
		super.fireChangeEvent();
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
	public void setRipple(Color color) {
		radio.setRipple(color);
	}

	@Override
	public Color getRipple() {
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
	
	public void setSelectedColor(final Color color) {
		setStyleProperty(CssMixin.MDC_RADIO_BUTTON__CHECKED_COLOR, color.getCssName());
	}
	
	public void setUnselectedColor(final Color color) {
		setStyleProperty(CssMixin.MDC_RADIO_BUTTON__UNCHECKED_COLOR, color.getCssName());
	}
}
