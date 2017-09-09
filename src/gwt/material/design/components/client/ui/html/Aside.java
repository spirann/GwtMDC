package gwt.material.design.components.client.ui.html;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Aside extends MaterialWidget {

	public Aside(){
		super(HtmlElements.ASIDE.createElement());
	}
	
	public Aside(final String primaryClass) {
		super(HtmlElements.ASIDE.createElement(), primaryClass);
	}
	
	public Aside(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.ASIDE.createElement(), primaryClass, initialClasses);
	}
	
}
