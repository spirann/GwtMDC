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
package gwt.material.design.components.client.ui.misc.datePicker.inlines;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasSelection;
import gwt.material.design.components.client.base.mixin.HasSelectionMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.lang.MdcMonth;
import gwt.material.design.components.client.ui.MaterialIconButton;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class YearRange extends Div implements HasSelection<Integer>, HasSelectionHandlers<Integer> {

	protected final MaterialIconButton previousMonthAct = new MaterialIconButton(IconType.CHEVRON_LEFT);
	protected final Label label = new Label(CssName.MDC_DATEPICKER__INLINE_SELECTOR__LABEL, CssName.MDC_TYPOGRAPHY__BODY_1);
	protected final MaterialIconButton nextMonthAct = new MaterialIconButton(IconType.CHEVRON_RIGHT);

	protected final HasSelectionMixin<YearRange, Integer> selectionMixin = new HasSelectionMixin<>(this);

	public YearRange() {
		super(CssName.MDC_DATEPICKER__INLINE_SELECTOR);
		setSelection(new MdcMonth().getYear());
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		previousMonthAct.addClickHandler(event -> previous());
		nextMonthAct.addClickHandler(event -> next());

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

	protected void drawSelection(final Integer year) {
		if (initialized)
			label.setText((year - 12) + " ~ " + (year + 12));
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return selectionMixin.addSelectionHandler(handler);
	}

	@Override
	public void setSelection(Integer selected) {
		drawSelection(selected);
		selectionMixin.setSelection(selected);
	}

	@Override
	public void setSelection(Integer selected, boolean fireEvents) {
		drawSelection(selected);
		selectionMixin.setSelection(selected, fireEvents);
	}

	@Override
	public Integer getSelection() {
		return selectionMixin.getSelection();
	}

	public void previous() {
		setSelection(getSelection() - 25);
	}

	public void next() {
		setSelection(getSelection() + 25);
	}
}
