package gwt.material.design.components.client.base;

import gwt.material.design.components.client.validation.TextFieldValidation;

public interface HasTextFieldValidation {

	public void setValidation(TextFieldValidation validation);
	
	public TextFieldValidation getValidation();
	
}
