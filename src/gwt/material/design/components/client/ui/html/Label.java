package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.constants.HtmlElements;

public class Label extends Text {

	public Label() {
		super(HtmlElements.LABEL.createElement());
	}

	public Label(final String primaryClass) {
		super(HtmlElements.LABEL.createElement(), primaryClass);
	}

	public Label(final String primaryClass, final String... initialClasses) {
		super(HtmlElements.LABEL.createElement(), primaryClass, initialClasses);
	}
}
