package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialLayoutContent extends MaterialWidget{

	public MaterialLayoutContent(){
		super(Document.get().createElement("main"), CssName.MDL_LAYOUT_CONTENT);
	}
	
}
