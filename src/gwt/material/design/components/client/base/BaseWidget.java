package gwt.material.design.components.client.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.ComplexPanel;

import gwt.material.design.components.client.constants.Color;

public class BaseWidget extends ComplexPanel {

	public void setStyleProperty(final String attribute, final String value) {
		setStyleProperty(getElement(), attribute, value);
	}

	protected native void setStyleProperty(final Element element, final String attribute, final String value)/*-{
		element.style.setProperty(attribute, value);
	}-*/;
	
	public void setMaxWidth(final String maxWidth){
		setStyleProperty("max-width", maxWidth);
	}
	
	public void setMinWidth(final String minWidth){
		setStyleProperty("min-width", minWidth);
	}
	
	public void setMaxHeight(final String maxHeight){
		setStyleProperty("max-height", maxHeight);
	}
	
	public void setMinHeight(final String minHeight){
		setStyleProperty("min-height", minHeight);
	}
	
	public void setPadding(final String padding){
		setStyleProperty("padding", padding);
	}
	
	public void setPaddingTop(final String paddingTop){
		setStyleProperty("padding-top", paddingTop);
	}
	
	public void setPaddingBottom(final String paddingBottom){
		setStyleProperty("padding-bottom", paddingBottom);
	}
	
	public void setPaddingLeft(final String paddingLeft){
		setStyleProperty("padding-left", paddingLeft);
	}
	
	public void setPaddingRight(final String paddingRight){
		setStyleProperty("padding-right", paddingRight);
	}
	
	public void setMargin(final String margin){
		setStyleProperty("margin", margin);
	}
	
	public void setMarginTop(final String marginTop){
		setStyleProperty("margin-top", marginTop);
	}
	
	public void setMarginBottom(final String marginBottom){
		setStyleProperty("margin-bottom", marginBottom);
	}
	
	public void setMarginLeft(final String marginLeft){
		setStyleProperty("margin-left", marginLeft);
	}
	
	public void setMarginRight(final String marginRight){
		setStyleProperty("margin-right", marginRight);
	}
	
	public void setBackground(final String background){
		setStyleProperty("background", background);
	}
	
	public void setBackgroundImg(final ImageResource imageResource){
		setBackground("url('" + imageResource.getSafeUri().asString() + "') center / cover");
	}
	
	public void setBackgroundColor(final Color color){
		setStyleProperty("background-color", color.getCssName());
	}
	
	public void setTextColor(final Color color){
		setStyleProperty("color", color.getCssName());
	}
}
