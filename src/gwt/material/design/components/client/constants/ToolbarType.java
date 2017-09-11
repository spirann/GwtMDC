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
public enum ToolbarType implements CssType, Type {

	DEFAULT(""), 
	FIXED(CssName.MDC_TOOLBAR_FIXED), 
	WATERFALL(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_WATERFALL), 
	LASTROW_ONLY(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_LASTROW_ONLY), 
	FLEXIBLE(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_FLEXIBLE), 
	FLEXIBLE_BEHAVIOR(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_FLEXIBLE + " " + CssName.MDC_TOOLBAR_FLEXIBLE_BEHAVIOR),
	WATERFALL_FLEXIBLE(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_WATERFALL + " " + CssName.MDC_TOOLBAR_FLEXIBLE), 
	WATERFALL_FLEXIBLE_BEHAVIOR(CssName.MDC_TOOLBAR_FIXED + " " + CssName.MDC_TOOLBAR_WATERFALL + " " + CssName.MDC_TOOLBAR_FLEXIBLE + " " + CssName.MDC_TOOLBAR_FLEXIBLE_BEHAVIOR);
	
	private final String cssClass;

	ToolbarType(final String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String getCssName() {
		return cssClass;
	}

	public static Type fromStyleName(final String styleName) {
		return EnumHelper.fromStyleName(styleName, ToolbarType.class, DEFAULT);
	}

}
