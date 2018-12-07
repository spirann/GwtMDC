package gwt.material.design.components.client.base.mixin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasClassForIconPosition;
import gwt.material.design.components.client.base.interfaces.HasIcon;
import gwt.material.design.components.client.base.interfaces.HasIconPosition;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssType;
import gwt.material.design.components.client.constants.IconPosition;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.MaterialIcon;

public class InputIconMixin<UIO extends MaterialWidget & HasType<T>, T extends CssType & HasClassForIconPosition> extends AbstractMixin<UIO>
		implements HasIcon, HasType<T>, HasIconPosition {

	protected final Widget input;
	protected final MaterialIcon iconElement;
	protected final TypeMixin<UIO, T> typeMixin;

	private IconPosition iconPosition = IconPosition.LEADING;

	public InputIconMixin(final T type, final UIO uiObject, final MaterialIcon iconElement, final Widget input) {
		super(uiObject);
		this.typeMixin = new TypeMixin<>(uiObject, type);
		this.iconElement = iconElement;
		this.input = input;
	}

	@Override
	public IconType getIcon() {
		return iconElement.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		iconElement.setType(iconType);
		updateIconPositionClass();
	}

	@Override
	public void setIcon(IconType iconType, boolean animate) {
		iconElement.setType(iconType, animate);
		updateIconPositionClass();
	}

	@Override
	public void setIconColor(Color color) {
		iconElement.setColor(color);
	}

	@Override
	public void setType(T type) {
		clearIconPosition();
		typeMixin.setType(type);
		updateIconPositionClass();
	}

	@Override
	public T getType() {
		return typeMixin.getType();
	}

	@Override
	public void setIconPosition(IconPosition iconPosition) {
		this.iconPosition = iconPosition;
		updateIconPositionClass();
	}

	@Override
	public IconPosition getIconPosition() {
		return iconPosition;
	}

	void clearIconPosition() {
		final T type = typeMixin.getType();
		if (type != null) {
			uiObject.removeStyleName(type.getCssClassForLeadingIcon());
			uiObject.removeStyleName(type.getCssClassForTrailingIcon());
		}
	}

	public void updateIconPositionClass() {

		final T type = typeMixin.getType();
		if (type != null) {
			uiObject.removeStyleName(type.getCssClassForLeadingIcon());
			uiObject.removeStyleName(type.getCssClassForTrailingIcon());

			if (iconElement.getType() != null) {
				switch (iconPosition) {
				case TRAILING:
					uiObject.addStyleName(type.getCssClassForTrailingIcon());
					if (uiObject.isAttached())
						uiObject.insert(iconElement, uiObject.getWidgetIndex(input) + 1);
					break;
				case LEADING:
				default:
					uiObject.addStyleName(type.getCssClassForLeadingIcon());
					if (uiObject.isAttached())
						uiObject.insert(iconElement, uiObject.getWidgetIndex(input));
					break;
				}
			}
		}
	}
}
