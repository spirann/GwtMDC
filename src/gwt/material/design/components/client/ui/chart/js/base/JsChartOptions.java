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
package gwt.material.design.components.client.ui.chart.js.base;

import com.google.gwt.core.client.JsArray;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("rawtypes")
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsChartOptions {

	@JsProperty
    public String width;
	
	@JsProperty
    public String height;
	
	@JsProperty
    public JsChartClassNames classNames;

    @JsProperty
    public Double high;
    
    @JsProperty
    public Double low;

    @JsProperty
    public boolean showLabel;
    
    @JsProperty
    public boolean reverseData;
    
    @JsProperty
    public JsArray plugins;

}
