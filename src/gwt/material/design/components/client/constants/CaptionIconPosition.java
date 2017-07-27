package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum CaptionIconPosition implements CssType, Type {

	NONE(""),
	START(CssName.MDC_GRID_LIST_ICON_ALIGN_START), 
	END(CssName.MDC_GRID_LIST_ICON_ALIGN_END);

	private final String cssClass;

	CaptionIconPosition(final String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String getCssName() {
		return cssClass;
	}

	public static CaptionIconPosition fromStyleName(final String styleName) {
		return EnumHelper.fromStyleName(styleName, CaptionIconPosition.class, NONE);
	}
}
