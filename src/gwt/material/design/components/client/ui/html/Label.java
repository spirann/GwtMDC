package gwt.material.design.components.client.ui.html;


import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasTypography;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Typography;

public class Label extends MaterialWidget implements HasText, HasTypography {

	private final TextMixin<Label> textMixin = new TextMixin<>(this);
	private final TypeMixin<Label, Typography> typographiMixin = new TypeMixin<>(this);

	public Label() {
		super(HtmlElements.LABEL.createElement(), CssName.MDC_TYPOGRAPHY);
	}

	@Override
	public String getText() {
		return textMixin.getText();
	}

	@Override
	public void setText(String text) {
		textMixin.setText(text);
	}

	@Override
	public void setTypography(Typography typography) {
		typographiMixin.setType(typography);
	}

	@Override
	public Typography getTypography() {
		return typographiMixin.getType();
	}

}
