package gwt.material.design.components.client.ui.misc.datePicker.selectors;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasNext;
import gwt.material.design.components.client.base.interfaces.HasPrevious;
import gwt.material.design.components.client.base.interfaces.HasSelection;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.HasSelectionMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.base.widget.MaterialWidget;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.DatePickerType;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Div;

public abstract class AbstractSelector<D, W extends MaterialWidget> extends Div
		implements HasType<DatePickerType>, HasSelection<D>, HasSelectionHandlers<D> {

	protected final TypeMixin<AbstractSelector<D, W>, DatePickerType> typeMixin = new TypeMixin<>(this,
			DatePickerType.SINGLE);
	protected final HasSelectionMixin<AbstractSelector<D, W>, D> selectionMixin = new HasSelectionMixin<>(this);
	protected final Map<Object, W> items = new LinkedHashMap<>();

	public AbstractSelector() {
		super();
	}

	public AbstractSelector(final String... initialClasses) {
		super(initialClasses);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		draw(getInitialValues());
	}

	@Override
	public HandlerRegistration addSelectionHandler(SelectionHandler<D> handler) {
		return selectionMixin.addSelectionHandler(handler);
	}

	@Override
	public void setType(DatePickerType type) {
		typeMixin.setType(type);
	}

	@Override
	public DatePickerType getType() {
		return typeMixin.getType();
	}

	@Override
	public void setSelection(D selected) {
		drawSelection(selected);
		selectionMixin.setSelection(selected);
	}

	@Override
	public void setSelection(D selected, boolean fireEvents) {
		drawSelection(selected);
		selectionMixin.setSelection(selected, fireEvents);
	}

	@Override
	public D getSelection() {
		return selectionMixin.getSelection();
	}

	protected abstract <V> W drawItem(final V value);

	protected abstract D getInitialValues();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void draw(final D values) {
		
		clear();
		items.clear();
		
		if(values == null)
			return;
		
		asList(values).forEach(value -> {
			final W item = drawItem(value);
			item.addClickHandler(event -> {

				final List selectedDates = asList(getSelection());
				final boolean isSelected = selectedDates.contains(value);

				switch (getType()) {
				case RANGE:
					if (selectedDates.size() > 1) {
						selectedDates.clear();
						selectedDates.add(getTheFarthestFrom(value, selectedDates));
					}
					break;
				case SINGLE:
					selectedDates.clear();
					break;
				default:
				case MULTIPLE:
					break;
				}
				
				if (isSelected)
					selectedDates.remove(value);
				else
					selectedDates.add(value);

				if (values.getClass().isArray())
					setSelection((D) selectedDates.stream().toArray(Object[]::new));
				else
					setSelection((D) selectedDates.iterator().next());

			});
			items.put(value, item);
			add(item);
		});
		drawSelection(getSelection());
	}

	protected void drawSelection(final D values) {
		unSelectAll();

		if (values != null)
			switch (getType()) {
			case RANGE:
				drawRangeSelect(values);
				break;
			case MULTIPLE:
				drawMultipleSelect(values);
				break;
			case SINGLE:
			default:
				drawSingleSelect(values);
				break;
			}
	}

	@SuppressWarnings("unchecked")
	protected void drawSingleSelect(final D selectedItems) {

		final W item = (W) asList(selectedItems).stream().filter(selectedItem -> items.containsKey(selectedItem))
				.findAny().map(selectedItem -> items.get(selectedItem)).orElse(null);

		if (item != null) {
			item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE);
			item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_FIRST);
			item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_LAST);
		}
	}

	protected void drawMultipleSelect(final D selectedItems) {

		final List<?> collection = asList(selectedItems);
		collection.stream().filter(selectedItem -> items.containsKey(selectedItem)).forEach(selectedItem -> {

			final W item = items.get(selectedItem);
			item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE);

			if (selectedItem instanceof HasPrevious) {
				final HasPrevious<?> hasPrevious = (HasPrevious<?>) selectedItem;
				if (!collection.contains(hasPrevious.previous()))
					item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_FIRST);
			}

			if (selectedItem instanceof HasNext) {
				final HasNext<?> hasNext = (HasNext<?>) selectedItem;
				if (!collection.contains(hasNext.next()))
					item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_LAST);
			}

		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void drawRangeSelect(final D selectedItems) {

		final List<Comparable> ordened = (List<Comparable>) asList(selectedItems).stream().sorted()
				.collect(Collectors.toList());

		final Comparable start = ordened.get(0);
		final Comparable end = ordened.get(ordened.size() - 1);

		items.values().stream().forEach(item -> {

			final Comparable dataObComparable = item.getDataObject();

			final int startCompare = dataObComparable.compareTo(start);
			final int endCompare = dataObComparable.compareTo(end);

			if (startCompare >= 0 || endCompare <= 0) {
				item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE);
				if (startCompare == 0)
					item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_FIRST);
				if (endCompare == 0)
					item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_LAST);
			}
		});
	}

	protected final native void unSelectAll() /*-{
		var activeClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ACTIVE;
		var activeFirstClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ACTIVE_FIRST;
		var activeLastClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ACTIVE_LAST;
		$wnd.jQuery(this).find().removeClass(activeClass);
		$wnd.jQuery(this).find().removeClass(activeFirstClass);
		$wnd.jQuery(this).find().removeClass(activeLastClass);
	}-*/;

	protected abstract <V> long toNumber(final V value);

	protected <V> V getTheFarthestFrom(final V value, final Collection<V> values) {

		if (values == null || values.isEmpty())
			return null;

		if (values.size() == 1)
			return values.iterator().next();

		return values.stream().max(Comparator.comparing(d -> calcDifference(toNumber(d), toNumber(value))))
				.orElse(null);

	}

	protected long calcDifference(final long valueOne, final long valueTwo) {
		return valueOne > valueTwo ? valueOne - valueTwo : valueTwo - valueOne;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List asList(final D value) {
		if(value == null)
			return new LinkedList<>();
		
		if(value instanceof Collection)
			return (List) ((Collection) value).stream().collect(Collectors.toList());
		
		if(value.getClass().isArray())
			return Arrays.asList((Object[]) value);
		
		return Arrays.asList(value);
	}
}
