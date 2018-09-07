package gwt.material.design.components.client.ui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasAspectRatio;
import gwt.material.design.components.client.base.HasImage;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.base.mixin.AspectRatioMixin;
import gwt.material.design.components.client.constants.AspectRatio;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Li;
import gwt.material.design.components.client.ui.html.Span;
import gwt.material.design.components.client.utils.helper.JsHelper;

public class MaterialImageListItem extends Li implements HasImage, HasLabel, HasAspectRatio {

	private Div imageContent = new Div();
	private MaterialImage image;
	private Div supportingDiv = new Div(CssName.MDC_IMAGE_LIST__SUPPORTING);
	private Span label = new Span(CssName.MDC_IMAGE_LIST__LABEL);
		
	private final AspectRatioMixin<Div> aspectRatioMixin = new AspectRatioMixin<Div>(imageContent);

	public MaterialImageListItem() {
		super(CssName.MDC_IMAGE_LIST__ITEM);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setLabel(getLabel());
		supportingDiv.insert(label, 0);
		add(supportingDiv);
		addClickHandler(event -> JsHelper.clearFocus());
	}
	
	@UiChild(tagname = "supporting")
	public void addSupporting(final Widget widget) {
		supportingDiv.add(widget);
	}

	protected void drawImage() {

		image = new MaterialImage();
		image.addStyleName(CssName.MDC_IMAGE_LIST__IMAGE);
		
		imageContent.clear();
		imageContent.removeStyleName(CssName.MDC_IMAGE_LIST__IMAGE_ASPECT_CONTAINER);

		if (getAspectRatio() != null && !getAspectRatio().equals(AspectRatio.DEFAULT)) {			
			imageContent.addStyleName(CssName.MDC_IMAGE_LIST__IMAGE_ASPECT_CONTAINER);
		}
				
		imageContent.add(image);		
		insert(imageContent, 0);
		
	}

	@Override
	public void setUrl(String url) {
		drawImage();
		image.setUrl(url);
	}

	@Override
	public String getUrl() {
		return image == null ? null : image.getUrl();
	}

	@Override
	public void setResource(ImageResource resource) {
		drawImage();
		image.setResource(resource);
	}

	@Override
	public ImageResource getResource() {
		return image == null ? null : image.getResource();
	}

	@Override
	public void setLabel(String label) {
		this.label.setText(label);

		if ((label == null || label.isEmpty()) && supportingDiv.getChildrenList().size() < 2) {
			supportingDiv.setDisplay(Display.NONE);
		} else {
			supportingDiv.setDisplay(Display.FLEX);
		}
	}

	@Override
	public String getLabel() {
		return label.getText();
	}

	@Override
	public void setAspectRatio(AspectRatio aspectRatio) {
		aspectRatioMixin.setAspectRatio(aspectRatio);
		
		if (image != null) {
			setUrl(image.getUrl());
		}
	}

	@Override
	public AspectRatio getAspectRatio() {
		return aspectRatioMixin.getAspectRatio();
	}
}
