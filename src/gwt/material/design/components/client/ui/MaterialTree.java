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
import java.util.Collection;
import java.util.stream.Collectors;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.TreeType;
import gwt.material.design.components.client.events.FilterEvent;
import gwt.material.design.components.client.events.FilterEvent.FilterHandler;
import gwt.material.design.components.client.events.FilterEvent.HasFilterHandlers;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.misc.tree.js.JsItem;
import gwt.material.design.components.client.ui.misc.tree.js.JsOptions;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTree extends Div
		implements HasType<TreeType>, HasSelectionHandlers<Collection<MaterialTree.Item>>, HasFilterHandlers<Collection<MaterialTree.Item>> {

	protected final JsOptions options = new JsOptions();
	protected JsItem[] data;

	public MaterialTree() {
		super(CssName.MDC_TREE);
		loadDefaultOptions();
	}
	
	protected void loadDefaultOptions() {
		options.onSelect = toFunction(() -> fireSelectionEvent());
		options.onFilter = toFunction(() -> fireFilterEvent());
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setData(data);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		var options = this.@gwt.material.design.components.client.ui.MaterialTree::options;
		return new $wnd.MDCTree(element, options);
	}-*/;

	protected void fireSelectionEvent() {
		SelectionEvent.fire(MaterialTree.this, getSelectedItems());
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<Collection<Item>> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}
	
	protected void fireFilterEvent() {
		FilterEvent.fire(MaterialTree.this, getFilteredItems());
	}

	@Override
	public HandlerRegistration addFilterHandler(FilterHandler<Collection<Item>> handler) {
		return addHandler(handler, FilterEvent.getType());
	}

	protected native void setData(final JsItem[] data)/*-{
		this.@gwt.material.design.components.client.ui.MaterialTree::data = data;
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			tree.setData(data);
	}-*/;

	protected native JsItem[] selectedItems()/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			return tree.getSelectedItems();
		else
			return [];
	}-*/;

	protected native JsItem[] filteredItems()/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			return tree.getFilteredItems();
		else
			return [];
	}-*/;

	protected native JsItem[] children(final String parentId)/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			return tree.getChildren(parentId);
		else
			return [];
	}-*/;

	protected native JsItem[] allChildren(final String parentId)/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			return tree.getAllChildren(parentId);
		else
			return [];
	}-*/;

	protected native JsItem[] allParents(final String parentId)/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			return tree.getAllParents(parentId);
		else
			return [];
	}-*/;

	protected native void setOptions(final JsOptions options)/*-{
		this.@gwt.material.design.components.client.ui.MaterialTree::options = options;
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			tree.setOptions(options);
	}-*/;

	public void setData(final Collection<Item> data) {
		setData(data.stream().map(item -> item.jsItem).toArray(JsItem[]::new));
	}

	public Collection<Item> getSelectedItems() {
		return Arrays.asList(selectedItems()).stream().map(jsItem -> new Item(jsItem))
				.collect(Collectors.toList());
	}

	public Collection<Item> getFilteredItems() {
		return Arrays.asList(filteredItems()).stream().map(jsItem -> new Item(jsItem))
				.collect(Collectors.toList());
	}

	public Collection<Item> getChildren(final Item item) {
		return Arrays.asList(children(item.getId())).stream().map(jsItem -> new Item(jsItem))
				.collect(Collectors.toList());
	}

	public Collection<Item> getAllChildren(final Item item) {
		return Arrays.asList(allChildren(item.getId())).stream().map(jsItem -> new Item(jsItem))
				.collect(Collectors.toList());
	}

	public Collection<Item> getAllParents(final Item item) {
		return Arrays.asList(allParents(item.getParent())).stream().map(jsItem -> new Item(jsItem))
				.collect(Collectors.toList());
	}

	public native void filter(final String text)/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			tree.filter(text);
	}-*/;

	public native void selectAll()/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			tree.selectAll();
	}-*/;

	public native void unselectAll()/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			tree.unselectAll();
	}-*/;

	public native int countSelectedItems()/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			return tree.countSelectedItems();
		else
			return -1;
	}-*/;

	public native int countFilteredItems()/*-{
		var tree = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;
		if (tree)
			return tree.countFilteredItems();
		else
			return -1;
	}-*/;

	public void setExpandIcon(final IconType icon) {
		options.expandIcon = icon.getCssName();
		setOptions(options);
	}

	public void setCollapseIcon(final IconType icon) {
		options.collapseIcon = icon.getCssName();
		setOptions(options);
	}

	@Override
	public void setType(final TreeType type) {
		options.selectionType = type.getCssName();
		setOptions(options);
	}

	@Override
	public TreeType getType() {
		return TreeType.fromStyleName(options.selectionType);
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

	public static class Item {

		private final JsItem jsItem;

		public Item() {
			this(new JsItem());
		}

		private Item(final JsItem jsItem) {
			this.jsItem = jsItem;
		}

		public Object getData() {
			return jsItem.data;
		}

		public void setData(final Object data) {
			jsItem.data = data;
		}

		public String getId() {
			return jsItem.id;
		}

		public void setId(final String id) {
			jsItem.id = id;
		}

		public String getName() {
			return jsItem.name;
		}

		public void setName(final String name) {
			jsItem.name = name;
		}

		public String getParent() {
			return jsItem.parent;
		}

		public void setParent(final String parent) {
			jsItem.parent = parent;
		}

		public boolean isSelected() {
			return jsItem.selected;
		}

		public void setSelected(boolean selected) {
			jsItem.selected = selected;
		}

		public void setOnClick(final Runnable onClick) {
			jsItem.onClick = toFunction(onClick);
		}

		public void setOnSelect(final Runnable onSelect) {
			jsItem.onSelect = toFunction(onSelect);
		}

		public void setOnUnselect(final Runnable onUnselect) {
			jsItem.onUnselect = toFunction(onUnselect);
		}

	}

	protected static final native JavaScriptObject toFunction(final Runnable runnable)/*-{
		return function() {
			runnable.@java.lang.Runnable::run()();
		};
	}-*/;
}
