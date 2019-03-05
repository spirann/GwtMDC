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
package gwt.material.design.components.client.ui.misc.datePicker;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasSelection;
import gwt.material.design.components.client.base.mixin.HasSelectionMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialIconButton;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MonthYear extends Div implements HasSelection<MdcMonth>, HasSelectionHandlers<MdcMonth> {

	protected final MaterialIconButton previousMonthAct = new MaterialIconButton(IconType.CHEVRON_LEFT);
	protected final Label label = new Label(CssName.MDC_DATEPICKER__MONTH_YEAR__LABEL, CssName.MDC_TYPOGRAPHY__BODY_1);
	protected final MaterialIconButton nextMonthAct = new MaterialIconButton(IconType.CHEVRON_RIGHT);

	protected final HasSelectionMixin<MonthYear, MdcMonth> selectionMixin = new HasSelectionMixin<>(this);

	public MonthYear() {
		super(CssName.MDC_DATEPICKER__MONTH_YEAR);
		setSelection(new MdcMonth());
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		previousMonthAct.addClickHandler(event -> setSelection(getSelection().previous()));
		nextMonthAct.addClickHandler(event -> setSelection(getSelection().next()));

		add(previousMonthAct);
		add(label);
		add(nextMonthAct);	
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		drawSelection(getSelection());
	}
	
	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return label.addClickHandler(handler);
	}
	
	protected void drawSelection(final MdcMonth month) {
		if (initialized)
			label.setText(IMessages.INSTANCE.mdc_calendar_body_month(
					IMessages.INSTANCE.mdc_calendar_full_month(month.getMonth()), month.getYear()));
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<MdcMonth> handler) {
		return selectionMixin.addSelectionHandler(handler);
	}

	@Override
	public void setSelection(MdcMonth selected) {
		drawSelection(selected);
		selectionMixin.setSelection(selected);
	}

	@Override
	public void setSelection(MdcMonth selected, boolean fireEvents) {
		drawSelection(selected);
		selectionMixin.setSelection(selected, fireEvents);
	}

	@Override
	public MdcMonth getSelection() {
		return selectionMixin.getSelection();
	}
}
