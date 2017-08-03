package gwt.material.design.components.client.ui;


import com.google.gwt.resources.client.TextResource;

import gwt.material.design.components.client.base.HasSvg;
import gwt.material.design.components.client.base.MaterialWidget;
import gwt.material.design.components.client.base.mixin.SvgMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.HtmlElements;

public class MaterialSvg extends MaterialWidget implements HasSvg {

	private final SvgMixin<MaterialSvg> svgMixin = new SvgMixin<MaterialSvg>(this);

	public MaterialSvg() {
		super(HtmlElements.DIV.createElement());
	}

	@Override
	public void setResource(TextResource resource) {
		svgMixin.setResource(resource);
	}

	@Override
	public void setSvg(String text) {
		svgMixin.setSvg(text);
	}

	@Override
	public void setFillColor(Color fillColor) {
		svgMixin.setFillColor(fillColor);
	}

	@Override
	public Color getFillColor() {
		return svgMixin.getFillColor();
	}

}
