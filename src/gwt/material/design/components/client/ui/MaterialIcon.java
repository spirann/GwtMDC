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

import gwt.material.design.components.client.base.interfaces.HasHref;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.IconMixin;
import gwt.material.design.components.client.constants.BorderRadius;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Icon;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialIcon extends Icon implements HasType<IconType>, HasHref {

	protected final HrefMixin<MaterialIcon> hrefMixin = new HrefMixin<>(this);
	protected final IconMixin<MaterialIcon> iconMixin = new IconMixin<>(this);

	public MaterialIcon() {
		super(CssName.MATERIAL_ICONS);
		setBorderRadius(BorderRadius.CIRCLE);
	}
	
	public MaterialIcon(String... initialClasses) {
		super(initialClasses);
		addStyleName(CssName.MATERIAL_ICONS);
		setBorderRadius(BorderRadius.CIRCLE);
	}
	
	public MaterialIcon(final IconType type, String... initialClasses) {
		this(initialClasses);
		setType(type);
	}
	
	public MaterialIcon(final IconType type) {
		this();
		setType(type);
	}

	@Override
	public void setType(IconType type) {
		iconMixin.setIcon(type);
	}
	
	public void setType(IconType type, boolean animate) {
		iconMixin.setIcon(type, animate);
	}

	@Override
	public IconType getType() {
		return iconMixin.getIcon();
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

}
