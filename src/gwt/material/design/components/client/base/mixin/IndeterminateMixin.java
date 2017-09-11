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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasIndeterminate;

/**
 * @author Richeli Vargas
 */
public class IndeterminateMixin<T extends UIObject> extends AbstractMixin<T> implements HasIndeterminate {

	public IndeterminateMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setIndeterminate(boolean indeterminate) {
		setIndeterminate(uiObject.getElement(), indeterminate);
	}

	@Override
	public boolean isIndeterminate() {
		return isIndeterminate(uiObject.getElement());
	}

	protected native Boolean isIndeterminate(Element element)/*-{
		return element.indeterminate;
	}-*/;

	protected native void setIndeterminate(Element element, boolean value)/*-{
		element.indeterminate = value;
	}-*/;

}
