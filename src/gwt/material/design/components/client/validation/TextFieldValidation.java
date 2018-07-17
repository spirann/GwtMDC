package gwt.material.design.components.client.validation;

import gwt.material.design.components.client.constants.State;

public interface TextFieldValidation extends Validation {

	/**
	 * 
	 * @param value
	 * @param isRequired
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public State validate(String value, boolean isRequired, int minLength, int maxLength);

	public static final TextFieldValidation PASSWORD_SECURITY_LEVEL__VALIDATION = (value, isRequired, minLength,
			maxLength) -> {

		final int securityLevel = ValidationUtils.passwordLevel(value);

		switch (securityLevel) {
		case 0:
		case 1:
			return State.ERROR;
		case 2:
			return State.WARNING;
		case 3:
			return State.SUCCESS;
		default:
			return State.NONE;
		}

	};

	public static final TextFieldValidation MIN_MAX_LENGTH__NONE_RESULT__VALIDATION = (value, isRequired, minLength,
			maxLength) -> {

		final int length = value.length();

		if (length < minLength) {
			return State.ERROR;
		} else if (length > maxLength) {
			return State.ERROR;
		}

		return State.NONE;

	};

	public static final TextFieldValidation MIN_MAX_LENGTH__SUCCESS_RESULT__VALIDATION = (value, isRequired, minLength,
			maxLength) -> {

		final int length = value.length();

		if (length < minLength) {
			return State.ERROR;
		} else if (length > maxLength) {
			return State.ERROR;
		}

		return State.SUCCESS;

	};

	public static final TextFieldValidation REQUIRED__NONE_RESULT__VALIDATION = (value, isRequired, minLength,
			maxLength) -> {

		if (isRequired && value.isEmpty()) {
			return State.ERROR;
		}

		return State.NONE;

	};

	public static final TextFieldValidation REQUIRED__SUCCESS_RESULT__VALIDATION = (value, isRequired, minLength,
			maxLength) -> {

		if (isRequired && value.isEmpty()) {
			return State.ERROR;
		}

		return State.SUCCESS;

	};

}
