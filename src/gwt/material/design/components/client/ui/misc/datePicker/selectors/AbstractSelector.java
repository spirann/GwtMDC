package gwt.material.design.components.client.ui.misc.datePicker.selectors;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.base.interfaces.HasNext;
import gwt.material.design.components.client.base.interfaces.HasPrevious;
import gwt.material.design.components.client.base.interfaces.HasSelection;
import gwt.material.design.components.client.base.interfaces.HasType;
import gwt.material.design.components.client.base.mixin.HasSelectionMixin;
import gwt.material.design.components.client.base.mixin.TypeMixin;
import gwt.material.design.components.client.constants.CssName;
import gwt.material.design.components.client.constants.DatePickerType;
import gwt.material.design.components.client.events.SelectionEvent.HasSelectionHandlers;
import gwt.material.design.components.client.events.SelectionEvent.SelectionHandler;
import gwt.material.design.components.client.ui.html.Div;
import gwt.material.design.components.client.utils.helper.ObjectHelper;

public abstract class AbstractSelector<D, W extends AbstractItem<?>> extends Div
		implements HasType<DatePickerType>, HasSelection<D>, HasSelectionHandlers<D> {

	protected final TypeMixin<AbstractSelector<D, W>, DatePickerType> typeMixin = new TypeMixin<>(this,
			DatePickerType.RANGE);
	protected final HasSelectionMixin<AbstractSelector<D, W>, D> selectionMixin = new HasSelectionMixin<>(this);

	protected final Map<Object, W> items = new LinkedHashMap<>();
	protected final Map<Object, Collection<String>> tooltips = new LinkedHashMap<>();

	public AbstractSelector() {
		super(CssName.MDC_DATEPICKER__CONTENT);
	}

	public AbstractSelector(final String... initialClasses) {
		super(ObjectHelper.concat(new String[] { CssName.MDC_DATEPICKER__CONTENT }, initialClasses));
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		draw(getInitialValues());
	}

	@Override
	public HandlerRegistration addSelectionHandler(final SelectionHandler<D> handler) {
		return selectionMixin.addSelectionHandler(handler);
	}

	@Override
	public void setType(final DatePickerType type) {
		typeMixin.setType(type);
	}

	@Override
	public DatePickerType getType() {
		return typeMixin.getType();
	}

	@Override
	public void setSelection(final D selected) {
		drawSelection(selected);
		selectionMixin.setSelection(selected);
	}

	@Override
	public void setSelection(final D selected, boolean fireEvents) {
		drawSelection(selected);
		selectionMixin.setSelection(selected, fireEvents);
	}

	@Override
	public D getSelection() {
		return selectionMixin.getSelection();
	}

	protected abstract <V> W drawItem(final V value);

	protected abstract D getInitialValues();

	protected abstract <V> long toNumber(final V value);

	@SuppressWarnings({ "unchecked" })
	public void draw(final D values) {

		clear();
		items.clear();

		if (values == null)
			return;

		asList(values).forEach(value -> {
			final W item = drawItem(value);
			applyTooltips(value, item);
			item.addClickHandler(event -> {
				final List<Object> selectedDates = asList(getSelection());
				final boolean isSelected = selectedDates.contains(value);
				switch (getType()) {
				case RANGE:
					if (selectedDates.size() > 1) {
						final Object theFarthestFrom = getTheFarthestFrom(value, selectedDates);
						selectedDates.clear();
						selectedDates.add(theFarthestFrom);
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
		unSelectAll(getElement());

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

		final List<Comparable> ordened = (List<Comparable>) (List<?>) asList(selectedItems).stream().sorted()
				.collect(Collectors.toList());

		if (ordened.isEmpty())
			return;

		final Comparable start = ordened.get(0);
		final Comparable end = ordened.get(ordened.size() - 1);

		items.values().stream().forEach(item -> {

			final Comparable dataObComparable = item.getDataObject();
			final int startCompare = dataObComparable.compareTo(start);
			final int endCompare = dataObComparable.compareTo(end);

			if (startCompare >= 0 && endCompare <= 0) {
				item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE);
				if (startCompare == 0)
					item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_FIRST);
				if (endCompare == 0)
					item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_LAST);
			}
		});
	}

	protected final native void unSelectAll(final Element parent) /*-{
		var activeClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ACTIVE;
		var activeFirstClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ACTIVE_FIRST;
		var activeLastClass = @gwt.material.design.components.client.constants.CssName::MDC_DATEPICKER__ACTIVE_LAST;
		var jQueryChildren = $wnd.jQuery(parent).find("*");
		jQueryChildren.removeClass(activeClass);
		jQueryChildren.removeClass(activeFirstClass);
		jQueryChildren.removeClass(activeLastClass);
	}-*/;

	/**
	 * 
	 * @param value
	 * @param values
	 * @return
	 */
	protected <V> V getTheFarthestFrom(final V value, final Collection<V> values) {

		if (values == null || values.isEmpty())
			return null;

		if (values.size() == 1)
			return values.iterator().next();

		final long valueAsNumber = toNumber(value);

		return values.stream().max(Comparator.comparing(d -> calcDifference(toNumber(d), valueAsNumber))).orElse(null);

	}

	protected long calcDifference(final long valueOne, final long valueTwo) {
		return valueOne > valueTwo ? valueOne - valueTwo : valueTwo - valueOne;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> asList(final D value) {
		if (value == null)
			return new LinkedList<>();

		if (value instanceof Collection)
			return (List<T>) ((Collection<T>) value).stream().collect(Collectors.toList());

		if (value.getClass().isArray())
			return Arrays.stream((T[]) value).collect(Collectors.toList());

		return (List<T>) Arrays.asList(value);
	}

	protected <V> void applyTooltips(final V value, final W item) {
		final Collection<String> tooltips = this.tooltips.get(value);
		item.clearTooltips();
		if (tooltips != null)
			item.addTooltip(tooltips.stream().toArray(String[]::new));
	}

	public <V> void clearTooltips(final V value) {
		this.tooltips.remove(value);

		final W item = items.get(value);
		if (item != null)
			item.clearTooltips();
	}

	public <V> void addTooltips(final V value, final String... tooltip) {
		final Collection<String> tooltips = this.tooltips.getOrDefault(value, new LinkedList<String>());
		tooltips.addAll(Arrays.asList(tooltip));
		if (tooltips.isEmpty())
			this.tooltips.remove(value);
		else
			this.tooltips.put(value, tooltips);

		final W item = items.get(value);
		if (item != null)
			item.addTooltip(tooltips.stream().toArray(String[]::new));
	}
}
