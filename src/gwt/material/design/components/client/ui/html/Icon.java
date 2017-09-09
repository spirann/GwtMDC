package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Icon extends MaterialWidget {

	public Icon() {
		super(HtmlElements.I.createElement());
	}

	public Icon(final String primaryClass) {
		super(HtmlElements.I.createElement(), primaryClass);
	}

	public Icon(final String primaryClass, String... initialClasses) {
		super(HtmlElements.I.createElement(), primaryClass, initialClasses);
	}
}
