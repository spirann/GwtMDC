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

import gwt.material.design.components.client.base.interfaces.HasResize;
import gwt.material.design.components.client.constants.Resize;
import gwt.material.design.components.client.ui.misc.input.MaterialInput;
import gwt.material.design.components.client.ui.misc.input.MaterialInputArea;
import gwt.material.design.components.client.ui.misc.input.MaterialInputBox;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialTextArea extends MaterialInputBox implements HasResize {
	
	public MaterialTextArea() {
		super();
	}

	@Override
	protected MaterialInput contructInput() {
		return new MaterialInputArea();
	}
	
	public void setRows(int rows) {
		if (input instanceof MaterialInputArea) {
			((MaterialInputArea) input).setRows(rows);
		}
	}

	public void setCols(int cols) {
		if (input instanceof MaterialInputArea) {
			((MaterialInputArea) input).setCols(cols);
		}
	}
	
	@Override
	public void setResize(Resize resize) {
		((MaterialInputArea) input).setResize(resize);
	}

	@Override
	public Resize getResize() {
		return ((MaterialInputArea) input).getResize();
	}
}
