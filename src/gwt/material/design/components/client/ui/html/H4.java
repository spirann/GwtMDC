package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.constants.HtmlElements;

public class H4 extends Text {

	public H4() {
		super(HtmlElements.H4.createElement());
	}

	public H4(final String primaryClass) {
		super(HtmlElements.H4.createElement(), primaryClass);
	}
	
	public H4(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.H4.createElement(), primaryClass, initialClasses);
	}
}
