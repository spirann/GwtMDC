package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.constants.IconType;

public class IconMixin<T extends UIObject> extends AbstractMixin<T> implements HasIcon {

	private IconType iconType;

	public IconMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public IconType getIcon() {
		return iconType;
	}

	@Override
	public void setIcon(IconType iconType) {
		this.iconType = iconType;
		if (iconType == null) {
			uiObject.getElement().setInnerHTML("");
		} else {
			uiObject.getElement().setInnerHTML(iconType.getCssName());
		}
	}

}
