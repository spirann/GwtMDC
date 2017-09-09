package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Html extends MaterialWidget {
	
	public Html(){
		super(HtmlElements.HTML.createElement());
	}
	
	public Html(final String primaryClass) {
		super(HtmlElements.HTML.createElement(), primaryClass);
	}
	
	public Html(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.HTML.createElement(), primaryClass, initialClasses);
	}
}
