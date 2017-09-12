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
 * Types of Button.<br>
 *
 * @author Richeli Vargas
 */
public enum HideOn implements CssType {
	NONE(""),
	PHONE(CssName.MDC_HIDE_ON_PHONE),
	TABLET(CssName.MDC_HIDE_ON_TABLET),
	DESKTOP(CssName.MDC_HIDE_ON_DESKTOP),
	PHONE_TABLET(CssName.MDC_HIDE_ON_PHONE + " " + CssName.MDC_HIDE_ON_TABLET),
	PHONE_DESKTOP(CssName.MDC_HIDE_ON_PHONE + " " + CssName.MDC_HIDE_ON_DESKTOP),
	TABLET_DESKTOP(CssName.MDC_HIDE_ON_TABLET + " " + CssName.MDC_HIDE_ON_DESKTOP),
	PHONE_TABLE_DESKTOP(CssName.MDC_HIDE_ON_PHONE + " " + CssName.MDC_HIDE_ON_TABLET + " " + CssName.MDC_HIDE_ON_DESKTOP);

	private final String cssClass;

	HideOn(final String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String getCssName() {
		return cssClass;
	}

	public static HideOn fromStyleName(final String styleName) {
		return EnumHelper.fromStyleName(styleName, HideOn.class, NONE);
	}
}
