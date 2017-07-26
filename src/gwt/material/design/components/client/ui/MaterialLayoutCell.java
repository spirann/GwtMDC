package gwt.material.design.components.client.ui;



import gwt.material.design.components.client.base.HasColumns;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.ColumnsMixin;
import gwt.material.design.components.client.base.mixin.StyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.CssType;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.utils.helper.EnumHelper;

public class MaterialLayoutCell extends MaterialWidget implements HasColumns {

	public enum Align implements CssType {

		TOP(CssName.MDC_LAYOUT_CELL_ALIGN_TOP), 
		MIDDLE(CssName.MDC_LAYOUT_CELL_ALIGN_MIDDLE), 
		BOTTOM(CssName.MDC_LAYOUT_CELL_ALIGN_BOTTOM);

		private final String cssClass;

		Align(final String cssClass) {
			this.cssClass = cssClass;
		}

		@Override
		public String getCssName() {
			return cssClass;
		}

		public static Align fromStyleName(final String styleName) {
			return EnumHelper.fromStyleName(styleName, Align.class, TOP);
		}
	};

	private final ColumnsMixin<MaterialLayoutCell> columnsMixin = new ColumnsMixin<MaterialLayoutCell>(this);
	private final StyleMixin<MaterialLayoutCell> alignMixin = new StyleMixin<>(this);

	public MaterialLayoutCell() {
		super(HtmlElements.DIV.createElement(), CssName.MDC_LAYOUT_CELL);
	}

	/**
	 * Alignment Items are defined to stretch, by default, taking up the height
	 * of their corresponding row. You can switch to a different behavior by
	 * using one of the mdc-layout-grid__cell--align-{position} alignment
	 * classes, where {position} is one of {top}, {middle} or {bottom}.
	 * 
	 * @param align
	 */
	public void setAlign(final Align align) {
		alignMixin.setStyle(align.getCssName());
	}

	/**
	 * 
	 * @return
	 */
	public Align getAlign() {
		return Align.fromStyleName(alignMixin.getStyle());
	}

	@Override
	public void setCols(int columns) {
		columnsMixin.setCols(columns);
	}

	@Override
	public void setColsDesktop(int columns) {
		columnsMixin.setColsDesktop(columns);
	}

	@Override
	public void setColsTablet(int columns) {
		columnsMixin.setColsTablet(columns);
	}

	@Override
	public void setColsPhone(int columns) {
		columnsMixin.setColsPhone(columns);
	}

	@Override
	public void setOrder(int order) {
		columnsMixin.setOrder(order);
	}
}
