package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.components.client.base.HasToolbarFixedAdjust;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.StyleEnabledMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialMain extends MaterialWidget implements HasToolbarFixedAdjust{

	private final StyleEnabledMixin<MaterialMain> toolbarFixedAdjustMixin = new StyleEnabledMixin<>(
			this, CssName.MDC_TOOLBAR_FIXED_ADJUST);
	
	public MaterialMain(){
		super(Document.get().createElement(HtmlElements.MAIN), CssName.MDC_TYPOGRAPHY);
	}

	@Override
	public void setToolbarFixedAdjust(boolean toolbarFixedAdjust) {
		toolbarFixedAdjustMixin.setEnabled(toolbarFixedAdjust);
	}

	@Override
	public boolean isToolbarFixedAdjust() {
		return toolbarFixedAdjustMixin.isEnabled();
	}
}