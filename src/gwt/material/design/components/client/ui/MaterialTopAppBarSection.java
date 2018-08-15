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

import gwt.material.design.components.client.base.HasAlign;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.constants.TopAppBarAlign;
import gwt.material.design.components.client.ui.html.Section;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTopAppBarSection extends Section implements HasAlign<TopAppBarAlign> {

	protected final TypeMixin<MaterialTopAppBarSection, TopAppBarAlign> alignMixin = new TypeMixin<>(this);

	public MaterialTopAppBarSection() {
		super(CssName.MDC_TOP_APP_BAR__SECTION);
		setRole(Role.TOOLBAR);
	}

	/**
	 * Toolbar sections are aligned to the toolbar’s center. You can change this
	 * behavior by applying mdc-toolbar__section--align-start or
	 * mdc-toolbar__section--align-end to align the sections to the start or the end
	 * of the toolbar (respectively).
	 * 
	 * @param align
	 */
	@Override
	public void setAlign(final TopAppBarAlign align) {
		alignMixin.setType(align);
	}

	@Override
	public TopAppBarAlign getAlign() {
		return alignMixin.getType();
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_TOP_APP_BAR__INK_COLOR, color.getCssName());
	}
}
