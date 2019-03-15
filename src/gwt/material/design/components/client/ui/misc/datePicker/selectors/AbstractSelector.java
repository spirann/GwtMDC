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

public abstract class AbstractSelector<D extends Comparable<D> & HasPrevious<D> & HasNext<D>, W extends AbstractItem<D>>
		extends Div implements HasType<DatePickerType>, HasSelection<Collection<D>>, HasSelectionHandlers<Collection<D>> {

	protected final TypeMixin<AbstractSelector<D, W>, DatePickerType> typeMixin = new TypeMixin<>(this,
			DatePickerType.RANGE);
	protected final HasSelectionMixin<AbstractSelector<D, W>, Collection<D>> selectionMixin = new HasSelectionMixin<>(this);

	protected final Map<D, W> items = new LinkedHashMap<>();
	protected final Map<D, Collection<String>> tooltips = new LinkedHashMap<>();

	private D minValue;
	private D maxValue;

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
	public HandlerRegistration addSelectionHandler(final SelectionHandler<Collection<D>> handler) {
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
	public void setSelection(final Collection<D> selected) {
		drawSelection(selected);
		selectionMixin.setSelection(selected);
	}

	@Override
	public void setSelection(final Collection<D> selected, boolean fireEvents) {
		drawSelection(selected);
		selectionMixin.setSelection(selected, fireEvents);
	}

	@Override
	public Collection<D> getSelection() {
		return selectionMixin.getSelection();
	}

	protected abstract W drawItem(final D value);

	protected abstract Collection<D> getInitialValues();

	protected abstract long toNumber(final D value);

	public void draw(final Collection<D> values) {

		clear();
		items.clear();

		if (values == null)
			return;

		values.forEach(value -> {
			
			final W item = drawItem(value);
			applyMinMaxValues(item);
			applyTooltips(value, item);
			item.addClickHandler(event -> {
				
				if(!item.isEnabled())
					return;
				
				final Collection<D> selectedDates = getSelection() == null ? new LinkedList<>() : new LinkedList<>(getSelection());				
				final boolean isSelected = selectedDates.contains(value);
				
				switch (getType()) {
				case RANGE:
					if (selectedDates.size() > 1) {
						final D theFarthestFrom = getTheFarthestFrom(value, selectedDates);
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

				setSelection(selectedDates);			
			});
			items.put(value, item);
			add(item);
		});
		drawSelection(getSelection());
	}

	protected void drawSelection(final Collection<D> values) {
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

	protected void drawSingleSelect(final Collection<D> selectedItems) {
		final W item = (W) selectedItems.stream()
				.filter(selectedItem -> items.containsKey(selectedItem)).findAny()
				.map(selectedItem -> items.get(selectedItem)).orElse(null);

		if (item != null) {
			item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE);
			item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_FIRST);
			item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_LAST);
		}
	}

	protected void drawMultipleSelect(final Collection<D> collection) {

		collection.stream().filter(selectedItem -> items.containsKey(selectedItem)).forEach(selectedItem -> {

			final W item = items.get(selectedItem);
			item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE);

			if (!collection.contains(selectedItem.previous()))
				item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_FIRST);

			if (!collection.contains(selectedItem.next()))
				item.addStyleName(CssName.MDC_DATEPICKER__ACTIVE_LAST);
			
		});
	}

	protected void drawRangeSelect(final Collection<D> selectedItems) {

		final List<D> ordened = selectedItems.stream().sorted().collect(Collectors.toList());

		if (ordened.isEmpty())
			return;

		final D start = ordened.get(0);
		final D end = ordened.get(ordened.size() - 1);

		items.values().stream().forEach(item -> {

			final D dataObComparable = item.getDataObject();
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
	protected D getTheFarthestFrom(final D value, final Collection<D> values) {

		if (values == null || values.isEmpty())
			return null;

		if (values.size() == 1)
			return values.iterator().next();

		final long valueAsNumber = toNumber(value);

		return values.stream().max(Comparator.comparing(d -> calcDifference(toNumber(d), valueAsNumber)))
				.orElse(null);

	}

	protected long calcDifference(final long valueOne, final long valueTwo) {
		return valueOne > valueTwo ? valueOne - valueTwo : valueTwo - valueOne;
	}

	protected void applyTooltips(final D value, final W item) {
		final Collection<String> tooltips = this.tooltips.get(value);
		item.clearTooltips();
		if (tooltips != null)
			item.addTooltip(tooltips.stream().toArray(String[]::new));
	}

	public void clearTooltips(final D value) {
		this.tooltips.remove(value);
		final W item = items.get(value);
		if (item != null)
			item.clearTooltips();
	}

	public void addTooltips(final D value, final String... tooltip) {
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

	protected void applyMinMaxValues(final W widget) {
		final long min = minValue == null ? 0 : toNumber(minValue);
		final long max = maxValue == null ? Long.MAX_VALUE : toNumber(maxValue);
		final long value = toNumber(widget.getDataObject());		
		widget.setEnabled(value >= min && value <= max);
	}

	public D getMinValue() {
		return minValue;
	}

	public void setMinValue(final D minValue) {
		this.minValue = minValue;
		if(initialized)
			items.values().forEach(item -> applyMinMaxValues(item));
	}

	public D getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(final D maxValue) {
		this.maxValue = maxValue;
		if(initialized)
			items.values().forEach(item -> applyMinMaxValues(item));
	}

}
