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
package gwt.material.design.components.client.base.widget;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.GestureChangeEvent;
import com.google.gwt.event.dom.client.GestureChangeHandler;
import com.google.gwt.event.dom.client.GestureEndEvent;
import com.google.gwt.event.dom.client.GestureEndHandler;
import com.google.gwt.event.dom.client.GestureStartEvent;
import com.google.gwt.event.dom.client.GestureStartHandler;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.WidgetCollection;

import gwt.material.design.components.client.base.interfaces.FromString;
import gwt.material.design.components.client.base.interfaces.HasAlt;
import gwt.material.design.components.client.base.interfaces.HasAriaControls;
import gwt.material.design.components.client.base.interfaces.HasAriaDescribedBy;
import gwt.material.design.components.client.base.interfaces.HasAriaLabel;
import gwt.material.design.components.client.base.interfaces.HasAriaLabelledBy;
import gwt.material.design.components.client.base.interfaces.HasAriaModal;
import gwt.material.design.components.client.base.interfaces.HasAriaSelected;
import gwt.material.design.components.client.base.interfaces.HasAutoInitData;
import gwt.material.design.components.client.base.interfaces.HasDataObject;
import gwt.material.design.components.client.base.interfaces.HasElevation;
import gwt.material.design.components.client.base.interfaces.HasHideOn;
import gwt.material.design.components.client.base.interfaces.HasInitialClasses;
import gwt.material.design.components.client.base.interfaces.HasInteractionHandlers;
import gwt.material.design.components.client.base.interfaces.HasRipple;
import gwt.material.design.components.client.base.interfaces.HasRole;
import gwt.material.design.components.client.base.interfaces.HasRtl;
import gwt.material.design.components.client.base.interfaces.HasTabindex;
import gwt.material.design.components.client.base.interfaces.HasTooltip;
import gwt.material.design.components.client.base.mixin.AutoInitDataMixin;
import gwt.material.design.components.client.base.mixin.EnabledMixin;
import gwt.material.design.components.client.base.mixin.RippleMixin;
import gwt.material.design.components.client.base.mixin.TooltipMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.base.mixin.base.AttributeMixin;
import gwt.material.design.components.client.constants.AutoInitData;
import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.CssAttribute;
import gwt.material.design.components.client.constants.Elevation;
import gwt.material.design.components.client.constants.HideOn;
import gwt.material.design.components.client.constants.Role;
import gwt.material.design.components.client.constants.TooltipPosition;
import gwt.material.design.components.client.events.DragEndEvent;
import gwt.material.design.components.client.events.DragEnterEvent;
import gwt.material.design.components.client.events.DragLeaveEvent;
import gwt.material.design.components.client.events.DragMoveEvent;
import gwt.material.design.components.client.events.DragOverEvent;
import gwt.material.design.components.client.events.DragStartEvent;
import gwt.material.design.components.client.events.DropActivateEvent;
import gwt.material.design.components.client.events.DropDeactivateEvent;
import gwt.material.design.components.client.events.DropEvent;
import gwt.material.design.components.client.events.ResizeEvent;
import gwt.material.design.components.client.events.ResizeEvent.HasResizeHandlers;
import gwt.material.design.components.client.events.ResizeEvent.ResizeHandler;

/**
 * 
 * @author Richeli Vargas
 *
 */
@SuppressWarnings("deprecation")
public class MaterialWidget extends MaterialUIObject
		implements HasInitialClasses, HasEnabled, HasInteractionHandlers, HasAllFocusHandlers, HasResizeHandlers, HasAutoInitData, HasRole, HasRipple, HasElevation, HasRtl,
		HasHideOn, HasAlt, HasAriaLabel, HasTabindex, HasAriaControls, HasAriaDescribedBy, HasAriaSelected, HasAriaModal, HasAriaLabelledBy, HasTooltip, HasDataObject {

	static {
		autoInit();
	}

	public static native void autoInit()/*-{
		$wnd.mdc.autoInit();
	}-*/;

	protected class Appender {

		public static final int SEQUENTIAL = -1;
		public static final int START = -2;
		public static final int END = -3;

		public Widget widget;
		public int index = SEQUENTIAL;

		public Appender(Widget widget, int index) {
			this.widget = widget;
			this.index = index;
		}

		public Appender(Widget widget) {
			this.widget = widget;
		}
	}

	// /////////////////////////////////////////////////////////////
	// Initialize java script component
	// /////////////////////////////////////////////////////////////
	protected JavaScriptObject jsElement;

	// /////////////////////////////////////////////////////////////
	// Data object helps the developer to save same entity
	// or any object who is necessary
	// /////////////////////////////////////////////////////////////
	private Object object;

	// /////////////////////////////////////////////////////////////
	// Mixin list
	// /////////////////////////////////////////////////////////////
	protected final EnabledMixin<MaterialWidget> enabledMixin = new EnabledMixin<>(this);
	protected final AutoInitDataMixin<MaterialWidget> autoInitMixin = new AutoInitDataMixin<MaterialWidget>(this);
	protected final RippleMixin<MaterialWidget> ripleMixin = new RippleMixin<>(this);
	protected final TypeMixin<MaterialWidget, Elevation> elevationMixin = new TypeMixin<>(this);
	protected final TypeMixin<MaterialWidget, HideOn> hideOnMixin = new TypeMixin<>(this);
	protected final TooltipMixin<MaterialWidget> tooltipMixin = new TooltipMixin<>(this);
	protected final AttributeMixin<MaterialWidget, Role> roleMixin = new AttributeMixin<>(this, CssAttribute.ROLE, value -> Role.fromStyleName(value));
	protected final AttributeMixin<MaterialWidget, String> rtlMixin = new AttributeMixin<>(this, CssAttribute.DIR, FromString.TO_STRING);
	protected final AttributeMixin<MaterialWidget, String> altMixin = new AttributeMixin<>(this, CssAttribute.ALT, FromString.TO_STRING);
	protected final AttributeMixin<MaterialWidget, String> ariaLabelMixin = new AttributeMixin<>(this, CssAttribute.ARIA_LABEL, FromString.TO_STRING);
	protected final AttributeMixin<MaterialWidget, String> ariaControlsMixin = new AttributeMixin<>(this, CssAttribute.ARIA_CONTROLS, FromString.TO_STRING);
	protected final AttributeMixin<MaterialWidget, String> ariaDescribedByMixin = new AttributeMixin<>(this, CssAttribute.ARIA_DESCRIBEDBY, FromString.TO_STRING);
	protected final AttributeMixin<MaterialWidget, Boolean> ariaSelectedMixin = new AttributeMixin<>(this, CssAttribute.ARIA_SELECTED, false, FromString.TO_BOOLEAN);
	protected final AttributeMixin<MaterialWidget, Boolean> ariaModalMixin = new AttributeMixin<>(this, CssAttribute.ARIA_MODAL, false, FromString.TO_BOOLEAN);
	protected final AttributeMixin<MaterialWidget, String> ariaLabelledByMixin = new AttributeMixin<>(this, CssAttribute.ARIA_LABELLEDBY, FromString.TO_STRING);
	protected final AttributeMixin<MaterialWidget, Integer> tabindexMixin = new AttributeMixin<>(this, CssAttribute.TABINDEX, FromString.TO_INTEGER);

	private String[] initialClasses;

	protected List<Appender> onLoadAdd;

	protected boolean initialized = false;

	public MaterialWidget(Element element) {
		super(element);
	}

	public MaterialWidget(Element element, String... initialClass) {
		this(element);
		setInitialClasses(initialClass);
	}

	protected final void jsInit() {
		jsElement = jsInit(getElement());
	}

	public JavaScriptObject asJavaScriptObject() {
		return jsElement;
	}

	protected native JavaScriptObject jsInit(final Element element)/*-{
		return element;
	}-*/;

	@Override
	protected void onLoad() {
		super.onLoad();

		if (initialClasses != null) {
			for (String initial : initialClasses) {
				if (!initial.isEmpty()) {
					removeStyleName(initial);
					addStyleName(initial);
				}
			}
		}

		if (onLoadAdd != null) {

			final List<Appender> end = new LinkedList<>();

			// Check the onLoadAdd items.
			for (Appender item : onLoadAdd) {
				if (item.index == Appender.SEQUENTIAL)
					add(item.widget, getElement());
				else if (item.index == Appender.START)
					insert(item.widget, 0);
				else if (item.index == Appender.END)
					end.add(item);
				// insert(item.widget, onLoadAdd.size());
				else
					insert(item.widget, item.index);
			}
			end.forEach(item -> add(item.widget, getElement()));
			end.clear();
			onLoadAdd.clear();
		}

		if (!initialized) {
			onInitialize();
			initialized = true;
		}

	}

	protected void onInitialize() {
		jsInit();
	}

	@Override
	public void clear() {
		super.clear();
		if (onLoadAdd != null)
			onLoadAdd.clear();
	}

	@Override
	public void add(Widget child) {
		add(child, getElement());
	}

	@Override
	protected void add(Widget child, com.google.gwt.user.client.Element container) {
		if (!isAttached()) {
			if (onLoadAdd == null)
				onLoadAdd = new ArrayList<>();
			onLoadAdd.add(new Appender(child));
		} else {
			super.add(child, container);
		}
	}

	private static native Element resolve(Element maybePotential) /*-{
		return maybePotential.__gwt_resolve ? maybePotential.__gwt_resolve()
				: maybePotential;
	}-*/;

	@Override
	protected void insert(Widget child, com.google.gwt.user.client.Element container, int beforeIndex, boolean domInsert) {
		if (!isAttached()) {
			if (onLoadAdd == null)
				onLoadAdd = new ArrayList<>();
			onLoadAdd.add(new Appender(child, beforeIndex));
		} else {
			// Regular child addition
			if (beforeIndex == Appender.START)
				beforeIndex = 0;
			else if (beforeIndex == Appender.END)
				beforeIndex = getWidgetCount();
			else if (beforeIndex == Appender.SEQUENTIAL)
				beforeIndex = getWidgetCount();

			super.insert(child, container, beforeIndex, domInsert);
		}
	}

	/**
	 * Inserts a widget at a specific index
	 *
	 * @param child
	 *            - widget to be inserted
	 * @param beforeIndex
	 *            - index for the widget
	 */
	public void insert(final Widget child, final int beforeIndex) {
		insert(child, (Element) getElement(), beforeIndex, true);
	}

	@Override
	public void setInitialClasses(String... initialClasses) {
		this.initialClasses = initialClasses;
	}

	@Override
	public String[] getInitialClasses() {
		return initialClasses;
	}

	/**
	 * Set the style attribute of your element. Note that this will override any
	 * {@link Element#getStyle()} changes and vice-versa.
	 */
	public void setStyle(String style) {
		getElement().setAttribute("style", style);
	}

	/**
	 * Set the 'class' attribute of this element. Note that this will override
	 * {@link #addStyleName(String)} and vice-versa.
	 */
	public void setClass(String cssClasses) {
		getElement().setAttribute("class", cssClasses);
	}

	@Override
	public WidgetCollection getChildren() {
		return super.getChildren();
	}

	public List<Widget> getChildrenList() {
		final List<Widget> children = new ArrayList<>();
		for (int i = 0; i < getWidgetCount(); i++)
			children.add(getWidget(i));
		return children;
	}

	// Events

	@Override
	public HandlerRegistration addClickHandler(final ClickHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onClick(event);
		}, ClickEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onMouseDown(event);
		}, MouseDownEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onMouseMove(event);
		}, MouseMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onMouseOut(event);
		}, MouseOutEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onMouseOver(event);
		}, MouseOverEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onMouseUp(event);
		}, MouseUpEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onMouseWheel(event);
		}, MouseWheelEvent.getType());
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onDoubleClick(event);
		}, DoubleClickEvent.getType());
	}

	@Override
	public HandlerRegistration addDragStartHandler(DragStartEvent.DragStartHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onDragStart(event);
		}, DragStartEvent.getType());
	}

	@Override
	public HandlerRegistration addDragMoveHandler(DragMoveEvent.DragMoveHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onDragMove(event);
		}, DragMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addDragEndHandler(DragEndEvent.DragEndHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onDragEnd(event);
		}, DragEndEvent.getType());
	}

	@Override
	public HandlerRegistration addDropActivateHandler(DropActivateEvent.DropActivateHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onDropActivate(event);
			}
		}, DropActivateEvent.getType());
	}

	@Override
	public HandlerRegistration addDragEnterHandler(DragEnterEvent.DragEnterHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onDragEnter(event);
		}, DragEnterEvent.getType());
	}

	@Override
	public HandlerRegistration addDragLeaveHandler(DragLeaveEvent.DragLeaveHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onDragLeave(event);
		}, DragLeaveEvent.getType());
	}

	@Override
	public HandlerRegistration addDragOverHandler(final DragOverEvent.DragOverHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onDragOver(event);
		}, DragOverEvent.getType());
	}

	@Override
	public HandlerRegistration addDropDeactivateHandler(DropDeactivateEvent.DropDeactivateHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onDropDeactivate(event);
		}, DropDeactivateEvent.getType());
	}

	@Override
	public HandlerRegistration addDropHandler(DropEvent.DropHandler handler) {
		return addHandler(event -> {
			if (isEnabled())
				handler.onDrop(event);
		}, DropEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onTouchCancel(event);
		}, TouchCancelEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onTouchEnd(event);
		}, TouchEndEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onTouchMove(event);
		}, TouchMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onTouchStart(event);
		}, TouchStartEvent.getType());
	}

	@Override
	public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onGestureChange(event);
		}, GestureChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onGestureEnd(event);
		}, GestureEndEvent.getType());
	}

	@Override
	public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onGestureStart(event);
		}, GestureStartEvent.getType());

	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onKeyDown(event);
		}, KeyDownEvent.getType());
	}

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onKeyPress(event);
		}, KeyPressEvent.getType());
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onKeyUp(event);
		}, KeyUpEvent.getType());
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onBlur(event);
		}, BlurEvent.getType());
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled())
				handler.onFocus(event);
		}, FocusEvent.getType());
	}

	private boolean resizeSensorInitialized = false;

	protected native void initializeResizeSensor()/*-{
		var _this = this;		
		var initialized = this.@gwt.material.design.components.client.base.widget.MaterialWidget::resizeSensorInitialized;
		if (!initialized) {
			this.@gwt.material.design.components.client.base.widget.MaterialWidget::resizeSensorInitialized = true;
			var element = this.@gwt.material.design.components.client.base.widget.MaterialWidget::getElement()();	
			new $wnd.ResizeSensor(element, function() {
				_this.@gwt.material.design.components.client.base.widget.MaterialWidget::fireResizeEvent()();
			});
		}
	}-*/;

	protected final void fireResizeEvent() {
		ResizeEvent.fire(this);
	}

	@Override
	public HandlerRegistration addResizeHandler(final ResizeHandler handler) {
		initializeResizeSensor();
		return addHandler(handler, ResizeEvent.getType());
	}

	@Override
	public boolean isEnabled() {
		return enabledMixin.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		enabledMixin.setEnabled(enabled);
	}

	@Override
	public void setRtl(boolean rtl) {
		if (rtl) {
			rtlMixin.setValue("rtl");
			setCssProperty("direction", "rtl");
		} else {
			rtlMixin.setValue(null);
			setCssProperty("direction", "ltr");
		}
	}

	@Override
	public boolean isRtl() {
		return rtlMixin.getValue() != null;
	}

	@Override
	public void setAutoInitData(AutoInitData autoInitData) {
		autoInitMixin.setAutoInitData(autoInitData);
	}

	@Override
	public AutoInitData getAutoInitData() {
		return autoInitMixin.getAutoInitData();
	}

	@Override
	public void setRole(Role role) {
		roleMixin.setValue(role);
	}

	@Override
	public Role getRole() {
		return roleMixin.getValue();
	}

	@Override
	public void setRipple(Color color) {
		ripleMixin.setRipple(color);
	}

	@Override
	public Color getRipple() {
		return ripleMixin.getRipple();
	}

	@Override
	public void setElevation(Elevation elevation) {
		elevationMixin.setType(elevation);
	}

	@Override
	public Elevation getElevation() {
		return elevationMixin.getType();
	}

	@Override
	public void setHideOn(HideOn hideOn) {
		hideOnMixin.setType(hideOn);
	}

	@Override
	public HideOn getHideOn() {
		return hideOnMixin.getType();
	}

	@Override
	public void setAriaLabel(String ariaLabel) {
		ariaLabelMixin.setValue(ariaLabel);
	}

	@Override
	public String getAriaLabel() {
		return ariaLabelMixin.getValue();
	}

	@Override
	public void setAriaControls(String target) {
		ariaControlsMixin.setValue(target);
	}

	@Override
	public String getAriaControls() {
		return ariaControlsMixin.getValue();
	}

	@Override
	public void setAriaDescribedBy(String target) {
		ariaDescribedByMixin.setValue(target);
	}

	@Override
	public String getAriaDescribedBy() {
		return ariaDescribedByMixin.getValue();
	}

	@Override
	public void setAlt(String alt) {
		altMixin.setValue(alt);
	}

	@Override
	public String getAlt() {
		return altMixin.getValue();
	}

	@Override
	public void setTabindex(int tabindex) {
		tabindexMixin.setValue(tabindex);
	}

	@Override
	public int getTabindex() {
		return tabindexMixin.getValue();
	}

	@Override
	public void setAriaSelected(boolean selected) {
		ariaSelectedMixin.setValue(selected);
	}

	@Override
	public boolean isAreaSelected() {
		return ariaSelectedMixin.getValue();
	}

	@Override
	public void setAriaModal(boolean modal) {
		ariaModalMixin.setValue(modal);
	}

	@Override
	public boolean isAreaModal() {
		return ariaModalMixin.getValue();
	}

	@Override
	public void setAriaLabelledBy(String labelledby) {
		ariaLabelledByMixin.setValue(labelledby);
	}

	@Override
	public String getAriaLabelledBy() {
		return ariaLabelledByMixin.getValue();
	}

	@Override
	public void setTooltip(String tooltip) {
		tooltipMixin.setTooltip(tooltip);
	}

	@Override
	public String getTooltip() {
		return tooltipMixin.getTooltip();
	}

	public TooltipPosition getTooltipPosition() {
		return tooltipMixin.getTooltipPosition();
	}

	public void setTooltipPosition(TooltipPosition position) {
		tooltipMixin.setTooltipPosition(position);
	}
	
	@Override
	public void setTooltipColor(Color color) {
		tooltipMixin.setTooltipColor(color);
	}

	@Override
	public void setTooltipBackgroundColor(Color color) {
		tooltipMixin.setTooltipBackgroundColor(color);
	}

	@Override
	public <O> void setDataObject(O object) {
		this.object = object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <O> O getDataObject() {
		return object == null ? null : (O) object;
	}
}
