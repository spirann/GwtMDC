package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum CaptionType implements CssType, Type {

	ONE_LINE(""), TWO_LINES(CssName.MDC_GRID_LIST_TWO_LINE_CAPTION);

	private final String cssClass;

	CaptionType(final String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String getCssName() {
		return cssClass;
	}

	public static CaptionType fromStyleName(final String styleName) {
		return EnumHelper.fromStyleName(styleName, CaptionType.class, ONE_LINE);
	}
}
