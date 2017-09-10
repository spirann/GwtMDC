package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.AutoInitData;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FabType;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialFab extends Button implements HasType<FabType>, HasHref, HasIcon {

	protected Span icon = new Span(CssName.MDC_FAB_ICON);

	protected final HrefMixin<MaterialFab> hrefMixin = new HrefMixin<>(this);
	protected final TypeMixin<MaterialFab, FabType> typeMixin = new TypeMixin<>(this);
	protected final IconMixin<Span> iconMixin = new IconMixin<>(icon);

	private boolean initialized = false;

	public MaterialFab() {
		super(CssName.MDC_FAB, CssName.MATERIAL_ICONS);
		setAutoInitData(AutoInitData.MDC_RIPPLE);
		setRipple(Ripple.DEFAULT);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {			
			add(icon);
			initialized = true;
		}
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

	@Override
	public void setType(FabType type) {
		typeMixin.setType(type);
	}

	@Override
	public FabType getType() {
		return typeMixin.getType();
	}

	@Override
	public IconType getIcon() {
		return iconMixin.getIcon();
	}

	@Override
	public void setIcon(IconType iconType) {
		iconMixin.setIcon(iconType);
	}
}
