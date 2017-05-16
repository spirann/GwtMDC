package gwt.material.design.lite.client.demo.buttons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.lite.client.constants.ButtonType;
import gwt.material.design.lite.client.ui.MaterialCodePanel;
import gwt.material.design.lite.client.utils.StringUtils;

public class ButtonsUi extends Composite {

	private static ButtonUiUiBinder uiBinder = GWT.create(ButtonUiUiBinder.class);

	interface ButtonUiUiBinder extends UiBinder<Widget, ButtonsUi> {
	}

	@UiField
	MaterialCodePanel codeFab;

	@UiField
	MaterialCodePanel codeMiniFab;

	@UiField
	MaterialCodePanel codeRaised;
	
	@UiField
	MaterialCodePanel codeFlat;

	@UiField
	MaterialCodePanel codeIcon;

	public ButtonsUi() {
		initWidget(uiBinder.createAndBindUi(this));

		codeFab.setText(getExample(ButtonType.FAB));
		codeMiniFab.setText(getExample(ButtonType.MINI_FAB));
		codeRaised.setText(getExample(ButtonType.RAISED));
		codeFlat.setText(getExample(ButtonType.FLAT));
		codeIcon.setText(getExample(ButtonType.ICON));
	}

	private String getExample(final ButtonType buttonType) {

		final String title = StringUtils.capitalize(buttonType.toString().replaceAll("_", " ").toLowerCase());
		final String type = buttonType.toString();
		final String value;

		switch (buttonType) {
		case FAB:
		case MINI_FAB:
		case ICON:
			value = "iconType=\"ADD\"";
			break;
		case FLAT:
		case RAISED:			
		default:
			value = "text=\"button\"";
			break;
		}

		final StringBuilder example = new StringBuilder();
		example.append("<!-- " + title + " -->\n");
		example.append("<mdl:MaterialButton type=\"" + type + "\" " + value + " /> \n");
		example.append("\n<!-- " + title + " colored -->\n");
		example.append(
				"<mdl:MaterialButton type=\"" + type + "\" " + value + " colorScheme=\"BUTTON_COLORED\"/> \n");
		example.append("\n<!-- " + title + " primary -->\n");
		example.append(
				"<mdl:MaterialButton type=\"" + type + "\" " + value + " colorScheme=\"BUTTON_PRIMARY\"/> \n");
		example.append("\n<!-- " + title + " accent -->\n");
		example.append("<mdl:MaterialButton type=\"" + type + "\" " + value + " colorScheme=\"BUTTON_ACCENT\"/> \n");
		example.append("\n<!-- " + title + " disabled -->\n");
		example.append("<mdl:MaterialButton type=\"" + type + "\" " + value + " enabled=\"false\"/> \n");

		return example.toString();
	}
}
