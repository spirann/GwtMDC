package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.lite.client.base.HasType;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.CodeTypeMixin;
import gwt.material.design.lite.client.constants.CodeType;

public class MaterialCodePanel extends MaterialWidget implements HasType<CodeType>, HasText {

	class MaterialCode extends MaterialWidget implements HasType<CodeType>, HasText {

		private CodeTypeMixin<MaterialCode> codeTypeMixin;

		protected MaterialCode() {
			super(Document.get().createElement("code"));
		}

		protected CodeTypeMixin<MaterialCode> getCodeTypeMixin() {
			if (codeTypeMixin == null) {
				codeTypeMixin = new CodeTypeMixin<>(this);
			}
			return codeTypeMixin;
		}

		@Override
		public void setType(CodeType type) {
			getCodeTypeMixin().setType(type);
		}

		@Override
		public CodeType getType() {
			return getCodeTypeMixin().getType();
		}

		@Override
		public String getText() {
			return getElement().getInnerText();
		}

		@Override
		public void setText(String text) {
			getElement().setInnerText(text);
		}

	}

	private MaterialCode code = new MaterialCode();

	public MaterialCodePanel() {
		super(Document.get().createElement("pre"));
		add(code);
	}

	@Override
	public String getText() {
		return code.getText();
	}

	@Override
	public void setText(String text) {
		code.setText(text);
	}

	@Override
	public void setType(CodeType type) {
		code.setType(type);
	}

	@Override
	public CodeType getType() {
		return code.getType();
	}
}
