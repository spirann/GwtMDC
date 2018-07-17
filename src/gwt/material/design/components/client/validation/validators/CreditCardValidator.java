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
package gwt.material.design.components.client.validation.validators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Richeli Vargas
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CreditCardValidator {

    /**
     * Option specifying that no cards are allowed. This is useful if you want
     * only custom card types to validate so you turn off the default cards with
     * this option.
     * <br/>
     * <pre>
     * CreditCardValidator v = new CreditCardValidator(CreditCardValidator.NONE);
     * v.addAllowedCardType(customType);
     * v.isValid(aCardNumber);
     * </pre>
     *
     * @since Validator 1.1.2
     */
    public static final int NONE = 0;

    /**
     * Option specifying that American Express cards are allowed.
     */
    public static final int AMEX = 1 << 0;

    /**
     * Option specifying that Visa cards are allowed.
     */
    public static final int VISA = 1 << 1;

    /**
     * Option specifying that Mastercard cards are allowed.
     */
    public static final int MASTERCARD = 1 << 2;

    /**
     * Option specifying that Discover cards are allowed.
     */
    public static final int DISCOVER = 1 << 3;

    /**
     * The CreditCardTypes that are allowed to pass validation.
     */
	private static Collection cardTypes = new ArrayList();

    /**
     * Create a new CreditCardValidator with default options.
     */
    //private CreditCard() {
    //    this(AMEX + VISA + MASTERCARD + DISCOVER);
    //}
    /**
     * Create a new CreditCardValidator with the specified options.
     *
     * @param options Pass in CreditCardValidator.VISA +
     * CreditCardValidator.AMEX to specify that those are the only valid card
     * types.
     */
    //private CreditCard(int options) {
    //    super();
    //   initialize(options);
    //}
	private static void initialize(int options) {

        cardTypes.clear();

        final Flags f = new Flags(options);
        if (f.isOn(VISA)) {
            cardTypes.add(new Visa());
        }

        if (f.isOn(AMEX)) {
            cardTypes.add(new Amex());
        }

        if (f.isOn(MASTERCARD)) {
            cardTypes.add(new Mastercard());
        }

        if (f.isOn(DISCOVER)) {
            cardTypes.add(new Discover());
        }
    }

    public static boolean isValid(String card) {
        return isValid(card, AMEX + VISA + MASTERCARD + DISCOVER);
    }

    /**
     * Checks if the field is a valid credit card number.
     *
     * @param card The card number to validate.
     * @param options Pass in CreditCardValidator.VISA +
     * CreditCardValidator.AMEX to specify that those are the only valid card
     * types.
     * @return Whether the card number is valid.
     */
    public static boolean isValid(String card, final int options) {

        if ((card == null) || (card.length() < 13) || (card.length() > 19)) {
            return false;
        }

        if (!luhnCheck(card)) {
            return false;
        }

        initialize(options);

        final Iterator types = cardTypes.iterator();
        while (types.hasNext()) {
            final CreditCardType type = (CreditCardType) types.next();
            if (type.matches(card)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Add an allowed CreditCardType that participates in the card validation
     * algorithm.
     *
     * @param type The type that is now allowed to pass validation.
     * @since Validator 1.1.2
     */
    //public void addAllowedCardType(CreditCardType type) {
    //    this.cardTypes.add(type);
    //}

    /**
     * Checks for a valid credit card number.
     *
     * @param cardNumber Credit Card Number.
     * @return Whether the card number passes the luhnCheck.
     */
    private static boolean luhnCheck(String cardNumber) {
        // number must be validated as 0..9 numeric first!!
        int digits = cardNumber.length();
        int oddOrEven = digits & 1;
        long sum = 0;
        for (int count = 0; count < digits; count++) {
            int digit = 0;
            try {
                digit = Integer.parseInt(cardNumber.charAt(count) + "");
            } catch (NumberFormatException e) {
                return false;
            }

            if (((count & 1) ^ oddOrEven) == 0) { // not
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        return (sum == 0) ? false : (sum % 10 == 0);
    }

    /**
     * CreditCardType implementations define how validation is performed for one
     * type/brand of credit card.
     *
     * @since Validator 1.1.2
     */
    public interface CreditCardType {

        /**
         * Returns true if the card number matches this type of credit card.
         * Note that this method is <strong>not</strong> responsible for
         * analyzing the general form of the card number because
         * <code>CreditCardValidator</code> performs those checks before calling
         * this method. It is generally only required to valid the length and
         * prefix of the number to determine if it's the correct type.
         *
         * @param card The card number, never null.
         * @return true if the number matches.
         */
        boolean matches(String card);

    }

    /**
     * Change to support Visa Carte Blue used in France has been removed - see
     * Bug 35926
     */
    private static class Visa implements CreditCardType {

        private static final String PREFIX = "4";

        @Override
        public boolean matches(String card) {
            return (card.substring(0, 1).equals(PREFIX)
                    && (card.length() == 13 || card.length() == 16));
        }
    }

    private static class Amex implements CreditCardType {

        private static final String PREFIX = "34,37,";

        @Override
        public boolean matches(String card) {
            String prefix2 = card.substring(0, 2) + ",";
            return ((PREFIX.indexOf(prefix2) != -1) && (card.length() == 15));
        }
    }

    private static class Discover implements CreditCardType {

        private static final String PREFIX = "6011";

        @Override
        public boolean matches(String card) {
            return (card.substring(0, 4).equals(PREFIX) && (card.length() == 16));
        }
    }

    private static class Mastercard implements CreditCardType {

        private static final String PREFIX = "51,52,53,54,55,";

        @Override
        public boolean matches(String card) {
            String prefix2 = card.substring(0, 2) + ",";
            return ((PREFIX.indexOf(prefix2) != -1) && (card.length() == 16));
        }
    }

}

/**
 * Represents a collection of 64 boolean (on/off) flags. Individual flags are
 * represented by powers of 2. For example,<br/>
 * Flag 1 = 1<br/>
 * Flag 2 = 2<br/>
 * Flag 3 = 4<br/>
 * Flag 4 = 8<br/><br/>
 * or using shift operator to make numbering easier:<br/>
 * Flag 1 = 1 &lt;&lt; 0<br/>
 * Flag 2 = 1 &lt;&lt; 1<br/>
 * Flag 3 = 1 &lt;&lt; 2<br/>
 * Flag 4 = 1 &lt;&lt; 3<br/>
 *
 * <p>
 * There cannot be a flag with a value of 3 because that represents Flag 1 and
 * Flag 2 both being on/true.
 * </p>
 *
 * @version $Revision: 478334 $ $Date: 2006-11-22 21:31:54 +0000 (Wed, 22 Nov
 * 2006) $
 */
class Flags implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4661765472338471658L;
	
	/**
     * Represents the current flag state.
     */
    private long flags = 0;

    /**
     * Create a new Flags object.
     */
    public Flags() {
        super();
    }

    /**
     * Initialize a new Flags object with the given flags.
     *
     * @param flags collection of boolean flags to represent.
     */
    public Flags(long flags) {
        super();
        this.flags = flags;
    }

    /**
     * Returns the current flags.
     *
     * @return collection of boolean flags represented.
     */
    public long getFlags() {
        return this.flags;
    }

    /**
     * Tests whether the given flag is on. If the flag is not a power of 2 (ie.
     * 3) this tests whether the combination of flags is on.
     *
     * @param flag Flag value to check.
     *
     * @return whether the specified flag value is on.
     */
    public boolean isOn(long flag) {
        return (this.flags & flag) > 0;
    }

    /**
     * Tests whether the given flag is off. If the flag is not a power of 2 (ie.
     * 3) this tests whether the combination of flags is off.
     *
     * @param flag Flag value to check.
     *
     * @return whether the specified flag value is off.
     */
    public boolean isOff(long flag) {
        return (this.flags & flag) == 0;
    }

    /**
     * Turns on the given flag. If the flag is not a power of 2 (ie. 3) this
     * turns on multiple flags.
     *
     * @param flag Flag value to turn on.
     */
    public void turnOn(long flag) {
        this.flags |= flag;
    }

    /**
     * Turns off the given flag. If the flag is not a power of 2 (ie. 3) this
     * turns off multiple flags.
     *
     * @param flag Flag value to turn off.
     */
    public void turnOff(long flag) {
        this.flags &= ~flag;
    }

    /**
     * Turn off all flags.
     */
    public void turnOffAll() {
        this.flags = 0;
    }

    /**
     * Turn off all flags. This is a synonym for <code>turnOffAll()</code>.
     *
     * @since Validator 1.1.1
     */
    public void clear() {
        this.flags = 0;
    }

    /**
     * Turn on all 64 flags.
     */
    public void turnOnAll() {
        this.flags = Long.MAX_VALUE;
    }

    /**
     * Tests if two Flags objects are in the same state.
     *
     * @param obj object being tested
     * @see java.lang.Object#equals(java.lang.Object)
     *
     * @return whether the objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Flags)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Flags f = (Flags) obj;

        return this.flags == f.flags;
    }

    /**
     * The hash code is based on the current state of the flags.
     *
     * @see java.lang.Object#hashCode()
     *
     * @return the hash code for this object.
     */
    @Override
    public int hashCode() {
        return (int) this.flags;
    }

    /**
     * Returns a 64 length String with the first flag on the right and the 64th
     * flag on the left. A 1 indicates the flag is on, a 0 means it's off.
     *
     * @return string representation of this object.
     */
    @Override
    public String toString() {
        final StringBuffer bin = new StringBuffer(Long.toBinaryString(this.flags));
        for (int i = 64 - bin.length(); i > 0; i--) {
            bin.insert(0, "0");
        }
        return bin.toString();
    }

}
