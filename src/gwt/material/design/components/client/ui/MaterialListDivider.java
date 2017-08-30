package gwt.material.design.components.client.ui;



import gwt.material.design.components.client.base.HasInset;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Role;

public class MaterialListDivider extends MaterialWidget implements HasInset {

	protected final ApplyStyleMixin<MaterialListDivider> insetMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_LIST_DIVIDER_INSET);
	
	public MaterialListDivider(){
		super(HtmlElements.DIV.createElement(), CssName.MDC_LIST_DIVIDER);
		setRole(Role.SEPARATOR);
	}
	
	public MaterialListDivider(final boolean inset){
		this();
		setInset(inset);
	}

	@Override
	public void setInset(boolean inset) {
		insetMixin.setApply(inset);
	}

	@Override
	public boolean isInset() {
		return insetMixin.isApplied();
	}
	
}
