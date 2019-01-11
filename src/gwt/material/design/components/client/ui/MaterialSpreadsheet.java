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

import java.util.Arrays;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.constants.ColumnAlign;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.OrderBy;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.jExcel.js.JsContextMenuTexts;
import gwt.material.design.components.client.ui.misc.jExcel.js.JsOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSpreadsheet extends Div {

	public static interface Formatter {
		public Object format(final String id, final Element cell, final int row, final int column,
				final Object value);
	}

	protected final Div spreadsheet = new Div(CssName.MDC_SPREADSHEET__SPREADSHEET, CssName.MDC_TYPOGRAPHY);
	protected final JsOptions options = new JsOptions();
	protected Formatter formatter;

	public MaterialSpreadsheet() {
		super(CssName.MDC_SPREADSHEET);
		loadDefaultOptions();
	}

	@Override
	protected void onInitialize() {
		add(spreadsheet);
		super.onInitialize();
	}

	protected void loadDefaultOptions() {
		options.tableId = spreadsheet.getId();
		options.defaultColWidth = 100;
		options.minSpareRows = 0;
		options.minSpareCols = 0;
		options.columnSorting = true;
		options.columnResize = true;
		options.rowDrag = true;
		options.editable = true;
		options.allowInsertRow = true;
		options.allowManualInsertRow = true;
		options.allowInsertColumn = true;
		options.allowManualInsertColumn = true;
		options.allowDeleteRow = true;
		options.allowDeleteColumn = true;
		options.wordWrap = false;
		options.csvFileName = "csv_file";
		options.csvHeaders = false;
		options.separator = ";";
		options.delimiter = ";";
		options.selectionCopy = true;
		options.tableOverflow = false;
		options.tableHeight = "300px";
		options.contextMenuClass = CssName.MDC_MENU;
		options.contextMenuTextClass = CssName.MDC_MENU__TEXT;
		options.contextMenuCommandClass = CssName.MDC_MENU__COMMAND;

		final JsContextMenuTexts contextMenuTexts = new JsContextMenuTexts();
		contextMenuTexts.insertRow = IMessages.INSTANCE.mdc_jexcel_insert_row();
		contextMenuTexts.insertColumn = IMessages.INSTANCE.mdc_jexcel_insert_column();
		contextMenuTexts.deleteRow = IMessages.INSTANCE.mdc_jexcel_delete_row();
		contextMenuTexts.deleteColumn = IMessages.INSTANCE.mdc_jexcel_delete_column();
		contextMenuTexts.copy = IMessages.INSTANCE.mdc_jexcel_copy();
		contextMenuTexts.about = IMessages.INSTANCE.mdc_jexcel_about();
		contextMenuTexts.saveAs = IMessages.INSTANCE.mdc_jexcel_save_as();
		contextMenuTexts.orderAscending = IMessages.INSTANCE.mdc_jexcel_order_ascending();
		contextMenuTexts.orderDescending = IMessages.INSTANCE.mdc_jexcel_order_descending();

		options.contextMenuTexts = contextMenuTexts;
	}

	@Override
	protected native JavaScriptObject jsInit(final Element ignore)/*-{
		var _this = this;
		var options = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::options;
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		var jsElement = $wnd.jQuery(element).jexcel(options);
		var updateSettings = {
			table : function(instance, cell, col, row, val, id) {
				var formatter = _this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::formatter;
				if (formatter) {
					var jCell = $wnd.jQuery(cell);
					var element = $doc.getElementById(jCell.attr('id'));
					var value = formatter.@gwt.material.design.components.client.ui.MaterialSpreadsheet.Formatter::format(Ljava/lang/String;Lcom/google/gwt/dom/client/Element;IILjava/lang/Object;)(id,element,row,col,val);
					if (value)
						jCell.html('' + value);
					else
						jCell.html('' + val);
				}
			}
		};
		$wnd.jQuery(element).jexcel('updateSettings', updateSettings);
		return jsElement;
	}-*/;

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	public void setUrl(final String url) {
		options.csv = url;
		if (initialized)
			jsInit();
	}

	public void setData(final Object[][] data) {
		options.csv = null;
		options.data = data;
		if (initialized)
			jsInit();
	}

	/**
	 * 
	 * @param cellId Str compatible with excel, or as object. Ex.: A1
	 * @param data   New value for the cell
	 */
	public native void setData(final String cellId, final Object data) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('setValue', cellId, data);
	}-*/;

	public native Object[][] getData()/*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('getData', false);
	}-*/;

	public native Object[][] getSelectedData() /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('getData', true);
	}-*/;

	/**
	 * 
	 * @param cell Compatible with excel, or as object. Ex.: A1
	 */
	public native Object getData(final String cellId) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('getValue', cellId);
	}-*/;

	/**
	 * 
	 * @param row Row number starting from zero.
	 * @return
	 */
	public native Object[] getRowData(final int row) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('getRowData', row);
	}-*/;

	/**
	 * 
	 * @param column Column number starting from zero.
	 * @return
	 */
	public native Object[] getColumnData(final int column) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('getColumnData', column);
	}-*/;

	/**
	 * 
	 * @param headers
	 */
	public void setHeaders(final String[] headers) {
		options.colHeaders = headers;
		if (initialized)
			for (int col = 0; headers != null && col < headers.length; col++)
				setHeader(headers[col], col);
	}

	/**
	 * 
	 * @param header New header title
	 * @param column Column number starting on zero
	 */
	public native void setHeader(final String header, final int column) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		var options = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::options;

		if (options && options.colHeaders && options.colHeaders.length > column)
			options.colHeaders[column] = header;

		$wnd.jQuery(element).jexcel('setHeader', column, header);

	}-*/;

	public native void insertColumn(final int countColumns, final String[] headers) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('insertColumn', countColumns,
				headers);
	}-*/;

	public native void deleteColumn(final int column) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('deleteColumn', column);
	}-*/;

	public native void insertRow(final int countRows) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('insertRow', countRows);
	}-*/;

	public native void deleteRow(final int row) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel('deleteRow', row);
	}-*/;

	public void setColumnsWidth(final int[] widths) {
		options.colWidths = widths;

		if (initialized)
			for (int col = 0; widths != null && col < widths.length; col++)
				setColumnWidth(widths[col], col);
	}

	public native void setColumnWidth(final int column, final int width) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		var options = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::options;

		if (options && options.colHeaders && options.colHeaders.length > column)
			options.colWidths[column] = width;

		return $wnd.jQuery(element).jexcel('setWidth', column, width);

	}-*/;

	/**
	 * 
	 * @param column  Column number starting on zero
	 * @param orderBy
	 */
	public void orderBy(final int column, final OrderBy orderBy) {
		switch (orderBy) {
		case ASC:
			orderBy(column, 2);
			break;
		case DESC:
			orderBy(column, 1);
			break;
		case NONE:
		default:
			orderBy(column, 0);
			break;
		}
	}

	/**
	 * 
	 * @param column  Column number starting on zero
	 * @param orderBy Zero will toggle current option, one for desc, two for asc
	 */
	protected native void orderBy(final int column, final int orderBy) /*-{
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		$wnd.jQuery(element).jexcel('orderBy', column, orderBy);
	}-*/;

	/**
	 * 
	 * @param cellId Compatible with excel, or as object. Ex.: A1
	 * @param state
	 */
	public native void setCellState(final String cellId, final State state) /*-{
		var _state = state ? state.@gwt.material.design.components.client.constants.State::getCssName()()
				: '';
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		var cell = $wnd.jQuery(element).jexcel('getCell', cellId);
		if (_state && _state.length > 0)
			$wnd.jQuery(cell).attr('state', _state);
		else
			$wnd.jQuery(cell).removeAttr('state');
	}-*/;

	public void setSort(final boolean sort) {
		options.columnSorting = sort;
		if (initialized)
			jsInit();
	}

	public void setResize(final boolean resize) {
		options.columnResize = resize;
		if (initialized)
			jsInit();
	}

	public void setRowDrag(final boolean rowDrag) {
		options.rowDrag = rowDrag;
		if (initialized)
			jsInit();
	}

	public void setEditable(final boolean editable) {
		options.editable = editable;
		if (initialized)
			jsInit();
	}

	public void setAllowInsertRow(final boolean allowInsertRow) {
		options.allowInsertRow = allowInsertRow;
		if (initialized)
			jsInit();
	}

	public void setAllowManualInsertRow(final boolean allowManualInsertRow) {
		options.allowManualInsertRow = allowManualInsertRow;
		if (initialized)
			jsInit();
	}

	public void setAllowInsertColumn(final boolean allowInsertColumn) {
		options.allowInsertColumn = allowInsertColumn;
		if (initialized)
			jsInit();
	}

	public void setAllowManualInsertColumn(final boolean allowManualInsertColumn) {
		options.allowManualInsertColumn = allowManualInsertColumn;
		if (initialized)
			jsInit();
	}

	public void setAllowDeleteRow(final boolean allowDeleteRow) {
		options.allowDeleteRow = allowDeleteRow;
		if (initialized)
			jsInit();
	}

	public void setAllowDeleteColumn(final boolean allowDeleteColumn) {
		options.allowDeleteColumn = allowDeleteColumn;
		if (initialized)
			jsInit();
	}

	public void setWordWrap(final boolean wordWrap) {
		options.wordWrap = wordWrap;
		if (initialized)
			jsInit();
	}

	public void setCsvFileName(final String csvFileName) {
		options.csvFileName = csvFileName;
		if (initialized)
			jsInit();
	}

	public void setCsvHeaders(final boolean csvHeaders) {
		options.csvHeaders = csvHeaders;
		if (initialized)
			jsInit();
	}

	/**
	 * 
	 * @param aligns
	 */
	public void setAlignments(final ColumnAlign[] aligns) {
		options.colAlignments = aligns == null ? null
				: Arrays.asList(aligns).stream().map(align -> align.getCssName())
						.toArray(String[]::new);
		if (initialized)
			jsInit();
	}
}
