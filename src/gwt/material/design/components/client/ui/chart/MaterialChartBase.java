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
import gwt.material.design.components.client.ui.chart.js.JsChartClassNames;
import gwt.material.design.components.client.ui.chart.js.JsChartOptions;

/**
 * 
 * https://gionkunz.github.io/chartist-js/getting-started.html
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialChartBase<V, O extends JsChartOptions> extends BaseWidget implements HasValue<V>, HasChartAspectRatio {

	private boolean valueChangeHandlerInitialized;
	
	private V value;
	
	protected O options;
	
	private boolean initialized = false;
	
	protected final ChartAspectRatioMixin<MaterialChartBase<V, O>> aspectRatioMixin = new ChartAspectRatioMixin<>(this);
	
	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	public MaterialChartBase(O options) {
		super();
		setElement(HtmlElements.DIV.createElement());
		
		this.options = options;		
		this.options.showLabel = true;
		this.options.classNames = new JsChartClassNames();
		this.options.classNames.chartPie = "ct-chart-pie";
		this.options.classNames.chartDonut = "ct-chart-donut";
		this.options.classNames.series = "ct-series";
		this.options.classNames.slicePie = "ct-slice-pie";
		this.options.classNames.sliceDonut = "ct-slice-donut";
		this.options.classNames.sliceDonutSolid = "ct-slice-donut-solid";
		this.options.classNames.label = "ct-label";
		
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
		jsElement = jsInit(getElement(), getValue(), options);
	}

	public JavaScriptObject asJavaScriptObject() {
		return jsElement;
	}

	protected JavaScriptObject jsInit(final Element element, final V value, final O options) {
		return element;
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialChartBase.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<V> handler) {
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
	public void setValue(V value) {
		setValue(value, true);
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public void setValue(V value, boolean fireEvents) {
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
	
	public void setLabelColors(String colors) {		
		final String[] colorsArray = colors.split(" ");		
		for(int i = 0; i < ThemeAttribute.MDC_CHARTIST__SERIES__LABEL.length && i < colorsArray.length;i++) {			
			setStyleProperty(ThemeAttribute.MDC_CHARTIST__SERIES__LABEL[i], Color.valueOf(colorsArray[i]).getCssName());			
		}
	}
	
	public void setLabelColors(Color... colors) {		
		for(int i = 0; i < ThemeAttribute.MDC_CHARTIST__SERIES__LABEL.length && i < colors.length;i++) {			
			setStyleProperty(ThemeAttribute.MDC_CHARTIST__SERIES__LABEL[i], colors[i].getCssName());			
		}
	}
}
