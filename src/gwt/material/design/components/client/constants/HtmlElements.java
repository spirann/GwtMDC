package gwt.material.design.components.client.constants;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public enum HtmlElements {
	
	BODY("body"),
	HEADER("header"),
	FOOTER("footer"),
	DIV("div"),
	UL("ul"),
	LI("li"),
	ASIDE("aside"),
	NAV("nav"),
	SECTION("section"),
	SPAN("span"),
	MAIN("main"),
	BUTTON("button"),
	LABEL("label"),
	BR("br"),
	CODE("code"),
	PRE("pre"),
	IFRAME("iframe"),
	HTML("html"),
	INPUT("input"),
	TEXTAREA("textarea"),
	A("a"),
	I("i"),
	IMG("img"),
	P("p"),
	SVG("svg"),
	PATH("path"),
	H1("h1"),
	H2("h2"),
	H3("h3"),
	H4("h4");
	
	private final String tag;
	
	private HtmlElements(final String tag){
		this.tag = tag;
	}
	
	public String getTag(){
		return tag;
	}
	
	public Element createElement(){
		return Document.get().createElement(tag);
	}	
}
