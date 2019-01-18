/*
*
*
*
*/
function MDCTree(element, options) {
		var defaults = {
			/**
			* Without selection
		 	* <code>none</code> or <code>null</code>
		 	* 
		 	* Checkbox selection
		 	* <code>filter</code>
		 	* 
		 	* Radio button selection
		 	* <code>choice</code>
		 	*/
			selectionType : null,
			// Class for selector element
			selectorClass : 'mdc-tree--selector',
			// Class for show filtered text
			filteredTextClass : 'mdc-tree--filtered-text',
			// Expand class  for to expand action
			expandClass : 'mdc-tree--collapse',
			// Material design icon for to expand action
			expandIcon : 'chevron_right',
			// Expand class  for to collapse action
	     	collapseClass : 'mdc-tree--expand',
			// Material design icon for to collapse action
	    	collapseIcon : 'expand_more',
			// Class  for to name element
			nameClass : 'mdc-tree--name',
			// Limit of filtered items to expand
			maxToAutoExpand : 10,
			// Event for selection action
			onSelect : null,
			// Event for filter action
			onFilter : null
		};

    	this.root = element;	
    	this.options = $.extend(defaults, options);
    	this.data = null;	
    	this.last_selected_item;
    	this.textFilter = '';
    
    /**
	* Set data
	*/   
    this.setData = function (data) {
    	this.clear(); 	
    	this.data = data;
    	this.draw();
	}
	
	/**
	* Set data
	*/   
    this.setOptions = function (options) {
    
    	// Clear selected items
	   	if(this.options.selectionType !== options.selectionType){
    		this.unselectAll();
    		this.last_selected_item = null;   
    	}
    		
    	this.options = $.extend(defaults, options);
		this.draw();
	}
	
	/**
	* Filter all items
	*/   
	this.filter = function(text){
    	this.textFilter = text;		
    	this.draw();
    	var count = this.countFilteredItems();
    	
    	if(count > 0 && count <= this.options.maxToAutoExpand) 
    		this.expandAll();
    		
    	if(this.options.onFilter)
    		this.options.onFilter();	
	}
	
	/**
	* Remove all data
	*/
	this.clear = function () {
    	$(this.root).empty();
    	this.data = [];
    	this.last_selected_item = null;    	
	}
		
	/**
	* Draw all data items
	*/
	this.draw = function () {
    	$(this.root).empty();
    	const _this = this;
		const orphansArray = this.orphans().sort(function(a, b){return a.name.toLowerCase().localeCompare(b.name.toLowerCase());});
  		if (orphansArray.length) {
    		const items = this.toDraw(orphansArray),
        	ul = document.createElement('ul');
    		items.forEach(function(li) {
      			$(ul).append(li);
    		});
    		$(this.root).append(ul);
  		}
  		
  		// Atualiza os items selecionados
  		this.select(this.getSelectedItems());		
  		
  		// Open first level
  		// Do not open all levels, because it`s slow 
  		$(this.root).find('.' + this.options.expandClass).click();
	}

	this.toDraw = function(items){
		const _this = this;
		return items.filter(function(item){
		
			var defined_text_filter = _this.textFilter && _this.textFilter.length > 0;
  			var has_text_filter = _this.hasTextFilter(item);
			var has_child_with_text_filter = _this.hasChildWithTextFilter(item);
		
			return !defined_text_filter || has_text_filter || has_child_with_text_filter;	
		
		}).map(function(item){
    			return _this.generateListItem(item);
    	});
	}
	
	/**
	* List all orphan items
	*/
	this.orphans = function () {
  		return this.data.filter(function(item) {
    		return item.parent === null || item.parent === undefined;
  		});
	}
	
	/**
	* List all selected items
	*/
	this.getSelectedItems = function () {
  		return this.data.filter(function(item) {
    		return item.selected;
  		});
	}
	
	/**
	* Return the number of selected items
	*/	
	this.countSelectedItems = function(){
		return this.getSelectedItems().length;
	}

	/**
	* List all filtered items
	*/
	this.getFilteredItems = function(){
		const _this = this;
		return this.data.filter(function(item) {
    		return _this.hasTextFilter(item);
  		});
	}
	
	/**
	* Return the number of filtered items
	*/	
	this.countFilteredItems = function(){
		return this.getFilteredItems().length;
	}
	
	/**
	* Return true if the item has at least one item
	*/
	this.hasChildren = function (parentId) {
  		return this.data.some(function(item) {
    		return item.parent === parentId;
  		});
	}
	
	/**
	* Return true if the item has at least one unselected item
	*/
	this.hasUnselectedChildren = function (parentId) {
  		return this.data.some(function(item) {
    		return item.parent === parentId && !item.selected;
  		});
	}

	/**
	* List direct children of an item
	*/
	this.getChildren = function (parentId) {
  		return this.data.filter(function(item) {
    		return item.parent === parentId;
  		});
	}
	
	/**
	* List all children of an item
	* This goes to the last level
	*/
	this.getAllChildren = function (parentId) {		
		const _this = this;
  		var children = this.data.filter(function(item) {
    		return item.parent === parentId;
  		});
  		children.forEach(function(item){
  			children = children.concat(_this.getAllChildren(item.id));
  		});
  		return children;
	}
	
	/**
	* List all parents of an item
	* This goes to the first level
	*/
	this.getAllParents = function (parent) {
		const _this = this;
  		var parents = this.data.filter(function(item) {
    		return item.id === parent;
  		});
  		parents.forEach(function(item){
  			parents = parents.concat(_this.getAllParents(item.parent));
  		});
  		return parents;
	}

	/**
	* Create an element for the tree
	*/
	this.generateListItem = function (item) {  
		const _this = this;
  		const li = document.createElement('li');
  		li.id = 'item-' + item.id;
  		  		
  		const li_header = document.createElement('div');
  		
  		const span = document.createElement('span');
  		$(span).addClass(this.options.nameClass);
  		$(span).html(item.name);
  	
  		if (this.hasChildren(item.id)) {
  		
    		const a = document.createElement('i');
    		a.href = '#';
    		$(a).html(this.options.expandIcon);
    		$(a).addClass(this.options.expandClass);
    		$(a).click(function(){
    			if($(a).hasClass(_this.options.expandClass))
    				_this.expand(item, a, li);
    			else if($(a).hasClass(_this.options.collapseClass))
    				_this.collapse(item, a, li);
    		});
    		$(li_header).append(a); 
    		
    		$(span).dblclick(function(){
    			$(a).click();
    		});
  		}
  		
  		if(item.onClick)
  			$(span).click(item.onClick);
  		
  		if(this.options.selectionType) {
  			
  			var action;
  			
  			// Create action element
  			if(this.options.selectionType === 'filter')
  				action = this.getCheckbox(item);
  			else if(this.options.selectionType === 'choice') 
  				action = this.getRadio(item);
  				
  			if(action)  {	
  			
  				// Append the action element
  				$(li_header).append(action);  
  				
  				// It is to remoe the focus after the click
  				// and remove the waves circle
  				$(action).click(function(){
  					document.activeElement.blur();	
  				});
  				
  				// Add change event
  				$(li_header).find('input').change(function() {
  					// Update the item
  					item.selected = this.checked;
  					
  					// //////////////////////////////////////////////
  					// Update item  and throws the events	
  					// //////////////////////////////////////////////
  					_this.updateItemState(item);
  					
  					if(_this.options.selectionType === 'filter') {
  						// Add events if action is a checkbox  						
  							  						
  						// //////////////////////////////////////////////
  						// Update all children and throws the events	
  						// //////////////////////////////////////////////
  						_this.updateChildrenState(item);
						
						// //////////////////////////////////////////////
						// Update all parents and throws the events		
						// //////////////////////////////////////////////
						_this.updateParentsState(item);  						
  							
  					} else if(_this.options.selectionType === 'choice') {
  						// Add events if action is a radio button
  						
  						if(item.selected) {  						
  							// Notify last selected item that who is unselected  						
  							if(_this.last_selected_item){
  								var last_selected_index = jQuery.inArray(_this.last_selected_item, _this.data);
								_this.data[last_selected_index].selected = false;
								if(_this.last_selected_item.onUnselect)
  									_this.last_selected_item.onUnselect();  								
  							}
  						
  							_this.last_selected_item = item;
  						} else {
  							if(_this.last_selected_item && _this.last_selected_item.id === item.id)
  								_this.last_selected_item = null
  						}
  					}  	
  					
  					_this.notifySelection();				  							
  				});  				
  				
  				if(this.last_selected_item) {	  	
  					item.selected = this.last_selected_item.id === item.id;  					  				
				}
				// Set type of action, it's necessary because of css file				  					  					
  				$(action).attr('type', this.options.selectionType);
  				// Select the item if it's selected
  				$(li_header).find('input').prop('checked', item.selected);	 
  			
  			}
  		}
  		
  		$(li_header).append(span);  		
  		$(li).append(li_header);
  		
  		// //////////////////////////////////////////////////////////////////////////
  		// Apply text filter
  		// //////////////////////////////////////////////////////////////////////////  	
  		var defined_text_filter = this.textFilter && this.textFilter.length > 0;
  		var has_text_filter = this.hasTextFilter(item);
		var has_child_with_text_filter = this.hasChildWithTextFilter(item);		
  		
  		if(has_text_filter && defined_text_filter){
			span.innerHTML = highlightsSearchedText(item.name, this.textFilter, this.options.filteredTextClass);
  			//$(span).addClass(this.options.filteredTextClass);
  		} 
  		
  		if(has_text_filter || has_child_with_text_filter || !defined_text_filter)
  			$(li).css('display','');
  		else
  			$(li).css('display','none');
  		  		
  		return li;
	}
	
	/**
	* Select all items
	*/
	this.selectAll = function (){
		this.select(this.orphans());
		this.notifySelection();
	}
	
	/**
	* Select a list of items
	*/
	this.select = function (itemsToSelect) {
		if(this.options.selectionType === 'filter') {
			const _this = this;
			const toSelectArray = [];
			
			itemsToSelect.forEach(function(item){
			
				if(item.parent) {
			
					// List all parents 
					var itemParents = _this.getAllParents(item.parent);
					// Verifies if any parent will be selected
					var hasParentToSelect = itemParents.some(function(parent){
						return itemsToSelect.includes(parent);
					});
					// If any parent will be selected
					// so this item should be selected
					if(!hasParentToSelect)
						toSelectArray.push(item);
					
				} else {
					// If the item has not parent,
					// so I add it to the list
					toSelectArray.push(item);
				}	
			});
		
			toSelectArray.forEach(function(item){
			
				// If the developer did not set to true, I do it
				item.selected = true;
				
				// //////////////////////////////////////////////
  				// Update item  and throws the events	
  				// //////////////////////////////////////////////
  				_this.updateItemState(item);
				
				// //////////////////////////////////////////////
  				// Update all children and throws the events	
  				// //////////////////////////////////////////////
  				_this.updateChildrenState(item);
				
				// //////////////////////////////////////////////
				// Update all parents and throws the events		
				// //////////////////////////////////////////////
				_this.updateParentsState(item);  	
			
			});
			
		} else if(this.options.selectionType === 'choice' && itemsToSelect.length > 0) {
			// //////////////////////////////////////////////
  			// Update item  and throws the events	
  			// //////////////////////////////////////////////
  			itemsToSelect[0].selected = true;
  			this.updateItemState(itemsToSelect[0]);
		}		
	}
	
	/**
	* Unselect all items
	*/
	this.unselectAll = function (){
		const _this = this;
		this.orphans().forEach(function(item){
		
				// If the developer did not set to false, I do it
				item.selected = false;
				
				// //////////////////////////////////////////////
  				// Update item  and throws the events	
  				// //////////////////////////////////////////////
  				_this.updateItemState(item);
		
				// //////////////////////////////////////////////
  				// Update all children and throws the events	
  				// //////////////////////////////////////////////
  				_this.updateChildrenState(item);	
  				
			});	
		
		this.notifySelection();
	}
	
	/**
	* Unselect all list of items
	*/
	this.unselect = function (itemsToUnselect) {
		const _this = this;
		if(this.options.selectionType === 'filter' && itemsToUnselect) {
		
			itemsToUnselect.forEach(function(item){
				// If the developer did not set to false, I do it
				item.selected = false;
				
				// //////////////////////////////////////////////
  				// Update item  and throws the events	
  				// //////////////////////////////////////////////
  				_this.updateItemState(item);
		
				// //////////////////////////////////////////////
  				// Update all children and throws the events	
  				// //////////////////////////////////////////////
  				_this.updateChildrenState(item);	
						
				// //////////////////////////////////////////////
				// Update all parents and throws the events		
				// //////////////////////////////////////////////
				_this.updateParentsState(item);
			});	
		
		} else if(options.selectionType === 'choice' && itemsToUnselect) {
			itemsToUnselect.forEach(function(item){
				// If the developer did not set to false, I do it
				item.selected = false;
				
				// //////////////////////////////////////////////
  				// Update item  and throws the events	
  				// //////////////////////////////////////////////
  				_this.updateItemState(item);
			});	
		}
	}
	
	this.notifySelection = function() {
  		if(this.options.onSelect)
  			this.options.onSelect();		
	}
	
	/**
	* Expand all items
	*/
	this.expandAll = function() {		
		var count = 0;
		do {			
  			$(this.root).find('.' + this.options.expandClass).click();
  			count = $(this.root).find('.' + this.options.expandClass).length;
		} while(count > 0);		
	}

	/**
	* Expand event
	* It's called when you click at arrow
	*/
	this.expand = function (item, element, parent) {
  		const _this = this, 
  			id = parent.id.replace('item-', ''),
        	kids = this.getChildren(id).sort(function(a, b){
        		
        		// First order by no children items
        		// After by name
        		
        		var aHasChildren = _this.hasChildren(a.id);
        		var bHasChildren = _this.hasChildren(b.id);
        		
        		if(aHasChildren && !bHasChildren)
        			return 1;
        		if(!aHasChildren && bHasChildren)
        			return -1;	
        		
        		return a.name.toLowerCase().localeCompare(b.name.toLowerCase());
        	}),
        	items = this.toDraw(kids),
        	ul = document.createElement('ul');
  		items.forEach(function(li) {
			$(ul).append(li);
  		});
		$(parent).append(ul);
		$(element).removeClass(this.options.expandClass);
		$(element).addClass(this.options.collapseClass);
		$(element).html(this.options.collapseIcon);
	}

	/**
	* Collapse event
	* It's called when you click at arrow
	*/
	this.collapse = function (item, element, parent) {
  		$(parent).find('ul').remove();
		$(element).removeClass(this.options.collapseClass);
		$(element).addClass(this.options.expandClass);
		$(element).html(this.options.expandIcon);
	}
	
	this.hasTextFilter = function (item){
		var defined_text_filter = this.textFilter && this.textFilter.length > 0;
		return defined_text_filter && item.name && item.name.toLowerCase().indexOf(this.textFilter.toLowerCase()) > -1;
	}
	
	this.hasChildWithTextFilter = function (item){
		const _this = this;
		return this.getAllChildren(item.id).some(function(child) {
    		return _this.hasTextFilter(child);
  		});
	}
	
	/**
	* Update item and throws the events
	*/
	this.updateItemState = function (item){
		var item_index = jQuery.inArray(item, this.data);
  		this.data[item_index].selected = item.selected;
  				 	
  		// Throw the event 	
  		if(item.onSelect && item.selected) 
  			item.onSelect();  							
  		else if	(item.onUnselect && !item.selected) 
  			item.onUnselect();	
	}
	
	/**
	* Update all children and throws the events
	*/
	this.updateChildrenState = function (item){
	
		if(!item || !item.id)
			return;	
		
		const _this = this; 
		
  		$('#item-' + item.id).find('ul input').prop('checked', item.selected);
  		this.getAllChildren(item.id).forEach(function(child){  		
  							
			// Update the itens								
  			var child_index = jQuery.inArray(child, _this.data);
			_this.data[child_index].selected = item.selected;
			// Throw the events
			if(child.onSelect && item.selected) 
  				child.onSelect();  							
  			else if(child.onUnselect && !item.selected) 
  				child.onUnselect();
  													
		});
	}
	
	/**
	* Update all children and throws the events
	*/
	this.updateParentsState = function (item){

		if(!item || !item.parent)
			return;	
	
		const _this = this; 
		
		this.getAllParents(item.parent).forEach(function(parent){
		  				
  			var parentIsSelected = item.selected && !_this.hasUnselectedChildren(parent.id);			
  			// Update the itens								
  			var parent_index = jQuery.inArray(parent, _this.data);
			_this.data[parent_index].selected = parentIsSelected;		
			// Throws the events
			if(parent.onSelect && parentIsSelected) 
  				parent.onSelect();  							
  			else if(parent.onUnselect && !parentIsSelected) 
  				parent.onUnselect();					
			
			// Update drawed parent
			// To select the parent, the parent can not has unslected children
			$('#item-' + parent.id + ' > div input').prop('checked', parentIsSelected);
																					
		});
	}
	
	/**
	* Create a checkbox for filter type
	*/
	this.getCheckbox = function (item){		
		var checkbox = document.createElement('div');
    	$(checkbox).addClass('mdc-checkbox');
    	$(checkbox).addClass(this.options.selectorClass);
    	
    	var input = document.createElement('input');
    	$(input).addClass('mdc-checkbox__native-control');
    	$(input).attr('id', 'chk_' + item.id);
    	$(input).attr('type', 'checkbox');
    	$(checkbox).append(input);
    	
		var checkbox__background = document.createElement('div');
    	$(checkbox__background).addClass('mdc-checkbox__background');    	
    	$(checkbox).append(checkbox__background);
    	
		var checkbox__checkmark = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    	$(checkbox__checkmark).addClass('mdc-checkbox__checkmark');
    	$(checkbox__checkmark).attr('viewBox', '0 0 24 24');
    	$(checkbox__background).append(checkbox__checkmark);
    	
    	var checkbox__checkmark_path = document.createElementNS('http://www.w3.org/2000/svg', 'path');
    	$(checkbox__checkmark_path).addClass('mdc-checkbox__checkmark-path');
    	$(checkbox__checkmark_path).attr('fill', 'none');
    	$(checkbox__checkmark_path).css('stroke', 'var(--mdc-checkbox-on-checked-color)');
    	$(checkbox__checkmark_path).attr('d', 'M1.73,12.91 8.1,19.28 22.79,4.59');
    	$(checkbox__checkmark).append(checkbox__checkmark_path);
    	
    	var checkbox__mixedmark = document.createElement('div');
    	$(checkbox__mixedmark).addClass('mdc-checkbox__mixedmark');
    	$(checkbox__mixedmark).css('border-color', 'var(--mdc-checkbox-on-checked-color)');
    	$(checkbox__background).append(checkbox__mixedmark);
    	
    	return checkbox;
	}
	
	/**
	* Create a radio button for choice type
	*/
	this.getRadio = function (item){		
		var radio = document.createElement('div');
    	$(radio).addClass('mdc-radio');
    	$(radio).addClass(this.options.selectorClass);
    	
    	var input = document.createElement('input');
    	$(input).addClass('mdc-radio__native-control');
    	$(input).attr('id', 'radio_' + item.id);
    	$(input).attr('type', 'radio');
    	$(input).attr('name', this.root.getAttribute('id'));
    	$(radio).append(input);
    	
		var radio__background = document.createElement('div');
    	$(radio__background).addClass('mdc-radio__background');
    	$(radio).append(radio__background);
    	
		var radio__outer_circle = document.createElement('div');
    	$(radio__outer_circle).addClass('mdc-radio__outer-circle');
    	$(radio__background).append(radio__outer_circle);
    	
    	var radio__inner_circle = document.createElement('div');
    	$(radio__inner_circle).addClass('mdc-radio__inner-circle');
    	$(radio__background).append(radio__inner_circle);
    	
    	return radio;
	}
	
	function highlightsSearchedText(text, searchedText, highlightsClass) {
	
		const filter_lower = searchedText; 
		const to_replace = [];
		var html = text;
		var name_lower = text.toLowerCase();
		var name =text;
		var indexOf = name_lower.indexOf(filter_lower);
		var count = 0;
		do {		
			var finded = name.substring(indexOf, indexOf + filter_lower.length);			
			if(!to_replace.includes(finded)){
				var re = new RegExp(finded, 'g');
				html = html.replace(re, indexToRemove(count));
				to_replace.push(finded);
				count++;
			}			
			name_lower = name_lower.substring(indexOf + filter_lower.length);
			name = name.substring(indexOf + filter_lower.length);		
			indexOf = name_lower.indexOf(filter_lower);						
		}	while(indexOf > -1);

		for(var i = 0; i < to_replace.length; i++){
			var finded = indexToRemove(i);
			var re = new RegExp(finded, 'g');
			html = html.replace(re, '<span class=" '+ highlightsClass + '">' + to_replace[i] + '</span>');
		}		
		
		return html;
	} 
	
	function indexToRemove(index){
		return '_remove_' + index + '_remove_';
	}
	
}
