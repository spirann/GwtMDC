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

import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.BannerType;
import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialBanner extends Div implements HasText, HasType<BannerType> {

	protected final Div content = new Div(CssName.MDC_BANNER__CONTENT);
	protected final Div avatar = new Div(CssName.MDC_BANNER__AVATAR);
	protected final Label label = new Label(CssName.MDC_BANNER__LABEL);	
	protected final Div actions = new Div(CssName.MDC_BANNER__ACTIONS);
	protected final MaterialListDivider border = new MaterialListDivider();
	
	protected final TypeMixin<MaterialBanner, BannerType> typeMixin = new TypeMixin<>(this);
	
	public MaterialBanner() {
		super(CssName.MDC_BANNER);
	}

	@Override
	protected void onInitialize() {
		
		content.add(avatar);
		content.add(label);

		final MaterialButton button = new MaterialButton();
		button.setText("chupa");
		button.setType(ButtonType.OUTLINE);
		
		actions.add(button);

		add(new MaterialListDivider());
		add(actions);
		add(content);
		add(new MaterialListDivider());
		
		setType(BannerType.VERTICAL);
		setAvatar(IconType.WIFI);
		
		super.onInitialize();
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
		avatar.clear();
		avatar.add(new MaterialImage(url));
	}
	
}
