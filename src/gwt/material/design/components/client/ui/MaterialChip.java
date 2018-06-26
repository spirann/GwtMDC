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

import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialChip extends Div implements HasText {

	protected MaterialIcon iconLeading = new MaterialIcon();
	protected MaterialIcon iconTrailing = new MaterialIcon();
	protected Div text = new Div(CssName.MDC_CHIP__TEXT);
	protected Div checkmark = new Div(CssName.MDC_CHIP__ICON__CHECKMARK);
	protected MaterialSvg checkmarkSvg = new MaterialSvg();

	protected final TextMixin<Div> textMixin = new TextMixin<>(text);

	public MaterialChip() {
		super(CssName.MDC_CHIP);
				
	}

	@Override
	protected void onInitialize() {

		checkmarkSvg.addStyleName(CssName.MDC_CHIP__ICON__CHECKMARK__SVG);
		checkmarkSvg.setResource(MaterialResources.INSTANCE.mdcChipCheckmark());

		checkmark.add(checkmarkSvg);

		iconLeading.addStyleName(CssName.MDC_CHIP__ICON);
		iconLeading.addStyleName(CssName.MDC_CHIP__ICON__LEADING);

		iconTrailing.addStyleName(CssName.MDC_CHIP__ICON);
		iconTrailing.addStyleName(CssName.MDC_CHIP__ICON__TRAILING);
		
		if(iconLeading.getType() != null) {
			add(iconLeading);
		}
		
		add(checkmark);
		add(text);
		
		if(iconTrailing.getType() != null) {
			add(iconTrailing);
		}
		
		super.onInitialize();
	}

	@Override
	public String getText() {
		return textMixin.getText();
	}

	@Override
	public void setText(String text) {
		textMixin.setText(text);
	}

	
	public IconType getIconLeading() {
		return iconLeading.getType();
	}

	
	public void setIconLeading(IconType iconType) {
		iconLeading.setType(iconType);
		
		if(iconType == null && iconLeading.getParent() != null) {
			iconLeading.removeFromParent();
		}
		
		if(iconType != null && initialized) {
			insert(iconLeading, 0);
		}
	}

	public IconType getIconTrailing() {
		return iconTrailing.getType();
	}
	
	public void setIconTrailing(IconType iconType) {
		iconTrailing.setType(iconType);
		
		if(iconType == null && iconTrailing.getParent() != null) {
			iconTrailing.removeFromParent();
		}
		
		if(iconType != null && initialized) {
			insert(iconTrailing, getWidgetCount());
		}
	}
	
}
