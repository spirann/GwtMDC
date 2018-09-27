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

import gwt.material.design.components.client.base.interfaces.HasInset;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Hr;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialListDivider extends Hr implements HasInset {

	protected final ApplyStyleMixin<MaterialListDivider> insetMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_LIST_DIVIDER__INSET);

	public MaterialListDivider() {
		super(CssName.MDC_LIST_DIVIDER);
		//setRole(Role.SEPARATOR);
	}

	public MaterialListDivider(final boolean inset) {
		this();
		setInset(inset);
	}

	@Override
	public void setInset(boolean inset) {
		insetMixin.setApply(inset);
	}

	@Override
	public boolean isInset() {
		return insetMixin.isApplied();
	}

}
