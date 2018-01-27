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
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.HasDiscrete;
import gwt.material.design.components.client.base.HasInputHandlers;
import gwt.material.design.components.client.base.HasMarkers;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.InputEvent;
import gwt.material.design.components.client.handlers.InputHandler;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSlider extends MaterialFormField<Double>
		implements HasInputHandlers<Double>, HasDiscrete, HasMarkers {

	private boolean inputHandlerInitialized;

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialSlider::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialSlider::jsElement = $wnd.mdc.slider.MDCSlider
				.attachTo(element);
	}-*/;

	protected final AttributeMixin<MaterialSlider> valueminMixin = new AttributeMixin<>(this, "aria-valuemin", "0");
	protected final AttributeMixin<MaterialSlider> valuenowMixin = new AttributeMixin<>(this, "aria-valuenow", "0");
	protected final AttributeMixin<MaterialSlider> valuemaxMixin = new AttributeMixin<>(this, "aria-valuemax", "0");
	protected final AttributeMixin<MaterialSlider> dataStepMixin = new AttributeMixin<>(this, "data-step", "0");
	protected final AttributeMixin<MaterialSlider> enabledMixin = new AttributeMixin<>(this, "aria-disabled", "false");
	protected final ApplyStyleMixin<MaterialSlider> discreteMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_SLIDER_DISCRETE);
	protected final ApplyStyleMixin<MaterialSlider> markersMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_SLIDER_DISPLAY_MARKERS);

	// /////////////////////////////////////////////////////////////
	// Slider
	// /////////////////////////////////////////////////////////////
	protected Div slider = new Div(CssName.MDC_SLIDER);
	protected Div trackContainer = new Div(CssName.MDC_SLIDER_TRACK_CONTAINER);
	protected Div track = new Div(CssName.MDC_SLIDER_TRACK);
	protected Div markerContainer = new Div(CssName.MDC_SLIDER_TRACK_MARKER_CONTAINER);
	protected Div thumbContainer = new Div(CssName.MDC_SLIDER_THUMB_CONTAINER);
	protected Div focusRing = new Div(CssName.MDC_SLIDER_FOCUS_RING);
	protected Div pin = new Div(CssName.MDC_SLIDER_PIN);
	protected Span pinMarker = new Span(CssName.MDC_SLIDER_PIN_VALUE_MARKER);

	public MaterialSlider() {
		super();
		setRole(Role.SLIDER);
		setWidth("100%");
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		trackContainer.add(track);
		trackContainer.add(markerContainer);

		pin.add(pinMarker);
		thumbContainer.getElement().setInnerHTML(MaterialResources.INSTANCE.mdcSliderThumb().getText());
		thumbContainer.add(focusRing);
		thumbContainer.add(pin);

		slider.add(trackContainer);
		slider.add(thumbContainer);
		add(slider);

		initializeChageEventListener();
		initializeInputEventListener();
		jsInit();

		setupTrackMarker();

	}

	protected void setupTrackMarker() {

		if (initialized) {

			final int count = (int) ((getMax() - getMin()) / getStep());
			markerContainer.clear();
			if (isMarkers()) {
				for (int i = 0; i < count; i++) {
					final Div div = new Div(CssName.MDC_SLIDER_TRACK_MARKER);
					markerContainer.add(div);
				}
			}

		}

	}

	protected native void initializeChageEventListener()/*-{
		var _this = this;
		var element = this.@gwt.material.design.components.client.ui.MaterialSlider::getElement()();
		element
				.addEventListener(
						'MDCSlider:change',
						function() {
							_this.@gwt.material.design.components.client.ui.MaterialSlider::fireChangeEvent()();
						});
	}-*/;

	protected native void initializeInputEventListener()/*-{
		var _this = this;
		var element = this.@gwt.material.design.components.client.ui.MaterialSlider::getElement()();

		var pinMarker = this.@gwt.material.design.components.client.ui.MaterialSlider::pinMarker;
		var pinMarkerElement = pinMarker.@gwt.material.design.components.client.ui.html.Span::getElement()();

		element
				.addEventListener(
						'MDCSlider:input',
						function() {
							pinMarkerElement.innerText = parseFloat(
									_this.@gwt.material.design.components.client.ui.MaterialSlider::getValue()())
									.toFixed(0);
							_this.@gwt.material.design.components.client.ui.MaterialSlider::fireChangeEvent()();
						});
	}-*/;

	@Override
	public void setValue(Double value, boolean fireEvents) {
		valuenowMixin.setAttribute(value);
		super.setValue(value, fireEvents);
	}

	@Override
	public Double getValue() {
		return valuenowMixin.getAttributeAsDouble();
	}

	public void setMin(final double min) {
		valueminMixin.setAttribute(min);
		setupTrackMarker();
	}

	public double getMin() {
		return valueminMixin.getAttributeAsDouble();
	}

	public void setMax(final double max) {
		valuemaxMixin.setAttribute(max);
		setupTrackMarker();
	}

	public double getMax() {
		return valuemaxMixin.getAttributeAsDouble();
	}

	@Override
	public HandlerRegistration addInputHandler(InputHandler<Double> handler) {

		if (!inputHandlerInitialized) {
			inputHandlerInitialized = true;
			addHandler(new InputHandler<Double>() {
				public void onInput(InputEvent<Double> event) {
					InputEvent.fire(MaterialSlider.this, getValue());
				}
			}, InputEvent.getType());
		}

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
		setupTrackMarker();
	}

	public Double getStep() {
		return dataStepMixin.getAttributeAsDouble();
	}

	@Override
	public void setMarkers(boolean markers) {
		markersMixin.setApply(markers);
		setupTrackMarker();
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
}
