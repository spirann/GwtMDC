/*
 * #%L
 * GwtMaterial
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
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Richeli Vargas
 */
public class ApplyStyleMixin<T extends Widget> extends StyleMixin<T> {

	private boolean apply = false;
	
	private final String cssClass; 
	
	private HandlerRegistration handler;
	
	public ApplyStyleMixin(final T widget, final String cssClass) {
		super(widget);
		this.cssClass = cssClass;
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
	
	public void setApply(boolean apply) {
		this.apply = apply;
		
		if (!uiObject.isAttached() && handler == null) {
			
			handler = uiObject.addAttachHandler(event -> {
				if (event.isAttached()) {
					apply(apply);
				} else if (handler != null) {
					handler.removeHandler();
					handler = null;
				}
			});
			
		} else {
			apply(apply);
		}
	}
	
	private void apply(boolean apply){
		
		setStyle(null);

		if (apply) {
			setStyle(cssClass);
		}
		
	}

	public boolean isApplied() {
		return apply;
	}
}
