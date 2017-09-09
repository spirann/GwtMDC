package gwt.material.design.components.client.ui.html;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasTypography;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.HasFor;
import gwt.material.design.components.client.constants.Typography;

public class Text extends MaterialWidget implements HasText, HasTypography, HasFor {

	protected final TextMixin<Text> textMixin = new TextMixin<>(this);
	protected final TypeMixin<Text, Typography> typographiMixin = new TypeMixin<>(this);
	protected final AttributeMixin<Text> forMixin = new AttributeMixin<>(this, "for");

	protected Text(Element element) {
		super(element);
	}

	protected Text(Element element, String primaryClass) {
		super(element, primaryClass);
	}

	protected Text(Element element, String primaryClass, String... initialClass) {
		super(element, primaryClass, initialClass);
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

	@Override
	public void setFor(String elementId) {
		forMixin.setAttribute(elementId);
	}
}
