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

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialPieChart extends MaterialChartBase {

	@Override
	protected JavaScriptObject jsInit(final Element element) {
		setValue(5d,4d,3d);
		return jsElement;
	}

	protected native JavaScriptObject jsInit(final Element element, final MaterialChartSerie[] values)/*-{

		var data = {
			series : values
		};

		var sum = function(a, b) {
			return a + b
		};

		return new $wnd.Chartist.Pie(element, {
			series : values
		}, {
			classNames : {
				chartPie : 'ct-chart-pie',
				chartDonut : 'ct-chart-donut',
				series : 'ct-series',
				slicePie : 'ct-slice-pie',
				sliceDonut : 'ct-slice-donut',
				sliceDonutSolid : 'ct-slice-donut-solid',
				label : 'ct-label'
			},
			width : '100%',
			height : '100%',
			chartPadding : 0,
			donut : true,
			donutSolid : true,
			donutWidth : '100%',
			startAngle : 0,
			showLabel : true,
			labelOffset : 0,
			labelDirection : 'neutral',
			ignoreEmptyValues : false,
			reverseData : false,
		});

	}-*/;

	public void setValue(Double... values) {

		final MaterialChartSerie[] series = new MaterialChartSerie[values.length];
		for (int i = 0; i < values.length; i++) {
			final MaterialChartSerie s = new MaterialChartSerie();
			s.value = values[i];
			s.name = "S" + (i + 1);
			s.meta = "S" + (i + 1);
			series[i] = s;
		}

		jsElement = jsInit(getElement(), series);

	}
}
