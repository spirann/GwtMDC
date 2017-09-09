package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.constants.HtmlElements;

public class Span extends Text {

	public Span() {
		super(HtmlElements.SPAN.createElement());
	}

	public Span(final String primaryClass) {
		super(HtmlElements.SPAN.createElement(), primaryClass);
	}
	
	public Span(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.SPAN.createElement(), primaryClass, initialClasses);
	}
}
