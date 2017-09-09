package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;

public class Button extends MaterialWidget {

	public Button() {
		super(HtmlElements.BUTTON.createElement());
	}
	
	public Button(final String primaryClass) {
		super(HtmlElements.BUTTON.createElement(), primaryClass);
	}
	
	public Button(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.BUTTON.createElement(), primaryClass, initialClasses);
	}
}
