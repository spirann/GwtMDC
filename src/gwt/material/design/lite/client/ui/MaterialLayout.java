package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.HasFixedDrawer;
import gwt.material.design.lite.client.base.HasFixedHeader;
import gwt.material.design.lite.client.base.HasFixedTabs;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.FixedDrawerMixin;
import gwt.material.design.lite.client.base.mixin.FixedHeaderMixin;
import gwt.material.design.lite.client.base.mixin.FixedTabsMixin;
import gwt.material.design.lite.client.constants.CssName;

public class MaterialLayout extends MaterialWidget implements HasFixedHeader, HasFixedDrawer, HasFixedTabs {

	private FixedHeaderMixin<MaterialLayout> fixedHeaderMixin;
	private FixedDrawerMixin<MaterialLayout> fixedDrawerMixin;
	private FixedTabsMixin<MaterialLayout> fixedTabsMixin;

	public MaterialLayout() {
		super(Document.get().createDivElement(), CssName.MDL_LAYOUT, CssName.MDL_JS_LAYOUT);
	}

	protected FixedHeaderMixin<MaterialLayout> getFixedHeaderMixin() {
		if (fixedHeaderMixin == null) {
			fixedHeaderMixin = new FixedHeaderMixin<>(this);
		}
		return fixedHeaderMixin;
	}
	
	protected FixedDrawerMixin<MaterialLayout> getFixedDrawerMixin() {
		if (fixedDrawerMixin == null) {
			fixedDrawerMixin = new FixedDrawerMixin<>(this);
		}
		return fixedDrawerMixin;
	}
	
	protected FixedTabsMixin<MaterialLayout> getFixedTabsMixin() {
		if (fixedTabsMixin == null) {
			fixedTabsMixin = new FixedTabsMixin<>(this);
		}
		return fixedTabsMixin;
	}

	@Override
	public void setFixedHeader(boolean fixed) {
		getFixedHeaderMixin().setFixedHeader(fixed);
	}
	
	@Override
	public void setFixedDrawer(boolean fixed) {
		getFixedDrawerMixin().setFixedDrawer(fixed);
	}

	@Override
	public void setFixedTabs(boolean fixed) {
		getFixedTabsMixin().setFixedTabs(fixed);
	}
}
