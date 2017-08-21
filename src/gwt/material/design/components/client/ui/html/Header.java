package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Header extends MaterialWidget {

	public Header() {
		super(HtmlElements.HEADER.createElement());
	}

	public Header(final String cssClass) {
		super(HtmlElements.HEADER.createElement(), cssClass);
	}
}
