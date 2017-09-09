package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.constants.HtmlElements;

public class H3 extends Text {

	public H3() {
		super(HtmlElements.H3.createElement());
	}

	public H3(final String primaryClass) {
		super(HtmlElements.H3.createElement(), primaryClass);
	}
	
	public H3(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.H3.createElement(), primaryClass, initialClasses);
	}
}
