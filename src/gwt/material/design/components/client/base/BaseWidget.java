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
package gwt.material.design.components.client.base;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.ComplexPanel;

import gwt.material.design.components.client.constants.BorderCollapse;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class BaseWidget extends ComplexPanel {

	public void setStyleProperty(final String attribute, final String value) {
		StyleHelper.setStyleProperty(getElement(), attribute, value);
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

	public void setTextAlign(final TextAlign textAlign) {
		setStyleProperty("text-align", textAlign.getCssName());
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
		setBackgroundImage("url('" + imageResource.getSafeUri().asString() + "') center / cover");
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
	
	public void setBorderRadius(final int borderRadius) {
		setStyleProperty("border-radius", borderRadius + "px");
	}
}
