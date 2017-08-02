/*
 * #%L
 * Gwt Material Design Components
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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

import gwt.material.design.components.client.base.HasDisabledClass;

/**
 * 
 * @author Richeli Vargas
 *
 * @param <T>
 */
public class EnabledMixin<T extends Widget & IndexedPanel.ForIsWidget & HasEnabled & HasDisabledClass>
		extends AbstractMixin<T> implements HasEnabled, HasDisabledClass {

	private static final String DISABLED = "disabled";

	private String disabledClass;

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

	public String getDisabledClass() {
		return disabledClass;
	}

	public void setDisabledClass(String disabledClass) {
		this.disabledClass = disabledClass;
	}

	@SuppressWarnings("unchecked")
	private void applyEnabled(boolean enabled, T element) {

		final String disabledClass;

		if (element instanceof HasDisabledClass) {
			disabledClass = ((HasDisabledClass) element).getDisabledClass();
		} else {
			disabledClass = null;
		}

		if (enabled) {
			element.getElement().removeAttribute(DISABLED);
			if (disabledClass != null) {
				element.getElement().removeClassName(disabledClass);
			}
		} else {
			element.getElement().setAttribute(DISABLED, "");
			if (disabledClass != null) {
				element.getElement().addClassName(disabledClass);
			}
		}

		for (int index = 0; index < element.getWidgetCount(); index++) {

			final Widget widget = element.getWidget(index);

			if (widget instanceof Widget && widget instanceof IndexedPanel.ForIsWidget && widget instanceof HasEnabled
					&& widget instanceof HasDisabledClass) {
				applyEnabled(enabled, (T) widget);
			}
		}

	}
}
