package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Section extends MaterialWidget {

	public Section() {
		super(HtmlElements.SECTION.createElement());
	}

	public Section(final String cssClass) {
		super(HtmlElements.SECTION.createElement(), cssClass);
	}
}
