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

import gwt.material.design.components.client.events.ProgressEvent.ProgressHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("rawtypes")
public class ProgressEvent<T> extends GwtEvent<ProgressHandler<T>> {
	
	public interface HasProgressHandlers<T> extends HasHandlers {
		  HandlerRegistration addProgressHandler(ProgressHandler<T> handler);
	}
	
	public static interface ProgressHandler<T> extends EventHandler {
		void onProgress(ProgressEvent<T> event);
	}

	/**
	 * Handler type.
	 */
	public static final Type<ProgressHandler> TYPE = new Type<ProgressHandler>();

	/**
	 * Fires a data change event on all registered handlers in the handler manager.
	 * If no such handlers exist, this method will do nothing.
	 * 
	 * @param <T>
	 *            the old data type
	 * @param source
	 *            the source of the handlers
	 * @param data
	 *            the data
	 */
	public static <T> void fire(final HasProgressHandlers<T> source, final T data, final long loaded, final long total) {
		source.fireEvent(new ProgressEvent<T>(data, loaded, total));
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<ProgressHandler> getType() {
		return TYPE;
	}

	/**
	 * Creates a data change event.
	 * 
	 * @param data
	 *            the data
	 */
	private final T data;
	private final long loaded;
	private final long total;
	
	protected ProgressEvent(final T data, final long loaded, final long total) {
		this.data = data;
		this.loaded = loaded;
		this.total = total;
	}

	public T getData() {
		return data;
	}

	public long getLoaded() {
		return loaded;
	}

	public long getTotal() {
		return total;
	}

	// The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	@SuppressWarnings("unchecked")
	@Override
	public final Type<ProgressHandler<T>> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(ProgressHandler<T> handler) {
		handler.onProgress(this);
	}

}
