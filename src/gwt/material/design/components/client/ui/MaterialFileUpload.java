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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.interfaces.HasFired;
import gwt.material.design.components.client.base.interfaces.HasValidation;
import gwt.material.design.components.client.base.mixin.FiredMixin;
import gwt.material.design.components.client.base.mixin.ValidationMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.FileType;
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
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialFileUpload.File;
import gwt.material.design.components.client.ui.html.Input;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsData;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsFile;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsOptions;
import gwt.material.design.components.client.ui.misc.fileUpload.js.JsProgressData;
import gwt.material.design.components.client.utils.helper.Formatation;
import gwt.material.design.components.client.utils.helper.JsHelper;
import gwt.material.design.components.client.utils.helper.PrimitiveHelper;
import gwt.material.design.components.client.utils.helper.UiHelper;
import gwt.material.design.components.client.validation.FileUploadValidation;
import gwt.material.design.components.client.validation.Validation.Result;
import gwt.material.design.components.client.validation.ValidationRegistration;

/**
 * https://github.com/blueimp/jQuery-File-Upload/wiki/Basic-plugin
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialFileUpload extends Input
		implements HasFired, HasStartHandlers, HasStopHandlers, HasValidation<MaterialFileUpload, FileUploadValidation>,
		HasChangeHandlers<Collection<File>>, HasAddHandlers<Collection<MaterialFileUpload.File>>,
		HasDoneHandlers<MaterialFileUpload.Data>, HasErrorHandlers<MaterialFileUpload.Data>,
		HasProgressHandlers<Collection<File>>, HasAbortHandlers<MaterialFileUpload.Data> {

	protected final JsOptions options = new JsOptions();
	protected final List<JsFile> files = new LinkedList<>();
	protected Collection<File> cacheFiles;

	protected final FiredMixin<MaterialFileUpload> firedMixin = new FiredMixin<MaterialFileUpload>(this, () -> fire());
	protected final ValidationMixin<MaterialFileUpload, FileUploadValidation> validationMixin = new ValidationMixin<>(
			this);

	public MaterialFileUpload() {
		super(InputType.FILE, CssName.MDC_FILE_UPLOAD);
		loadDefaultOptions();
	}

	protected void loadDefaultOptions() {
		options.accept = null;
		options.url = null;
		options.type = "POST";
		options.dataType = "text";
		options.dropZone = null;
		options.autoUpload = false;
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

		addValidation((source) -> {

			if (cacheFiles == null)
				return new Result(State.NONE);

			if (!source.isSingleFileUploads()) {
				final Integer maxNumberOfFiles = source.getMaxNumberOfFiles();
				if (maxNumberOfFiles != null && cacheFiles.size() > maxNumberOfFiles)
					return new Result(State.ERROR, 2201,
							IMessages.INSTANCE.mdc_file_upload__err__max_number_of_files_exceeded(maxNumberOfFiles));

				final int totalSize = cacheFiles.stream().mapToInt(f -> f.getSize()).sum();
				final Integer limitMultiFileUploadSize = source.getLimitMultiFileUploadSize();
				if (limitMultiFileUploadSize != null && totalSize > limitMultiFileUploadSize)
					return new Result(State.ERROR, 2202,
							IMessages.INSTANCE.mdc_file_upload__err__max_limit_multi_upload_size_exceeded(
									Formatation.bytes(limitMultiFileUploadSize), Formatation.bytes(totalSize)));

			}

			final Integer maxFileSize = source.getMaxFileSize();
			if (maxFileSize != null)
				for (MaterialFileUpload.File file : cacheFiles)
					if (file.getSize() > maxFileSize)
						return new Result(State.ERROR, 2203,
								IMessages.INSTANCE.mdc_file_upload__err__file_size_is_too_bg(
										Formatation.bytes(maxFileSize), file.getName(),
										Formatation.bytes(file.getSize())));

			return new Result(State.NONE);

		});
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

		options.replaceFileInput = false;

		if (options.dropZoneId) {
			var elementId = @gwt.material.design.components.client.utils.helper.JsHelper::concatToId(Ljava/lang/String;)(options.dropZoneId);
			options.dropZone = $wnd.jQuery(elementId);
		}

		if (options.dropZone)
			$wnd.jQuery(options.dropZone).bind('drop dragover', function(e) {
				e.preventDefault();
			});

		options.pasteZone = options.dropZone;

		options.change = function(e, data) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireChangeEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);
		};

		options.add = function(e, data) {

			var validate = _this.@gwt.material.design.components.client.ui.MaterialFileUpload::validate(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);

			if (validate) {
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::addFiles([Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsFile;)(data.files);
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireAddEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);

				if (options.autoUpload)
					data.submit();

			} else
				data.abort();

			return validate;
		};

		options.start = function(e) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireStartEvent()();
		};

		options.drop = function(e, data) {
			console.log('drop');
			for ( var v in data)
				console.log(v);
		};

		options.progress = function(e, data) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireProgressEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsProgressData;)(data);
		};

		options.done = function(e, data) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireDoneEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);
		};

		options.stop = function(e) {
			_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireStopEvent()();
		};

		options.fail = function(e, data) {
			if (data.errorThrown === 'abort')
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireAbortEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;)(data);
			else
				_this.@gwt.material.design.components.client.ui.MaterialFileUpload::fireErrorEvent(Lgwt/material/design/components/client/ui/misc/fileUpload/js/JsData;ILjava/lang/String;)(data, 0, data.errorThrown);
		};

		return $wnd.jQuery(element).fileupload(options);

	}-*/;

	protected boolean validate(final JsData jsData) {
		cacheFiles = this.listFiles(jsData);
		final Collection<Result> erros = ValidationMixin.filterResults(validate(), State.ERROR);
		erros.stream().forEach(result -> fireErrorEvent(jsData, result.getCode(), result.getMessage()));
		return erros.isEmpty();
	}

	@Override
	public ValidationRegistration addValidation(FileUploadValidation validation) {
		return validationMixin.addValidation(validation);
	}

	@Override
	public Collection<Result> validate() {
		return validationMixin.validate();
	}

	protected void addFiles(final JsFile[] files) {
		if (isSingleFileUploads())
			this.files.clear();
		this.files.addAll(Arrays.asList(files));
	}

	public List<File> getFiles() {
		return files.stream().map(file -> toFile(file)).collect(Collectors.toList());
	}

	public void removeFile(final File file) {
		files.removeIf(jsFile -> File.toString(jsFile).equals(file.toString()));
	}

	public void removeFiles() {
		files.clear();
	}

	public UploadRequest submit() {
		return new UploadRequest(submit(files.stream().toArray(JsFile[]::new)));
	}

	public UploadRequest submit(final File[] files) {
		final JsFile[] jsFiles = Arrays.asList(files).stream().map(file -> file.toNative()).toArray(JsFile[]::new);
		return new UploadRequest(submit(jsFiles));
	}

	protected native JavaScriptObject submit(final JsFile[] files) /*-{
		var element = this.@gwt.material.design.components.client.ui.MaterialFileUpload::getElement()();
		return $wnd.jQuery(element).fileupload('send', {
			files : files
		// , formData: {param_1: 'test 1', param_2: 'test 2'}
		});
	}-*/;

	public void fire() {
		UiHelper.fireClickEvent(getElement());
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
		ProgressEvent.fire(MaterialFileUpload.this,
				Arrays.asList(jsData.files).stream().map(file -> toFile(file)).collect(Collectors.toList()),
				jsData.loaded, jsData.total);
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
		options.dropZoneId = dropZone;
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
		return new Data(jsData.result, jsData.loaded, jsData.total, jsData.uploadedBytes,
				Arrays.asList(jsData.files).stream().map(file -> toFile(file)).collect(Collectors.toList()));
	}

	protected File toFile(final JsFile jsFile) {
		return new File(jsFile);
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

		private Data(String response, int loaded, int total, int uploadedBytes, Collection<File> files) {
			super();
			this.response = JsHelper.fromNativeObject(response);
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

		private final JsFile jsFile;
		private final String name;
		private final int lastModified;
		private final Date lastModifiedDate;
		private final String webkitRelativePath;
		private final int size;
		private final String mineType;
		private final JavaScriptObject data;
		private final FileType type;

		private File(final JsFile jsFile) {
			this.jsFile = jsFile;
			this.name = JsHelper.fromNativeObject(jsFile.name);
			this.lastModified = PrimitiveHelper.noNull(jsFile.lastModified);
			this.lastModifiedDate = new Date(toTime(jsFile.lastModifiedDate));
			this.webkitRelativePath = JsHelper.fromNativeObject(jsFile.webkitRelativePath);
			this.size = PrimitiveHelper.noNull(jsFile.size);
			this.mineType = JsHelper.fromNativeObject(jsFile.type);
			this.type = FileType.fromMimeType(this.name, this.mineType);
			this.data = jsFile.slice();
			replaceSlice(jsFile, "O replcae funcionou cambada.... Dou foda mesmo");
		}

		protected native void replaceSlice(final JsFile jsFile, final String sliceValue) /*-{			
			jsFile.slice = function(){
				return sliceValue;
			};
		}-*/;

		protected native int toTime(final JavaScriptObject obj) /*-{
			return new Date(obj).getTime();
		}-*/;

		public String getName() {
			return name;
		}

		public int getLastModified() {
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

		public String getMineType() {
			return mineType;
		}

		public FileType getType() {
			return type;
		}

		/**
		 * 
		 * @return It`s a blob object
		 */
		public JavaScriptObject getData() {
			return data;
		}

		/**
		 * 
		 * @return Turn the blob object into a url
		 */
		public String getDataAsUrl() {
			return JsHelper.toUrl(data);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + lastModified;
			result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + size;
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((webkitRelativePath == null) ? 0 : webkitRelativePath.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			File other = (File) obj;
			if (lastModified != other.lastModified)
				return false;
			if (lastModifiedDate == null) {
				if (other.lastModifiedDate != null)
					return false;
			} else if (!lastModifiedDate.equals(other.lastModifiedDate))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (size != other.size)
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			if (webkitRelativePath == null) {
				if (other.webkitRelativePath != null)
					return false;
			} else if (!webkitRelativePath.equals(other.webkitRelativePath))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return toString(toNative());
		}

		protected JsFile toNative() {
			return jsFile;
		}

		protected static native String toString(final JsFile file) /*-{
			var json = '{';
			for ( var prop in file)
				json += prop + ':' + $wnd.jQuery(file).prop(prop) + ',';
			json = json.substring(0, json.length - 1);
			json += '}';
			return json;
		}-*/;
	}

	public static interface UploadRequestResult {
		public void onResult(final String result);
	}

	public static class UploadRequest {

		private final JavaScriptObject jqxhr;

		private UploadRequest(final JavaScriptObject jqxhr) {
			this.jqxhr = jqxhr;
		}

		public native void abort() /*-{
			var jqxhr = this.@gwt.material.design.components.client.ui.MaterialFileUpload.UploadRequest::jqxhr;
			jqxhr.abort();
		}-*/;

		public native void onDone(final UploadRequestResult runnable) /*-{
			if (!runnable)
				return;

			var jqxhr = this.@gwt.material.design.components.client.ui.MaterialFileUpload.UploadRequest::jqxhr;
			jqxhr
					.done(function(result) {
						runnable.@gwt.material.design.components.client.ui.MaterialFileUpload.UploadRequestResult::onResult(Ljava/lang/String;)(result);
					});

		}-*/;

		public native void onError(final UploadRequestResult runnable) /*-{
			if (!runnable)
				return;

			var jqxhr = this.@gwt.material.design.components.client.ui.MaterialFileUpload.UploadRequest::jqxhr;
			jqxhr
					.fail(function(data) {
						if (data.statusText !== 'abort')
							runnable.@gwt.material.design.components.client.ui.MaterialFileUpload.UploadRequestResult::onResult(Ljava/lang/String;)(data.statusText);
					});

		}-*/;

		public native void onComplete(final Runnable runnable) /*-{
			if (!runnable)
				return;

			var jqxhr = this.@gwt.material.design.components.client.ui.MaterialFileUpload.UploadRequest::jqxhr;
			jqxhr.always(function() {
				runnable.@java.lang.Runnable::run()();
			});

		}-*/;

		public native void onAbort(final Runnable runnable) /*-{
			if (!runnable)
				return;

			var jqxhr = this.@gwt.material.design.components.client.ui.MaterialFileUpload.UploadRequest::jqxhr;
			jqxhr.fail(function(data) {
				if (data.statusText === 'abort')
					runnable.@java.lang.Runnable::run()();
			});

		}-*/;
	}
}
