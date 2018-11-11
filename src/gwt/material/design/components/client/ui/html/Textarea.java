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
package gwt.material.design.components.client.ui.html;

import gwt.material.design.components.client.base.interfaces.HasResize;
import gwt.material.design.components.client.base.mixin.ResizeMixin;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Resize;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class Textarea extends MaterialWidget implements HasResize {

	protected final ResizeMixin<Textarea> resizeMixin = new ResizeMixin<>(this);
	
	public Textarea() {
		super(HtmlElements.TEXTAREA.createElement());
	}

	public Textarea(final String ... initialClasses) {
		super(HtmlElements.TEXTAREA.createElement(), initialClasses);
	}

	public native void setRows(Integer rows)/*-{
		var element = this.@gwt.material.design.components.client.ui.html.Textarea::getElement()();
		element.rows = rows;
	}-*/;

	public native void setCols(Integer cols)/*-{
		var element = this.@gwt.material.design.components.client.ui.html.Textarea::getElement()();
		element.cols = cols;
	}-*/;
	
	@Override
	public void setResize(Resize resize) {
		resizeMixin.setResize(resize);
	}

	@Override
	public Resize getResize() {
		return resizeMixin.getResize();
	}
}
