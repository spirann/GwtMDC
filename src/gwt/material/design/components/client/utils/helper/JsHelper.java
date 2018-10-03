package gwt.material.design.components.client.utils.helper;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class JsHelper {

	public static long paserDate(final String value) {
		return Long.valueOf(paser(value));
	}

	public static native String paser(final String value)/*-{
		console.log(value + ': time: ' + new Date(value).getTime());
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

	public static native void setTooltip(final Element element, final String text, final String... tooltipClasses)/*-{
		
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
}
