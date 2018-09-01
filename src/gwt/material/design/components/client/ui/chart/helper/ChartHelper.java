package gwt.material.design.components.client.ui.chart.helper;

import java.util.Arrays;

import gwt.material.design.components.client.ui.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.chart.js.JsChartData;
import gwt.material.design.components.client.ui.chart.js.JsChartSerie;

public class ChartHelper {

	/*
	 * Convert MaterialChartSerie to js chart series
	 */
	
	public static JsChartSerie toNativeSerie(final MaterialChartSerie serie) {

		if(serie == null) {
			return null;
		}
		
		final JsChartSerie jsValue = new JsChartSerie();
		jsValue.value = serie.getValue();
		jsValue.name = serie.getName();
		jsValue.className = serie.getClassName();
		jsValue.meta = serie.getMeta();

		return jsValue;
	}

	public static JsChartSerie[] toNativeSerie(final MaterialChartSerie[] series) {
		return series == null || series.length == 0 ? new JsChartSerie[0]
				: Arrays.asList(series).stream().map(value -> toNativeSerie(value)).toArray(JsChartSerie[]::new);
	}

	public static JsChartSerie[][] toNativeSerie(final MaterialChartSerie[][] series) {
		return series == null || series.length == 0 ? new JsChartSerie[0][0]
				: Arrays.asList(series).stream().map(value -> toNativeSerie(value)).toArray(JsChartSerie[][]::new);
	}
	
	/*
	 * Convert MaterialChartSerie to label's
	 */
	
	public static String toLabel(final MaterialChartSerie serie) {

		if(serie == null) {
			return null;
		}
		
		return serie.getName();
	}

	public static String[] toLabel(final MaterialChartSerie[] series) {
		return series == null || series.length == 0 ? new String[0]
				: Arrays.asList(series).stream().map(value -> toLabel(value)).toArray(String[]::new);
	}

	public static String[][] toLabel(final MaterialChartSerie[][] series) {
		return series == null || series.length == 0 ? new String[0][0]
				: Arrays.asList(series).stream().map(value -> toLabel(value)).toArray(String[][]::new);
	}


	/*
	 * Convert MaterialChartSerie to chart data
	 */
	public static JsChartData toNativeData(final MaterialChartSerie serie) {
		return toNativeData(new MaterialChartSerie[] {serie});		
	}
	
	public static JsChartData toNativeData(final MaterialChartSerie[] series) {		
		final JsChartData data = new JsChartData();
		data.series = toNativeSerie(series);
		data.labels = toLabel(series);		
		return data;
	}

	public static JsChartData toNativeData(final MaterialChartSerie[][] series) {
		final JsChartData data = new JsChartData();
		data.series = toNativeSerie(series);
		data.labels = toLabel(series);
		return data;
	}
	
	/*
	 * Convert MaterialChartSerie to values
	 */
	
	public static Double toValue(final MaterialChartSerie serie) {

		if(serie == null) {
			return null;
		}
		
		return serie.getValue();
	}

	public static Double[] toValue(final MaterialChartSerie[] series) {
		return series == null || series.length == 0 ? new Double[0]
				: Arrays.asList(series).stream().map(value -> toValue(value)).toArray(Double[]::new);
	}

	public static Double[][] toValue(final MaterialChartSerie[][] series) {
		return series == null || series.length == 0 ? new Double[0][0]
				: Arrays.asList(series).stream().map(value -> toValue(value)).toArray(Double[][]::new);
	}
}
