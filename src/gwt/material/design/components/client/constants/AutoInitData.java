package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum AutoInitData implements CssType {

	NONE(""),
	MDC_RIPPLE("MDCRipple");

    private final String cssClass;

    AutoInitData(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static AutoInitData fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, AutoInitData.class, NONE);
    }
}
