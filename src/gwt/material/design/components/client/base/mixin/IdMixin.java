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
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasId;

/**
 * @author Richeli Vargas
 */
public class IdMixin<T extends Widget & HasId> extends AbstractMixin<T> implements HasId {

	private String id;
	private HandlerRegistration handlerRegistration;

	public IdMixin(final T uiObject) {
		super(uiObject);
	}

	@Override
	public void setId(final String id) {
		this.id = id;

		if (uiObject.isAttached()) {
			uiObject.getElement().setId(id);
		} else {
			
			if(handlerRegistration != null) {
				handlerRegistration.removeHandler();
			}
			
			handlerRegistration = uiObject.addAttachHandler(event -> {
				uiObject.getElement().setId(id);
				handlerRegistration.removeHandler();
			});
		}
	}

	@Override
	public String getId() {
		return id;
	}
}
