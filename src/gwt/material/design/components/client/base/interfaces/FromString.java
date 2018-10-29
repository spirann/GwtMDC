package gwt.material.design.components.client.base.interfaces;

public interface FromString <V> {
	
	public static final FromString<String> TO_STRING = (value) -> isInvalid(value) ? null : String.valueOf(value);
	public static final FromString<Integer> TO_INTEGER = (value) ->isInvalid(value) ? null : Integer.valueOf(value);
	public static final FromString<Double> TO_DOUBLE = (value) -> isInvalid(value) ? null : Double.valueOf(value);
	public static final FromString<Long> TO_LONG = (value) ->  isInvalid(value) ? null : Long.valueOf(value);
	public static final FromString<Boolean> TO_BOOLEAN = (value) -> isInvalid(value) ? false : Boolean.valueOf(value);
	
	public V from(String value);
	
	static boolean isInvalid(final String value) {
		return value == null || value.isEmpty();
	}
}
