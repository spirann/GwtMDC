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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.Color;

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
	 * @param colorStart
	 * @param colorEnd
	 * @param count
	 * @return
	 */
	public static native String[] generatePalette(final String colorStart, final String colorEnd, final int count)/*-{
		return $wnd.chroma.scale([ colorStart, colorEnd ]).mode('lch').colors(count);
	}-*/;

	/**
	 * https://gka.github.io/chroma.js/
	 * 
	 * @param color
	 * @return If the luminance is bigger than 0.5 return Black, else return White
	 */
	public static native String getColorIn(final String color)/*-{		
		var luminance = $wnd.chroma(color).luminance();		
		if(luminance > 0.5){ return '#000';} 
		else { return '#fff';}		
	}-*/;
	
	/**
	 * https://gka.github.io/chroma.js/
	 * 
	 * Calculate the luminance.
	 * Normalized to 0 for darkest black and 1 for lightest white.
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
	 * Calculate the luminance.
	 * Normalized to 0 for darkest black and 1 for lightest white.
	 * 
	 * @param color
	 * @return The luminance
	 */
	public static double luminance(final Color color){		
		return luminance(setupComputedBackgroundColor(color));
	}

}
