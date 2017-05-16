package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Overflow;

import gwt.material.design.lite.client.base.HasCellAlign;
import gwt.material.design.lite.client.base.HasCellStretch;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.CellStretchMixin;
import gwt.material.design.lite.client.base.mixin.CssNameMixin;
import gwt.material.design.lite.client.constants.CellAlign;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialCell extends MaterialWidget implements HasCellAlign, HasCellStretch {

    private CssNameMixin<MaterialCell, CellAlign> alignMixin;
    private CellStretchMixin<MaterialCell> strechMixin;
    
	public MaterialCell(){
		super(Document.get().createDivElement(), CssName.MDL_CEL);
		getElement().getStyle().setOverflow(Overflow.HIDDEN);
	}

	protected CssNameMixin<MaterialCell, CellAlign> getCellAlignMixin() {
        if (alignMixin == null) {
        	alignMixin = new CssNameMixin<>(this);
        }
        return alignMixin;
    }

	protected CellStretchMixin<MaterialCell> getCellStretchMixin() {
        if (strechMixin == null) {
        	strechMixin = new CellStretchMixin<>(this);
        }
        return strechMixin;
    }
	
	@Override
	public void setCellAlign(CellAlign align) {
		getCellAlignMixin().setCssName(align);
	}

	@Override
	public CellAlign getCellAlign() {
		return getCellAlignMixin().getCssName();
	}
	
	/**
	 * Stretches the cell vertically to fill the parent
	 */
	public void setStretch(final boolean stretch){
		getCellStretchMixin().setStretch(stretch);
	}
}
