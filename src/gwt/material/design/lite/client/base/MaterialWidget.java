package gwt.material.design.lite.client.base;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.FontWeight;
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
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.WidgetCollection;

import gwt.material.design.lite.client.base.mixin.ColorsMixin;
import gwt.material.design.lite.client.base.mixin.CssNameMixin;
import gwt.material.design.lite.client.base.mixin.EnabledMixin;
import gwt.material.design.lite.client.base.mixin.FlexboxMixin;
import gwt.material.design.lite.client.base.mixin.FontSizeMixin;
import gwt.material.design.lite.client.base.mixin.GridMixin;
import gwt.material.design.lite.client.base.mixin.IdMixin;
import gwt.material.design.lite.client.constants.Color;
import gwt.material.design.lite.client.constants.Display;
import gwt.material.design.lite.client.constants.Flex;
import gwt.material.design.lite.client.constants.FlexAlignContent;
import gwt.material.design.lite.client.constants.FlexAlignItems;
import gwt.material.design.lite.client.constants.FlexAlignSelf;
import gwt.material.design.lite.client.constants.FlexDirection;
import gwt.material.design.lite.client.constants.FlexJustifyContent;
import gwt.material.design.lite.client.constants.FlexWrap;
import gwt.material.design.lite.client.constants.HideOn;
import gwt.material.design.lite.client.constants.ShowOn;
import gwt.material.design.lite.client.constants.TextAlign;
import gwt.material.design.lite.client.events.DragEndEvent;
import gwt.material.design.lite.client.events.DragEnterEvent;
import gwt.material.design.lite.client.events.DragLeaveEvent;
import gwt.material.design.lite.client.events.DragMoveEvent;
import gwt.material.design.lite.client.events.DragOverEvent;
import gwt.material.design.lite.client.events.DragStartEvent;
import gwt.material.design.lite.client.events.DropActivateEvent;
import gwt.material.design.lite.client.events.DropDeactivateEvent;
import gwt.material.design.lite.client.events.DropEvent;

@SuppressWarnings("deprecation")
public class MaterialWidget extends ComplexPanel
		implements HasId, HasInitialClasses, HasColors, HasEnabled, HasInteractionHandlers, 
		HasAllFocusHandlers, HasGrid, HasTextAlign, HasInlineStyle, HasFlexbox, HasHideOn, HasShowOn {

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

	private IdMixin<MaterialWidget> idMixin;
	private EnabledMixin<MaterialWidget> enabledMixin;
	private ColorsMixin<MaterialWidget> colorsMixin;
	private GridMixin<MaterialWidget> gridMixin;
	private CssNameMixin<MaterialWidget, TextAlign> textAlignMixin;
	private FontSizeMixin<MaterialWidget> fontSizeMixin;
	private CssNameMixin<MaterialWidget, FontWeight> fontWeightMixin;
    private FlexboxMixin<MaterialWidget> flexboxMixin;
    private CssNameMixin<MaterialWidget, HideOn> hideOnMixin;
    private CssNameMixin<MaterialWidget, ShowOn> showOnMixin;
    
	private String primaryClass;
	private String[] initialClasses;

	private List<Appender> onLoadAdd;

	public static native void upgradeElement(final Element element)/*-{
		$wnd.componentHandler.upgradeElement(element);
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

		upgradeElement(getElement());
	}

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
	// ///
	
	protected IdMixin<MaterialWidget> getIdMixin() {
        if (idMixin == null) {
            idMixin = new IdMixin<>(this);
        }
        return idMixin;
    }
	
	protected EnabledMixin<MaterialWidget> getEnabledMixin() {
		if (enabledMixin == null) {
			enabledMixin = new EnabledMixin<>(this);
		}
		return enabledMixin;
	}

	protected ColorsMixin<MaterialWidget> getColorsMixin() {
		if (colorsMixin == null) {
			colorsMixin = new ColorsMixin<>(this);
		}
		return colorsMixin;
	}

	protected GridMixin<MaterialWidget> getGridMixin() {
		if (gridMixin == null) {
			gridMixin = new GridMixin<>(this);
		}
		return gridMixin;
	}

	protected CssNameMixin<MaterialWidget, TextAlign> getTextAlignMixin() {
        if (textAlignMixin == null) {
            textAlignMixin = new CssNameMixin<>(this);
        }
        return textAlignMixin;
    }
	
	protected CssNameMixin<MaterialWidget, FontWeight> getFontWeightMixin() {
        if (fontWeightMixin == null) {
            fontWeightMixin = new CssNameMixin<>(this);
        }
        return fontWeightMixin;
    }
	
	protected FontSizeMixin<MaterialWidget> getFontSizeMixin() {
        if (fontSizeMixin == null) {
            fontSizeMixin = new FontSizeMixin<>(this);
        }
        return fontSizeMixin;
    }
	
	protected FlexboxMixin<MaterialWidget> getFlexboxMixin() {
        if (flexboxMixin == null) {
            flexboxMixin = new FlexboxMixin<>(this);
        }
        return flexboxMixin;
    }
	
	protected CssNameMixin<MaterialWidget, HideOn> getHideOnMixin() {
        if (hideOnMixin == null) {
        	hideOnMixin = new CssNameMixin<>(this);
        }
        return hideOnMixin;
    }
	
	protected CssNameMixin<MaterialWidget, ShowOn> getShowOnMixin() {
        if (showOnMixin == null) {
        	showOnMixin = new CssNameMixin<>(this);
        }
        return showOnMixin;
    }
	
    @Override
    public void setId(String id) {
        getIdMixin().setId(id);
    }

    @Override
    public String getId() {
        return getIdMixin().getId();
    }
	
	@Override
	public boolean isEnabled() {
		return getEnabledMixin().isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		getEnabledMixin().setEnabled(enabled);
	}

	@Override
	public void setBackgroundColor(Color bgColor) {
		getColorsMixin().setBackgroundColor(bgColor);
	}

	@Override
	public Color getBackgroundColor() {
		return getColorsMixin().getBackgroundColor();
	}

	@Override
	public void setTextColor(Color textColor) {
		getColorsMixin().setTextColor(textColor);
	}

	@Override
	public Color getTextColor() {
		return getColorsMixin().getTextColor();
	}

	@Override
	public void setCols(int cols) {
		getGridMixin().setCols(cols);
	}
	
	@Override
	public void setColsDesktop(int cols) {
		getGridMixin().setColsDesktop(cols);
	}

	@Override
	public void setColsTablet(int cols) {
		getGridMixin().setColsTablet(cols);
	}

	@Override
	public void setColsPhone(int cols) {
		getGridMixin().setColsPhone(cols);
	}

	@Override
	public void setOffset(int offset) {
		getGridMixin().setOffset(offset);
	}
	
	@Override
	public void setOffsetDesktop(int offset) {
		getGridMixin().setOffsetDesktop(offset);
	}

	@Override
	public void setOffsetTablet(int offset) {
		getGridMixin().setOffsetTablet(offset);
	}

	@Override
	public void setOffsetPhone(int offset) {
		getGridMixin().setOffsetPhone(offset);
	}
	
    @Override
    public TextAlign getTextAlign() {
        return getTextAlignMixin().getCssName();
    }

    @Override
    public void setTextAlign(TextAlign align) {
        getTextAlignMixin().setCssName(align);
    }
    
    @Override
    public void setMargin(double margin) {
        getElement().getStyle().setMargin(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginTop(final double margin) {
        getElement().getStyle().setMarginTop(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginLeft(final double margin) {
        getElement().getStyle().setMarginLeft(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginRight(final double margin) {
        getElement().getStyle().setMarginRight(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginBottom(final double margin) {
        getElement().getStyle().setMarginBottom(margin, Style.Unit.PX);
    }

    @Override
    public void setPadding(double padding) {
        getElement().getStyle().setPadding(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingTop(final double padding) {
        getElement().getStyle().setPaddingTop(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingLeft(final double padding) {
        getElement().getStyle().setPaddingLeft(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingRight(final double padding) {
        getElement().getStyle().setPaddingRight(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingBottom(final double padding) {
        getElement().getStyle().setPaddingBottom(padding, Style.Unit.PX);
    }
    
    @Override
    public void setOpacity(double opacity) {
        getElement().getStyle().setOpacity(opacity);
    }

    @Override
    public double getOpacity() {
        return Double.parseDouble(getElement().getStyle().getOpacity());
    }

    @Override
    public void setFontSize(String fontSize) {
        getFontSizeMixin().setFontSize(fontSize);
    }

    @Override
    public String getFontSize() {
        return getFontSizeMixin().getFontSize();
    }

    @Override
    public void setFontSize(double fontSize, Style.Unit unit) {
        getFontSizeMixin().setFontSize(fontSize, unit);
    }

    @Override
    public void setDisplay(Display display) {
        getFlexboxMixin().setDisplay(display);
    }

    @Override
    public void setFlexDirection(FlexDirection flexDirection) {
        getFlexboxMixin().setFlexDirection(flexDirection);
    }

    @Override
    public void setFlex(Flex flex) {
        getFlexboxMixin().setFlex(flex);
    }

    @Override
    public void setFlexGrow(Integer flexGrow) {
        getFlexboxMixin().setFlexGrow(flexGrow);
    }

    @Override
    public void setFlexShrink(Integer flexShrink) {
        getFlexboxMixin().setFlexShrink(flexShrink);
    }

    @Override
    public void setFlexBasis(String flexBasis) {
        getFlexboxMixin().setFlexBasis(flexBasis);
    }

    @Override
    public void setFlexOrder(Integer flexOrder) {
        getFlexboxMixin().setFlexOrder(flexOrder);
    }

    @Override
    public void setFlexWrap(FlexWrap flexWrap) {
        getFlexboxMixin().setFlexWrap(flexWrap);
    }

    @Override
    public void setFlexAlignContent(FlexAlignContent flexAlignContent) {
        getFlexboxMixin().setFlexAlignContent(flexAlignContent);
    }

    @Override
    public void setFlexAlignSelf(FlexAlignSelf flexAlignSelf) {
        getFlexboxMixin().setFlexAlignSelf(flexAlignSelf);
    }

    @Override
    public void setFlexAlignItems(FlexAlignItems flexAlignItems) {
        getFlexboxMixin().setFlexAlignItems(flexAlignItems);
    }

    @Override
    public void setFlexJustifyContent(FlexJustifyContent flexJustifyContent) {
        getFlexboxMixin().setFlexJustifyContent(flexJustifyContent);
    }

    @Override
    public void setGwtDisplay(Style.Display display) {
        getFlexboxMixin().setGwtDisplay(display);
    }

	@Override
	public void setHideOn(HideOn hideOn) {
		getHideOnMixin().setCssName(hideOn);
	}

	@Override
	public HideOn getHideOn() {
		return getHideOnMixin().getCssName();
	}

	@Override
	public void setShowOn(ShowOn showOn) {
		getShowOnMixin().setCssName(showOn);
	}

	@Override
	public ShowOn getShowOn() {
		return getShowOnMixin().getCssName();
	}

}
