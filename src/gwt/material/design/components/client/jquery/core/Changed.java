/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gwt.material.design.components.client.jquery.core;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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


import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * obj The object to be observed. callback The function called each time changes
 * are made, with the following argument: changes An array of objects each
 * representing a change. The properties of these change objects are: name: The
 * name of the property which was changed. object: The changed object after the
 * change was made. type: A string indicating the type of change taking place.
 * One of "add", "update", or "delete". oldValue: Only for "update" and "delete"
 * types. The value before the change. acceptList The list of types of changes
 * to be observed on the given object for the given callback. If omitted, the
 * array ["add", "update", "delete", "reconfigure", "setPrototype",
 * "preventExtensions"] will be used.
 *
 * @author Cristian Rinaldi
 * @param <T>
 */
@JsType
public class Changed<T>{

    @JsProperty
    public native String getName();

    @JsProperty
    public native T getObject();

    @JsProperty
    public native String getType();
}
