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

import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.constants.ChartAspectRatio;
import gwt.material.design.components.client.constants.ChartAxisLabelPosition;
import gwt.material.design.components.client.constants.ChartStackMode;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.misc.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.misc.chart.js.bar.JsBarChartClassNames;
import gwt.material.design.components.client.ui.misc.chart.js.bar.JsBarChartOptions;
import gwt.material.design.components.client.ui.misc.chart.js.base.JsAxis;
import gwt.material.design.components.client.ui.misc.chart.js.base.JsChartData;
import gwt.material.design.components.client.ui.misc.chart.js.base.JsChartPadding;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialBarChart extends MaterialChartBase<Double[], String[], JsBarChartOptions> {

	private boolean showBarLabel = false;
	private String barLabelClass = CssName.MDC_CHART__LINE_CHART__LABEL;

	public MaterialBarChart() {
		super(new JsBarChartOptions(), ChartAspectRatio.ASPECT_1x3);
	}

	@Override
	protected void initializeDefaultOptions() {
		super.initializeDefaultOptions();
		final JsBarChartClassNames classNames = new JsBarChartClassNames();
		classNames.chart = CssName.MDC_CHART__BAR_CHART;
		classNames.label = CssName.MDC_CHART__BAR_CHART__LABEL;
		classNames.horizontalBars = CssName.MDC_CHART__BAR_CHART__HORIZONTAL_BARS;
		classNames.series = CssName.MDC_CHART__BAR_CHART__SERIES;
		classNames.labelGroup = CssName.MDC_CHART__BAR_CHART__LABEL_GROUP;
		classNames.bar = CssName.MDC_CHART__BAR_CHART__BAR;
		classNames.grid = CssName.MDC_CHART__BAR_CHART__GRID;
		classNames.gridGroup = CssName.MDC_CHART__BAR_CHART__GRID_GROUP;
		classNames.gridBackground = CssName.MDC_CHART__BAR_CHART__GRID_BACKGROUND;
		classNames.vertical = CssName.MDC_CHART__BAR_CHART__VERTICAL;
		classNames.horizontal = CssName.MDC_CHART__BAR_CHART__HORIZONTAL;
		classNames.start = CssName.MDC_CHART__BAR_CHART__START;
		classNames.end = CssName.MDC_CHART__BAR_CHART__END;
		options.classNames = classNames;

		this.options.chartPadding = new JsChartPadding();
		this.options.chartPadding.top = 15;
		this.options.chartPadding.right = 15;
		this.options.chartPadding.bottom = 5;
		this.options.chartPadding.left = 10;

		this.options.showGridBackground = false;
		this.options.distributeSeries = false;
		this.options.horizontalBars = false;
		this.options.stackBars = false;
		this.options.referenceValue = 0;
		this.options.seriesBarDistance = 15;
		this.options.stackMode = ChartStackMode.ACCUMULATE.getCssName();

		options.axisX = new JsAxis();
		options.axisX.showGrid = true;
		options.axisX.showLabel = true;
		options.axisX.offset = 30;
		options.axisX.position = ChartAxisLabelPosition.END.getCssName();
		options.axisX.scaleMinSpace = 30;
		options.axisX.onlyInteger = false;

		options.axisY = new JsAxis();
		options.axisY.showGrid = true;
		options.axisY.showLabel = true;
		options.axisY.offset = 40;
		options.axisY.position = ChartAxisLabelPosition.START.getCssName();
		options.axisY.scaleMinSpace = 20;
		options.axisY.onlyInteger = false;
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element, final JsChartData data,
			final JsBarChartOptions options)/*-{
		return new $wnd.Chartist.Bar(element, data, options);
	}-*/;

	@Override
	protected native void applyAnimations(final JavaScriptObject chart, final int durations) /*-{

		// Let's put a sequence number aside so we can use it in the event callbacks
		var seq = 0, delays = 40, count = 0;

		// Once the chart is fully created we reset the sequence
		chart.on('created', function() {
			seq = 0;
			count++;
		});

		chart.on('draw', function(data) {
			
			if (count > 0)
				return;

			seq++;

			if (data.type === 'label' && data.axis === 'x') {
				data.element.animate({
					y : {
						begin : seq * delays,
						dur : durations,
						from : data.y + 100,
						to : data.y,
						// We can specify an easing function from Chartist.Svg.Easing
						easing : 'easeOutQuart'
					}
				});
			} else if (data.type === 'label' && data.axis === 'y') {
				data.element.animate({
					x : {
						begin : seq * delays,
						dur : durations,
						from : data.x - 100,
						to : data.x,
						easing : 'easeOutQuart'
					}
				});
			} else if (data.type === 'bar' && data.x1 == data.x2) {
				// Vertical bars
				data.element.animate({
					y2 : {
						begin : seq * delays,
						dur : durations,
						from : data.y1,
						to : data.y2
					}
				});
			} else if (data.type === 'bar' && data.y1 == data.y2) {
				// Horizontal bars
				data.element.animate({
					x2 : {
						begin : seq * delays,
						dur : durations,
						from : data.x1,
						to : data.x2
					}
				});
			} else if (data.type === 'grid') {
				// Using data.axis we get x or y which we can use to construct our animation definition objects
				var pos1Animation = {
					begin : seq * delays,
					dur : durations,
					from : data[data.axis.units.pos + '1'] - 30,
					to : data[data.axis.units.pos + '1'],
					easing : 'easeOutQuart'
				};

				var pos2Animation = {
					begin : seq * delays,
					dur : durations,
					from : data[data.axis.units.pos + '2'] - 100,
					to : data[data.axis.units.pos + '2'],
					easing : 'easeOutQuart'
				};

				var animations = {};
				animations[data.axis.units.pos + '1'] = pos1Animation;
				animations[data.axis.units.pos + '2'] = pos2Animation;
				animations['opacity'] = {
					begin : seq * delays,
					dur : durations,
					from : 0,
					to : 1,
					easing : 'easeOutQuart'
				};

				data.element.animate(animations);
			}
		});

	}-*/;

	@Override
	protected List<JavaScriptObject> getPlugins() {
		final List<JavaScriptObject> plugins = super.getPlugins();

		if (showBarLabel)
			plugins.add(loadBarLabelPlugin(barLabelClass));

		return plugins;
	}

	protected native JavaScriptObject loadBarLabelPlugin(final String labelClass)/*-{

		var _this = this;
		var func = function(value) {
			return _this.@gwt.material.design.components.client.ui.misc.chart.base.MaterialChartBase::format(Ljava/lang/Double;)(value);
		};

		return new $wnd.Chartist.plugins.ctPointLabels({
			textAnchor : 'middle',
			labelClass : labelClass,
			labelInterpolationFnc : func
		});

	}-*/;

	public String getBarLabelClass() {
		return barLabelClass;
	}

	public void setBarLabelClass(String pointLabelClass) {
		this.barLabelClass = pointLabelClass;
		redraw(false);
	}

	public boolean isShowBarLabel() {
		return showBarLabel;
	}

	public void setShowBarLabel(boolean showPointLabel) {
		this.showBarLabel = showPointLabel;
		redraw(false);
	}

	/**
	 * Unless low/high are explicitly set, bar chart will be centered at zero by
	 * default. Set referenceValue to null to auto scale.
	 * 
	 * @return
	 */
	public double getReferenceValue() {
		return this.options.referenceValue;
	}

	/**
	 * Unless low/high are explicitly set, bar chart will be centered at zero by
	 * default. Set referenceValue to null to auto scale.
	 * 
	 * @param referenceValue
	 */
	public void setReferenceValue(double referenceValue) {
		this.options.referenceValue = referenceValue;
		redraw(false);
	}

	/**
	 * If set to true then each bar will represent a series and the data array is
	 * expected to be a one dimensional array of data values rather than a series
	 * array of series. This is useful if the bar chart should represent a profile
	 * rather than some data over time.
	 * 
	 * @return
	 */
	public boolean isDistributeSeries() {
		return this.options.distributeSeries;
	}

	/**
	 * If set to true then each bar will represent a series and the data array is
	 * expected to be a one dimensional array of data values rather than a series
	 * array of series. This is useful if the bar chart should represent a profile
	 * rather than some data over time.
	 * 
	 * @param distributeSeries
	 */
	public void setDistributeSeries(boolean distributeSeries) {
		this.options.distributeSeries = distributeSeries;
		redraw();
	}

	/**
	 * Inverts the axes of the bar chart in order to draw a horizontal bar chart. Be
	 * aware that you also need to invert your axis settings as the Y Axis will now
	 * display the labels and the X Axis the values.
	 * 
	 * @return
	 */
	public boolean isHorizontalBars() {
		return this.options.horizontalBars;
	}

	/**
	 * Inverts the axes of the bar chart in order to draw a horizontal bar chart. Be
	 * aware that you also need to invert your axis settings as the Y Axis will now
	 * display the labels and the X Axis the values.
	 * 
	 * @param horizontalBars
	 */
	public void setHorizontalBars(boolean horizontalBars) {
		this.options.horizontalBars = horizontalBars;
		redraw();
	}

	/**
	 * Specify the distance in pixel of bars in a group
	 * 
	 * @return
	 */
	public int getSeriesBarDistance() {
		return this.options.seriesBarDistance;
	}

	/**
	 * Specify the distance in pixel of bars in a group
	 * 
	 * @param seriesBarDistance
	 */
	public void setSeriesBarDistance(int seriesBarDistance) {
		this.options.seriesBarDistance = seriesBarDistance;
		redraw(false);
	}

	/**
	 * If set to true this property will cause the series bars to be stacked. Check
	 * the `stackMode` option for further stacking options.
	 * 
	 * @return
	 */
	public boolean isStackBars() {
		return this.options.stackBars;
	}

	/**
	 * If set to true this property will cause the series bars to be stacked. Check
	 * the `stackMode` option for further stacking options.
	 * 
	 * @param stackBars
	 */
	public void setStackBars(boolean stackBars) {
		this.options.stackBars = stackBars;
		redraw();
	}

	/**
	 * If set to 'overlap' this property will force the stacked bars to draw from
	 * the zero line.
	 * 
	 * If set to 'accumulate' this property will form a total for each series point.
	 * This will also influence the y-axis and the overall bounds of the chart. In
	 * stacked mode the seriesBarDistance property will have no effect.
	 * 
	 * @return
	 */
	public ChartStackMode getStackMode() {
		return ChartStackMode.fromStyleName(this.options.stackMode);
	}

	/**
	 * If set to 'overlap' this property will force the stacked bars to draw from
	 * the zero line.
	 * 
	 * If set to 'accumulate' this property will form a total for each series point.
	 * This will also influence the y-axis and the overall bounds of the chart. In
	 * stacked mode the seriesBarDistance property will have no effect.
	 * 
	 * @param stackMode
	 */
	public void setStackMode(ChartStackMode stackMode) {
		this.options.stackMode = stackMode.getCssName();
		redraw();
	}

	/**
	 * If the bar chart should add a background fill.
	 * 
	 * @return
	 */
	public boolean isShowGridBackground() {
		return this.options.showGridBackground;
	}

	/**
	 * If the bar chart should add a background fill.
	 * 
	 * @param color
	 */
	public void setShowGridBackground(final boolean showGridBackground) {
		this.options.showGridBackground = showGridBackground;
		redraw(false);
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPadding(final int padding) {
		options.chartPadding.top = padding;
		options.chartPadding.right = padding;
		options.chartPadding.bottom = padding;
		options.chartPadding.left = padding;
		redraw(false);
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPaddingTop(final int padding) {
		options.chartPadding.top = padding;
		redraw(false);
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPaddingRight(final int padding) {
		options.chartPadding.right = padding;
		redraw(false);
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPaddingBottom(final int padding) {
		options.chartPadding.bottom = padding;
		redraw(false);
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPaddingLeft(final int padding) {
		options.chartPadding.left = padding;
		redraw(false);
	}

	public void setGridBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_CHARTIST__GRID_BACKGROUND, color.getCssName());
	}

	// ////////////////////////////////////////////////////////////////////
	// Axix X settings
	// ////////////////////////////////////////////////////////////////////

	/**
	 * If labels should be shown or not
	 * 
	 * @return
	 */
	public boolean isAxisXShowLabel() {
		return options.axisX.showLabel;
	}

	/**
	 * If labels should be shown or not
	 * 
	 * @param showLabel
	 */
	public void setAxisXShowLabel(boolean showLabel) {
		options.axisX.showLabel = showLabel;
		redraw(false);
	}

	/**
	 * If the axis grid should be drawn or not
	 * 
	 * @return
	 */
	public boolean isAxisXShowGrid() {
		return options.axisX.showGrid;
	}

	/**
	 * If the axis grid should be drawn or not
	 * 
	 * @param showGrid
	 */
	public void setAxisXShowGrid(boolean showGrid) {
		options.axisX.showGrid = showGrid;
		redraw(false);
	}

	/**
	 * Use only integer values (whole numbers) for the scale steps
	 * 
	 * @return
	 */
	public boolean isAxisXOnlyInteger() {
		return options.axisX.onlyInteger;
	}

	/**
	 * Use only integer values (whole numbers) for the scale steps
	 * 
	 * @param onlyInteger
	 */
	public void setAxisXOnlyInteger(boolean onlyInteger) {
		options.axisX.onlyInteger = onlyInteger;
		redraw(false);
	}

	/**
	 * The offset of the chart drawing area to the border of the container
	 * 
	 * @return
	 */
	public int getAxisXOffset() {
		return options.axisX.offset;
	}

	/**
	 * The offset of the chart drawing area to the border of the container
	 * 
	 * @param offset
	 */
	public void setAxisXOffset(int offset) {
		options.axisX.offset = offset;
		redraw(false);
	}

	/**
	 * Position where labels are placed. Can be set to `start` or `end` where
	 * `start` is equivalent to left or top on vertical axis and `end` is equivalent
	 * to right or bottom on horizontal axis.
	 * 
	 * @return
	 */
	public ChartAxisLabelPosition getAxisXPosition() {
		return ChartAxisLabelPosition.fromStyleName(options.axisX.position);
	}

	/**
	 * Position where labels are placed. Can be set to `start` or `end` where
	 * `start` is equivalent to left or top on vertical axis and `end` is equivalent
	 * to right or bottom on horizontal axis.
	 * 
	 * @param position
	 */
	public void setAxisXPosition(ChartAxisLabelPosition position) {
		options.axisX.position = position.getCssName();
		redraw(false);
	}

	/**
	 * This value specifies the minimum height in pixel of the scale steps
	 * 
	 * @return
	 */
	public int getAxisXScaleMinSpace() {
		return options.axisX.scaleMinSpace;
	}

	/**
	 * This value specifies the minimum height in pixel of the scale steps
	 * 
	 * @param scaleMinSpace
	 */
	public void setAxisXScaleMinSpace(int scaleMinSpace) {
		options.axisX.scaleMinSpace = scaleMinSpace;
		redraw(false);
	}

	// ////////////////////////////////////////////////////////////////////
	// Axix Y settings
	// ////////////////////////////////////////////////////////////////////

	/**
	 * If labels should be shown or not
	 * 
	 * @return
	 */
	public boolean isAxisYShowLabel() {
		return options.axisY.showLabel;
	}

	/**
	 * If labels should be shown or not
	 * 
	 * @param showLabel
	 */
	public void setAxisYShowLabel(boolean showLabel) {
		options.axisY.showLabel = showLabel;
		redraw(false);
	}

	/**
	 * If the axis grid should be drawn or not
	 * 
	 * @return
	 */
	public boolean isAxisYShowGrid() {
		return options.axisY.showGrid;
	}

	/**
	 * If the axis grid should be drawn or not
	 * 
	 * @param showGrid
	 */
	public void setAxisYShowGrid(boolean showGrid) {
		options.axisY.showGrid = showGrid;
		redraw(false);
	}

	/**
	 * Use only integer values (whole numbers) for the scale steps
	 * 
	 * @return
	 */
	public boolean isAxisYOnlyInteger() {
		return options.axisY.onlyInteger;
	}

	/**
	 * Use only integer values (whole numbers) for the scale steps
	 * 
	 * @param onlyInteger
	 */
	public void setAxisYOnlyInteger(boolean onlyInteger) {
		options.axisY.onlyInteger = onlyInteger;
		redraw(false);
	}

	/**
	 * The offset of the chart drawing area to the border of the container
	 * 
	 * @return
	 */
	public int getAxisYOffset() {
		return options.axisY.offset;
	}

	/**
	 * The offset of the chart drawing area to the border of the container
	 * 
	 * @param offset
	 */
	public void setAxisYOffset(int offset) {
		options.axisY.offset = offset;
		redraw(false);
	}

	/**
	 * Position where labels are placed. Can be set to `start` or `end` where
	 * `start` is equivalent to left or top on vertical axis and `end` is equivalent
	 * to right or bottom on horizontal axis.
	 * 
	 * @return
	 */
	public ChartAxisLabelPosition getAxisYPosition() {
		return ChartAxisLabelPosition.fromStyleName(options.axisY.position);
	}

	/**
	 * Position where labels are placed. Can be set to `start` or `end` where
	 * `start` is equivalent to left or top on vertical axis and `end` is equivalent
	 * to right or bottom on horizontal axis.
	 * 
	 * @param position
	 */
	public void setAxisYPosition(ChartAxisLabelPosition position) {
		options.axisY.position = position.getCssName();
		redraw(false);
	}

	/**
	 * This value specifies the minimum height in pixel of the scale steps
	 * 
	 * @return
	 */
	public int getAxisYScaleMinSpace() {
		return options.axisY.scaleMinSpace;
	}

	/**
	 * This value specifies the minimum height in pixel of the scale steps
	 * 
	 * @param scaleMinSpace
	 */
	public void setAxisYScaleMinSpace(int scaleMinSpace) {
		options.axisY.scaleMinSpace = scaleMinSpace;
		redraw(false);
	}
}
