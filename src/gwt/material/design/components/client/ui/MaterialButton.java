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

import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Button;
import gwt.material.design.components.client.ui.html.Span;
import gwt.material.design.components.client.utils.JsUtils;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialButton extends Button implements HasType<ButtonType>, HasHref, HasText, HasIcon, HasDense {

	protected final Span label = new Span();
	protected final TextMixin<Span> textMixin = new TextMixin<>(label);
	protected final HrefMixin<MaterialButton> hrefMixin = new HrefMixin<>(this);
	protected final TypeMixin<MaterialButton, ButtonType> typeMixin = new TypeMixin<>(this);
	protected final ApplyStyleMixin<MaterialButton> denseMixin = new ApplyStyleMixin<>(this, CssName.MDC_BUTTON__DENSE);

	protected MaterialIcon icon;
	
	public MaterialButton() {
		super(CssName.MDC_BUTTON);
	}
	
	@Override
	protected void onInitialize() {
		
		ripleMixin.initialize();
		
		add(label);				
		addClickHandler(event -> JsUtils.clearFocus());
		
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

	@Override
	public void setType(ButtonType type) {
		typeMixin.setType(type);
	}

	@Override
	public ButtonType getType() {
		return typeMixin.getType();
	}

	@Override
	public IconType getIcon() {
		return icon == null ? null : icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {	
		
		if(iconType == null) {
			if(icon != null) {
				icon.removeFromParent();	
			}
			icon = null;
			return;
		}
		
		if(icon == null) {
			icon = new MaterialIcon(CssName.MDC_BUTTON__ICON);
			insert(icon, 0);
		}
		
		icon.setType(iconType);
	}

	@Override
	public void setDense(boolean dense) {
		denseMixin.setApply(dense);
	}

	@Override
	public boolean isDense() {
		return denseMixin.isApplied();
	}

}
