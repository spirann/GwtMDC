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

import gwt.material.design.components.client.base.interfaces.HasDense;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Ul;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialList extends Ul implements HasDense {

	protected final ToggleStyleMixin<MaterialList> avatarMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_LIST__AVATAR_LIST);
	
	protected final ToggleStyleMixin<MaterialList> denseMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_LIST__DENSE);
	
	protected final ToggleStyleMixin<MaterialList> twoLinesMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_LIST__TWO_LINE);
	
	public MaterialList(){
		super(CssName.MDC_LIST);
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.list.MDCList(element);
	}-*/;
	
	public void setHasAvatar(final boolean hasAvatar) {
		avatarMixin.toggle(hasAvatar);
	}
	
	public boolean hasAvatar() {
		return avatarMixin.isApplied();
	}

	@Override
	public void setDense(boolean dense) {
		denseMixin.toggle(dense);
	}

	@Override
	public boolean isDense() {
		return denseMixin.isApplied();
	}
	
	public void setTwoLines(boolean twoLines) {
		twoLinesMixin.toggle(twoLines);
	}

	public boolean isTwoLines() {
		return twoLinesMixin.isApplied();
	}
}
