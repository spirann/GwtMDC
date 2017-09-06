package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.constants.TabBarType;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialTabBar extends MaterialWidget implements HasType<TabBarType>, HasChangeHandlers {

	protected JavaScriptObject javaScriptComponent;

	protected static native JavaScriptObject jsInit(final Element element)/*-{
		return $wnd.mdc.tabs.MDCTabBar.attachTo(element);
	}-*/;

	private Span indicator = new Span(CssName.MDC_TAB_BAR_INDICATOR);

	protected final TypeMixin<MaterialTabBar, TabBarType> typeMixin = new TypeMixin<>(this);

	private boolean initialized = false;

	private int activeTabIndex = -1;

	public MaterialTabBar() {
		super(HtmlElements.NAV.createElement(), CssName.MDC_TAB_BAR, CssName.MDC_TAB_BAR_INDICATOR_ACCENT);
		setRole(Role.TAB_BAR);
		setType(TabBarType.ICON_WITH_TEXT);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			super.add(indicator);

			addChangeEvent(getElement());

			javaScriptComponent = jsInit(getElement());
			preventDefaultClick(javaScriptComponent, true);
			loadFirstSelected();
			initialized = true;

		}
	}

	protected native void addChangeEvent(Element element)/*-{

		var _this = this;
		element.addEventListener('MDCTabBar:change',function(tabs) {
			
			var dynamicTabBar = tabs.detail;
			var nthChildIndex = dynamicTabBar.activeTabIndex;
						
			_this.@gwt.material.design.components.client.ui.MaterialTabBar::setActiveTabIndex(I)(nthChildIndex);

		});

	}-*/;

	protected void fireChangeEvent() {
		ChangeEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
	}

	protected void loadFirstSelected() {

		for (int i = 0; i < getWidgetCount(); i++) {

			final Widget child = getWidget(i);

			if (child instanceof MaterialTab) {
				final MaterialTab tab = (MaterialTab) child;
				if (tab.isActive()) {
					setActiveTabIndex(i);
				}
			}

		}
	}

	protected void updateTabs(final int oldIndex, final int newIndex) {

		final Widget oldChild = getWidget(oldIndex);

		if (oldChild instanceof MaterialTab) {
			final MaterialTab tab = (MaterialTab) oldChild;
			tab.setActive(false, false);
		}

		final Widget newChild = getWidget(newIndex);

		if (newChild instanceof MaterialTab) {
			final MaterialTab tab = (MaterialTab) newChild;
			tab.setActive(true, false);
		}
	}

	@Override
	public void add(Widget child) {
		super.add(child);
		super.add(indicator);
	}

	protected native void preventDefaultClick(final JavaScriptObject tabBar, final boolean prevent)/*-{
		tabBar.preventDefaultOnClick = prevent;
	}-*/;

	public int getActiveTabIndex() {
		return activeTabIndex;
	}

	public void setActiveTabIndex(int activeTabIndex) {

		if (activeTabIndex < 0) {
			throw new IllegalArgumentException("Invalid value: " + activeTabIndex);
		}

		final int oldActiveTabIndex = this.activeTabIndex;
		this.activeTabIndex = activeTabIndex;

		if (isAttached() && this.activeTabIndex != oldActiveTabIndex && oldActiveTabIndex != -1) {
			this.activeTabIndex = activeTabIndex;

			updateTabs(oldActiveTabIndex, activeTabIndex);
			setActiveTabIndex(javaScriptComponent, activeTabIndex);
			fireChangeEvent();
		}
	}

	protected native void setActiveTabIndex(final JavaScriptObject tabBar, final int activeTabIndex)/*-{
		tabBar.activeTabIndex = activeTabIndex;
	}-*/;
	
	@Override
	public void setType(TabBarType type) {
		typeMixin.setType(type);
	}

	@Override
	public TabBarType getType() {
		return typeMixin.getType();
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onChange(event);
			}
		}, ChangeEvent.getType());
	}
}
