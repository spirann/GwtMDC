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

import gwt.material.design.components.client.base.interfaces.HasClassForIconPosition;
import gwt.material.design.components.client.utils.helper.EnumHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public enum TextFieldType implements CssType, HasClassForIconPosition {

	FILLED("", 
			CssName.MDC_TEXT_FIELD__WITH_LEADING_ICON, CssName.MDC_TEXT_FIELD__WITH_TRAILING_ICON),
	SHAPED_FILLED(CssName.MDC_TEXT_FIELD__SHAPED_FILLED, 
			CssName.MDC_TEXT_FIELD__WITH_LEADING_ICON, CssName.MDC_TEXT_FIELD__WITH_TRAILING_ICON),
	OUTLINED(CssName.MDC_TEXT_FIELD__OUTLINED, 
			CssName.MDC_TEXT_FIELD__OUTLINED__WITH_LEADING_ICON, CssName.MDC_TEXT_FIELD__OUTLINED__WITH_TRAILING_ICON),
	SHAPED(CssName.MDC_TEXT_FIELD__OUTLINED + " " + CssName.MDC_TEXT_FIELD__SHAPED, 
			CssName.MDC_TEXT_FIELD__OUTLINED__WITH_LEADING_ICON, CssName.MDC_TEXT_FIELD__OUTLINED__WITH_TRAILING_ICON),
	FULLWIDTH(CssName.MDC_TEXT_FIELD__FULLWIDTH, 
			CssName.MDC_TEXT_FIELD__WITH_LEADING_ICON, CssName.MDC_TEXT_FIELD__WITH_TRAILING_ICON);

    private final String cssClass;
    private final String cssClassForLeadingIcon;
    private final String cssClassForTrailingIcon;
    

    TextFieldType(final String cssClass, final String cssClassForLeadingIcon, final String cssClassForTrailingIcon) {
        this.cssClass = cssClass;
        this.cssClassForLeadingIcon = cssClassForLeadingIcon;
        this.cssClassForTrailingIcon = cssClassForTrailingIcon;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    @Override
    public String getCssClassForLeadingIcon() {
		return cssClassForLeadingIcon;
	}

    @Override
	public String getCssClassForTrailingIcon() {
		return cssClassForTrailingIcon;
	}

	public static TextFieldType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, TextFieldType.class, FILLED);
    }
}
