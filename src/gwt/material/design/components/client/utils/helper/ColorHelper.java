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
package gwt.material.design.components.client.utils.helper;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssType;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class ColorHelper {

	/**
	 * Returns first enum constant found..
	 *
	 * @param styleName
	 *            Space-separated list of styles
	 * @param enumClass
	 *            Type of enum
	 * @param defaultValue
	 *            Default value of no match was found
	 * @return First enum constant found or default value
	 */
	public static <E extends Enum<? extends Style.HasCssName>> E fromStyleName(final String styleName,
			final Class<E> enumClass, final E defaultValue) {
		return EnumHelper.fromStyleName(styleName, enumClass, defaultValue, true);
	}

	public static String setupComputedBackgroundColor(Color color) {
		MaterialWidget temp = new MaterialWidget(Document.get().createDivElement());
		temp.setBackgroundColor(color);
		RootPanel.get().add(temp);
		String computed = getComputedBackgroundColor(temp.getElement()).toLowerCase();
		temp.removeFromParent();
		return computed;
	}

	/**
	 * Native call to getComputedStyle.
	 */
	protected static native String getComputedBackgroundColor(Element e)/*-{
		var cs = $wnd.document.defaultView.getComputedStyle(e, null);
		return cs.getPropertyValue('background-color');
	}-*/;

	/**
	 * https://gka.github.io/chroma.js/
	 * 
	 * Generate a color palette from a color list
	 * 
	 * @param colorStart
	 * @param colorEnd
	 * @param count
	 *            number of colors to return
	 * @return
	 */
	public static native String[] generatePalette(final int count, final String... colors)/*-{
		return $wnd.chroma.scale(Array.from(colors)).colors(count);
	}-*/;

	/**
	 * Generate a color palette from a single color
	 * 
	 * @param count
	 *            number of colors to return
	 * @param color
	 *            color to generate the palette
	 * @param limit
	 *            number of different colors
	 * @return
	 */
	public static String[] generatePalette(final int count, final String color, final int limit) {

		final String[] palette = generatePalette(limit + 1, color, "#eee");
		final List<String> cleanedColors = Arrays.asList(palette).subList(0, palette.length - 1);

		final String[] colors = new String[count];
		for (int i = 0; i < colors.length;) {
			for (String c : cleanedColors) {
				if (i >= colors.length)
					break;
				colors[i++] = c;
			}
		}

		return colors;
	}

	/**
	 * Generate a colored color palette
	 * 
	 * @param count
	 *            number of colors to return
	 * @param color
	 *            color to start the palette
	 * @return
	 */
	public static String[] generateColloredPalette(final int count, final String color) {

		final String[] colors = new String[count];

		String auxColor = color;
		for (int i = 0; i < colors.length;) {

			double hue = getHue(auxColor) + 10;

			if (hue > 360)
				hue = hue - 360;

			auxColor = getColor(hue, 1, 0.4);

			for (String c : Arrays.asList(generatePalette(6, auxColor, "#eee")).subList(2, 3))
				colors[i++] = c;
		}

		return colors;
	}

	public static native String getColor(final double hue, final double saturation, final double lightness)/*-{
		return $wnd.chroma(hue, saturation, lightness, 'hsl');
	}-*/;

	/**
	 * https://gka.github.io/chroma.js/
	 * 
	 * @param color
	 * @return If the luminance is bigger than 0.5 return Black, else return White
	 */
	public static native String getColorIn(final String color)/*-{
		var luminance = $wnd.chroma(color).luminance();
		if (luminance > 0.5) {
			return '#000';
		} else {
			return '#fff';
		}
	}-*/;

	/**
	 * https://gka.github.io/chroma.js/
	 * 
	 * Calculate the luminance. Normalized to 0 for darkest black and 1 for lightest
	 * white.
	 * 
	 * @param color
	 * @return The luminance
	 */
	public static native double luminance(final String color)/*-{
		return $wnd.chroma(color).luminance();
	}-*/;

	/**
	 * https://gka.github.io/chroma.js/
	 * 
	 * Calculate the luminance. Normalized to 0 for darkest black and 1 for lightest
	 * white.
	 * 
	 * @param color
	 * @return The luminance
	 */
	public static double luminance(final Color color) {
		return luminance(setupComputedBackgroundColor(color));
	}

	/**
	 * Once loaded, chroma.js can change colors. One way we already saw above, you
	 * can change the lightness.
	 * 
	 * @param color
	 * @return
	 */
	public static native String darken(final String color)/*-{
		return $wnd.chroma(color).darken();
	}-*/;

	/**
	 * Similar to darken, but the opposite direction
	 * 
	 * @param color
	 * @return
	 */
	public static native String brighten(final String color)/*-{
		return $wnd.chroma(color).brighten();
	}-*/;

	/**
	 * Similar to darken, but the opposite direction
	 * 
	 * @param color
	 * @return
	 */
	public static native String saturate(final String color, final int saturation)/*-{
		return $wnd.chroma(color).saturate(saturation);
	}-*/;

	public static native double getHue(final String color)/*-{
		var hsl = $wnd.chroma(color).hsl();

		if (hsl[0] == null) {
			return 0;
		}

		return hsl[0];

	}-*/;

	/**
	 * https://gka.github.io/chroma.js/
	 * 
	 * Mixes two colors
	 * 
	 * @param colorA
	 * @param colorB
	 * @return
	 */
	public static native String mix(final String colorA, final String colorB)/*-{
		return $wnd.chroma.mix(colorA, colorB, 0.5, 'rgb');
	}-*/;

	public enum MixMode implements CssType {

		RGB("rgb"), HSL("hsl"), LAB("lab"), LCH("lch"), LRGB("lrgb");

		private final String cssClass;

		MixMode(final String cssClass) {
			this.cssClass = cssClass;
		}

		@Override
		public String getCssName() {
			return cssClass;
		}

		public static MixMode fromStyleName(final String styleName) {
			return EnumHelper.fromStyleName(styleName, MixMode.class, RGB);
		}
	}

	public static native String mix(final String colorA, final String colorB, final double ratio,
			final MixMode mixMode)/*-{
		return $wnd.chroma.mix(colorA, colorB, ratio, mixMode.toString()
				.toLowerCase());
	}-*/;

	public enum BlendMode implements CssType {

		MULTIPLY("multiply"), DARKEN("darken"), LIGHTEN("lighten"), SCREEN("screen"), OVERLAY("overlay"), BURN(
				"burn"), DODGE("dodge");

		private final String cssClass;

		BlendMode(final String cssClass) {
			this.cssClass = cssClass;
		}

		@Override
		public String getCssName() {
			return cssClass;
		}

		public static BlendMode fromStyleName(final String styleName) {
			return EnumHelper.fromStyleName(styleName, BlendMode.class, MULTIPLY);
		}
	}

	/**
	 * Blends two colors using RGB channel-wise blend functions. Valid blend modes
	 * are multiply, darken, lighten, screen, overlay, burn, and dodge.
	 * 
	 * @param colorA
	 * @param colorB
	 * @return
	 */
	private static native String blend(final String colorA, final String colorB, final BlendMode blendMode)/*-{
		return $wnd.chroma.blend(colorA, colorB, blendMode.toString()
				.toLowerCase());
	}-*/;

}
