package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class H2 extends MaterialWidget {

	public H2() {
		super(HtmlElements.H2.createElement());
	}

	public H2(final String cssClass) {
		super(HtmlElements.H2.createElement(), cssClass);
	}
}
