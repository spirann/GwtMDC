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

import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasType;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CodeType;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.ui.html.Code;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCode extends MaterialWidget implements HasType<CodeType>, HasText {

	protected Code code = new Code();

	protected final TypeMixin<Code, CodeType> typeMixin = new TypeMixin<>(code);	
	
	public MaterialCode() {
		super(HtmlElements.PRE.createElement(), CssName.PRISM_LANGUAGE_MARKUP, CssName.MDC_CODE);
		add(code);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		highlight();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (getType() == null) {
			setType(CodeType.XML);
		}		
	}
	
	public native void highlightAll()/*-{
		$wnd.Prism.highlightAll();
	}-*/;

	private void highlight() {
		if (initialized) {
			highlightAll();
		}
	}

	@Override
	public String getText() {
		return code.getText();
	}

	@Override
	public void setText(String text) {
		code.setText(text);
		highlight();
	}

	public void setTextResource(TextResource resource) {
		setText(resource.getText());
	}

	@Override
	public void setType(CodeType type) {
		typeMixin.setType(type);
		highlight();
	}

	@Override
	public CodeType getType() {
		return typeMixin.getType();
	}
}
