/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gwt.material.design.components.client.jquery.core;

import jsinterop.annotations.JsFunction;

/**
 *
 * 
 * 
 * @author Cristian Rinaldi
 * @param <E>
 */
@FunctionalInterface
@JsFunction
public interface EventListener<E extends JsObject> {
    public void onEvent(E event);
}
