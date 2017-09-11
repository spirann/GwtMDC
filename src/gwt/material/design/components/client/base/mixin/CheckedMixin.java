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

import gwt.material.design.components.client.base.HasChecked;

/**
 * @author Richeli Vargas
 */
public class CheckedMixin<T extends UIObject> extends AbstractMixin<T> implements HasChecked {

	public CheckedMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setChecked(boolean checked) {
		setChecked(uiObject.getElement(), checked);
	}

	@Override
	public boolean isChecked() {
		return isChecked(uiObject.getElement());
	}

	protected native Boolean isChecked(Element element)/*-{
		return element.checked;
	}-*/;

	protected native void setChecked(Element element, boolean value)/*-{
		element.checked = value;
	}-*/;
	
}
