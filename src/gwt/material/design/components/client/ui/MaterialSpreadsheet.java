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

import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialSpreadsheet extends Div {

	private final Div spreadsheet = new Div();

	public MaterialSpreadsheet() {
		super(CssName.MDC_SPREADSHEET);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(spreadsheet);
		test(spreadsheet.getElement());
	}

	public native void setUrl(final String url)/*-{

		var spreadsheet = this.@gwt.material.design.components.client.ui.MaterialSpreadsheet::spreadsheet;
		$wnd.jQuery(spreadsheet).jexcel({
			// URL from the CSV file
			csv : url,
			// Get the first of the CSV file and consider the headers
			csvHeaders : true,
			// Default column widths
			colWidths : [ 300, 80, 100 ]
		});

	}-*/;

	protected native void test(final Element element)/*-{

		data = [ [ 'Google', 1998, 807.80 ], [ 'Apple', 1976, 116.52 ],
				[ 'Yahoo', 1994, 38.66 ], ];

		$wnd.jQuery(element).jexcel({
			data : data,
			colWidths : [ 300, 80, 100 ]
		});

	}-*/;
}
