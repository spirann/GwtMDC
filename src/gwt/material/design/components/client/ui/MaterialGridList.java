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

import gwt.material.design.components.client.base.HasAspectRatio;
import gwt.material.design.components.client.base.HasCaptionContent;
import gwt.material.design.components.client.base.mixin.AspectRatioMixin;
import gwt.material.design.components.client.base.mixin.CaptionSettingMixin;
import gwt.material.design.components.client.constants.AspectRatio;
import gwt.material.design.components.client.constants.CaptionIconPosition;
import gwt.material.design.components.client.constants.CaptionPosition;
import gwt.material.design.components.client.constants.CaptionType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialGridList extends Div implements HasCaptionContent, HasAspectRatio {	
	
	protected final CaptionSettingMixin<MaterialGridList> captionSettingMixin = new CaptionSettingMixin<MaterialGridList>(this);
	protected final AspectRatioMixin<MaterialGridList> aspectRatioMixin = new AspectRatioMixin<MaterialGridList>(this, CssName.MDC_GRID_LIST_TILE_ASPECT);
	
	public MaterialGridList(){
		super(CssName.MDC_GRID_LIST);
	}
	
	@Override
	public void setCaptionType(CaptionType type) {
		captionSettingMixin.setCaptionType(type);
	}

	@Override
	public CaptionType getCaptionType() {
		return captionSettingMixin.getCaptionType();
	}

	@Override
	public void setCaptionPosition(CaptionPosition position) {
		captionSettingMixin.setCaptionPosition(position);
	}

	@Override
	public CaptionPosition getCaptionPosition() {
		return captionSettingMixin.getCaptionPosition();
	}

	@Override
	public void setCaptionIconPosition(CaptionIconPosition position) {
		captionSettingMixin.setCaptionIconPosition(position);
	}

	@Override
	public CaptionIconPosition getCaptionIconPosition() {
		return captionSettingMixin.getCaptionIconPosition();
	}

	@Override
	public void setAspectRatio(AspectRatio aspectRatio) {
		aspectRatioMixin.setAspectRatio(aspectRatio);
	}

	@Override
	public AspectRatio getAspectRatio() {
		return aspectRatioMixin.getAspectRatio();
	}

}
