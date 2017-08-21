package gwt.material.design.components.client.handlers;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.EventHandler;

import gwt.material.design.components.client.events.AcceptEvent;

public interface AcceptHandler<T> extends EventHandler {

	/**
	 * Called when {@link ValueChangeEvent} is fired.
	 * 
	 * @param event
	 *            the {@link ValueChangeEvent} that was fired
	 */
	void onAccept(AcceptEvent<T> event);
}
