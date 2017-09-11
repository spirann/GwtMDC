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
package gwt.material.design.components.client.base;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.MaterialListGroup;
import gwt.material.design.components.client.ui.html.Aside;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialDrawerBase extends Aside implements HasOpen {

	protected JavaScriptObject jsElement;
	protected MaterialListGroup content = new MaterialListGroup();

	private HandlerRegistration handler;

	private boolean initialized = false;

	protected MaterialDrawerBase(final String primaryClass) {
		super(primaryClass);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if (!initialized) {
			
			jsElement = getElement();
			
			initialized = true;
		}
	}

	@Override
	protected void onUnload() {
		super.onUnload();

		if (handler != null) {
			handler.removeHandler();
		}
	}

	@Override
	public void add(Widget child) {

		if (content.getParent() == null) {
			super.add(content);
		}

		content.add(child);

	}

	@Override
	public void insert(Widget child, int beforeIndex) {
		content.insert(child, beforeIndex);
	}

	@Override
	public Widget getWidget(int index) {
		return content.getWidget(index);
	}

	@Override
	public int getWidgetCount() {
		return content.getWidgetCount();
	}

	@Override
	public void setOpen(boolean open) {

		if (!isAttached() && handler == null) {

			handler = addAttachHandler(event -> {

				if (event.isAttached()) {
					setNativeOpen(open);
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}

			});

		} else {
			setNativeOpen(open);
		}
	}

	@Override
	public native boolean isOpen()/*-{
		
		var drawer = this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement;
		
		return drawer && drawer.style.display != "none";
		
	}-*/;


	protected native void setNativeOpen(boolean open)/*-{
		
		var drawer = this.@gwt.material.design.components.client.base.MaterialDrawerBase::jsElement;
		
		if (open) {
			drawer.style.display = "flex";
		} else {
			drawer.style.display = "none";
		}
		
	}-*/;

	@Override
	public void open() {
		setOpen(true);
	}

	@Override
	public void close() {
		setOpen(false);
	}

	public static class Spacer extends MaterialWidget {

		protected Spacer(final String cssClass) {
			super(HtmlElements.DIV.createElement(), cssClass);
		}

	}

}
