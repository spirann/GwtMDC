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
package gwt.material.design.components.client.base.mixin.base;

import com.google.gwt.dom.client.Style;

import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * @author Richeli Vargas
 */
public abstract class HasCssNameMixin<UIO extends MaterialUIObject, S extends Style.HasCssName> extends AbstractMixin<UIO> {

	private S hasCssName;

	protected HasCssNameMixin(final UIO widget) {
		super(widget);
	}

	protected void setHasCssName(S hasCssName) {

		if (this.hasCssName != null)
			StyleHelper.removeStyle(uiObject, this.hasCssName);

		this.hasCssName = hasCssName;
		StyleHelper.addStyle(uiObject, hasCssName);

	}

	protected S getHasCssName() {
		return hasCssName;
	}
}
