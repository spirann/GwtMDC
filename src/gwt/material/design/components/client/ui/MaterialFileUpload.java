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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.ui.html.Input;

/**
 * https://github.com/blueimp/jQuery-File-Upload/wiki/Basic-plugin
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialFileUpload extends Input {

	public MaterialFileUpload() {
		super(InputType.FILE);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		setAttribute("name", "files[]");
		setAttribute("data-url", getAppContainer() + "/file_upload");
		setAttribute("multiple", "");
		init(getElement());
	}

	private String appContainer = null;

	public String getAppContainer() {

		if (appContainer == null) {
			final String[] str = GWT.getModuleBaseURL().split("/");

			if (str.length > 4) {
				appContainer = "/" + str[3];
			} else {
				appContainer = "";
			}
		}

		return appContainer;
	}

	native void init(Element element) /*-{

		var printData = function(method, data) {

			console.log(method + ' -------------------------------------');
			console.log('fileInput: ' + data.fileInput);
			console.log('form: ' + data.form);
			console.log('files: ' + data.files);
			console.log('fileInputClone: ' + data.fileInputClone);
			console.log('originalFiles: ' + data.originalFiles);
			console.log('paramName: ' + data.paramName);
			console.log('_response: ' + data._response);
			console.log('_progress: ' + data._progress);
			// Functions			
			// console.log('process: ' + data.process);
			// console.log('submit: ' + data.submit);
			// console.log('abort: ' + data.abort);
			// console.log('submit: ' + data.submit);
			// console.log('state: ' + data.state);
			// console.log('processing: ' + data.processing);
			// console.log('progress: ' + data.progress);
			// console.log('response: ' + data.response);

			$wnd.jQuery.each(data.files, function(index, file) {

				// Attributes
				console.log('		-------------------------------------');
				console.log('name: ' + file.name);
				console.log('lastModified: ' + file.lastModified);
				console.log('lastModifiedDate: ' + file.lastModifiedDate);
				console.log('webkitRelativePath: ' + file.webkitRelativePath);
				console.log('size: ' + file.size);
				console.log('type: ' + file.type);
				console.log('slice: ' + file.slice);
			});

		};

		$wnd.jQuery(element).fileupload({
			formAcceptCharset : 'utf-8',
			maxNumberOfFiles : 2,
			multipart : true,
			singleFileUploads : false,
			sequentialUploads : true,
			limitMultiFileUploads : 3,
			//limitConcurrentUploads : 3,
			//limitMultiFileUploadSizeOverhead : 512, // in bytes
			// limitMultiFileUploadSize : undefined,  // in bytes

			add : function(e, data) {
				printData('add', data);
				data.submit();
			},
			start : function(e) {
				console.log('start');
			},
			stop : function(e) {
				console.log('stop');
			},
			drop : function(e, data) {
				printData('drop', data);
			},
			done : function(e, data) {
				printData('done', data);
			},
			progress : function(e, data) {
				var progress = parseInt(data.loaded / data.total * 100, 10);
				console.log('progress: ' + progress + '%');
			},
			progressall : function(e, data) {
				var progress = parseInt(data.loaded / data.total * 100, 10);
				console.log('progressall: ' + progress + '%');
			},
			change : function(e, data) {
				printData('change', data);
			}

		});

	}-*/;

}
