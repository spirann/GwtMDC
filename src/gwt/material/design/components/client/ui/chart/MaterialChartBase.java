package gwt.material.design.components.client.ui.chart;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.BaseWidget;
import gwt.material.design.components.client.base.HasChartAspectRatio;
import gwt.material.design.components.client.base.mixin.ChartAspectRatioMixin;
import gwt.material.design.components.client.constants.ChartAspectRatio;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.ThemeAttribute;

public class MaterialChartBase extends BaseWidget implements HasValue<MaterialChartSerie[]>, HasChartAspectRatio {

	private boolean valueChangeHandlerInitialized;
	
	private MaterialChartSerie[] value;
	
	private boolean initialized = false;
	
	protected final ChartAspectRatioMixin<MaterialChartBase> aspectRatioMixin = new ChartAspectRatioMixin<>(this);
	
	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	public MaterialChartBase() {
		super();
		setElement(HtmlElements.DIV.createElement());
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		jsInit();
		initialized = true;
	}
	
	protected void redraw() {
		if(initialized) {
			jsInit();
		}
	}

	protected void jsInit() {
		jsElement = jsInit(getElement());
	}

	public JavaScriptObject asJavaScriptObject() {
		return jsElement;
	}

	protected JavaScriptObject jsInit(final Element element) {
		return element;
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialChartBase.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<MaterialChartSerie[]> handler) {
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
	public void setValue(MaterialChartSerie[] value) {
		setValue(value, true);
	}

	@Override
	public MaterialChartSerie[] getValue() {
		return value;
	}

	@Override
	public void setValue(MaterialChartSerie[] value, boolean fireEvents) {
		this.value = value;
		redraw();
		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public void setChartAspectRatio(ChartAspectRatio aspectRatio) {
		aspectRatioMixin.setChartAspectRatio(aspectRatio);
	}

	@Override
	public ChartAspectRatio getChartAspectRatio() {
		return aspectRatioMixin.getChartAspectRatio();
	}

	public void setColors(String colors) {		
		final String[] colorsArray = colors.split(" ");		
		for(int i = 0; i < ThemeAttribute.MDC_CHARTIST__SERIES.length && i < colorsArray.length;i++) {			
			setStyleProperty(ThemeAttribute.MDC_CHARTIST__SERIES[i], Color.valueOf(colorsArray[i]).getCssName());			
		}
	}
	
	public void setColors(Color... colors) {		
		for(int i = 0; i < ThemeAttribute.MDC_CHARTIST__SERIES.length && i < colors.length;i++) {			
			setStyleProperty(ThemeAttribute.MDC_CHARTIST__SERIES[i], colors[i].getCssName());			
		}
	}
}
