package gwt.material.design.components.client.validation;

import java.util.Collection;

import gwt.material.design.components.client.constants.State;
import gwt.material.design.components.client.resources.message.IMessages;
import gwt.material.design.components.client.ui.MaterialFileUpload;
import gwt.material.design.components.client.utils.helper.Formatation;

public interface FileUploadValidation extends Validation {

	/**
	 * 
	 * @param value
	 * @param isRequired
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public Result validate(final MaterialFileUpload source, final Collection<MaterialFileUpload.File> files);

	public class Defaults {

		// //////////////////////////////////////////////////////////////////////////////////////
		// Default validation
		// //////////////////////////////////////////////////////////////////////////////////////

		public static final FileUploadValidation default_validation() {
			return (source, files) -> {

				if (!source.isSingleFileUploads()) {
					final Integer maxNumberOfFiles = source.getMaxNumberOfFiles();
					if (maxNumberOfFiles != null && files.size() > maxNumberOfFiles)
						return new Result(State.ERROR, 2201, IMessages.INSTANCE
								.mdc_file_upload__err__max_number_of_files_exceeded(maxNumberOfFiles));

					final int totalSize = files.stream().mapToInt(f -> f.getSize()).sum();
					final Integer limitMultiFileUploadSize = source.getLimitMultiFileUploadSize();
					if (limitMultiFileUploadSize != null && totalSize > limitMultiFileUploadSize)
						return new Result(State.ERROR, 2202,
								IMessages.INSTANCE.mdc_file_upload__err__max_limit_multi_upload_size_exceeded(
										Formatation.bytes(limitMultiFileUploadSize), Formatation.bytes(totalSize)));

				}

				final Integer maxFileSize = source.getMaxFileSize();
				if (maxFileSize != null)
					for (MaterialFileUpload.File file : files)
						if (file.getSize() > maxFileSize)
							return new Result(State.ERROR, 2203,
									IMessages.INSTANCE.mdc_file_upload__err__file_size_is_too_bg(
											Formatation.bytes(maxFileSize), file.getName(),
											Formatation.bytes(file.getSize())));

				return new Result(State.NONE, 0, "");

			};
		}
	}
}
