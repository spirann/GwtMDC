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
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.interfaces.HasDiscrete;
import gwt.material.design.components.client.base.interfaces.HasInputHandlers;
import gwt.material.design.components.client.base.interfaces.HasMarkers;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.InputEvent;
import gwt.material.design.components.client.events.InputEvent.InputHandler;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Span;

/**
 * Because MDCSlider updates its UI based on the values it reads in when it is
 * instantiated, there is potential for an incorrect first render before the
 * script containing the MDCSlider initialization logic executes.
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSlider extends MaterialValuedField<Double>
		implements HasInputHandlers<Double>, HasDiscrete, HasMarkers {

	// /////////////////////////////////////////////////////////////
	// Slider
	// /////////////////////////////////////////////////////////////
	protected final Div trackContainer = new Div(CssName.MDC_SLIDER__TRACK_CONTAINER);
	protected final Div track = new Div(CssName.MDC_SLIDER__TRACK);
	protected final Div markerContainer = new Div(CssName.MDC_SLIDER__TRACK_MARKER_CONTAINER);
	protected final Div thumbContainer = new Div(CssName.MDC_SLIDER__THUMB_CONTAINER);
	protected final Div focusRing = new Div(CssName.MDC_SLIDER__FOCUS_RING);
	protected final Div pin = new Div(CssName.MDC_SLIDER__PIN);
	protected final Span pinValueMarker = new Span(CssName.MDC_SLIDER__PIN_VALUE_MARKER);

	// /////////////////////////////////////////////////////////////
	// Mixins
	// /////////////////////////////////////////////////////////////
	protected final AttributeMixin<MaterialSlider, Double> valueminMixin = new AttributeMixin<>(this,
			CssAttribute.ARIA_VALUEMIN, 0.0, FromString.TO_DOUBLE);
	protected final AttributeMixin<MaterialSlider, Double> valuenowMixin = new AttributeMixin<>(this,
			CssAttribute.ARIA_VALUENOW, 5.0, FromString.TO_DOUBLE);
	protected final AttributeMixin<MaterialSlider, Double> valuemaxMixin = new AttributeMixin<>(this,
			CssAttribute.ARIA_VALUEMAX, 10.0, FromString.TO_DOUBLE);
	protected final AttributeMixin<MaterialSlider, Double> dataStepMixin = new AttributeMixin<>(this,
			CssAttribute.DATA_STEP, 1.0, FromString.TO_DOUBLE);
	protected final AttributeMixin<MaterialSlider, Boolean> enabledMixin = new AttributeMixin<>(this,
			CssAttribute.ARIA_DISABLED, FromString.TO_BOOLEAN);
	protected final ToggleStyleMixin<MaterialSlider> discreteMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_SLIDER__DISCRETE);
	protected final ToggleStyleMixin<MaterialSlider> markersMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_SLIDER__DISCRETE + " " + CssName.MDC_SLIDER__DISPLAY_MARKERS);

	public MaterialSlider() {
		super(CssName.MDC_SLIDER);
		setRole(Role.SLIDER);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.slider.MDCSlider(element);
	}-*/;

	@Override
	protected void onInitialize() {

		trackContainer.add(track);
		trackContainer.add(markerContainer);

		pin.add(pinValueMarker);

		thumbContainer.getElement().setInnerHTML(MaterialResources.INSTANCE.mdcSliderThumb().getText());
		thumbContainer.add(pin);
		thumbContainer.add(focusRing);

		add(trackContainer);
		add(thumbContainer);

		initializeChageEventListener();
		initializeInputEventListener();

		super.onInitialize();
		
		addResizeHandler(event -> layout());
	}

	protected native void initializeChageEventListener()/*-{
		var _this = this;
		var element = this.@gwt.material.design.components.client.ui.MaterialSlider::getElement()();
		var milliseconds = 0;

		function onChange() {
			var now = new Date().getTime();
			if ((now - milliseconds) > 100) {
				milliseconds = now;
				_this.@gwt.material.design.components.client.ui.MaterialSlider::fireChangeEvent()();
			}
		}

		element.addEventListener('MDCSlider:change', onChange);

	}-*/;

	protected native void initializeInputEventListener()/*-{
		var _this = this;
		var element = this.@gwt.material.design.components.client.ui.MaterialSlider::getElement()();
		var milliseconds = 0;

		function onInput() {
			var now = new Date().getTime();
			if ((now - milliseconds) > 100) {
				milliseconds = now;
				_this.@gwt.material.design.components.client.ui.MaterialSlider::draw()();
				_this.@gwt.material.design.components.client.ui.MaterialSlider::fireInputEvent()();
			}
		}

		element.addEventListener('MDCSlider:input', onInput);

	}-*/;

	protected native void draw()/*-{

		var pinMarker = this.@gwt.material.design.components.client.ui.MaterialSlider::pinValueMarker;
		var pinMarkerElement = pinMarker.@gwt.material.design.components.client.ui.html.Span::getElement()();
		var value = this.@gwt.material.design.components.client.ui.MaterialSlider::getValue()();

		var formattedValue = parseFloat(value).toFixed(0);
		pinMarkerElement.innerText = formattedValue;

		if (value < 100.0)
			pinMarkerElement.style.fontSize = "0.875rem";
		else if (value < 1000.0)
			pinMarkerElement.style.fontSize = "0.745rem";
		else if (value < 10000.0)
			pinMarkerElement.style.fontSize = "0.645rem";
		else
			pinMarkerElement.style.fontSize = "0.875rem"; // Default size

	}-*/;

	protected native void layout()/*-{
		var jsElement = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (jsElement)
			jsElement.layout();
	}-*/;

	protected native void nativeSetValue(final Double value)/*-{
		var slider = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		slider.value = value;
	}-*/;

	@Override
	public void setValue(Double value, boolean fireEvents) {
		valuenowMixin.setValue(value);
		super.setValue(value, fireEvents);
		if (initialized)
			nativeSetValue(value);
	}

	@Override
	public Double getValue() {
		return valuenowMixin.getValue();
	}

	public void setMin(final double min) {
		valueminMixin.setValue(min);
	}

	public double getMin() {
		return valueminMixin.getValue();
	}

	public void setMax(final double max) {
		valuemaxMixin.setValue(max);
	}

	public double getMax() {
		return valuemaxMixin.getValue();
	}

	protected void fireInputEvent() {
		InputEvent.fire(this, getValue());
	}

	@Override
	public HandlerRegistration addInputHandler(InputHandler<Double> handler) {
		return addHandler(handler, InputEvent.getType());
	}

	@Override
	public void setDiscrete(final boolean discrete) {
		discreteMixin.toggle(discrete);
	}

	@Override
	public boolean isDiscrete() {
		return discreteMixin.isApplied();
	}

	public void setStep(final Double step) {
		dataStepMixin.setValue(step);
	}

	public Double getStep() {
		return dataStepMixin.getValue();
	}

	@Override
	public void setMarkers(boolean markers) {
		markersMixin.toggle(markers);
	}

	@Override
	public boolean isMarkers() {
		return markersMixin.isApplied();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		enabledMixin.setValue(!enabled);
	}

	public void setTrackColor(final Color color) {
		setCssProperty(CssMixin.MDC_SLIDER__TRACK_COLOR, color.getCssName());
	}

	public void setTrackFillColor(final Color color) {
		setCssProperty(CssMixin.MDC_SLIDER__TRACK_FILL_COLOR, color.getCssName());
	}

	public void setTickMarkerColor(final Color color) {
		setCssProperty(CssMixin.MDC_SLIDER__TICK_MARKER_COLOR, color.getCssName());
	}

	public void setSliderThumbColor(final Color color) {
		setCssProperty(CssMixin.MDC_SLIDER__SLIDER_THUMB_COLOR, color.getCssName());
	}

	public void setPinColor(final Color color) {
		setCssProperty(CssMixin.MDC_SLIDER__PIN_COLOR, color.getCssName());
	}

	public void setValuePinColor(final Color color) {
		setCssProperty(CssMixin.MDC_SLIDER__VALUE_PIN_COLOR, color.getCssName());
	}
}
