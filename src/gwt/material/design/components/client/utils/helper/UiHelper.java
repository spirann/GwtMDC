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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * This static helper class is supposed to collect common methods used for
 * multiple kind of UI classes. It is defined as abstract to prohibit
 * 
 * @author Richeli Vargas
 */
public final class UiHelper {

	private static String PRESSED_CSS_STYLE_NAME = "pressed";

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

	/**
	 * 
	 * @param widget
	 * @return True if the widget and all children is empty
	 */
	public static boolean isEmpty(final Widget widget) {
		return isEmpty(widget.getElement());
	}

	/**
	 * 
	 * @param widget
	 * @return True if the widget and all children is empty
	 */
	public static boolean isEmpty(final Element element) {
		return element.getInnerText().trim().isEmpty();
	}

	/**
	 * Set an attribute if element is empty, or else the attribute is removed
	 * 
	 * @param element
	 * @param attribute
	 */
	public static void setAttrIfIsEmpty(final Widget widget, final String attribute) {
		setAttrIfIsEmpty(widget.getElement(), attribute);
	}

	/**
	 * Set an attribute if element is empty, or else the attribute is removed
	 * 
	 * @param element
	 * @param attribute
	 */
	public static native void setAttrIfIsEmpty(final Element element, final String attribute) /*-{
		var isEmpty = @gwt.material.design.components.client.utils.helper.UiHelper::isEmpty(Lcom/google/gwt/dom/client/Element;)(element);
		if (isEmpty)
			$wnd.jQuery(element).attr(attribute, '');
		else
			$wnd.jQuery(element).removeAttr(attribute);
	}-*/;

	/**
	 * Throws window resize event
	 */
	public static native void fireWindowResize()/*-{
		$wnd.dispatchEvent(new Event('resize'));
	}-*/;

	public static native void fireClickEvent(Element element)/*-{

		var evt = new MouseEvent("click", {
			"view" : window,
			"bubbles" : true,
			"cancelable" : false
		});

		element.dispatchEvent(evt);

	}-*/;

	/**
	 * Adds a mouse pressed handler to a widget. Adds a CSS style ('pressed',
	 * {@link #PRESSED_CSS_STYLE_NAME}) to the widget as long as the mouse is
	 * pressed (or the user touches the widget). See
	 * {@link #addMousePressedHandlers(Widget, String)}.
	 *
	 * @param widget
	 *            The widget to which the "pressed" style musst be applied
	 */
	public static void addMousePressedHandlers(final Widget widget) {
		addMousePressedHandlers(widget, PRESSED_CSS_STYLE_NAME);
	}

	/**
	 * Adds a mouse pressed handler to a widget. Adds a CSS style to the widget as
	 * long as the mouse is pressed (or the user touches the widget on mobile
	 * browser).
	 *
	 * @param widget
	 *            The widget to which the style must be applied for mouse/touch
	 *            event
	 * @param cssStyleName
	 *            CSS style name to be applied
	 */
	public static void addMousePressedHandlers(final Widget widget, final String cssStyleName) {
		widget.sinkEvents(Event.ONMOUSEDOWN);
		widget.sinkEvents(Event.ONMOUSEUP);
		widget.sinkEvents(Event.ONMOUSEOUT);
		widget.sinkEvents(Event.TOUCHEVENTS);

		widget.addHandler(event -> {
			widget.addStyleName(cssStyleName);
		}, MouseDownEvent.getType());

		widget.addHandler(event -> {
			widget.removeStyleName(cssStyleName);
		}, MouseUpEvent.getType());

		widget.addHandler(event -> {
			widget.removeStyleName(cssStyleName);
		}, MouseOutEvent.getType());

		// Touch Events
		widget.addHandler(event -> {
			widget.addStyleName(cssStyleName);
		}, TouchStartEvent.getType());

		widget.addHandler(event -> {
			widget.removeStyleName(cssStyleName);
		}, TouchEndEvent.getType());

		widget.addHandler(event -> {
			widget.removeStyleName(cssStyleName);
		}, TouchCancelEvent.getType());
	}

	public static int calculateSpaceToBottom(Widget widget) {
		return Window.getClientHeight() - widget.getAbsoluteTop() - widget.getOffsetHeight();
	}

	public static HandlerRegistration bindNativeEvent(final Widget widget, final String event,
			final Runnable runnable) {

		final JavaScriptObject handler = _bindNativeEvent(widget.getElement(), event, runnable);
		return () -> {
			unbindNativeEvent(widget, event, handler);
		};
	}

	public static HandlerRegistration bindNativeEvent(final Element element, final String event,
			final Runnable runnable) {

		final JavaScriptObject handler = _bindNativeEvent(element, event, runnable);
		return () -> {
			unbindNativeEvent(element, event, handler);
		};
	}

	private static native JavaScriptObject _bindNativeEvent(final Element element, final String event,
			final Runnable runnable)/*-{
		if (element)
			return $wnd.jQuery(element).bind(event, function(e) {
				runnable.@java.lang.Runnable::run()();
			});

		return null;
	}-*/;

	public static void unbindNativeEvent(final Widget widget, final String event, final JavaScriptObject handler) {
		unbindNativeEvent(widget.getElement(), event, handler);
	}

	public static native void unbindNativeEvent(final Element element, final String event,
			final JavaScriptObject handler)/*-{
		if (element && handler)
			$wnd.jQuery(element).unbind(event, handler);
	}-*/;

	/**
	 * Method to turn draggable an element
	 * 
	 * @param element
	 */
	public static native void turnDraggable(final Element element)/*-{
		(function($) {

			$.fn.drags = function(opt) {

				opt = $.extend({
					handle : "",
					cursor : "move"
				}, opt);

				var mouse_up__handler = function() {
					if (opt.handle === "")
						$wnd.jQuery(this).removeClass('draggable');
					else
						$wnd.jQuery(this).removeClass('active-handle').parent()
								.removeClass('draggable');
				};

				var mouse_down__handler = function(e) {
					if (opt.handle === "")
						var $drag = $(this).addClass('draggable');
					else
						var $drag = $(this).addClass('active-handle').parent()
								.addClass('draggable');

					var z_idx = $drag.css('z-index'), drg_h = $drag
							.outerHeight(), drg_w = $drag.outerWidth(), pos_y = $drag
							.offset().top
							+ drg_h - e.pageY, pos_x = $drag.offset().left
							+ drg_w - e.pageX;

					var mouse_move__handler = function(e) {

						var mouse_up__on__mouse_move__handler = function() {
							$wnd.jQuery(this).removeClass('draggable').css(
									'z-index', z_idx);
						};

						$wnd.jQuery('.draggable').offset({
							top : e.pageY + pos_y - drg_h,
							left : e.pageX + pos_x - drg_w
						}).on("mouseup", mouse_up__on__mouse_move__handler);
					};

					$drag.css('z-index', 1000).parents().on("mousemove",
							mouse_move__handler);

					e.preventDefault(); // disable selection
				};

				if (opt.handle === "")
					var $el = this;
				else
					var $el = this.find(opt.handle);

				return $el.css('cursor', opt.cursor).on("mousedown",
						mouse_down__handler).on("mouseup", mouse_up__handler);
			}
		})($wnd.jQuery);

		$wnd.jQuery(element).drags();

	}-*/;
}
