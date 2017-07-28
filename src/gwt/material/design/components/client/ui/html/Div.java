package gwt.material.design.components.client.ui.html;



import gwt.material.design.components.client.base.HasToolbarFixedAdjust;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.StyleEnabledMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class Div extends MaterialWidget implements HasToolbarFixedAdjust{

	private final StyleEnabledMixin<Div> toolbarFixedAdjustMixin = new StyleEnabledMixin<>(
			this, CssName.MDC_TOOLBAR_FIXED_ADJUST);
	
	public Div(){
		super(HtmlElements.DIV.createElement(), CssName.MDC_TYPOGRAPHY);
	}
	
	public Div(final String... cssClass) {
		super(HtmlElements.DIV.createElement(), CssName.MDC_TYPOGRAPHY, cssClass);
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
