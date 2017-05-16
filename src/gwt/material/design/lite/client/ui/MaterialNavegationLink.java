package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.lite.client.base.HasHref;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialNavegationLink extends MaterialWidget implements HasText, HasHref{
	
	public MaterialNavegationLink() {
		super(Document.get().createElement("a"), CssName.MDL_NAVEGATION_LINK);
	}
	
	@Override
	public String getText() {
		return getElement().getInnerText();
	}

	@Override
	public void setText(String text) {
		getElement().setInnerText(text);
	}
	
	@Override
    public void setHref(String href) {
        getElement().setAttribute("href", href);
    }

    @Override
    public String getHref() {
        return getElement().getAttribute("href");
    }
}
