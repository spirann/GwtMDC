package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum Role implements CssType {

	NONE(""),
	BUTTON("button"),
	SLIDER("slider"),
	TOOLBAR("toolbar"),
	ALERT_DIALOG("alertdialog"),
	PROGRESS_BAR("progressbar"),
	SEPARATOR("separator");

    private final String cssClass;

    Role(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Role fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Role.class, NONE);
    }
}
