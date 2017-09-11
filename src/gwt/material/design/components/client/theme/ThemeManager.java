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
package gwt.material.design.components.client.theme;

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

import gwt.material.design.components.client.GwtMDC;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class ThemeManager {

	private static StyleElement currentTheme;

	public static void applyTheme(final MaterialTheme theme) {
		applyTheme(theme.getText());
	}
	
	public static void applyTheme(final TextResource resource) {
		applyTheme(resource.getText());
	}

	public static void applyTheme(final String resource) {
		
		
		// Remove old styles
		if (currentTheme != null) {
			currentTheme.removeFromParent();
		}

		GwtMDC.loadCssResources();
		
		// Apply the theme
		currentTheme = StyleInjector.injectStylesheetAtEnd(resource);
		
	}
}
