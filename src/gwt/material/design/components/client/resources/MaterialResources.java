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
package gwt.material.design.components.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource.MimeType;
import com.google.gwt.resources.client.TextResource;

/**
 * 
 * @author Richeli Vargas
 *
 */
public interface MaterialResources extends ClientBundle {
	MaterialResources INSTANCE = GWT.create(MaterialResources.class);

	@Source("js/jquery-3.3.1.min.js")
	TextResource jquery();
	
	@Source("js/resize-sensor.min.js")
	TextResource resizeSensor();
		
	@Source("js/vanilla-masker.min.js")
	TextResource jqueryMaskJs();
	
	@Source("js/material-components-web.js")
	TextResource materialComponentsWebJs();

	@Source("js/chartist/chartist.min.js")
	TextResource chartistJs();
	
	@Source("js/chartist/chartist-plugin-tooltip.min.js")
	TextResource chartistTooltipPluginJs();
	
	@Source("js/chartist/chartist-plugin-pointlabels.min.js")
	TextResource chartistPointLabelPluginJs();
	
	@Source("js/chartist/chartist-plugin-zoom.min.js")
	TextResource chartistZoomPluginJs();
	
	@Source("js/prism.min.js")
	TextResource prismJs();
	
	@Source("js/chroma.min.js")
	TextResource chromaJs();

	@Source("css/prism.min.css")
	TextResource prismCss();

	@Source("css/material-components-root.css")
	TextResource materialComponentsRootCss();
	
	@Source("css/mixin.css")
	TextResource mixinCss();
	
	@Source("css/material-components-web-addins.css")
	TextResource materialComponentsWebAddinsCss();

	@Source("css/material-components-web.min.css")
	TextResource materialComponentsWebCss();
	
	@Source("css/chartist/chartist.min.css")
	TextResource chartistCss();
	
	@Source("css/chartist/chartist-plugin-tooltip.min.css")
	TextResource chartistTooltipPluginCss();
	
	@Source("css/chartist/chartist-mixin.min.css")
	TextResource chartMixinCss();

	// ////////////////////////////////////////
	// Images
	// ////////////////////////////////////////
	@Source("image/mdc-checkbox__checkmark.svg")
	@MimeType("image/svg+xml")
	TextResource mdcCheckboxCheckmark();
	
	@Source("image/mdc-slider__thumb.svg")
	@MimeType("image/svg+xml")
	TextResource mdcSliderThumb();
	
	@Source("image/mdc-chip__checkmark.svg")
	@MimeType("image/svg+xml")
	TextResource mdcChipCheckmark();
	
	@Source("image/mdc-notched-outline.svg")
	@MimeType("image/svg+xml")
	TextResource mdcNotchedOutline();
	
	@Source("image/mdc-circular-progress__path.svg")
	@MimeType("image/svg+xml")
	TextResource mdcCircularProgressPath();
	
}
