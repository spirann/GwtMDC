package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FabType;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialFab extends MaterialWidget implements HasType<FabType>, HasHref, HasIcon {

	private final AttributeMixin<MaterialFab> hrefMixin = new AttributeMixin<>(this, "href");
	private final AttributeMixin<MaterialFab> targetMixin = new AttributeMixin<>(this, "target");
	private final TypeMixin<MaterialFab, FabType> typeMixin = new TypeMixin<>(this);

	private Span icon = new Span(CssName.MDC_FAB_ICON);

	public MaterialFab() {
		super(HtmlElements.BUTTON.createElement(), CssName.MDC_FAB, CssName.MATERIAL_ICONS);
		setAutoInitData("MDCRipple");
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		add(icon);
		if (getRipple() == null) {
			setRipple(Ripple.DEFAULT);
		}
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
	public void setType(FabType type) {
		typeMixin.setType(type);
	}

	@Override
	public FabType getType() {
		return typeMixin.getType();
	}

	@Override
	public IconType getIcon() {
		final String iconTypeClass = icon.getElement().getInnerText();
		return iconTypeClass.isEmpty() ? null : IconType.valueOf(iconTypeClass);
	}

	@Override
	public void setIcon(IconType iconType) {
		if (iconType == null) {
			icon.getElement().setInnerText("");
		} else {
			icon.getElement().setInnerText(iconType.getCssName());
		}
	}
}
