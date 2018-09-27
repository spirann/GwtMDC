package gwt.material.design.components.client.ui.misc.chart.helper;

import java.util.Arrays;

import gwt.material.design.components.client.ui.misc.chart.base.MaterialChartSerie;
import gwt.material.design.components.client.ui.misc.chart.js.base.JsChartData;
import gwt.material.design.components.client.ui.misc.chart.js.base.JsChartSerie;
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
	 * @param series
	 * @return
	 */
	public static <V, L> JsChartData toNativeData(final MaterialChartSerie<V, L>[] series) {
		final JsChartData data = new JsChartData();
		data.series = JsHelper.toJsArray(toJsChartSerie(series));
		data.labels = JsHelper.toJsArray(toLabel(series));
		return data;
	}

	/**
	 * Convert MaterialChartSerie to JsChartSerie
	 * 
	 * @param serie
	 * @return
	 */
	protected static <V, L> Object toJsChartSerie(final MaterialChartSerie<V, L> serie) {

		if (serie == null) {
			return new JsChartSerie();
		}

		
		if(serie.getValue() instanceof Number) {
			
			final JsChartSerie jsChartSerie = new JsChartSerie();

			jsChartSerie.value = serie.getValue() == null ? null : (Double) serie.getValue();
			jsChartSerie.name = serie.getName();
			jsChartSerie.className = serie.getClassName();
			jsChartSerie.meta = serie.getMeta();

			return jsChartSerie;
			
		} else if(serie.getValue() instanceof Number[]) {
			
			final Number[] values = (Number[]) serie.getValue();
			final JsChartSerie[] jsChartSerie = new JsChartSerie[values.length];
			
			for(int i = 0; i < values.length; i++) {
				jsChartSerie[i] = new JsChartSerie();
				jsChartSerie[i].value = serie.getValue() == null ? null : (Double) values[i];
				jsChartSerie[i].name = serie.getName();
				jsChartSerie[i].className = serie.getClassName();
				jsChartSerie[i].meta = serie.getMeta();				
			}
			
			return jsChartSerie;
			
		} else {
			throw new IllegalArgumentException("Value is not Number ou Number Array.");
		}
	}

	/**
	 * Convert MaterialChartSerie to JsChartSerie
	 * 
	 * @param series
	 * @return
	 */
	protected static <V, L> Object[] toJsChartSerie(final MaterialChartSerie<V, L>[] series) {
		return series == null || series.length == 0 ? new JsChartSerie[0]
				: Arrays.asList(series).stream().map(value -> toJsChartSerie(value)).toArray(Object[]::new);
	}

	/*
	 * Convert MaterialChartSerie to values
	 */

	protected static <V, L> Object toValue(final MaterialChartSerie<V, L> serie) {

		if (serie == null) {
			return null;
		}

		return serie.getValue();
	}

	protected static <V, L> Object[] toValue(final MaterialChartSerie<V, L>[] series) {
		return series == null || series.length == 0 ? new Double[0]
				: Arrays.asList(series).stream().map(value -> toValue(value)).toArray(Double[]::new);
	}

	/*
	 * Convert MaterialChartSerie to Labels
	 */

	protected static <V, L> String toLabel(final MaterialChartSerie<V, L> serie) {

		if (serie == null) {
			return null;
		}

		return serie.getLabel().toString();
	}

	protected static <V, L> String[] toLabel(final MaterialChartSerie<V, L>[] series) {
		
		if(series == null || series.length == 0 || series[0].getLabel() == null) {
			return new String[0];
		}
		
		if(series[0].getLabel() instanceof String) {
			return series == null || series.length == 0 ? new String[0]
					: Arrays.asList(series).stream().map(value -> toLabel(value)).toArray(String[]::new);	
		} else if(series[0].getLabel() instanceof String[]) {			
			return (String[]) series[0].getLabel();			
		} else {
			throw new IllegalArgumentException("Value is not String ou String Array.");
		}
	}
	
	protected static <V, L> String toSerieName(final MaterialChartSerie<V, L> serie) {

		if (serie == null) {
			return null;
		}

		return serie.getName();
	}

	protected static <V, L> String[] toSerieName(final MaterialChartSerie<V, L>[] series) {
		return series == null || series.length == 0 ? new String[0]
				: Arrays.asList(series).stream().map(value -> toSerieName(value)).toArray(String[]::new);
	}

}
