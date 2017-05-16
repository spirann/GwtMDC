package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialTabBar extends MaterialWidget{

	public MaterialTabBar(){
		super(Document.get().createDivElement(), CssName.MDL_LAYOUT_TAB_BAR, CssName.MDL_RIPPLE_EFFECT);
	}
	
}
