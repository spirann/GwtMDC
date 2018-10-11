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

import gwt.material.design.components.client.base.interfaces.HasIndeterminate;
import gwt.material.design.components.client.base.interfaces.HasProgress;
import gwt.material.design.components.client.base.interfaces.HasReverse;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialCircularProgress extends Div implements HasReverse, HasIndeterminate, HasProgress {

	protected final MaterialSvg path = new MaterialSvg();

	protected final ApplyStyleMixin<MaterialCircularProgress> indeterminateMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_CIRCULAR_PROGRESS__INDETERMINATE);
	protected final ApplyStyleMixin<MaterialCircularProgress> reverseMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_CIRCULAR_PROGRESS__REVERSED);

	private double progress = 0d;

	public MaterialCircularProgress() {
		super(CssName.MDC_CIRCULAR_PROGRESS);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		path.setResource(MaterialResources.INSTANCE.mdcCircularProgressPath());
		add(path);
		setNativeProgress(getProgress());
	}

	public native void setNativeProgress(double val)/*-{

		var element = this.@gwt.material.design.components.client.ui.MaterialCircularProgress::getElement()();
		var pathClass = @gwt.material.design.components.client.constants.CssName::MDC_CIRCULAR_PROGRESS__PATH;

		var path = element.getElementsByClassName(pathClass)[0];

		if (!path)
			return;

		if (isNaN(val)) {
			val = 0;
		} else {
			
			var r = $wnd.jQuery(path).attr('r');
			var c = Math.PI * (r * 2);

			if (val < 0)
				val = 0;

			if (val > 1)
				val = 1;

			var pct = ((100 - (val * 100)) / 100) * c;
			var dasharray = (c * 1.0) + ', ' + c
		
			$wnd.jQuery(path).css({
				strokeDashoffset : pct,
				strokeDasharray : dasharray
			});
		}

	}-*/;

	@Override
	public void setWidth(String width) {
		setStyleProperty(CssMixin.MDC_CIRCULAR_PROGRESS__WIDTH, width);
	}

	@Override
	public void setHeight(String height) {
		setStyleProperty(CssMixin.MDC_CIRCULAR_PROGRESS__WIDTH, height);
	}

	@Override
	public void setSize(String width, String height) {
		setStyleProperty(CssMixin.MDC_CIRCULAR_PROGRESS__WIDTH, width);
	}
	
	@Override
	public void setColor(Color color) {
		setStyleProperty(CssMixin.MDC_CIRCULAR_PROGRESS__INDICATOR_COLOR, color.getCssName());
	}

	public void setIndicatorColor(final Color color) {
		setColor(color);
	}

	public void setTrackColor(final Color color) {
		setStyleProperty(CssMixin.MDC_CIRCULAR_PROGRESS__TRACK_COLOR, color.getCssName());
	}

	@Override
	public void setProgress(double progress) {
		this.progress = progress;
		if (initialized)
			setNativeProgress(progress);
	}

	@Override
	public double getProgress() {
		return progress;
	}

	@Override
	public void setIndeterminate(boolean indeterminate) {
		indeterminateMixin.setApply(indeterminate);
	}

	@Override
	public boolean isIndeterminate() {
		return indeterminateMixin.isApplied();
	}

	@Override
	public void setReverse(boolean reverse) {
		reverseMixin.setApply(reverse);
	}

	@Override
	public boolean isReverse() {
		return reverseMixin.isApplied();
	}

	public void setStartAngle(double startAngle) {
		setStyleProperty(CssMixin.MDC_CIRCULAR_PROGRESS__START_ANGLE, startAngle + "deg");
	}
}