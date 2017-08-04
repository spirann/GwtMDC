package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum FabType implements CssType {

	DEFAULT(""),
	MINI(CssName.MDC_FAB_MINI),
	FIXED(CssName.MDC_FAB_FIXED),
	PLAIN(CssName.MDC_FAB_PLAIN),
	MINI_PLAIN(CssName.MDC_FAB_MINI + " " + CssName.MDC_FAB_PLAIN),
	FIXED_PLAIN(CssName.MDC_FAB_FIXED + " " + CssName.MDC_FAB_PLAIN);

    private final String cssClass;

    FabType(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static FabType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, FabType.class, DEFAULT);
    }
}

