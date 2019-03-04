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
package gwt.material.design.components.client.ui.misc.calendar;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialSelectedField;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDatePickerItem extends MaterialSelectedField implements HasName, HasText {

	// ////////////////////////////////////////////////////////////////
	// Control for change value in RadioButton group
	// Because the event is not trigger when another radio is selected
	// ////////////////////////////////////////////////////////////////
	protected final static Map<String, MaterialDatePickerItem> history = new HashMap<>();

	protected void updateHistory() {
		if (isSelected())
			putInHistory(true);
		else
			removeFromHistory();
	}

	protected void putInHistory(final boolean fireEvent) {
		final MaterialDatePickerItem old = history.get(getName());
		if (fireEvent && old != null && old != this) {
			old.setSelected(false, false);
			old.fireSelectEvent();
		}
		history.put(getName(), this);
	}

	protected void removeFromHistory() {
		final MaterialDatePickerItem old = history.get(getName());
		if (old == this)
			history.remove(getName());
	}

	protected final TextMixin<MaterialDatePickerItem> textMixin = new TextMixin<>(this);
	protected final AttributeMixin<MaterialDatePickerItem, String> nameMixin = new AttributeMixin<>(this,
			CssAttribute.NAME, FromString.TO_STRING);

	public MaterialDatePickerItem() {
		super(HtmlElements.LABEL.createElement(), CssName.MDC_DATEPICKER__DAYS__ITEM,
				CssName.MDC_TYPOGRAPHY__CAPTION);
		super.initializeSelectedMixin(this, CssName.MDC_DATEPICKER__DAYS__ACTIVE);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {

		ripleMixin.initialize();
		fireChangeOnClick = true;

		addClickHandler(event -> {
			event.stopPropagation();
			setSelected(true, false);
		});

		if (isSelected())
			updateHistory();

		super.onInitialize();
	}

	@Override
	public void setSelected(boolean selected, boolean fireEvents) {
		super.setSelected(selected, fireEvents);
		updateHistory();
	}

	@Override
	public void setName(String name) {
		nameMixin.setValue(name);
	}

	@Override
	public String getName() {
		return nameMixin.getValue();
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
