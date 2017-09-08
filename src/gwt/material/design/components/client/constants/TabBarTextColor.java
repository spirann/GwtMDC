package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum TabBarTextColor implements CssType {

	ON_PRIMARY(""),
	PRIMARY("mdc-tab-bar__primary"),
	ON_SECONDARY("mdc-tab-bar__on_secondary"),
	SECONDARY("mdc-tab-bar__secondary"),
	ON_BACKGROUND("mdc-tab-bar__on_background");

    private final String cssClass;

    TabBarTextColor(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static TabBarTextColor fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, TabBarTextColor.class, ON_PRIMARY);
    }
}
