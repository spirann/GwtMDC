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
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.form.MaterialSelectedField;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTab extends MaterialSelectedField implements HasText, HasIcon {

	protected Div content = new Div(CssName.MDC_TAB__CONTENT);
	protected Span label = new Span(CssName.MDC_TAB__TEXT_LABEL);
	protected MaterialIcon icon = new MaterialIcon(CssName.MDC_TAB__ICON);
	protected MaterialTabIndicator indicator = new MaterialTabIndicator();
	protected Span ripple = new Span(CssName.MDC_TAB__RIPPLE);

	protected final ApplyStyleMixin<MaterialTab> stackedMixin = new ApplyStyleMixin<>(this, CssName.MDC_TAB__STACKED);

	public MaterialTab() {
		super(HtmlElements.BUTTON.createElement(), CssName.MDC_TAB);
		super.initializeSelectedMixin(CssName.MDC_TAB__ACTIVE);
		setRole(Role.TAB);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.tab.MDCTab(element);
	}-*/;

	@Override
	protected void onInitialize() {

		fireChangeOnClick = true;
		
		content.add(icon);
		content.add(label);

		add(content);
		add(indicator);
		add(ripple);

		
		addKeyUpHandler(event -> setSelected(true));

		super.onInitialize();

	}

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public void setText(String text) {
		label.setText(text);
	}

	@Override
	public void setSelected(boolean selected, boolean fireEvents) {
		super.setSelected(selected, fireEvents);
		indicator.setSelected(selected, false);
	}

	@Override
	public void setColor(Color color) {
		setTextColor(color);
	}
	
	@Override
	public void setTextColor(Color color) {
		setStyleProperty(CssMixin.MDC_TAB__COLOR, color.getCssName());
	}

	public void setSelectedColor(Color color) {
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
	
	@Override
	public void setIconColor(Color color) {
		icon.setColor(color);
	}
	
	public void setStacked(final boolean stacked) {
		stackedMixin.setApply(stacked);
	}
	
	public boolean isStacked() {
		return stackedMixin.isApplied();
	}
}
