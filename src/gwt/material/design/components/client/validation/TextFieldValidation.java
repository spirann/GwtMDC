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
				else if (!isRequired && value.isEmpty())
					return new Result(State.NONE, "");
				else if (isRequired && value.isEmpty())
					return new Result(State.ERROR, "");

				final int length = Masker.toPattern(value, inputMask).length();

				final int inputMaskLength = inputMask.length();

				if (length != inputMaskLength)
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__value_invalid());
				else if (isRequired)
					return new Result(State.SUCCESS, "");
				else
					return new Result(State.NONE, "");

			};
		}

		// //////////////////////////////////////////////////////////////////////////////////////
		// Date vaidation
		// //////////////////////////////////////////////////////////////////////////////////////

		public static final TextFieldValidation date() {
			return (value, inputMask, isRequired, minLength, maxLength) -> {

				if (inputMask == null)
					return new Result(State.NONE, "");				
				else if (!isRequired && value.isEmpty())
					return new Result(State.NONE, "");
				else if (isRequired && value.isEmpty())
					return new Result(State.ERROR, "");
				
				final String maskedValue = Masker.toPattern(value, inputMask);
				final int length = maskedValue.length();

				if (length != inputMask.length())
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__value_invalid());
				
				final int day;
				final int month;
				final int year;

				switch (inputMask) {
				case "99/99/9999":
				case "99-99-9999":
					day = Integer.parseInt(maskedValue.substring(0, 2));
					month = Integer.parseInt(maskedValue.substring(3, 5));
					year = Integer.parseInt(maskedValue.substring(6));
					break;
				case "9999/99/99":
				case "9999-99-99":
					day = Integer.parseInt(maskedValue.substring(9));
					month = Integer.parseInt(maskedValue.substring(6, 8));
					year = Integer.parseInt(maskedValue.substring(0, 5));
					break;
				default:
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__value_invalid());
				}

				final int[] monthLength = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
				if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
					monthLength[1] = 29;

				if (year < 1970 || year > 3000 || month < 1 || month > 12 || day < 0 || day > monthLength[month - 1])
					return new Result(State.ERROR, IMessages.INSTANCE.mdc_validation__value_invalid());
				if (isRequired)
					return new Result(State.SUCCESS, "");
				else
					return new Result(State.NONE, "");

			};
		}
	}
}
