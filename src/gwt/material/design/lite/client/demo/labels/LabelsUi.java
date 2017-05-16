package gwt.material.design.lite.client.demo.labels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.lite.client.constants.LabelType;
import gwt.material.design.lite.client.ui.MaterialCodePanel;
import gwt.material.design.lite.client.utils.StringUtils;

public class LabelsUi extends Composite {

	private static ButtonUiUiBinder uiBinder = GWT.create(ButtonUiUiBinder.class);

	interface ButtonUiUiBinder extends UiBinder<Widget, LabelsUi> {
	}

	@UiField
	MaterialCodePanel code;
	
	public LabelsUi() {
		initWidget(uiBinder.createAndBindUi(this));

		final StringBuilder example = new StringBuilder();
		for(LabelType labelType : LabelType.values()){
			example.append(getExample(labelType));
		}
		code.setText(example.toString());
	}

	private String getExample(final LabelType labelType) {

		final String title = StringUtils.capitalize(labelType.toString().replaceAll("_", " ").toLowerCase());
		final String type = labelType.toString();

		final StringBuilder example = new StringBuilder();
		example.append("\n<!-- " + title + " -->\n");
		example.append("<mdl:MaterialLabel text=\"" + title + "\" type=\"" + type +"\"/>");

		return example.toString();
	}
}
