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

	// ////////////////////////////////////////
	// CSS attributes
	// ////////////////////////////////////////

	@Source("css/material-attr--theme.css")
	TextResource themeAttributesCss();

	@Source("css/material-attr--components.css")
	TextResource componentsAttributesCss();

	// ////////////////////////////////////////
	// JQuery
	// ////////////////////////////////////////

	@Source("js/jquery-3.3.1.min.js")
	TextResource jquery();

	// ////////////////////////////////////////
	// Google Material Compoentes Web
	// ////////////////////////////////////////

	@Source("js/material-components-web.min.js")
	TextResource materialComponentsWebJs();

	@Source("css/material-components-web.min.css")
	TextResource materialComponentsWebCss();

	// ////////////////////////////////////////
	// GWT MDC Compoentes
	// ////////////////////////////////////////
	
	@Source("js/mdc_tree.js")
	TextResource mdcTreeJs();

	@Source("css/material-components-root.css")
	TextResource materialComponentsRootCss();

	@Source("css/mixin.css")
	TextResource mixinCss();

	@Source("css/material-components-web-addins.css")
	TextResource materialComponentsWebAddinsCss();

	// ////////////////////////////////////////
	// Chroma - Colors utils
	// ////////////////////////////////////////

	@Source("js/chroma.min.js")
	TextResource chromaJs();

	// ////////////////////////////////////////
	// Vanilla Masker
	// ////////////////////////////////////////

	@Source("js/vanilla-masker.min.js")
	TextResource jqueryMaskJs();

	// ////////////////////////////////////////
	// Resize Sensor
	// ////////////////////////////////////////

	@Source("js/resize-sensor.min.js")
	TextResource resizeSensor();

	// ////////////////////////////////////////
	// Prism
	// ////////////////////////////////////////

	@Source("js/prism.min.js")
	TextResource prismJs();

	@Source("css/prism.min.css")
	TextResource prismCss();

	// ////////////////////////////////////////
	// File Upload
	// ////////////////////////////////////////

	@Source("js/fileUpload/jquery.fileupload.js")
	TextResource fileUploadJs();

	@Source("js/fileUpload/jquery.iframe-transport.js")
	TextResource fileUploadIframeTransportJs();

	@Source("js/fileUpload/jquery.ui.widget.js")
	TextResource fileUploadWidgetJs();

	// ////////////////////////////////////////
	// Chartist
	// ////////////////////////////////////////

	@Source("js/chartist/chartist.min.js")
	TextResource chartistJs();

	@Source("js/chartist/chartist-plugin-tooltip.min.js")
	TextResource chartistTooltipPluginJs();

	@Source("js/chartist/chartist-plugin-pointlabels.min.js")
	TextResource chartistPointLabelPluginJs();

	@Source("js/chartist/chartist-plugin-zoom.min.js")
	TextResource chartistZoomPluginJs();

	@Source("css/chartist/chartist.min.css")
	TextResource chartistCss();

	@Source("css/chartist/chartist-plugin-tooltip.min.css")
	TextResource chartistTooltipPluginCss();

	@Source("css/chartist/chartist-mixin.min.css")
	TextResource chartMixinCss();

	// ////////////////////////////////////////
	// JExcel
	// ////////////////////////////////////////

	@Source("js/jexcel/numeral.min.js")
	TextResource jExcelNumeralJs();

	@Source("js/jexcel/excel-formula.min.js")
	TextResource jExcelFormulaJs();

	@Source("js/jexcel/jquery.csv.min.js")
	TextResource jExcelCsvJs();

	@Source("js/jexcel/jquery.jcalendar.js")
	TextResource jExcelCalendarJs();

	@Source("js/jexcel/jquery.jdropdown.js")
	TextResource jExcelDropdownJs();

	@Source("js/jexcel/jquery.jexcel.js")
	TextResource jExcelJs();

	@Source("css/jexcel/jquery.jcalendar.css")
	TextResource jExcelCalendarCss();

	@Source("css/jexcel/jquery.jdropdown.css")
	TextResource jExcelDropdownCss();

	@Source("css/jexcel/jquery.jexcel.bootstrap.css")
	TextResource jExcelBootstrapCss();

	@Source("css/jexcel/jquery.jexcel.css")
	TextResource jExcelCss();

	@Source("css/jexcel/jquery.jexcel.green.css")
	TextResource jExcelGreenCss();

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

	@Source("image/mdc-circular-progress__path.svg")
	@MimeType("image/svg+xml")
	TextResource mdcCircularProgressPath();

}
