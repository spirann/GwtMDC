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

import gwt.material.design.components.client.base.HasAspectRatio;
import gwt.material.design.components.client.constants.AspectRatio;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class AspectRatioMixin<W extends Widget> extends StyleMixin<W> implements HasAspectRatio {

	private AspectRatio aspectRatio;

	public AspectRatioMixin(final W widget) {
		super(widget);
	}
	
	@Override
	public void setAspectRatio(AspectRatio aspectRatio) {
		this.aspectRatio = aspectRatio;
		if (aspectRatio == null) {
			setStyle(null);
		} else {
			setStyle(aspectRatio.getCssName());
		}
	}
	
	@Override
	public AspectRatio getAspectRatio() {
		return aspectRatio;
	}

}
