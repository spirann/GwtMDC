package gwt.material.design.components.client.ui.html;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Pre extends MaterialWidget {

	public Pre(){
		super(HtmlElements.PRE.createElement());
	}
	
	public Pre(final String primaryClass) {
		super(HtmlElements.PRE.createElement(), primaryClass);
	}
	
	public Pre(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.PRE.createElement(), primaryClass, initialClasses);
	}
	
}
