package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Main extends MaterialWidget {

	public Main() {
		super(HtmlElements.MAIN.createElement());
	}

	public Main(final String primaryClass) {
		super(HtmlElements.MAIN.createElement(), primaryClass);
	}

	public Main(final String primaryClass, final String... initialClasses) {
		super(HtmlElements.MAIN.createElement(), primaryClass, initialClasses);
	}
}
