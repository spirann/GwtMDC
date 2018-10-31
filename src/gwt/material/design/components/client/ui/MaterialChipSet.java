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

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.ChipSetType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialChipSet extends Div implements HasType<ChipSetType> {

	protected final TypeMixin<MaterialChipSet, ChipSetType> typeMixin = new TypeMixin<>(this, ChipSetType.DEFAULT);

	protected List<MaterialChip> selectedChips = new LinkedList<>();

	public MaterialChipSet() {
		super(CssName.MDC_CHIP_SET);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.chips.MDCChipSet(element);
	}-*/;

	@Override
	public void setType(ChipSetType type) {
		typeMixin.setType(type);
	}

	@Override
	public ChipSetType getType() {
		return typeMixin.getType();
	}

}
