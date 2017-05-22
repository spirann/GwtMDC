package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum Typography implements CssType {

	DISPLAY_4(CssName.MDC_TYPOGRAPHY_DISPLAY_4),
	DISPLAY_3(CssName.MDC_TYPOGRAPHY_DISPLAY_3),
	DISPLAY_2(CssName.MDC_TYPOGRAPHY_DISPLAY_2),
	DISPLAY_1(CssName.MDC_TYPOGRAPHY_DISPLAY_1),
	HEADLINE(CssName.MDC_TYPOGRAPHY_HEADLINE),
	TITLE(CssName.MDC_TYPOGRAPHY_TITLE),
	SUBHEADING_2(CssName.MDC_TYPOGRAPHY_SUBHEADING_2),
	SUBHEADING_1(CssName.MDC_TYPOGRAPHY_SUBHEADING_1),
	BODY_2(CssName.MDC_TYPOGRAPHY_BODY_2),
	BODY_1(CssName.MDC_TYPOGRAPHY_BODY_1),
	CAPTION(CssName.MDC_TYPOGRAPHY_CAPTION);

    private final String cssClass;

    Typography(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Typography fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Typography.class, BODY_1);
    }
}
