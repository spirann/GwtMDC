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
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.widget.MaterialSelectedField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSwitch extends MaterialSelectedField implements HasText {

	// /////////////////////////////////////////////////////////////
	// Switch
	// /////////////////////////////////////////////////////////////
	protected Div switch_ = new Div(CssName.MDC_SWITCH);
	protected Div track = new Div(CssName.MDC_SWITCH__TRACK);
	protected Input input = new Input(InputType.CHECKBOX, CssName.MDC_SWITCH__NATIVE_CONTROL);
	protected Div thumbUnderlay = new Div(CssName.MDC_SWITCH__THUMB_UNDERLAY);
	protected Div thumb = new Div(CssName.MDC_SWITCH__THUMB);
	protected Label label = new Label(CssName.MDC_SWITCH__LABEL);

	public MaterialSwitch() {
		super(CssName.MDC_SWITCH_WRAPPER);
		super.initializeSelectedMixin(switch_, CssName.MDC_SWITCH__CHECKED, input);
	}	

	@Override
	protected void onInitialize() {
		
		input.setRole(Role.SWITCH);
		
		label.setFor(input.getId());

		thumb.add(input);
		thumbUnderlay.add(thumb);
				
		switch_.add(track);
		switch_.add(thumbUnderlay);

		add(switch_);
		add(label);
				
		super.onInitialize();
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{		
		var _this = this;			
		var switch_ = this.@gwt.material.design.components.client.ui.MaterialSwitch::switch_;
		var switch_element = switch_.@gwt.material.design.components.client.ui.html.Div::getElement()();		
		return new $wnd.mdc.switchControl.MDCSwitch(switch_element);
	}-*/;

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
		switch_.setRipple(color);
	}

	@Override
	public Color getRipple() {
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
	
	public void setSelectedColor(final Color color) {
		setCssProperty(CssMixin.MDC_SWITCH__CHECKED_COLOR, color.getCssName());
	}
	
	public void setUnselectedColor(final Color color) {
		setCssProperty(CssMixin.MDC_SWITCH__UNCHECKED_COLOR, color.getCssName());
	}
}
