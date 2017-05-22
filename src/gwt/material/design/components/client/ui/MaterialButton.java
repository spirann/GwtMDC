package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;

public class MaterialButton extends MaterialWidget implements HasType<IconType>, HasHref, HasText {

	private final TextMixin<MaterialButton> textMixin = new TextMixin<>(this);
	private final AttributeMixin<MaterialButton> hrefMixin = new AttributeMixin<>(this, "href");
	private final AttributeMixin<MaterialButton> targetMixin = new AttributeMixin<>(this, "target");
	
	private IconType type;

	public MaterialButton() {
		super(Document.get().createElement(HtmlElements.BUTTON), CssName.MDC_BUTTON, CssName.MDC_BUTTON_RAISED);
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
	public void setType(IconType type) {
		this.type = type;
		getElement().setInnerHTML(type.getCssName());
	}

	@Override
	public IconType getType() {
		return type;
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

}
