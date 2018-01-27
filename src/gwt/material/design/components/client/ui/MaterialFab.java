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

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.AutoInitData;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FabType;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialFab extends Button implements HasType<FabType>, HasHref, HasIcon {

	protected Span icon = new Span(CssName.MDC_FAB_ICON);

	protected final HrefMixin<MaterialFab> hrefMixin = new HrefMixin<>(this);
	protected final TypeMixin<MaterialFab, FabType> typeMixin = new TypeMixin<>(this);
	protected final IconMixin<Span> iconMixin = new IconMixin<>(icon);

	public MaterialFab() {
		super(CssName.MDC_FAB, CssName.MATERIAL_ICONS);
		setAutoInitData(AutoInitData.MDC_RIPPLE);
		setRipple(Ripple.DEFAULT);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(icon);
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
}
