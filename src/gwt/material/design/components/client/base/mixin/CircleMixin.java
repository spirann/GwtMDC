package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasCircle;

public class CircleMixin<T extends Widget & HasCircle> extends AbstractMixin<T> implements HasCircle {
	
	public CircleMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setCircle(boolean circle) {
		if(circle){
			uiObject.getElement().getStyle().setProperty("borderRadius", "50%");
		}else {
			uiObject.getElement().getStyle().setProperty("borderRadius", "0");
		}
	}

}
