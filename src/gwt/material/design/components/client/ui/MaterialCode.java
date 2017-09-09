package gwt.material.design.components.client.ui;

import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CodeType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.html.Code;

public class MaterialCode extends MaterialWidget implements HasType<CodeType>, HasText {

	protected Code code = new Code();

	protected final TypeMixin<Code, CodeType> typeMixin = new TypeMixin<>(code);	
	
	private boolean initialized = false;

	public MaterialCode() {
		super(HtmlElements.PRE.createElement(), CssName.PRISM_LANGUAGE_MARKUP, CssName.MDC_CODE);
		add(code);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			if (getType() == null) {
				setType(CodeType.XML);
			}

			initialized = true;
		}

		highlight();
	}

	public native void highlightAll()/*-{
		$wnd.Prism.highlightAll();
	}-*/;

	private void highlight() {
		if (initialized) {
			highlightAll();
		}
	}

	@Override
	public String getText() {
		return code.getText();
	}

	@Override
	public void setText(String text) {
		code.setText(text);
		highlight();
	}

	public void setTextResource(TextResource resource) {
		setText(resource.getText());
	}

	@Override
	public void setType(CodeType type) {
		typeMixin.setType(type);
		highlight();
	}

	@Override
	public CodeType getType() {
		return typeMixin.getType();
	}
}
