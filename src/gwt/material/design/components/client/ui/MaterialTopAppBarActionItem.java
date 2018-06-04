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
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.ui.html.Anchor;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTopAppBarActionItem extends Anchor implements HasType<IconType>, HasHref {

	protected final AttributeMixin<MaterialTopAppBarActionItem> hrefMixin = new AttributeMixin<>(this, "href");
	protected final AttributeMixin<MaterialTopAppBarActionItem> targetMixin = new AttributeMixin<>(this, "target");
	protected final IconMixin<MaterialTopAppBarActionItem> iconMixin = new IconMixin<>(this);

	public MaterialTopAppBarActionItem() {
		super(CssName.MATERIAL_ICONS, CssName.MDC_TOP_APP_BAR__ACTION_ITEM);
	}

	public MaterialTopAppBarActionItem(final IconType type) {
		this();
		setType(type);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (getRipple() == null) {
			setRipple(Ripple.SECONDARY);
		}
	}

	@Override
	public void setType(IconType type) {
		iconMixin.setIcon(type);
	}

	@Override
	public IconType getType() {
		return iconMixin.getIcon();
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
