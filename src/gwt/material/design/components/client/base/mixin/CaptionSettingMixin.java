package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.components.client.base.HasCaptionContent;
import gwt.material.design.components.client.constants.CaptionIconPosition;
import gwt.material.design.components.client.constants.CaptionPosition;
import gwt.material.design.components.client.constants.CaptionType;

public class CaptionSettingMixin<W extends Widget> extends AbstractMixin<W> implements HasCaptionContent {

	private CaptionType type;
	private CaptionPosition position;
	private CaptionIconPosition iconPosition;

	private final StyleMixin<W> typeMixin;
	private final StyleMixin<W> positionMixin;
	private final StyleMixin<W> iconPositionMixin;

	public CaptionSettingMixin(final W widget) {
		super(widget);
		typeMixin = new StyleMixin<W>(widget);
		positionMixin = new StyleMixin<W>(widget);
		iconPositionMixin = new StyleMixin<W>(widget);
	}

	@Override
	public void setCaptionType(CaptionType type) {
		this.type = type;
		if (type == null) {
			typeMixin.setStyle(null);
		} else {
			typeMixin.setStyle(type.getCssName());
		}
	}

	@Override
	public CaptionType getCaptionType() {
		return type;
	}

	@Override
	public void setCaptionPosition(CaptionPosition position) {
		this.position = position;
		if (position == null) {
			positionMixin.setStyle(null);
		} else {
			positionMixin.setStyle(position.getCssName());
		}
	}

	@Override
	public CaptionPosition getCaptionPosition() {
		return position;
	}

	@Override
	public void setCaptionIconPosition(CaptionIconPosition iconPosition) {
		this.iconPosition = iconPosition;
		if (iconPosition == null) {
			iconPositionMixin.setStyle(null);
		} else {
			iconPositionMixin.setStyle(iconPosition.getCssName());
		}
	}

	@Override
	public CaptionIconPosition getCaptionIconPosition() {
		return iconPosition;
	}

}
