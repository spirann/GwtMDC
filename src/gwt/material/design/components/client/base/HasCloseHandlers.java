package gwt.material.design.components.client.base;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import gwt.material.design.components.client.handlers.CloseHandler;

public interface HasCloseHandlers extends HasHandlers {
	  /**
	   * Adds a {@link ValueChangeEvent} handler.
	   * 
	   * @param handler the handler
	   * @return the registration for the event
	   */
	  HandlerRegistration addCloseHandler(CloseHandler handler);

}
