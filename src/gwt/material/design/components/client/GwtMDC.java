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
package gwt.material.design.components.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.theme.MaterialThemes;
import gwt.material.design.components.client.theme.ThemeManager;
import gwt.material.design.components.client.utils.helper.StyleHelper;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author Richeli Vargas
 */
public class GwtMDC implements EntryPoint {

	private static StyleElement[] styles = {};

	public void onModuleLoad() {
		
		// ///////////////////////////////////////////////////////////////
		// Load main resources
		// ///////////////////////////////////////////////////////////////
		loadJsResources();
		loadCssResources();

		// ///////////////////////////////////////////////////////////////
		// Configure background
		// ///////////////////////////////////////////////////////////////
		StyleHelper.setStyleProperty(RootPanel.getBodyElement(), "background-color", "var(--mdc-theme-background)");

		// ///////////////////////////////////////////////////////////////
		// Apply default theme
		// ///////////////////////////////////////////////////////////////
		ThemeManager.applyTheme(MaterialThemes.INSTANCE.indigo_pink());
	}

	public static void loadJsResources() {
		injectJs(MaterialResources.INSTANCE.materialComponentsWebJs());
		injectJs(MaterialResources.INSTANCE.chartistJs());
		injectJs(MaterialResources.INSTANCE.chartistTooltipPluginJs());
		injectJs(MaterialResources.INSTANCE.prismJs());
		injectJs(MaterialResources.INSTANCE.chromaJs());
		injectJs(MaterialResources.INSTANCE.jqueryMaskJs());
	}

	public static void loadCssResources() {

		// Clear old styles
		for (StyleElement style : styles) {
			style.removeFromParent();
		}

		// Load new styles
		styles = new StyleElement[] { 
				injectCss(MaterialResources.INSTANCE.materialComponentsWebCss()), 
				injectCss(MaterialResources.INSTANCE.chartistCss()),
				injectCss(MaterialResources.INSTANCE.chartistTooltipPluginCss()),
				injectCss(MaterialResources.INSTANCE.prismCss()), 
				injectCss(MaterialResources.INSTANCE.addinsCss()), 
				injectCss(MaterialResources.INSTANCE.mixinCss()), 
				injectCss(MaterialResources.INSTANCE.chartMixinCss()) };

	}

	public static StyleElement injectCss(TextResource resource) {
		return StyleInjector.injectStylesheetAtEnd(resource.getText());
	}

	public static void injectJs(TextResource resource) {
		injectJs(resource, true, false);
	}

	public static void injectDebugJs(TextResource resource) {
		injectJs(resource, false, true);
	}

	public static void injectJs(TextResource resource, boolean removeTag, boolean sourceUrl) {

		final String text = resource.getText() + (sourceUrl ? "//# sourceURL=" + resource.getName() + ".js" : "");

		// Inject the script resource
		ScriptInjector.fromString(text).setWindow(ScriptInjector.TOP_WINDOW).setRemoveTag(removeTag).inject();

	}

}
