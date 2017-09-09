package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Header extends MaterialWidget {

	public Header() {
		super(HtmlElements.HEADER.createElement());
	}

	public Header(final String primaryClass) {
		super(HtmlElements.HEADER.createElement(), primaryClass);
	}
	
	public Header(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.HEADER.createElement(), primaryClass, initialClasses);
	}
}
