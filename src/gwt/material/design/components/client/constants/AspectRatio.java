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
 * 
 * @author Richeli Vargas
 *
 */
public enum AspectRatio implements CssType {

	DEFAULT(""),
	ASPECT_1x1("mdc-aspect-1x1"),
	ASPECT_16x9("mdc-aspect-16x9"),
	ASPECT_2x3("mdc-aspect-2x3"),
	ASPECT_3x2("mdc-aspect-3x2"),
	ASPECT_4x3("mdc-aspect-4x3"),
	ASPECT_3x4("mdc-aspect-3x4");

    private final String cssClass;

    AspectRatio(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }    

    public static AspectRatio fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, AspectRatio.class, DEFAULT);
    }
}
