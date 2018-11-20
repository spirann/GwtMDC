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
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.interfaces.HasToggler;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.TogglerMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.BannerType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialBanner extends Div implements HasText, HasType<BannerType>, HasOpen, HasToggler {

	protected final Div navegation = new Div(CssName.MDC_BANNER_NAVEGATION);
	protected final Div content = new Div(CssName.MDC_BANNER__CONTENT);
	protected final Div avatar = new Div(CssName.MDC_BANNER__AVATAR);
	protected final Label label = new Label(CssName.MDC_BANNER__LABEL, CssName.MDC_TYPOGRAPHY__BODY_2);
	protected final Div actions = new Div(CssName.MDC_BANNER__ACTIONS);
	protected final MaterialDivider border = new MaterialDivider();

	protected final TypeMixin<Div, BannerType> typeMixin = new TypeMixin<>(navegation, BannerType.VERTICAL);
	protected final ToggleStyleMixin<Div> openMixin = new ToggleStyleMixin<>(navegation, CssName.MDC_BANNER__OPENED);
	protected final TogglerMixin<MaterialBanner> togglerMixin = new TogglerMixin<>(this);

	public MaterialBanner() {
		super(CssName.MDC_BANNER);
	}

	@Override
	protected void onInitialize() {
		content.add(avatar);
		content.add(label);

		navegation.add(new MaterialDivider());
		navegation.add(actions);
		navegation.add(content);
		navegation.add(new MaterialDivider());

		super.add(navegation);
		super.onInitialize();
	}

	@UiChild(tagname = "action", limit = 2)
	public void addAction(final MaterialButton action) {
		actions.add(action);
	}

	@Override
	public void setToggler(String togglerId) {
		togglerMixin.setToggler(togglerId);
	}

	@Override
	public String getToggler() {
		return togglerMixin.getToggler();
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
	public void setType(BannerType type) {
		typeMixin.setType(type);
	}

	@Override
	public BannerType getType() {
		return typeMixin.getType();
	}

	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_BANNER__FILL_COLOR, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setCssProperty(CssMixin.MDC_BANNER__INK_COLOR, color.getCssName());
	}

	public void setAvatar(final IconType iconType) {
		final MaterialIcon icon = new MaterialIcon(iconType);
		setAvatar(icon);
	}

	public void setAvatar(final MaterialIcon icon) {
		avatar.clear();
		avatar.add(icon);
	}

	public void setAvatar(final ImageResource resource) {
		avatar.clear();
		avatar.add(new MaterialImage(resource));
	}

	public void setAvatar(final String url) {
		try {
			// It`s in ui:binder
			if (url != null && !url.contains("http") && !url.contains("."))
				setAvatar(IconType.valueOf(url));
			else
				avatar.add(new MaterialImage(url));
		} catch (Throwable ignore) {
			avatar.clear();
			avatar.add(new MaterialImage(url));
		}
	}

	public void setAvatarBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_BANNER__AVATAR_FILL_COLOR, color.getCssName());
	}

	public void setAvatarColor(Color color) {
		setCssProperty(CssMixin.MDC_BANNER__AVATAR_INK_COLOR, color.getCssName());
	}

	@Override
	public void setOpen(boolean open) {
		openMixin.toggle(open);
	}

	@Override
	public boolean isOpen() {
		return openMixin.isApplied();
	}

	@Override
	public void open() {
		setOpen(true);
	}

	@Override
	public void close() {
		setOpen(false);
	}
}
