package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasRipple;
import gwt.material.design.components.client.constants.MdcAttribute;
import gwt.material.design.components.client.constants.Ripple;

public class RippleMixin<W extends UIObject> extends StyleMixin<W> implements HasRipple {

	private Ripple type;

	public RippleMixin(final W widget) {
		super(widget);
	}

	@Override
	public void setRipple(Ripple type) {
		this.type = type;
		if (type == null) {
			setStyle(null);
		} else {
			setStyle(type.getCssName());
		}
	}

	@Override
	public Ripple getRipple() {
		return type;
	}

	//@Override
	public void setUnbounded(boolean unbounded) {
		if (unbounded) {
			uiObject.getElement().setAttribute(MdcAttribute.DATA_MDC_RIPPLE_IS_UNBOUNDED, "");
		} else {
			uiObject.getElement().removeAttribute(MdcAttribute.DATA_MDC_RIPPLE_IS_UNBOUNDED);
		}
	}
}
