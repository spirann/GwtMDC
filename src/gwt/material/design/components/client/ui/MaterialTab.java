package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Role;

public class MaterialTab extends MaterialWidget implements HasHref, HasText {

	protected final TextMixin<MaterialTab> textMixin = new TextMixin<>(this);
	protected final AttributeMixin<MaterialTab> hrefMixin = new AttributeMixin<>(this, "href");
	protected final AttributeMixin<MaterialTab> ariaControlsMixin = new AttributeMixin<>(this, "aria-controls");
	protected final ApplyStyleMixin<MaterialTab> activeMixin = new ApplyStyleMixin<>(this, CssName.MDC_TAB_ACTIVE);

	private MaterialTabBar parent;

	private boolean initialized = false;

	public MaterialTab() {
		super(HtmlElements.A.createElement(), CssName.MDC_TAB);
		setRole(Role.TAB);
		addClickEvent(getElement());
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			initialized = true;
		}
	}

	@Override
	protected void onAttach() {
		super.onAttach();

		if (getParent() instanceof MaterialTabBar) {
			parent = (MaterialTabBar) getParent();
		} else {
			parent = null;
		}

		setActive(isActive());
	}

	 protected native void addClickEvent(Element element)/*-{
	 	var _this = this;
	 	element.addEventListener('click', function() {
	 		_this.@gwt.material.design.components.client.ui.MaterialTab::setActive(Z)(true);	 	
	 	});	 	
	}-*/;

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
		ariaControlsMixin.setAttribute(target);
	}

	@Override
	public String getTarget() {
		return ariaControlsMixin.getAttribute();
	}

	@Override
	public boolean isActive() {
		return activeMixin.isApplied();
	}
	
	@Override
	public void setActive(boolean active) {
		if(parent != null && parent.isAttached() && active) {
			parent.select(-1);
		}
		activeMixin.setApply(active);
	}

}
