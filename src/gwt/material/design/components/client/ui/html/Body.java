package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Body extends MaterialWidget {

	public Body(){
		super(HtmlElements.BODY.createElement());
	}
	
	public Body(final String primaryClass) {
		super(HtmlElements.BODY.createElement(), primaryClass);
	}
	
	public Body(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.BODY.createElement(), primaryClass, initialClasses);
	}
}
