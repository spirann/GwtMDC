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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasActive;
import gwt.material.design.components.client.base.HasActiveHandlers;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.ActiveMixin;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.events.ActiveEvent;
import gwt.material.design.components.client.events.ActiveEvent.ActiveHandler;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Span;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTab extends Button implements HasText, HasIcon, HasActive, HasActiveHandlers<Boolean> {

	protected Div content = new Div(CssName.MDC_TAB__CONTENT);
	protected Span label = new Span(CssName.MDC_TAB__TEXT_LABEL);
	protected MaterialIcon icon = new MaterialIcon(CssName.MDC_TAB__ICON);
	protected MaterialTabIndicator indicator = new MaterialTabIndicator();
	protected Span ripple = new Span(CssName.MDC_TAB__RIPPLE);

	protected ActiveMixin<MaterialTab> activeMixin = new ActiveMixin<>(this, CssName.MDC_TAB__ACTIVE);
	protected final ApplyStyleMixin<MaterialTab> stackedMixin = new ApplyStyleMixin<>(this, CssName.MDC_TAB__STACKED);

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

		content.add(icon);
		content.add(label);

		add(content);
		add(indicator);
		add(ripple);

		addClickHandler(event -> setActive(true));
		addKeyUpHandler(event -> setActive(true));

		super.onInitialize();

		setActive(isActive());
	}

	protected void fireActiveEvent() {
		ActiveEvent.fire(this, isActive());
	}

	@Override
	public HandlerRegistration addActiveHandler(ActiveHandler<Boolean> handler) {
		return addHandler(handler, ActiveEvent.getType());
	}

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

	protected native void setNativeActive(boolean active)/*-{
		var tab = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		tab.active = active;
	}-*/;

	protected native boolean isNativeActive()/*-{
		var tab = this.@gwt.material.design.components.client.base.MaterialWidget::jsElement;
		return tab.active;
	}-*/;

	protected void active() {
		final Widget parent = getParent();
		if (parent != null && parent instanceof MaterialWidget) {
			final MaterialWidget materialWidget = (MaterialWidget) parent;
			TimerHelper.schedule(1, () -> {
				materialWidget.getChildrenList().stream().filter(child -> child instanceof MaterialTab && child != this)
						.forEach(child -> ((MaterialTab) child).setActive(false));
				active(true);
			});
		}
	}

	protected void active(boolean active) {
		setNativeActive(active);
		activeMixin.setActive(active);
		indicator.setActive(active);
		fireActiveEvent();
	}

	@Override
	public void setActive(boolean active) {

		if (active == activeMixin.isActive()) {
			return;
		}

		super.setActive(active);

		if (active) {
			active();
		} else {
			active(active);
		}
	}

	@Override
	public void setColor(Color color) {
		setTextColor(color);
	}
	
	@Override
	public void setTextColor(Color color) {
		setStyleProperty(CssMixin.MDC_TAB__COLOR, color.getCssName());
	}

	public void setActiveColor(Color color) {
		setStyleProperty(CssMixin.MDC_TAB__ACTIVED_COLOR, color.getCssName());
	}

	@Override
	public IconType getIcon() {
		return icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		icon.setType(iconType);
	}
	
	public void setStacked(final boolean stacked) {
		stackedMixin.setApply(stacked);
	}
	
	public boolean isStacked() {
		return stackedMixin.isApplied();
	}
}
