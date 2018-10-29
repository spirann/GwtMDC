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

import gwt.material.design.components.client.base.interfaces.HasName;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.InputTypeMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.InputType;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Input extends MaterialWidget implements HasType<InputType>, HasName {

	protected final InputTypeMixin<Input> inputTypeMixin = new InputTypeMixin<Input>(this);
	protected final AttributeMixin<Input, String> nameMixin = new AttributeMixin<>(this, CssAttribute.NAME);

	public Input() {
		super(HtmlElements.INPUT.createElement());
	}

	public Input(final InputType type) {
		this();
		setType(type);
	}

	public Input(final InputType type, final String... initialClasses) {
		super(HtmlElements.INPUT.createElement(), initialClasses);
		setType(type);
	}

	public Input(final String... initialClasses) {
		super(HtmlElements.INPUT.createElement(), initialClasses);
	}

	@Override
	public void setType(InputType type) {
		inputTypeMixin.setType(type);
	}

	@Override
	public InputType getType() {
		return inputTypeMixin.getType();
	}

	@Override
	public void setName(String name) {
		nameMixin.setValue(name);
	}

	@Override
	public String getName() {
		return nameMixin.getValue();
	}

}
