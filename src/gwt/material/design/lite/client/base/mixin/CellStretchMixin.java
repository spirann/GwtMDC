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
package gwt.material.design.lite.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

import gwt.material.design.lite.client.base.HasCellStretch;
import gwt.material.design.lite.client.constants.CssName;

/**
 * @author Richeli Vargas
 */
public class CellStretchMixin<T extends UIObject & HasCellStretch> extends AbstractMixin<T> implements HasCellStretch {

    public CellStretchMixin(final T widget) {
        super(widget);
    }

	@Override
	public void setStretch(boolean stretch) {
		uiObject.removeStyleName(CssName.MDL_CEL_STRETCH);
		if(stretch){
			uiObject.addStyleName(CssName.MDL_CEL_STRETCH);
		}
	}

}
