package gwt.material.design.components.client.ui;

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.html.Div;

public class MaterialDrawerHeader extends MaterialWidget {

	protected Div headerContent = new Div(CssName.MDC_TEMPORARY_DRAWER_HEADER_CONTENT);

	private boolean initialized = false;

	public MaterialDrawerHeader() {
		super(HtmlElements.HEADER.createElement(), CssName.MDC_TEMPORARY_DRAWER_HEADER);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			super.add(headerContent);
			initialized = true;
		}
		
	}

	@Override
	public void add(Widget child) {
		headerContent.add(child);
	}

}
