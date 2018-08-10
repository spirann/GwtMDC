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

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasSelected;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.ui.html.Li;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialListItem extends Li implements HasHref, HasText, HasIcon, HasSelected {

	private MaterialIcon icon = new MaterialIcon();
	private MaterialImage avatar = new MaterialImage();
	private Span textContent = new Span(CssName.MDC_LIST_ITEM__TEXT);
	private Label primaryText = new Label(CssName.MDC_LIST_ITEM__TEXT_PRIMARY_TEXT);
	private Label secondaryText = new Label(CssName.MDC_LIST_ITEM__TEXT_SECONDARY_TEXT);

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////	
	private final HrefMixin<MaterialListItem> hrefMixin = new HrefMixin<>(this);
	private final AttributeMixin<MaterialIcon> ariaHiddenMixin = new AttributeMixin<>(icon, "aria-hidden");
	private final ApplyStyleMixin<MaterialListItem> selectedMixin = new ApplyStyleMixin<>(this, CssName.MDC_LIST_ITEM__SELECTED);

	private boolean initialized = false;

	public MaterialListItem() {
		super(CssName.MDC_LIST_ITEM);
	}
	
	@Override
	protected void onLoad() {

		if (!initialized) {

			ripleMixin.initialize();
			
			ariaHiddenMixin.setAttribute(true);

			avatar.addStyleName(CssName.MDC_LIST_ITEM__GRAPHIC);
			avatar.setCircle(true);

			if (avatar.getUrl() != null && !avatar.getUrl().isEmpty()) {
				insert(avatar, 0);
			}

			icon.addStyleName(CssName.MDC_LIST_ITEM__GRAPHIC);
			icon.setCircle(true);

			if (icon.getType() != null) {
				insert(icon, 0);
			}

			textContent.add(primaryText);
			textContent.add(secondaryText);
			
			add(textContent);
			
			initialized = true;			
		}

		super.onLoad();
	}

	private native void removeDatail(final Element element, final String className)/*-{
		var elements = element.getElementsByClassName(className);
		var i;
		for (i = 0; i < elements.length; i++) {
			var el = elements[i];
			el.parentNode.removeChild(el);
		}
	}-*/;

	private void insertDetail(final Widget widget, final String detailClass, final int detailPos) {
		widget.addStyleName(detailClass);
		insert(widget, detailPos);
	}

	@UiChild(tagname = "start")
	public void addStartDetail(final Widget widget, final boolean prevent) {
		insertDetail(widget, CssName.MDC_LIST_ITEM__GRAPHIC, Appender.START);
		if (prevent) {
			prevent(widget.getElement());
		}
	}

	@UiChild(tagname = "end")
	public void addEndDetail(final Widget widget, final boolean prevent) {
		insertDetail(widget, CssName.MDC_LIST_ITEM__META, Appender.END);
		if (prevent) {
			widget.getElement().setAttribute("prevent", "true");
			prevent(widget.getElement());
		}
	}

	protected native void prevent(final Element element)/*-{

		var event = function(evt) {
			evt.stopPropagation();
			evt.cancelBubble = true;
		};

		element.addEventListener('click', event);
		element.addEventListener('toggle', event);
		element.addEventListener('mousedown', event);
		element.addEventListener('pointerdown', event);

	}-*/;

	@Override
	public String getText() {
		return primaryText.getText();
	}

	@Override
	public void setText(String text) {
		primaryText.setText(text);
	}

	public String getTextSecondary() {
		return secondaryText.getText();
	}

	public void setTextSecondary(String text) {
		secondaryText.setText(text);
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
	public IconType getIcon() {
		return icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		icon.setType(iconType);

		if (icon.getType() != null && isAttached() && icon.getParent() == null) {
			insert(icon, 0);
		} else if (icon.getType() == null && icon.getParent() != null) {
			icon.removeFromParent();
		}
	}

	public void setAvatar(final ImageResource resource) {
		setAvatarUrl(resource == null ? null : resource.getSafeUri().asString());
	}

	public void setAvatarUrl(final String url) {
		avatar.setUrl(url);

		if (avatar.getUrl() != null && !avatar.getUrl().isEmpty() && isAttached() && avatar.getParent() == null) {
			insert(avatar, getWidgetIndex(primaryText));
		} else if ((avatar.getUrl() == null || avatar.getUrl().isEmpty()) && avatar.getParent() != null) {
			avatar.removeFromParent();
		}
	}

	public void setPrimaryTextColor(Color color) {
		setStyleProperty(CssMixin.MDC_LIST_ITEM__PRIMARY_TEXT_COLOR, color.getCssName());
	}
	
	public void setSecondaryTextColor(Color color) {
		setStyleProperty(CssMixin.MDC_LIST_ITEM__SECONDARY_TEXT_COLOR, color.getCssName());
	}
	
	public void setGraphicColor(Color color) {
		setStyleProperty(CssMixin.MDC_LIST_ITEM__GRAPHIC_COLOR, color.getCssName());
	}
	
	public void setMetaColor(Color color) {
		setStyleProperty(CssMixin.MDC_LIST_ITEM__META_COLOR, color.getCssName());
	}
	
	public void setIconColor(Color color) {
		icon.setTextColor(color);
	}

	public void setIconBackgroundColor(Color color) {
		icon.setBackgroundColor(color);
	}

	public void setAvatarBackgroundColor(Color color) {
		avatar.setBackgroundColor(color);
	}

	@Override
	public boolean isSelected() {
		return selectedMixin.isApplied();
	}

	@Override
	public void setSelected(boolean selected) {
		selectedMixin.setApply(selected);
	}
	
}
