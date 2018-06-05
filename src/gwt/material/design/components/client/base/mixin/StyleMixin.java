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

/**
 * @author Richeli Vargas
 */
public class StyleMixin<H extends Widget> extends AbstractMixin<H> {

	private String style;

	public StyleMixin(final H widget) {
		super(widget);
	}

	public void setStyle(String styles) {
		
		if (this.style != null && !this.style.isEmpty()) {
			for (String style : this.style.split(" ")) {
				uiObject.removeStyleName(style);
			}
		}
		
		this.style = styles;

		if (styles != null && !styles.isEmpty()) {
			for (String style : styles.split(" ")) {
				uiObject.addStyleName(style);
			}
		}
		
	}

	public String getStyle() {
		return style;
	}
}
