package gwt.material.design.components.client.ui;

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Header;

public class MaterialDrawerHeader extends Header {

	protected Div headerContent = new Div(CssName.MDC_TEMPORARY_DRAWER_HEADER_CONTENT);

	private boolean initialized = false;

	public MaterialDrawerHeader() {
		super(CssName.MDC_TEMPORARY_DRAWER_HEADER);
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
	
	@Override
	public Widget getWidget(int index) {
		return headerContent.getWidget(index);
	}

	@Override
	public int getWidgetCount() {
		return headerContent.getWidgetCount();
	}
	
	@Override
	public int getWidgetIndex(Widget child) {
		return headerContent.getWidgetIndex(child);
	}
}
