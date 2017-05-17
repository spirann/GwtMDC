package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.HasHeaderSeamed;
import gwt.material.design.lite.client.base.HasHeaderTransparent;
import gwt.material.design.lite.client.base.HasType;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.HeaderSeamedMixin;
import gwt.material.design.lite.client.base.mixin.HeaderTransparentMixin;
import gwt.material.design.lite.client.base.mixin.HeaderTypeMixin;
import gwt.material.design.lite.client.constants.CssName;
import gwt.material.design.lite.client.constants.HeaderType;

public class MaterialLayoutHeader extends MaterialWidget implements HasType<HeaderType>, HasHeaderTransparent, HasHeaderSeamed {

	private HeaderTypeMixin<MaterialLayoutHeader> headerTypeMixin;
	private HeaderTransparentMixin<MaterialLayoutHeader> headerTransparentMixin;
	private HeaderSeamedMixin<MaterialLayoutHeader> headerSeamedMixin;
	

	public MaterialLayoutHeader() {
		super(Document.get().createElement("header"), CssName.MDL_LAYOUT_HEADER);
	}

	protected HeaderTypeMixin<MaterialLayoutHeader> getHeaderTypeMixin() {
		if (headerTypeMixin == null) {
			headerTypeMixin = new HeaderTypeMixin<>(this);
		}
		return headerTypeMixin;
	}
	
	protected HeaderTransparentMixin<MaterialLayoutHeader> getHeaderTransparentMixin() {
		if (headerTransparentMixin == null) {
			headerTransparentMixin = new HeaderTransparentMixin<>(this);
		}
		return headerTransparentMixin;
	}

	protected HeaderSeamedMixin<MaterialLayoutHeader> getHeaderSeamedMixin() {
		if (headerSeamedMixin == null) {
			headerSeamedMixin = new HeaderSeamedMixin<>(this);
		}
		return headerSeamedMixin;
	}

	@Override
	public void setType(HeaderType type) {
		getHeaderTypeMixin().setType(type);
	}

	@Override
	public HeaderType getType() {
		return getHeaderTypeMixin().getType();
	}

	@Override
	public void setTransparent(boolean transparent) {
		getHeaderTransparentMixin().setTransparent(transparent);
	}
	
	@Override
	public void setSeamed(boolean seamed) {
		getHeaderSeamedMixin().setSeamed(seamed);
	}
	
}
