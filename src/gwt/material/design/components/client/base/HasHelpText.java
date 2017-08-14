package gwt.material.design.components.client.base;

public interface HasHelpText {

	public void setHelpText(final String text);
	
	public String getHelpText();
	
	public void setHelpTextPersistent(boolean persistent);
	
	public boolean isHelpTextPersistent();
		
	public void setHelpTextValidation(boolean validation);
	
	public boolean isHelpTextValidation();
	
}
