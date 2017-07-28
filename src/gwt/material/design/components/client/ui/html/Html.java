package gwt.material.design.components.client.ui.html;



import gwt.material.design.components.client.base.HasToolbarFixedAdjust;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.StyleEnabledMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class Html extends MaterialWidget implements HasToolbarFixedAdjust{

	private final StyleEnabledMixin<Html> toolbarFixedAdjustMixin = new StyleEnabledMixin<>(
			this, CssName.MDC_TOOLBAR_FIXED_ADJUST);
	
	public Html(){
		super(HtmlElements.HTML.createElement(), CssName.MDC_TYPOGRAPHY);
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