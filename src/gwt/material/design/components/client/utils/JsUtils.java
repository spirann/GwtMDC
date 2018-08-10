package gwt.material.design.components.client.utils;

import com.google.gwt.dom.client.Element;

public class JsUtils {

	public static native void clearFocus()/*-{
		$doc.activeElement.blur();
	}-*/;
	
	public static native void doClick(Element el)/*-{
		
		var evt = new MouseEvent("click", {
			"view" : window,
			"bubbles" : true,
			"cancelable" : false
		});
		
		el.dispatchEvent(evt);
		
	}-*/;
}
