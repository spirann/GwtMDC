package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum CaptionPosition implements CssType, Type {

	START(CssName.MDC_GRID_LIST_HEADER_CAPTION), END("");

	private final String cssClass;

	CaptionPosition(final String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String getCssName() {
		return cssClass;
	}

	public static CaptionPosition fromStyleName(final String styleName) {
		return EnumHelper.fromStyleName(styleName, CaptionPosition.class, END);
	}
}
