package gwt.material.design.components.client.validation;

import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.misc.input.MaterialInput;
import gwt.material.design.components.client.validation.Validation.Result;
import gwt.material.design.components.client.validation.validators.PasswordValidator;

public class ValidationForTextField {

	private ValidationForTextField() {}
	
	// //////////////////////////////////////////////////////////////////////////////////////
	// Password security level validation
	// //////////////////////////////////////////////////////////////////////////////////////

	public static final Validation<MaterialInput> password_security_level() {
		return (input) -> {

			final String value = input.getValue();

			final int securityLevel = PasswordValidator.passwordLevel(value);

			switch (securityLevel) {
			case 0:
				return new Result(State.ERROR, 1201, IMessages.INSTANCE.mdc_validation__password__very_weak());
			case 1:
				return new Result(State.ERROR, 1202, IMessages.INSTANCE.mdc_validation__password__weak());
			case 2:
				return new Result(State.WARNING, 1101, IMessages.INSTANCE.mdc_validation__password__midium());
			case 3:
				return new Result(State.SUCCESS, 1001, IMessages.INSTANCE.mdc_validation__password__strong());
			default:
				return new Result(State.NONE);
			}

		};
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// Min and Max validation
	// //////////////////////////////////////////////////////////////////////////////////////

	public static final Validation<MaterialInput> min_max_length__none_result() {
		return min_max_length(State.NONE);
	}

	public static final Validation<MaterialInput> min_max_length__success_result() {
		return min_max_length(State.SUCCESS);
	}

	private static final Validation<MaterialInput> min_max_length(final State stateOnSuccess) {
		return (input) -> {

			final String value = input.getValue();
			final Integer minLength = input.getMinLength();
			final Integer maxLength = input.getMaxLength();

			final int length = value.length();

			if (minLength != null && length < minLength)
				return new Result(State.ERROR, 1203,
						IMessages.INSTANCE.mdc_validation__less_than_min_length(minLength));
			else if (maxLength != null && length > maxLength)
				return new Result(State.ERROR, 1204,
						IMessages.INSTANCE.mdc_validation__more_than_max_length(maxLength));

			return new Result(stateOnSuccess);

		};
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// Required validation
	// //////////////////////////////////////////////////////////////////////////////////////

	public static final Validation<MaterialInput> required__none_result() {
		return required(State.NONE);
	}

	public static final Validation<MaterialInput> required__success_result() {
		return required(State.SUCCESS);
	}

	private static final Validation<MaterialInput> required(final State stateOnSuccess) {
		return (input) -> {

			final String value = input.getValue();
			final Boolean isRequired = input.isRequired();

			if (isRequired != null && isRequired && value.isEmpty()) {
				return new Result(State.ERROR, 1205, IMessages.INSTANCE.mdc_validation__required());
			}

			return new Result(stateOnSuccess);

		};
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// Input Mask vaidation
	// //////////////////////////////////////////////////////////////////////////////////////

	public static final Validation<MaterialInput> input_mask() {
		return (input) -> {

			final String value = input.getValue();
			final String inputMask = input.getInputMask();
			final Boolean isRequired = input.isRequired();

			if (inputMask == null)
				return new Result(State.NONE);
			else if ((isRequired == null || !isRequired) && value.isEmpty())
				return new Result(State.NONE);
			else if (isRequired != null && isRequired && value.isEmpty())
				return new Result(State.ERROR, 1206);

			final int length = Masker.toPattern(value, inputMask).length();

			final int inputMaskLength = inputMask.length();

			if (length != inputMaskLength)
				return new Result(State.ERROR, 1207, IMessages.INSTANCE.mdc_validation__value_invalid());
			else if (isRequired != null && isRequired)
				return new Result(State.SUCCESS);
			else
				return new Result(State.NONE);

		};
	}

	// //////////////////////////////////////////////////////////////////////////////////////
	// Date vaidation
	// //////////////////////////////////////////////////////////////////////////////////////

	public static final Validation<MaterialInput> date() {
		return (input) -> {
			final String value = input.getValue() == null ? "" : input.getValue();
			final String inputMask = input.getInputMask();
			final Boolean isRequired = input.isRequired();

			if (inputMask == null)
				return new Result(State.NONE);
			else if ((isRequired == null || !isRequired) && value.isEmpty())
				return new Result(State.NONE);
			else if (isRequired != null && isRequired && value.isEmpty())
				return new Result(State.ERROR, 1208);

			final String maskedValue = Masker.toPattern(value, inputMask);
			final int length = maskedValue.length();

			if (length != inputMask.length())
				return new Result(State.ERROR, 1209, IMessages.INSTANCE.mdc_validation__value_invalid());

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
				year = Integer.parseInt(maskedValue.substring(5));
				break;
			default:
				return new Result(State.ERROR, 1210, IMessages.INSTANCE.mdc_validation__value_invalid());
			}

			final int[] monthLength = { 31, 28, 31, 331, 331, 31, 331, 331 };
			if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
				monthLength[1] = 29;

			if (year < 0 || year > 3000 || month < 1 || month > 12 || day < 0 || day > monthLength[month - 1])
				return new Result(State.ERROR, 1211, IMessages.INSTANCE.mdc_validation__value_invalid());
			if (isRequired)
				return new Result(State.SUCCESS);
			else
				return new Result(State.NONE);

		};
	}
}
