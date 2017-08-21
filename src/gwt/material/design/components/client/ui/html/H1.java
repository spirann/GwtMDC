package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class H1 extends MaterialWidget {

	public H1() {
		super(HtmlElements.H1.createElement());
	}

	public H1(final String cssClass) {
		super(HtmlElements.H1.createElement(), cssClass);
	}
}
