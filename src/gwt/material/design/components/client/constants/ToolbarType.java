package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum ToolbarType implements CssType, Type {

	DEFAULT(""), 
	FIXED(CssName.MDC_TOOLBAR_FIXED), 
	WATERFALL(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_WATERFALL), 
	LASTROW_ONLY(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_LASTROW_ONLY), 
	FLEXIBLE(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_FLEXIBLE), 
	FLEXIBLE_BEHAVIOR(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_FLEXIBLE + " " + CssName.MDC_TOOLBAR_FLEXIBLE_BEHAVIOR),
	WATERFALL_FLEXIBLE(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_WATERFALL + " " + CssName.MDC_TOOLBAR_FLEXIBLE), 
	WATERFALL_FLEXIBLE_BEHAVIOR(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_WATERFALL + " " + CssName.MDC_TOOLBAR_FLEXIBLE + " " + CssName.MDC_TOOLBAR_FLEXIBLE_BEHAVIOR);
	
	private final String cssClass;

	ToolbarType(final String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String getCssName() {
		return cssClass;
	}

	public static Type fromStyleName(final String styleName) {
		return EnumHelper.fromStyleName(styleName, ToolbarType.class, DEFAULT);
	}

}
