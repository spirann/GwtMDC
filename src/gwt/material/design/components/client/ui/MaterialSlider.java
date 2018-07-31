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

import gwt.material.design.components.client.base.HasDiscrete;
import gwt.material.design.components.client.base.HasInputHandlers;
import gwt.material.design.components.client.base.HasMarkers;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.CssProperty;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.InputEvent;
import gwt.material.design.components.client.events.InputEvent.InputHandler;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Span;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSlider extends MaterialFormField<Double>
		implements HasInputHandlers<Double>, HasDiscrete, HasMarkers {

	// /////////////////////////////////////////////////////////////
	// Slider
	// /////////////////////////////////////////////////////////////	
	protected Div trackContainer = new Div(CssName.MDC_SLIDER__TRACK_CONTAINER);
	protected Div track = new Div(CssName.MDC_SLIDER__TRACK);	
	protected Div markerContainer = new Div(CssName.MDC_SLIDER__TRACK_MARKER_CONTAINER);	
	protected Div thumbContainer = new Div(CssName.MDC_SLIDER__THUMB_CONTAINER);	
	protected Div focusRing = new Div(CssName.MDC_SLIDER__FOCUS_RING);
	protected Div pin = new Div(CssName.MDC_SLIDER__PIN);
	protected Span pinValueMarker = new Span(CssName.MDC_SLIDER__PIN_VALUE_MARKER);
	
	// /////////////////////////////////////////////////////////////
	// Mixins
	// /////////////////////////////////////////////////////////////
	protected final AttributeMixin<MaterialSlider> valueminMixin = new AttributeMixin<>(this, "aria-valuemin");
	protected final AttributeMixin<MaterialSlider> valuenowMixin = new AttributeMixin<>(this, "aria-valuenow");
	protected final AttributeMixin<MaterialSlider> valuemaxMixin = new AttributeMixin<>(this, "aria-valuemax");
	protected final AttributeMixin<MaterialSlider> dataStepMixin = new AttributeMixin<>(this, "data-step");
	protected final AttributeMixin<MaterialSlider> enabledMixin = new AttributeMixin<>(this, "aria-disabled");
	protected final ApplyStyleMixin<MaterialSlider> discreteMixin = new ApplyStyleMixin<>(this, CssName.MDC_SLIDER__DISCRETE);
	protected final ApplyStyleMixin<MaterialSlider> markersMixin = new ApplyStyleMixin<>(this, CssName.MDC_SLIDER__DISCRETE + " " + CssName.MDC_SLIDER__DISPLAY_MARKERS);

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

		TimerHelper.schedule(1, () -> MaterialSlider.super.onInitialize());
		//super.onInitialize();

	}

	protected native void initializeChageEventListener()/*-{
		var _this = this;
		var element = this.@gwt.material.design.components.client.ui.MaterialSlider::getElement()();
		element.addEventListener('MDCSlider:change', function() {
			_this.@gwt.material.design.components.client.ui.MaterialSlider::fireChangeEvent()();
		});
	}-*/;

	protected native void initializeInputEventListener()/*-{
		var _this = this;
		var element = this.@gwt.material.design.components.client.ui.MaterialSlider::getElement()();
		element.addEventListener('MDCSlider:input', function() {											
			_this.@gwt.material.design.components.client.ui.MaterialSlider::draw()();
			_this.@gwt.material.design.components.client.ui.MaterialSlider::fireInputEvent()();
		});
	}-*/;
	
	protected native void draw()/*-{
	
		var pinMarker = this.@gwt.material.design.components.client.ui.MaterialSlider::pinValueMarker;
		var pinMarkerElement = pinMarker.@gwt.material.design.components.client.ui.html.Span::getElement()();
		var value = this.@gwt.material.design.components.client.ui.MaterialSlider::getValue()();
		
		var formattedValue = parseFloat(value).toFixed(0);
		pinMarkerElement.innerText = formattedValue;

		if (value < 100.0) {
			pinMarkerElement.style.fontSize = "0.875rem";
		} else if (value < 1000.0) {
			pinMarkerElement.style.fontSize = "0.745rem";
		} else if (value < 10000.0) {
			pinMarkerElement.style.fontSize = "0.645rem";
		} else {
			// Default size
			pinMarkerElement.style.fontSize = "0.875rem";
		}

	}-*/;
	
	protected native void layout()/*-{
		var slider = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		slider.layout();
	}-*/;
	
	protected native void nativeSetValue(final Double value)/*-{
		var slider = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		slider.value = value;
	}-*/;

	@Override
	public void setValue(Double value, boolean fireEvents) {
		valuenowMixin.setAttribute(value);
		super.setValue(value, fireEvents);
		if(initialized) {
			nativeSetValue(value);
		}
	}

	@Override
	public Double getValue() {
		return valuenowMixin.getAttributeAsDouble();
	}

	public void setMin(final double min) {
		valueminMixin.setAttribute(min);
	}

	public double getMin() {
		return valueminMixin.getAttributeAsDouble();
	}

	public void setMax(final double max) {
		valuemaxMixin.setAttribute(max);
	}

	public double getMax() {
		return valuemaxMixin.getAttributeAsDouble();
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
		discreteMixin.setApply(discrete);
	}

	@Override
	public boolean isDiscrete() {
		return discreteMixin.isApplied();
	}

	public void setStep(final Double step) {
		dataStepMixin.setAttribute(step);
	}

	public Double getStep() {
		return dataStepMixin.getAttributeAsDouble();
	}

	@Override
	public void setMarkers(boolean markers) {
		markersMixin.setApply(markers);
	}

	@Override
	public boolean isMarkers() {
		return markersMixin.isApplied();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		enabledMixin.setAttribute(!enabled);
	}
	
	@Override
	public void setTextColor(final Color color) {
		setStyleProperty(CssProperty.MDC_SLIDER__TEXT_COLOR, color.getCssName());
	}
	
	@Override
	public void setColor(Color color) {
		setStyleProperty(CssProperty.MDC_SLIDER__COLOR, color.getCssName());
	}

	@Override
	public void setBackgroundColor(Color color) {
		setStyleProperty(CssProperty.MDC_SLIDER__BACKGROUND_COLOR, color.getCssName());
	}
}
