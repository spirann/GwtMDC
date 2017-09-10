package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.MaterialDrawerBase;
import gwt.material.design.components.client.constants.CssName;

public class MaterialDrawerPermanent extends MaterialDrawerBase {

	public MaterialDrawerPermanent() {
		super(CssName.MDC_PERMANENT_DRAWER);
	}

	public static class Spacer extends MaterialDrawerBase.Spacer {

		public Spacer() {
			super(CssName.MDC_PERMANENT_DRAWER_TOOLBAR_SPACER);
		}

	}

}
