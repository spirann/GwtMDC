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
package gwt.material.design.lite.client.constants;

import gwt.material.design.lite.client.utils.helper.EnumHelper;

/**
 * Types of Button.<br>
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
public enum LabelType implements CssType {
	
    DISPLAY_4("mdl-typography--display-4"),
    DISPLAY_4_COLOR_CONRTAST("mdl-typography--display-4-color-contrast"),
    DISPLAY_3("mdl-typography--display-3"),
    DISPLAY_3_COLOR_CONRTAST("mdl-typography--display-3-color-contrast"),
    DISPLAY_2("mdl-typography--display-2"),
    DISPLAY_2_COLOR_CONRTAST("mdl-typography--display-2-color-contrast"),
    DISPLAY_1("mdl-typography--display-1"),
    DISPLAY_1_COLOR_CONRTAST("mdl-typography--display-1-color-contrast"),
    HEADLINE("mdl-typography--headline"),
    HEADLINE_COLOR_CONRTAST("mdl-typography--headline-color-contrast"),
    TITLE("mdl-typography--title"),
    TITLE_COLOR_CONRTAST("mdl-typography--title-color-contrast"),
    SUBHEAD("mdl-typography--subhead"),
    SUBHEAD_COLOR_CONRTAST("mdl-typography--subhead-color-contrast"),
    BODY_2("mdl-typography--body-2-force-preferred-font"),
    BODY_2_COLOR_CONRTAST("mdl-typography--body-2-force-preferred-font-color-contrast"),
    BODY_1("mdl-typography--body-1-force-preferred-font"),
    BODY_1_COLOR_CONRTAST("mdl-typography--body-1-force-preferred-font-color-contrast"),
    CAPTION("mdl-typography--caption-force-preferred-font"),
    CAPTION_COLOR_CONRTAST("mdl-typography--caption-force-preferred-font-color-contrast");

    private final String cssClass;

    LabelType(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static LabelType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, LabelType.class, BODY_1);
    }
}
