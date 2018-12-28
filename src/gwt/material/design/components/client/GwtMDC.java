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

import gwt.material.design.components.client.resources.MaterialResources;

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
	}

	public static void loadJsResources() {
		// Google Material Components
		injectJs(MaterialResources.INSTANCE.jquery());
		injectJs(MaterialResources.INSTANCE.resizeSensor());
		injectJs(MaterialResources.INSTANCE.materialComponentsWebJs());
		// Chartist
		injectJs(MaterialResources.INSTANCE.chartistJs());
		injectJs(MaterialResources.INSTANCE.chartistTooltipPluginJs());
		injectJs(MaterialResources.INSTANCE.chartistPointLabelPluginJs());
		injectJs(MaterialResources.INSTANCE.chartistZoomPluginJs());
		// Prism (Code formatation)
		injectJs(MaterialResources.INSTANCE.prismJs());
		// Chroma
		injectJs(MaterialResources.INSTANCE.chromaJs());
		// Masker
		injectJs(MaterialResources.INSTANCE.jqueryMaskJs());
		// File Upload
		injectJs(MaterialResources.INSTANCE.fileUploadWidgetJs());
		injectJs(MaterialResources.INSTANCE.fileUploadJs());
		injectJs(MaterialResources.INSTANCE.fileUploadIframeTransportJs());
		// JExcel
		injectJs(MaterialResources.INSTANCE.jExcelJs());
		injectJs(MaterialResources.INSTANCE.jExcelCsvJs());
		injectJs(MaterialResources.INSTANCE.jExcelFormulaJs());
		injectJs(MaterialResources.INSTANCE.jExcelDropdownJs());
		injectJs(MaterialResources.INSTANCE.jExcelCalendarJs());
	}

	public static void loadCssResources() {

		// Clear old styles
		for (StyleElement style : styles) {
			style.removeFromParent();
		}

		// Load new styles
		styles = new StyleElement[] {
				// Google Material Components
				injectCss(MaterialResources.INSTANCE.materialComponentsWebCss()),
				injectCss(MaterialResources.INSTANCE.materialComponentsRootCss()),
				injectCss(MaterialResources.INSTANCE.materialComponentsWebAddinsCss()),
				injectCss(MaterialResources.INSTANCE.mixinCss()),
				// Prism (Code formatation)
				injectCss(MaterialResources.INSTANCE.prismCss()),
				// Chartist
				injectCss(MaterialResources.INSTANCE.chartistCss()),
				injectCss(MaterialResources.INSTANCE.chartistTooltipPluginCss()),
				injectCss(MaterialResources.INSTANCE.chartMixinCss()),
				// JExcel
				injectCss(MaterialResources.INSTANCE.jExcelCss()),
				injectCss(MaterialResources.INSTANCE.jExcelBootstrapCss()),
				injectCss(MaterialResources.INSTANCE.jExcelCalendarCss()),
				injectCss(MaterialResources.INSTANCE.jExcelDropdownCss()),
				injectCss(MaterialResources.INSTANCE.jExcelGreenCss()),
				// Load default values
				injectCss(MaterialResources.INSTANCE.componentsAttributesCss()),
				injectCss(MaterialResources.INSTANCE.themeAttributesCss()) };

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
