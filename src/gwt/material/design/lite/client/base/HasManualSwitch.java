package gwt.material.design.lite.client.base;

/**
 * 
 * @author Richeli Vargas
 *
 */
public interface HasManualSwitch {

	/**
	 * Disables tab switching when clicking on tab separators. Useful for
	 * disabling default behavior and setting up your own event listeners.
	 * 
	 * @param manual
	 */
	public void setManualSwitch(boolean manual);

}
