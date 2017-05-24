package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialToolbarNavLink extends MaterialWidget implements HasText {

	private final TextMixin<MaterialToolbarNavLink> textMixin = new TextMixin<>(this);

	public MaterialToolbarNavLink() {
		super(Document.get().createElement(HtmlElements.SPAN), CssName.MDC_TOOLBAR_NAV_LINK);
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
