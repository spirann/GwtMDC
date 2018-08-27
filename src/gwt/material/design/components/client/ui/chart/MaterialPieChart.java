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

import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialPieChart extends Div {

	public MaterialPieChart() {
		super();
	}

	@Override
	protected JavaScriptObject jsInit(final Element element) {
		return jsInit(element, new Double[] { 5d, 3d, 4d });
	}

	protected native JavaScriptObject jsInit(final Element element, final Double[] values)/*-{

		var data = {
			series : values
		};

		var sum = function(a, b) {
			return a + b
		};

		return new $wnd.Chartist.Pie(element, {
  			series: values,
  			labels: values
		}, {
			donut: true,
			donutWidth: 60,
  			startAngle: 270,
			showLabel: true
		});

	}-*/;

	public void setValue(Double... values) {
		jsElement = jsInit(getElement(), values);
		
	}
}
