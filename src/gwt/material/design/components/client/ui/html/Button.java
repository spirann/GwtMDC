package gwt.material.design.components.client.ui.html;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Button extends MaterialWidget {

	public Button(){
		super(HtmlElements.BUTTON.createElement());
	}
	
	public Button(final String cssClass) {
		super(HtmlElements.BUTTON.createElement(), cssClass);
	}
	
	public Button(final String cssClass, String ... otherClasses) {
		super(HtmlElements.BUTTON.createElement(), cssClass, otherClasses);
	}
}
