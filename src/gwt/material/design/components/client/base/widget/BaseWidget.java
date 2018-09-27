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
package gwt.material.design.components.client.base.widget;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.OutlineStyle;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.ComplexPanel;

import gwt.material.design.components.client.base.interfaces.HasFlexbox;
import gwt.material.design.components.client.base.mixin.FlexboxMixin;
import gwt.material.design.components.client.constants.BorderCollapse;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.constants.Flex;
import gwt.material.design.components.client.constants.FlexAlignContent;
import gwt.material.design.components.client.constants.FlexAlignItems;
import gwt.material.design.components.client.constants.FlexAlignSelf;
import gwt.material.design.components.client.constants.FlexDirection;
import gwt.material.design.components.client.constants.FlexJustifyContent;
import gwt.material.design.components.client.constants.FlexWrap;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class BaseWidget extends ComplexPanel implements HasFlexbox {

	protected final FlexboxMixin<BaseWidget> flexboxMixin = new FlexboxMixin<>(this);

	protected void setStyleProperty(final String attribute, final String value) {
		StyleHelper.setStyleProperty(getElement(), attribute, value == null ? "none" : value);
	}

	public void setInnerHTML(final String html) {
		getElement().setInnerHTML(html);
	}

	public void setInnerSafeHTML(final SafeHtml html) {
		getElement().setInnerSafeHtml(html);
	}

	@Override
	public void setWidth(final String width) {
		setStyleProperty("width", width);
	}

	@Override
	public void setHeight(final String height) {
		setStyleProperty("height", height);
	}

	public void setMaxWidth(final String maxWidth) {
		setStyleProperty("max-width", maxWidth);
	}

	public void setMinWidth(final String minWidth) {
		setStyleProperty("min-width", minWidth);
	}

	public void setMaxHeight(final String maxHeight) {
		setStyleProperty("max-height", maxHeight);
	}

	public void setMinHeight(final String minHeight) {
		setStyleProperty("min-height", minHeight);
	}

	public void setLineHeight(final String lineHeight) {
		setStyleProperty("line-height", lineHeight);
	}

	public void setLetterSpacing(final String letterSpacing) {
		setStyleProperty("letter-spacing", letterSpacing);
	}

	public void setFontSize(final String fontSize) {
		setStyleProperty("font-size", fontSize);
	}

	public void setFontFamily(final String fontFamily) {
		setStyleProperty("font-family", fontFamily);
	}

	public void setTextAlign(final TextAlign textAlign) {
		setStyleProperty("text-align", textAlign.getCssName());
	}

	public void setCursor(Cursor cursor) {
		setStyleProperty("cursor", cursor.getCssName());
	}

	public void setPadding(final int padding) {
		setStyleProperty("padding", padding + "px");
	}

	public void setPaddingTop(final int paddingTop) {
		setStyleProperty("padding-top", paddingTop + "px");
	}

	public void setPaddingBottom(final int paddingBottom) {
		setStyleProperty("padding-bottom", paddingBottom + "px");
	}

	public void setPaddingLeft(final int paddingLeft) {
		setStyleProperty("padding-left", paddingLeft + "px");
	}

	public void setPaddingRight(final int paddingRight) {
		setStyleProperty("padding-right", paddingRight + "px");
	}

	public void setMargin(final int margin) {
		setStyleProperty("margin", margin + "px");
	}

	public void setMarginTop(final int marginTop) {
		setStyleProperty("margin-top", marginTop + "px");
	}

	public void setMarginBottom(final int marginBottom) {
		setStyleProperty("margin-bottom", marginBottom + "px");
	}

	public void setMarginLeft(final int marginLeft) {
		setStyleProperty("margin-left", marginLeft + "px");
	}

	public void setMarginRight(final int marginRight) {
		setStyleProperty("margin-right", marginRight + "px");
	}

	public void setBackground(final String background) {
		setStyleProperty("background", background);
	}

	public void setBackgroundImageResource(final ImageResource imageResource) {
		setStyleProperty("background",
				"url('" + imageResource.getSafeUri().asString() + "') no-repeat center center fixed");
		setStyleProperty("-webkit-background-size", "cover");
		setStyleProperty("-moz-background-size", "cover");
		setStyleProperty("-o-background-size", "cover");
		setStyleProperty("background-size", "cover");
	}

	public void setBackgroundImage(final String url) {
		setStyleProperty("background-image", url);
	}

	public void setBackgroundColor(final Color color) {
		setStyleProperty("background-color", color.getCssName());
	}

	public void setColor(final Color color) {
		setStyleProperty("color", color.getCssName());
	}

	public void setTextColor(final Color color) {
		setColor(color);
	}

	public void setOverflow(final Overflow overflow) {
		setStyleProperty("overflow", overflow.getCssName());
	}

	public void setOverflowX(final Overflow overflow) {
		setStyleProperty("overflow-x", overflow.getCssName());
	}

	public void setOverflowY(final Overflow overflow) {
		setStyleProperty("overflow-y", overflow.getCssName());
	}

	public void setTop(final String top) {
		setStyleProperty("top", top);
	}

	public void setBottom(final String bottom) {
		setStyleProperty("bottom", bottom);
	}

	public void setLeft(final String left) {
		setStyleProperty("left", left);
	}

	public void setRight(final String right) {
		setStyleProperty("right", right);
	}

	public void setLayoutPosition(final Style.Position position) {
		setStyleProperty("position", position.getCssName());
	}

	public void setZIndex(final int zIndex) {
		setStyleProperty("z-index", String.valueOf(zIndex));
	}

	public void setOutlineWidth(final int borderWidth) {
		setStyleProperty("outline-width", borderWidth + "px");
	}

	public void setOutilineColor(final Color color) {
		setStyleProperty("outline-color", color.getCssName());
	}

	public void setOutlineStyle(final OutlineStyle outlineStyle) {
		setStyleProperty("outline-style", outlineStyle.getCssName());
	}

	public void setBorderWidth(final int borderWidth) {
		setStyleProperty("border-width", borderWidth + "px");
	}

	public void setBorderColor(final Color color) {
		setStyleProperty("border-color", color.getCssName());
	}

	public void setBorderStyle(final BorderStyle borderStyle) {
		setStyleProperty("border-style", borderStyle.getCssName());
	}

	public void setBorderSpacing(final int borderSpacing) {
		setStyleProperty("border-spacing", borderSpacing + "px");
	}

	public void setBorderCollapse(final BorderCollapse borderCollapse) {
		setStyleProperty("border-collapse", borderCollapse.getCssName());
	}

	public void setBorderRadius(final String borderRadius) {
		setStyleProperty("border-radius", borderRadius);
	}

	public void setOpacity(final double opacity) {
		setStyleProperty("opacity", String.valueOf(opacity));
	}

	public final void setVisibility(Visibility value) {
		setStyleProperty("visibility", value.getCssName());
	}

	/*
	 * *****************************************************************************
	 *
	 * FLEX BOX SETTER'S
	 * 
	 */

	@Override
	public void setGwtDisplay(Style.Display display) {
		flexboxMixin.setGwtDisplay(display);
	}

	@Override
	public void setDisplay(Display display) {
		flexboxMixin.setDisplay(display);
	}

	@Override
	public void setFlexDirection(FlexDirection flexDirection) {
		flexboxMixin.setFlexDirection(flexDirection);
	}

	@Override
	public void setFlex(Flex flex) {
		flexboxMixin.setFlex(flex);
	}

	@Override
	public void setFlexGrow(Integer flexGrow) {
		flexboxMixin.setFlexGrow(flexGrow);
	}

	@Override
	public void setFlexShrink(Integer flexShrink) {
		flexboxMixin.setFlexShrink(flexShrink);
	}

	@Override
	public void setFlexBasis(String flexBasis) {
		flexboxMixin.setFlexBasis(flexBasis);
	}

	@Override
	public void setFlexOrder(Integer flexOrder) {
		flexboxMixin.setFlexOrder(flexOrder);
	}

	@Override
	public void setFlexWrap(FlexWrap flexWrap) {
		flexboxMixin.setFlexWrap(flexWrap);
	}

	@Override
	public void setFlexAlignContent(FlexAlignContent flexAlignContent) {
		flexboxMixin.setFlexAlignContent(flexAlignContent);
	}

	@Override
	public void setFlexAlignSelf(FlexAlignSelf flexAlignSelf) {
		flexboxMixin.setFlexAlignSelf(flexAlignSelf);
	}

	@Override
	public void setFlexAlignItems(FlexAlignItems flexAlignItems) {
		flexboxMixin.setFlexAlignItems(flexAlignItems);
	}

	@Override
	public void setFlexJustifyContent(FlexJustifyContent flexJustifyContent) {
		flexboxMixin.setFlexJustifyContent(flexJustifyContent);
	}

	public void setScrollBarWidth(final String width) {
		setStyleProperty(CssMixin.MDC_SCROLLBAR_WIDTH, width);
	}

	public void setScrollBarTrackColor(final Color color) {
		setStyleProperty(CssMixin.MDC_SCROLLBAR_TRACK_FILL, color.getCssName());
	}

	public void setScrollBarThumbCorner(final String width) {
		setStyleProperty(CssMixin.MDC_SCROLLBAR_THUMB_CORNER, width);
	}

	public void setScrollBarThumbColor(final Color color) {
		setStyleProperty(CssMixin.MDC_SCROLLBAR_THUMB_FILL, color.getCssName());
	}
}
