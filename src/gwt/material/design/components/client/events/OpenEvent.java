package gwt.material.design.components.client.events;

import com.google.gwt.event.shared.GwtEvent;

import gwt.material.design.components.client.base.HasOpenHandlers;
import gwt.material.design.components.client.handlers.OpenHandler;

public class OpenEvent extends GwtEvent<OpenHandler> {

	/**
	 * Handler type.
	 */
	public static final Type<OpenHandler> TYPE = new Type<OpenHandler>();

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
	public static void fire(HasOpenHandlers source) {
		source.fireEvent(new OpenEvent());
	}

	/**
	 * Gets the type associated with this event.
	 * 
	 * @return returns the handler type
	 */
	public static Type<OpenHandler> getType() {
		return TYPE;
	}

	/**
	 * Creates a value change event.
	 * 
	 * @param value
	 *            the value
	 */
	protected OpenEvent() {
	}

	// The instance knows its BeforeSelectionHandler is of type I, but the TYPE
	// field itself does not, so we have to do an unsafe cast here.
	public final Type<OpenHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(OpenHandler handler) {
		handler.onOpen(this);
	}

}
