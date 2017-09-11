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
public enum Elevation implements CssType {

	Z_0(CssName.MDC_ELEVATION_Z0),
	Z_1(CssName.MDC_ELEVATION_Z1),
	Z_2(CssName.MDC_ELEVATION_Z2),
	Z_3(CssName.MDC_ELEVATION_Z3),
	Z_4(CssName.MDC_ELEVATION_Z4),
	Z_5(CssName.MDC_ELEVATION_Z5),
	Z_6(CssName.MDC_ELEVATION_Z6),
	Z_7(CssName.MDC_ELEVATION_Z7),
	Z_8(CssName.MDC_ELEVATION_Z8),
	Z_9(CssName.MDC_ELEVATION_Z9),
	Z_10(CssName.MDC_ELEVATION_Z10),
	Z_11(CssName.MDC_ELEVATION_Z11),
	Z_12(CssName.MDC_ELEVATION_Z12),
	Z_13(CssName.MDC_ELEVATION_Z13),
	Z_14(CssName.MDC_ELEVATION_Z14),
	Z_15(CssName.MDC_ELEVATION_Z15),
	Z_16(CssName.MDC_ELEVATION_Z16),
	Z_17(CssName.MDC_ELEVATION_Z17),
	Z_18(CssName.MDC_ELEVATION_Z18),
	Z_19(CssName.MDC_ELEVATION_Z19),
	Z_20(CssName.MDC_ELEVATION_Z20),
	Z_21(CssName.MDC_ELEVATION_Z21),
	Z_22(CssName.MDC_ELEVATION_Z22),
	Z_23(CssName.MDC_ELEVATION_Z23),
	Z_24(CssName.MDC_ELEVATION_Z24);

    private final String cssClass;

    Elevation(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Elevation fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Elevation.class, Z_0);
    }
}
