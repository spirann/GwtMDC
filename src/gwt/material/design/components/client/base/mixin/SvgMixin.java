/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.components.client.base.mixin;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasSvg;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * @author kevzlou7979
 */
public class SvgMixin<T extends UIObject & HasSvg> extends AbstractMixin<T> implements HasSvg {

	private static final String SVG = "svg";
	
    private TextResource resource;

    private Color fillColor;
    
    public SvgMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setResource(TextResource resource) {
        this.resource = resource;
        uiObject.getElement().setInnerHTML(resource.getText());
        
        if(this.fillColor != null){
        	setAttribute(uiObject.getElement(), SVG, "fill", fillColor.getCssName());
        }
        
        setAttribute(uiObject.getElement(), SVG, "width", "100%");
        setAttribute(uiObject.getElement(), SVG, "height", "100%");
        setAttribute(uiObject.getElement(), SVG, "viewBox", "0 0 100% 100%");
    }
    
    @Override
    public TextResource getResource() {
        return resource;
    }

	@Override
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;		
		if(this.fillColor != null){
        	setAttribute(uiObject.getElement(), SVG, "fill", fillColor.getCssName());
        }
	}

	@Override
	public Color getFillColor() {
		return fillColor;
	}

	@Override
	public void setWidth(String width) {
		if(width != null){
			setAttribute(uiObject.getElement(), SVG, "width", width);
		}
	}

	@Override
	public void setHeight(String height) {
		if(height != null){
			setAttribute(uiObject.getElement(), SVG, "height", height);
		}
	}

    
    private void setAttribute(Element parent, String tag, String attribute, String value){

		final NodeList<Element> elements = parent.getElementsByTagName(tag);	
		for(int i  = 0; i < elements.getLength(); i++){
			StyleHelper.setStyleProperty(elements.getItem(i), attribute, value);
		}
	}
	
}