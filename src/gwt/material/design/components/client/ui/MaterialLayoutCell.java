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

import gwt.material.design.components.client.base.interfaces.HasAlign;
import gwt.material.design.components.client.base.interfaces.HasCellColumns;
import gwt.material.design.components.client.base.mixin.ColumnsMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.LayoutCellAlign;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLayoutCell extends Div implements HasAlign<LayoutCellAlign>, HasCellColumns {

	protected final ColumnsMixin<MaterialLayoutCell> columnsMixin = new ColumnsMixin<MaterialLayoutCell>(this);
	protected final TypeMixin<MaterialLayoutCell, LayoutCellAlign> alignMixin = new TypeMixin<>(this);

	public MaterialLayoutCell() {
		super(CssName.MDC_LAYOUT_GRID__CELL);
	}

	/**
	 * Alignment Items are defined to stretch, by default, taking up the height of
	 * their corresponding row. You can switch to a different behavior by using one
	 * of the mdc-layout-grid__cell--align-{position} alignment classes, where
	 * {position} is one of {top}, {middle} or {bottom}.
	 * 
	 * @param align
	 */
	@Override
	public void setAlign(final LayoutCellAlign align) {
		alignMixin.setType(align);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public LayoutCellAlign getAlign() {
		return alignMixin.getType();
	}

	/**
	 * Material design�s responsive UI is based on a column-variate grid layout. It
	 * has 12 columns on desktop, 8 columns on tablet and 4 columns on phone.
	 */
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
