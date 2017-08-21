package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class H3 extends MaterialWidget {

	public H3() {
		super(HtmlElements.H3.createElement());
	}

	public H3(final String cssClass) {
		super(HtmlElements.H3.createElement(), cssClass);
	}
}
