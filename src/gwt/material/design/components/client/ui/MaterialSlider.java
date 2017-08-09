package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.HasDiscrete;
import gwt.material.design.components.client.base.HasInputHandlers;
import gwt.material.design.components.client.base.HasMarkers;
import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.events.InputEvent;
import gwt.material.design.components.client.handlers.InputHandler;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialSlider extends MaterialFormField<Double>
		implements HasInputHandlers<Double>, HasDiscrete, HasMarkers {

	private boolean inputHandlerInitialized;

	// /////////////////////////////////////////////////////////////
	// Initialize Checkbox
	// /////////////////////////////////////////////////////////////
	public static native void sliderInit(Element element)/*-{
		$wnd.mdc.slider.MDCSlider.attachTo(element);
	}-*/;

	protected final AttributeMixin<MaterialSlider> valueminMixin = new AttributeMixin<>(this, "aria-valuemin", "0");
	protected final AttributeMixin<MaterialSlider> valuenowMixin = new AttributeMixin<>(this, "aria-valuenow", "0");
	protected final AttributeMixin<MaterialSlider> valuemaxMixin = new AttributeMixin<>(this, "aria-valuemax", "0");
	protected final AttributeMixin<MaterialSlider> dataStepMixin = new AttributeMixin<>(this, "data-step", "0");
	protected final ApplyStyleMixin<MaterialSlider> discreteMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_SLIDER_DISCRETE);
	protected final ApplyStyleMixin<MaterialSlider> markersMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_SLIDER_DISPLAY_MARKERS);

	// /////////////////////////////////////////////////////////////
	// Slider
	// /////////////////////////////////////////////////////////////
	protected Div trackContainer = new Div(CssName.MDC_SLIDER_TRACK_CONTAINER);
	protected Div track = new Div(CssName.MDC_SLIDER_TRACK);
	protected Div markerContainer = new Div(CssName.MDC_SLIDER_TRACK_MARKER_CONTAINER);
	protected Div thumbContainer = new Div(CssName.MDC_SLIDER_THUMB_CONTAINER);
	protected Div focusRing = new Div(CssName.MDC_SLIDER_FOCUS_RING);
	protected Div pin = new Div(CssName.MDC_SLIDER_PIN);
	protected Span pinMarker = new Span(CssName.MDC_SLIDER_PIN_VALUE_MARKER);

	private boolean initialized = false;

	public MaterialSlider() {
		super(CssName.MDC_SLIDER);
		setRole("slider");
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			trackContainer.add(track);
			trackContainer.add(markerContainer);

			pin.add(pinMarker);
			thumbContainer.getElement().setInnerHTML(MaterialResources.INSTANCE.mdcSliderThumb().getText());
			thumbContainer.add(focusRing);
			thumbContainer.add(pin);

			add(trackContainer);
			add(thumbContainer);

			addChageEvent(getElement());
			addInputEvent(getElement(), pinMarker.getElement());
			sliderInit(getElement());

			initialized = true;
		}
		
		//setupTrackMarker(getElement());
		
	}

	protected native void setupTrackMarker(Element element)/*-{
		element.setupTrackMarker();
	}-*/;	
	
	protected native void addChageEvent(Element element)/*-{
		var _this = this;
		element.addEventListener('MDCSlider:change', displayDate);
		function displayDate() {
			_this.@gwt.material.design.components.client.ui.MaterialSlider::fireChangeEvent()();
		}
	}-*/;

	protected native void addInputEvent(Element element, Element pinMarker)/*-{
		var _this = this;
		element.addEventListener('MDCSlider:input', displayDate);
		function displayDate() {
			pinMarker.innerText = parseFloat(
					_this.@gwt.material.design.components.client.ui.MaterialSlider::getValue()())
					.toFixed(0);
			_this.@gwt.material.design.components.client.ui.MaterialSlider::fireChangeEvent()();
		}
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
	}

	@Override
	public void setMarkers(boolean markers) {
		markersMixin.setApply(markers);
	}

	@Override
	public boolean isMarkers() {
		return markersMixin.isApplied();
	}
}
