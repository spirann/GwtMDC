package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Ul extends MaterialWidget {

	public Ul() {
		super(HtmlElements.UL.createElement());
	}

	public Ul(final String primaryClass) {
		super(HtmlElements.UL.createElement(), primaryClass);
	}

	public Ul(final String primaryClass, final String... initialClasses) {
		super(HtmlElements.UL.createElement(), primaryClass, initialClasses);
	}

}
