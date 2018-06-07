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
package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.base.HasCellColumns;
import gwt.material.design.components.client.base.mixin.ColumnsMixin;
import gwt.material.design.components.client.base.mixin.StyleMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.CssType;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.utils.helper.EnumHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLayoutCell extends Div implements HasCellColumns {

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

	protected final ColumnsMixin<MaterialLayoutCell> columnsMixin = new ColumnsMixin<MaterialLayoutCell>(this);
	protected final StyleMixin<MaterialLayoutCell> alignMixin = new StyleMixin<>(this);

	public MaterialLayoutCell() {
		super(CssName.MDC_LAYOUT_CELL);
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
	public void setDesktopCols(int columns) {
		columnsMixin.setDesktopCols(columns);
	}

	@Override
	public void setTabletCols(int columns) {
		columnsMixin.setTabletCols(columns);
	}

	@Override
	public void setPhoneCols(int columns) {
		columnsMixin.setPhoneCols(columns);
	}

	@Override
	public void setOrder(int order) {
		columnsMixin.setOrder(order);
	}
}
