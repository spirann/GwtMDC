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
package gwt.material.design.components.client.base.mixin;

import com.google.gwt.resources.client.ImageResource;

import gwt.material.design.components.client.base.interfaces.HasImage;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.base.widget.MaterialUIObject;
import gwt.material.design.components.client.constants.CssAttribute;

/**
 * @author Richeli Vargas
 */
public class ImageMixin<UIO extends MaterialUIObject & HasImage> extends AttributeMixin<UIO, String> implements HasImage {

	private ImageResource resource;

	public ImageMixin(final UIO uiObject) {
		super(uiObject, CssAttribute.SRC);
	}

	@Override
	public void setUrl(String url) {
		setValue(url);
	}

	@Override
	public String getUrl() {
		return getValue();
	}

	/**
	 * @param resource
	 *            ImageResource
	 */
	@Override
	public void setResource(ImageResource resource) {
		this.resource = resource;
		if (resource == null)
			setUrl(null);
		else
			setUrl(resource.getSafeUri().asString());
	}

	@Override
	public ImageResource getResource() {
		return resource;
	}
}
