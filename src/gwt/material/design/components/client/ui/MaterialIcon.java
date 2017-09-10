package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Icon;

public class MaterialIcon extends Icon implements HasType<IconType>, HasHref {

	protected final HrefMixin<MaterialIcon> hrefMixin = new HrefMixin<>(this);
	protected final IconMixin<MaterialIcon> iconMixin = new IconMixin<>(this);

	public MaterialIcon() {
		super(CssName.MATERIAL_ICONS);
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
		hrefMixin.setHref(href);
	}

	@Override
	public String getHref() {
		return hrefMixin.getHref();
	}

	@Override
	public void setTarget(String target) {
		hrefMixin.setTarget(target);
	}

	@Override
	public String getTarget() {
		return hrefMixin.getTarget();
	}

}
