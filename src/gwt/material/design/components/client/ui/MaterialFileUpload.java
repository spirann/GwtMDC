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

		$wnd.jQuery(element).fileupload(
				{
					sequentialUploads : true,
					dataType : 'png',
					add : function(e, data) {
						$wnd.jQuery.each(data.files, function(index, file) {
							console.log('add: ' + file.name);
						});
						data.submit();
					},
					done : function(e, data) {
						$wnd.jQuery.each(data.result.files,
								function(index, file) {
									console.log('done: ' + file.name);
								});
					},
					progress : function(e, data) {
						var progress = parseInt(data.loaded / data.total * 100, 10);
						console.log('progress: ' + progress + '%');
					},
					progressall : function(e, data) {
						var progress = parseInt(data.loaded / data.total * 100, 10);
						console.log('progressall: ' + progress + '%');
					},
					progressall : function(e, data) {
						var progress = parseInt(data.loaded / data.total * 100,
								10);
						console.log('progressall: ' + progress + '%');
					},
					drop : function(e, data) {
						$wnd.jQuery.each(data.files, function(index, file) {
							console.log('drop: ' + file.name);
						});
					},
					change : function(e, data) {
						$wnd.jQuery.each(data.files, function(index, file) {
							console.log('change: ' + file.name);
						});
					}

				});

	}-*/;

}
