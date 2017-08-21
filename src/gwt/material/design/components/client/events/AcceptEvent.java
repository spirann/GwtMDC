package gwt.material.design.components.client.events;

import com.google.gwt.event.shared.GwtEvent;

import gwt.material.design.components.client.base.HasAcceptHandlers;
import gwt.material.design.components.client.handlers.AcceptHandler;

public class AcceptEvent<T> extends GwtEvent<AcceptHandler<T>> {
	
	 /**
	   * Handler type.
	   */
	  private static Type<AcceptHandler<?>> TYPE;

	  /**
	   * Fires a value change event on all registered handlers in the handler
	   * manager. If no such handlers exist, this method will do nothing.
	   * 
	   * @param <T> the old value type
	   * @param source the source of the handlers
	   * @param value the value
	   */
	  public static <T> void fire(HasAcceptHandlers<T> source, T value) {
	    if (TYPE != null) {
	      AcceptEvent<T> event = new AcceptEvent<T>(value);
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
	  public static <T> void fireIfNotEqual(HasAcceptHandlers<T> source,
	      T oldValue, T newValue) {
	    if (shouldFire(source, oldValue, newValue)) {
	      AcceptEvent<T> event = new AcceptEvent<T>(newValue);
	      source.fireEvent(event);
	    }
	  }

	  /**
	   * Gets the type associated with this event.
	   * 
	   * @return returns the handler type
	   */
	  public static Type<AcceptHandler<?>> getType() {
	    if (TYPE == null) {
	      TYPE = new Type<AcceptHandler<?>>();
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
	  protected static <T> boolean shouldFire(HasAcceptHandlers<T> source,
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
	  protected AcceptEvent(T value) {
	    this.value = value;
	  }

	  // The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	  // field itself does not, so we have to do an unsafe cast here.
	  @SuppressWarnings({"unchecked", "rawtypes"})
	  @Override
	  public final Type<AcceptHandler<T>> getAssociatedType() {
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
	  protected void dispatch(AcceptHandler<T> handler) {
	    handler.onAccept(this);
	  }
	
}
