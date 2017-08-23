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

import gwt.material.design.components.client.base.HasPattern;

/**
 * @author Richeli Vargas
 */
public class PatternMixin<T extends UIObject> extends AbstractMixin<T> implements HasPattern {

	public PatternMixin(final T widget) {
		super(widget);
	}

	@Override
	public void setPattern(String pattern) {
		setPattern(uiObject.getElement(), pattern == null ? "" : pattern);
	}

	@Override
	public String getPattern() {
		return getPattern(uiObject.getElement());
	}

	protected native String getPattern(Element element)/*-{
		return element.pattern;
	}-*/;

	protected native void setPattern(Element element, String pattern)/*-{
		
		if(pattern){			
			element.pattern = pattern;
		} else {
			element.removeAttribute("pattern");
		}
		
	}-*/;
}