package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.HasActive;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.ActiveMixin;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialTabContent extends MaterialWidget implements HasActive{
	
	private  ActiveMixin<MaterialTabContent> activeMixin;
	
	public MaterialTabContent() {
		super(Document.get().createElement("section"), CssName.MDL_LAYOUT_TAB_PANEL);
	}
	
	protected ActiveMixin<MaterialTabContent> getActiveMixin(){		
		if(activeMixin == null){
			activeMixin = new ActiveMixin<MaterialTabContent>(this);
		}
		return activeMixin;
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
