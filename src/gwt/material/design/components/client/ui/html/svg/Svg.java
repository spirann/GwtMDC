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
package gwt.material.design.components.client.ui.html.svg;

import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.HtmlElements;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Svg extends MaterialWidget {

	protected final AttributeMixin<Svg, String> widthMixin = new AttributeMixin<>(this, CssAttribute.WIDTH);
	protected final AttributeMixin<Svg, String> heightMixin = new AttributeMixin<>(this, CssAttribute.HEIGHT);

	public Svg() {
		super(HtmlElements.SVG.createElement());
	}

	public Svg(final String... initialClasses) {
		super(HtmlElements.SVG.createElement(), initialClasses);
	}

	@Override
	public void setWidth(String width) {
		widthMixin.setValue(width);
	}

	@Override
	public void setHeight(String height) {
		heightMixin.setValue(height);
	}
}
