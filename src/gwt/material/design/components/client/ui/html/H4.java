package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class H4 extends MaterialWidget {

	public H4() {
		super(HtmlElements.H4.createElement());
	}

	public H4(final String cssClass) {
		super(HtmlElements.H4.createElement(), cssClass);
	}
}
