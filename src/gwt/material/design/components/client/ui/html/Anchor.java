package gwt.material.design.components.client.ui.html;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Anchor extends MaterialWidget {

	public Anchor(){
		super(HtmlElements.A.createElement());
	}
	
	public Anchor(final String primaryClass) {
		super(HtmlElements.A.createElement(), primaryClass);
	}
	
	public Anchor(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.A.createElement(), primaryClass, initialClasses);
	}
	
}
