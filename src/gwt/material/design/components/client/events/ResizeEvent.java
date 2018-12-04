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

import gwt.material.design.components.client.base.interfaces.HasResizeHandlers;
import gwt.material.design.components.client.events.ResizeEvent.ResizeHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class ResizeEvent extends GwtEvent<ResizeHandler> {

	public static interface ResizeHandler extends EventHandler {
		void onResize(ResizeEvent event);
	}
	
	/**
	 * Handler type.
	 */
	public static final Type<ResizeHandler> TYPE = new Type<ResizeHandler>();

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
	public static void fire(HasResizeHandlers source) {
		source.fireEvent(new ResizeEvent());
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<ResizeHandler> getType() {
		return TYPE;
	}

	/**
	 * Creates a value change event.
	 * 
	 * @param value
	 *            the value
	 */
	protected ResizeEvent() {
	}

	// The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	@Override
	public final Type<ResizeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ResizeHandler handler) {
		handler.onResize(this);
	}

}
