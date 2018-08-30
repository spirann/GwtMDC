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
import gwt.material.design.components.client.constants.ChartAspectRatio;
import gwt.material.design.components.client.constants.PieChartType;
import gwt.material.design.components.client.ui.chart.js.JsChartSerie;
import gwt.material.design.components.client.ui.chart.js.JsPieChartOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialPieChart extends MaterialChartBase<JsChartSerie[], JsPieChartOptions>
		implements HasType<PieChartType> {

	private PieChartType type = PieChartType.PIE;

	public MaterialPieChart() {
		super(new JsPieChartOptions());
		setChartAspectRatio(ChartAspectRatio.ASPECT_1x1);
		
		options.donut = false;
		options.donutSolid = true;
		options.donutWidth = "36px";
		options.startAngle = 270;
		options.labelOffset = 0;
		options.labelDirection = "neutral";
		options.ignoreEmptyValues = false;
		options.reverseData = false;
	}

	protected void jsInit() {

		options.donut = true;

		if (getValue() == null || getValue().length == 0) {
			options.total = 0;
		} else
			switch (getType()) {
			case GAUGE:
				options.total = Arrays.asList(getValue()).stream().mapToDouble(serie -> serie.value).sum() * 2;
				break;
			case PIE:
				options.donut = false;
			case DONUT:
			default:
				options.total = Arrays.asList(getValue()).stream().mapToDouble(serie -> serie.value).sum();
				break;
			}

		super.jsInit();
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element, final JsChartSerie[] value,
			final JsPieChartOptions options)/*-{

		var data = {
  			series: value
		};

		return new $wnd.Chartist.Pie(element, data, options);

	}-*/;

	public void setValue(Double[] values) {

		final JsChartSerie[] series = new JsChartSerie[values.length];
		for (int i = 0; i < values.length; i++) {
			final JsChartSerie s = new JsChartSerie();
			s.value = values[i];
			s.name = "S" + (i + 1);
			s.meta = "S" + (i + 1);
			series[i] = s;
		}

		setValue(series);
		jsInit();
	}

	@Override
	public void setType(PieChartType type) {
		this.type = type;
		redraw();
	}

	@Override
	public PieChartType getType() {
		return type;
	}

	public boolean isShowLabel() {
		return options.showLabel;
	}

	public void setShowLabel(boolean showLabel) {
		options.showLabel = showLabel;
		redraw();
	}

	public boolean isIgnoreEmptyValues() {
		return options.ignoreEmptyValues;
	}

	public void setIgnoreEmptyValues(boolean ignoreEmptyValues) {
		options.ignoreEmptyValues = ignoreEmptyValues;
		redraw();
	}

	public boolean isReverseData() {
		return options.reverseData;
	}

	public void setReverseData(boolean reverseData) {
		options.reverseData = reverseData;
		redraw();
	}

	public String getDonutWidth() {
		return options.donutWidth;
	}

	public void setDonutWidth(String donutWidth) {
		options.donutWidth = donutWidth;
		redraw();
	}

}
