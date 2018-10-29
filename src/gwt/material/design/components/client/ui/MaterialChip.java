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

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasText;

import gwt.material.design.components.client.base.interfaces.HasIcon;
import gwt.material.design.components.client.base.interfaces.HasImage;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.TextMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.base.widget.MaterialSelectedField;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.ChipSetType;
import gwt.material.design.components.client.constants.ChipType;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.Display;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.resources.MaterialResources;
import gwt.material.design.components.client.ui.html.Div;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class MaterialChip extends MaterialSelectedField implements HasType<ChipType>, HasText, HasIcon, HasImage {

	// ////////////////////////////////////////////////////////////////
	// Control for change value in RadioChip group
	// Because the event is not trigger when another radio is selected
	// ////////////////////////////////////////////////////////////////
	protected final static Map<String, MaterialChip> history = new HashMap<>();

	protected void updateHistory() {
		if (isSelected())
			putInHistory(true);
		else
			removeFromHistory();
	}

	protected void putInHistory(final boolean fireEvent) {

		if (getParent() == null || !(getParent() instanceof MaterialChipSet))
			return;

		final MaterialChipSet chipSet = (MaterialChipSet) getParent();

		if (!chipSet.getType().equals(ChipSetType.CHOICE))
			return;

		final String parent = chipSet.getId();

		final MaterialChip old = history.get(parent);
		if (fireEvent && old != null && old != this)
			old.fireChangeEvent();

		history.put(parent, this);
	}

	protected void removeFromHistory() {

		if (getParent() == null)
			return;

		final String parent = ((MaterialWidget) getParent()).getId();

		final MaterialChip old = history.get(parent);
		if (old == this)
			history.remove(parent);

	}

	protected final MaterialImage imageLeading = new MaterialImage();
	protected final MaterialIcon iconLeading = new MaterialIcon(CssName.MDC_CHIP__ICON,
			CssName.MDC_CHIP__ICON__LEADING);
	protected final MaterialIcon iconTrailing = new MaterialIcon(IconType.CANCEL, CssName.MDC_CHIP__ICON,
			CssName.MDC_CHIP__ICON__TRAILING);
	protected final Div text = new Div(CssName.MDC_CHIP__TEXT);
	protected final Div checkmark = new Div(CssName.MDC_CHIP__ICON__CHECKMARK);
	protected final MaterialSvg checkmarkSvg = new MaterialSvg(CssName.MDC_CHIP__ICON__CHECKMARK__SVG);

	protected final TextMixin<Div> textMixin = new TextMixin<>(text);
	protected final TypeMixin<MaterialChip, ChipType> typeMixin = new TypeMixin<>(this);

	protected boolean closeable = false;

	public MaterialChip() {
		super(CssName.MDC_CHIP);
		super.initializeSelectedMixin(CssName.MDC_CHIP__SELECTED);
	}

	@Override
	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onInitialize() {

		fireChangeOnClick = true;
		ripleMixin.initialize();

		checkmarkSvg.setResource(MaterialResources.INSTANCE.mdcChipCheckmark());
		checkmark.add(checkmarkSvg);

		imageLeading.addStyleName(CssName.MDC_CHIP__ICON);
		imageLeading.addStyleName(CssName.MDC_CHIP__ICON__LEADING);

		add(checkmark);

		if (iconLeading.getType() != null)
			add(iconLeading);

		if (imageLeading.getUrl() != null && !imageLeading.getUrl().isEmpty())
			add(imageLeading);

		add(text);

		if (closeable)
			add(iconTrailing);

		super.onInitialize();

		if (isSelected())
			updateHistory();
	}

	@Override
	protected void fireChangeEvent() {
		updateHistory();
		super.fireChangeEvent();
	}

	@Override
	public String getText() {
		return textMixin.getText();
	}

	@Override
	public void setText(String text) {
		textMixin.setText(text);
	}

	@Override
	public void setType(ChipType type) {
		typeMixin.setType(type);
	}

	@Override
	public ChipType getType() {
		return typeMixin.getType();
	}

	public void setCloseable(final boolean closeable) {

		this.closeable = closeable;

		if (initialized) {
			if (closeable)
				insert(iconTrailing, getWidgetCount());
			else if (iconTrailing.getParent() != null)
				iconTrailing.removeFromParent();
		}
	}

	public boolean isCloseable() {
		return closeable;
	}

	public void setCloseableColor(final Color color) {
		iconTrailing.setColor(color);
	}

	public void setShowCheckmark(final boolean show) {
		if (show)
			checkmark.setDisplay(Display.INITIAL);
		else
			checkmark.setDisplay(Display.NONE);
	}

	@Override
	public IconType getIcon() {
		return iconLeading.getType();
	}

	@Override
	public void setIcon(IconType iconType) {
		setIcon(iconType, false);
	}

	@Override
	public void setIcon(IconType iconType, boolean animate) {
		iconLeading.setType(iconType, animate);

		if (iconType == null && iconLeading.getParent() != null)
			iconLeading.removeFromParent();

		if (iconType != null && initialized)
			insert(iconLeading, 0);
	}

	@Override
	public void setUrl(String url) {
		imageLeading.setUrl(url);

		if (url == null && imageLeading.getParent() != null)
			imageLeading.removeFromParent();

		if (url != null && initialized)
			insert(imageLeading, iconLeading.getParent() == null ? 0 : 1);
	}

	@Override
	public String getUrl() {
		return imageLeading.getUrl();
	}

	@Override
	public void setResource(ImageResource resource) {

		imageLeading.setResource(resource);

		if (resource == null && imageLeading.getParent() != null)
			imageLeading.removeFromParent();

		if (resource != null && initialized)
			insert(imageLeading, iconLeading.getParent() == null ? 0 : 1);
	}

	@Override
	public ImageResource getResource() {
		return imageLeading.getResource();
	}

	@Override
	public void setBackgroundColor(Color color) {
		setBackground(null);
		super.setBackgroundColor(color);
		setCssProperty(CssMixin.MDC_CHIP_CONTAINER_FILL_COLOR, color.getCssName());
	}

	@Override
	public void setTextColor(Color color) {
		setCssProperty(CssMixin.MDC_CHIP__TEXT_COLOR, color.getCssName());
	}

	public void setIconColor(Color color) {
		setCssProperty(CssMixin.MDC_CHIP_ICON_LEADING_COLOR, color.getCssName());
	}

	public void setCloseColor(Color color) {
		setCssProperty(CssMixin.MDC_CHIP_ICON_TREALING_COLOR, color.getCssName());
	}

	public void setSelectColor(Color color) {
		setCssProperty(CssMixin.MDC_CHIP_ICON_SELECTED_COLOR, color.getCssName());
	}

}
