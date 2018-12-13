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

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.events.AddEvent;
import gwt.material.design.components.client.events.AddEvent.AddHandler;
import gwt.material.design.components.client.events.AddEvent.HasAddHandlers;
import gwt.material.design.components.client.events.ChangeEvent;
import gwt.material.design.components.client.events.ChangeEvent.ChangeHandler;
import gwt.material.design.components.client.events.ChangeEvent.HasChangeHandlers;
import gwt.material.design.components.client.events.DoneEvent;
import gwt.material.design.components.client.events.DoneEvent.DoneHandler;
import gwt.material.design.components.client.events.DoneEvent.HasDoneHandlers;
import gwt.material.design.components.client.events.StartEvent;
import gwt.material.design.components.client.events.StartEvent.HasStartHandlers;
import gwt.material.design.components.client.events.StartEvent.StartHandler;
import gwt.material.design.components.client.events.StopEvent;
import gwt.material.design.components.client.events.StopEvent.HasStopHandlers;
import gwt.material.design.components.client.events.StopEvent.StopHandler;
import gwt.material.design.components.client.ui.MaterialFileUpload.File;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsFileUploadData;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsFileUploadFile;
import gwt.material.design.components.client.utils.debug.Console;

/**
 * https://github.com/blueimp/jQuery-File-Upload/wiki/Basic-plugin
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialFileUpload extends Input
		implements HasStartHandlers, HasStopHandlers, HasChangeHandlers<Collection<File>>, HasAddHandlers<Collection<File>>, HasDoneHandlers<Collection<File>> {

	public MaterialFileUpload() {
		super(InputType.FILE);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		setAttribute("name", "filesToUpload[]");
		setAttribute("data-url", getAppContainer() + "/file_upload");
		setAttribute("multiple", "true");
		setAttribute("accept", ".png");
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

		var _this = this;

		var options = {
			autoUpload : true,
			formAcceptCharset : 'utf-8',
			maxNumberOfFiles : 2,
			multipart : true,
			singleFileUploads : false,
			sequentialUploads : false,
			//limitMultiFileUploadSizeOverhead : 512, // in bytes
			//limitMultiFileUploadSize : null,  // in bytes
			maxFileSize : 1024000,
			progressInterval : 100,
			bitrateInterval : 500,

			change : function(e, data) {

				console
						.log('Change ----------------------------------------------------');
				for ( var key in data) {
					//console.log(key);
				}

				var uploadErrors = validation(e, data);

				if (uploadErrors.length > 0) {
					alert(uploadErrors.join("\n"));
					return false;
				}

				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireDoneEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsFileUploadData;)(data);

			},
			add : function(e, data) {

				console
						.log('Add ----------------------------------------------------');
				for ( var key in data) {
					//console.log(key);
				}

				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::validateFiles(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsFileUploadData;)(data);

				var uploadErrors = validation(e, data);

				if (uploadErrors.length > 0) {
					alert(uploadErrors.join("\n"));
					return false;
				}

				data.submit();

				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireAddEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsFileUploadData;)(data);

			},
			start : function(e) {
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireStartEvent()();
			},
			drop : function(e, data) {
				printData('drop', data);
			},
			progress : function(e, data) {
				console.log('Progress ---------------------------------------------------- ' +  data.data.values);
				for ( var key in data.data.values) {
					console.log(key);
				}

				$wnd.jQuery.each(data.data, function(index, file) {
					for ( var key in file) {
						console.log(key);
					}
				});

				var progress = parseInt(data.loaded / data.total * 100, 10);
				console.log('progress: ' + progress + '%');
			},
			progressall : function(e, data) {
				console
						.log('Progressall ----------------------------------------------------');
				for ( var key in data) {
					//console.log(key);
				}
				var progress = parseInt(data.loaded / data.total * 100, 10);
				console.log('data.loaded: ' + data.loaded);
				console.log('data.total: ' + data.total);
				console.log('data.bitrate: ' + data.bitrate);
			},
			done : function(e, data) {
				console
						.log('Done ----------------------------------------------------');
				for ( var key in data) {
					//console.log(key);
				}
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireDoneEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsFileUploadData;)(data);
			},
			stop : function(e) {
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireStopEvent()();
			}

		};

		$wnd.jQuery(element).fileupload(options);

		var validation = function(e, data) {
			var messages = @gwt.material.design.components.client.resources.message.IMessages::INSTANCE;
			var uploadErrors = [];

			var maxFileSize;
			if (options.maxFileSize)
				maxFileSize = size_format(options.maxFileSize);

			if (options.maxNumberOfFiles
					&& data.files.length > options.maxNumberOfFiles)
				uploadErrors
						.push(messages.@gwt.material.design.components.client.resources.message.IMessages::mdc_file_upload__err__max_number_of_files_exceeded(I)(options.maxNumberOfFiles));

			$wnd.jQuery
					.each(
							data.originalFiles,
							function(index, file) {
								if (options.maxFileSize
										&& file.size > options.maxFileSize) {
									var fileSize = size_format(file.size);
									var message = messages.@gwt.material.design.components.client.resources.message.IMessages::mdc_file_upload__err__file_size_is_too_bg(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(maxFileSize, file.name, fileSize);
									uploadErrors.push(message);
								}
							});

			return uploadErrors;
		};

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

		var size_format = function bytesToSize(bytes) {
			var sizes = [ 'Bytes', 'KB', 'MB', 'GB', 'TB', 'PB' ];
			for (var i = 0; i < sizes.length; i++) {
				if (bytes <= 1024)
					return bytes + ' ' + sizes[i];
				else
					bytes = parseFloat(bytes / 1024).toFixed(2);
			}
			return bytes + ' PM';
		};

	}-*/;

	protected void fireStartEvent() {
		StartEvent.fire(MaterialFileUpload.this);
	}

	@Override
	public HandlerRegistration addStartHandler(final StartHandler handler) {
		return addHandler(handler, StartEvent.getType());
	}

	protected void fireStopEvent() {
		StopEvent.fire(MaterialFileUpload.this);
	}

	@Override
	public HandlerRegistration addStopHandler(final StopHandler handler) {
		return addHandler(handler, StopEvent.getType());
	}

	protected void fireChangeEvent(final JsFileUploadData data) {
		ChangeEvent.fire(MaterialFileUpload.this, listFiles(data));
	}

	@Override
	public HandlerRegistration addChangeHandler(final ChangeHandler<Collection<File>> handler) {
		return addHandler(handler, ChangeEvent.getType());
	}

	protected void fireAddEvent(final JsFileUploadData data) {
		AddEvent.fire(MaterialFileUpload.this, listFiles(data));
	}

	@Override
	public HandlerRegistration addAddHandler(final AddHandler<Collection<File>> handler) {
		return addHandler(handler, AddEvent.getType());
	}

	protected void fireDoneEvent(final JsFileUploadData data) {
		DoneEvent.fire(MaterialFileUpload.this, listFiles(data));
	}

	@Override
	public HandlerRegistration addDoneHandler(final DoneHandler<Collection<File>> handler) {
		return addHandler(handler, DoneEvent.getType());
	}

	protected Collection<File> listFiles(final JsFileUploadData data) {
		return Arrays.asList(data.files).stream().map(file -> toFile(file)).collect(Collectors.toList());
	}

	protected File toFile(final JsFileUploadFile jsFile) {
		return new File(jsFile.name, jsFile.lastModified, jsFile.lastModifiedDate, jsFile.webkitRelativePath, jsFile.size, jsFile.type);
	}

	protected String[] validateFiles(final JsFileUploadData data) {

		for (int index = 0; index < data.files.length; index++) {
			final JsFileUploadFile file = data.files[index];
			Console.log("funfo mesmo: " + file.lastModifiedDate);
		}

		return new String[] {};
	}

	/**
	 * accept file_extension|audio/*|video/*|image/*|media_type
	 * 
	 * <p>
	 * file_extension: A file extension starting with the STOP character, e.g: .gif,
	 * .jpg, .png, .doc
	 * </p>
	 * <p>
	 * audio/*: All sound files are accepted
	 * </p>
	 * <p>
	 * video/*: All video files are accepted
	 * </p>
	 * <p>
	 * image/*: All image files are accepted
	 * </p>
	 * <p>
	 * media_type: A valid media type, with no parameters. Look at IANA Media Types
	 * for a complete list of standard media types
	 * </p>
	 * 
	 * @param accept
	 */
	public void setAccept(final String accept) {

	}

	public static class File {

		private final String name;
		private final Long lastModified;
		private final Date lastModifiedDate;
		private final String webkitRelativePath;
		private final Integer size;
		private final String type;

		private File(String name, Long lastModified, Date lastModifiedDate, String webkitRelativePath, Integer size, String type) {
			this.name = name;
			this.lastModified = lastModified;
			this.lastModifiedDate = lastModifiedDate;
			this.webkitRelativePath = webkitRelativePath;
			this.size = size;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public Long getLastModified() {
			return lastModified;
		}

		public Date getLastModifiedDate() {
			return lastModifiedDate;
		}

		public String getWebkitRelativePath() {
			return webkitRelativePath;
		}

		public Integer getSize() {
			return size;
		}

		public String getType() {
			return type;
		}

	}

}
