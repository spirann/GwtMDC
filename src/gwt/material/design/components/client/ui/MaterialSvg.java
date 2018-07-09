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
package gwt.material.design.components.client.ui;

import com.google.gwt.resources.client.TextResource;

import gwt.material.design.components.client.base.HasSvg;
import gwt.material.design.components.client.base.mixin.SvgMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSvg extends Div implements HasSvg {

	protected final SvgMixin<MaterialSvg> svgMixin = new SvgMixin<MaterialSvg>(this);

	public MaterialSvg() {
		super();
	}
	
	public MaterialSvg(final String... initialClasses) {
		super(initialClasses);
	}

	@Override
	public void setResource(TextResource resource) {
		svgMixin.setResource(resource);
	}

	@Override
	public void setSvg(String text) {
		svgMixin.setSvg(text);
	}

	@Override
	public void setFillColor(Color fillColor) {
		svgMixin.setFillColor(fillColor);
	}

	@Override
	public Color getFillColor() {
		return svgMixin.getFillColor();
	}

}
