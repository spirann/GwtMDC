package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.MdcAttribute;

public class MaterialToolbarIcon extends MaterialWidget implements HasType<IconType>, HasHref {

	private final AttributeMixin<MaterialToolbarIcon> hrefMixin = new AttributeMixin<>(this, "href");
	private final AttributeMixin<MaterialToolbarIcon> targetMixin = new AttributeMixin<>(this, "target");
	
	private IconType type;

	public MaterialToolbarIcon() {
		super(Document.get().createElement(HtmlElements.A), CssName.MDC_TOOLBAR_ICON_MENU, CssName.MDC_RIPPLE_SURFACE, CssName.MDC_RIPPLE_SURFACE_ACCENT);
		getElement().setAttribute(MdcAttribute.DATA_MDC_RIPPLE_IS_UNBOUNDED, "");
	}

	
	// toolbar-nav-link__text
	
	public MaterialToolbarIcon(final IconType type){
		this();
		setType(type);
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