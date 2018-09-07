package gwt.material.design.components.client.utils.helper;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;

public class JsHelper {

	public static native void clearFocus()/*-{
		$doc.activeElement.blur();
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
