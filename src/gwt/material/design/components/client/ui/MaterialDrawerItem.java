package gwt.material.design.components.client.ui;


import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialDrawerItem extends MaterialWidget implements HasType<ButtonType>, HasHref, HasText, HasIcon {


	private MaterialIcon icon = new MaterialIcon();
	private Label label = new Label();
	
	private final TextMixin<Label> textMixin = new TextMixin<>(label);
	private final AttributeMixin<MaterialDrawerItem> hrefMixin = new AttributeMixin<>(this, "href");
	private final AttributeMixin<MaterialDrawerItem> targetMixin = new AttributeMixin<>(this, "target");
	private final TypeMixin<MaterialDrawerItem, ButtonType> typeMixin = new TypeMixin<>(this);
	
	public MaterialDrawerItem() {
		super(HtmlElements.LI.createElement(), CssName.MDC_LIST_ITEM);
		setAutoInitData("MDCRipple");
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		//icon.getElement().setAttribute("aria-hidden","true");
		icon.setTextColor(Color.MDC_THEME_TEXT_PRIMARY_ON_BACKGROUND);
		icon.addStyleName(CssName.MDC_LIST_ITEM_START_DETAIL);
		
		add(icon);
		add(label);
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

	@Override
	public IconType getIcon() {
		return icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		icon.setType(iconType);
	}
}
