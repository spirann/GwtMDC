package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.constants.HtmlElements;

public class Code extends Text {

	public Code() {
		super(HtmlElements.CODE.createElement());
	}

	public Code(final String primaryClass) {
		super(HtmlElements.CODE.createElement(), primaryClass);
	}

	public Code(final String primaryClass, final String... initialClasses) {
		super(HtmlElements.CODE.createElement(), primaryClass, initialClasses);
	}

}
