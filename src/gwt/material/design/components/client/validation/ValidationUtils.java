package gwt.material.design.components.client.validation;

public class ValidationUtils {

	/**
	 * 
	 * @param value
	 * @return
	 * 0 - Very weak
	 * 1 - Weak
	 * 2 - Medium
	 * 3 - Strong
	 */
	public static native int passwordLevel(final String value)/*-{

		var strongRegex = new RegExp(
				"^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		
		var mediumRegex = new RegExp(
				"^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$",
				"g");
		
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");

		if (value.length == 0) {
			// Empty
			return -1;
		} else if (false == enoughRegex.test(value)) {
			// Very Weak
			return 0;
		} else if (strongRegex.test(value)) {
			// Strong
			return 3;
		} else if (mediumRegex.test(value)) {
			// Medium
			return 2;
		} else {
			// Weak
			return 1;
		}

	}-*/;

}
