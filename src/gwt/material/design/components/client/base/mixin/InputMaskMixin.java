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
package gwt.material.design.components.client.base.mixin;

import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.interfaces.HasInputMask;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.masker.Masker;
import gwt.material.design.components.client.utils.debug.Console;
import gwt.material.design.components.client.utils.helper.DOMHelper;
import gwt.material.design.components.client.utils.helper.ObjectHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class InputMaskMixin<UIO extends MaterialUIObject & HasKeyUpHandlers> extends AttributeMixin<UIO, String>
		implements HasInputMask {

	private HandlerRegistration handler;

	public InputMaskMixin(UIO uiObject) {
		super(uiObject, CssAttribute.INPUT_MASK, FromString.TO_STRING);
	}

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	@Override
	public void setInputMask(String inputMask) {

		setValue(inputMask);

		if (ObjectHelper.isNullOrEmpty(inputMask))
			clearHandler();
		else if (handler == null)
			handler = uiObject.addKeyUpHandler(event -> {
				Console.log("Value: " + DOMHelper.getValue(uiObject.getElement()));
				DOMHelper.setValue(uiObject.getElement(),
						Masker.toPattern(DOMHelper.getValue(uiObject.getElement()), getInputMask()));
				
			});
	}

	@Override
	public String getInputMask() {
		return super.getValue();
	}

	public String getValue() {
		return Masker.fromPattern(DOMHelper.getValue(uiObject.getElement()), getInputMask());
	}

	protected void clearHandler() {
		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}

}
