package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasTypography;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Typography;

public class MaterialLabel extends MaterialWidget implements HasText, HasTypography {

	private final TextMixin<MaterialLabel> textMixin = new TextMixin<>(this);
	private final TypeMixin<MaterialLabel, Typography> typographiMixin = new TypeMixin<>(this);
	
	public MaterialLabel() {
		super(Document.get().createElement(HtmlElements.SPAN), CssName.MDC_TYPOGRAPHY_ADJUST_MARGIN);
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
