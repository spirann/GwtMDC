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
import gwt.material.design.components.client.ui.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.chart.helper.ChartHelper;
import gwt.material.design.components.client.ui.chart.js.JsAxis;
import gwt.material.design.components.client.ui.chart.js.JsChartData;
import gwt.material.design.components.client.ui.chart.js.JsLineChartClassNames;
import gwt.material.design.components.client.ui.chart.js.JsLineChartOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLineChart extends MaterialChartBase<MaterialChartSerie[][], JsLineChartOptions> {

	public MaterialLineChart() {
		super(new JsLineChartOptions());
		setChartAspectRatio(ChartAspectRatio.ASPECT_3x4);

		final JsLineChartClassNames classNames = new JsLineChartClassNames();
		classNames.chart = "ct-chart-line";
		classNames.label = "ct-label";
		classNames.labelGroup = "ct-labels";
		classNames.series = "ct-series";
		classNames.line = "ct-line";
		classNames.point = "ct-point";
		classNames.area = "ct-area";
		classNames.grid = "ct-grid";
		classNames.gridGroup = "ct-grids";
		classNames.gridBackground = "ct-grid-background";
		classNames.vertical = "ct-vertical";
		classNames.horizontal = "ct-horizontal";
		classNames.start = "ct-start";
		classNames.end = "ct-end";
		options.classNames = classNames;

		//options.high = 100;
		//options.low = 0;
		/*
		 * options.fullWidth = true; options.divisor = 4; options.fullWidth = true;
		 * options.showArea = false; options.showPoint = false; options.lineSmooth =
		 * false; options.high = 100; options.low = 0; // options.divisor = 4;
		 * options.height = "100px"; // options.ticks = new int[] {1,10,20,30};
		 */
		options.axisX = new JsAxis();
		options.axisX.showGrid = true;
		options.axisX.showLabel = true;
		// options.axisX.offset = 30;

		options.axisY = new JsAxis();
		options.axisY.showGrid = true;
		options.axisY.showLabel = true;
		// options.axisY.offset = 40;

	}

	@Override
	protected JavaScriptObject jsInit(final Element element, MaterialChartSerie[][] values,
			final JsLineChartOptions options) {

		if (values == null) {
			values = new MaterialChartSerie[0][0];
		}

		return jsInit(element, ChartHelper.toNativeData(values), options);
	}

	protected native JavaScriptObject jsInit(final Element element, final JsChartData data,
			final JsLineChartOptions options)/*-{
		return new $wnd.Chartist.Line(element, data, options);
	}-*/;

}
