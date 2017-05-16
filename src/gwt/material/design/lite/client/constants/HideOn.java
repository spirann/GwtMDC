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
public enum HideOn implements CssType {
    DESKTOP("mdl-cell--hide-desktop"),
    TABLET("mdl-cell--hide-tablet"),
    PHONE("mdl-cell--hide-phone"),
    DESKTOP_AND_TABLET(DESKTOP.getClass() + " " + TABLET.getCssName()),
    DESKTOP_AND_PHONE(DESKTOP.getClass() + " " + PHONE.getCssName()),
    TABLET_AND_PHONE(TABLET.getClass() + " " + PHONE.getCssName()),
    DESKTOP_AND_TABLET_AND_PHONE(DESKTOP.getClass() + " " + TABLET.getClass() + " " + PHONE.getCssName());

    private final String cssClass;

    HideOn(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static HideOn fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, HideOn.class, DESKTOP);
    }
}
