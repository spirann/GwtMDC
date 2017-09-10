package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasInset;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Div;

public class MaterialListDivider extends Div implements HasInset {

	protected final ApplyStyleMixin<MaterialListDivider> insetMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_LIST_DIVIDER_INSET);

	public MaterialListDivider() {
		super(CssName.MDC_LIST_DIVIDER);
		setRole(Role.SEPARATOR);
	}

	public MaterialListDivider(final boolean inset) {
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
