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
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class EnabledMixin<T extends Widget & IndexedPanel.ForIsWidget & HasEnabled>
		extends AbstractMixin<T> implements HasEnabled {

	private static final String DISABLED = "disabled";

	private HandlerRegistration handler;

	public EnabledMixin(final T widget) {
		super(widget);
	}

	@Override
	public void setUiObject(T uiObject) {
		super.setUiObject(uiObject);

		// Clean up previous handler
		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}

	@Override
	public boolean isEnabled() {
		return !uiObject.getElement().hasAttribute(DISABLED);
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (!uiObject.isAttached() && handler == null) {
			handler = uiObject.addAttachHandler(event -> {
				if (event.isAttached()) {
					applyEnabled(enabled, uiObject);
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}
			});
		} else {
			applyEnabled(enabled, uiObject);
		}
	}

	@SuppressWarnings("unchecked")
	private void applyEnabled(boolean enabled, T element) {

		if (enabled) {
			element.getElement().removeAttribute(DISABLED);
		} else {
			element.getElement().setAttribute(DISABLED, "");
		}

		for (int index = 0; index < element.getWidgetCount(); index++) {

			final Widget widget = element.getWidget(index);

			if (widget instanceof Widget && widget instanceof IndexedPanel.ForIsWidget && widget instanceof HasEnabled) {
				applyEnabled(enabled, (T) widget);
			}
		}

	}
}
