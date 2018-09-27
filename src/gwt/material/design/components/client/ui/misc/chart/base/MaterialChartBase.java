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
package gwt.material.design.components.client.ui.misc.chart.base;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.interfaces.HasChartAspectRatio;
import gwt.material.design.components.client.base.mixin.ChartAspectRatioMixin;
import gwt.material.design.components.client.base.widget.BaseWidget;
import gwt.material.design.components.client.constants.ChartAspectRatio;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.misc.chart.helper.ChartHelper;
import gwt.material.design.components.client.ui.misc.chart.js.base.JsChartData;
import gwt.material.design.components.client.ui.misc.chart.js.base.JsChartOptions;
import gwt.material.design.components.client.utils.helper.JsHelper;

/**
 * 
 * https://gionkunz.github.io/chartist-js/getting-started.html
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialChartBase<V, L, O extends JsChartOptions> extends BaseWidget
		implements HasValue<MaterialChartSerie<V, L>[]>, HasChartAspectRatio {

	private boolean valueChangeHandlerInitialized;

	private MaterialChartSerie<V, L>[] value;

	protected O options;

	private boolean initialized = false;

	protected final ChartAspectRatioMixin<MaterialChartBase<V, L, O>> aspectRatioMixin = new ChartAspectRatioMixin<>(
			this);

	// /////////////////////////////////////////////////////////////
	// Plugings
	// /////////////////////////////////////////////////////////////
	private ChartValueFormatter formatter;
	private boolean showTooltip = false;

	// /////////////////////////////////////////////////////////////
	// Animation
	// /////////////////////////////////////////////////////////////
	private boolean animated = false;
	private int animationDuration = 250;

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	public MaterialChartBase(O options, final ChartAspectRatio defaultAspect) {
		super();
		this.options = options;
		setElement(HtmlElements.DIV.createElement());
		setChartAspectRatio(defaultAspect);
		initializeDefaultOptions();
	}

	protected void initializeDefaultOptions() {
		this.options.plugins = JsHelper.toJsArray(getPlugins().stream().toArray());
	}

	protected List<JavaScriptObject> getPlugins() {

		final List<JavaScriptObject> plugins = new LinkedList<>();

		final JavaScriptObject tooltipPlugin = loadTooltipPlugin(getElement(), options, showTooltip);
		if (tooltipPlugin != null) {
			plugins.add(tooltipPlugin);
		}

		return plugins;
	}

	protected final native JavaScriptObject loadTooltipPlugin(final Element element, final O options,
			final boolean addTooltipPlugin)/*-{

		var _this = this;

		var tooltipClass = @gwt.material.design.components.client.constants.CssName::MDC_CHART__TOOLTIP;
		@gwt.material.design.components.client.utils.helper.JsHelper::removeAllElementsByClassName(Ljava/lang/String;Lcom/google/gwt/dom/client/Element;)(tooltipClass, element);

		// Instantiete the plugin
		if (addTooltipPlugin) {

			// Find correct point class
			var pointClass;
			if (options.classNames.point)
				pointClass = options.classNames.point;
			else if (options.classNames.bar)
				pointClass = options.classNames.bar;
			else if (options.classNames.slicePie)
				pointClass = options.classNames.slicePie;
			else if (options.donut && !options.donutSolid
					&& options.classNames.sliceDonut)
				pointClass = options.classNames.sliceDonut;
			else if (options.donut && options.donutSolid
					&& options.classNames.sliceDonutSolid)
				pointClass = options.classNames.sliceDonutSolid;
			else if (options.classNames.slicePie)
				pointClass = options.classNames.slicePie;

			// Create a function to format the value
			var func = function(value) {
				return _this.@gwt.material.design.components.client.ui.misc.chart.base.MaterialChartBase::format(Ljava/lang/Double;)(value);
			};

			// Create the tooltip plugin
			var tooltipPlugin = $wnd.Chartist.plugins.tooltip({
				transformTooltipTextFnc : func,
				pointClass : pointClass
			});

			return tooltipPlugin;
		}

	}-*/;

	protected String format(final Double value) {
		return formatter == null ? String.valueOf(value) : formatter.format(value);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		jsInit();
		initialized = true;
	}

	protected void redraw() {
		if (initialized && jsElement != null) {
			this.options.plugins = JsHelper.toJsArray(getPlugins().stream().toArray());
			jsInit();
		}
	}

	protected native void clearEvents(final JavaScriptObject chart) /*-{
		chart.off();
	}-*/;

	protected native void update(final JavaScriptObject chart, final JsChartData data,
			final JsChartOptions options) /*-{
		chart.update(data, options, true);
	}-*/;

	protected native void detach(final JavaScriptObject chart) /*-{
		chart.detach();
	}-*/;

	protected void jsInit() {

		jsElement = jsInit(getElement(), getValue(), options);

		if (getValue() != null) {
			for (int v = 0; v < getValue().length; v++) {
				final Color color = getValue()[v].getColor();
				if (color != null) {
					setStyleProperty(CssMixin.MDC_CHARTIST__SERIES + "_" + ChartHelper.alphaNumerate(v),
							color.getCssName());
				}
			}
		}

		if (animated) {
			applyAnimations(jsElement, animationDuration);
		}

	}

	protected native void applyAnimations(final JavaScriptObject chart, final int duration) /*-{		
	}-*/;

	public JavaScriptObject asJavaScriptObject() {
		return jsElement;
	}

	protected JavaScriptObject jsInit(final Element element, final MaterialChartSerie<V, L>[] value, final O options) {
		return jsInit(element, value == null ? new JsChartData() : ChartHelper.toNativeData(value), options);
	}

	protected native JavaScriptObject jsInit(final Element element, final JsChartData data, final O options)/*-{
	}-*/;

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	protected void fireChangeEvent() {
		ValueChangeEvent.fire(MaterialChartBase.this, getValue());
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<MaterialChartSerie<V, L>[]> handler) {
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
	public void setValue(MaterialChartSerie<V, L>[] value) {
		setValue(value, true);
	}

	@Override
	public MaterialChartSerie<V, L>[] getValue() {
		return value;
	}

	@Override
	public void setValue(MaterialChartSerie<V, L>[] value, boolean fireEvents) {
		this.value = value;
		redraw();
		if (fireEvents) {
			fireChangeEvent();
		}
	}

	@Override
	public void setChartAspectRatio(ChartAspectRatio aspectRatio) {
		aspectRatioMixin.setChartAspectRatio(aspectRatio);
		redraw();
	}

	@Override
	public ChartAspectRatio getChartAspectRatio() {
		return aspectRatioMixin.getChartAspectRatio();
	}

	@Override
	public void setTextColor(Color color) {
		setStyleProperty(CssMixin.MDC_CHARTIST__LABEL, color.getCssName());
	}

	public void setColors(String colors) {
		final String[] colorsArray = colors.split(" ");
		for (int i = 0; i < colorsArray.length; i++) {
			setStyleProperty(CssMixin.MDC_CHARTIST__SERIES + "_" + ChartHelper.alphaNumerate(i),
					Color.valueOf(colorsArray[i]).getCssName());
		}
	}

	public void setColors(Color... colors) {
		for (int i = 0; i < colors.length; i++) {
			setStyleProperty(CssMixin.MDC_CHARTIST__SERIES + "_" + ChartHelper.alphaNumerate(i),
					colors[i].getCssName());
		}
	}

	public boolean isAnimated() {
		return animated;
	}

	public void setAnimated(boolean animated) {
		this.animated = animated;
		redraw();
	}

	/**
	 * Duration of the animation in milliseconds
	 * 
	 * @return
	 */
	public int getAnimationDuration() {
		return animationDuration;
	}

	/**
	 * Duration of the animation in milliseconds
	 * 
	 * @param animationDuration
	 */
	public void setAnimationDuration(int animationDuration) {
		this.animationDuration = animationDuration;
		redraw();
	}

	public boolean isShowLabel() {
		return options.showLabel;
	}

	public void setShowLabel(boolean showLabel) {
		options.showLabel = showLabel;
		redraw();
	}

	public Double getHigh() {
		return options.high;
	}

	public void setHigh(final Double high) {
		options.high = high;
		redraw();
	}

	public Double getLow() {
		return options.low;
	}

	public void setLow(final Double low) {
		options.low = low;
		redraw();
	}

	public boolean isReverseData() {
		return options.reverseData;
	}

	public void setReverseData(boolean reverseData) {
		options.reverseData = reverseData;
		redraw();
	}

	@Override
	public void setWidth(String width) {
		options.width = width;
		redraw();
	}

	@Override
	public void setHeight(String height) {
		options.height = height;
		redraw();
	}

	// ////////////////////////////////////////////////////////////////////
	// Plugin settings
	// ////////////////////////////////////////////////////////////////////

	// Tooltip

	public boolean isShowTooltip() {
		return showTooltip;
	}

	/**
	 * Show or not tooltips in points
	 * 
	 * @param showTooltip
	 */
	public void setShowTooltip(boolean showTooltip) {
		this.showTooltip = showTooltip;
		redraw();
	}

	/**
	 * Format tooltip value
	 * 
	 * @return
	 */
	public ChartValueFormatter getValueFormatter() {
		return formatter;
	}

	/**
	 * Format tooltip value
	 * 
	 * @param formatter
	 */
	public void setValueFormatter(ChartValueFormatter formatter) {
		this.formatter = formatter;
		if (isShowTooltip()) {
			redraw();
		}
	}
}
