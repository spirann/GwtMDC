package gwt.material.design.components.client.ui;



import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.StyleEnabledMixin;
import gwt.material.design.components.client.base.mixin.StyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.CssType;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.utils.helper.EnumHelper;

public class MaterialToolbarSection extends MaterialWidget {

	public enum Align implements CssType {

		START(CssName.MDC_TOOLBAR_SECTION_ALIGN_START), 
		CENTER(CssName.MDC_TOOLBAR_SECTION_ALIGN_CENTER), 
		END(CssName.MDC_TOOLBAR_SECTION_ALIGN_END);

		private final String cssClass;

		Align(final String cssClass) {
			this.cssClass = cssClass;
		}

		@Override
		public String getCssName() {
			return cssClass;
		}

		public static Align fromStyleName(final String styleName) {
			return EnumHelper.fromStyleName(styleName, Align.class, CENTER);
		}
	};

	protected final StyleMixin<MaterialToolbarSection> alignMixin = new StyleMixin<>(this);
	protected final StyleEnabledMixin<MaterialToolbarSection> shrinkToFitMixin = new StyleEnabledMixin<>(
			this, CssName.MDC_TOOLBAR_SECTION_SHRINK_TO_FIT);

	public MaterialToolbarSection() {
		super(HtmlElements.SECTION.createElement(), CssName.MDC_TOOLBAR_SECTION);
	}

	/**
	 * Toolbar sections are aligned to the toolbar’s center. You can change this
	 * behavior by applying mdc-toolbar__section--align-start or
	 * mdc-toolbar__section--align-end to align the sections to the start or the
	 * end of the toolbar (respectively).
	 * 
	 * @param align
	 */
	public void setAlign(final Align align) {
		alignMixin.setStyle(align.getCssName());
	}

	public void getAlign() {
		Align.fromStyleName(alignMixin.getStyle());
	}

	/**
	 * Toolbar sections are laid out using flexbox. Each section will take up an
	 * equal amount of space within the toolbar by default. But you can
	 * accommodate very long section (very long title) by adding
	 * mdc-toolbar__section--shrink-to-fit to other sections.
	 * 
	 * @param shrinkToFit
	 */
	public void setShrinkToFit(final boolean shrinkToFit) {
		shrinkToFitMixin.setEnabled(shrinkToFit);
	}

	public boolean isShrinkToFit() {
		return shrinkToFitMixin.isEnabled();
	}
}
