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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import gwt.material.design.components.client.events.ErrorEvent.ErrorHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("rawtypes")
public class ErrorEvent<T> extends GwtEvent<ErrorHandler<T>> {
	
	public interface HasErrorHandlers<T> extends HasHandlers {
		  HandlerRegistration addErrorHandler(ErrorHandler<T> handler);
	}
	
	public static interface ErrorHandler<T> extends EventHandler {
		void onError(ErrorEvent<T> event);
	}

	/**
	 * Handler type.
	 */
	public static final Type<ErrorHandler> TYPE = new Type<ErrorHandler>();

	/**
	 * Fires a value change event on all registered handlers in the handler manager.
	 * If no such handlers exist, this method will do nothing.
	 * 
	 * @param <T>
	 *            the old value type
	 * @param source
	 *            the source of the handlers
	 * @param value
	 *            the value
	 */
	public static <T> void fire(final HasErrorHandlers<T> source, final T value, final int code, final String description) {
		source.fireEvent(new ErrorEvent<T>(value, code, description));
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<ErrorHandler> getType() {
		return TYPE;
	}

	/**
	 * Creates a value change event.
	 * 
	 * @param value
	 *            the value
	 */
	private final T value;
	private final int code;
	private final String description;
	
	protected ErrorEvent(final T value, final int code, final String description) {
		this.value = value;
		this.code = code;
		this.description = description;
	}

	public T getValue() {
		return value;
	}
	
	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	// The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	@SuppressWarnings("unchecked")
	@Override
	public final Type<ErrorHandler<T>> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(ErrorHandler<T> handler) {
		handler.onError(this);
	}

}
