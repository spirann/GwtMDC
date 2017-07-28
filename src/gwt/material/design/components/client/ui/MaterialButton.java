package gwt.material.design.components.client.ui;


import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialButton extends MaterialWidget implements HasType<ButtonType>, HasHref, HasText {

	private final TextMixin<MaterialButton> textMixin = new TextMixin<>(this);
	private final AttributeMixin<MaterialButton> hrefMixin = new AttributeMixin<>(this, "href");
	private final AttributeMixin<MaterialButton> targetMixin = new AttributeMixin<>(this, "target");
	private final TypeMixin<MaterialButton, ButtonType> typeMixin = new TypeMixin<>(this);

	public MaterialButton() {
		super(HtmlElements.BUTTON.createElement(), CssName.MDC_BUTTON);
		setAutoInitData("MDCRipple");
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
	public void setHref(String href) {
		hrefMixin.setAttribute(href);
	}

	@Override
	public String getHref() {
		return hrefMixin.getAttribute();
	}

	@Override
	public void setTarget(String target) {
		targetMixin.setAttribute(target);
	}

	@Override
	public String getTarget() {
		return targetMixin.getAttribute();
	}

	@Override
	public void setType(ButtonType type) {
		typeMixin.setType(type);
	}

	@Override
	public ButtonType getType() {
		return typeMixin.getType();
	}
}
