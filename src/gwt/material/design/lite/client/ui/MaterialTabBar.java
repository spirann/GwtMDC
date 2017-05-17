package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.HasManualSwitch;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.ManualSwitchMixin;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialTabBar extends MaterialWidget implements HasManualSwitch{

	private ManualSwitchMixin<MaterialTabBar> manualSwitchMixin;
	
	public MaterialTabBar(){
		super(Document.get().createDivElement(), CssName.MDL_LAYOUT_TAB_BAR, CssName.MDL_RIPPLE_EFFECT);
	}

	protected ManualSwitchMixin<MaterialTabBar> getManualSwitchMixin() {
		if (manualSwitchMixin == null) {
			manualSwitchMixin = new ManualSwitchMixin<>(this);
		}
		return manualSwitchMixin;
	}
		
	@Override
	public void setManualSwitch(boolean manual) {
		getManualSwitchMixin().setManualSwitch(manual);
	}
	
}
