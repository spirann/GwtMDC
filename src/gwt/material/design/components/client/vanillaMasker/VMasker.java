package gwt.material.design.components.client.vanillaMasker;

import com.google.gwt.dom.client.Element;

public class VMasker {

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	public static native void maskPattern(Element element, String mask)/*-{
		$wnd.VMasker(element).maskPattern(mask);
	}-*/;

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	public static native String toPattern(String text, String mask)/*-{
		return $wnd.VMasker.toPattern(text, mask);
	}-*/;

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	public static native String toPattern(String text, String mask, String placeholder)/*-{
		return $wnd.VMasker.toPattern(text, {
			pattern : mask,
			placeholder : placeholder
		});
	}-*/;

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	public static String fromPattern(String text, String mask) {

		if (mask == null || mask.isEmpty() || text == null || text.isEmpty()) {
			return text;
		}

		final String chars[] = mask
				.replaceAll("9", "")
				.replaceAll("A", "")
				.replaceAll("S", "")
				.split("");

		for (String character : chars) {
			text = text.replace(character, "");
		}

		return text;
	}
}
