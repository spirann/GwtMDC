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
package gwt.material.design.components.client.ui.misc.datePicker.headers;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.components.client.base.mixin.HasValueMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.lang.MdcDate;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class SingleDateHeader extends Div implements HasValue<MdcDate>{
	
	protected final Label dayOfWeekLabel = new Label(CssName.MDC_TYPOGRAPHY__CAPTION, CssName.MDC_DATEPICKER__HEADER__SINGLE_DATE__WEEK);
	protected final Label monthLabel = new Label(CssName.MDC_TYPOGRAPHY__CAPTION, CssName.MDC_DATEPICKER__HEADER__SINGLE_DATE__MONTH);
	protected final Label dayLabel = new Label(CssName.MDC_TYPOGRAPHY__HEADLINE_2, CssName.MDC_DATEPICKER__HEADER__SINGLE_DATE__DAY);
	protected final Label yearLabel = new Label(CssName.MDC_TYPOGRAPHY__CAPTION, CssName.MDC_DATEPICKER__HEADER__SINGLE_DATE__YEAR);
	
	protected final HasValueMixin<SingleDateHeader, MdcDate> valueMixin = new HasValueMixin<>(this);
	
	public SingleDateHeader() {
		super(CssName.MDC_DATEPICKER__HEADER__SINGLE_DATE);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(dayOfWeekLabel);
		add(monthLabel);
		add(dayLabel);
		add(yearLabel);
	}

	protected void drawValue(final MdcDate date) {
		dayOfWeekLabel.setText(date.getDayOfWeekFullName());
		monthLabel.setText(date.getMonthFullName());
		dayLabel.setText(String.valueOf(date.getDay()));
		yearLabel.setText(String.valueOf(date.getYear()));
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<MdcDate> handler) {
		return valueMixin.addValueChangeHandler(handler);
	}

	@Override
	public MdcDate getValue() {
		return valueMixin.getValue();
	}

	@Override
	public void setValue(MdcDate value) {
		drawValue(value);
		valueMixin.setValue(value);
	}

	@Override
	public void setValue(MdcDate value, boolean fireEvents) {
		drawValue(value);
		valueMixin.setValue(value, fireEvents);
	}

}
