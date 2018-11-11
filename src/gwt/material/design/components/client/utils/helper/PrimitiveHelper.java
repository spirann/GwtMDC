package gwt.material.design.components.client.utils.helper;

public class PrimitiveHelper {

	public static Integer toInteger(final String string) {
		return toInteger(string, null);
	}

	public static Integer toInteger(final String string, final Integer defaultValue) {
		final String value = string == null ? null : string.replaceAll("[^0-9]", "");
		return value == null || value.isEmpty() ? defaultValue : Integer.valueOf(value);
	}

}
