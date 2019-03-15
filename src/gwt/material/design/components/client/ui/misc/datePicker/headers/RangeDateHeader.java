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
import gwt.material.design.components.client.lang.MdcRange;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class RangeDateHeader extends Div implements HasValue<MdcRange<MdcDate>> {

	protected final Div graphics = new Div(CssName.MDC_DATEPICKER__HEADER__RANGE_DATE__GRAPHICS);
	protected final Div labels = new Div(CssName.MDC_DATEPICKER__HEADER__RANGE_DATE__LABELS);
	protected final Label startLabel = new Label(CssName.MDC_TYPOGRAPHY,
			CssName.MDC_DATEPICKER__HEADER__RANGE_DATE__START);
	protected final Label endLabel = new Label(CssName.MDC_TYPOGRAPHY,
			CssName.MDC_DATEPICKER__HEADER__RANGE_DATE__END);
	protected final Div divider = new Div(CssName.MDC_DATEPICKER__HEADER__RANGE_DATE__DIVIDER);

	protected final HasValueMixin<RangeDateHeader, MdcRange<MdcDate>> valueMixin = new HasValueMixin<>(this);

	public RangeDateHeader() {
		super(CssName.MDC_DATEPICKER__HEADER__RANGE_DATE);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		startLabel.setText(IMessages.INSTANCE.mdc_calendar_initial_date());
		startLabel.setOpacity(0.38);
		
		graphics.add(new Div(CssName.MDC_DATEPICKER__HEADER__RANGE_DATE__CIRCLE));
		graphics.add(new Div(CssName.MDC_DATEPICKER__HEADER__RANGE_DATE__LINE));
		graphics.add(new Div(CssName.MDC_DATEPICKER__HEADER__RANGE_DATE__CIRCLE));

		labels.add(startLabel);
		labels.add(divider);
		labels.add(endLabel);

		add(graphics);
		add(labels);
		
		drawValue(getValue());
	}

	protected void drawValue(final MdcRange<MdcDate> range) {

		if (range == null) {
			drawValue(new MdcRange<>());
		} else {

			final MdcDate start = range.getStart();
			final MdcDate end = range.getEnd();

			if (start == null) {
				startLabel.setText(IMessages.INSTANCE.mdc_calendar_initial_date());
				startLabel.setOpacity(0.38);
			} else {
				startLabel.setText(start.toString());
				startLabel.setOpacity(1);
			}

			if (end == null) {
				endLabel.setText(IMessages.INSTANCE.mdc_calendar_final_date());
				endLabel.setOpacity(0.38);
			} else {
				endLabel.setText(end.toString());
				endLabel.setOpacity(1);
			}
		}

	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<MdcRange<MdcDate>> handler) {
		return valueMixin.addValueChangeHandler(handler);
	}

	@Override
	public MdcRange<MdcDate> getValue() {
		return valueMixin.getValue();
	}

	@Override
	public void setValue(final MdcRange<MdcDate> value) {
		drawValue(value);
		valueMixin.setValue(value);
	}

	@Override
	public void setValue(final MdcRange<MdcDate> value, boolean fireEvents) {
		drawValue(value);
		valueMixin.setValue(value, fireEvents);
	}

}
