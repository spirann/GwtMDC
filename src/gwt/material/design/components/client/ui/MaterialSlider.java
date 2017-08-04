package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.MaterialFormField;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;

public class MaterialSlider extends MaterialFormField<Double> {

	// /////////////////////////////////////////////////////////////
	// Initialize Checkbox
	// /////////////////////////////////////////////////////////////
	public static native void sliderInit(Element element)/*-{
		$wnd.mdc.slider.MDCSlider.attachTo(element);
	}-*/;
	
	protected final AttributeMixin<MaterialSlider> valueminMixin = new AttributeMixin<>(this, "aria-valuemin");
	protected final AttributeMixin<MaterialSlider> valuenowMixin = new AttributeMixin<>(this, "aria-valuenow");
	protected final AttributeMixin<MaterialSlider> valuemaxMixin = new AttributeMixin<>(this, "aria-valuemax");

	// /////////////////////////////////////////////////////////////
	// Slider
	// /////////////////////////////////////////////////////////////
	protected Div slider = new Div(CssName.MDC_SLIDER);
	protected Div trackContainer = new Div(CssName.MDC_SLIDER_TRACK_CONTAINER);
	protected Div track = new Div(CssName.MDC_SLIDER_TRACK);
	protected Div thumbContainer = new Div(CssName.MDC_SLIDER_THUMB_CONTAINER);
	protected Div focusRing = new Div(CssName.MDC_SLIDER_FOCUS_RING);

	private boolean initialized = false;

	public MaterialSlider(){
		super();
		setRole("slider");
		setWidth("100%");
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			trackContainer.add(track);
			thumbContainer.getElement().setInnerHTML(MaterialResources.INSTANCE.mdcSliderThumb().getText());
			thumbContainer.add(focusRing);

			slider.add(trackContainer);
			slider.add(thumbContainer);

			add(slider);			
				
			sliderInit(slider.getElement());
			
			initialized = true;

			layout(slider.getElement());
		}
	}
	
	public native void layout(Element element)/*-{
		element.layout();
	}-*/;
	
	public native void setMin(Element element, double value)/*-{
		element.min = value;
	}-*/;
	
	public native void setMax(Element element, double value)/*-{
		element.max = value;
	}-*/;
	
	public native void setValue(Element element, double value)/*-{
		element.value = value;
	}-*/;

	@Override
	public void setValue(Double value, boolean fireEvents) {
		valuenowMixin.setAttribute(value);
		//setValue(slider.getElement(), value);
		super.setValue(value, fireEvents);
	}

	@Override
	public Double getValue() {
		return valuenowMixin.getAttributeAsDouble();
	}
	
	public void setMin(final Double min){
		valueminMixin.setAttribute(min);
		//setMin(slider.getElement(), min);
	}
	
	public void setMax(final Double max){
		valuemaxMixin.setAttribute(max);
		//setMax(slider.getElement(), max);
	}
}
