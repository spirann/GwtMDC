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

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasSelection;
import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;

/**
 * @author Richeli Vargas
 */
public class HasSelectionMixin<UIO extends MaterialUIObject & HasSelection<T> & HasSelectionHandlers<T>, T>
		extends AbstractMixin<UIO> implements HasSelection<T>, HasSelectionHandlers<T> {

	protected boolean valueChangeHandlerInitialized = false;
	protected T selected;

	public HasSelectionMixin(final UIO widget) {
		super(widget);
	}

	@Override
	public void setSelection(T selected) {
		setSelection(selected, true);
	}

	@Override
	public void setSelection(T selected, boolean fireEvents) {
		this.selected = selected;
		if (fireEvents)
			fireSelectEvent();
	}

	@Override
	public T getSelection() {
		return selected;
	}

	protected void fireSelectEvent() {
		SelectionEvent.fire(uiObject, selected);
	}

	@Override
	public HandlerRegistration addSelectionHandler(final SelectionHandler<T> handler) {
		if (!valueChangeHandlerInitialized) {
			valueChangeHandlerInitialized = true;
			uiObject.addDomHandler(event -> {
				event.preventDefault();
				event.stopPropagation();
				fireSelectEvent();
			}, ChangeEvent.getType());
		}
		return uiObject.addHandler(handler, SelectionEvent.getType());
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		uiObject.fireEvent(event);
	}

}
