package gwt.material.design.components.client.ui;

import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Ripple;

public class MaterialToolbarNavLink extends MaterialWidget implements HasText {

	protected final TextMixin<MaterialToolbarNavLink> textMixin = new TextMixin<>(this);

	public MaterialToolbarNavLink() {
		super(HtmlElements.SPAN.createElement(), CssName.MDC_TOOLBAR_NAV_LINK);
	}

	private boolean initialized = false;

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			if (getRipple() == null) {
				setRipple(Ripple.SECONDARY);
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

}
