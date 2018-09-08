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
package gwt.material.design.components.client.ui.chart.base;

import gwt.material.design.components.client.constants.Color;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialChartSerie {

	private final double value;

	private final String name;

	private final String className;
    
	private final String meta;

	private final Color color;
    
	public MaterialChartSerie(double value, String name) {
		this(value, name, null, null, null);
	}
	
	public MaterialChartSerie(double value, String name, Color color) {
		this(value, name, null, null, color);
	}
	
	public MaterialChartSerie(double value, String name, String className, String meta, Color color) {
		super();
		this.value = value;
		this.name = name;
		this.className = className;
		this.meta = meta;
		this.color = color;
	}

	public double getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public String getMeta() {
		return meta;
	}

	public Color getColor() {
		return color;
	}
	
}
