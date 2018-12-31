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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.jExcel.js.JsOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSpreadsheet extends Div {

	protected final Div spreadsheet = new Div(CssName.MDC_SPREADSHEET__SPREADSHEET, CssName.MDC_TYPOGRAPHY);
	protected final JsOptions options = new JsOptions();
	
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
		options.defaultColWidth = 50;
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
		options.csvHeaders = true;
		options.separator = ";";
		options.delimiter = ";";
		options.selectionCopy = true;
		options.tableOverflow = false;
		options.tableHeight = "300px";
		options.contextMenuClass = CssName.MDC_MENU;
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		var options = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::options;		
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		return $wnd.jQuery(element).jexcel(options);	
	}-*/;
	

	public void setUrl(final String url) {
		options.csv = url;
		if(initialized)
			jsInit();
	}
	
	public native void jsIn(final String url)/*-{

		var options = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::options;
		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		var element = spreadsheet.@com.google.gwt.user.client.ui.UIObject::getElement()();
		
		options.csv = options;
		
		$wnd.jQuery(element).jexcel({
			contextMenuClass : contextMenuClass,
			// URL from the CSV file
			csv : url,
			// Get the first of the CSV file and consider the headers
			csvHeaders : true,
			// Default column widths
			colWidths : [ 300, 80, 100 ],
			// Allow row dragging
            rowDrag:true,
            // Disable corner selection
            //selectionCopy: true,
			// Allow scroll
			//tableOverflow : true,
			// Set height
			tableHeight : '100%',
			// csv file name
			csvFileName : 'Uhuuuuu',
			// Set csv separator
			separator: ';',
			// Set csv delimiter
			delimiter: ';'
		});

	}-*/;
}
