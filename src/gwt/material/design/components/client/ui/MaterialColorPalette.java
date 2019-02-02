package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.widget.MaterialValuedField;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;

public class MaterialColorPalette extends MaterialValuedField<Color> {

	protected static final String[] COLORS_NAMES = { "", "50", "100", "200", "300", "400", "500", "600", "700", "800",
			"900", "A<br/>100", "A<br/>200", "A<br/>400", "A<br/>700" };
	protected static final Color[][] COLORS = {

			// RED
			{ Color.RED, Color.RED_50, Color.RED_100, Color.RED_200, Color.RED_300, Color.RED_400, Color.RED_500,
					Color.RED_600, Color.RED_700, Color.RED_800, Color.RED_900, Color.RED_A100, Color.RED_A200,
					Color.RED_A400, Color.RED_A700 },
			// PINK
			{ Color.PINK, Color.PINK_50, Color.PINK_100, Color.PINK_200, Color.PINK_300, Color.PINK_400, Color.PINK_500,
					Color.PINK_600, Color.PINK_700, Color.PINK_800, Color.PINK_900, Color.PINK_A100, Color.PINK_A200,
					Color.PINK_A400, Color.PINK_A700 },
			// PURPLE
			{ Color.PURPLE, Color.PURPLE_50, Color.PURPLE_100, Color.PURPLE_200, Color.PURPLE_300, Color.PURPLE_400,
					Color.PURPLE_500, Color.PURPLE_600, Color.PURPLE_700, Color.PURPLE_800, Color.PURPLE_900,
					Color.PURPLE_A100, Color.PURPLE_A200, Color.PURPLE_A400, Color.PURPLE_A700 },
			// DEEP PURPLE
			{ Color.DEEP_PURPLE, Color.DEEP_PURPLE_50, Color.DEEP_PURPLE_100, Color.DEEP_PURPLE_200,
					Color.DEEP_PURPLE_300, Color.DEEP_PURPLE_400, Color.DEEP_PURPLE_500, Color.DEEP_PURPLE_600,
					Color.DEEP_PURPLE_700, Color.DEEP_PURPLE_800, Color.DEEP_PURPLE_900, Color.DEEP_PURPLE_A100,
					Color.DEEP_PURPLE_A200, Color.DEEP_PURPLE_A400, Color.DEEP_PURPLE_A700 },
			// INDIGO
			{ Color.INDIGO, Color.INDIGO_50, Color.INDIGO_100, Color.INDIGO_200, Color.INDIGO_300, Color.INDIGO_400,
					Color.INDIGO_500, Color.INDIGO_600, Color.INDIGO_700, Color.INDIGO_800, Color.INDIGO_900,
					Color.INDIGO_A100, Color.INDIGO_A200, Color.INDIGO_A400, Color.INDIGO_A700 },
			// BLUE
			{ Color.BLUE, Color.BLUE_50, Color.BLUE_100, Color.BLUE_200, Color.BLUE_300, Color.BLUE_400, Color.BLUE_500,
					Color.BLUE_600, Color.BLUE_700, Color.BLUE_800, Color.BLUE_900, Color.BLUE_A100, Color.BLUE_A200,
					Color.BLUE_A400, Color.BLUE_A700 },
			// LIGHT BLUE
			{ Color.LIGHT_BLUE, Color.LIGHT_BLUE_50, Color.LIGHT_BLUE_100, Color.LIGHT_BLUE_200, Color.LIGHT_BLUE_300,
					Color.LIGHT_BLUE_400, Color.LIGHT_BLUE_500, Color.LIGHT_BLUE_600, Color.LIGHT_BLUE_700,
					Color.LIGHT_BLUE_800, Color.LIGHT_BLUE_900, Color.LIGHT_BLUE_A100, Color.LIGHT_BLUE_A200,
					Color.LIGHT_BLUE_A400, Color.LIGHT_BLUE_A700 },
			// CYAN
			{ Color.CYAN, Color.CYAN_50, Color.CYAN_100, Color.CYAN_200, Color.CYAN_300, Color.CYAN_400, Color.CYAN_500,
					Color.CYAN_600, Color.CYAN_700, Color.CYAN_800, Color.CYAN_900, Color.CYAN_A100, Color.CYAN_A200,
					Color.CYAN_A400, Color.CYAN_A700 },
			// TEAL
			{ Color.TEAL, Color.TEAL_50, Color.TEAL_100, Color.TEAL_200, Color.TEAL_300, Color.TEAL_400, Color.TEAL_500,
					Color.TEAL_600, Color.TEAL_700, Color.TEAL_800, Color.TEAL_900, Color.TEAL_A100, Color.TEAL_A200,
					Color.TEAL_A400, Color.TEAL_A700 },
			// GREEN
			{ Color.GREEN, Color.GREEN_50, Color.GREEN_100, Color.GREEN_200, Color.GREEN_300, Color.GREEN_400,
					Color.GREEN_500, Color.GREEN_600, Color.GREEN_700, Color.GREEN_800, Color.GREEN_900,
					Color.GREEN_A100, Color.GREEN_A200, Color.GREEN_A400, Color.GREEN_A700 },
			// LIGHT GREEN
			{ Color.LIGHT_GREEN, Color.LIGHT_GREEN_50, Color.LIGHT_GREEN_100, Color.LIGHT_GREEN_200,
					Color.LIGHT_GREEN_300, Color.LIGHT_GREEN_400, Color.LIGHT_GREEN_500, Color.LIGHT_GREEN_600,
					Color.LIGHT_GREEN_700, Color.LIGHT_GREEN_800, Color.LIGHT_GREEN_900, Color.LIGHT_GREEN_A100,
					Color.LIGHT_GREEN_A200, Color.LIGHT_GREEN_A400, Color.LIGHT_GREEN_A700 },
			// LIME
			{ Color.LIME, Color.LIME_50, Color.LIME_100, Color.LIME_200, Color.LIME_300, Color.LIME_400, Color.LIME_500,
					Color.LIME_600, Color.LIME_700, Color.LIME_800, Color.LIME_900, Color.LIME_A100, Color.LIME_A200,
					Color.LIME_A400, Color.LIME_A700 },
			// YELLOW
			{ Color.YELLOW, Color.YELLOW_50, Color.YELLOW_100, Color.YELLOW_200, Color.YELLOW_300, Color.YELLOW_400,
					Color.YELLOW_500, Color.YELLOW_600, Color.YELLOW_700, Color.YELLOW_800, Color.YELLOW_900,
					Color.YELLOW_A100, Color.YELLOW_A200, Color.YELLOW_A400, Color.YELLOW_A700 },
			// AMBER
			{ Color.AMBER, Color.AMBER_50, Color.AMBER_100, Color.AMBER_200, Color.AMBER_300, Color.AMBER_400,
					Color.AMBER_500, Color.AMBER_600, Color.AMBER_700, Color.AMBER_800, Color.AMBER_900,
					Color.AMBER_A100, Color.AMBER_A200, Color.AMBER_A400, Color.AMBER_A700 },
			// ORANGE
			{ Color.ORANGE, Color.ORANGE_50, Color.ORANGE_100, Color.ORANGE_200, Color.ORANGE_300, Color.ORANGE_400,
					Color.ORANGE_500, Color.ORANGE_600, Color.ORANGE_700, Color.ORANGE_800, Color.ORANGE_900,
					Color.ORANGE_A100, Color.ORANGE_A200, Color.ORANGE_A400, Color.ORANGE_A700 },
			// DEEP ORANGE
			{ Color.DEEP_ORANGE, Color.DEEP_ORANGE_50, Color.DEEP_ORANGE_100, Color.DEEP_ORANGE_200,
					Color.DEEP_ORANGE_300, Color.DEEP_ORANGE_400, Color.DEEP_ORANGE_500, Color.DEEP_ORANGE_600,
					Color.DEEP_ORANGE_700, Color.DEEP_ORANGE_800, Color.DEEP_ORANGE_900, Color.DEEP_ORANGE_A100,
					Color.DEEP_ORANGE_A200, Color.DEEP_ORANGE_A400, Color.DEEP_ORANGE_A700 },
			// BROWN
			{ Color.BROWN, Color.BROWN_50, Color.BROWN_100, Color.BROWN_200, Color.BROWN_300, Color.BROWN_400,
					Color.BROWN_500, Color.BROWN_600, Color.BROWN_700, Color.BROWN_800, Color.BROWN_900 },
			// GRAY
			{ Color.GRAY, Color.GRAY_50, Color.GRAY_100, Color.GRAY_200, Color.GRAY_300, Color.GRAY_400, Color.GRAY_500,
					Color.GRAY_600, Color.GRAY_700, Color.GRAY_800, Color.GRAY_900 },
			// BLUE GRAY
			{ Color.BLUE_GRAY, Color.BLUE_GRAY_50, Color.BLUE_GRAY_100, Color.BLUE_GRAY_200, Color.BLUE_GRAY_300,
					Color.BLUE_GRAY_400, Color.BLUE_GRAY_500, Color.BLUE_GRAY_600, Color.BLUE_GRAY_700,
					Color.BLUE_GRAY_800, Color.BLUE_GRAY_900 },
			// BLACK
			{ Color.BLACK },
			// WHITE
			{ Color.WHITE },
			// TRANSPARENT
			{ Color.TRANSPARENT }

	};

	public MaterialColorPalette() {
		super(CssName.MDC_COLOR_PALETTE);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		drawHeader(getElement(), COLORS_NAMES);
		drawItems(getElement(), COLORS);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	native void drawHeader(final Element parent, final String[] colors)/*-{
		var _this = this;
		var group_class = @gwt.material.design.components.client.constants.CssName::MDC_COLOR_PALETTE__BUTTON_GROUP;
		var item_class = @gwt.material.design.components.client.constants.CssName::MDC_COLOR_PALETTE__LABEL;

		var content = $doc.createElement('div');
		$wnd.jQuery(content).addClass(group_class);

		colors.forEach(function(color) {
			var item = $doc.createElement('button');
			$wnd.jQuery(item).addClass(item_class);
			$wnd.jQuery(item).html(color);
			$wnd.jQuery(content).append(item);
		});
		$wnd.jQuery(parent).append(content);

	}-*/;

	native void drawItems(final Element parent, final Color[][] colors)/*-{
		var _this = this;
		var group_class = @gwt.material.design.components.client.constants.CssName::MDC_COLOR_PALETTE__BUTTON_GROUP;
		var item_class = @gwt.material.design.components.client.constants.CssName::MDC_COLOR_PALETTE__BUTTON;
		
		var setValue = function(color) {
			_this.@gwt.material.design.components.client.ui.MaterialColorPalette::setValue(Lgwt/material/design/components/client/constants/Color;)(color);
		};

		var generateItem = function(color) {

			var color_name = color.@gwt.material.design.components.client.constants.Color::toString()();
			var color_css = color.@gwt.material.design.components.client.constants.Color::getCssName()();

			var item = $doc.createElement('button');
			$wnd.jQuery(item).addClass(item_class);
			$wnd.jQuery(item).addClass(color_name);
			$wnd.jQuery(item).css('background-color', color_css);
			$wnd.jQuery(item).attr('color', color_css);
			$wnd.jQuery(item).click(function() {
				setValue(color);
			});

			return item;

		};

		colors.forEach(function(colorsGroup) {
			var content = $doc.createElement('div');
			$wnd.jQuery(content).addClass(group_class);

			colorsGroup.forEach(function(color) {
				$wnd.jQuery(content).append(generateItem(color));
			});

			$wnd.jQuery(parent).append(content);
		});

	}-*/;

	@Override
	public void setValue(Color value) {
		unselect(getValue());
		select(value);
		super.setValue(value);
	}

	void unselect(Color color) {
		if (color != null)
			unselect(getElement(), color.toString(), CssName.MDC_COLOR_PALETTE__BUTTON_SELECTED);
	}

	void select(Color color) {
		if (color != null)
			select(getElement(), color.toString(), CssName.MDC_COLOR_PALETTE__BUTTON_SELECTED);
	}

	native void unselect(final Element element, final String colorClass, final String style)/*-{
		$wnd.jQuery(element).find('.' + colorClass).removeClass(style);
	}-*/;

	native void select(final Element element, final String colorClass, final String style)/*-{
		$wnd.jQuery(element).find('.' + colorClass).addClass(style);
	}-*/;

}
