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
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
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
public class MaterialBanner extends Div implements HasText, HasType<BannerType>, HasOpen {

	protected final Div content = new Div(CssName.MDC_BANNER__CONTENT);
	protected final Div avatar = new Div(CssName.MDC_BANNER__AVATAR);
	protected final Label label = new Label(CssName.MDC_BANNER__LABEL, CssName.MDC_TYPOGRAPHY__BODY_2);
	protected final Div actions = new Div(CssName.MDC_BANNER__ACTIONS);
	protected final MaterialDivider border = new MaterialDivider();

	protected final TypeMixin<MaterialBanner, BannerType> typeMixin = new TypeMixin<>(this, BannerType.VERTICAL);
	protected final ApplyStyleMixin<MaterialBanner> openMixin = new ApplyStyleMixin<>(this, CssName.MDC_BANNER__OPENED);
	
	public MaterialBanner() {
		super(CssName.MDC_BANNER);		
	}

	@Override
	protected void onInitialize() {

		content.add(avatar);
		content.add(label);

		add(new MaterialDivider());
		add(actions);
		add(content);
		add(new MaterialDivider());

		super.onInitialize();
	}

	@UiChild(tagname = "action", limit = 2)
	public void addAction(final MaterialButton action) {
		actions.add(action);
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
		setStyleProperty(CssMixin.MDC_BANNER__FILL_COLOR, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_BANNER__INK_COLOR, color.getCssName());
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
		} catch (Throwable ignore) {
			avatar.clear();
			avatar.add(new MaterialImage(url));
		}
	}

	public void setAvatarBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_BANNER__AVATAR_FILL_COLOR, color.getCssName());
	}

	public void setAvatarColor(Color color) {
		setStyleProperty(CssMixin.MDC_BANNER__AVATAR_INK_COLOR, color.getCssName());
	}

	@Override
	public void setOpen(boolean open) {	
		openMixin.setApply(open);
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
