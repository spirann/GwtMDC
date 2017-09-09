package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Li extends MaterialWidget {

	public Li() {
		super(HtmlElements.LI.createElement());
	}

	public Li(final String primaryClass) {
		super(HtmlElements.LI.createElement(), primaryClass);
	}

	public Li(final String primaryClass, final String... initialClasses) {
		super(HtmlElements.LI.createElement(), primaryClass, initialClasses);
	}
}
