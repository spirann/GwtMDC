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
package gwt.material.design.components.client.base.widget;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.mixin.HasValueMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialValuedField<T> extends Div implements HasValue<T> {

	protected final HasValueMixin<MaterialValuedField<T>, T> hasValueMixin = new HasValueMixin<>(this);

	protected MaterialValuedField() {
		this(CssName.MDC_FORM_FIELD);
	}

	public MaterialValuedField(final String... initialClasses) {
		super(initialClasses);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.formField.MDCFormField(element);
	}-*/;

	protected void fireChangeEvent() {
		hasValueMixin.fireChangeEvent();
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		return hasValueMixin.addValueChangeHandler(handler);
	}

	@Override
	public void setValue(T value) {
		hasValueMixin.setValue(value);
	}

	@Override
	public T getValue() {
		return hasValueMixin.getValue();
	}

	@Override
	public void setValue(T value, boolean fireEvents) {
		hasValueMixin.setValue(value, fireEvents);		
	}

}
