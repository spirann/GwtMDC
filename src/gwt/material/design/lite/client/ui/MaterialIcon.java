package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;

import gwt.material.design.lite.client.base.HasType;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.constants.CssName;
import gwt.material.design.lite.client.constants.IconType;

public class MaterialIcon extends MaterialWidget implements HasType<IconType> {

	private IconType type;

	public MaterialIcon() {
		super(Document.get().createElement("i"), CssName.MATERIAL_ICONS);
	}

	public MaterialIcon(final IconType type){
		this();
		setType(type);
	}

	@Override
	public void setType(IconType type) {
		this.type = type;
		getElement().setInnerHTML(type.getCssName());
	}

	@Override
	public IconType getType() {
		return type;
	}

}
