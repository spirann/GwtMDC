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

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.constants.PieChartType;
import gwt.material.design.components.client.ui.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.chart.helper.ChartHelper;
import gwt.material.design.components.client.ui.chart.js.JsChartData;
import gwt.material.design.components.client.ui.chart.js.JsLineChartOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLineChart extends MaterialChartBase<MaterialChartSerie[][], JsLineChartOptions>
		implements HasType<PieChartType> {

	private PieChartType type = PieChartType.PIE;

	public MaterialLineChart() {
		super(new JsLineChartOptions());
		options.fullWidth = true;
		options.showArea = true;
	}

	protected void jsInit() {
		super.jsInit();
	}

	@Override
	protected JavaScriptObject jsInit(final Element element, final MaterialChartSerie[][] values,
			final JsLineChartOptions options) {
		
		final JsChartData data = new JsChartData();
		data.labels = new String[] {"1", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2"};
		data.series = ChartHelper.toValue(values);
		
		return jsInit(element, data, options);
	}

	protected native JavaScriptObject jsInit(final Element element, final JsChartData data,
			final JsLineChartOptions options)/*-{
		return new $wnd.Chartist.Line(element, data, options);
	}-*/;
	
	@Override
	public void setType(PieChartType type) {
		this.type = type;
		redraw();
	}

	@Override
	public PieChartType getType() {
		return type;
	}

}
