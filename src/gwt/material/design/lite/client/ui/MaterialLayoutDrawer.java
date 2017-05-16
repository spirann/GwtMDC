package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialLayoutDrawer extends MaterialWidget {
	
	public MaterialLayoutDrawer() {
		super(Document.get().createElement("div"), CssName.MDL_LAYOUT_DRAWER);
	}
}
