package gwt.material.design.components.client.resources.message;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface IMessages extends Messages {

	public static final IMessages INSTANCE = GWT.create(IMessages.class);
	
	@DefaultMessage("Password is very weak")
	String mdc_validation__password__very_weak();
		
	@DefaultMessage("Password is weak")
	String mdc_validation__password__weak();
	
	@DefaultMessage("Password is medium")
	String mdc_validation__password__midium();

	@DefaultMessage("Password is strong")
	String mdc_validation__password__strong();
	
	@DefaultMessage("The text should has {0} or more characters")
	String mdc_validation__less_than_min_length(int minLenght);
	
	@DefaultMessage("The text should has {0} or less characters")
	String mdc_validation__more_than_max_length(int maxLenght);
	
	@DefaultMessage("This field cannot be empty")
	String mdc_validation__required();
}
