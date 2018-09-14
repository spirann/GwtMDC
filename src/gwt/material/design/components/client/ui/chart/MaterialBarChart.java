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

import gwt.material.design.components.client.constants.ChartAspectRatio;
import gwt.material.design.components.client.constants.ChartAxisLabelPosition;
import gwt.material.design.components.client.constants.ChartStackMode;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.ui.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.chart.js.base.JsAxis;
import gwt.material.design.components.client.ui.chart.js.base.JsChartData;
import gwt.material.design.components.client.ui.chart.js.base.JsChartPadding;
import gwt.material.design.components.client.ui.chart.js.bar.JsBarChartClassNames;
import gwt.material.design.components.client.ui.chart.js.bar.JsBarChartOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialBarChart extends MaterialChartBase<Double[], String[], JsBarChartOptions> {

	public MaterialBarChart() {
		super(new JsBarChartOptions(), ChartAspectRatio.ASPECT_3x4);

		final JsBarChartClassNames classNames = new JsBarChartClassNames();
		classNames.chart = "ct-chart-bar";
		classNames.label = "ct-label";
		classNames.horizontalBars = "ct-horizontal-bars";
		classNames.series = "ct-series";
		classNames.labelGroup = "ct-labels";
		classNames.bar = "ct-bar";
		classNames.grid = "ct-grid";
		classNames.gridGroup = "ct-grids";
		classNames.gridBackground = "ct-grid-background";
		classNames.vertical = "ct-vertical";
		classNames.horizontal = "ct-horizontal";
		classNames.start = "ct-start";
		classNames.end = "ct-end";
		options.classNames = classNames;

		this.options.chartPadding = new JsChartPadding();
		this.options.chartPadding.top = 0;
		this.options.chartPadding.right = 0;
		this.options.chartPadding.bottom = 0;
		this.options.chartPadding.left = 0;
		
		this.options.referenceValue = 0;		
		this.options.showGridBackground = false;
		this.options.referenceValue = 0;
		this.options.distributeSeries = false;
		this.options.horizontalBars = false;
		this.options.seriesBarDistance = 15;
		this.options.stackBars = false;
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


	public int getReferenceValue() {
		return this.options.referenceValue;
	}

	public void setReferenceValue(int referenceValue) {
		this.options.referenceValue = referenceValue;
		redraw();
	}

	public boolean isDistributeSeries() {
		return this.options.distributeSeries;
	}

	public void setDistributeSeries(boolean distributeSeries) {
		this.options.distributeSeries = distributeSeries;
		redraw();
	}

	public boolean isHorizontalBars() {
		return this.options.horizontalBars;
	}

	public void setHorizontalBars(boolean horizontalBars) {
		this.options.horizontalBars = horizontalBars;
		redraw();
	}

	public int getSeriesBarDistance() {
		return this.options.seriesBarDistance;
	}

	public void setSeriesBarDistance(int seriesBarDistance) {
		this.options.seriesBarDistance = seriesBarDistance;
		redraw();
	}

	public boolean isStackBars() {
		return this.options.stackBars;
	}

	public void setStackBars(boolean stackBars) {
		this.options.stackBars = stackBars;
		redraw();
	}

	public ChartStackMode getStackMode() {
		return ChartStackMode.fromStyleName(this.options.stackMode);
	}

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
		redraw();
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
		redraw();
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPaddingTop(final int padding) {
		options.chartPadding.top = padding;
		redraw();
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPaddingRight(final int padding) {
		options.chartPadding.right = padding;
		redraw();
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPaddingBottom(final int padding) {
		options.chartPadding.bottom = padding;
		redraw();
	}

	/**
	 * Padding of the chart drawing area to the container element and labels
	 * 
	 * @param padding
	 */
	public void setChartPaddingLeft(final int padding) {
		options.chartPadding.left = padding;
		redraw();
	}

	public void setGridBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_CHARTIST__GRID_BACKGROUND, color.getCssName());
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
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
		redraw();
	}
}