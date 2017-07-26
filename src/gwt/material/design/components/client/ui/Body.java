package gwt.material.design.components.client.ui;



import gwt.material.design.components.client.base.HasToolbarFixedAdjust;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.StyleEnabledMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class Body extends MaterialWidget implements HasToolbarFixedAdjust{

	private final StyleEnabledMixin<Body> toolbarFixedAdjustMixin = new StyleEnabledMixin<>(
			this, CssName.MDC_TOOLBAR_FIXED_ADJUST);
	
	public Body(){
		super(HtmlElements.BODY.createElement(), CssName.MDC_TYPOGRAPHY);
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
