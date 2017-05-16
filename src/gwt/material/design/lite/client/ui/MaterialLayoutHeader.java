package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.HasHeaderTransparent;
import gwt.material.design.lite.client.base.HasType;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.HeaderTransparentMixin;
import gwt.material.design.lite.client.base.mixin.HeaderTypeMixin;
import gwt.material.design.lite.client.constants.CssName;
import gwt.material.design.lite.client.constants.HeaderType;

public class MaterialLayoutHeader extends MaterialWidget implements HasHeaderTransparent, HasType<HeaderType> {

	private HeaderTransparentMixin<MaterialLayoutHeader> headerTransparentMixin;
	private HeaderTypeMixin<MaterialLayoutHeader> headerTypeMixin;

	public MaterialLayoutHeader() {
		super(Document.get().createElement("header"), CssName.MDL_LAYOUT_HEADER);
	}

	protected HeaderTransparentMixin<MaterialLayoutHeader> getHeaderTransparentMixin() {
		if (headerTransparentMixin == null) {
			headerTransparentMixin = new HeaderTransparentMixin<>(this);
		}
		return headerTransparentMixin;
	}
	
	protected HeaderTypeMixin<MaterialLayoutHeader> getHeaderTypeMixin() {
		if (headerTypeMixin == null) {
			headerTypeMixin = new HeaderTypeMixin<>(this);
		}
		return headerTypeMixin;
	}

	@Override
	public void setTransparent(boolean transparent) {
		getHeaderTransparentMixin().setTransparent(transparent);
	}

	@Override
	public void setType(HeaderType type) {
		getHeaderTypeMixin().setType(type);
	}

	@Override
	public HeaderType getType() {
		return getHeaderTypeMixin().getType();
	}
	
	
}
