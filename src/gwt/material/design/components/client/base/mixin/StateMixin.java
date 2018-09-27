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

import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasState;
import gwt.material.design.components.client.constants.State;

/**
 * @author Richeli Vargas
 */
public class StateMixin<T extends Widget & HasState> extends AbstractMixin<T> implements HasState {

	private State state;
	
	public StateMixin(final T uiObject) {
		super(uiObject);
	}
	
	@Override
	public void setState(State state) {
		this.state = state;

		uiObject.getElement().removeAttribute("state");

		if (state != null && !state.getCssName().isEmpty()) {
			uiObject.getElement().setAttribute("state", state.getCssName());
		}
	}

	@Override
	public State getState() {
		return state;
	}

}
