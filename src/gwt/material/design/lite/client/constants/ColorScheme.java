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
 * Color constants.
 *
 * @author kevzlou7979
 */
public enum ColorScheme implements CssType {
    DEFAULT(""),
    BUTTON_COLORED("mdl-button--colored"),
    BUTTON_PRIMARY("mdl-button--primary"),
    BUTTON_ACCENT("mdl-button--accent");

    private final String cssClass;

    ColorScheme(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static ColorScheme fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, ColorScheme.class, ColorScheme.DEFAULT);
    }
}
