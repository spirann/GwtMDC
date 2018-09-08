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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.constants.ChartAspectRatio;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.PieChartType;
import gwt.material.design.components.client.constants.ThemeAttribute;
import gwt.material.design.components.client.ui.chart.base.MaterialChartBase;
import gwt.material.design.components.client.ui.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.chart.helper.ChartHelper;
import gwt.material.design.components.client.ui.chart.js.JsChartData;
import gwt.material.design.components.client.ui.chart.js.JsPieChartClassNames;
import gwt.material.design.components.client.ui.chart.js.JsPieChartOptions;
import gwt.material.design.components.client.utils.helper.ColorHelper;
import gwt.material.design.components.client.utils.helper.ColorHelper.BlendMode;
import gwt.material.design.components.client.utils.helper.ColorHelper.MixMode;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialPieChart extends MaterialChartBase<MaterialChartSerie[], JsPieChartOptions>
		implements HasType<PieChartType> {

	private PieChartType type = PieChartType.PIE;

	public MaterialPieChart() {
		super(new JsPieChartOptions());
		setChartAspectRatio(ChartAspectRatio.ASPECT_1x1);

		final JsPieChartClassNames classNames = new JsPieChartClassNames();
		classNames.chartPie = "ct-chart-pie";
		classNames.chartDonut = "ct-chart-donut";
		classNames.series = "ct-series";
		classNames.slicePie = "ct-slice-pie";
		classNames.sliceDonut = "ct-slice-donut";
		classNames.sliceDonutSolid = "ct-slice-donut-solid";
		classNames.label = "ct-label";		
		options.classNames = classNames;

		options.showLabel = true;
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
				options.total = Arrays.asList(getValue()).stream().mapToDouble(serie -> serie.getValue()).sum() * 2;
				break;
			case PIE:
				options.donut = false;
			case DONUT:
			default:
				options.total = Arrays.asList(getValue()).stream().mapToDouble(serie -> serie.getValue()).sum();
				break;
			}

		super.jsInit();
	}

	@Override
	protected JavaScriptObject jsInit(final Element element, final MaterialChartSerie[] values,
			final JsPieChartOptions options) {
		return jsInit(element, ChartHelper.toNativeData(values), options);
	}

	protected native JavaScriptObject jsInit(final Element element, final JsChartData data,
			final JsPieChartOptions options)/*-{
		return new $wnd.Chartist.Pie(element, data, options);
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

	@Override
	protected void redraw() {
		super.redraw();
		paint();
	}
	
	protected void paint() {
		
		final int seriesCount = getValue() == null ? 0 : getValue().length;
		final String color = StyleHelper.getComputedProperty(ThemeAttribute.MDC_THEME_SECONDARY).replace(" ", "");
		final String startColor = ColorHelper.mix(color, "#000", 0.25, MixMode.RGB);
		final String endColor = ColorHelper.mix(color, "#fff", 0.85, MixMode.HSL);
		
		GWT.log("color" + color);
		GWT.log("color dark: " + startColor);
		GWT.log("color brighten: " + endColor);
		//sGWT.log("color light" + ColorHelper.blend(color, "FFF", BlendMode.MULTIPLY));
		
		final String[] colors = ColorHelper.generatePalette(startColor, endColor, seriesCount);
		for (int i = 0; i < colors.length; i++) {
			setStyleProperty(CssMixin.MDC_CHARTIST__SERIES + "_" + ChartHelper.alphaNumerate(i), colors[i]);
			setStyleProperty(CssMixin.MDC_CHARTIST__LABEL + "_" + ChartHelper.alphaNumerate(i), ColorHelper.getColorIn(colors[i]));
		}
	}
	
	/**
	 * 
	 * @param colors Ex. "RED BLUE GREEN"
	 */
	public void setLabelColors(String colors) {		
		final String[] colorsArray = colors.split(" ");		
		for(int i = 0; i < colorsArray.length;i++) {			
			setStyleProperty(CssMixin.MDC_CHARTIST__LABEL + "_" + ChartHelper.alphaNumerate(i), Color.valueOf(colorsArray[i]).getCssName());			
		}
	}
	
	public void setLabelColors(Color... colors) {		
		for(int i = 0; i < colors.length;i++) {			
			setStyleProperty(CssMixin.MDC_CHARTIST__LABEL + "_" + ChartHelper.alphaNumerate(i), colors[i].getCssName());			
		}
	}
}
