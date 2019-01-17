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

import gwt.material.design.components.client.events.TypingEvent.TypingHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class TypingEvent extends GwtEvent<TypingHandler> {

	public interface HasTypingHandlers extends HasHandlers {
		HandlerRegistration addTypingHandler(TypingHandler handler);
		public void setTypeingDelay(int typingDelay);
	}
	
	public static interface TypingHandler extends EventHandler {
		void onTyping(TypingEvent event);
	}
	
	/**
	 * Handler type.
	 */
	public static Type<TypingHandler> TYPE;

	
	/**
	 * Fires a value change event on all registered handlers in the handler
	 * manager. If no such handlers exist, this method will do nothing.
	 * 
	 * @param the
	 *            old value type
	 * @param source
	 *            the source of the handlers
	 * @param text
	 *            the value
	 */
	public static  void fire(HasTypingHandlers source, final String text) {
		source.fireEvent(new TypingEvent(text));
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<TypingHandler> getType() {
		
		if (TYPE == null) {
			TYPE = new Type<TypingHandler>();
		}
		
		return TYPE;
	}

	private final String text;
	
	/**
	 * Creates a value change event.
	 * 
	 * @param value
	 *            the value
	 */
	protected TypingEvent(final String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	// The instance knows its BeforeTypingHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final Type<TypingHandler> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(TypingHandler handler) {
		handler.onTyping(this);
	}

}
