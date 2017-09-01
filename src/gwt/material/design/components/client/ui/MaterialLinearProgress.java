package gwt.material.design.components.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

import gwt.material.design.components.client.base.HasBuffer;
import gwt.material.design.components.client.base.HasIndeterminate;
import gwt.material.design.components.client.base.HasProgress;
import gwt.material.design.components.client.base.HasReverse;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.ApplyStyleMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialLinearProgress extends MaterialWidget
		implements HasReverse, HasIndeterminate, HasProgress, HasBuffer {

	// /////////////////////////////////////////////////////////////
	// Initialize Checkbox
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject linearProgress;

	public static native JavaScriptObject linearProgressInit(Element element)/*-{
		return $wnd.mdc.linearProgress.MDCLinearProgress.attachTo(element);
	}-*/;

	protected final ApplyStyleMixin<MaterialLinearProgress> indeterminateMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_LINEAR_PROGRESS_INDETERMINATE);

	protected final ApplyStyleMixin<MaterialLinearProgress> reverseMixin = new ApplyStyleMixin<>(this,
			CssName.MDC_LINEAR_PROGRESS_REVERSED);

	protected Div bufferingDots = new Div(CssName.MDC_LINEAR_PROGRESS_BUFFERING_DOTS);
	protected Div buffer_ = new Div(CssName.MDC_LINEAR_PROGRESS_BUFFER);
	protected Div primaryBar = new Div(CssName.MDC_LINEAR_PROGRESS_BAR + " " + CssName.MDC_LINEAR_PROGRESS_PRIMARY_BAR);
	protected Span primaryInner = new Span(CssName.MDC_LINEAR_PROGRESS_BAR_INNER);
	protected Div secondaryBar = new Div(
			CssName.MDC_LINEAR_PROGRESS_BAR + " " + CssName.MDC_LINEAR_PROGRESS_SECONDARY_BAR);
	protected Span secondaryInner = new Span(CssName.MDC_LINEAR_PROGRESS_BAR_INNER);

	private Double progress = 0.0;
	private double buffer = 1.0;

	private boolean initialized = false;

	public MaterialLinearProgress() {
		super(HtmlElements.DIV.createElement(), CssName.MDC_LINEAR_PROGRESS);
		setRole(Role.PROGRESS_BAR);
	}

	native String btoa(String b64) /*-{
		return btoa(b64);
	}-*/;

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			primaryBar.add(primaryInner);
			secondaryBar.add(secondaryInner);

			add(bufferingDots);
			add(buffer_);
			add(primaryBar);
			add(secondaryBar);

			linearProgress = linearProgressInit(getElement());

			setBuffer(buffer);
			setProgress(progress);

			initialized = true;
		}
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
	public void setReverse(boolean dense) {
		reverseMixin.setApply(dense);
	}

	@Override
	public boolean isReverse() {
		return reverseMixin.isApplied();
	}

	/**
	 * Sets the buffer bar to this value. Value should be between [0, 1].
	 */
	@Override
	public void setBuffer(double buffer) {
		this.buffer = buffer < 0 || buffer > 1 ? 1.0 : buffer;
		if (linearProgress != null) {
			bufferingDots.setWidth("calc(" + ((1 - buffer) * 100) + "% - 6px)");
			setBuffer(linearProgress, this.buffer);
		}
	}

	@Override
	public double getBuffer() {
		return buffer;
	}

	protected native void setBuffer(final JavaScriptObject linearProgress, double buffer)/*-{
		linearProgress.buffer = buffer;
	}-*/;

	/**
	 * Sets the progress bar to this value. Value should be between [0, 1].
	 */
	@Override
	public void setProgress(double progress) {
		this.progress = progress < 0 || progress > 1 ? 0.0 : progress;
		if (linearProgress != null) {
			setProgress(linearProgress, this.progress);
		}
	}

	@Override
	public double getProgress() {
		return progress;
	}

	protected native void setProgress(final JavaScriptObject linearProgress, double progress)/*-{
		linearProgress.progress = progress;
	}-*/;

	public void setProgressColor(final Color color) {
		primaryInner.setBackgroundColor(color);
		secondaryInner.setBackgroundColor(color);
	}

	public void setBufferColor(final Color color) {
		final String css = color.getCssName();
		buffer_.setBackgroundColor(color);
		bufferingDots.setBackgroundImage("radial-gradient(ellipse 4px 4px at center, " + css + " 0%, " + css
				+ " 47%, " + css + " 47%, transparent 47%, transparent 48%)");
	}

}
