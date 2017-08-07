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

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.constants.CodeType;

/**
 * @author kevzlou7979
 * @author Ben Dol
 */
public class CodeTypeMixin<T extends Widget & HasType<CodeType>>  extends StyleMixin<T>
		implements HasType<CodeType> {

	private CodeType CodeType;

	public CodeTypeMixin(final T widget) {
		super(widget);
	}

	@Override
	public void setType(CodeType CodeType) {
		this.CodeType = CodeType;
		setStyle(CodeType.getCssName());
	}

	@Override
	public CodeType getType() {
		return CodeType;
	}

}
