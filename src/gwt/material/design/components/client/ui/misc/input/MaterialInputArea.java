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
package gwt.material.design.components.client.ui.misc.input;

import gwt.material.design.components.client.base.interfaces.HasResize;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Resize;
import gwt.material.design.components.client.ui.html.Textarea;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialInputArea extends MaterialInput implements HasResize {

	public MaterialInputArea() {
		super();
		addStyleName(CssName.MDC_TEXT_FIELD__TEXTAREA);
	}
	
	@Override
	protected MaterialWidget constructInput() {
		return new Textarea(CssName.MDC_TEXT_FIELD__INPUT);
	}

	public void setRows(Integer rows) {
		if (input instanceof Textarea) {
			((Textarea) input).setRows(rows);
		}
	}

	public void setCols(Integer cols) {
		if (input instanceof Textarea) {
			((Textarea) input).setCols(cols);
		}
	}
	
	@Override
	public void setResize(Resize resize) {
		((Textarea) input).setResize(resize);
	}

	@Override
	public Resize getResize() {
		return ((Textarea) input).getResize();
	}
}
