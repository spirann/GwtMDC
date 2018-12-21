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
package gwt.material.design.components.client.constants;

import java.util.Arrays;

/**
 * 
 * @author Richeli Vargas
 *
 */
public enum FileType {

	UNKNOW_TYPE("", ""),
	/* =========== */
	/* Media types */
	/* =========== */
	AUDIO("audio/", ""), 
	IMAGE("image/", ""), 
	VIDEO("video/", ""),
	/* =========== */
	/* Text types  */
	/* =========== */
	TEXT("text/plain", "txt"), 
	JSON("application/json", "json"), 
	HTML("text/html", "html"), 
	JAVASCRIPT("text/javascript", "js"), 
	CSS("text/css", "css"),
	/* =========== */
	/* Zip types   */
	/* =========== */
	ZIP("application/x-zip-compressed", "zip"), 
	WINRAR("", "rar"), ZIP_7Z("", "7z"), 
	TAR("application/x-tar", "tar"), 
	TAR_GZ("application/x-gzip", "gz"),
	/* =========== */
	/* MS types    */
	/* =========== */
	MS_WORD("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"), 
	MS_POWERPOINT("application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx"), 
	MS_EXCEL("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","xlsx"), 
	MS_PUBLISHER("application/vnd.ms-publisher", "pub"), 
	MS_ACCESS("application/msaccess", "access"),
	/* =========== */
	/* App types   */
	/* =========== */
	EXE("application/x-msdownload", "exe"),
	UNKNOW_APPLICATION("application/", "");
	
	private final String mimeType;
	private final String fileExtension;

	FileType(final String mimeType, final String fileExtension) {
		this.mimeType = mimeType;
		this.fileExtension = fileExtension;
	}

	public static FileType fromMimeType(final String fileName, final String mimeType) {

		final boolean nameInvalid = fileName == null || fileName.trim().isEmpty();
		final boolean mineTypeInvalid = mimeType == null || mimeType.trim().isEmpty();

		if (nameInvalid && mineTypeInvalid)
			return UNKNOW_TYPE;

		if (mineTypeInvalid)
			return Arrays.asList(values()).stream().filter(type -> {
				final String[] parts = fileName.split(".");
				final String extension = String.valueOf(parts[parts.length -1]);
				return extension.toLowerCase().equals(type.fileExtension);
			}).findAny().orElse(UNKNOW_TYPE);

		return Arrays.asList(values()).stream().filter(type -> 
			!type.mimeType.isEmpty() 
			&& !type.equals(UNKNOW_APPLICATION)
			&& mimeType.toLowerCase().startsWith(type.mimeType)).findAny()
				.orElse(mimeType.toLowerCase().startsWith(UNKNOW_APPLICATION.mimeType) ? UNKNOW_APPLICATION : UNKNOW_TYPE);
	}
}
