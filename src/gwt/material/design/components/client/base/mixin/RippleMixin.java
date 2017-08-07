package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasRipple;
import gwt.material.design.components.client.constants.Ripple;

public class RippleMixin<W extends Widget> extends StyleMixin<W> implements HasRipple {

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

}
