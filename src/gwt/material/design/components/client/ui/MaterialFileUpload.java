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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasFired;
import gwt.material.design.components.client.base.mixin.FiredMixin;
import gwt.material.design.components.client.constants.InputType;
import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.events.AbortEvent;
import gwt.material.design.components.client.events.AbortEvent.AbortHandler;
import gwt.material.design.components.client.events.AbortEvent.HasAbortHandlers;
import gwt.material.design.components.client.events.AddEvent;
import gwt.material.design.components.client.events.AddEvent.AddHandler;
import gwt.material.design.components.client.events.AddEvent.HasAddHandlers;
import gwt.material.design.components.client.events.ChangeEvent;
import gwt.material.design.components.client.events.ChangeEvent.ChangeHandler;
import gwt.material.design.components.client.events.ChangeEvent.HasChangeHandlers;
import gwt.material.design.components.client.events.DoneEvent;
import gwt.material.design.components.client.events.DoneEvent.DoneHandler;
import gwt.material.design.components.client.events.DoneEvent.HasDoneHandlers;
import gwt.material.design.components.client.events.ErrorEvent;
import gwt.material.design.components.client.events.ErrorEvent.ErrorHandler;
import gwt.material.design.components.client.events.ErrorEvent.HasErrorHandlers;
import gwt.material.design.components.client.events.ProgressEvent;
import gwt.material.design.components.client.events.ProgressEvent.HasProgressHandlers;
import gwt.material.design.components.client.events.ProgressEvent.ProgressHandler;
import gwt.material.design.components.client.events.StartEvent;
import gwt.material.design.components.client.events.StartEvent.HasStartHandlers;
import gwt.material.design.components.client.events.StartEvent.StartHandler;
import gwt.material.design.components.client.events.StopEvent;
import gwt.material.design.components.client.events.StopEvent.HasStopHandlers;
import gwt.material.design.components.client.events.StopEvent.StopHandler;
import gwt.material.design.components.client.ui.MaterialFileUpload.File;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsData;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsFile;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsOptions;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsProgressData;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.utils.helper.PrimitiveHelper;
import gwt.material.design.components.client.validation.FileUploadValidation;
import gwt.material.design.components.client.validation.Validation.Result;

/**
 * https://github.com/blueimp/jQuery-File-Upload/wiki/Basic-plugin
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialFileUpload extends Input
		implements HasFired, HasStartHandlers, HasStopHandlers, HasChangeHandlers<Collection<File>>, HasAddHandlers<Collection<MaterialFileUpload.File>>,
		HasDoneHandlers<MaterialFileUpload.Data>, HasErrorHandlers<MaterialFileUpload.Data>, HasProgressHandlers<Collection<File>>, HasAbortHandlers<MaterialFileUpload.Data> {

	protected final JsOptions options = new JsOptions();

	private JsData data;
	private FileUploadValidation validation;
	
	private FiredMixin<MaterialFileUpload> firedMixin = new FiredMixin<MaterialFileUpload>(this, () -> fire());

	public MaterialFileUpload() {
		super(InputType.FILE);
		loadDefaultOptions();
	}

	protected void loadDefaultOptions() {
		options.accept = null;
		options.url = null;
		options.type = "POST";
		options.dataType = "text";
		options.dropZone = null;
		options.autoUpload = true;
		options.formAcceptCharset = "utf-8";
		options.multipart = true;
		options.singleFileUploads = true;
		options.sequentialUploads = false;
		options.limitConcurrentUploads = null;
		options.maxNumberOfFiles = null;
		options.limitMultiFileUploadSize = null;
		options.maxFileSize = null;
		options.progressInterval = 100;
		options.bitrateInterval = 500;
	}

	protected void layout() {
		if (initialized)
			jsInit();
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{

		var appContainer = @gwt.material.design.components.client.utils.helper.HttpHelper::getAppContainer()();
		var _this = this;
		var options = this.@gwt.material.design.components.client.ui.MaterialFileUpload::options;

		if ($wnd.jQuery(element).attr("name"))
			$wnd.jQuery(element).attr("name", "files[]");

		if (options.url && options.url.indexOf("http") == -1)
			$wnd.jQuery(element).attr("data-url", appContainer + options.url);
		else
			$wnd.jQuery(element).attr("data-url", options.url);

		$wnd.jQuery(element).attr("multiple", !options.singleFileUploads);
		$wnd.jQuery(element).attr("accept", options.accept);

		options.change = function(e, data) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireChangeEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);
		};

		options.add = function(e, data) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::data = data;
			var validate = _this.@gwt.material.design.components.client.ui.MaterialFileUpload::validate(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);

			if (validate) {
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireAddEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);
			} else {
				data.abort();
			}

			return validate;
		};

		options.start = function(e) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireStartEvent()();
		};

		options.drop = function(e, data) {
			printData('drop', data);
		};

		options.progress = function(e, data) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireProgressEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsProgressData;)(data);
		};

		options.done = function(e, data) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireDoneEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);
		};

		options.stop = function(e) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::data = null;
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireStopEvent()();
		};

		options.fail = function(e, data) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::data = null;
			if (data.errorThrown === 'abort')
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireAbortEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);
			else
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireErrorEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;ILjava/lang/String;)(data, 0, data.errorThrown);
		};

		return $wnd.jQuery(element).fileupload(options);

	}-*/;

	protected boolean validate(final JsData jsData) {

		final Result defaultResult = FileUploadValidation.Defaults.default_validation().validate(this, toData(jsData).getFiles());

		boolean error = defaultResult.getState().equals(State.ERROR);

		if (error)
			fireErrorEvent(jsData, defaultResult.getCode(), defaultResult.getMessage());
		else {

			if (validation == null)
				return true;

			final Result result = validation.validate(this, toData(jsData).getFiles());
			error = result.getState().equals(State.ERROR);

			if (error)
				fireErrorEvent(jsData, result.getCode(), result.getMessage());
		}

		return !error;
	}

	public void submit() {
		if (data != null)
			data.submit();
	}

	public void abort() {
		if (data != null)
			data.abort();
	}

	public void fire() {
		JsHelper.doClick(getElement());
	}

	@Override
	public void setFired(String firedId) {
		firedMixin.setFired(firedId);
	}

	@Override
	public String getFired() {
		return firedMixin.getFired();
	}
	
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

	protected void fireChangeEvent(final JsData data) {
		ChangeEvent.fire(MaterialFileUpload.this, listFiles(data));
	}

	@Override
	public HandlerRegistration addChangeHandler(final ChangeHandler<Collection<File>> handler) {
		return addHandler(handler, ChangeEvent.getType());
	}

	protected void fireAddEvent(final JsData data) {
		AddEvent.fire(MaterialFileUpload.this, listFiles(data));
	}

	@Override
	public HandlerRegistration addAddHandler(final AddHandler<Collection<File>> handler) {
		return addHandler(handler, AddEvent.getType());
	}

	protected void fireDoneEvent(final JsData data) {
		DoneEvent.fire(MaterialFileUpload.this, toData(data));
	}

	@Override
	public HandlerRegistration addDoneHandler(final DoneHandler<Data> handler) {
		return addHandler(handler, DoneEvent.getType());
	}

	protected void fireProgressEvent(final JsProgressData jsData) {
		ProgressEvent.fire(MaterialFileUpload.this, Arrays.asList(jsData.files).stream().map(file -> toFile(file)).collect(Collectors.toList()), jsData.loaded, jsData.total);
	}

	@Override
	public HandlerRegistration addProgressHandler(final ProgressHandler<Collection<File>> handler) {
		return addHandler(handler, ProgressEvent.getType());
	}

	protected void fireAbortEvent(final JsData data) {
		AbortEvent.fire(MaterialFileUpload.this, toData(data));
	}

	@Override
	public HandlerRegistration addAbortHandler(final AbortHandler<Data> handler) {
		return addHandler(handler, AbortEvent.getType());
	}

	protected void fireErrorEvent(final JsData data, final int code, final String description) {
		ErrorEvent.fire(MaterialFileUpload.this, toData(data), code, description);
	}

	@Override
	public HandlerRegistration addErrorHandler(final ErrorHandler<Data> handler) {
		return addHandler(handler, ErrorEvent.getType());
	}

	public void setUrl(final String url) {
		options.url = url;
		layout();
	}

	public String getUrl() {
		return options.url;
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
		options.accept = accept;
		layout();
	}

	public String getAccept() {
		return options.accept;
	}

	/**
	 * The HTTP request method for the file uploads. Can be "POST", "PUT" or "PATCH"
	 * and defaults to "POST".<br/>
	 * 
	 * Type: string<br/>
	 * Example: 'PUT'
	 * 
	 * <p>
	 * <strong>Note:<strong> "PUT" and "PATCH" are only supported by browser
	 * supporting XHR file uploads, as iframe transport uploads rely on standard
	 * HTML forms which only support "POST" file uploads. See Browser support. If
	 * the type is defined as "PUT" or "PATCH", the iframe transport will send the
	 * files via "POST" and transfer the original method as "_method" URL parameter.
	 * </p>
	 * <p>
	 * <strong>Note:<strong> As was noted above, it's a common practice to use
	 * "_method" to transfer the type of your request. For example, "Ruby on Rails"
	 * framework uses a hidden input with the name "_method" within each form, so it
	 * will likely override the value that you will set here.
	 * </p>
	 */
	public void setHttpRequestMethod(final String type) {
		options.type = type;
		layout();
	}

	public String getHttpRequestMethod() {
		return options.type;
	}

	/**
	 * The type of data that is expected back from the server.
	 * 
	 * <p>
	 * <strong>Note:<strong> The UI version of the File Upload plugin sets this
	 * option to "json" by default.
	 * </p>
	 * Type: string<br/>
	 * Example: 'json'
	 */
	public void setDataType(final String dataType) {
		options.dataType = dataType;
		layout();
	}

	public String getDataType() {
		return options.dataType;
	}

	/**
	 * The drop target jQuery object, by default the complete document. Set to null
	 * or an empty jQuery collection to disable drag & drop support:
	 * <p>
	 * Type: jQuery Object Default: $(document) Note: If you want to allow specific
	 * drop zones but disable the default browser action for file drops on the
	 * document, add the following JavaScript code:
	 * </p>
	 * $(document).bind('drop dragover', function (e) { e.preventDefault(); }); Note
	 * that preventing the default action for both the "drop" and "dragover" events
	 * is required to disable the default browser drop action.
	 */
	public void setDropZone(final Element dropZone) {
		options.dropZone = JsHelper.toJQueryObject(dropZone);
		layout();
	}

	public void setDropZone(final Widget dropZone) {
		options.dropZone = JsHelper.toJQueryObject(dropZone);
		layout();
	}

	public void setDropZone(final String dropZone) {
		options.dropZone = JsHelper.toJQueryObject(dropZone);
		layout();
	}

	/**
	 * By default, files added to the widget are uploaded as soon as the user clicks
	 * on the start buttons. To enable automatic uploads, set this option to true.
	 * 
	 * Type: boolean <br/>
	 * Default: true
	 * 
	 * <p>
	 * Please note that in the basic File Upload plugin, this option is set to true
	 * by default.
	 * </p>
	 */
	public void setAutoUpload(final boolean autoUpload) {
		options.autoUpload = autoUpload;
		layout();
	}

	public boolean isAutoUpload() {
		return options.autoUpload;
	}

	/**
	 * Allows to set the accept-charset attribute for the iframe upload forms. If
	 * formAcceptCharset is not set, the accept-charset attribute of the file upload
	 * widget form is used, if available.<br/>
	 * 
	 * Type: string<br/>
	 * Example: 'utf-8'
	 */
	public void setFormAcceptCharset(final String formAcceptCharset) {
		options.formAcceptCharset = formAcceptCharset;
		layout();
	}

	public String getFormAcceptCharset() {
		return options.formAcceptCharset;
	}

	/**
	 * By default, XHR file uploads are sent as multipart/form-data. The iframe
	 * transport is always using multipart/form-data. If this option is set to
	 * false, the file content is streamed to the server url instead of sending a
	 * RFC 2388 multipart message for XMLHttpRequest file uploads. Non-multipart
	 * uploads are also referred to as HTTP PUT file upload.
	 * 
	 * <p>
	 * <strong>Note:<strong> Additional form data is ignored when the multipart
	 * option is set to false. Non-multipart uploads (multipart: false) are broken
	 * in Safari 5.1 - see issue #700.
	 * </p>
	 * 
	 * Type: boolean <br/>
	 * Default: true
	 */
	public void setMultipart(final boolean multipart) {
		options.multipart = multipart;
		layout();
	}

	public boolean isMultipart() {
		return options.multipart;
	}

	/**
	 * By default, each file of a selection is uploaded using an individual request
	 * for XHR type uploads. Set this option to false to upload file selections in
	 * one request each.
	 * <p>
	 * <strong>Note:<strong> Uploading multiple files with one request requires the
	 * multipart option to be set to true (the default).
	 * </p>
	 * Type: boolean Default: true
	 */
	public void setSingleFileUploads(final boolean singleFileUploads) {
		options.singleFileUploads = singleFileUploads;
		layout();
	}

	public boolean isSingleFileUploads() {
		return options.singleFileUploads;
	}

	/**
	 * Set this option to true to issue all file upload requests in a sequential
	 * order instead of simultaneous requests.<br/>
	 * 
	 * Type: boolean<br/>
	 * Default: false
	 */
	public void setSequentialUploads(final boolean sequentialUploads) {
		options.sequentialUploads = sequentialUploads;
		layout();
	}

	public boolean isSequentialUploads() {
		return options.sequentialUploads;
	}

	/**
	 * To limit the number of concurrent uploads, set this option to an integer
	 * value greater than 0.
	 * 
	 * Type: integer <br/>
	 * Default: undefined <br/>
	 * Example: 3 <br/>
	 * 
	 * <p>
	 * <strong>Note:<strong> This option is ignored, if sequentialUploads is set to
	 * true.
	 * </p>
	 */
	public void setLimitConcurrentUploads(final Integer limitConcurrentUploads) {
		options.limitConcurrentUploads = limitConcurrentUploads;
		layout();
	}

	public Integer getLimitConcurrentUploads() {
		return options.limitConcurrentUploads;
	}

	/**
	 * This option limits the number of files that are allowed to be uploaded using
	 * this widget. By default, unlimited file uploads are allowed.<br/>
	 * 
	 * Type: integer <br/>
	 * Example: 10
	 * 
	 * <p>
	 * <strong>Note:<strong> The maxNumberOfFiles option depends on the
	 * getNumberOfFiles option, which is defined by the UI and AngularJS
	 * implementations.
	 * </p>
	 */
	public void setMaxNumberOfFiles(final Integer maxNumberOfFiles) {
		options.maxNumberOfFiles = maxNumberOfFiles;
		layout();
	}

	public Integer getMaxNumberOfFiles() {
		return options.maxNumberOfFiles;
	}

	/**
	 * The following option limits the number of files uploaded with one XHR request
	 * to keep the request size under or equal to the defined limit in bytes:<br/>
	 * 
	 * Type: integer Default: undefined <br/>
	 * Example: 1000000
	 * <p>
	 * <strong>Note:<strong> This option is ignored, if singleFileUploads is set to
	 * true.
	 * </p>
	 */
	public void setLimitMultiFileUploadSize(final Integer limitMultiFileUploadSize) {
		options.limitMultiFileUploadSize = limitMultiFileUploadSize;
		layout();
	}

	public Integer getLimitMultiFileUploadSize() {
		return options.limitMultiFileUploadSize;
	}

	/**
	 * The maximum allowed file size in bytes.<br/>
	 * 
	 * Type: integer <br/>
	 * Default: undefined <br/>
	 * Example: 10000000 // 10 MB
	 */
	public void setMaxFileSize(final Integer maxFileSize) {
		options.maxFileSize = maxFileSize;
		layout();
	}

	public Integer getMaxFileSize() {
		return options.maxFileSize;
	}

	/**
	 * The minimum time interval in milliseconds to calculate and trigger progress
	 * events.<br/>
	 * 
	 * Type: integer <br/>
	 * Default: 100
	 */
	public void setProgressInterval(final int progressInterval) {
		options.progressInterval = progressInterval;
		layout();
	}

	public int getProgressInterval() {
		return options.progressInterval;
	}

	/**
	 * The minimum time interval in milliseconds to calculate progress bitrate.<br/>
	 * 
	 * Type: integer <br/>
	 * Default: 500
	 */
	public void setBitrateInterval(final int bitrateInterval) {
		options.bitrateInterval = bitrateInterval;
		layout();
	}

	public int getBitrateInterval() {
		return options.bitrateInterval;
	}

	// ////////////////////////////////////////////////////////////////
	// Utility methods
	// ////////////////////////////////////////////////////////////////

	protected Collection<File> listFiles(final JsData jsData) {
		return toData(jsData).getFiles();
	}

	protected Data toData(final JsData jsData) {
		return new Data(jsData._response, jsData.loaded, jsData.total, jsData.uploadedBytes,
				Arrays.asList(jsData.files).stream().map(file -> toFile(file)).collect(Collectors.toList()));
	}

	protected File toFile(final JsFile jsFile) {
		return new File(jsFile.name, jsFile.lastModified, jsFile.lastModifiedDate, jsFile.webkitRelativePath, jsFile.size, jsFile.type);
	}

	// ////////////////////////////////////////////////////////////////
	// Inner classes
	// ////////////////////////////////////////////////////////////////

	public static class Data {

		private final String response;

		private final int loaded;

		private final int total;

		private final int uploadedBytes;

		private final Collection<File> files;

		public Data(String response, int loaded, int total, int uploadedBytes, Collection<File> files) {
			super();
			this.response = response;
			this.loaded = PrimitiveHelper.noNull(loaded);
			this.total = PrimitiveHelper.noNull(total);
			this.uploadedBytes = PrimitiveHelper.noNull(uploadedBytes);
			this.files = files;
		}

		public String getResponse() {
			return response;
		}

		public long getLoaded() {
			return loaded;
		}

		public long getTotal() {
			return total;
		}

		public long getUploadedBytes() {
			return uploadedBytes;
		}

		public Collection<File> getFiles() {
			return files;
		}

	}

	public static class File {

		private final String name;
		private final long lastModified;
		private final Date lastModifiedDate;
		private final String webkitRelativePath;
		private final int size;
		private final String type;

		private File(String name, int lastModified, Date lastModifiedDate, String webkitRelativePath, int size, String type) {
			this.name = name;
			this.lastModified = PrimitiveHelper.noNull(lastModified) * 1000L;
			this.lastModifiedDate = lastModifiedDate;
			this.webkitRelativePath = webkitRelativePath;
			this.size = PrimitiveHelper.noNull(size);
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public long getLastModified() {
			return lastModified;
		}

		public Date getLastModifiedDate() {
			return lastModifiedDate;
		}

		public String getWebkitRelativePath() {
			return webkitRelativePath;
		}

		public int getSize() {
			return size;
		}

		public String getType() {
			return type;
		}

	}

}
