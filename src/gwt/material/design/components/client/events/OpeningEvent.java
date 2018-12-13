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

import gwt.material.design.components.client.events.OpeningEvent.OpeningHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class OpeningEvent extends GwtEvent<OpeningHandler> {

	public interface HasOpeningHandlers extends HasHandlers {
		HandlerRegistration addOpeningHandler(OpeningHandler handler);
	}
	
	public static interface OpeningHandler extends EventHandler {
		void onOpening(OpeningEvent event);
	}
	
	/**
	 * Handler type.
	 */
	public static final Type<OpeningHandler> TYPE = new Type<OpeningHandler>();

	/**
	 * Fires a value change event on all registered handlers in the handler
	 * manager. If no such handlers exist, this method will do nothing.
	 * 
	 * @param <T>
	 *            the old value type
	 * @param source
	 *            the source of the handlers
	 * @param value
	 *            the value
	 */
	public static void fire(HasOpeningHandlers source) {
		source.fireEvent(new OpeningEvent());
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<OpeningHandler> getType() {
		return TYPE;
	}

	/**
	 * Creates a value change event.
	 * 
	 * @param value
	 *            the value
	 */
	protected OpeningEvent() {
	}

	// The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	public final Type<OpeningHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(OpeningHandler handler) {
		handler.onOpening(this);
	}

}
