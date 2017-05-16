package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialLayoutSpacer extends MaterialWidget{

	public MaterialLayoutSpacer(){
		super(Document.get().createDivElement(), CssName.MDL_LAYOUT_SPACER);
	}
	
}
