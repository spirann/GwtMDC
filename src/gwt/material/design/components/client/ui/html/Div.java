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

import gwt.material.design.components.client.base.HasToolbarFixedAdjust;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.StyleEnabledMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Div extends MaterialWidget implements HasToolbarFixedAdjust {

	private final StyleEnabledMixin<Div> toolbarFixedAdjustMixin = new StyleEnabledMixin<>(this,
			CssName.MDC_TOOLBAR_FIXED_ADJUST);

	public Div() {
		super(HtmlElements.DIV.createElement());
	}

	public Div(final String primaryClass) {
		super(HtmlElements.DIV.createElement(), primaryClass);
	}

	public Div(final String primaryClass, final String... initialClasses) {
		super(HtmlElements.DIV.createElement(), primaryClass, initialClasses);
	}

	@Override
	public void setToolbarFixedAdjust(boolean toolbarFixedAdjust) {
		toolbarFixedAdjustMixin.setEnabled(toolbarFixedAdjust);
	}

	@Override
	public boolean isToolbarFixedAdjust() {
		return toolbarFixedAdjustMixin.isEnabled();
	}
}
