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

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialPieChart extends MaterialChartBase implements HasType<PieChartType> {

	private PieChartType type = PieChartType.PIE;
	private boolean donutSolid = false;
	private boolean showLabel = true;
	private boolean ignoreEmptyValues = false;
	private boolean reverseData = false;
	private String donutWidth = "36px";
	
	public MaterialPieChart() {
		super();
		setChartAspectRatio(ChartAspectRatio.ASPECT_1x1);
	}
	
	protected void jsInit() {
		jsElement = jsInit(getElement());
	}
	
	@Override
	protected JavaScriptObject jsInit(final Element element) {
		
		final double total;
		boolean donut = true;
		
		if(getValue() == null || getValue().length == 0) {
			total = 0;
		} else switch (getType()) {
		case GAUGE:
			total = Arrays.asList(getValue()).stream().mapToDouble(serie -> serie.value).sum() * 2;
			break;
		case PIE:	
			donut = false;
		case DONUT:
		default:
			total = Arrays.asList(getValue()).stream().mapToDouble(serie -> serie.value).sum();
			break;
		}
		
		return jsInit(element, getValue(), donut, donutSolid, total, showLabel, ignoreEmptyValues, reverseData, donutWidth);
	}

	protected native JavaScriptObject jsInit(final Element element, 
			final MaterialChartSerie[] values, 
			final boolean donut, 
			final boolean donutSolid,
			final double total, 
			final boolean showLabel,
			final boolean ignoreEmptyValues,
			final boolean reverseData,
			final String donutWidth)/*-{

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
			chartPadding : 0,
			donut : donut,
			donutSolid : donutSolid,
			donutWidth : donutWidth,
			startAngle : 270,
			showLabel : showLabel,
			labelOffset : 0,
			labelDirection : 'neutral',
			ignoreEmptyValues : ignoreEmptyValues,
			reverseData : reverseData,
			total: total
		});

	}-*/;

	public void setValue(Double[] values) {

		final MaterialChartSerie[] series = new MaterialChartSerie[values.length];
		for (int i = 0; i < values.length; i++) {
			final MaterialChartSerie s = new MaterialChartSerie();
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
		return showLabel;
	}

	public void setShowLabel(boolean showLabel) {
		this.showLabel = showLabel;
		redraw();
	}

	public boolean isIgnoreEmptyValues() {
		return ignoreEmptyValues;
	}

	public void setIgnoreEmptyValues(boolean ignoreEmptyValues) {
		this.ignoreEmptyValues = ignoreEmptyValues;
		redraw();
	}

	public boolean isReverseData() {
		return reverseData;
	}

	public void setReverseData(boolean reverseData) {
		this.reverseData = reverseData;
		redraw();
	}

	public String getDonutWidth() {
		return donutWidth;
	}

	public void setDonutWidth(String donutWidth) {
		this.donutWidth = donutWidth;
		redraw();
	}
	
}
