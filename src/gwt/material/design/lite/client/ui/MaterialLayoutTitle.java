package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialLayoutTitle extends MaterialWidget implements HasText{
	
	public MaterialLayoutTitle() {
		super(Document.get().createElement("span"), CssName.MDL_LAYOUT_TITLE);
	}
	
	@Override
	public String getText() {
		return getElement().getInnerText();
	}

	@Override
	public void setText(String text) {
		getElement().setInnerText(text);
	}
}
