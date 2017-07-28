package gwt.material.design.components.client.theme;

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

import gwt.material.design.components.client.resources.MaterialResources;

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

		// Reload system style
		StyleInjector.inject(MaterialResources.INSTANCE.materialComponentsWebCss().getText());
		StyleInjector.inject(MaterialResources.INSTANCE.addinsCss().getText());
		StyleInjector.inject(MaterialResources.INSTANCE.prismCss().getText());

		// Apply the theme
		currentTheme = StyleInjector.injectStylesheet(resource);
	}
}
