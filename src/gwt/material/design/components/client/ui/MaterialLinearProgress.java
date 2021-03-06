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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.interfaces.HasBuffer;
import gwt.material.design.components.client.base.interfaces.HasIndeterminate;
import gwt.material.design.components.client.base.interfaces.HasProgress;
import gwt.material.design.components.client.base.interfaces.HasReverse;
import gwt.material.design.components.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Span;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialLinearProgress extends Div implements HasReverse, HasIndeterminate, HasProgress, HasBuffer {

	protected final ToggleStyleMixin<MaterialLinearProgress> indeterminateMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_LINEAR_PROGRESS__INDETERMINATE);

	protected final ToggleStyleMixin<MaterialLinearProgress> reverseMixin = new ToggleStyleMixin<>(this,
			CssName.MDC_LINEAR_PROGRESS__REVERSED);

	protected Div bufferingDots = new Div(CssName.MDC_LINEAR_PROGRESS__BUFFERING_DOTS);
	protected Div buffer_ = new Div(CssName.MDC_LINEAR_PROGRESS__BUFFER);
	protected Div primaryBar = new Div(CssName.MDC_LINEAR_PROGRESS__BAR, CssName.MDC_LINEAR_PROGRESS__PRIMARY_BAR);
	protected Span primaryInner = new Span(CssName.MDC_LINEAR_PROGRESS__BAR_INNER);
	protected Div secondaryBar = new Div(CssName.MDC_LINEAR_PROGRESS__BAR, CssName.MDC_LINEAR_PROGRESS__SECONDARY_BAR);
	protected Span secondaryInner = new Span(CssName.MDC_LINEAR_PROGRESS__BAR_INNER);

	protected Double progress = 0.0;
	protected double buffer = 1.0;

	public MaterialLinearProgress() {
		super(CssName.MDC_LINEAR_PROGRESS);
		setRole(Role.PROGRESS_BAR);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return new $wnd.mdc.linearProgress.MDCLinearProgress(element);
	}-*/;

	@Override
	protected void onInitialize() {

		primaryBar.add(primaryInner);
		secondaryBar.add(secondaryInner);

		add(bufferingDots);
		add(buffer_);
		add(primaryBar);
		add(secondaryBar);

		super.onInitialize();

		setBuffer(buffer);
		setProgress(progress);
	}

	@Override
	public void setIndeterminate(boolean indeterminate) {
		indeterminateMixin.toggle(indeterminate);
	}

	@Override
	public boolean isIndeterminate() {
		return indeterminateMixin.isApplied();
	}

	@Override
	public void setReverse(boolean reverse) {
		reverseMixin.toggle(reverse);
	}

	@Override
	public boolean isReverse() {
		return reverseMixin.isApplied();
	}

	@Override
	public double getBuffer() {
		return buffer;
	}

	/**
	 * Sets the buffer bar to this value. Value should be between [0, 1].
	 */
	@Override
	public native void setBuffer(double buffer)/*-{

		this.@gwt.material.design.components.client.ui.MaterialLinearProgress::buffer = buffer;

		var bufferingDots = this.@gwt.material.design.components.client.ui.MaterialLinearProgress::bufferingDots;
		var bufferingDotsElement = bufferingDots.@gwt.material.design.components.client.ui.html.Div::getElement()();
		var linearProgress = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;

		if (linearProgress) {
			bufferingDotsElement.style.width = "calc(" + ((1 - buffer) * 100)
					+ "% - 6px)";
			linearProgress.buffer = buffer;
		}

	}-*/;

	@Override
	public double getProgress() {
		return progress;
	}

	/**
	 * Sets the progress bar to this value. Value should be between [0, 1].
	 */
	@Override
	public native void setProgress(double progress)/*-{

		this.@gwt.material.design.components.client.ui.MaterialLinearProgress::progress = progress;
		var linearProgress = this.@gwt.material.design.components.client.base.widget.MaterialWidget::jsElement;

		if (linearProgress)
			linearProgress.progress = progress;

	}-*/;

	public void setIndicatorColor(final Color color) {
		setCssProperty(CssMixin.MDC_LINEAR_PROGRESS__INDICATOR_COLOR, color.getCssName());
	}

	public void setTrackColor(final Color color) {
		setCssProperty(CssMixin.MDC_LINEAR_PROGRESS__TRACK_COLOR, color.getCssName());
	}

}
