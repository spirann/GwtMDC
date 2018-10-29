/*
 * #%L
 * Gwt Material Design Components
 * %%
 * Copyright (C) 2017 - 2017 Gwt Material Design Components
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

import com.google.gwt.resources.client.TextResource;

import gwt.material.design.components.client.base.interfaces.HasSvg;
import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * @author Richeli Vargas
 */
public class SvgMixin<UIO extends MaterialUIObject & HasSvg> extends AbstractMixin<UIO> implements HasSvg {

	private Color fillColor;

	public SvgMixin(final UIO widget) {
		super(widget);
	}

	@Override
	public void setResource(TextResource resource) {
		setSvg(resource.getText());
	}

	@Override
	public void setSvg(String svg) {

		uiObject.getElement().setInnerHTML(svg);

		if (this.fillColor != null)
			StyleHelper.setCssProperty(uiObject, HtmlElements.SVG.getTag(), "fill", fillColor.getCssName());

		StyleHelper.setCssProperty(uiObject, HtmlElements.SVG.getTag(), "width", "100%");
		StyleHelper.setCssProperty(uiObject, HtmlElements.SVG.getTag(), "height", "100%");
		StyleHelper.setCssProperty(uiObject, HtmlElements.SVG.getTag(), "viewBox", "0 0 100% 100%");
	}

	@Override
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
		if (this.fillColor != null)
			StyleHelper.setCssProperty(uiObject, HtmlElements.SVG.getTag(), "fill", fillColor.getCssName());

	}

	@Override
	public Color getFillColor() {
		return fillColor;
	}

	@Override
	public void setWidth(String width) {
		StyleHelper.setCssProperty(uiObject, HtmlElements.SVG.getTag(), "width", width);

	}

	@Override
	public void setHeight(String height) {
		StyleHelper.setCssProperty(uiObject, HtmlElements.SVG.getTag(), "height", height);
	}

}
