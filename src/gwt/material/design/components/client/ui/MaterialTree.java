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
import com.google.gwt.user.client.Random;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.tree.js.JsItem;
import gwt.material.design.components.client.ui.misc.tree.js.JsOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTree extends Div {

	protected final JsOptions options = new JsOptions();
	
	public MaterialTree() {
		super(CssName.MDC_TREE);
		options.selectionType = "choice";
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		var options = this.@gwt.material.design.components.client.ui.MaterialTree::options;
		return new $wnd.MDCTree(element, options);
	}-*/;

	protected void onInitialize() {
		super.onInitialize();

		setData(generateData_forTest());
	}

	protected JsItem[] generateData_forTest() {

		final int count = 1000;
		final List<JsItem> items = new LinkedList<>();

		for (int i = 0; i < count; i++) {

			final JsItem  item = new JsItem();
			item.id = "item_" + i;
			item.name = "Item " + i;
			//item.onClick = onClick(item);
			//item.onSelect = onSelect(item);
			//item.onUnselect = onUnselect(item);
			// Checkbox
			item.selected = i == 1;
			// Radio button
			//item.action = "choice";

			final int parent = Random.nextInt(i);

			if (i > 0)
				item.parent = items.get(parent).id;

			items.add(item);
		}

		return items.stream().toArray(JsItem[]::new);
	}

	protected native JavaScriptObject onClick(final JsItem item) /*-{
		return function() {
			console.log('click on: ' + item.name);
		};
	}-*/;

	protected native JavaScriptObject onSelect(final JsItem item) /*-{
		return function() {
			console.log('select on: ' + item.name);
		};
	}-*/;

	protected native JavaScriptObject onUnselect(final JsItem item) /*-{
		return function() {
			console.log('unselect on: ' + item.name);
		};
	}-*/;

	protected native void setData(final JsItem[] data)/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			tree.setData(data);
	}-*/;
	
	@Override
	public void setColor(Color color) {
		setCssProperty(CssMixin.MDC_TREE__INK_COLOR, color.getCssName());
	}

	@Override
	public void setBackgroundColor(Color color) {
		setCssProperty(CssMixin.MDC_TREE__FILL_COLOR, color.getCssName());
	}

	public void setHoverColor(Color color) {
		setCssProperty(CssMixin.MDC_TREE__HOVER_COLOR, color.getCssName());
	}

	public void setOnHoverColor(Color color) {
		setCssProperty(CssMixin.MDC_TREE__HOVER_INK_COLOR, color.getCssName());
	}
}
