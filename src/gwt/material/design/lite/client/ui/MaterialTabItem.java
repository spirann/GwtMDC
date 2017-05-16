package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.lite.client.base.HasActive;
import gwt.material.design.lite.client.base.HasHref;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.ActiveMixin;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialTabItem extends MaterialWidget implements HasText, HasHref, HasActive{
	
	private  ActiveMixin<MaterialTabItem> activeMixin;
	
	public MaterialTabItem() {
		super(Document.get().createElement("a"), CssName.MDL_LAYOUT_TAB_ITEM);
	}
	
	protected ActiveMixin<MaterialTabItem> getActiveMixin(){		
		if(activeMixin == null){
			activeMixin = new ActiveMixin<MaterialTabItem>(this);
		}
		return activeMixin;
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

	@Override
	public void setActive(boolean active) {
		getActiveMixin().setActive(active);
	}
	
	@Override
	public boolean isActive() {
		return getActiveMixin().isActive();
	}
}
