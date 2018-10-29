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

import gwt.material.design.components.client.base.interfaces.HasCircle;
import gwt.material.design.components.client.base.mixin.base.AbstractMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;

/**
 * 
 * @author Richeli Vargas
 */
public class CircleMixin<UIO extends MaterialUIObject & HasCircle> extends AbstractMixin<UIO> implements HasCircle {

	public CircleMixin(final UIO uiObject) {
		super(uiObject);
	}

	@Override
	public void setCircle(boolean circle) {
		if (circle)
			uiObject.setBorderRadius("50%");
		else
			uiObject.setBorderRadius("0");
	}

}
