package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Footer extends MaterialWidget {

	public Footer() {
		super(HtmlElements.FOOTER.createElement());
	}

	public Footer(final String primaryClass) {
		super(HtmlElements.FOOTER.createElement(), primaryClass);
	}
	
	public Footer(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.FOOTER.createElement(), primaryClass, initialClasses);
	}
}
