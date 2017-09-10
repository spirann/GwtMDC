package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Icon;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialTab extends MaterialWidget implements HasHref, HasText, HasIcon {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialTab::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialTab::jsElement = new $wnd.mdc.tabs.MDCTabFoundation(element);
	}-*/;
	
	protected Icon icon = new Icon(CssName.MATERIAL_ICONS, CssName.MDC_TAB_ICON);
	protected Span label = new Span(CssName.MDC_TAB_ICON_TEXT);

	protected final TextMixin<Span> textMixin = new TextMixin<>(label);
	protected final IconMixin<MaterialWidget> iconMixin = new IconMixin<>(icon);
	protected final HrefMixin<MaterialTab> hrefMixin = new HrefMixin<>(this);
	protected final AttributeMixin<MaterialTab> ariaControlsMixin = new AttributeMixin<>(this, "aria-controls");

	protected final ApplyStyleMixin<MaterialTab> activeMixin = new ApplyStyleMixin<>(this, CssName.MDC_TAB_ACTIVE);
	
	private boolean initialized = false;

	private HandlerRegistration handler;

	public MaterialTab() {
		super(HtmlElements.A.createElement(), CssName.MDC_TAB);
		setRole(Role.TAB);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			if (getIcon() != null) {
				add(icon);
			}

			add(label);

			jsInit();
			
			updateTarget();			

			initialized = true;
		}
	}

	protected void updateTarget() {

		if (getTarget() != null && !getTarget().isEmpty()) {

			final Element target = DOM.getElementById(getTarget());
			if (target == null) {
				
				final String style;
				if (isActive()) {
					style = "#" + getTarget() + "{ display: block; }";
				} else {
					style = "#" + getTarget() + "{ display: none; }";
				}

				StyleInjector.injectStylesheetAtEnd(style);

			} else {
				if (isActive()) {
					target.getStyle().setDisplay(Style.Display.BLOCK);
				} else {
					target.getStyle().setDisplay(Style.Display.NONE);
				}
			}
		}
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return super.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Timer timer = new Timer() {

					@Override
					public void run() {
						handler.onClick(event);
					}
				};
				timer.schedule(50);
			}
		});
	}

	@Override
	public void setActive(boolean active) {
		setActive(active, true);
	}

	protected void setActive(boolean active, boolean notifyParent) {
		super.setActive(active);
		activeMixin.setApply(active);
		
		updateTarget();

		if (notifyParent && isAttached()) {

			final Widget widget = getParent();

			if (widget instanceof MaterialTabBar) {
				final MaterialTabBar materialTabBar = (MaterialTabBar) widget;
				materialTabBar.setActiveTabIndex(materialTabBar.getWidgetIndex(this));
			}
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
		hrefMixin.setHref(href);
	}

	@Override
	public String getHref() {
		return hrefMixin.getHref();
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
	public IconType getIcon() {
		return iconMixin.getIcon();
	}

	@Override
	public void setIcon(IconType iconType) {

		if (iconType != null && isAttached() && !icon.isAttached()) {
			insert(icon, 0);
		}

		iconMixin.setIcon(iconType);
	}

}
