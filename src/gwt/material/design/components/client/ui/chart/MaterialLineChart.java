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

import gwt.material.design.components.client.ui.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.chart.helper.ChartHelper;
import gwt.material.design.components.client.ui.chart.js.JsAxis;
import gwt.material.design.components.client.ui.chart.js.JsChartData;
import gwt.material.design.components.client.ui.chart.js.JsLineChartOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLineChart extends MaterialChartBase<MaterialChartSerie[][], JsLineChartOptions> {

	public MaterialLineChart() {
		super(new JsLineChartOptions());
		
		options.fullWidth = true;	
		options.showArea = true;
		options.showPoint = false;
		options.lineSmooth = true;
		options.low = 0;
		
		options.axisX = new JsAxis();
		options.axisX.showGrid = false;
		options.axisX.showLabel = false;
		options.axisX.offset = 30;
		
		options.axisY = new JsAxis();
		options.axisY.showGrid = false;
		options.axisY.showLabel = false;
		options.axisY.offset = 40;
	}

	@Override
	protected JavaScriptObject jsInit(final Element element, final MaterialChartSerie[][] values,
			final JsLineChartOptions options) {
		return jsInit(element, ChartHelper.toNativeData(values), options);
	}

	protected native JavaScriptObject jsInit(final Element element, final JsChartData data,
			final JsLineChartOptions options)/*-{
		return new $wnd.Chartist.Line(element, data, options);
	}-*/;

}
