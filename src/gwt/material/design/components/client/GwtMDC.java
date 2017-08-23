package gwt.material.design.components.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.ScriptInjector;
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

	public void onModuleLoad() {
		
		// ///////////////////////////////////////////////////////////////
		// Load main resources
		// ///////////////////////////////////////////////////////////////
		loadResources();
		
		// ///////////////////////////////////////////////////////////////
		// Configure background
		// ///////////////////////////////////////////////////////////////
		StyleHelper.setStyleProperty(RootPanel.getBodyElement(), 
				"background-color", "var(--mdc-theme-background)");

		// ///////////////////////////////////////////////////////////////
		// Apply default theme
		// ///////////////////////////////////////////////////////////////
		ThemeManager.applyTheme(MaterialThemes.INSTANCE.indigo_pink());
	}
	
	public static void loadResources() {
		injectJs(MaterialResources.INSTANCE.jqueryJs());
		injectJs(MaterialResources.INSTANCE.googleAnalyticsJs());
		injectJs(MaterialResources.INSTANCE.materialComponentsWebJs());
		injectJs(MaterialResources.INSTANCE.prismJs());
		injectCss(MaterialResources.INSTANCE.materialComponentsWebCss());
		injectCss(MaterialResources.INSTANCE.addinsCss());
		injectCss(MaterialResources.INSTANCE.prismCss());
	}

	public static void injectCss(TextResource resource) {
		StyleInjector.injectStylesheet(resource.getText());
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
