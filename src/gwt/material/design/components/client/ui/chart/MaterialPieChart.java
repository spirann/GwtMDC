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

import java.util.Arrays;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.ChartAspectRatio;
import gwt.material.design.components.client.constants.ChartLabelDirection;
import gwt.material.design.components.client.constants.ChartLabelPosition;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.PieChartType;
import gwt.material.design.components.client.ui.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.chart.helper.ChartHelper;
import gwt.material.design.components.client.ui.chart.js.base.JsChartData;
import gwt.material.design.components.client.ui.chart.js.pie.JsPieChartClassNames;
import gwt.material.design.components.client.ui.chart.js.pie.JsPieChartOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialPieChart extends MaterialChartBase<Double, String, JsPieChartOptions>
		implements HasType<PieChartType> {

	private PieChartType type = PieChartType.PIE;

	protected AttributeMixin<MaterialPieChart> labelPositionMixin = new AttributeMixin<>(this, "label_position",
			"inside");

	public MaterialPieChart() {
		super(new JsPieChartOptions(), ChartAspectRatio.ASPECT_1x1);
	}

	@Override
	protected void initializedDefaultOptions() {
		super.initializedDefaultOptions();
		final JsPieChartClassNames classNames = new JsPieChartClassNames();
		classNames.chartPie = CssName.MDC_CHART__PIE_CHART;
		classNames.chartDonut = CssName.MDC_CHART__PIE_CHART__CHART_DONUT;
		classNames.slicePie = CssName.MDC_CHART__PIE_CHART__SLICE_PIE;
		classNames.sliceDonut = CssName.MDC_CHART__PIE_CHART__SLICE_DONUT;
		classNames.sliceDonutSolid = CssName.MDC_CHART__PIE_CHART__SLICE_DONUT_SOLID;
		classNames.label = CssName.MDC_CHART__PIE_CHART__LABEL;
		classNames.series = CssName.MDC_CHART__PIE_CHART__SERIES;
		options.classNames = classNames;

		options.chartPadding = 0;
		options.showLabel = true;
		options.donut = false;
		options.donutSolid = true;
		options.startAngle = 270;
		options.donutWidth = "36px";
		options.labelDirection = ChartLabelDirection.NEUTRAL.getCssName();
		options.labelPosition = ChartLabelPosition.INSIDE.getCssName();
		options.labelOffset = 0;
		options.ignoreEmptyValues = false;
		options.reverseData = false;
	}

	protected void calcTotal(final MaterialChartSerie<Double, String>[] value) {
		options.donut = true;
		if (value == null || value.length == 0) {
			options.total = 0;
		} else
			switch (getType()) {
			case GAUGE:
				options.total = Arrays.asList(value).stream().mapToDouble(serie -> serie.getValue()).sum() * 2;
				break;
			case PIE:
				options.donut = false;
			case DONUT:
			default:
				options.total = Arrays.asList(value).stream().mapToDouble(serie -> serie.getValue()).sum();
				break;
			}
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element, final JsChartData data,
			final JsPieChartOptions options)/*-{
		return new $wnd.Chartist.Pie(element, data, options);
	}-*/;

	@Override
	public void setValue(MaterialChartSerie<Double, String>[] value, boolean fireEvents) {
		calcTotal(value);
		super.setValue(value, fireEvents);
	}

	@Override
	public void setType(PieChartType type) {
		this.type = type;
		calcTotal(getValue());
		redraw();
	}

	@Override
	public PieChartType getType() {
		return type;
	}

	public boolean isIgnoreEmptyValues() {
		return options.ignoreEmptyValues;
	}

	public void setIgnoreEmptyValues(boolean ignoreEmptyValues) {
		options.ignoreEmptyValues = ignoreEmptyValues;
		redraw();
	}

	public String getDonutWidth() {
		return options.donutWidth;
	}

	public void setDonutWidth(String donutWidth) {
		options.donutWidth = donutWidth;
		redraw();
	}

	public ChartLabelPosition getLabelPosition() {
		return ChartLabelPosition.fromStyleName(options.labelPosition);
	}

	public void setLabelPosition(ChartLabelPosition labelPosition) {
		options.labelPosition = labelPosition == null ? ChartLabelPosition.INSIDE.getCssName() : labelPosition.getCssName();
		labelPositionMixin.setAttribute(options.labelPosition);
		redraw();
	}

	public ChartLabelDirection getLabelDirection() {
		return ChartLabelDirection.fromStyleName(options.labelDirection);
	}

	public void setLabelDirection(ChartLabelDirection labelDirection) {
		options.labelDirection = labelDirection == null ? ChartLabelDirection.NEUTRAL.getCssName()
				: labelDirection.getCssName();
		redraw();
	}

	public int getLabelOffset() {
		return options.labelOffset;
	}

	public void setLabelOffset(int labelOffset) {
		this.options.labelOffset = labelOffset;
		redraw();
	}

	public int getStartAngle() {
		return options.startAngle;
	}

	public void setStartAngle(int startAngle) {
		this.options.startAngle = startAngle;
		redraw();
	}

	public boolean isDonutSolid() {
		return this.options.donutSolid;
	}

	public void setDonutSolid(final boolean donutSolid) {
		this.options.donutSolid = donutSolid;
		redraw();
	}

	public int getChartPadding() {
		return options.startAngle;
	}

	public void setChartPadding(int padding) {
		options.chartPadding = padding;
		redraw();
	}

	/**
	 * 
	 * @param colors
	 *            Ex. "RED BLUE GREEN"
	 */
	public void setInsideLabelColors(String colors) {
		final String[] colorsArray = colors.split(" ");
		for (int i = 0; i < colorsArray.length; i++)
			setStyleProperty(CssMixin.MDC_CHARTIST__LABEL + "_" + ChartHelper.alphaNumerate(i),
					Color.valueOf(colorsArray[i]).getCssName());

	}

	public void setInsideColors(Color... colors) {
		for (int i = 0; i < colors.length; i++)
			setStyleProperty(CssMixin.MDC_CHARTIST__LABEL + "_" + ChartHelper.alphaNumerate(i), colors[i].getCssName());
	}
}
