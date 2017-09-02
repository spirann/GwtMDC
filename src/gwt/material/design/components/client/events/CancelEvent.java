package gwt.material.design.components.client.events;

import com.google.gwt.event.shared.GwtEvent;

import gwt.material.design.components.client.base.HasCancelHandlers;
import gwt.material.design.components.client.handlers.CancelHandler;

public class CancelEvent extends GwtEvent<CancelHandler> {
	
	 /**
	   * Handler type.
	   */
	  private static final Type<CancelHandler> TYPE = new Type<CancelHandler>();

	  /**
	   * Fires a value change event on all registered handlers in the handler
	   * manager. If no such handlers exist, this method will do nothing.
	   * 
	   * @param  the old value type
	   * @param source the source of the handlers
	   * @param value the value
	   */
	  public static  void fire(HasCancelHandlers source) {
	      source.fireEvent(new CancelEvent());
	  }

	  /**
	   * Gets the type associated with this event.
	   * 
	   * @return returns the handler type
	   */
	  public static Type<CancelHandler> getType() {
	    return TYPE;
	  }

	  /**
	   * Creates a value change event.
	   * 
	   * @param value the value
	   */
	  protected CancelEvent() {
	  }

	  // The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	  // field itself does not, so we have to do an unsafe cast here.
	  @Override
	  public final Type<CancelHandler> getAssociatedType() {
	    return TYPE;
	  }

	  @Override
	  protected void dispatch(CancelHandler handler) {
	    handler.onCancel(this);
	  }
	
}
