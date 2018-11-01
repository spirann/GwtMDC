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

import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.interfaces.HasHref;
import gwt.material.design.components.client.base.interfaces.HasIcon;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FabType;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialFab extends Button implements HasType<FabType>, HasHref, HasText, HasIcon {

	protected final Span icon = new Span(CssName.MDC_FAB__ICON, CssName.MATERIAL_ICONS);
	protected final Span label = new Span(CssName.MDC_FAB__LABEL);

	protected final TextMixin<Span> textMixin = new TextMixin<>(label);
	protected final HrefMixin<MaterialFab> hrefMixin = new HrefMixin<>(this);
	protected final TypeMixin<MaterialFab, FabType> typeMixin = new TypeMixin<>(this);
	protected final IconMixin<Span> iconMixin = new IconMixin<>(icon);
	protected final ToggleStyleMixin<MaterialFab> extendedMixin = new ToggleStyleMixin<>(this, CssName.MDC_FAB__EXTENDED);
	protected final ToggleStyleMixin<MaterialFab> exitedMixin = new ToggleStyleMixin<>(this, CssName.MDC_FAB__EXITED);

	public MaterialFab() {
		super(CssName.MDC_FAB);
		setRole(Role.BUTTON);
	}

	@Override
	protected void onInitialize() {
		ripleMixin.initialize();
		add(icon);
		add(label);
		addClickHandler(event -> getElement().blur());
		super.onInitialize();
	}

	@Override
	public String getText() {
		return textMixin.getText();
	}

	@Override
	public void setText(String text) {
		textMixin.setText(text);
		extendedMixin.toggle(text != null && !text.trim().isEmpty());
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
	
	@Override
	public void setIcon(IconType iconType, boolean animate) {
		iconMixin.setIcon(iconType, animate);
	}
	
	@Override
	public void setIconColor(Color color) {
		iconMixin.setIconColor(color);
	}

	public void setExited(final boolean isExited) {
		exitedMixin.toggle(isExited);
	}

	public boolean isExited() {
		return exitedMixin.isApplied();
	}

	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_FAB__FILL_COLOR, color.getCssName());
	}

	@Override
	public void setTextColor(Color color) {
		setCssProperty(CssMixin.MDC_FAB__INK_COLOR, color.getCssName());
	}
	
	@Override
	public void setColor(Color color) {
		setCssProperty(CssMixin.MDC_FAB__INK_COLOR, color.getCssName());
		iconMixin.setIconColor(color);
	}

}
