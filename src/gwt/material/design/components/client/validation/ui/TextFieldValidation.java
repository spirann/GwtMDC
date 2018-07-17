package gwt.material.design.components.client.validation.ui;

import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.validation.Validation;
import gwt.material.design.components.client.validation.validators.PasswordValidator;

public interface TextFieldValidation extends Validation {

	/**
	 * 
	 * @param value
	 * @param isRequired
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public Result validate(String value, boolean isRequired, int minLength, int maxLength);

	public static class Defaults {

		public static final TextFieldValidation password_security_level__validation() {
			return (value, isRequired, minLength, maxLength) -> {

				final int securityLevel = PasswordValidator.passwordLevel(value);

				switch (securityLevel) {
				case 0:
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__password__very_weak());
				case 1:
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__password__weak());
				case 2:
					return new Result(State.WARNING, IMessages.INSTANCE.mdc_validation__password__midium());
				case 3:
					return new Result(State.SUCCESS, IMessages.INSTANCE.mdc_validation__password__strong());
				default:
					return new Result(State.NONE, "");
				}

			};
		}

		public static final TextFieldValidation min_max_length__none_result__validation() {
			return (value, isRequired, minLength, maxLength) -> {

				final int length = value.length();

				if (length < minLength) {
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__less_than_min_length(minLength));
				} else if (length > maxLength) {
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__more_than_max_length(maxLength));
				}

				return new Result(State.NONE, "");

			};
		}

		public static final TextFieldValidation min_max_length__success_result__validation() {
			return (value, isRequired, minLength, maxLength) -> {

				final int length = value.length();

				if (length < minLength) {
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__less_than_min_length(minLength));
				} else if (length > maxLength) {
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__more_than_max_length(maxLength));
				}

				return new Result(State.SUCCESS, "");

			};
		}

		public static final TextFieldValidation required__none_result__validation() {
			return (value, isRequired, minLength, maxLength) -> {

				if (isRequired && value.isEmpty()) {
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__required());
				}

				return new Result(State.NONE, "");

			};			
		}

		public static final TextFieldValidation required__success_result__validation() {
			return (value, isRequired, minLength, maxLength) -> {

				if (isRequired && value.isEmpty()) {
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__required());
				}

				return new Result(State.SUCCESS, "");

			};
		}
	}
}
