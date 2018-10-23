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
package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.interfaces.HasIcon;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.utils.helper.StyleHelper;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class IconMixin<T extends UIObject> extends AbstractMixin<T> implements HasIcon {

	private IconType iconType;

	public IconMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public IconType getIcon() {
		return iconType;
	}

	@Override
	public void setIcon(IconType iconType) {
		this.iconType = iconType;
		if (iconType == null)
			uiObject.getElement().setInnerHTML("");
		else
			uiObject.getElement().setInnerHTML(iconType.getCssName());
	}

	@Override
	public void setIcon(IconType iconType, boolean animate) {
		if (animate && getIcon() != null && uiObject.getElement().hasParentElement()) {
			uiObject.addStyleName(CssName.MATERIAL_ICONS__CHANGE_ANIMATION);

			final String animationDuration = StyleHelper.getComputedProperty(uiObject, "animation-duration")
					.replaceAll("[a-zA-Z]", "");

			final int duration = (int) (Double.valueOf(animationDuration) * 1000);
			final int halfDuration = duration / 2;

			TimerHelper.schedule(halfDuration, () -> {
				setIcon(iconType);
				TimerHelper.schedule(halfDuration,
						() -> uiObject.removeStyleName(CssName.MATERIAL_ICONS__CHANGE_ANIMATION));
			});
		} else
			setIcon(iconType);
	}

	@Override
	public void setIconColor(Color color) {
		if (uiObject instanceof MaterialWidget)
			((MaterialWidget) uiObject).setColor(color);
		else
			StyleHelper.setStyleProperty(uiObject, "color", color.getCssName());
	}

}
