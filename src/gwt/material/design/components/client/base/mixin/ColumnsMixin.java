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
package gwt.material.design.components.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasCellColumns;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.ScreenSize;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class ColumnsMixin<T extends UIObject & HasCellColumns> extends AbstractMixin<T> implements HasCellColumns {

	private int cols = 0;
	private int colsDesktop = 0;
	private int colsTablet = 0;
	private int colsPhone = 0;

	private int order = 0;
	
	public ColumnsMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setCols(int columns) {
		setColumns(cols, columns, null);
		cols = columns;
	}

	@Override
	public void setDesktopCols(int columns) {
		setColumns(colsDesktop, columns, ScreenSize.DESKTOP);
		colsDesktop = columns;
	}

	@Override
	public void setTabletCols(int columns) {
		setColumns(colsTablet, columns, ScreenSize.TABLET);
		colsTablet = columns;
	}

	@Override
	public void setPhoneCols(int columns) {
		setColumns(colsPhone, columns, ScreenSize.PHONE);
		colsPhone = columns;
	}

	@Override
	public void setOrder(int order) {
		setOrder(this.order, order);
		this.order = order;
	}
	
	private void setColumns(final int oldCols, final int newCols, final ScreenSize screenSize) {

		if (oldCols >= 1 && oldCols <= 12) {
			uiObject.removeStyleName(CssName.MDC_LAYOUT_CELL_SPAN.replace("{columns}", String.valueOf(oldCols))
					.replace("-{screen_size}", screenSize == null ? "" : "-" + screenSize.getCssName()));
		}

		if (newCols >= 1 && newCols <= 12) {
			uiObject.addStyleName(CssName.MDC_LAYOUT_CELL_SPAN.replace("{columns}", String.valueOf(newCols))
					.replace("-{screen_size}", screenSize == null ? "" : "-" + screenSize.getCssName()));
		}
	}
	
	private void setOrder(final int oldOrder, final int newOrder) {

		if (oldOrder >= 1 && oldOrder <= 12) {
			uiObject.removeStyleName(CssName.MDC_LAYOUT_CELL_ORDER
					.replace("{number}", String.valueOf(oldOrder)));
		}

		if (newOrder >= 1 && newOrder <= 12) {
			uiObject.addStyleName(CssName.MDC_LAYOUT_CELL_ORDER
					.replace("{number}", String.valueOf(newOrder)));
		}
	}

}
