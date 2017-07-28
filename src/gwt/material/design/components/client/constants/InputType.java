package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

public enum InputType implements CssType {

	BUTTON("button"),
	CHECKBOX("checkbox"),
	COLOR("color"),
	DATE("date"),
	DATETIME_LOCAL("datetime-local"),
	EMAIL("email"),
	FILE("file"),
	HIDDEN("hidden"),
	NUMBER("number"),
	PASSWORD("password"),
	RADIO("radio"),
	RANGE("range"),
	RESET("reset"),
	SEARCH("search"),
	SUBMIT("submit"),
	TEL("tel"),
	TEXT("text"),
	TIME("time"),
	URL("url"),
	WEEK("week");

    private final String cssClass;

    InputType(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static InputType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, InputType.class, TEXT);
    }
}
