package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.constants.HtmlElements;

public class H1 extends Text {

	public H1() {
		super(HtmlElements.H1.createElement());
	}

	public H1(final String primaryClass) {
		super(HtmlElements.H1.createElement(), primaryClass);
	}
	
	public H1(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.H1.createElement(), primaryClass, initialClasses);
	}
}
