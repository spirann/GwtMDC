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
package gwt.material.design.components.client.constants;

import gwt.material.design.components.client.utils.helper.EnumHelper;

/**
 * @author Richeli Vargas
 */
public enum ChartAspectRatio implements CssType {
	DEFAULT(""),
	ASPECT_1x1("ct-square"), // 1
	ASPECT_15x16("ct-minor-second"), // 15:16
	ASPECT_8x9("ct-major-second"), // 8:9
	ASPECT_5x6("ct-minor-third"), // 5:6
	ASPECT_4x5("ct-major-third"), // 4:5
	ASPECT_3x4("ct-perfect-fourth"), // 3:4
	ASPECT_2x3("ct-perfect-fifth"), // 2:3
	ASPECT_5x8("ct-minor-sixth"), // 5:8
	ASPECT_1x1_618("ct-golden-section"), // 1:1.618
	ASPECT_3x5("ct-major-sixth"), // 3:5
	ASPECT_9x16("ct-minor-seventh"), // 9:16
	ASPECT_8x15("ct-major-seventh"), // 8:15
	ASPECT_1x2("ct-octave"), // 1:2
	ASPECT_2x5("ct-major-tenth"), // 2:5
	ASPECT_3x8("ct-major-eleventh"), // 3:8
	ASPECT_1x3("ct-major-twelfth"), // 1:3
	ASPECT_1x4("ct-double-octave"); // 1:4

	private final String cssClass;

	ChartAspectRatio(final String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String getCssName() {
		return cssClass;
	}

	public static ChartAspectRatio fromStyleName(final String styleName) {
		return EnumHelper.fromStyleName(styleName, ChartAspectRatio.class, DEFAULT);
	}
}
