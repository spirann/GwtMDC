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
 * WITHOUUIO WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.components.client.base.mixin;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasAutoInitData;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.constants.AutoInitData;
import gwt.material.design.components.client.constants.CssAttribute;

/**
 * @author Richeli Vargas
 */
public class AutoInitDataMixin<UIO extends MaterialUIObject & HasAutoInitData> extends AttributeMixin<UIO, AutoInitData>
		implements HasAutoInitData {

	private HandlerRegistration handler;

	public AutoInitDataMixin(final UIO uiObject) {
		super(uiObject, CssAttribute.DATA_MDC_AUTO_INIT);
	}

	void init() {
		clearHandler();
		switch (getValue()) {
		case MDC_RIPPLE:
			rippleInit(uiObject.getElement());
			break;
		case NONE:
		default:
			break;
		}
	}

	void clearHandler() {
		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}

	@Override
	public void setUiObject(UIO uiObject) {
		super.setUiObject(uiObject);
		clearHandler();
	}

	@Override
	public void setAutoInitData(AutoInitData autoInitData) {
		super.setValue(autoInitData);

		if (autoInitData != null && !uiObject.isAttached() && handler == null)
			handler = uiObject.addAttachHandler(event -> {
				if (event.isAttached())
					init();
			});
		else if (autoInitData != null)
			init();

	}

	public static native void rippleInit(Element element)/*-{
		$wnd.mdc.ripple.MDCRipple.attachTo(element);
	}-*/;

	@Override
	public AutoInitData getAutoInitData() {
		return getValue();
	}

}
