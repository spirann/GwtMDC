package gwt.material.design.components.client.vanillaMasker;

import com.google.gwt.dom.client.Element;

public class VMasker {

	public static native void maskPattern(Element element, String mask)/*-{
		$wnd.VMasker(element).maskPattern(mask);
	}-*/;

	public static native String toPattern(String text, String mask)/*-{
		return $wnd.VMasker.toPattern(text, mask);
	}-*/;

	public static native String toPattern(String text, String mask, String placeholder)/*-{
		return $wnd.VMasker.toPattern(text, {pattern: mask, placeholder: placeholder});
	}-*/;

}
