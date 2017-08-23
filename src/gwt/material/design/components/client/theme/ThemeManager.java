package gwt.material.design.components.client.theme;

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

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

		// Apply the theme
		currentTheme = StyleInjector.injectStylesheetAtEnd(resource);
	}
}
