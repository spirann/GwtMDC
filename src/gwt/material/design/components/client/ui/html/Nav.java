package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Nav extends MaterialWidget {

	public Nav() {
		super(HtmlElements.NAV.createElement());
	}

	public Nav(final String primaryClass) {
		super(HtmlElements.NAV.createElement(), primaryClass);
	}

	public Nav(final String primaryClass, final String... initialClasses) {
		super(HtmlElements.NAV.createElement(), primaryClass, initialClasses);
	}
}
