package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum Ripple implements CssType {

	NONE(""),
	DEFAULT(CssName.MDC_RIPPLE_SURFACE),
	PRIMARY(CssName.MDC_RIPPLE_SURFACE + " " + CssName.MDC_RIPPLE_SURFACE_PRIMARY),
	ACCENT(CssName.MDC_RIPPLE_SURFACE + " " + CssName.MDC_RIPPLE_SURFACE_ACCENT),
	ON_PRIMARY(CssName.MDC_RIPPLE_SURFACE + " " + CssName.MDC_RIPPLE_SURFACE_ON_PRIMARY),
	ON_ACCENT(CssName.MDC_RIPPLE_SURFACE + " " + CssName.MDC_RIPPLE_SURFACE_ON_ACCENT),
	ON_BACKGROUND(CssName.MDC_RIPPLE_SURFACE + " " + CssName.MDC_RIPPLE_SURFACE_ON_BACKGROUND);

    private final String cssClass;

    Ripple(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Ripple fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Ripple.class, DEFAULT);
    }
}
