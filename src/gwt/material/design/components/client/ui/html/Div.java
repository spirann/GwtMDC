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
		super(HtmlElements.DIV.createElement());
	}
	
	public Div(final String primaryClass) {
		super(HtmlElements.DIV.createElement(), primaryClass);
	}
	
	public Div(final String primaryClass, final String ... initialClasses) {
		super(HtmlElements.DIV.createElement(), primaryClass, initialClasses);
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
