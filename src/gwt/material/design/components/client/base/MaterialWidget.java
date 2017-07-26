package gwt.material.design.components.client.base;

import java.util.ArrayList;
import java.util.List;

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

import gwt.material.design.components.client.base.mixin.ActiveMixin;
import gwt.material.design.components.client.base.mixin.AttributeMixin;
import gwt.material.design.components.client.base.mixin.CircleMixin;
import gwt.material.design.components.client.base.mixin.EnabledMixin;
import gwt.material.design.components.client.base.mixin.IdMixin;
import gwt.material.design.components.client.base.mixin.RippleMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.Elevation;
import gwt.material.design.components.client.constants.Ripple;
import gwt.material.design.components.client.events.DragEndEvent;
import gwt.material.design.components.client.events.DragEnterEvent;
import gwt.material.design.components.client.events.DragLeaveEvent;
import gwt.material.design.components.client.events.DragMoveEvent;
import gwt.material.design.components.client.events.DragOverEvent;
import gwt.material.design.components.client.events.DragStartEvent;
import gwt.material.design.components.client.events.DropActivateEvent;
import gwt.material.design.components.client.events.DropDeactivateEvent;
import gwt.material.design.components.client.events.DropEvent;

@SuppressWarnings("deprecation")
public class MaterialWidget extends BaseWidget implements HasId, HasInitialClasses, HasEnabled, HasInteractionHandlers,
		HasAllFocusHandlers, HasAutoInitData, HasRole, HasActive, HasRipple, HasCircle, HasElevation {

	static {
		autoInit();
	}

	class Appender {
		Widget widget;
		int index = -1;

		public Appender(Widget widget, int index) {
			this.widget = widget;
			this.index = index;
		}

		public Appender(Widget widget) {
			this.widget = widget;
		}
	}

	private final IdMixin<MaterialWidget> idMixin = new IdMixin<>(this);;
	private final EnabledMixin<MaterialWidget> enabledMixin = new EnabledMixin<>(this);
	private final ActiveMixin<MaterialWidget> activeMixin = new ActiveMixin<>(this);
	private final AttributeMixin<MaterialWidget> autoInitMixin = new AttributeMixin<MaterialWidget>(this,
			"data-mdc-auto-init");
	private final AttributeMixin<MaterialWidget> roleMixin = new AttributeMixin<MaterialWidget>(this, "role");
	private final AttributeMixin<MaterialWidget> rtlMixin = new AttributeMixin<MaterialWidget>(this, "dir");
	private final RippleMixin<MaterialWidget> ripleMixin = new RippleMixin<>(this);
	private final CircleMixin<MaterialWidget> circleMixin = new CircleMixin<MaterialWidget>(this);
	private final TypeMixin<MaterialWidget, Elevation> elevationMixin = new TypeMixin<>(this);

	private String primaryClass;
	private String[] initialClasses;

	private List<Appender> onLoadAdd;

	private boolean initialize = false;

	public static native void autoInit()/*-{
		$wnd.mdc.autoInit();
	}-*/;

	public static native void rippleInit(Element element)/*-{
		$wnd.mdc.ripple.MDCRipple.attachTo(element);
	}-*/;

	public MaterialWidget() {
	}

	public MaterialWidget(Element element) {
		setElement(element);
	}

	public MaterialWidget(Element element, String primaryClass) {
		this(element);
		setPrimaryClass(primaryClass);
	}

	public MaterialWidget(Element element, String primaryClass, String... initialClass) {
		this(element, primaryClass);
		setInitialClasses(initialClass);
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		if (getPrimaryClass() != null && !getPrimaryClass().isEmpty()) {
			setStylePrimaryName(getPrimaryClass());
		}

		if (initialClasses != null) {
			for (String initial : initialClasses) {
				if (!initial.isEmpty()) {
					removeStyleName(initial);
					addStyleName(initial);
				}
			}
		}

		if (onLoadAdd != null) {
			// Check the onLoadAdd items.
			for (Appender item : onLoadAdd) {
				if (item.index == -1) {
					add(item.widget, (Element) getElement());
				} else {
					insert(item.widget, item.index);
				}
			}
			onLoadAdd.clear();
		}

		initialize = true;
		
		rippleInit(getElement());
		
	}

	/*
	 * @Override public void setStyleName(String style) {
	 * super.setStyleName(style); if (initialize) {
	 * upgradeElement(getElement()); } }
	 * 
	 * @Override public void setStylePrimaryName(String style) {
	 * super.setStylePrimaryName(style); if (initialize) {
	 * upgradeElement(getElement()); } }
	 * 
	 * @Override public void addStyleName(String style) {
	 * super.addStyleName(style); if (initialize) {
	 * upgradeElement(getElement()); } }
	 */

	@Override
	public void add(Widget child) {
		super.add(child, (Element) getElement());
	}

	@Override
	protected void add(Widget child, com.google.gwt.user.client.Element container) {
		if (!isAttached()) {
			if (onLoadAdd == null) {
				onLoadAdd = new ArrayList<>();
			}
			onLoadAdd.add(new Appender(child));
		} else {
			super.add(child, container);
		}
	}

	@Override
	protected void insert(Widget child, com.google.gwt.user.client.Element container, int beforeIndex,
			boolean domInsert) {
		if (!isAttached()) {
			if (onLoadAdd == null) {
				onLoadAdd = new ArrayList<>();
			}
			onLoadAdd.add(new Appender(child, beforeIndex));
		} else {
			// Regular child addition
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

	public void setPrimaryClass(final String primaryClass) {
		this.primaryClass = primaryClass;
		setStylePrimaryName(primaryClass);
	}

	public String getPrimaryClass() {
		return primaryClass;
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
		List<Widget> children = new ArrayList<>();
		for (int i = 0; i < getWidgetCount(); i++) {
			children.add(getWidget(i));
		}
		return children;
	}

	// Events

	@Override
	public HandlerRegistration addClickHandler(final ClickHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onClick(event);
			}
		}, ClickEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onMouseDown(event);
			}
		}, MouseDownEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onMouseMove(event);
			}
		}, MouseMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onMouseOut(event);
			}
		}, MouseOutEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onMouseOver(event);
			}
		}, MouseOverEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onMouseUp(event);
			}
		}, MouseUpEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onMouseWheel(event);
			}
		}, MouseWheelEvent.getType());
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onDoubleClick(event);
			}
		}, DoubleClickEvent.getType());
	}

	@Override
	public HandlerRegistration addDragStartHandler(DragStartEvent.DragStartHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onDragStart(event);
			}
		}, DragStartEvent.getType());
	}

	@Override
	public HandlerRegistration addDragMoveHandler(DragMoveEvent.DragMoveHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onDragMove(event);
			}
		}, DragMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addDragEndHandler(DragEndEvent.DragEndHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onDragEnd(event);
			}
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
			if (isEnabled()) {
				handler.onDragEnter(event);
			}
		}, DragEnterEvent.getType());
	}

	@Override
	public HandlerRegistration addDragLeaveHandler(DragLeaveEvent.DragLeaveHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onDragLeave(event);
			}
		}, DragLeaveEvent.getType());
	}

	@Override
	public HandlerRegistration addDragOverHandler(final DragOverEvent.DragOverHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onDragOver(event);
			}
		}, DragOverEvent.getType());
	}

	@Override
	public HandlerRegistration addDropDeactivateHandler(DropDeactivateEvent.DropDeactivateHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onDropDeactivate(event);
			}
		}, DropDeactivateEvent.getType());
	}

	@Override
	public HandlerRegistration addDropHandler(DropEvent.DropHandler handler) {
		return addHandler(event -> {
			if (isEnabled()) {
				handler.onDrop(event);
			}
		}, DropEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onTouchCancel(event);
			}
		}, TouchCancelEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onTouchEnd(event);
			}
		}, TouchEndEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onTouchMove(event);
			}
		}, TouchMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onTouchStart(event);
			}
		}, TouchStartEvent.getType());
	}

	@Override
	public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onGestureChange(event);
			}
		}, GestureChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onGestureEnd(event);
			}
		}, GestureEndEvent.getType());
	}

	@Override
	public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onGestureStart(event);
			}
		}, GestureStartEvent.getType());
	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onKeyDown(event);
			}
		}, KeyDownEvent.getType());
	}

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onKeyPress(event);
			}
		}, KeyPressEvent.getType());
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onKeyUp(event);
			}
		}, KeyUpEvent.getType());
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onBlur(event);
			}
		}, BlurEvent.getType());
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return addDomHandler(event -> {
			if (isEnabled()) {
				handler.onFocus(event);
			}
		}, FocusEvent.getType());
	}

	@Override
	public void setId(String id) {
		idMixin.setId(id);
	}

	@Override
	public String getId() {
		return idMixin.getId();
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
	public boolean isActive() {
		return activeMixin.isActive();
	}

	@Override
	public void setActive(boolean active) {
		activeMixin.setActive(active);
	}

	public void setRtl(boolean rtl) {
		if (rtl) {
			rtlMixin.setAttribute("rtl");
		} else {
			rtlMixin.setAttribute(null);
		}
	}

	public boolean isRtl() {
		return rtlMixin.getAttribute() != null;
	}

	@Override
	public void setAutoInitData(String autoInitData) {
		autoInitMixin.setAttribute(autoInitData);
	}

	@Override
	public String getAutoInitData() {
		return autoInitMixin.getAttribute();
	}

	@Override
	public void setRole(String role) {
		roleMixin.setAttribute(role);
	}

	@Override
	public String getRole() {
		return roleMixin.getAttribute();
	}

	@Override
	public void setRipple(Ripple ripple) {
		ripleMixin.setRipple(ripple);
	}

	@Override
	public Ripple getRipple() {
		return ripleMixin.getRipple();
	}

	@Override
	public void setCircle(boolean circle) {
		circleMixin.setCircle(circle);
	}

	@Override
	public void setElevation(Elevation elevation) {
		elevationMixin.setType(elevation);
	}

	@Override
	public Elevation getElevation() {
		return elevationMixin.getType();
	}
}
