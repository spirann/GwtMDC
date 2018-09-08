package gwt.material.design.components.client.ui.chart.helper;

import java.util.Arrays;

import com.google.gwt.core.client.JsArray;

import gwt.material.design.components.client.ui.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.chart.js.JsChartData;
import gwt.material.design.components.client.ui.chart.js.JsChartSerie;
import gwt.material.design.components.client.utils.helper.JsHelper;

public class ChartHelper {

	public static final int maxSeries() {
		return 26;
	}
	
	/**
	 * Return the letter from index serie
	 * 
	 * @param seriesIndex
	 * @return
	 */
	public static native String alphaNumerate(final int seriesIndex)/*-{
		return $wnd.Chartist.alphaNumerate(seriesIndex);
	}-*/;
	
	/**
	 * Convert MaterialChartSerie to JsChartData
	 * 
	 * @param serie
	 * @return
	 */
	public static JsChartData toNativeData(final MaterialChartSerie serie) {
		return toNativeData(new MaterialChartSerie[] { serie });
	}

	/**
	 * Convert MaterialChartSerie to JsChartData
	 * 
	 * @param series
	 * @return
	 */
	public static JsChartData toNativeData(final MaterialChartSerie[] series) {
		final JsChartData data = new JsChartData();
		data.series = JsHelper.toJsArray(toJsChartSerie(series));
		data.labels = JsHelper.toJsArray(toLabel(series));
		return data;
	}

	/**
	 * Convert MaterialChartSerie to JsChartData
	 * 
	 * @param series
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static JsChartData toNativeData(final MaterialChartSerie[][] series) {
		final JsChartData data = new JsChartData();

		final int countSeries = series.length;
		final JsArray[] arrays = new JsArray[countSeries];
		for (int i = 0; i < countSeries; i++) {
			arrays[i] = JsHelper.toJsArray(toJsChartSerie(series[i]));
		}

		data.series = JsHelper.toJsArray(arrays);

		if (series == null || series.length == 0) {
		} else {
			data.labels = JsHelper.toJsArray(toLabel(series[0]));
		}
		return data;
	}

	/**
	 * Convert MaterialChartSerie to JsChartSerie
	 * 
	 * @param serie
	 * @return
	 */
	public static JsChartSerie toJsChartSerie(final MaterialChartSerie serie) {

		final JsChartSerie jsChartSerie = new JsChartSerie();

		if (serie == null) {
			return jsChartSerie;
		}

		jsChartSerie.value = serie.getValue();
		jsChartSerie.name = serie.getName();
		jsChartSerie.className = serie.getClassName();
		jsChartSerie.meta = serie.getMeta();

		return jsChartSerie;
	}

	/**
	 * Convert MaterialChartSerie to JsChartSerie
	 * 
	 * @param series
	 * @return
	 */
	public static JsChartSerie[] toJsChartSerie(final MaterialChartSerie[] series) {
		return series == null || series.length == 0 ? new JsChartSerie[0]
				: Arrays.asList(series).stream().map(value -> toJsChartSerie(value)).toArray(JsChartSerie[]::new);
	}

	/**
	 * Convert MaterialChartSerie to JsChartSerie
	 * 
	 * @param series
	 * @return
	 */
	public static JsChartSerie[][] toJsChartSerie(final MaterialChartSerie[][] series) {
		return series == null || series.length == 0 ? new JsChartSerie[0][0]
				: Arrays.asList(series).stream().map(value -> toJsChartSerie(value)).toArray(JsChartSerie[][]::new);
	}

	/*
	 * Convert MaterialChartSerie to values
	 */

	public static Double toValue(final MaterialChartSerie serie) {

		if (serie == null) {
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

	/*
	 * Convert MaterialChartSerie to Labels
	 */

	public static String toLabel(final MaterialChartSerie serie) {

		if (serie == null) {
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
}
