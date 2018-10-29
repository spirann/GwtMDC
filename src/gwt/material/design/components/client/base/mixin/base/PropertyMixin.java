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
 * WITHOUUIO WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.components.client.base.mixin.base;

import com.google.gwt.dom.client.Style.HasCssName;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.widget.MaterialUIObject;

/**
 * @author Richeli Vargas
 */
public class PropertyMixin<UIO extends MaterialUIObject, V> extends AbstractMixin<UIO> {

	protected final String property;
	protected final FromString<V> fromString;

	public PropertyMixin(final UIO uiObject, final String property, final FromString<V> fromString) {
		super(uiObject);
		this.property = property;
		this.fromString = fromString;
	}

	public PropertyMixin(final UIO uiObject, final String property, V value, final FromString<V> converter) {
		this(uiObject, property, converter);
		setValue(value);
	}

	public String getProperty() {
		return property;
	}

	public V getValue() {
		return fromString == null ? null : fromString.from(uiObject.getProperty(property));
	}

	public void setValue(V value) {
		setValue(property, value, uiObject);
	}

	public void setValue(String property, V value, MaterialUIObject uiObject) {
		uiObject.setProperty(property,
				value == null || (value instanceof Boolean && !((Boolean) value)) ? null : (value instanceof HasCssName ? ((HasCssName) value).getCssName() : value.toString()));
	}

}
