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
package gwt.material.design.components.client.ui.html;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasTypography;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.HasFor;
import gwt.material.design.components.client.constants.Typography;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Text extends MaterialWidget implements HasText, HasTypography, HasFor {

	protected final TextMixin<Text> textMixin = new TextMixin<>(this);
	protected final TypeMixin<Text, Typography> typographiMixin = new TypeMixin<>(this);
	protected final AttributeMixin<Text> forMixin = new AttributeMixin<>(this, "for");

	protected Text(Element element) {
		super(element);
	}

	protected Text(Element element, String primaryClass) {
		super(element, primaryClass);
	}

	protected Text(Element element, String primaryClass, String... initialClass) {
		super(element, primaryClass, initialClass);
	}

	@Override
	public String getText() {
		return textMixin.getText();
	}

	@Override
	public void setText(String text) {
		textMixin.setText(text);
	}

	@Override
	public void setTypography(Typography typography) {
		typographiMixin.setType(typography);
	}

	@Override
	public Typography getTypography() {
		return typographiMixin.getType();
	}

	@Override
	public void setFor(String elementId) {
		forMixin.setAttribute(elementId);
	}
}
