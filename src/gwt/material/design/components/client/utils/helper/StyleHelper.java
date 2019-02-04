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

import com.google.gwt.dom.client.Element;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Helper methods regarding CSS styling of UIObjects.
 *
 * @author Richeli Vargas
 */
public final class StyleHelper {

	private StyleHelper() {
	}

	/**
	 * Returns {@code true} if specified style is contained in space-separated list
	 * of styles
	 *
	 * @param styleNames Space-separated list of styles
	 * @param style      Style to look for
	 * @return True if contains style
	 */
	public static boolean containsStyle(final String styleNames, final String style) {
		if (styleNames == null || style == null)
			return false;

		final String[] styles = styleNames.split("\\s");

		for (final String s : styles)
			if (style.equals(s))
				return true;

		return false;
	}

	/**
	 * Toggles a style name on a ui object
	 *
	 * @param uiObject    Object to toggle style on
	 * @param toggleStyle whether or not to toggle the style name on the object
	 * @param styleName   Style name
	 */
	public static void toggleStyle(final UIObject uiObject, final boolean toggleStyle, final String styleName) {
		if (toggleStyle)
			addStyle(uiObject, styleName);
		else
			removeStyle(uiObject, styleName);
	}

	public static void toggleStyle(final UIObject uiObject, final String styleName) {
		toggleStyle(uiObject, !hasStyle(uiObject, styleName), styleName);
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Add style
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void addStyle(final UIObject uiObject, final Style.HasCssName style) {
		addStyle(uiObject.getElement(), style == null ? null : style.getCssName());
	}

	public final static void addStyle(final Element element, final Style.HasCssName style) {
		addStyle(element, style == null ? null : style.getCssName());
	}

	public final static void addStyle(final UIObject uiObject, final String style) {
		addStyle(uiObject.getElement(), style);
	}

	public final static native void addStyle(final Element element, final String style)/*-{
		if (style && style.length > 0) {
			$wnd.jQuery(element).removeClass(style);
			$wnd.jQuery(element).addClass(style);
		}
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Remove style
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void removeStyle(final UIObject uiObject, final Style.HasCssName style) {
		removeStyle(uiObject.getElement(), style == null ? null : style.getCssName());
	}

	public final static void removeStyle(final Element element, final Style.HasCssName style) {
		removeStyle(element, style == null ? null : style.getCssName());
	}

	public final static void removeStyle(final UIObject uiObject, final String style) {
		removeStyle(uiObject.getElement(), style);
	}

	public final static native void removeStyle(final Element element, final String style)/*-{
		if (style && style.length > 0)
			$wnd.jQuery(element).removeClass(style);
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Has style
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static boolean hasStyle(final UIObject uiObject, final Style.HasCssName style) {
		return hasStyle(uiObject.getElement(), style == null ? null : style.getCssName());
	}

	public final static boolean hasStyle(final Element element, final Style.HasCssName style) {
		return hasStyle(element, style == null ? null : style.getCssName());
	}

	public final static boolean hasStyle(final UIObject uiObject, final String style) {
		return hasStyle(uiObject.getElement(), style);
	}

	public final static native boolean hasStyle(final Element element, final String style)/*-{
		if (style && style.length > 0)
			return $wnd.jQuery(element).hasClass(style);
		else
			return false;
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Set css property at an element
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void setCssProperty(final UIObject uiObject, final String property,
			final Style.HasCssName value) {
		setCssProperty(uiObject.getElement(), property, value == null ? null : value.getCssName());
	}

	public final static void setCssProperty(final Element element, final String property,
			final Style.HasCssName value) {
		setCssProperty(element, property, value == null ? null : value.getCssName());
	}

	public final static void setCssProperty(final UIObject uiObject, final String property, final String value) {
		setCssProperty(uiObject.getElement(), property, value);
	}

	public final static native void setCssProperty(final Element element, final String property,
			final String value)/*-{
		if (value && value.length > 0)
			$wnd.jQuery(element).css(property, value);
		else
			$wnd.jQuery(element).css(property, '');
	}-*/;

	public final static native void setCssProperty(final String property, final String value)/*-{
		if (value && value.length > 0)
			$wnd.jQuery('html').css(property, value);
		else
			$wnd.jQuery('html').css(property, '');
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Set css property at an element by selector
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void setCssPropertyById(final String selector, final String property,
			final Style.HasCssName value) {
		setCssProperty(DOMHelper.toId(selector), property, value == null ? null : value.getCssName());
	}

	public final static void setCssPropertyById(final String selector, final String property, final String value) {
		setCssProperty(DOMHelper.toId(selector), property, value);
	}

	public final static void setCssPropertyByClass(final String selector, final String property,
			final Style.HasCssName value) {
		setCssProperty(DOMHelper.toClass(selector), property, value == null ? null : value.getCssName());
	}

	public final static void setCssPropertyByClass(final String selector, final String property,
			final String value) {
		setCssProperty(DOMHelper.toClass(selector), property, value);
	}

	public final static native void setCssProperty(final String selector, String property, String value) /*-{
		if (value && value.length > 0)
			$wnd.jQuery(selector).css(property, value);
		else
			$wnd.jQuery(selector).css(property, '');
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Set css property at element's children
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void setCssProperty(final UIObject uiObject, String child, String property,
			final Style.HasCssName value) {
		setCssProperty(uiObject.getElement(), child, property, value == null ? null : value.getCssName());
	}

	public final static void setCssProperty(final Element element, String child, String property,
			final Style.HasCssName value) {
		setCssProperty(element, child, property, value == null ? null : value.getCssName());
	}

	public final static void setCssProperty(final UIObject uiObject, String child, String property, String value) {
		setCssProperty(uiObject.getElement(), child, property, value);
	}

	public final static native void setCssProperty(final Element parent, String child, String property,
			String value) /*-{
		if (value && value.length > 0)
			$wnd.jQuery(parent).find(child).css(property, value);
		else
			$wnd.jQuery(parent).find(child).css(property, '');
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Remove css property
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void removeCssProperty(final UIObject uiObject, final String property) {
		removeCssProperty(uiObject.getElement(), property);
	}

	public final static native void removeCssProperty(final Element element, final String property)/*-{
		$wnd.jQuery(element).css(property, "");
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Get css property
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static String getCssProperty(final UIObject uiObject, final String property) {
		return getCssProperty(uiObject.getElement(), property);
	}

	public final static native String getCssProperty(final Element element, final String property)/*-{
		return $wnd.jQuery(element).css(property);
	}-*/;

	public final static native String getCssProperty(final String property)/*-{
		return $wnd.jQuery('html').css(property);
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Set property
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void setProperty(final UIObject uiObject, final String property,
			final Style.HasCssName value) {
		setProperty(uiObject.getElement(), property, value == null ? null : value.getCssName());
	}

	public final static void setProperty(final Element element, final String property,
			final Style.HasCssName value) {
		setProperty(element, property, value == null ? null : value.getCssName());
	}

	public final static void setProperty(final UIObject uiObject, final String property, final String value) {
		setProperty(uiObject.getElement(), property, value);
	}

	public final static native void setProperty(final Element element, final String property,
			final String value)/*-{
		if (value && value.length > 0)
			$wnd.jQuery(element).prop(property, value);
		else
			$wnd.jQuery(element).removeProp(property);
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Remove property
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void removeProperty(final UIObject uiObject, final String property) {
		removeProperty(uiObject.getElement(), property);
	}

	public final static native void removeProperty(final Element element, final String property)/*-{
		$wnd.jQuery(element).removeProp(property);
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Get property
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static String getProperty(final UIObject uiObject, final String property) {
		return getProperty(uiObject.getElement(), property);
	}

	public final static boolean getPropertyAsBoolean(final UIObject uiObject, final String property) {
		return Boolean.valueOf(getProperty(uiObject.getElement(), property));
	}

	public final static native String getProperty(final Element element, final String property)/*-{
		var prop = $wnd.jQuery(element).prop(property);
		if (!prop)
			return null;
		else
			return new String(prop);
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Set attribute
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void setAttribute(final UIObject uiObject, final String attribute,
			final Style.HasCssName value) {
		setAttribute(uiObject.getElement(), attribute, value == null ? null : value.getCssName());
	}

	public final static void setAttribute(final Element element, final String attribute,
			final Style.HasCssName value) {
		setAttribute(element, attribute, value == null ? null : value.getCssName());
	}

	public final static void setAttribute(final UIObject uiObject, final String attribute, final String value) {
		setAttribute(uiObject.getElement(), attribute, value);
	}

	public final static native void setAttribute(final Element element, final String attribute,
			final String value)/*-{
		if (value && value.length > 0)
			$wnd.jQuery(element).attr(attribute, value);
		else
			$wnd.jQuery(element).removeAttr(attribute);
	}-*/;

	public final static void setAttribute(final UIObject parent, String child, String attribute, String value) {
		setAttribute(parent.getElement(), child, attribute, value);
	}

	public final static native void setAttribute(final Element parent, String child, String attribute,
			String value) /*-{
		if (value && value.length > 0)
			$wnd.jQuery(parent).find(child).attr(attribute, value);
		else
			$wnd.jQuery(parent).find(child).removeAttr(attribute);
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Remove attribute
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static void removeAttribute(final UIObject uiObject, final String attribute) {
		removeAttribute(uiObject.getElement(), attribute);
	}

	public final static native void removeAttribute(final Element element, final String attribute)/*-{
		$wnd.jQuery(element).removeAttr(attribute);
	}-*/;

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	// Get attribute
	// ////////////////////////////////////////////////////////////////////////////////////////////////

	public final static String getAttribute(final UIObject uiObject, final String attribute) {
		return getAttribute(uiObject.getElement(), attribute);
	}

	public final static boolean getAttributeAsBoolean(final UIObject uiObject, final String attribute) {
		return Boolean.valueOf(getAttribute(uiObject.getElement(), attribute));
	}

	public final static native String getAttribute(final Element element, final String attribute)/*-{
		var attr = $wnd.jQuery(element).attr(attribute);
		if (!attr)
			return null;
		else
			return new String(attr);
	}-*/;

}
