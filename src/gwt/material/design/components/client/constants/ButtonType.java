package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum ButtonType implements CssType {

	FLAT(""),
	FAB(CssName.MDC_BUTTON_ACCENT),
	RAISED(CssName.MDC_BUTTON_RAISED);

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
