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
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasAspectRatio;
import gwt.material.design.components.client.base.interfaces.HasImage;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.base.mixin.AspectRatioMixin;
import gwt.material.design.components.client.base.mixin.ImageMixin;
import gwt.material.design.components.client.constants.AspectRatio;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCard extends Div implements HasImage, HasAspectRatio {

	private CardMedia media = new CardMedia();

	private Div header = new Div(CssName.MDC_CARD__CONTENT);
	private Div content = new Div(CssName.MDC_CARD__CONTENT);

	private Div actions = new Div(CssName.MDC_CARD__ACTIONS);
	private Div buttons = new Div(CssName.MDC_CARD__ACTIONS_BUTTONS);
	private Div icons = new Div(CssName.MDC_CARD__ACTIONS_ICONS);

	protected final ToggleStyleMixin<MaterialCard> outlineMixin = new ToggleStyleMixin<>(this, CssName.MDC_CARD__OUTLINE);
	
	public MaterialCard() {
		super(CssName.MDC_CARD);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(header);

		add(media);

		add(content);

		actions.add(buttons);
		actions.add(icons);
		
		if (buttons.getWidgetCount() > 0 || icons.getWidgetCount() > 0) {
			add(actions);
		}
	}

	@UiChild(limit = 1, tagname = "header")
	public void addHeader(final Widget chield) {
		header.add(chield);
	}

	@UiChild(limit = 1, tagname = "media")
	public void addMedia(final Widget chield) {
		media.add(chield);
	}

	@UiChild(limit = 1, tagname = "content")
	public void addContent(final Widget chield) {
		content.add(chield);
	}

	@UiChild(tagname = "button")
	public void addButtons(final Widget chield) {
		chield.addStyleName(CssName.MDC_CARD__ACTION);
		chield.addStyleName(CssName.MDC_CARD__ACTION_BUTTON);
		buttons.add(chield);
		add(actions);
	}

	@UiChild(tagname = "icon")
	public void addIcons(final Widget chield) {
		chield.addStyleName(CssName.MDC_CARD__ACTION);
		chield.addStyleName(CssName.MDC_CARD__ACTION_ICON);
		icons.add(chield);
		add(actions);
	}

	@Override
	public void setUrl(String url) {
		media.setUrl(url);
	}

	@Override
	public String getUrl() {
		return media.getUrl();
	}

	@Override
	public void setResource(ImageResource resource) {
		media.setResource(resource);
	}

	@Override
	public ImageResource getResource() {
		return media.getResource();
	}

	@Override
	public void setAspectRatio(AspectRatio aspectRatio) {
		media.setAspectRatio(aspectRatio);
	}

	@Override
	public AspectRatio getAspectRatio() {
		return media.getAspectRatio();
	}

	public void setOutline(final boolean outline) {
		outlineMixin.toggle(outline);
	}
	
	public boolean isOutline() {
		return outlineMixin.isApplied(); 
	}
	
	protected class CardMedia extends Div implements HasImage, HasAspectRatio {

		private Div mediaContent = new Div(CssName.MDC_CARD__MEDIA_CONTENT);

		protected final ImageMixin<CardMedia> imageMixin = new ImageMixin<CardMedia>(CardMedia.this) {
			@Override
			public void setUrl(String url) {
				super.setUrl(url);
				CardMedia.this.getElement().removeAttribute("src");
				CardMedia.this.getElement().getStyle().setProperty("backgroundImage", "url(" + url + ")");
			}
		};

		protected final AspectRatioMixin<CardMedia> aspectRatioMixin = new AspectRatioMixin<CardMedia>(this);

		protected CardMedia() {
			super(CssName.MDC_CARD__MEDIA);
		}

		@Override
		protected void onLoad() {
			super.onLoad();
			add(mediaContent);
		}

		@Override
		protected void add(Widget child, Element container) {
			mediaContent.add(child);
		}

		@Override
		public void insert(Widget child, int beforeIndex) {
			mediaContent.insert(child, beforeIndex);
		}

		@Override
		public void setUrl(String url) {
			imageMixin.setUrl(url);
		}

		@Override
		public String getUrl() {
			return imageMixin.getUrl();
		}

		@Override
		public void setResource(ImageResource resource) {
			imageMixin.setResource(resource);
		}

		@Override
		public ImageResource getResource() {
			return imageMixin.getResource();
		}

		@Override
		public void setAspectRatio(AspectRatio aspectRatio) {
			aspectRatioMixin.setAspectRatio(aspectRatio);
		}

		@Override
		public AspectRatio getAspectRatio() {
			return aspectRatioMixin.getAspectRatio();
		}

	}
}
