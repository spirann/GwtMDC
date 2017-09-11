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

import com.google.gwt.event.shared.GwtEvent;

import gwt.material.design.components.client.base.HasInputHandlers;
import gwt.material.design.components.client.handlers.InputHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class InputEvent<T> extends GwtEvent<InputHandler<T>> {
	
	 /**
	   * Handler type.
	   */
	  private static Type<InputHandler<?>> TYPE;

	  /**
	   * Fires a value change event on all registered handlers in the handler
	   * manager. If no such handlers exist, this method will do nothing.
	   * 
	   * @param <T> the old value type
	   * @param source the source of the handlers
	   * @param value the value
	   */
	  public static <T> void fire(HasInputHandlers<T> source, T value) {
	    if (TYPE != null) {
	      InputEvent<T> event = new InputEvent<T>(value);
	      source.fireEvent(event);
	    }
	  }

	  /**
	   * Fires value change event if the old value is not equal to the new value.
	   * Use this call rather than making the decision to short circuit yourself for
	   * safe handling of null.
	   * 
	   * @param <T> the old value type
	   * @param source the source of the handlers
	   * @param oldValue the oldValue, may be null
	   * @param newValue the newValue, may be null
	   */
	  public static <T> void fireIfNotEqual(HasInputHandlers<T> source,
	      T oldValue, T newValue) {
	    if (shouldFire(source, oldValue, newValue)) {
	      InputEvent<T> event = new InputEvent<T>(newValue);
	      source.fireEvent(event);
	    }
	  }

	  /**
	   * Gets the type associated with this event.
	   * 
	   * @return returns the handler type
	   */
	  public static Type<InputHandler<?>> getType() {
	    if (TYPE == null) {
	      TYPE = new Type<InputHandler<?>>();
	    }
	    return TYPE;
	  }

	  /**
	   * Convenience method to allow subtypes to know when they should fire a value
	   * change event in a null-safe manner.
	   * 
	   * @param <T> value type
	   * @param source the source
	   * @param oldValue the old value
	   * @param newValue the new value
	   * @return whether the event should be fired
	   */
	  protected static <T> boolean shouldFire(HasInputHandlers<T> source,
	      T oldValue, T newValue) {
	    return TYPE != null && oldValue != newValue
	        && (oldValue == null || !oldValue.equals(newValue));
	  }

	  private final T value;

	  /**
	   * Creates a value change event.
	   * 
	   * @param value the value
	   */
	  protected InputEvent(T value) {
	    this.value = value;
	  }

	  // The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	  // field itself does not, so we have to do an unsafe cast here.
	  @SuppressWarnings({"unchecked", "rawtypes"})
	  @Override
	  public final Type<InputHandler<T>> getAssociatedType() {
	    return (Type) TYPE;
	  }

	  /**
	   * Gets the value.
	   * 
	   * @return the value
	   */
	  public T getValue() {
	    return value;
	  }
	 
	  @Override
	  public String toDebugString() {
	    return super.toDebugString() + getValue();
	  }

	  @Override
	  protected void dispatch(InputHandler<T> handler) {
	    handler.onInput(this);
	  }
	
}
