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
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasCaption;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasImage;
import gwt.material.design.components.client.base.HasSupportText;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Li;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialGridListTile extends Li implements HasIcon, HasCaption, HasSupportText, HasImage {

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialGridListTile::getElement()();
		this.@gwt.material.design.components.client.ui.MaterialGridListTile::jsElement = $wnd.mdc.gridList.MDCGridList
				.attachTo(element);
	}-*/;

	protected Div primary = new Div(CssName.MDC_GRID_TILE_PRIMARY, CssName.MDC_TYPOGRAPHY);
	protected MaterialImage image = new MaterialImage();
	protected Span secondary = new Span(CssName.MDC_GRID_TILE_SECONDARY);
	protected MaterialIcon icon = new MaterialIcon();
	protected Span caption = new Span(CssName.MDC_GRID_TILE_TITLE, CssName.MDC_TYPOGRAPHY);
	protected Span supportText = new Span(CssName.MDC_GRID_TILE_SUPPORT_TEXT, CssName.MDC_TYPOGRAPHY);

	public MaterialGridListTile() {
		super(CssName.MDC_GRID_TILE);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		super.add(primary);

		image.addStyleName(CssName.MDC_GRID_TILE_PRIMARY_CONTENT);

		primary.setZIndex(0);
		primary.add(image);

		icon.addStyleName(CssName.MDC_GRID_TILE_ICON);
		icon.setCircle(true);
		icon.setPadding(0);

		secondary.setZIndex(1);
		secondary.add(icon);
		secondary.add(caption);
		secondary.add(supportText);

		jsInit();
	}

	private void checkSecondary() {
		if (secondary.getParent() == null) {
			super.add(secondary);
		}
	}

	@Override
	public void add(Widget child) {
		primary.add(child);
	}

	@Override
	public IconType getIcon() {
		return icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		checkSecondary();
		icon.setType(iconType);
	}

	public void setIconColor(final Color color) {
		checkSecondary();
		icon.setTextColor(color);
	}

	public void setIconBackgroundColor(final Color color) {
		checkSecondary();
		icon.setBackgroundColor(color);
	}

	public HandlerRegistration addIconClickHandler(ClickHandler handler) {
		checkSecondary();
		return icon.addClickHandler(handler);
	}

	@Override
	public String getCaption() {
		return caption.getElement().getInnerText();
	}

	@Override
	public void setCaption(String caption) {
		checkSecondary();
		this.caption.getElement().setInnerText(caption);
	}

	@Override
	public String getSupportText() {
		return supportText.getElement().getInnerText();
	}

	@Override
	public void setSupportText(String supportText) {
		checkSecondary();
		this.supportText.getElement().setInnerText(supportText);
	}

	@Override
	public void setUrl(String url) {
		image.setUrl(url);
	}

	@Override
	public String getUrl() {
		return image.getUrl();
	}

	@Override
	public void setResource(ImageResource resource) {
		image.setResource(resource);
	}

	@Override
	public ImageResource getResource() {
		return image.getResource();
	}

	@Override
	public void setBackground(String background) {
		primary.setBackground(background);
	}

	@Override
	public void setBackgroundColor(Color color) {
		primary.setBackgroundColor(color);
	}
}
