package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasAspectRatio;
import gwt.material.design.components.client.base.HasCaptionContent;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AspectRatioMixin;
import gwt.material.design.components.client.base.mixin.CaptionSettingMixin;
import gwt.material.design.components.client.constants.AspectRatio;
import gwt.material.design.components.client.constants.CaptionIconPosition;
import gwt.material.design.components.client.constants.CaptionPosition;
import gwt.material.design.components.client.constants.CaptionType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialGridList extends MaterialWidget implements HasCaptionContent, HasAspectRatio {	
	
	private final CaptionSettingMixin<MaterialGridList> captionSettingMixin = new CaptionSettingMixin<MaterialGridList>(this);
	private final AspectRatioMixin<MaterialGridList> aspectRatioMixin = new AspectRatioMixin<MaterialGridList>(this, CssName.MDC_GRID_LIST_TILE_ASPECT);
	
	public MaterialGridList(){
		super(HtmlElements.DIV.createElement(), CssName.MDC_GRID_LIST);
	}
	
	@Override
	public void setCaptionType(CaptionType type) {
		captionSettingMixin.setCaptionType(type);
	}

	@Override
	public CaptionType getCaptionType() {
		return captionSettingMixin.getCaptionType();
	}

	@Override
	public void setCaptionPosition(CaptionPosition position) {
		captionSettingMixin.setCaptionPosition(position);
	}

	@Override
	public CaptionPosition getCaptionPosition() {
		return captionSettingMixin.getCaptionPosition();
	}

	@Override
	public void setCaptionIconPosition(CaptionIconPosition position) {
		captionSettingMixin.setCaptionIconPosition(position);
	}

	@Override
	public CaptionIconPosition getCaptionIconPosition() {
		return captionSettingMixin.getCaptionIconPosition();
	}

	@Override
	public void setAspectRatio(AspectRatio aspectRatio) {
		aspectRatioMixin.setAspectRatio(aspectRatio);
	}

	@Override
	public AspectRatio getAspectRatio() {
		return aspectRatioMixin.getAspectRatio();
	}

}
