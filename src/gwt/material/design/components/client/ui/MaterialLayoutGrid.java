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

import gwt.material.design.components.client.base.interfaces.HasAlign;
import gwt.material.design.components.client.base.interfaces.HasGap;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.LayoutGridAlign;
import gwt.material.design.components.client.ui.html.Div;

/**
 * The layout grid
 * 
 * A grid consists of a group of cells, which get positioned in sequence
 * according to a predefined number of columns. Cells specify how many columns
 * to span (the default being 4), and get placed side by side while there is
 * room. When there isn�t enough room for a cell, it gets moved to the beginning
 * of the next row, and placement continues as usual.
 * 
 * The grid has 12 columns in desktop mode (>= 840px), 8 columns in tablet mode
 * (>= 480px), and 4 columns in phone mode (< 480px). Column widths are
 * variable; margins and gaps are fixed, with columns taking up the remainder
 * of the space.
 * 
 * Margins (the space between the edge of the grid and the edge of the first
 * cell) and gaps (the space between edges of adjacent cells) are
 * user-definable, with the Material Design spec recommending 8px, 16px, 24px or
 * 40px as the sizes to choose from. The default margin and gap are both
 * 16px, but they don�t have to be the same size.
 * 
 * The grid and cells are not styled in any way, serving only for alignment and
 * positioning of elements.
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLayoutGrid extends Div implements HasAlign<LayoutGridAlign>, HasGap {
	
	protected final TypeMixin<MaterialLayoutGrid, LayoutGridAlign> alignMixin = new TypeMixin<>(this);

	public MaterialLayoutGrid() {
		super(CssName.MDC_LAYOUT_GRID);
	}

	public void setCellMargin(int margin) {
		setDesktopCellMargin(margin);
		setTabletCellMargin(margin);
		setPhoneCellMargin(margin);
	}
	
	public void setDesktopCellMargin(int margin) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_MARGIN_DESKTOP, margin + "px");
	}
	
	public void setTabletCellMargin(int margin) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_MARGIN_TABLET, margin + "px");
	}
	
	public void setPhoneCellMargin(int margin) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_MARGIN_PHONE, margin + "px");
	}

	public void setCellGutter(int gutter) {
		setDesktopCellGutter(gutter);
		setTabletCellGutter(gutter);
		setPhoneCellGutter(gutter);
	}
	
	public void setDesktopCellGutter(int gutter) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_GUTTER_DESKTOP, gutter + "px");
	}
	
	public void setTabletCellGutter(int gutter) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_GUTTER_TABLET, gutter + "px");
	}
	
	public void setPhoneCellGutter(int gutter) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_GUTTER_PHONE, gutter + "px");
	}
	
	
	@Override
	public void setGap(int gap) {
		setDesktopGap(gap);
		setTabletGap(gap);
		setPhoneGap(gap);
	}
	
	@Override
	public void setDesktopGap(int gap) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_GUTTER_DESKTOP, gap + "px");
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_MARGIN_DESKTOP, gap + "px");
	}
	
	@Override
	public void setTabletGap(int gap) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_GUTTER_TABLET, gap + "px");
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_MARGIN_TABLET, gap + "px");
	}
	
	@Override
	public void setPhoneGap(int gap) {
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_GUTTER_PHONE, gap + "px");
		setCssProperty(CssMixin.MDC_LAYOUT_GRID_MARGIN_PHONE, gap + "px");
	}
	
	/**
	 * 
	 */
	@Override
	public void setAlign(LayoutGridAlign align) {
		alignMixin.setType(align);
	}

	/**
	 * 
	 */
	@Override
	public LayoutGridAlign getAlign() {
		return alignMixin.getType();
	}

}
