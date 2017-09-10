package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Anchor;

public class MaterialToolbarIcon extends Anchor implements HasType<IconType>, HasHref {

	protected final AttributeMixin<MaterialToolbarIcon> hrefMixin = new AttributeMixin<>(this, "href");
	protected final AttributeMixin<MaterialToolbarIcon> targetMixin = new AttributeMixin<>(this, "target");
	protected final IconMixin<MaterialToolbarIcon> iconMixin = new IconMixin<>(this);
	
	private boolean initialized = false;

	public MaterialToolbarIcon() {
		super(CssName.MDC_TOOLBAR_ICON_MENU);
	}

	public MaterialToolbarIcon(final IconType type) {
		this();
		setType(type);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			if (getRipple() == null) {
				setRipple(Ripple.SECONDARY);
			}

			initialized = true;

		}
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
