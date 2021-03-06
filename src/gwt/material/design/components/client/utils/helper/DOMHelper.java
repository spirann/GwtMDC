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
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class DOMHelper {

	public static native String getValue(final Element element)/*-{
		var value = element.value;
		return value ? value : '';
	}-*/;

	public static native void setValue(final Element element, final String value)/*-{
		element.value = value;
	}-*/;

	protected final static boolean isClassOrId(final String selector) {
		return selector.startsWith(".") || selector.startsWith("#");
	}

	protected final static String toClass(final String selector) {
		return toClass(selector.split(" "));
	}

	protected final static String toClass(final String[] selectors) {
		return Arrays.asList(selectors).stream().map(_class -> isClassOrId(_class) ? _class : "." + _class)
				.collect(Collectors.joining(" "));
	}

	protected final static String toId(final String selector) {
		return toId(selector.split(" "));
	}

	protected final static String toId(final String[] selectors) {
		return Arrays.asList(selectors).stream().map(_class -> isClassOrId(_class) ? _class : "#" + _class)
				.collect(Collectors.joining(" "));
	}

	protected final static String toAttr(final String attribute, final String value) {
		if (value == null || value.trim().isEmpty())
			return "[" + attribute + "]";
		else
			return "[" + attribute + "=" + value + "]";
	}

	// //////////////////////////////////////////////////////////
	// Remove methods
	// //////////////////////////////////////////////////////////

	public static void removeByClass(final String selector) {
		remove(toClass(selector));
	}

	public static void removeByClass(final String selector, final Element parent) {
		remove(toClass(selector), parent);
	}

	public static void removeById(final String selector) {
		remove(toId(selector));
	}

	public static void removeById(final String selector, final Element parent) {
		remove(toId(selector), parent);
	}

	public static native void remove(final String selector)/*-{
		$wnd.jQuery(selector).remove();
	}-*/;

	public static native void remove(final String selector, final Element parent)/*-{
		$wnd.jQuery(parent).find(selector).remove();
	}-*/;

	// //////////////////////////////////////////////////////////
	// Contains methods
	// //////////////////////////////////////////////////////////

	public static boolean containsByClass(final String selector) {
		return contains(toClass(selector));
	}

	public static boolean containsByClass(final String selector, final Element parent) {
		return contains(toClass(selector), parent);
	}

	public static boolean containsById(final String selector) {
		return contains(toId(selector));
	}

	public static boolean containsById(final String selector, final Element parent) {
		return contains(toId(selector), parent);
	}

	public static native boolean contains(final String selector)/*-{
		return $wnd.jQuery('body').find(selector).length > 0;
	}-*/;

	public static native boolean contains(final String selector, final Element parent)/*-{
		return $wnd.jQuery(parent).find(selector).length > 0;
	}-*/;

	// //////////////////////////////////////////////////////////
	// Focus methods
	// //////////////////////////////////////////////////////////

	public static native void clearFocus()/*-{
		$doc.activeElement.blur();
	}-*/;

	// //////////////////////////////////////////////////////////
	// Get elements methods
	// //////////////////////////////////////////////////////////

	public static Element getElementByClass(final String selector) {
		return getElement(toClass(selector));
	}

	public static Element getElementByClass(final String selector, final Element parent) {
		return getElement(toClass(selector), parent);
	}

	public static Element getElementById(final String selector) {
		return getElement(toId(selector));
	}

	public static Element getElementById(final String selector, final Element parent) {
		return getElement(toId(selector), parent);
	}

	public static Element getElementByAttr(final String attribute, final String value) {
		return getElement(toAttr(attribute, value));
	}

	public static Element getElementByAttr(final String attribute, final String value, final Element parent) {
		return getElement(toAttr(attribute, value), parent);
	}

	public static native Element getElement(final String selector)/*-{
		var elements = $wnd.jQuery('body').find(selector);
		if (elements.length > 0)
			return elements[0];
		else
			return null;
	}-*/;

	public static native Element getElement(final String selector, final Element parent)/*-{
		var elements = $wnd.jQuery(parent).find(selector);
		if (elements.length > 0)
			return elements[0];
		else
			return null;
	}-*/;

	@SuppressWarnings("unchecked")
	public static <W extends Widget> Set<W> findByClass(final Class<W> _class, final Widget parent) {
		final Set<W> widgets = new LinkedHashSet<>();
		if (parent instanceof ComplexPanel) {
			final ComplexPanel complexPanel = (ComplexPanel) parent;
			for (int w = 0; w < complexPanel.getWidgetCount(); w++) {
				final Widget child = complexPanel.getWidget(w);
				if (child.getClass() == _class)
					widgets.add((W) child);
				widgets.addAll(findByClass(_class, child));
			}
		}
		return widgets;
	}
}
