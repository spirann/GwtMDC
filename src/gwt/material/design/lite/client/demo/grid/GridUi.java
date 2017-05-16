package gwt.material.design.lite.client.demo.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.lite.client.ui.MaterialCodePanel;

public class GridUi extends Composite {

	private static ButtonUiUiBinder uiBinder = GWT.create(ButtonUiUiBinder.class);

	interface ButtonUiUiBinder extends UiBinder<Widget, GridUi> {
	}

	@UiField
	MaterialCodePanel code;

	public GridUi() {
		initWidget(uiBinder.createAndBindUi(this));
		code.setText(getExample());
	}

	private String getExample() {

		final StringBuilder example = new StringBuilder();
		example.append("<mdl:MaterialGrid backgroundColor=\"ACCENT\">\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"1\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"4\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"4\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"4\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"4\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"4\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"4\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"6\" colsTablet=\"8\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"6 (Tablet 8)\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"4\" colsTablet=\"6\" padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"4 (Tablet 6)\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"2\" colsPhone=\"4\"  padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"2 (Phone 4)\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" colsDesktop=\"6\" colsTablet=\"5\" colsPhone=\"2\"  padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"(Desktop 6) (Tablet 5) (Phone 2)\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" colsDesktop=\"4\" colsTablet=\"2\" colsPhone=\"1\"  padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"(Desktop 4) (Tablet 2) (Phone 1)\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" colsDesktop=\"2\" colsTablet=\"1\" colsPhone=\"1\"  padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"(Desktop 2) (Tablet 1) (Phone 1)\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"3\" offset=\"1\"  padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"3, offeset 1\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" colsDesktop=\"11\" colsTablet=\"6\" colsPhone=\"1\" offsetDesktop=\"1\" offsetTablet=\"2\" offsetPhone=\"3\"  padding=\"8\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"(Desktop 11) (Tablet 6) (Phone 1) (Offset Desktop 1) (Offset Tablet 2) (Offset Phone 3)\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"2\" padding=\"8\" hideOn=\"DESKTOP\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"	Hide on Desktop\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"2\" padding=\"8\" hideOn=\"TABLET\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"Hide on Tablet\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("\t\t<mdl:MaterialCell height=\"120px\" backgroundColor=\"PRIMARY\" cols=\"2\" padding=\"8\" hideOn=\"PHONE\">\n");
		example.append("\t\t\t\t<mdl:MaterialLabel textColor=\"PRIMARY_CONTRAST\" text=\"Hide on Phone\" />\n");
		example.append("\t\t</mdl:MaterialCell>\n");
		example.append("</mdl:MaterialGrid>\n");

		return example.toString();
	}
}
