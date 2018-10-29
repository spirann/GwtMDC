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

import gwt.material.design.components.client.base.interfaces.HasActive;
import gwt.material.design.components.client.base.mixin.base.ToggleStyleNameMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;

/**
 * @author Richeli Vargas
 */
public class ActiveMixin<UIO extends MaterialUIObject & HasActive> extends ToggleStyleNameMixin<UIO> implements HasActive {

	private boolean active = false;
	private UIO target;

	public ActiveMixin(final UIO uiobject, final String styleName) {
		super(uiobject, styleName);
	}

	public ActiveMixin(final UIO uiobject) {
		this(uiobject, "active");
	}

	public ActiveMixin(final UIO uiobject, final UIO target) {
		this(target);
		this.target = target;
	}
	
	public ActiveMixin(final UIO uiobject, final UIO target, final String styleName) {
		this(uiobject, styleName);
		this.target = target;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
		if (target != null)
			target.toggleStyleName(active, cssClass);			
		else
			uiObject.toggleStyleName(active, cssClass);
	}

	@Override
	public boolean isActive() {
		return active;
	}
}
