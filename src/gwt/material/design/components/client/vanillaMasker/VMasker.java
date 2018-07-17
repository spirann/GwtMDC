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
package gwt.material.design.components.client.vanillaMasker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.Messages;

/**
 * 
 * @author Richeli Vargas
 *
 */
public class VMasker {

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	public static native void maskPattern(Element element, String mask)/*-{
		$wnd.VMasker(element).maskPattern(mask);
	}-*/;
	
	public static native void unMask(Element element)/*-{
		$wnd.VMasker(element).unMask();
	}-*/;

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	public static native String toPattern(String text, String mask)/*-{
		return $wnd.VMasker.toPattern(text, mask);
	}-*/;

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	public static native String toPattern(String text, String mask, String placeholder)/*-{
		return $wnd.VMasker.toPattern(text, {
			pattern : mask,
			placeholder : placeholder
		});
	}-*/;

	/**
	 * 9 - Numbers <br/>
	 * A - Letters <br/>
	 * S - Alphanumeric
	 */
	public static String fromPattern(String text, String mask) {

		if (mask == null || mask.isEmpty() || text == null || text.isEmpty()) {
			return text;
		}

		final String chars[] = mask
				.replaceAll("9", "")
				.replaceAll("A", "")
				.replaceAll("S", "")
				.split("");

		for (String character : chars) {
			text = text.replace(character, "");
		}

		return text;
	}
	
	public interface Defaults extends Messages {
		
		public static final Defaults INSTANCE = GWT.create(Defaults.class);
		
		@DefaultMessage("(99) 9999-9999")
		String phone_number_10__mask();
		
		@DefaultMessage("(99) 99999-9999")
		String phone_number_11__mask();
		
		@DefaultMessage("9999 9999 9999 9999")
		String credit_card__mask();
		
		@DefaultMessage("99999-999")
		String postal_code__mask();
		
		// //////////////////////////////////////////////////////////////////////////
		// Brazilian documents
		// //////////////////////////////////////////////////////////////////////////
		
		@DefaultMessage("999.999.999-99")
		String cpf__mask();

		@DefaultMessage("99.999.999/9999-99")
		String cnpj__mask();
		
		@DefaultMessage("99.999.999-9")
		String rg__mask();
	}
}
