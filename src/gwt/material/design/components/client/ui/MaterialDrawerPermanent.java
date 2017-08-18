package gwt.material.design.components.client.ui;

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialDrawerPermanent extends MaterialWidget {

	protected MaterialListGroup content = new MaterialListGroup();

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	private boolean initialized = false;

	public MaterialDrawerPermanent() {
		super(HtmlElements.ASIDE.createElement(), CssName.MDC_PERMANENT_DRAWER);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

		}
	}

	@Override
	public void add(Widget child) {

		if (child instanceof Spacer) {
			super.add(child);
		} else if (child.getElement().getTagName().equalsIgnoreCase(HtmlElements.HEADER.getTag())) {
			super.add(child);
			if (content.getParent() != null) {
				super.add(content);
			}
		} else {
			if (content.getParent() == null) {
				super.add(content);
			}
			content.add(child);
		}

	}

	public static class Spacer extends MaterialWidget {

		public Spacer() {
			super(HtmlElements.DIV.createElement(), CssName.MDC_PERMANENT_DRAWER_TOOLBAR_SPACER);
		}

	}

}
