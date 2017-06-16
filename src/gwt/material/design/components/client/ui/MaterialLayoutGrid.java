package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.components.client.base.HasAlign;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Align;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

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
 * variable; margins and gutters are fixed, with columns taking up the remainder
 * of the space.
 * 
 * Margins (the space between the edge of the grid and the edge of the first
 * cell) and gutters (the space between edges of adjacent cells) are
 * user-definable, with the Material Design spec recommending 8px, 16px, 24px or
 * 40px as the sizes to choose from. The default margin and gutter are both
 * 16px, but they don�t have to be the same size.
 * 
 * The grid and cells are not styled in any way, serving only for alignment and
 * positioning of elements.
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLayoutGrid extends MaterialWidget implements HasAlign {
	
	private final TypeMixin<MaterialLayoutGrid, Align> alignMixin = new TypeMixin<>(this);

	public MaterialLayoutGrid() {
		super(Document.get().createElement(HtmlElements.DIV), CssName.MDC_LAYOUT_GRID);
		setPadding("0");
	}

	/**
	 * Default is 16px
	 * 
	 * @param padding
	 */
	public void setGridMargin(int margin) {
		setStyleProperty("--mdc-layout-grid-margin", margin + "px");
	}

	/**
	 * Default is 16px
	 * 
	 * @param gutter
	 */
	public void setGutter(int gutter) {
		setStyleProperty("--mdc-layout-grid-gutter", gutter + "px");
	}
	
	/**
	 * 
	 */
	@Override
	public void setAlign(Align align) {
		alignMixin.setType(align);
	}

	/**
	 * 
	 */
	@Override
	public Align getAlign() {
		return alignMixin.getType();
	}

}
