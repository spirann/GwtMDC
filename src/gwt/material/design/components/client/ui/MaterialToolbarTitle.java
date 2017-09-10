package gwt.material.design.components.client.ui;

import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialToolbarTitle extends Span implements HasText {

	protected final TextMixin<MaterialToolbarTitle> textMixin = new TextMixin<>(this);

	public MaterialToolbarTitle() {
		super(CssName.MDC_TOOLBAR_TITLE);
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
