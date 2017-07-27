package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum AspectRatio implements CssType {

	ASPECT_1x1(""),
	ASPECT_16x9("16x9"),
	ASPECT_2x3("2x3"),
	ASPECT_3x2("3x2"),
	ASPECT_4x3("4x3"),
	ASPECT_3x4("3x4");

    private final String cssClass;

    AspectRatio(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static AspectRatio fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, AspectRatio.class, ASPECT_1x1);
    }
}
