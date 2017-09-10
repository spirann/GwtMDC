package gwt.material.design.components.client.ui;

import com.google.gwt.resources.client.TextResource;

import gwt.material.design.components.client.base.HasSvg;
import gwt.material.design.components.client.base.mixin.SvgMixin;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.ui.html.Div;

public class MaterialSvg extends Div implements HasSvg {

	protected final SvgMixin<MaterialSvg> svgMixin = new SvgMixin<MaterialSvg>(this);

	public MaterialSvg() {
		super();
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
