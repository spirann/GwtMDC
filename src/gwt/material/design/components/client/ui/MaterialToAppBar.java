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

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.TopAppBarType;
import gwt.material.design.components.client.ui.html.Header;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialToAppBar extends Header implements HasType<TopAppBarType> {

	@Override
	protected native void jsInit()/*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialToAppBar::getElement()();
		this.@gwt.material.design.components.client.base.MaterialWidget::jsElement = new $wnd.MDCTopAppBar(element);
	}-*/;

	protected final TypeMixin<MaterialToAppBar, TopAppBarType> typeMixin = new TypeMixin<>(this);

	private boolean initialize = false;

	public MaterialToAppBar() {
		super(CssName.MDC_TOP_APP_BAR);
	}

	@Override
	public void setType(TopAppBarType type) {
		typeMixin.setType(type);
	}

	@Override
	public TopAppBarType getType() {
		return typeMixin.getType();
	}
}
