package gwt.material.design.components.client.ui;


import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialListSubheader extends Label {

	public MaterialListSubheader() {
		super(HtmlElements.H3.createElement());
		setInitialClasses(CssName.MDC_LIST_GROUP_SUBHEADER);
	}
}
