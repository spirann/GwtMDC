package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.constants.HtmlElements;

public class H2 extends Text {

	public H2() {
		super(HtmlElements.H2.createElement());
	}

	public H2(final String primaryClass) {
		super(HtmlElements.H2.createElement(), primaryClass);
	}
	
	public H2(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.H2.createElement(), primaryClass, initialClasses);
	}
}
