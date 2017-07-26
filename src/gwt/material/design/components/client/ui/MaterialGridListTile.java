package gwt.material.design.components.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasCaption;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.HasImage;
import gwt.material.design.components.client.base.HasSupportText;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.constants.Ripple;

public class MaterialGridListTile extends MaterialWidget implements HasIcon, HasCaption, HasSupportText, HasImage {

	public static native void gridListInit(Element element)/*-{
		$wnd.mdc.gridList.MDCGridList.attachTo(element);
	}-*/;

	private MaterialWidget primary = new MaterialWidget(HtmlElements.DIV.createElement(), CssName.MDC_GRID_TILE_PRIMARY,
			CssName.MDC_TYPOGRAPHY);

	private MaterialImage image = new MaterialImage();

	
	
	private MaterialWidget secondary = new MaterialWidget(HtmlElements.SPAN.createElement(),
			CssName.MDC_GRID_TILE_SECONDARY, CssName.MDC_TYPOGRAPHY);

	private MaterialIcon icon = new MaterialIcon();
	
	private MaterialWidget caption = new MaterialWidget(HtmlElements.SPAN.createElement(), CssName.MDC_GRID_TILE_TITLE,
			CssName.MDC_TYPOGRAPHY);

	private MaterialWidget supportText = new MaterialWidget(HtmlElements.SPAN.createElement(),
			CssName.MDC_GRID_TILE_SUPPORT_TEXT, CssName.MDC_TYPOGRAPHY);

	private boolean initialize = false;

	public MaterialGridListTile() {
		super(HtmlElements.LI.createElement(), CssName.MDC_GRID_TILE);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialize) {
			super.add(primary);

			gridListInit(getElement());

			image.addStyleName(CssName.MDC_GRID_TILE_PRIMARY_CONTENT);

			primary.setZIndex(0);
			primary.add(image);

			icon.setStyleName(CssName.MDC_GRID_TILE_ICON);
			icon.setRipple(Ripple.DEFAULT);
			icon.setCircle(true);
			icon.setBackgroundColor(Color.RED);
			icon.setLayoutPosition(Position.ABSOLUTE);
			
			secondary.setZIndex(1);
			secondary.add(icon);
			secondary.add(caption);
			secondary.add(supportText);

			initialize = true;
		}
	}

	private void checkSecondary() {
		if (secondary.getParent() == null) {
			super.add(secondary);
		}
	}

	@Override
	public void add(Widget child) {
		primary.add(child);
	}

	@Override
	public IconType getIcon() {

		if (icon == null) {
			return null;
		}

		return icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		checkSecondary();
		icon.setType(iconType);
	}

	public void setIconColor(final Color color) {
		checkSecondary();
		icon.setTextColor(color);
	}

	public void setIconBackgroundColor(final Color color) {
		checkSecondary();
		icon.setBackgroundColor(color);
	}

	public HandlerRegistration addIconClickHandler(ClickHandler handler) {
		checkSecondary();
		return icon.addClickHandler(handler);
	}

	@Override
	public String getCaption() {
		return caption.getElement().getInnerText();
	}

	@Override
	public void setCaption(String caption) {
		checkSecondary();
		this.caption.getElement().setInnerText(caption);
	}

	@Override
	public String getSupportText() {
		return supportText.getElement().getInnerText();
	}

	@Override
	public void setSupportText(String supportText) {
		checkSecondary();
		this.supportText.getElement().setInnerText(supportText);
	}

	@Override
	public void setUrl(String url) {
		image.setUrl(url);
	}

	@Override
	public String getUrl() {
		return image.getUrl();
	}

	@Override
	public void setResource(ImageResource resource) {
		image.setResource(resource);
	}

	@Override
	public ImageResource getResource() {
		return image.getResource();
	}

	@Override
	public void setBackground(String background) {
		primary.setBackground(background);
	}

	@Override
	public void setBackgroundColor(Color color) {
		primary.setBackgroundColor(color);
	}
}
