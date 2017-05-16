package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialNavegation extends MaterialWidget {
	
	public MaterialNavegation() {
		super(Document.get().createElement("nav"), CssName.MDL_NAVEGATION);
	}
}
