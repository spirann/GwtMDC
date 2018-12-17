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
package gwt.material.design.components.client.ui.misc.fileUpload.js;

import com.google.gwt.core.client.JavaScriptObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * 
 * @author Richeli Vargas
 *
 */
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsOptions {

	@JsProperty
	public String url;
	
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
	@JsProperty
	public String accept;

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
	@JsProperty
	public String type;

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
	@JsProperty
	public String dataType;

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
	@JsProperty
	public JavaScriptObject dropZone;
	
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
	@JsProperty
	public boolean autoUpload;
	
	/**
	 * Allows to set the accept-charset attribute for the iframe upload forms. If
	 * formAcceptCharset is not set, the accept-charset attribute of the file upload
	 * widget form is used, if available.<br/>
	 * 
	 * Type: string<br/>
	 * Example: 'utf-8'
	 */
	@JsProperty
	public String formAcceptCharset;
	
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
	@JsProperty
	public boolean multipart;
	
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
	@JsProperty
	public boolean singleFileUploads;
	
	/**
	 * Set this option to true to issue all file upload requests in a sequential
	 * order instead of simultaneous requests.<br/>
	 * 
	 * Type: boolean<br/>
	 * Default: false
	 */
	@JsProperty
	public boolean sequentialUploads;

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
	@JsProperty
	public Integer limitConcurrentUploads;
	
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
	@JsProperty
	public Integer maxNumberOfFiles;
	
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
	@JsProperty
	public Integer limitMultiFileUploadSize;
	
	/**
	 * The maximum allowed file size in bytes.<br/>
	 * 
	 * Type: integer <br/>
	 * Default: undefined <br/>
	 * Example: 10000000 // 10 MB
	 */
	@JsProperty
	public Integer maxFileSize;
	
	/**
	 * The minimum time interval in milliseconds to calculate and trigger progress
	 * events.<br/>
	 * 
	 * Type: integer <br/>
	 * Default: 100
	 */
	@JsProperty
	public int progressInterval;
	
	/**
	 * The minimum time interval in milliseconds to calculate progress bitrate.<br/>
	 * 
	 * Type: integer <br/>
	 * Default: 500
	 */
	@JsProperty
	public int bitrateInterval;

}
