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
package gwt.material.design.components.client.constants;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

/**
 * 
 * @author Richeli Vargas
 *
 */
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
	SVG__CIRCLE("circle"),
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
