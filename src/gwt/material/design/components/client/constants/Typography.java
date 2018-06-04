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
public enum Typography implements CssType {

	HEADLINE_1(CssName.MDC_TYPOGRAPHY_HEADLINE_1),
	HEADLINE_2(CssName.MDC_TYPOGRAPHY_HEADLINE_2),
	HEADLINE_3(CssName.MDC_TYPOGRAPHY_HEADLINE_3),
	HEADLINE_4(CssName.MDC_TYPOGRAPHY_HEADLINE_4),
	HEADLINE_5(CssName.MDC_TYPOGRAPHY_HEADLINE_5),
	HEADLINE_6(CssName.MDC_TYPOGRAPHY_HEADLINE_6),
	BODY_1(CssName.MDC_TYPOGRAPHY_BODY_1),
	BODY_2(CssName.MDC_TYPOGRAPHY_BODY_2),
	CAPTION(CssName.MDC_TYPOGRAPHY_CAPTION),
	BUTTON(CssName.MDC_TYPOGRAPHY_BUTTON),
	OVERLINE(CssName.MDC_TYPOGRAPHY_OVERLINE);

    private final String cssClass;

    Typography(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Typography fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Typography.class, BODY_1);
    }
}
