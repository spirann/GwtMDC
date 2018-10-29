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

import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.base.HasCssNameMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.constants.CssType;

/**
 * @author Richeli Vargas
 */
public class TypeMixin<UIO extends MaterialUIObject, T extends CssType> extends HasCssNameMixin<UIO, T> implements HasType<T> {
	
	public TypeMixin(final UIO uiObject) {
		super(uiObject);
	}
	
	public TypeMixin(final UIO uiObject, final T type) {
		super(uiObject);
		setType(type);
	}

	@Override
	public void setType(T type) {
		setHasCssName(type);
	}

	@Override
	public T getType() {
		return getHasCssName();
	}
}
