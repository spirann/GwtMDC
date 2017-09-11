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
package gwt.material.design.components.client.jquery.core;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * 
 * @author Richeli Vargas
 */
@JsType(isNative = true)
public class DOMTokenList {

    /**
     * This interface doesn't inherit any property.
     *
     * DOMTokenList.length Read only Is an integer representing the number of
     * objects stored in the object.
     * @return 
     */
    @JsProperty
    public native int getLenth();

    /**
     * Returns an item in the list by its index (or undefined if the number is
     * greater than or equal to the length of the list, prior to Gecko 7.0
     * returned null)
     *
     * @param index
     * @return
     */
    public native String item(int index);

    /**
     * Returns true if the underlying string contains token, otherwise false
     *
     * @param obj
     * @return
     */
    public native Boolean contains(String obj);

    /**
     * Adds token to the underlying string
     *
     * @param obj
     */
    public native void add(String obj);

    /**
     * Removes token from the underlying string
     *
     * @param obj
     */
    public native void remove(String obj);

    /**
     * Removes token from string and returns false. If token doesn't exist it's
     * added and the function returns true
     *
     * @param obj
     * @return
     */
    public native Boolean toggle(String obj);

}
