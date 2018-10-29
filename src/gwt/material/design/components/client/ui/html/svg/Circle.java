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

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.HtmlElements;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Circle extends MaterialWidget {

	protected final AttributeMixin<Circle, Double> cxMixin = new AttributeMixin<>(this, CssAttribute.CX, FromString.TO_DOUBLE);
	protected final AttributeMixin<Circle, Double> cyMixin = new AttributeMixin<>(this, CssAttribute.CY, FromString.TO_DOUBLE);
	protected final AttributeMixin<Circle, Double> rMixin = new AttributeMixin<>(this, CssAttribute.R, FromString.TO_DOUBLE);

	public Circle() {
		super(HtmlElements.SVG__CIRCLE.createElement());
	}

	public Circle(final String... initialClasses) {
		super(HtmlElements.SVG__CIRCLE.createElement(), initialClasses);
	}

	public void setCx(final double cx) {
		cxMixin.setValue(cx);
	}

	public void setCy(final double cy) {
		cyMixin.setValue(cy);
	}

	public void setR(final double r) {
		rMixin.setValue(r);
	}
}
