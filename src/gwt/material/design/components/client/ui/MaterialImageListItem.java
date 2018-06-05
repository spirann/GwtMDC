package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasImage;
import gwt.material.design.components.client.base.HasLabel;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.ui.html.Li;
import gwt.material.design.components.client.ui.html.Span;

public class MaterialImageListItem extends Li implements HasImage, HasLabel {

	private MaterialImage img;
	private Div supportingDiv = new Div(CssName.MDC_IMAGE_LIST__SUPPORTING);
	private Span label = new Span(CssName.MDC_IMAGE_LIST__LABEL);
	private boolean aspect = true;

	public MaterialImageListItem() {
		super(CssName.MDC_IMAGE_LIST__ITEM);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setLabel(getLabel());
		supportingDiv.insert(label, 0);
		add(supportingDiv);
	}
	
	@UiChild(tagname = "supporting")
	public void addSupporting(final Widget widget) {
		supportingDiv.add(widget);
	}

	protected void drawImage() {

		if (img != null) {
			img.getParent().removeFromParent();
		}

		img = new MaterialImage();
		img.addStyleName(CssName.MDC_IMAGE_LIST__IMAGE);
		
		final Div div;

		if (aspect) {
			
			img.setLayoutPosition(Position.RELATIVE);
			img.setHeight("auto");
			img.setPaddingBottom(0);
			img.setMarginBottom(0);
			
			div = new Div(CssName.MDC_IMAGE_LIST__IMAGE_ASPECT_CONTAINER);
			div.setHeight("auto");
			div.setPaddingBottom(0);
						
		} else {
			div = new Div();
		}
		
		
		div.add(img);
		
		
		insert(div, 0);
		
	}

	@Override
	public void setUrl(String url) {
		drawImage();
		img.setUrl(url);
	}

	@Override
	public String getUrl() {
		return img == null ? null : img.getUrl();
	}

	@Override
	public void setResource(ImageResource resource) {
		drawImage();
		img.setResource(resource);
	}

	@Override
	public ImageResource getResource() {
		return img == null ? null : img.getResource();
	}

	@Override
	public void setLabel(String label) {
		this.label.setText(label);

		if (label == null || label.isEmpty()) {
			supportingDiv.setDisplay(Display.NONE);
		} else {
			supportingDiv.setDisplay(Display.FLEX);
		}
	}

	@Override
	public String getLabel() {
		return label.getText();
	}

	public boolean isAspect() {
		return aspect;
	}

	public void setAspect(boolean aspect) {
		this.aspect = aspect;

		if (img != null) {
			setUrl(img.getUrl());
		}
	}
}
