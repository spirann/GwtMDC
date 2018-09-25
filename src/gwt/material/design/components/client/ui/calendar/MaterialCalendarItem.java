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
package gwt.material.design.components.client.ui.calendar;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.mixin.NameMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.form.MaterialSelectedField;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCalendarItem extends MaterialSelectedField implements HasName, HasText {

	// ////////////////////////////////////////////////////////////////
	// Control for change value in RadioButton group
	// Because the event is not trigger when another radio is selected
	// ////////////////////////////////////////////////////////////////
	protected final static Map<String, MaterialCalendarItem> history = new HashMap<>();

	protected void updateHistory() {
		if (isSelected()) {
			putInHistory(true);
		} else {
			removeFromHistory();
		}
	}
	
	protected void putInHistory(final boolean fireEvent) {
		final MaterialCalendarItem old = history.get(getName());
		if (fireEvent && old != null && old != this) {
			old.setSelected(false, false);
			old.fireChangeEvent();
		}
		history.put(getName(), this);
	}

	protected void removeFromHistory() {
		final MaterialCalendarItem old = history.get(getName());
		if (old == this) {
			history.remove(getName());
		}
	}
	
	protected final TextMixin<MaterialCalendarItem> textMixin = new TextMixin<>(this);
	protected final NameMixin<MaterialCalendarItem> nameMixin = new NameMixin<>(this);
	
	public MaterialCalendarItem() {
		super(HtmlElements.LABEL.createElement(), CssName.MDC_CALENDAR__DAY_SELECTOR__DAYS__LABEL, CssName.MDC_TYPOGRAPHY__CAPTION);
		super.initializeSelectedMixin(this, CssName.MDC_CALENDAR__DAY_SELECTOR__DAYS__LABEL_ACTIVE);
	}
	
	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{		
		return element;
	}-*/;

	@Override
	protected void onInitialize() {

		ripleMixin.initialize();
		fireChangeOnClick = true;
		
		addClickHandler(event -> setSelected(true, false));
		
		if (isSelected()) {
			updateHistory();
		}
		
		super.onInitialize();
	}
	
	@Override
	protected void fireChangeEvent() {
		updateHistory();
		super.fireChangeEvent();
	}

	@Override
	public void setName(String name) {
		nameMixin.setName(name);
	}

	@Override
	public String getName() {
		return nameMixin.getName();
	}

	@Override
	public String getText() {
		return textMixin.getText();
	}

	@Override
	public void setText(String text) {
		textMixin.setText(text);
	}
}
