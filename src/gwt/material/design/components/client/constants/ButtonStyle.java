package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum ButtonStyle implements CssType {

	ACCENT(CssName.MDC_BUTTON_ACCENT),
	COMPACT(CssName.MDC_BUTTON_COMPACT),
	DENSE(CssName.MDC_BUTTON_DENSE),
	PRIMARY(CssName.MDC_BUTTON_PRIMARY);

    private final String cssClass;

    ButtonStyle(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static ButtonStyle fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, ButtonStyle.class, ACCENT);
    }
}
