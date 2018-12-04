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

import java.util.Arrays;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.constants.ChartAspectRatio;
import gwt.material.design.components.client.constants.ChartLabelDirection;
import gwt.material.design.components.client.constants.ChartLabelPosition;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.PieChartType;
import gwt.material.design.components.client.ui.misc.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.misc.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.misc.chart.helper.ChartHelper;
import gwt.material.design.components.client.ui.misc.chart.js.base.JsChartData;
import gwt.material.design.components.client.ui.misc.chart.js.pie.JsPieChartClassNames;
import gwt.material.design.components.client.ui.misc.chart.js.pie.JsPieChartOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialPieChart extends MaterialChartBase<Double, String, JsPieChartOptions>
		implements HasType<PieChartType> {

	private PieChartType type = PieChartType.PIE;

	protected final AttributeMixin<MaterialPieChart, String> labelPositionMixin = new AttributeMixin<>(this,
			CssAttribute.LABEL_POSITION, "inside", FromString.TO_STRING);

	public MaterialPieChart() {
		super(new JsPieChartOptions(), ChartAspectRatio.ASPECT_1x1);
	}

	@Override
	protected void initializeDefaultOptions() {
		super.initializeDefaultOptions();
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
	protected native void applyAnimations(final JavaScriptObject chart, final int durations) /*-{

		// Let's put a sequence number aside so we can use it in the event callbacks
		var count = 0;

		// Once the chart is fully created we reset the sequence
		chart.on('created', function() {
			count++;
		});

		chart.on('draw', function(data) {
			if (data.type === 'slice' && count == 0) {

				// Get the total path length in order to use for dash array animation
				var pathLength = data.element._node.getTotalLength();

				// Set a dasharray that matches the path length as prerequisite to animate dashoffset
				data.element.attr({
					'stroke-dasharray' : pathLength + 'px ' + pathLength + 'px'
				});

				// Create animation definition while also assigning an ID to the animation for later sync usage
				var animationDefinition = {
					'stroke-dashoffset' : {
						id : 'anim' + data.index,
						dur : durations,
						from : -pathLength + 'px',
						to : '0px',
						easing : $wnd.Chartist.Svg.Easing.easeOutQuint,
						// We need to use `fill: 'freeze'` otherwise our animation will fall back to initial (not visible)
						fill : 'freeze'
					}
				};

				// If this was not the first slice, we need to time the animation so that it uses the end sync event of the previous animation
				if (data.index !== 0) {
					animationDefinition['stroke-dashoffset'].begin = 'anim'
							+ (data.index - 1) + '.end';
				}

				// We need to set an initial value before the animation starts as we are not in guided mode which would do that for us
				data.element.attr({
					'stroke-dashoffset' : -pathLength + 'px'
				});

				// We can't use guided mode as the animations need to rely on setting begin manually
				// See http://gionkunz.github.io/chartist-js/api-documentation.html#chartistsvg-function-animate
				data.element.animate(animationDefinition, false);
			}
		});

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
		redraw(false);
	}

	public ChartLabelPosition getLabelPosition() {
		return ChartLabelPosition.fromStyleName(options.labelPosition);
	}

	public void setLabelPosition(ChartLabelPosition labelPosition) {
		options.labelPosition = labelPosition == null ? ChartLabelPosition.INSIDE.getCssName()
				: labelPosition.getCssName();
		labelPositionMixin.setValue(options.labelPosition);
		redraw(false);
	}

	public ChartLabelDirection getLabelDirection() {
		return ChartLabelDirection.fromStyleName(options.labelDirection);
	}

	public void setLabelDirection(ChartLabelDirection labelDirection) {
		options.labelDirection = labelDirection == null ? ChartLabelDirection.NEUTRAL.getCssName()
				: labelDirection.getCssName();
		redraw(false);
	}

	public int getLabelOffset() {
		return options.labelOffset;
	}

	public void setLabelOffset(int labelOffset) {
		this.options.labelOffset = labelOffset;
		redraw(false);
	}

	public double getStartAngle() {
		return options.startAngle;
	}

	public void setStartAngle(double startAngle) {
		this.options.startAngle = startAngle;
		redraw();
	}

	public int getChartPadding() {
		return options.chartPadding;
	}

	public void setChartPadding(int padding) {
		options.chartPadding = padding;
		redraw(false);
	}

	/**
	 * 
	 * @param colors
	 *            Ex. "RED BLUE GREEN"
	 */
	public void setInsideLabelColors(String colors) {
		final String[] colorsArray = colors.split(" ");
		for (int i = 0; i < colorsArray.length; i++)
			setCssProperty(CssMixin.MDC_CHARTIST__LABEL + "_" + ChartHelper.alphaNumerate(i),
					Color.valueOf(colorsArray[i]).getCssName());

	}

	public void setInsideColors(Color... colors) {
		for (int i = 0; i < colors.length; i++)
			setCssProperty(CssMixin.MDC_CHARTIST__LABEL + "_" + ChartHelper.alphaNumerate(i), colors[i].getCssName());
	}
}
