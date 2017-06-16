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
package gwt.material.design.components.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface MaterialResources extends ClientBundle {
	MaterialResources INSTANCE = GWT.create(MaterialResources.class);

	@Source("js/jquery-2.1.1.min.js")
	TextResource jqueryJs();
	
	@Source("js/google_analytics.js")
	TextResource googleAnalyticsJs();

	@Source("js/material-components-web.min.js")
	TextResource materialComponentsWebJs();
	
	@Source("js/prism.js")
	TextResource prismJs();
	
	@Source("css/prism.css")
	TextResource prismCss();
	
	@Source("css/addins.css")
	TextResource addinsCss();
	
	@Source("css/material-components-web.min.css")
	TextResource materialComponentsWebCss();
	
}
