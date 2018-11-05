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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasOpen;
import gwt.material.design.components.client.base.interfaces.HasToggler;
import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class TogglerMixin<UIO extends MaterialUIObject & HasOpen & HasToggler> extends AbstractMixin<UIO> implements HasToggler {

	private String togglerId;
	private HandlerRegistration handler;
	private JavaScriptObject toggler;
	private JavaScriptObject event;

	public TogglerMixin(UIO uiObject) {
		super(uiObject);
	}

	void init() {
		clearHandler();
		if (toggler != null && event != null)
			unbind(toggler, event);

		toggler = find(togglerId);
		
		event = bind(toggler);
	}

	void clearHandler() {
		if (handler != null) {
			handler.removeHandler();
			handler = null;
		}
	}

	native JavaScriptObject find(final String tooglerId) /*-{
		return $wnd.jQuery('#' + tooglerId);
	}-*/;

	native JavaScriptObject bind(final JavaScriptObject toogler) /*-{		
		if(!toogler)
			return null;
			
		var _this = this;
		var handler = function() {
			_this.@gwt.material.design.components.client.base.mixin.TogglerMixin::toggle()();
		};
		$wnd.jQuery(toogler).bind("click", handler);
		return handler;
	}-*/;

	native void unbind(final JavaScriptObject toogler, final JavaScriptObject handler) /*-{
		$wnd.jQuery(toogler).unbind("click", handler);
	}-*/;

	void toggle() {
		final HasOpen hasOpen = (HasOpen) uiObject;	
		hasOpen.setOpen(!hasOpen.isOpen());
	}

	@Override
	public void setToggler(String togglerId) {
		this.togglerId = togglerId;

		if (togglerId != null && !uiObject.isAttached() && handler == null)
			handler = uiObject.addAttachHandler(event -> {
				if (event.isAttached())
					init();
			});
		else if (togglerId != null)
			init();
	}

	@Override
	public String getToggler() {
		return togglerId;
	}

}
