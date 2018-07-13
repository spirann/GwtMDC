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

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasInputMask;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class InputMaskMixin<T extends UIObject> extends AbstractMixin<T> implements HasInputMask {

	private static final String INPUT_MASK = "inputmask";

	private HandlerRegistration handlerRegistration;

	public InputMaskMixin(T uiObject) {
		super(uiObject);
	}

	/**
	 * S - Any char
	 * 9 - Numbers
	 * A - Letters
	 */
	@Override
	public void setInputMask(String inputMask) {

		uiObject.getElement().removeAttribute(inputMask);

		if (handlerRegistration != null) {
			handlerRegistration.removeHandler();
			handlerRegistration = null;
		}
		
		if (((Widget) uiObject).isAttached()) {
			unMask(uiObject.getElement());
		}
		
		if (inputMask != null) {

			if (((Widget) uiObject).isAttached()) {				
				setInputMask(uiObject.getElement(), inputMask);				
			} else {
				handlerRegistration = ((Widget) uiObject)
						.addAttachHandler(event -> setInputMask(inputMask));
			}

			uiObject.getElement().setAttribute(INPUT_MASK, inputMask);
			
		}
	}

	@Override
	public String getInputMask() {
		return uiObject.getElement().getAttribute(INPUT_MASK);
	}


	protected native void setInputMask(Element element, String inputMask)/*-{		
		$wnd.VMasker(element).maskPattern(inputMask);
	}-*/;
	
	protected native void unMask(Element element)/*-{		
		$wnd.VMasker(element).unMask();
	}-*/;

}
