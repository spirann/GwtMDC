package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialTab extends MaterialWidget implements HasHref, HasText, HasIcon {

	protected JavaScriptObject javaScriptComponent;

	protected static native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.tabs.MDCTabFoundation(element);
	}-*/;
	
	private MaterialWidget icon = new MaterialWidget(HtmlElements.I.createElement(), CssName.MATERIAL_ICONS,
			CssName.MDC_TAB_ICON);
	
	protected Span label = new Span(CssName.MDC_TAB_ICON_TEXT);

	protected final TextMixin<Span> textMixin = new TextMixin<>(label);
	protected final IconMixin<MaterialWidget> iconMixin = new IconMixin<>(icon);
	protected final AttributeMixin<MaterialTab> hrefMixin = new AttributeMixin<>(this, "href");
	protected final AttributeMixin<MaterialTab> ariaControlsMixin = new AttributeMixin<>(this, "aria-controls");

	private MaterialTabBar parent;

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
			
			javaScriptComponent = jsInit(getElement());
			
			initialized = true;
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
		
		if(notifyParent && isAttached()) {
			
			final Widget widget = getParent();
			
			if(widget instanceof MaterialTabBar) {
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
