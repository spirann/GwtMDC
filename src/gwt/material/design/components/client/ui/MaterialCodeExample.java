package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialCodeExample extends MaterialWidget  {

	public MaterialCodeExample() {
		super(Document.get().createElement(HtmlElements.PRE), CssName.PRISM_LANGUAGE_MARKUP);	
		setPadding(24);
	}

}
