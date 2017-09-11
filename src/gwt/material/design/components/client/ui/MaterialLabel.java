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
package gwt.material.design.components.client.ui;

import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Typography;
import gwt.material.design.components.client.ui.html.Label;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLabel extends Label {

	private boolean initialize = false;

	public MaterialLabel() {
		super(CssName.MDC_TYPOGRAPHY_ADJUST_MARGIN);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialize) {

			if (getTypography() == null) {
				setTypography(Typography.BODY_2);
			}

			getElement().appendChild(HtmlElements.BR.createElement());
			initialize = true;
		}
	}
}
