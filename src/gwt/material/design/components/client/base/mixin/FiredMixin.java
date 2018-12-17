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

import gwt.material.design.components.client.base.interfaces.HasFired;
import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.utils.helper.TimerHelper;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class FiredMixin<UIO extends MaterialUIObject> extends AbstractMixin<UIO>
		implements HasFired {

	private final Runnable runnable;
	private String firedId;
	private HandlerRegistration handler;
	private JavaScriptObject event;

	public FiredMixin(final UIO uiObject, final Runnable runnable) {
		super(uiObject);
		this.runnable = runnable;
	}

	void init() {
		clearHandler();
		if (isAttached(firedId)) {
			unbind(firedId, event);
			event = bind(firedId);
		} else if (firedId != null) {
			TimerHelper.schedule(1000, () -> {
				if (firedId != null) {
					unbind(firedId, event);
					event = bind(firedId);
				}
			});
		}
	}

	void clearHandler() {
		if (handler != null)
			handler.removeHandler();
		handler = null;
	}

	native boolean isAttached(final String firedId) /*-{
		var ids = firedId.split(" ");
		var fullIds = '';
		for (var i = 0; i < ids.length; i++) {
			fullIds += '#' + ids[i];
			if (i + 1 < ids.length)
				fullIds += ', ';
		}

		return firedId && $wnd.jQuery("body").find(fullIds).length == ids.length;
	}-*/;

	native JavaScriptObject bind(final String firedId) /*-{

		if (!firedId)
			return null;

		var _this = this;
		var handler = function() {
			_this.@gwt.material.design.components.client.base.mixin.FiredMixin::fire()();
		};

		var ids = firedId.split(" ");
		for (var i = 0; i < ids.length; i++) {
			var toggler = $wnd.jQuery('#' + ids[i]);
			if (toggler)
				$wnd.jQuery(toggler).bind("click", handler);
		}

		return handler;

	}-*/;

	native void unbind(final String firedId, final JavaScriptObject handler) /*-{

		if (!firedId || !handler)
			return;

		var ids = firedId.split(" ");
		for (var i = 0; i < ids.length; i++) {
			var toggler = $wnd.jQuery('#' + ids[i]);
			if (toggler)
				$wnd.jQuery(toggler).unbind("click", handler);
		}

	}-*/;

	void fire() {
		runnable.run();
	}

	@Override
	public void setFired(String firedId) {
		unbind(this.firedId, event);
		this.firedId = firedId;
		if (firedId != null && !uiObject.isAttached() && handler == null)
			handler = uiObject.addAttachHandler(event -> {
				if (event.isAttached())
					init();
			});
		else if (firedId != null)
			init();
	}

	@Override
	public String getFired() {
		return firedId;
	}

}
