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

import com.google.gwt.dom.client.Element;
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
public class MaterialUIObject extends ComplexPanel implements HasFlexbox {

	protected final FlexboxMixin<MaterialUIObject> flexboxMixin = new FlexboxMixin<>(this);

	public MaterialUIObject(Element element) {
		super();
		setElement(element);
	}
	
	public void setAttribute(final String attribute, final String value) {
		StyleHelper.setAttribute(getElement(), attribute, value);
	}
	
	public void removeAttribute(final String attribute) {
		StyleHelper.removeAttribute(getElement(), attribute);
	}
	
	public String getAttribute(final String attribute) {
		return StyleHelper.getAttribute(getElement(), attribute);
	}
	
	protected void setCssProperty(final String property, final String value) {
		StyleHelper.setCssProperty(getElement(), property, value);
	}
	
	protected void setCssProperty(final String property, final Style.HasCssName value) {
		StyleHelper.setCssProperty(getElement(), property, value);
	}

	public boolean hasStyleName(String style) {
		return StyleHelper.hasStyle(this, style);
	}
	
	@Override
	public void addStyleName(String style) {
		StyleHelper.addStyle(this, style);
	}

	@Override
	public void removeStyleName(String style) {
		StyleHelper.removeStyle(this, style);
	}
	
	public void toggleStyleName(final boolean toggleStyle, final String styleName) {
		StyleHelper.toggleStyle(this, toggleStyle, styleName);
	}

	public void toggleStyleName(final String styleName) {
		StyleHelper.toggleStyle(this, styleName);
	}
	
	public void setInnerHTML(final String html) {
		getElement().setInnerHTML(html);
	}

	public void setInnerSafeHTML(final SafeHtml html) {
		getElement().setInnerSafeHtml(html);
	}

	@Override
	public void setWidth(final String width) {
		setCssProperty("width", width);
	}

	@Override
	public void setHeight(final String height) {
		setCssProperty("height", height);
	}

	public void setMaxWidth(final String maxWidth) {
		setCssProperty("max-width", maxWidth);
	}

	public void setMinWidth(final String minWidth) {
		setCssProperty("min-width", minWidth);
	}

	public void setMaxHeight(final String maxHeight) {
		setCssProperty("max-height", maxHeight);
	}

	public void setMinHeight(final String minHeight) {
		setCssProperty("min-height", minHeight);
	}

	public void setLineHeight(final String lineHeight) {
		setCssProperty("line-height", lineHeight);
	}

	public void setLetterSpacing(final String letterSpacing) {
		setCssProperty("letter-spacing", letterSpacing);
	}

	public void setFontSize(final String fontSize) {
		setCssProperty("font-size", fontSize);
	}

	public void setFontFamily(final String fontFamily) {
		setCssProperty("font-family", fontFamily);
	}

	public void setTextAlign(final TextAlign textAlign) {
		setCssProperty("text-align", textAlign);
	}

	public void setCursor(Cursor cursor) {
		setCssProperty("cursor", cursor);
	}

	public void setPadding(final int padding) {
		setCssProperty("padding", padding + "px");
	}

	public void setPaddingTop(final int paddingTop) {
		setCssProperty("padding-top", paddingTop + "px");
	}

	public void setPaddingBottom(final int paddingBottom) {
		setCssProperty("padding-bottom", paddingBottom + "px");
	}

	public void setPaddingLeft(final int paddingLeft) {
		setCssProperty("padding-left", paddingLeft + "px");
	}

	public void setPaddingRight(final int paddingRight) {
		setCssProperty("padding-right", paddingRight + "px");
	}

	public void setMargin(final int margin) {
		setCssProperty("margin", margin + "px");
	}

	public void setMarginTop(final int marginTop) {
		setCssProperty("margin-top", marginTop + "px");
	}

	public void setMarginBottom(final int marginBottom) {
		setCssProperty("margin-bottom", marginBottom + "px");
	}

	public void setMarginLeft(final int marginLeft) {
		setCssProperty("margin-left", marginLeft + "px");
	}

	public void setMarginRight(final int marginRight) {
		setCssProperty("margin-right", marginRight + "px");
	}

	public void setBackground(final String background) {
		setCssProperty("background", background);
	}

	public void setBackgroundImageResource(final ImageResource imageResource) {
		setCssProperty("background",
				"url('" + imageResource.getSafeUri().asString() + "') no-repeat center center fixed");
		setCssProperty("-webkit-background-size", "cover");
		setCssProperty("-moz-background-size", "cover");
		setCssProperty("-o-background-size", "cover");
		setCssProperty("background-size", "cover");
	}

	public void setBackgroundImage(final String url) {
		setCssProperty("background-image", url);
	}

	public void setBackgroundColor(final Color color) {
		setCssProperty("background-color", color);
	}

	public void setColor(final Color color) {
		setCssProperty("color", color);
	}

	public void setTextColor(final Color color) {
		setColor(color);
	}

	public void setOverflow(final Overflow overflow) {
		setCssProperty("overflow", overflow);
	}

	public void setOverflowX(final Overflow overflow) {
		setCssProperty("overflow-x", overflow);
	}

	public void setOverflowY(final Overflow overflow) {
		setCssProperty("overflow-y", overflow);
	}

	public void setTop(final String top) {
		setCssProperty("top", top);
	}

	public void setBottom(final String bottom) {
		setCssProperty("bottom", bottom);
	}

	public void setLeft(final String left) {
		setCssProperty("left", left);
	}

	public void setRight(final String right) {
		setCssProperty("right", right);
	}

	public void setLayoutPosition(final Style.Position position) {
		setCssProperty("position", position);
	}

	public void setZIndex(final int zIndex) {
		setCssProperty("z-index", String.valueOf(zIndex));
	}

	public void setOutlineWidth(final int borderWidth) {
		setCssProperty("outline-width", borderWidth + "px");
	}

	public void setOutilineColor(final Color color) {
		setCssProperty("outline-color", color);
	}

	public void setOutlineStyle(final OutlineStyle outlineStyle) {
		setCssProperty("outline-style", outlineStyle);
	}

	public void setBorderWidth(final int borderWidth) {
		setCssProperty("border-width", borderWidth + "px");
	}

	public void setBorderColor(final Color color) {
		setCssProperty("border-color", color);
	}

	public void setBorderStyle(final BorderStyle borderStyle) {
		setCssProperty("border-style", borderStyle);
	}

	public void setBorderSpacing(final int borderSpacing) {
		setCssProperty("border-spacing", borderSpacing + "px");
	}

	public void setBorderCollapse(final BorderCollapse borderCollapse) {
		setCssProperty("border-collapse", borderCollapse);
	}

	public void setBorderRadius(final String borderRadius) {
		setCssProperty("border-radius", borderRadius);
	}

	public void setOpacity(final double opacity) {
		setCssProperty("opacity", String.valueOf(opacity));
	}

	public final void setVisibility(Visibility value) {
		setCssProperty("visibility", value);
	}

	/*
	 * -------------------------------------------------------------------------------
	 * Transform and animations
	 * -------------------------------------------------------------------------------
	 */
	public final void setTransform(final String transform) {
		setCssProperty("-webkit-transform", transform);
		setCssProperty("-moz-transform", transform);
		setCssProperty("-o-transform", transform);
		setCssProperty("transform", transform);
	}
	
	public final void setTransformOrigin(final String transformOrigin) {
		setCssProperty("-webkit-transform-origin", transformOrigin);
		setCssProperty("-moz-transform-origin", transformOrigin);
		setCssProperty("-o-transform-origin", transformOrigin);
		setCssProperty("transform-origin", transformOrigin);
	}
	
	public final void setTransition(final String trasition) {
		setCssProperty("-webkit-transition", trasition);
		setCssProperty("-moz-transition", trasition);
		setCssProperty("-o-transition", trasition);
		setCssProperty("transition", trasition);
	}
	
	public final void setTransitionDelay(final String trasitionDelay) {
		setCssProperty("-webkit-transition-delay", trasitionDelay);
		setCssProperty("-moz-transition-delay", trasitionDelay);
		setCssProperty("-o-transition-delay", trasitionDelay);
		setCssProperty("transition-delay", trasitionDelay);
	}	
	
	public final void setTransitionDuration(final String trasitionDuration) {
		setCssProperty("-webkit-transition-duration", trasitionDuration);
		setCssProperty("-moz-transition-duration", trasitionDuration);
		setCssProperty("-o-transition-duration", trasitionDuration);
		setCssProperty("transition-duration", trasitionDuration);
	}
	
	public final void setTransitionProperty(final String trasitionProperty) {
		setCssProperty("-webkit-transition-property", trasitionProperty);
		setCssProperty("-moz-transition-property", trasitionProperty);
		setCssProperty("-o-transition-property", trasitionProperty);
		setCssProperty("transition-property", trasitionProperty);
	}
	
	public final void setTransitionTimingFunction(final String trasitionTimingFunction) {
		setCssProperty("-webkit-transition-timing-function", trasitionTimingFunction);
		setCssProperty("-moz-transition-timing-function", trasitionTimingFunction);
		setCssProperty("-o-transition-timing-function", trasitionTimingFunction);
		setCssProperty("transition-timing-function", trasitionTimingFunction);
	}	
	
	/*
	 * -------------------------------------------------------------------------------
	 * FLEX BOX SETTER'S 
	 * -------------------------------------------------------------------------------
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
		setCssProperty(CssMixin.MDC_SCROLLBAR_WIDTH, width);
	}

	public void setScrollBarTrackColor(final Color color) {
		setCssProperty(CssMixin.MDC_SCROLLBAR_TRACK_FILL, color);
	}

	public void setScrollBarThumbCorner(final String width) {
		setCssProperty(CssMixin.MDC_SCROLLBAR_THUMB_CORNER, width);
	}

	public void setScrollBarThumbColor(final Color color) {
		setCssProperty(CssMixin.MDC_SCROLLBAR_THUMB_FILL, color);
	}
}
