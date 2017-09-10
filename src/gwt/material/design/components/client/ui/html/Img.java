package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Img extends MaterialWidget {

	public Img() {
		super(HtmlElements.IMG.createElement());
	}

	public Img(final String primaryClass) {
		super(HtmlElements.IMG.createElement(), primaryClass);
	}

	public Img(final String primaryClass, final String... initialClasses) {
		super(HtmlElements.IMG.createElement(), primaryClass, initialClasses);
	}

}
