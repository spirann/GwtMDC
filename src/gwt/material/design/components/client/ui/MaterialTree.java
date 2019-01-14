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

import java.util.Arrays;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.ui.html.Div;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTree extends Div {

	public MaterialTree() {
		super(CssName.MDC_TREE);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.MDCTree(element);
	}-*/;

	protected void onInitialize() {
		super.onInitialize();
		
		final Item parent = new Item();
		parent.id = "item_1";
		parent.name = "Item 1";
		
		final Item item2 = new Item();
		item2.id = "item_2";
		item2.name = "Item 2";
		item2.parent = "item_1";
		
		final Item item3 = new Item();
		item3.id = "item_3";
		item3.name = "Item 3";
		item3.parent = "item_2";
		
		setData(Arrays.asList(parent, item2, item3).stream().toArray(Item[]::new));
	}

	protected native void setData(final Item[] data)/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if(tree)
			tree.setData(data);
	}-*/;

	@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
	public static class Item {

		@JsProperty
		public String id;

		@JsProperty
		public String name;

		@JsProperty
		public String parent;

	}
	
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
