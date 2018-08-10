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
package gwt.material.design.components.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import gwt.material.design.components.client.base.HasActiveHandlers;
import gwt.material.design.components.client.events.ActiveEvent.ActiveHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class ActiveEvent<V> extends GwtEvent<ActiveHandler<V>> {

	public static interface ActiveHandler<V> extends EventHandler {
		void onActive(ActiveEvent<V> event);
	}
	
	/**
	 * Handler type.
	 */
	public static Type<ActiveHandler<?>> TYPE;

	
	/**
	 * Fires a value change event on all registered handlers in the handler
	 * manager. If no such handlers exist, this method will do nothing.
	 * 
	 * @param the
	 *            old value type
	 * @param source
	 *            the source of the handlers
	 * @param value
	 *            the value
	 */
	public static <V> void fire(HasActiveHandlers<V> source, final V value) {
		source.fireEvent(new ActiveEvent<V>(value));
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<ActiveHandler<?>> getType() {
		
		if (TYPE == null) {
			TYPE = new Type<ActiveHandler<?>>();
		}
		
		return TYPE;
	}

	private final V value;
	
	/**
	 * Creates a value change event.
	 * 
	 * @param value
	 *            the value
	 */
	protected ActiveEvent(final V value) {
		this.value = value;
	}
	
	public V getValue() {
		return value;
	}

	// The instance knows its BeforeActiveHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final Type<ActiveHandler<V>> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(ActiveHandler<V> handler) {
		handler.onActive(this);
	}

}
