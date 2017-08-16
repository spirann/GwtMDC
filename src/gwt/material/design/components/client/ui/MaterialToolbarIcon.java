package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;

public class MaterialToolbarIcon extends MaterialWidget implements HasType<IconType>, HasHref {

	protected final AttributeMixin<MaterialToolbarIcon> hrefMixin = new AttributeMixin<>(this, "href");
	protected final AttributeMixin<MaterialToolbarIcon> targetMixin = new AttributeMixin<>(this, "target");

	protected IconType type;

	private boolean initialized = false;

	public MaterialToolbarIcon() {
		super(HtmlElements.A.createElement(), CssName.MDC_TOOLBAR_ICON_MENU);
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
				setRipple(Ripple.ACCENT);
			}

			initialized = true;

		}
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
