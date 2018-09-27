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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.TopAppBarType;
import gwt.material.design.components.client.ui.html.Header;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTopAppBar extends Header implements HasType<TopAppBarType> {

	protected final TypeMixin<MaterialTopAppBar, TopAppBarType> typeMixin = new TypeMixin<>(this);

	private boolean initialize = false;

	public MaterialTopAppBar() {
		super(CssName.MDC_TOP_APP_BAR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.topAppBar.MDCTopAppBar(element);
	}-*/;

	
	@Override
	public void setType(TopAppBarType type) {
		typeMixin.setType(type);
	}

	@Override
	public TopAppBarType getType() {
		return typeMixin.getType();
	}
	
	@Override
	public void setBackgroundColor(Color color) {
		setStyleProperty(CssMixin.MDC_TOP_APP_BAR__FILL_COLOR, color.getCssName());
	}

	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_TOP_APP_BAR__INK_COLOR, color.getCssName());
	}
}
