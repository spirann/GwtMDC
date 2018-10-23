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

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasHref;
import gwt.material.design.components.client.base.interfaces.HasIcon;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FabType;
import gwt.material.design.components.client.constants.IconType;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialFabProgress extends MaterialCircularProgress implements HasType<FabType>, HasHref, HasIcon{

	protected final MaterialFab fab = new MaterialFab();
	
	protected final TypeMixin<MaterialFabProgress, FabType> typeMixin = new TypeMixin<>(this);
	
	public MaterialFabProgress() {
		addStyleName(CssName.MDC_FAB_PROGRESS);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(fab);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return fab.addClickHandler(handler);
	}
	
	@Override
	public IconType getIcon() {
		return fab.getIcon();
	}

	@Override
	public void setIcon(IconType iconType) {
		fab.setIcon(iconType);
	}

	@Override
	public void setIcon(IconType iconType, boolean animate) {
		fab.setIcon(iconType, animate);
	}

	@Override
	public void setIconColor(Color color) {
		fab.setIconColor(color);
	}

	@Override
	public void setHref(String href) {
		fab.setHref(href);
	}

	@Override
	public String getHref() {
		return fab.getHref();
	}

	@Override
	public void setTarget(String target) {
		fab.setTarget(target);
	}

	@Override
	public String getTarget() {
		return fab.getTarget();
	}

	@Override
	public void setType(FabType type) {
		typeMixin.setType(type);
		switch (type) {
		case FIXED:
			fab.setType(FabType.DEFAULT);
			break;
		case MINI_FIXED:
			fab.setType(FabType.MINI);
		default:
			fab.setType(type);
			break;
		}
	}

	@Override
	public FabType getType() {
		return typeMixin.getType();
	}
	
	@Override
	public void setBackgroundColor(Color color) {
		fab.setBackgroundColor(color);
	}
	
	@Override
	public void setTextColor(Color color) {
		fab.setColor(color);
	}
}
