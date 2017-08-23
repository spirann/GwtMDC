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
		// injectJs(MaterialResources.INSTANCE.googleAnalyticsJs());
		// injectJs(MaterialResources.INSTANCE.jqueryJs());
		injectJs(MaterialResources.INSTANCE.materialComponentsWebJs());
		injectJs(MaterialResources.INSTANCE.prismJs());
	}

	public static void loadCssResources() {

		// Clear old styles
		for (StyleElement style : styles) {
			style.removeFromParent();
		}

		// Load new styles
		styles = new StyleElement[] { 
				injectCss(MaterialResources.INSTANCE.materialComponentsWebCss()),
				injectCss(MaterialResources.INSTANCE.prismCss()), 
				injectCss(MaterialResources.INSTANCE.addinsCss()) 
		};

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

		String text = resource.getText() + (sourceUrl ? "//# sourceURL=" + resource.getName() + ".js" : "");

		// Inject the script resource
		ScriptInjector.fromString(text).setWindow(ScriptInjector.TOP_WINDOW).setRemoveTag(removeTag).inject();

	}
}
