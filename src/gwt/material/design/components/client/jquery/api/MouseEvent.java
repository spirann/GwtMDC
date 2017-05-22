package gwt.material.design.components.client.jquery.api;

import gwt.material.design.components.client.jquery.core.Element;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * MouseEvent for JQuery.
 * 
 * @author Ben Dol
 */
@JsType(isNative=true)
public class MouseEvent extends KeyEvent {

    @JsProperty
    public native int getPageX();

    @JsProperty
    public native int getPageY();

    @JsProperty
    public native int getClientX();

    @JsProperty
    public native int getClientY();

    @JsProperty
    public native int getOffsetX();

    @JsProperty
    public native int getOffsetY();

    @JsProperty
    public native int getScreenX();

    @JsProperty
    public native int getScreenY();

    @JsProperty
    public native Element getTarget();

    @JsProperty
    public native Element getToElement();

    @JsProperty
    public native int getButton();

    @JsProperty
    public native int getButtons();
}
