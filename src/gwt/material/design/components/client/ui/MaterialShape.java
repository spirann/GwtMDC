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

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialShape extends Div {

	private Div cornerTopLeft = new Div(CssName.MDC_SHAPE_CONTAINER__CORNER,
			CssName.MDC_SHAPE_CONTAINER__CORNER__TOP_LEFT);
	private Div cornerTopRight = new Div(CssName.MDC_SHAPE_CONTAINER__CORNER,
			CssName.MDC_SHAPE_CONTAINER__CORNER__TOP_RIGHT);
	private Div cornerBottomLeft = new Div(CssName.MDC_SHAPE_CONTAINER__CORNER,
			CssName.MDC_SHAPE_CONTAINER__CORNER__BOTTOM_LEFT);
	private Div cornerBottomRight = new Div(CssName.MDC_SHAPE_CONTAINER__CORNER,
			CssName.MDC_SHAPE_CONTAINER__CORNER__BOTTOM_RIGHT);

	public MaterialShape() {
		super(CssName.MDC_SHAPE_CONTAINER);
	}

	protected void setCorner(final Widget corner, final boolean show) {
		if (show) {
			add(corner);
		} else {
			remove(corner);
		}
	}

	public void setTopLeft(final boolean show) {
		setCorner(cornerTopLeft, show);
	}

	public void setTopRight(final boolean show) {
		setCorner(cornerTopRight, show);
	}

	public void setBottomLeft(final boolean show) {
		setCorner(cornerBottomLeft, show);
	}

	public void setBottomRight(final boolean show) {
		setCorner(cornerBottomRight, show);
	}

	public void setCornerOutlineColor(final Color color) {
		setCornerOutlineColor(cornerTopLeft, color);
		setCornerOutlineColor(cornerTopRight, color);
		setCornerOutlineColor(cornerBottomLeft, color);
		setCornerOutlineColor(cornerBottomRight, color);
	}

	public void setCornerOutlineSize(final int size) {
		setCornerOutlineSize(cornerTopLeft, size);
		setCornerOutlineSize(cornerTopRight, size);
		setCornerOutlineSize(cornerBottomLeft, size);
		setCornerOutlineSize(cornerBottomRight, size);
	}
	
	public void setCornerOutlineStyle(final BorderStyle style) {		
		setCornerOutlineStyle(cornerTopLeft, style);
		setCornerOutlineStyle(cornerTopRight, style);
		setCornerOutlineStyle(cornerBottomLeft, style);
		setCornerOutlineStyle(cornerBottomRight, style);
	}

	protected void setCornerOutlineColor(final Widget widget, final Color color) {
		widget.getElement().getStyle().setProperty("borderBottomColor", color.getCssName());
	}

	protected void setCornerOutlineSize(final Widget widget, final int size) {
		widget.getElement().getStyle().setProperty("borderBottomWidth", size + "px");
	}
	
	protected void setCornerOutlineStyle(final Widget widget, final BorderStyle style) {
		widget.getElement().getStyle().setProperty("borderBottomStyle", style.getCssName());
	}

	public void setCornerColor(final Color color) {
		cornerTopLeft.setBackgroundColor(color);
		cornerTopRight.setBackgroundColor(color);
		cornerBottomLeft.setBackgroundColor(color);
		cornerBottomRight.setBackgroundColor(color);
	}

	public void setCornerSize(final int pixels) {

		final String size = (pixels * 1.4142135623730951) + "";
		final String position = (-1d * ((pixels * 1.4142135623730951) / 2d)) + "";

		cornerTopLeft.setSize(size, size);
		cornerTopLeft.setTop(position);
		cornerTopLeft.setLeft(position);

		cornerTopRight.setSize(size, size);
		cornerTopRight.setTop(position);
		cornerTopRight.setRight(position);

		cornerBottomLeft.setSize(size, size);
		cornerBottomLeft.setBottom(position);
		cornerBottomLeft.setLeft(position);

		cornerBottomRight.setSize(size, size);
		cornerBottomRight.setBottom(position);
		cornerBottomRight.setRight(position);
	}
}
