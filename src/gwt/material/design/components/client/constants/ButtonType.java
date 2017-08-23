package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum ButtonType implements CssType {

	FLAT(""),
	RAISED(CssName.MDC_BUTTON_RAISED),
	DENSE(CssName.MDC_BUTTON_DENSE),
	DENSE_RAISED(CssName.MDC_BUTTON_RAISED + " " + CssName.MDC_BUTTON_DENSE),
	COMPACT(CssName.MDC_BUTTON_COMPACT),
	COMPACT_RAISED(CssName.MDC_BUTTON_RAISED + " " + CssName.MDC_BUTTON_COMPACT),
	PRIMARY(CssName.MDC_BUTTON_PRIMARY),
	PRIMARY_RAISED(CssName.MDC_BUTTON_RAISED + " " + CssName.MDC_BUTTON_PRIMARY),
	SECONDARY(CssName.MDC_BUTTON_SECONDARY),
	SECONDARY_RAISED(CssName.MDC_BUTTON_RAISED + " " + CssName.MDC_BUTTON_SECONDARY);

    private final String cssClass;

    ButtonType(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static ButtonType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, ButtonType.class, RAISED);
    }
}
