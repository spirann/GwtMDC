package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.CodeType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialCode extends MaterialWidget implements HasType<CodeType>, HasText {

	public static native void highlightAll()/*-{
		$wnd.Prism.highlightAll();
	}-*/;

	private CodeType type;

	private Element code = Document.get().createElement(HtmlElements.CODE);

	private boolean initialized = false;

	public MaterialCode() {
		super(Document.get().createElement(HtmlElements.PRE), CssName.PRISM_LANGUAGE_MARKUP);
		getElement().appendChild(code);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			if (type == null) {
				setType(CodeType.XML);
			}

			initialized = true;
		}

		highlight();
	}

	private void highlight() {
		if (initialized) {
			highlightAll();
		}
	}

	@Override
	public String getText() {
		return code.getInnerText();
	}

	@Override
	public void setText(String text) {
		code.setInnerText(text);
		highlight();
	}

	public void setTextResource(TextResource resource) {
		setText(resource.getText());
	}

	@Override
	public void setType(CodeType type) {

		if (this.type != null) {
			code.removeClassName(type.getCssName());
		}

		this.type = type;
		code.addClassName(type.getCssName());

		highlight();
	}

	@Override
	public CodeType getType() {
		return type;
	}
}
