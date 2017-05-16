package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.lite.client.base.HasType;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.LabelTypeMixin;
import gwt.material.design.lite.client.constants.LabelType;

public class MaterialLabel extends MaterialWidget implements HasType<LabelType>, HasText{

	private LabelTypeMixin<MaterialLabel> labelTypeMixin;
	
	public MaterialLabel() {
		super(Document.get().createElement("label"));
		setType(LabelType.BODY_1_COLOR_CONRTAST);
	}	
	
	protected LabelTypeMixin<MaterialLabel> getLabelTypeMixin() {
		if (labelTypeMixin == null) {
			labelTypeMixin = new LabelTypeMixin<>(this);
		}
		return labelTypeMixin;
	}
	
	@Override
	public String getPrimaryClass() {
		return getLabelTypeMixin().getType().getCssName();
	}
	
	@Override
	public void setType(LabelType type) {
		getLabelTypeMixin().setType(type);
	}

	@Override
	public LabelType getType() {
		return getLabelTypeMixin().getType();
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
