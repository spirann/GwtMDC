package gwt.material.design.lite.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.lite.client.base.HasColorScheme;
import gwt.material.design.lite.client.base.HasIcon;
import gwt.material.design.lite.client.base.HasType;
import gwt.material.design.lite.client.base.MaterialWidget;
import gwt.material.design.lite.client.base.mixin.ButtonTypeMixin;
import gwt.material.design.lite.client.base.mixin.ColorSchemeMixin;
import gwt.material.design.lite.client.constants.ButtonType;
import gwt.material.design.lite.client.constants.ColorScheme;
import gwt.material.design.lite.client.constants.CssName;
import gwt.material.design.lite.client.constants.IconType;

public class MaterialButton extends MaterialWidget implements HasType<ButtonType>, HasColorScheme, HasIcon, HasText {

	private ButtonTypeMixin<MaterialButton> buttonTypeMixin;

	private ColorSchemeMixin<MaterialButton> colorSchemeMixin;
	
	private MaterialIcon icon = new MaterialIcon();
	
	public MaterialButton() {
		this(ButtonType.FLAT);
	}
	
	public MaterialButton(final ButtonType type) {
		
		super(Document.get().createPushButtonElement(), CssName.MDL_BUTTON, CssName.MDL_JS_BUTTON, type.getCssName(),
				CssName.MDL_JS_RIPPLE_EFFECT);
		
		setType(type);
	}
	
	protected ButtonTypeMixin<MaterialButton> getButtonTypeMixin() {
		if (buttonTypeMixin == null) {
			buttonTypeMixin = new ButtonTypeMixin<>(this);
		}
		return buttonTypeMixin;
	}

	protected ColorSchemeMixin<MaterialButton> getColorSchemeMixin() {
		if (colorSchemeMixin == null) {
			colorSchemeMixin = new ColorSchemeMixin<>(this);
		}
		return colorSchemeMixin;
	}	
	
	@Override
	public void setType(ButtonType type) {
		getButtonTypeMixin().setType(type);
	}

	@Override
	public ButtonType getType() {
		return getButtonTypeMixin().getType();
	}

	@Override
	public void setColorScheme(final ColorScheme colorScheme) {
		getColorSchemeMixin().setColorScheme(colorScheme);
	}

	@Override
	public ColorScheme getColorScheme() {
		return getColorSchemeMixin().getColorScheme();
	}
	
	@Override
	public MaterialIcon getIcon() {
		return icon;
	}

	@Override
	public void setIconType(IconType iconType) {
		icon.setType(iconType);
		add(icon);		
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
