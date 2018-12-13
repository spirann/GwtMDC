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

import gwt.material.design.components.client.constants.CloseAction;
import gwt.material.design.components.client.events.ClosingEvent.ClosingHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class ClosingEvent extends GwtEvent<ClosingHandler> {
	
	public interface HasClosingHandlers extends HasHandlers {
		HandlerRegistration addClosingHandler(ClosingHandler handler);
	}
		
	public static interface ClosingHandler extends EventHandler {
		void onClosing(ClosingEvent event);
	}

	/**
	 * Handler type.
	 */
	public static final Type<ClosingHandler> TYPE = new Type<ClosingHandler>();

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
	public static void fire(final HasClosingHandlers source, final CloseAction action) {
		source.fireEvent(new ClosingEvent(action));
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<ClosingHandler> getType() {
		return TYPE;
	}

	private final CloseAction action;
	
	/**
	 * Creates a value change event.
	 * 
	 * @param value
	 *            the value
	 */
	protected ClosingEvent(final CloseAction action) {
		this.action = action;
	}

	public CloseAction getAction() {
		return action;
	}


	// The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	@Override
	public final Type<ClosingHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ClosingHandler handler) {
		handler.onClosing(this);
	}

}
