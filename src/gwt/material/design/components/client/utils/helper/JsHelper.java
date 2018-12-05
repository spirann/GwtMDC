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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class JsHelper {
	
	public static int getWidth(UIObject uiObject) {
		return getWidth(uiObject.getElement());
	}

	public static native int getWidth(Element element) /*-{
		return $wnd.jQuery(element).outerWidth();
	}-*/;

	public static int getHeight(UIObject uiObject) {
		return getHeight(uiObject.getElement());
	}

	public static native int getHeight(Element element) /*-{
		return $wnd.jQuery(element).outerHeight();
	}-*/;

	public static long paserDate(final String value) {
		return Long.valueOf(paser(value));
	}

	public static native String paser(final String value)/*-{
		return new Date(value + ' 12:00:00').getTime();
	}-*/;

	public static native void allowNumbersOnly(final Element element)/*-{
		$wnd.jQuery(element).keypress(function(event) {
			return /\d/.test(String.fromCharCode(event.keyCode));
		});
	}-*/;

	public static void hideEmpty(final Widget widget) {
		final Element element = widget.getElement();
		element.removeAttribute("empty");
		if (element.getInnerText().trim().isEmpty())
			element.setAttribute("empty", null);
	}

	public static native void setTooltip(final Element element, final String text,
			final String... tooltipClasses)/*-{
		var classes = Array.from(tooltipClasses);
		$wnd.jQuery(element).tooltip();
	}-*/;

	public static native void clearFocus()/*-{
		$doc.activeElement.blur();
	}-*/;

	public static native void throwsWindowResize()/*-{
		$wnd.dispatchEvent(new Event('resize'));
	}-*/;

	public static native void removeAllElementsByClassName(final String className)/*-{
		while ($doc.getElementsByClassName(className)[0])
			$doc.getElementsByClassName(className)[0].remove();
	}-*/;

	public static native void removeAllElementsByClassName(final String className, final Element parent)/*-{
		while (parent.getElementsByClassName(className)[0])
			parent.getElementsByClassName(className)[0].remove();
	}-*/;

	public static native boolean containsElementByClassName(final String className)/*-{
		return $doc.getElementsByClassName(className).length > 0;
	}-*/;

	public static native boolean containsElementByClassName(final String className, final Element parent)/*-{
		return parent.getElementsByClassName(className).length > 0;
	}-*/;

	public static native void doClick(Element element)/*-{

		var evt = new MouseEvent("click", {
			"view" : window,
			"bubbles" : true,
			"cancelable" : false
		});

		element.dispatchEvent(evt);

	}-*/;

	@SuppressWarnings("rawtypes")
	public static native JsArray toJsArray(final Object[] labels)/*-{

		var arr = [];

		if (!labels)
			return arr;

		var count = labels.length;

		for (var i = 0; i < count; i++)
			arr.push(labels[i]);

		return arr;

	}-*/;

	public static native void turnDraggable(final Element element)/*-{

		(function($) {
			$.fn.drags = function(opt) {

				opt = $.extend({
					handle : "",
					cursor : "move"
				}, opt);

				if (opt.handle === "")
					var $el = this;
				else
					var $el = this.find(opt.handle);

				return $el
						.css('cursor', opt.cursor)
						.on(
								"mousedown",
								function(e) {
									if (opt.handle === "")
										var $drag = $(this).addClass(
												'draggable');
									else
										var $drag = $(this).addClass(
												'active-handle').parent()
												.addClass('draggable');

									var z_idx = $drag.css('z-index'), drg_h = $drag
											.outerHeight(), drg_w = $drag
											.outerWidth(), pos_y = $drag
											.offset().top
											+ drg_h - e.pageY, pos_x = $drag
											.offset().left
											+ drg_w - e.pageX;
									$drag
											.css('z-index', 1000)
											.parents()
											.on(
													"mousemove",
													function(e) {
														$('.draggable')
																.offset(
																		{
																			top : e.pageY
																					+ pos_y
																					- drg_h,
																			left : e.pageX
																					+ pos_x
																					- drg_w
																		})
																.on(
																		"mouseup",
																		function() {
																			$wnd
																					.jQuery(
																							this)
																					.removeClass(
																							'draggable')
																					.css(
																							'z-index',
																							z_idx);
																		});
													});
									e.preventDefault(); // disable selection
								}).on(
								"mouseup",
								function() {
									if (opt.handle === "")
										$wnd.jQuery(this).removeClass(
												'draggable');
									else
										$wnd.jQuery(this).removeClass(
												'active-handle').parent()
												.removeClass('draggable');
								});
			}
		})($wnd.jQuery);

		$wnd.jQuery(element).drags();

	}-*/;

	public static native Element clone(Element element)/*-{

		(function($) {

			$.fn.getStyles = function(only, except) {

				// the map to return with requested styles and values as KVP
				var product = {};

				// the style object from the DOM element we need to iterate through
				var style;

				// recycle the name of the style attribute
				var name;

				// if it's a limited list, no need to run through the entire style object
				if (only && only instanceof Array) {

					for (var i = 0, l = only.length; i < l; i++) {
						// since we have the name already, just return via built-in .css method
						name = only[i];
						product[name] = this.css(name);
					}

				} else {

					// otherwise, we need to get everything
					var dom = this.get(0);

					// standards
					if (window.getComputedStyle) {

						// convenience methods to turn css case ('background-image') to camel ('backgroundImage')
						var pattern = /\-([a-z])/g;
						var uc = function(a, b) {
							return b.toUpperCase();
						};
						var camelize = function(string) {
							return string.replace(pattern, uc);
						};

						// make sure we're getting a good reference
						if (style = window.getComputedStyle(dom, null)) {
							var camel, value;
							// opera doesn't give back style.length - use truthy since a 0 length may as well be skipped anyways
							if (style.length) {
								for (var i = 0, l = style.length; i < l; i++) {
									name = style[i];
									camel = camelize(name);
									value = style.getPropertyValue(name);
									product[camel] = value;
								}
							} else {
								// opera
								for (name in style) {
									camel = camelize(name);
									value = style.getPropertyValue(name)
											|| style[name];
									product[camel] = value;
								}
							}
						}
					}
					// IE - first try currentStyle, then normal style object - don't bother with runtimeStyle
					else if (style = dom.currentStyle) {
						for (name in style) {
							product[name] = style[name];
						}
					} else if (style = dom.style) {
						for (name in style) {
							if (typeof style[name] != 'function') {
								product[name] = style[name];
							}
						}
					}

				}

				// remove any styles specified...
				// be careful on blacklist - sometimes vendor-specific values aren't obvious but will be visible...  e.g., excepting 'color' will still let '-webkit-text-fill-color' through, which will in fact color the text
				if (except && except instanceof Array) {
					for (var i = 0, l = except.length; i < l; i++) {
						name = except[i];
						delete product[name];
					}
				}

				// one way out so we can process blacklist in one spot
				return product;

			};

			// sugar - source is the selector, dom element or jQuery instance to copy from - only and except are optional
			$.fn.copyCSS = function(source, only, except) {
				var styles = $(source).getStyles(only, except);
				this.css(styles);
			};

		})($wnd.jQuery);

		var o = element;
		var d = $wnd.jQuery(o).clone();
		d.copyCSS(o);
		var od = $wnd.jQuery(o).find('*');
		var dd = $wnd.jQuery(d).find('*');
		dd.each(function(index, element) {
			var dc = $wnd.jQuery(element);
			var oc = $wnd.jQuery(od.get(index));
			dc.copyCSS(oc);
		});

		return d;

	}-*/;
}
