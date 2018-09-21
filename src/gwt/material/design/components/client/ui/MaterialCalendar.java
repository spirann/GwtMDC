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

import java.util.Date;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCalendar extends Div {

	protected Div header = new Div(CssName.MDC_CALENDAR__HEADER__CONTENT);
	protected Div body = new Div(CssName.MDC_CALENDAR__BODY__CONTENT);
	protected Label yearLabel = new Label(CssName.MDC_CALENDAR__HEADER__YEAR);
	
	public MaterialCalendar() {
		super(CssName.MDC_CALENDAR);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		if(yearLabel.getText().isEmpty()) {
			yearLabel.setText(String.valueOf((new Date()).getYear() + 1900));
		}
		
		header.add(yearLabel);
		
		add(header);
		add(body);
	}
}
