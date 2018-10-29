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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.constants.CssAttribute;

/**
 * @author Richeli Vargas
 */
public class EnabledMixin<UIO extends MaterialUIObject & HasEnabled> extends AttributeMixin<UIO, Boolean> implements HasEnabled {

	private HandlerRegistration handler;

	public EnabledMixin(final UIO uiObject) {
		super(uiObject, CssAttribute.DISABLED);
	}

	void clearHandler() {
		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		// Invert because of the attribute name in CSS
		// setValue(enabled ? null : Boolean.TRUE);

		// Test ideas to remove bellow
		if (!uiObject.isAttached() && handler == null)
			handler = uiObject.addAttachHandler(event -> {
				if (event.isAttached())
					setEnabled(enabled ? null : Boolean.TRUE, uiObject);
			});
		else
			setEnabled(enabled ? null : Boolean.TRUE, uiObject);
	}

	protected final void setEnabled(final boolean enabled, final MaterialUIObject uiObject) {

		clearHandler();
		setValue(getAttribute(), enabled ? null : Boolean.TRUE, uiObject);

		for (int index = 0; index < uiObject.getWidgetCount(); index++) {
			final Widget widget = uiObject.getWidget(index);
			if (widget instanceof MaterialUIObject)
				setEnabled(enabled, (MaterialUIObject) widget);
		}
	}

	@Override
	public boolean isEnabled() {
		// Invert because of the attribute name in CSS
		return getValue() == null ? Boolean.TRUE : Boolean.FALSE;
	}
}
