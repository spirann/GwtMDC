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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasPlaceholder;

/**
 * @author Richeli Vargas
 */
public class PlaceholderMixin<T extends UIObject> extends AbstractMixin<T> implements HasPlaceholder {

	public PlaceholderMixin(final T widget) {
		super(widget);
	}

	@Override
	public void setPlaceholder(String placeholder) {
		setPlaceholder(uiObject.getElement(), placeholder == null ? "" : placeholder);
	}

	@Override
	public String getPlaceholder() {
		return getPlaceholder(uiObject.getElement());
	}

	protected native String getPlaceholder(Element element)/*-{
		return element.placeholder;
	}-*/;

	protected native void setPlaceholder(Element element, String placeholder)/*-{
		element.placeholder = placeholder;
	}-*/;
}
