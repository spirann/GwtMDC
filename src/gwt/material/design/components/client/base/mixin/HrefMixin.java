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

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.components.client.base.HasHref;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class HrefMixin<T extends UIObject> extends AbstractMixin<T> implements HasHref {

	private static final String HREF = "href";
	private static final String TARGET = "target";
	
	public HrefMixin(T uiObject) {
		super(uiObject);
	}

	@Override
	public void setHref(String href) {
		uiObject.getElement().setAttribute(HREF, href);
	}

	@Override
	public String getHref() {
		return uiObject.getElement().getAttribute(HREF);
	}

	@Override
	public void setTarget(String target) {
		uiObject.getElement().setAttribute(TARGET, target);
	}

	@Override
	public String getTarget() {
		return uiObject.getElement().getAttribute(TARGET);
	}

}
