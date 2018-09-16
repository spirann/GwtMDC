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
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.chart.js.base.JsAxis;
import gwt.material.design.components.client.ui.chart.js.base.JsChartData;
import gwt.material.design.components.client.ui.chart.js.base.JsChartPadding;
import gwt.material.design.components.client.ui.chart.js.line.JsLineChartClassNames;
import gwt.material.design.components.client.ui.chart.js.line.JsLineChartOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLineChart extends MaterialChartBase<Double[], String[], JsLineChartOptions> {

	public MaterialLineChart() {
		super(new JsLineChartOptions(), ChartAspectRatio.ASPECT_1x3);
	}

	@Override
	protected void initializeDefaultOptions() {
		super.initializeDefaultOptions();
		final JsLineChartClassNames classNames = new JsLineChartClassNames();
		classNames.chart = CssName.MDC_CHART__LINE_CHART;
		classNames.label = CssName.MDC_CHART__LINE_CHART__LABEL;
		classNames.labelGroup = CssName.MDC_CHART__LINE_CHART__LABEL_GROUP;
		classNames.series = CssName.MDC_CHART__LINE_CHART__SERIES;
		classNames.line = CssName.MDC_CHART__LINE_CHART__LINE;
		classNames.point = CssName.MDC_CHART__LINE_CHART__POINT;
		classNames.area = CssName.MDC_CHART__LINE_CHART__AREA;
		classNames.grid = CssName.MDC_CHART__LINE_CHART__GRID;
		classNames.gridGroup = CssName.MDC_CHART__LINE_CHART__GRID_GROUP;
		classNames.gridBackground = CssName.MDC_CHART__LINE_CHART__GRID_BACKGROUND;
		classNames.vertical = CssName.MDC_CHART__LINE_CHART__VERTICAL;
		classNames.horizontal = CssName.MDC_CHART__LINE_CHART__HORIZONTAL;
		classNames.start = CssName.MDC_CHART__LINE_CHART__START;
		classNames.end = CssName.MDC_CHART__LINE_CHART__END;
		this.options.classNames = classNames;

		this.options.chartPadding = new JsChartPadding();
		this.options.chartPadding.top = 15;
		this.options.chartPadding.right = 15;
		this.options.chartPadding.bottom = 5;
		this.options.chartPadding.left = 10;

		this.options.fullWidth = true;
		this.options.lineSmooth = true;
		this.options.showArea = false;
		this.options.showPoint = true;
		this.options.showLine = true;
		this.options.areaBase = 0;
		this.options.showGridBackground = false;

		this.options.axisX = new JsAxis();
		this.options.axisX.showGrid = true;
		this.options.axisX.showLabel = true;
		this.options.axisX.offset = 30;
		this.options.axisX.position = ChartAxisLabelPosition.END.getCssName();

		this.options.axisY = new JsAxis();
		this.options.axisY.showGrid = true;
		this.options.axisY.showLabel = true;
		this.options.axisY.offset = 40;
		this.options.axisY.position = ChartAxisLabelPosition.START.getCssName();
		this.options.axisY.scaleMinSpace = 20;
		this.options.axisY.onlyInteger = false;
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element, final JsChartData data,
			final JsLineChartOptions options)/*-{
		return new $wnd.Chartist.Line(element, data, options);
	}-*/;

	public boolean isFullWidth() {
		return this.options.fullWidth;
	}

	public void setFullWidth(final boolean fullWidth) {
		this.options.fullWidth = fullWidth;
		redraw();
	}

	public boolean isLineSmooth() {
		return this.options.lineSmooth;
	}

	public void setLineSmooth(final boolean lineSmooth) {
		this.options.lineSmooth = lineSmooth;
		redraw();
	}

	public boolean isShowArea() {
		return this.options.showArea;
	}

	public void setShowArea(final boolean showArea) {
		this.options.showArea = showArea;
		redraw();
	}

	public boolean isShowPoint() {
		return this.options.showPoint;
	}

	public void setShowPoint(final boolean showPoint) {
		this.options.showPoint = showPoint;
		redraw();
	}

	public boolean isShowLine() {
		return this.options.showLine;
	}

	public void setShowLine(final boolean showLine) {
		this.options.showLine = showLine;
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
