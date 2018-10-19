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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.utils.helper.JsHelper;

/**
 * @author Richeli Vargas
 */
public class CutOutMixin<T extends Widget> extends AbstractMixin<T> {

	private final Div cutOut = new Div("mdc-cutout");
	private final Div scrim = new Div("mdc-cutout--scrim");

	public CutOutMixin(final T uiObject) {
		super(uiObject);
	}

	public void setText(String tooltip) {
	}

	public String getText() {
		return "";
	}

	public void open() {
		// attach(cutOut.getElement());
		cutOut.getElement().removeAllChildren();
		cutOut.add(scrim);
		
		final Element clone = JsHelper.clone(uiObject.getElement());
		setPosition(uiObject.getElement(), clone);
		
		append(clone, cutOut.getElement());
		RootPanel.get().add(cutOut);
	}

	protected native void append(final Element element, final Element parent)/*-{
		$wnd.jQuery(parent).append(element);
	}-*/;

	protected native void attach(final Element element)/*-{
		$wnd.jQuery('body').append(element);
	}-*/;

	protected native void unAttach(final Element element)/*-{
		$wnd.jQuery(element).remove();
	}-*/;

	protected native Element setPosition(final Element element, final Element clone)/*-{
		$wnd.jQuery(clone).css('top', $wnd.jQuery(element).offset().top);
		$wnd.jQuery(clone).css('left', $wnd.jQuery(element).offset().left);
		return clone;
	}-*/;
}
