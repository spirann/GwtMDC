package gwt.material.design.components.client.resources.message;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
public interface IMessages extends Messages {

	public static final IMessages INSTANCE = GWT.create(IMessages.class);
	
	@DefaultMessage("Months: 1 to 12")
	@AlternateMessage({
		"1", "January", 
		"2", "February", 
		"3", "March", 
		"4", "April", 
		"5", "May", 
		"6", "June", 
		"7", "July", 
		"8", "August", 
		"9", "September", 
		"10", "October", 
		"11", "November", 
		"12", "December"})
	String mdc_calendar_full_month(@Select int month);
	
	@DefaultMessage("Months: 1 to 12")
	@AlternateMessage({
		"1", "Jan", 
		"2", "Feb", 
		"3", "Mar", 
		"4", "Apr", 
		"5", "May", 
		"6", "Jun", 
		"7", "Jul", 
		"8", "Aug", 
		"9", "Sep", 
		"10", "Oct", 
		"11", "Nov", 
		"12", "Dec"})
	String mdc_calendar_short_month(@Select int month);
	
	@DefaultMessage("Week 1 to 7")
	@AlternateMessage({
		"1", "Sunday", 
		"2", "Monday", 
		"3", "Tuesday", 
		"4", "Wednesday", 
		"5", "Thursday", 
		"6", "Friday", 
		"7", "Saturday"})
	String mdc_calendar_full_week(@Select int day);
	
	@DefaultMessage("Week 1 to 7")
	@AlternateMessage({
		"1", "Sun", 
		"2", "Mon", 
		"3", "Tue", 
		"4", "Wed", 
		"5", "Thu", 
		"6", "Fri", 
		"7", "Sat"})
	String mdc_calendar_short_week(@Select int day);
	
	@DefaultMessage("Week 1 to 7")
	@AlternateMessage({
		"1", "S", 
		"2", "M", 
		"3", "T", 
		"4", "W", 
		"5", "T", 
		"6", "F", 
		"7", "S"})
	String mdc_calendar_letter_week(@Select int day);
	
	@DefaultMessage("{0}, {1} {2}")
	String mdc_calendar_header_day(String weekDay, String month, int day);
	
	@DefaultMessage("{1} {0}")
	String mdc_calendar_body_month(String month, int year);
	
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
	
	@DefaultMessage("The value is invalid")
	String mdc_validation__value_invalid();
}
