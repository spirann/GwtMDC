package gwt.material.design.components.client.events;

import com.google.gwt.event.shared.GwtEvent;

import gwt.material.design.components.client.base.HasAcceptHandlers;
import gwt.material.design.components.client.handlers.AcceptHandler;

public class AcceptEvent extends GwtEvent<AcceptHandler> {

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
