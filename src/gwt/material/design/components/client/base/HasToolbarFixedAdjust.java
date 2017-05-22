package gwt.material.design.components.client.base;

public interface HasToolbarFixedAdjust {

	/**
	 * Adjusting sibling elements of fixed toolbars
	 * 
	 * When using mdc-toolbar--fixed, you need to set the margin of the content
	 * to prevent toolbar overlaying your content. You can add the
	 * mdc-toolbar-fixed-adjust helper class to the toolbar’s adjacent sibling
	 * element, which will add default margin-top.
	 * 
	 * When you are using mdc-toolbar with JavaScript, you should assgin your
	 * content wrapper element to mdc-toolbar’s instance property
	 * fixedAdjustElement. This will make mdc-toolbar aware of the wrapper class
	 * and adjust the margin-top correspondingly.
	 * 
	 * @param toolbarFixedAdjust
	 */
	public void setToolbarFixedAdjust(boolean toolbarFixedAdjust);

	public boolean isToolbarFixedAdjust();

}
