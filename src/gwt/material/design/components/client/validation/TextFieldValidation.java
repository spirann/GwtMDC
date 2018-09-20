package gwt.material.design.components.client.validation;

import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.resources.message.IMessages;
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
	public Result validate(String value, String inputMask, boolean isRequired, int minLength, int maxLength);

	public class Defaults {

		// //////////////////////////////////////////////////////////////////////////////////////
		// Password security level validation
		// //////////////////////////////////////////////////////////////////////////////////////

		public static final TextFieldValidation password_security_level() {
			return (value, inputMask, isRequired, minLength, maxLength) -> {

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

		// //////////////////////////////////////////////////////////////////////////////////////
		// Min and Max validation
		// //////////////////////////////////////////////////////////////////////////////////////

		public static final TextFieldValidation min_max_length__none_result() {
			return min_max_length(State.NONE);
		}

		public static final TextFieldValidation min_max_length__success_result() {
			return min_max_length(State.SUCCESS);
		}

		private static final TextFieldValidation min_max_length(final State stateOnSuccess) {
			return (value, inputMask, isRequired, minLength, maxLength) -> {

				final int length = value.length();

				if (length < minLength)
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__less_than_min_length(minLength));
				else if (length > maxLength)
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__more_than_max_length(maxLength));

				return new Result(stateOnSuccess, "");

			};
		}

		// //////////////////////////////////////////////////////////////////////////////////////
		// Required validation
		// //////////////////////////////////////////////////////////////////////////////////////

		public static final TextFieldValidation required__none_result() {
			return required(State.NONE);
		}

		public static final TextFieldValidation required__success_result() {
			return required(State.SUCCESS);
		}

		private static final TextFieldValidation required(final State stateOnSuccess) {
			return (value, inputMask, isRequired, minLength, maxLength) -> {

				if (isRequired && value.isEmpty()) {
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__required());
				}

				return new Result(stateOnSuccess, "");

			};
		}

		// //////////////////////////////////////////////////////////////////////////////////////
		// Input Mask vaidation
		// //////////////////////////////////////////////////////////////////////////////////////

		public static final TextFieldValidation input_mask() {
			return (value, inputMask, isRequired, minLength, maxLength) -> {

				if (inputMask == null)
					return new Result(State.NONE, "");

				final int length = Masker.toPattern(value, inputMask).length();

				if (!isRequired && length == 0)
					return new Result(State.NONE, "");

				final int inputMaskLength = inputMask.length();

				if (length != inputMaskLength)
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__value_invalid());
				else if (isRequired)
					return new Result(State.SUCCESS, "");
				else
					return new Result(State.NONE, "");

			};
		}
	}
}
