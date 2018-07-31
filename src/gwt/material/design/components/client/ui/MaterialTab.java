/*
 * #%L
 * Gwt Material Design Components
 * %%
 * Copyright (C) 2017 - 2017 Gwt Material Design Components
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasSelectionHandlers;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Anchor;
import gwt.material.design.components.client.ui.html.Icon;
import gwt.material.design.components.client.ui.html.Span;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTab extends Anchor implements HasHref, HasText, HasIcon, HasSelectionHandlers<MaterialTab> {

	protected Icon icon = new Icon(CssName.MATERIAL_ICONS, CssName.MDC_TAB__ICON);
	protected Span label = new Span(CssName.MDC_TAB__ICON_TEXT);

	protected final TextMixin<Span> textMixin = new TextMixin<>(label);
	protected final IconMixin<MaterialWidget> iconMixin = new IconMixin<>(icon);
	protected final HrefMixin<MaterialTab> hrefMixin = new HrefMixin<>(this);
	protected final AttributeMixin<MaterialTab> ariaControlsMixin = new AttributeMixin<>(this, "aria-controls");

	protected final ApplyStyleMixin<MaterialTab> activeMixin = new ApplyStyleMixin<>(this, CssName.MDC_TAB__ACTIVE);

	private HandlerRegistration handler;

	public MaterialTab() {
		super(CssName.MDC_TAB);
		setRole(Role.TAB);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.tab.MDCTab(element);
	}-*/;

	@Override
	protected void onInitialize() {

		if (getIcon() != null) {
			add(icon);
		}

		add(label);

		if (!(getParent() instanceof MaterialTabBar)) {
			super.onInitialize();
		}
		
		updateTarget();
		preventDefaultClick(true);
		addNativeSelectEvent(getElement());
	}

	protected native void addNativeSelectEvent(Element element)/*-{

		var _this = this;
		element.addEventListener('MDCTab:selected', function(event) {
			_this.@gwt.material.design.components.client.ui.MaterialTab::fireSelectionEvent()();
		});

	}-*/;

	protected native void preventDefaultClick(final boolean prevent)/*-{

		var tab = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		if (tab) {
			tab.preventDefaultOnClick = prevent;
		}

	}-*/;

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
		return super.addClickHandler(event -> TimerHelper.schedule(50, () -> handler.onClick(event)));
	}
	
	protected void fireSelectionEvent() {
		SelectionEvent.fire(this, this);
	}	
	
	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<MaterialTab> handler) {
		return addHandler(handler, SelectionEvent.getType());
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
