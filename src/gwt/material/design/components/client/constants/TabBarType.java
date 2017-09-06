package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum TabBarType implements CssType {

	TEXT(""),
	ICON(CssName.MDC_TAB_BAR_ICON_TABS),
	ICON_WITH_TEXT(CssName.MDC_TAB_BAR_ICONS_WITH_TEXT);

    private final String cssClass;

    TabBarType(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static TabBarType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, TabBarType.class, TEXT);
    }
}
