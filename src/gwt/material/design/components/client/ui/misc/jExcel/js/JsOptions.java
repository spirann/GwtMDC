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
package gwt.material.design.components.client.ui.misc.jExcel.js;

import com.google.gwt.core.client.JavaScriptObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * 
 * @author Richeli Vargas
 *
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsOptions {

	// Column types and configurations
	// columns:[],
	// Column header titles
	// colHeaders:[],
	// Column width sizes
	// colWidths:[],
	// Column alignment
	// colAlignments:[],
	// Colum header classes
	// colHeaderClasses:[],
	// Column width that is used by default
	// Default: 50
	@JsProperty
	public int defaultColWidth;
	// Minimal number of blank rows in the end
	// Default: 0
	@JsProperty
	public int minSpareRows;
	// Minimal number of blank cols in the end
	// Default: 0
	@JsProperty
	public int minSpareCols;
	// Minimal table dimensions
	// minDimensions:[0,0],
	// Custom context menu (function)
	// Default: null
	@JsProperty
	public JavaScriptObject contextMenu;
	// Context menu class
	// Default: null
	@JsProperty
	public String contextMenuClass;
	// Allow column sorting
	// Default: true
	@JsProperty
	public boolean columnSorting;
	// Allow column resizing
	// Default: true
	@JsProperty
	public boolean columnResize;
	// Allow row dragging
	// Default: true
	@JsProperty
	public boolean rowDrag;
	// Allow table edition
	// Default: true
	@JsProperty
	public boolean editable;
	// Allow new rows
	// Default: true
	@JsProperty
	public boolean allowInsertRow;
	// Allow new rows
	// Default: true
	@JsProperty
	public boolean allowManualInsertRow;
	// Allow new columns
	// Default: true
	@JsProperty
	public boolean allowInsertColumn;
	// Allow new rows
	// Default: true
	@JsProperty
	public boolean allowManualInsertColumn;
	// Allow row delete
	// Default: true
	@JsProperty
	public boolean allowDeleteRow;
	// Allow column delete
	// Default: true
	@JsProperty
	public boolean allowDeleteColumn;
	// Global wrap
	// Default: false
	@JsProperty
	public boolean wordWrap;
	// ID of the table
	// Default: null
	@JsProperty
	public String tableId;
	// Csv url
	// Default: null
	@JsProperty
	public String csv;
	// Filename
	// Default: 'jexcel'
	@JsProperty
	public String csvFileName;
	// Csv loaded has header
	// Default: true
	@JsProperty
	public boolean csvHeaders;
	// ID of the table
	// Default: ';'
	@JsProperty
	public String separator;
	// ID of the table
	// Default: ';'
	@JsProperty
	public String delimiter;
	// Disable corner selection
	// Default: true
	@JsProperty
	public boolean selectionCopy;
	// Allow Overflow
	// Default: false
	@JsProperty
	public boolean tableOverflow;
	// Allow Overflow
	// Default: '300px'
	@JsProperty
	public String tableHeight;
	// About message
	// Default: null
	@JsProperty
	public String about;
	
}
