package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;

public class MaterialIcon extends MaterialWidget implements HasType<IconType>, HasHref {

	protected final AttributeMixin<MaterialIcon> hrefMixin = new AttributeMixin<>(this, "href");
	protected final AttributeMixin<MaterialIcon> targetMixin = new AttributeMixin<>(this, "target");
	protected final IconMixin<MaterialIcon> iconMixin = new IconMixin<>(this);

	public MaterialIcon() {
		super(HtmlElements.I.createElement(), CssName.MATERIAL_ICONS);
		setDisabledClass(CssName.MATERIAL_ICONS_DISABLED);
		setWidth("24px");
		setHeight("24px");
		setPadding(8);
	}

	public MaterialIcon(final IconType type) {
		this();
		setType(type);
	}

	@Override
	public void setType(IconType type) {
		iconMixin.setIcon(type);
	}

	@Override
	public IconType getType() {
		return iconMixin.getIcon();
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
