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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.interfaces.HasHref;
import gwt.material.design.components.client.base.interfaces.HasIcon;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialSelectedField;
import gwt.material.design.components.client.constants.BorderRadius;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Label;
import gwt.material.design.components.client.ui.html.Span;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialListItem extends MaterialSelectedField implements HasHref, HasText, HasIcon {

	private final MaterialIcon icon = new MaterialIcon(CssName.MDC_LIST_ITEM__GRAPHIC);
	private final MaterialImage avatar = new MaterialImage();
	private final Span textContent = new Span(CssName.MDC_LIST_ITEM__TEXT);
	private final Label primaryText = new Label(CssName.MDC_LIST_ITEM__TEXT_PRIMARY_TEXT);
	private final Label secondaryText = new Label(CssName.MDC_LIST_ITEM__TEXT_SECONDARY_TEXT);

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	private final HrefMixin<MaterialListItem> hrefMixin = new HrefMixin<>(this);
	private final AttributeMixin<MaterialIcon, Boolean> ariaHiddenMixin = new AttributeMixin<>(icon,
			CssAttribute.ARIA_HIDDEN, true, FromString.TO_BOOLEAN);

	private boolean initialized = false;

	public MaterialListItem() {
		super(HtmlElements.LI.createElement(), CssName.MDC_LIST_ITEM);
		super.initializeSelectedMixin(CssName.MDC_LIST_ITEM__SELECTED);
	}

	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {

		ripleMixin.initialize();

		avatar.addStyleName(CssName.MDC_LIST_ITEM__GRAPHIC);
		avatar.setBorderRadius(BorderRadius.CIRCLE);

		if (avatar.getUrl() != null && !avatar.getUrl().isEmpty())
			insert(avatar, 0);

		icon.setBorderRadius(BorderRadius.CIRCLE);

		if (icon.getType() != null)
			insert(icon, 0);

		textContent.add(primaryText);
		textContent.add(secondaryText);

		add(textContent);

		super.onInitialize();
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
		if (prevent)
			prevent(widget.getElement());

		if (widget instanceof MaterialCheckbox) {
			final MaterialCheckbox checkbox = (MaterialCheckbox) widget;
			checkbox.addAttachHandler(event -> {
				if (event.isAttached())
					checkbox.setSelected(isSelected(), false);
			});
			checkbox.addSelectionHandler(event -> setSelected(event.getValue(), true));
		}

		if (widget instanceof MaterialRadioButton) {
			final MaterialRadioButton radioButton = (MaterialRadioButton) widget;
			radioButton.addAttachHandler(event -> {
				if (event.isAttached())
					radioButton.setSelected(isSelected(), false);
			});
			radioButton.addSelectionHandler(event -> setSelected(event.getValue(), true));
		}
	}

	@UiChild(tagname = "end")
	public void addEndDetail(final Widget widget, final boolean prevent) {
		insertDetail(widget, CssName.MDC_LIST_ITEM__META, Appender.END);
		if (prevent) {
			StyleHelper.setAttribute(widget, CssAttribute.PREVENT, "true");
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
		setIcon(iconType, false);
	}

	@Override
	public void setIcon(IconType iconType, boolean animate) {
		icon.setType(iconType, animate);
		if (icon.getType() != null && isAttached() && icon.getParent() == null)
			insert(icon, 0);
		else if (icon.getType() == null && icon.getParent() != null)
			icon.removeFromParent();
	}

	public void setAvatar(final ImageResource resource) {
		setAvatarUrl(resource == null ? null : resource.getSafeUri().asString());
	}

	public void setAvatarUrl(final String url) {
		avatar.setUrl(url);
		if (avatar.getUrl() != null && !avatar.getUrl().isEmpty() && isAttached() && avatar.getParent() == null)
			insert(avatar, getWidgetIndex(primaryText));
		else if ((avatar.getUrl() == null || avatar.getUrl().isEmpty()) && avatar.getParent() != null)
			avatar.removeFromParent();
	}

	@Override
	public void setTextColor(Color color) {
		setCssProperty(CssMixin.MDC_LIST_ITEM__PRIMARY_TEXT_COLOR, color.getCssName());
	}

	public void setSecondaryTextColor(Color color) {
		setCssProperty(CssMixin.MDC_LIST_ITEM__SECONDARY_TEXT_COLOR, color.getCssName());
	}

	public void setGraphicColor(Color color) {
		setCssProperty(CssMixin.MDC_LIST_ITEM__GRAPHIC_COLOR, color.getCssName());
	}

	public void setMetaColor(Color color) {
		setCssProperty(CssMixin.MDC_LIST_ITEM__META_COLOR, color.getCssName());
	}

	public void setSelectedTextColor(Color color) {
		setCssProperty(CssMixin.MDC_LIST_ITEM__SELECTED_PRIMARY_TEXT_COLOR, color.getCssName());
	}

	public void setSelectedGraphicColor(Color color) {
		setCssProperty(CssMixin.MDC_LIST_ITEM__SELECTED_GRAPHIC_COLOR, color.getCssName());
	}

	public void setSelectedMetaColor(Color color) {
		setCssProperty(CssMixin.MDC_LIST_ITEM__SELECTED_META_COLOR, color.getCssName());
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

}
