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
public enum ButtonType implements CssType {

	FLAT(""),
	RAISED(CssName.MDC_BUTTON_RAISED),
	UNELEVATED(CssName.MDC_BUTTON_UNELEVATED),
	OUTLINE(CssName.MDC_BUTTON_OUTLINE),
	DENSE(CssName.MDC_BUTTON_DENSE);

    private final String cssClass;

    ButtonType(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static ButtonType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, ButtonType.class, RAISED);
    }
}
