package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialLayoutInner extends MaterialWidget {	
	public MaterialLayoutInner(){
		super(Document.get().createElement(HtmlElements.DIV), CssName.MDC_LAYOUT_GRID_INNER);
	}

}
