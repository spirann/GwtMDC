package gwt.material.design.components.client.ui;

import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.HrefMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.ButtonType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Button;

public class MaterialButton extends Button implements HasType<ButtonType>, HasHref, HasText {

	protected final TextMixin<MaterialButton> textMixin = new TextMixin<>(this);
	protected final HrefMixin<MaterialButton> hrefMixin = new HrefMixin<>(this);
	protected final TypeMixin<MaterialButton, ButtonType> typeMixin = new TypeMixin<>(this);

	private boolean initialized = false;

	public MaterialButton() {
		super(CssName.MDC_BUTTON);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			if (getType() == null) {
				setType(ButtonType.SECONDARY_RAISED);
			}

			initialized = true;

		}
	}

	@Override
	public String getText() {
		return textMixin.getText();
	}

	@Override
	public void setText(String text) {
		textMixin.setText(text);
	}

	@Override
	public void setHref(String href) {
		hrefMixin.setHref(href);
	}

	@Override
	public String getHref() {
		return hrefMixin.getHref();
	}

	@Override
	public void setTarget(String target) {
		hrefMixin.setTarget(target);
	}

	@Override
	public String getTarget() {
		return hrefMixin.getTarget();
	}

	@Override
	public void setType(ButtonType type) {
		typeMixin.setType(type);
	}

	@Override
	public ButtonType getType() {
		return typeMixin.getType();
	}
}
