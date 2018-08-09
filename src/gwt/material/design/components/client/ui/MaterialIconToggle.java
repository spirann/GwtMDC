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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Icon;
import gwt.material.design.components.client.utils.JsUtils;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialIconToggle extends Icon implements HasValue<Boolean> {

	protected final AttributeMixin<MaterialIconToggle> toggleOnMixin = new AttributeMixin<>(this, "data-toggle-on");
	protected final AttributeMixin<MaterialIconToggle> toggleOffMixin = new AttributeMixin<>(this, "data-toggle-off");
	protected final AttributeMixin<MaterialIconToggle> ariaPressedMixin = new AttributeMixin<>(this, "aria-pressed");

	protected Color colorOn = Color.MDC_THEME_SECONDARY;
	protected Color backgroundColorOn = Color.TRANSPARENT;

	protected Color colorOff = Color.MDC_THEME_TEXT_ICON_ON_BACKGROUND;
	protected Color backgroundColorOff = Color.TRANSPARENT;

	private boolean valueChangeHandlerInitialized;

	public MaterialIconToggle() {
		super(CssName.MDC_ICON_TOGGLE, CssName.MATERIAL_ICONS);
		setRole(Role.BUTTON);
		setCircle(true);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.iconToggle.MDCIconToggle(element);
	}-*/;

	
	@Override
	protected void onInitialize() {
		ripleMixin.initialize();
		initializeChageEventListener();
		updateColor();
		
		addClickHandler(event -> JsUtils.clearFocus());
		
		super.onInitialize();
	}

	public native void initializeChageEventListener()/*-{
		
		var _this = this;
		
		var element = this.@gwt.material.design.components.client.ui.MaterialIconToggle::getElement()();
		
		element.addEventListener('MDCIconToggle:change', function() {
			_this.@gwt.material.design.components.client.ui.MaterialIconToggle::updateColor()();
			_this.@gwt.material.design.components.client.ui.MaterialIconToggle::fireChangeEvent()();
		});
		
	}-*/;

	public void setToggleOn(final IconType icon) {
		toggleOnMixin.setAttribute("{\"content\": \"" + icon.getCssName() + "\"}");
	}

	public void setToggleOff(final IconType icon) {
		toggleOffMixin.setAttribute("{\"content\": \"" + icon.getCssName() + "\"}");
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void updateColor() {		
		if (getValue()) {
			setStyleProperty(CssMixin.MDC_ICON_TOGGLE__INK_COLOR, colorOn.getCssName());
			setBackgroundColor(backgroundColorOn);
		} else {
			setStyleProperty(CssMixin.MDC_ICON_TOGGLE__INK_COLOR, colorOff.getCssName());
			setBackgroundColor(backgroundColorOff);
		} 
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialIconToggle.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		// Initialization code
		if (!valueChangeHandlerInitialized) {
			valueChangeHandlerInitialized = true;
			addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					fireChangeEvent();
				}
			});
		}
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void setValue(final Boolean value) {
		setValue(value, true);
	}

	@Override
	public Boolean getValue() {
		return ariaPressedMixin.getAttributeAsBoolean();
	}

	@Override
	public void setValue(final Boolean value, boolean fireEvents) {
		this.ariaPressedMixin.setAttribute(value);
		if (fireEvents) {
			fireChangeEvent();
		}
	}

	public Color getColorOn() {
		return colorOn;
	}

	public void setColorOn(Color colorOn) {
		this.colorOn = colorOn;
	}

	public Color getColorOff() {
		return colorOff;
	}

	public void setColorOff(Color colorOff) {
		this.colorOff = colorOff;
	}

	public Color getBackgroundColorOn() {
		return backgroundColorOn;
	}

	public void setBackgroundColorOn(Color backgroundColorOn) {
		this.backgroundColorOn = backgroundColorOn;
	}

	public Color getBackgroundColorOff() {
		return backgroundColorOff;
	}

	public void setBackgroundColorOff(Color backgroundColorOff) {
		this.backgroundColorOff = backgroundColorOff;
	}
}
