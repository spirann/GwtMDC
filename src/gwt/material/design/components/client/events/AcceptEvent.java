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

import gwt.material.design.components.client.events.AcceptEvent.AcceptHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class AcceptEvent extends GwtEvent<AcceptHandler> {

	public interface HasAcceptHandlers extends HasHandlers {
		  HandlerRegistration addAcceptHandler(AcceptHandler handler);
	}
	
	public static interface AcceptHandler extends EventHandler {
		void onAccept(AcceptEvent event);
	}
	
	/**
	 * Handler type.
	 */
	public static final Type<AcceptHandler> TYPE = new Type<AcceptHandler>();

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
	public static void fire(HasAcceptHandlers source) {
		source.fireEvent(new AcceptEvent());
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<AcceptHandler> getType() {
		return TYPE;
	}

	/**
	 * Creates a value change event.
	 * 
	 * @param value
	 *            the value
	 */
	protected AcceptEvent() {
	}

	// The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	@Override
	public final Type<AcceptHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AcceptHandler handler) {
		handler.onAccept(this);
	}

}
