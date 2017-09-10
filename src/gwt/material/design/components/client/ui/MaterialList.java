package gwt.material.design.components.client.ui;



import gwt.material.design.components.client.base.HasDense;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Ul;

public class MaterialList extends Ul implements HasDense{

	protected final ApplyStyleMixin<MaterialList> avatarMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_LIST_AVATAR_LIST);
	
	protected final ApplyStyleMixin<MaterialList> denseMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_LIST_DENSE);
	
	public MaterialList(){
		super(CssName.MDC_LIST);
	}
	
	public void setHasAvatar(final boolean hasAvatar) {
		avatarMixin.setApply(hasAvatar);
	}

	@Override
	public void setDense(boolean dense) {
		denseMixin.setApply(dense);
	}

	@Override
	public boolean isDense() {
		return denseMixin.isApplied();
	}
}
