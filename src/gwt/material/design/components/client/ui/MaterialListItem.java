package gwt.material.design.components.client.ui;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.HasHref;
import gwt.material.design.components.client.base.HasIcon;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.HtmlElements;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.html.Label;

public class MaterialListItem extends MaterialWidget implements HasHref, HasText, HasIcon {

	private MaterialIcon icon = new MaterialIcon();
	private MaterialImage avatar = new MaterialImage(); 
	private Label primaryText = new Label();
	private Label secondaryText = new Label();

	// /////////////////////////////////////////////////////////////
	// Style mixin
	// /////////////////////////////////////////////////////////////
	private final TextMixin<Label> primaryTextMixin = new TextMixin<>(primaryText);
	private final TextMixin<Label> secondaryTextMixin = new TextMixin<>(secondaryText);
	private final AttributeMixin<MaterialListItem> hrefMixin = new AttributeMixin<>(this, "href");
	private final AttributeMixin<MaterialListItem> targetMixin = new AttributeMixin<>(this, "target");
	private final AttributeMixin<MaterialIcon> ariaHiddenMixin = new AttributeMixin<>(icon, "aria-hidden");

	private boolean initialized = false;

	public MaterialListItem() {
		super(HtmlElements.LI.createElement(), CssName.MDC_LIST_ITEM);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (!initialized) {

			ariaHiddenMixin.setAttribute(true);
			
			avatar.addStyleName(CssName.MDC_LIST_ITEM_START_DETAIL);
			avatar.setCircle(true);
			
			if(avatar.getUrl() != null && !avatar.getUrl().isEmpty()) {
				insert(avatar, 0);				
			}
			
			icon.addStyleName(CssName.MDC_LIST_ITEM_START_DETAIL);
			icon.setCircle(true);

			if(icon.getType() != null) {
				insert(icon, 0);				
			}
			
			primaryText.addStyleName(CssName.MDC_LIST_ITEM_TEXT);
			add(primaryText);
			
			secondaryText.addStyleName(CssName.MDC_LIST_ITEM_TEXT_SECONDARY);			
			primaryText.add(secondaryText);
			
			initialized = true;

		}
	}

	@Override
	public String getText() {
		return primaryTextMixin.getText();
	}

	@Override
	public void setText(String text) {
		primaryText.clear();
		primaryTextMixin.setText(text);
		primaryText.add(secondaryText);
	}
	
	public String getTextSecondary() {
		return secondaryTextMixin.getText();
	}

	public void setTextSecondary(String text) {
		secondaryTextMixin.setText(text);
	}

	@Override
	public void setHref(String href) {
		hrefMixin.setAttribute(href);
	}

	@Override
	public String getHref() {
		return hrefMixin.getAttribute();
	}

	@Override
	public void setTarget(String target) {
		targetMixin.setAttribute(target);
	}

	@Override
	public String getTarget() {
		return targetMixin.getAttribute();
	}

	@Override
	public IconType getIcon() {
		return icon.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		icon.setType(iconType);
		
		if(icon.getType() != null && isAttached() && icon.getParent() == null) {
			insert(icon, 0);
		} else if(icon.getType() == null && icon.getParent() != null) {
			icon.removeFromParent();
		}
	}

	
	public void setAvatar(final ImageResource resource) {		
		setAvatarUrl(resource == null ? null : resource.getSafeUri().asString());
	}
	
	public void setAvatarUrl(final String url) {
		avatar.setUrl(url);
		
		if(avatar.getUrl() != null && !avatar.getUrl().isEmpty() && isAttached() && avatar.getParent() == null) {
			insert(avatar, 0);
		} else if((avatar.getUrl() == null || avatar.getUrl().isEmpty()) && avatar.getParent() != null) {
			avatar.removeFromParent();
		}
	}
	
	@Override
	public void setTextColor(Color color) {
		primaryText.setTextColor(color);
	}

	public void setIconColor(Color color) {
		icon.setTextColor(color);
	}
	
	public void setIconBackgroundColor(Color color) {
		icon.setBackgroundColor(color);
	}
	
	public void setAvatarBackgroundColor(Color color) {
		avatar.setBackgroundColor(color);
	}
}
