package gwt.material.design.components.client.ui;

import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialListItem extends MaterialWidget implements HasHref, HasText, HasIcon {

	private MaterialIcon icon = new MaterialIcon();
	private Label label = new Label();

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	private final TextMixin<Label> textMixin = new TextMixin<>(label);
	private final AttributeMixin<MaterialListItem> hrefMixin = new AttributeMixin<>(this, "href");
	private final AttributeMixin<MaterialListItem> targetMixin = new AttributeMixin<>(this, "target");
	private final AttributeMixin<MaterialIcon> ariaHiddenMixin = new AttributeMixin<>(icon, "aria-hidden");

	private boolean initialized = false;

	public MaterialListItem() {
		super(HtmlElements.LI.createElement(), CssName.MDC_LIST_ITEM);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			ariaHiddenMixin.setAttribute(true);
			icon.addStyleName(CssName.MDC_LIST_ITEM_START_DETAIL);

			if (getRipple() == null) {
				setRipple(Ripple.ON_BACKGROUND);
			}

			add(icon);
			add(label);

			initialized = true;

		}
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
	public IconType getIcon() {
		return icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		icon.setType(iconType);
	}

	@Override
	public void setTextColor(Color color) {
		label.setTextColor(color);
	}

	public void setIconColor(Color color) {
		icon.setTextColor(color);
	}
}
