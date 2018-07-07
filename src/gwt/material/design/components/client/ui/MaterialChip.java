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

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasImage;
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
public class MaterialChip extends Div implements HasText, HasIcon, HasImage {

	protected MaterialImage imageLeading = new MaterialImage();
	protected MaterialIcon iconLeading = new MaterialIcon();
	
	protected MaterialIcon iconTrailing = new MaterialIcon();
	protected Div text = new Div(CssName.MDC_CHIP__TEXT);
	protected Div checkmark = new Div(CssName.MDC_CHIP__ICON__CHECKMARK);
	protected MaterialSvg checkmarkSvg = new MaterialSvg();

	protected final TextMixin<Div> textMixin = new TextMixin<>(text);

	protected boolean closeable = false;

	public MaterialChip() {
		super(CssName.MDC_CHIP);
	}

	@Override
	protected void onInitialize() {

		ripleMixin.initialize();
		
		checkmarkSvg.addStyleName(CssName.MDC_CHIP__ICON__CHECKMARK__SVG);
		checkmarkSvg.setResource(MaterialResources.INSTANCE.mdcChipCheckmark());

		checkmark.add(checkmarkSvg);

		imageLeading.addStyleName(CssName.MDC_CHIP__ICON);
		imageLeading.addStyleName(CssName.MDC_CHIP__ICON__LEADING);
				
		iconLeading.addStyleName(CssName.MDC_CHIP__ICON);
		iconLeading.addStyleName(CssName.MDC_CHIP__ICON__LEADING);

		iconTrailing.setType(IconType.CANCEL);
		iconTrailing.addStyleName(CssName.MDC_CHIP__ICON);
		iconTrailing.addStyleName(CssName.MDC_CHIP__ICON__TRAILING);
		
		if (iconLeading.getType() != null) {
			add(iconLeading);
		}
		
		if(imageLeading.getUrl() != null && !imageLeading.getUrl().isEmpty()) {
			add(imageLeading);
		}

		add(checkmark);
		add(text);

		if (closeable) {
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

	public void setCloseable(final boolean closeable) {

		this.closeable = closeable;

		if (initialized) {
			if (closeable) {
				insert(iconTrailing, getWidgetCount());
			} else if (iconTrailing.getParent() != null) {
				iconTrailing.removeFromParent();
			}
		}

	}
	
	public boolean isCloseable() {
		return closeable;
	}

	@Override
	public IconType getIcon() {
		return iconLeading.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		iconLeading.setType(iconType);

		if (iconType == null && iconLeading.getParent() != null) {
			iconLeading.removeFromParent();
		}

		if (iconType != null && initialized) {
			insert(iconLeading, 0);
		}
	}

	@Override
	public void setUrl(String url) {
		imageLeading.setUrl(url);
		
		if (url == null && imageLeading.getParent() != null) {
			imageLeading.removeFromParent();
		}

		if (url != null && initialized) {
			insert(imageLeading, iconLeading.getParent() == null ? 0 : 1);
		}
	}

	@Override
	public String getUrl() {
		return imageLeading.getUrl();
	}

	@Override
	public void setResource(ImageResource resource) {
		
		imageLeading.setResource(resource);
		
		if (resource == null && imageLeading.getParent() != null) {
			imageLeading.removeFromParent();
		}

		if (resource != null && initialized) {
			insert(imageLeading, iconLeading.getParent() == null ? 0 : 1);
		}
	}

	@Override
	public ImageResource getResource() {
		return imageLeading.getResource();
	}

}
