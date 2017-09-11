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

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Header;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDrawerHeader extends Header {

	protected Div headerContent = new Div(CssName.MDC_TEMPORARY_DRAWER_HEADER_CONTENT);

	private boolean initialized = false;

	public MaterialDrawerHeader() {
		super(CssName.MDC_TEMPORARY_DRAWER_HEADER);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {
			super.add(headerContent);
			initialized = true;
		}
		
	}

	@Override
	public void add(Widget child) {
		headerContent.add(child);
	}
	
	@Override
	public Widget getWidget(int index) {
		return headerContent.getWidget(index);
	}

	@Override
	public int getWidgetCount() {
		return headerContent.getWidgetCount();
	}
	
	@Override
	public int getWidgetIndex(Widget child) {
		return headerContent.getWidgetIndex(child);
	}
}
