package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasAspectRatio;
import gwt.material.design.components.client.constants.AspectRatio;

public class AspectRatioMixin<W extends UIObject> extends StyleMixin<W> implements HasAspectRatio {

	private AspectRatio aspectRatio;

	private String cssClass;
	
	public AspectRatioMixin(final W widget, final String cssClass) {
		super(widget);
		this.cssClass = cssClass;
	}
	
	@Override
	public void setAspectRatio(AspectRatio aspectRatio) {
		this.aspectRatio = aspectRatio;
		if (aspectRatio == null) {
			setStyle(null);
		} else {
			setStyle(cssClass + aspectRatio.getCssName());
		}
	}
	
	@Override
	public AspectRatio getAspectRatio() {
		return aspectRatio;
	}

}
