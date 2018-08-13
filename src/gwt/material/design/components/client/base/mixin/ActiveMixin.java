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

import gwt.material.design.components.client.base.HasActive;
import gwt.material.design.components.client.base.MaterialWidget;

/**
 * @author Richeli Vargas
 */
public class ActiveMixin<T extends Widget & HasActive> extends AbstractMixin<T> implements HasActive {
	private String styleName = "active";

	private boolean active = false;
	private MaterialWidget target;

	public ActiveMixin(final T widget) {
		super(widget);
	}
	
	public ActiveMixin(final T widget, final String styleName) {
		this(widget);
		this.styleName = styleName;
	}

	public ActiveMixin(final T widget, final MaterialWidget target) {
		super(widget);
		this.target = target;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
		if (target != null) {
			applyActiveStyle(target, active);
		} else {
			applyActiveStyle((Widget) uiObject, active);
		}
	}

	protected void applyActiveStyle(Widget widget, boolean active) {
		
		widget.removeStyleName(styleName);
		
		if (active) {			
			widget.addStyleName(styleName);
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}
}
