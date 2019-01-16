
/* data is in flat array format like this:
[
  {
    id: 'abc',
    name: 'ABCDE',
    parent: null,
    onClick: function(e) {}
  },
  {
    id: 'def',
    name: 'DEFGH',
    parent: 'abc',
    onClick: function(e) {}
  }
]
*/
function MDCTree(element, opts) {

    var root = element;
    var options = opts;
    var data = null;
    var expand_class = 'mdc-tree--collapse';
    var expand_icon = 'chevron_right';
    var collapse_class = 'mdc-tree--expand';
    var collapse_icon = 'expand_more';
    var name_class ='mdc-tree--name';
    var action_class ='mdc-tree--action';
    var last_selected_item;
    var textFilter = '11';
       
    /**
	* Set data
	*/   
    this.setData = function (data) {
    	MDCTree.data = data;
    	MDCTree.last_selected_item = null;
    	$(root).empty();
		const orphansArray = orphans();
  		if (orphansArray.length) {
    		const items = orphansArray.map(generateListItem),
        	ul = document.createElement('ul');
    		items.forEach(function(li) {
      			ul.appendChild(li);
    		});
    		root.appendChild(ul);
  		}
  		
  		// Atualiza os items selecionados
  		select(selected());
  		
  		// Open first level
  		// Do not open all levels, because it`s slow 
  		$(root).find('.' + expand_class).click();
	}
	
	/**
	* Select a list of items
	*/
	this.select = function(itemsToSelect) {
		select(itemsToSelect);
	}
	
	/**
	* Unselect a list of items
	*/
	this.unselect = function(itemsToUnselect) {
		unselect(itemsToUnselect);
	}
	
	/**
	* Return all selected items
	*/
	this.getSelectedItems = function(){
		return selected();
	}
	
	/**
	* List all orphan items
	*/
	function orphans() {
  		return MDCTree.data.filter(function(item) {
    		return item.parent === null || item.parent === undefined;
  		});
	}
	
	/**
	* List all selected items
	*/
	function selected() {
  		return MDCTree.data.filter(function(item) {
    		return item.selected;
  		});
	}
	
	/**
	* Return true if the item has at least one item
	*/
	function hasChildren(parentId) {
  		return MDCTree.data.some(function(item) {
    		return item.parent === parentId;
  		});
	}
	
	/**
	* Return true if the item has at least one unselected item
	*/
	function hasUnselectedChildren(parentId) {
  		return MDCTree.data.some(function(item) {
    		return item.parent === parentId && !item.selected;
  		});
	}

	/**
	* List direct children of an item
	*/
	function getChildren(parentId) {
  		return MDCTree.data.filter(function(item) {
    		return item.parent === parentId;
  		});
	}
	
	/**
	* List all children of an item
	* This goes to the last level
	*/
	function getAllChildren(parentId) {
  		var children = MDCTree.data.filter(function(item) {
    		return item.parent === parentId;
  		});
  		children.forEach(function(item){
  			children = children.concat(getAllChildren(item.id));
  		});
  		return children;
	}
	
	/**
	* List all parents of an item
	* This goes to the first level
	*/
	function getAllParents(parent) {
  		var parents = MDCTree.data.filter(function(item) {
    		return item.id === parent;
  		});
  		parents.forEach(function(item){
  			parents = parents.concat(getAllParents(item.parent));
  		});
  		return parents;
	}

	/**
	* Create an element for the tree
	*/
	function generateListItem(item) {  
  		const li = document.createElement('li');
  		li.id = 'item-' + item.id;
  		  		
  		const li_header = document.createElement('div');
  		
  		const span = document.createElement('span');
  		span.classList.add(name_class);
  		span.innerHTML = item.name;
  		
  		if (hasChildren(item.id)) {
  		
    		const a = document.createElement('i');
    		a.href = '#';
    		a.textContent = expand_icon;
    		a.classList.add(expand_class);
    		a.addEventListener('click', expand);
    		li_header.appendChild(a);
    		
    		span.addEventListener('dblclick', function(){
    			a.click();
    		});
  		}
  		
  		if(item.onClick)
  			span.addEventListener('click', item.onClick);
  		
  		if(options.selectionType) {
  			
  			var action;
  			
  			// Create action element
  			if(options.selectionType === 'filter')
  				action = getCheckbox(item);
  			else if(options.selectionType === 'choice') 
  				action = getRadio(item);
  				
  			if(action)  {	
  			
  				// Append the action element
  				li_header.appendChild(action);		
  				
  				// It is to remoe the focus after the click
  				// and remove the waves circle
  				action.addEventListener('click', function(){
  					document.activeElement.blur();	
  				});
  				
  				// Add change event
  				$(li_header).find('input').change(function() {
  					// Update the item
  					item.selected = this.checked;
  					
  					// //////////////////////////////////////////////
  					// Update item  and throws the events	
  					// //////////////////////////////////////////////
  					updateItemState(item);
  					
  					if(options.selectionType === 'filter') {
  						// Add events if action is a checkbox  						
  							  						
  						// //////////////////////////////////////////////
  						// Update all children and throws the events	
  						// //////////////////////////////////////////////
  						updateChildrenState(item);
						
						// //////////////////////////////////////////////
						// Update all parents and throws the events		
						// //////////////////////////////////////////////
						updateParentsState(item);  						
  							
  					} else if(options.selectionType === 'choice') {
  						// Add events if action is a radio button
  						
  						if(item.selected) {  						
  							// Notify last selected item that who is unselected  						
  							if(last_selected_item){
  								var last_selected_index = jQuery.inArray(last_selected_item, MDCTree.data);
								MDCTree.data[last_selected_index].selected = false;
								if(last_selected_item.onUnselect)
  									last_selected_item.onUnselect();  								
  								}
  						
  							last_selected_item = item;
  						} else {
  							if(last_selected_item && last_selected_item.id === item.id)
  								last_selected_item = null
  						}
  					}  						  							
  				});  				
  				
  				if(last_selected_item)	  	
  					item.selected = last_selected_item.id === item.id;  					  				
				// Set type of action, it's necessary because of css file				  					  					
  				action.setAttribute('type', options.selectionType);
  				// Select the item if it's selected
  				$(li_header).find('input').prop('checked', item.selected);	 
  			
  			}
  		}
  		
  		li_header.appendChild(span);  		
  		li.appendChild(li_header);
  		
  		// Apply text filter  			
  		var defined_text_filter = textFilter && textFilter.length > 0;
  		var has_text_filter = hasTextFilter(item);
		var has_child_with_text_filter = hasChildWithTextFilter(item);		
  		
  		console.log('---------------------------------');
  		console.log('Item: ' + item.name);
  		console.log('defined_text_filter: ' + defined_text_filter);
  		console.log('has_text_filter: ' + has_text_filter);
  		console.log('has_child_with_text_filter: ' + has_child_with_text_filter);
  		
  		if(has_text_filter && defined_text_filter)
  			span.classList.add('mdc-tree--filter-text');
  		else 
  			span.classList.remove('mdc-tree--filter-text');
  		
  		if(has_text_filter || has_child_with_text_filter || !defined_text_filter)
  			$(li).css('display','');
  		else
  			$(li).css('display','none');
  		
  		return li;
	}
	
	/**
	* Select a list of items
	*/
	function select(itemsToSelect) {
	
		if(options.selectionType === 'filter') {
	
			const toSelectArray = [];
			itemsToSelect.forEach(function(item){
				if(item.parent) {
			
					// List all parents 
					var itemParents = getAllParents(item.parent);
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
  				updateItemState(item);
		
				// //////////////////////////////////////////////
  				// Update all children and throws the events	
  				// //////////////////////////////////////////////
  				updateChildrenState(item);
						
				// //////////////////////////////////////////////
				// Update all parents and throws the events		
				// //////////////////////////////////////////////
				updateParentsState(item);  	
			
			});
			
		} else if(options.selectionType === 'choice' && itemsToSelect.length > 0) {
			// //////////////////////////////////////////////
  			// Update item  and throws the events	
  			// //////////////////////////////////////////////
  			itemsToSelect[0].selected = true;
  			updateItemState(itemsToSelect[0]);
		}
	}
	
	/**
	* Unselect a list of items
	*/
	function unselect(itemsToUnselect) {
		
		if(options.selectionType === 'filter') {
		
			itemsToUnselect.forEach(function(item){
				// If the developer did not set to false, I do it
				item.selected = false;
				
				// //////////////////////////////////////////////
  				// Update item  and throws the events	
  				// //////////////////////////////////////////////
  				updateItemState(item);
		
				// //////////////////////////////////////////////
  				// Update all children and throws the events	
  				// //////////////////////////////////////////////
  				updateChildrenState(item);	
						
				// //////////////////////////////////////////////
				// Update all parents and throws the events		
				// //////////////////////////////////////////////
				updateParentsState(item);
			});	
		
		} else if(options.selectionType === 'choice' && itemsToSelect.length > 0) {
			itemsToUnselect.forEach(function(item){
				// If the developer did not set to false, I do it
				item.selected = false;
				
				// //////////////////////////////////////////////
  				// Update item  and throws the events	
  				// //////////////////////////////////////////////
  				updateItemState(item);
			});	
		}
	
	}

	/**
	* Expand event
	* It's called when you click at arrow
	*/
	function expand(event) {
  		event.preventDefault();
  		event.stopPropagation();
  		const et = event.target,
        	parent = et.parentElement.parentElement,
        	id = parent.id.replace('item-', ''),
        	kids = getChildren(id),
        	items = kids.map(generateListItem),
        	ul = document.createElement('ul');
  		items.forEach(function(li) {
    		ul.appendChild(li);
  		});
		parent.appendChild(ul);
		et.classList.remove(expand_class);
		et.classList.add(collapse_class);
		et.textContent = collapse_icon;
		et.removeEventListener('click', expand);
		et.addEventListener('click', collapse);
	}

	/**
	* Collapse event
	* It's called when you click at arrow
	*/
	function collapse(event) {
		event.preventDefault();
		event.stopPropagation();
		const et = event.target,
		      parent = et.parentElement.parentElement,
		      ul = parent.querySelector('ul');        
		parent.removeChild(ul);
		et.classList.remove(collapse_class);
		et.classList.add(expand_class);
		et.textContent = expand_icon;
		et.removeEventListener('click', collapse);
		et.addEventListener('click', expand);
	}
	
	function hasTextFilter(item){
		var defined_text_filter = textFilter && textFilter.length > 0;
		return defined_text_filter && item.name && item.name.toLowerCase().indexOf(textFilter.toLowerCase()) > -1;
	}
	
	function hasChildWithTextFilter(item){
		return getAllChildren(item.id).some(function(child) {
    		return hasTextFilter(child);
  		});
	}
	
	/**
	* Update item and throws the events
	*/
	function updateItemState(item){
		var item_index = jQuery.inArray(item, MDCTree.data);
  		MDCTree.data[item_index].selected = item.selected;
  				 	
  		// Throw the event 	
  		if(item.onSelect && selected) 
  			item.onSelect();  							
  		else if	(item.onUnselect && !selected) 
  			item.onUnselect();	
	}
	
	/**
	* Update all children and throws the events
	*/
	function updateChildrenState(item){
	
		if(!item || !item.id)
			return;	
		
  		$('#item-' + item.id).find('ul input').prop('checked', item.selected);
  		getAllChildren(item.id).forEach(function(child){  		
  							
			// Update the itens								
  			var child_index = jQuery.inArray(child, MDCTree.data);
			MDCTree.data[child_index].selected = item.selected;
			// Throw the events
			if(child.onSelect && selected) 
  				child.onSelect();  							
  			else if(child.onUnselect && !selected) 
  				child.onUnselect();
  													
		});
	}
	
	/**
	* Update all children and throws the events
	*/
	function updateParentsState(item){

		if(!item || !item.parent)
			return;	
	
		getAllParents(item.parent).forEach(function(parent){
		  				
  			var parentIsSelected = item.selected && !hasUnselectedChildren(parent.id);			
  			// Update the itens								
  			var parent_index = jQuery.inArray(parent, MDCTree.data);
			MDCTree.data[parent_index].selected = parentIsSelected;		
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
	function getCheckbox(item){		
		var checkbox = document.createElement('div');
    	checkbox.classList.add('mdc-checkbox');
    	checkbox.classList.add(action_class);
    	
    	var input = document.createElement('input');
    	input.classList.add('mdc-checkbox__native-control');
    	input.setAttribute('id', 'chk_' + item.id);
    	input.setAttribute('type', 'checkbox');
    	checkbox.appendChild(input);
    	
		var checkbox__background = document.createElement('div');
    	checkbox__background.classList.add('mdc-checkbox__background');
    	checkbox.appendChild(checkbox__background);
    	
		var checkbox__checkmark = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    	checkbox__checkmark.classList.add('mdc-checkbox__checkmark');
    	checkbox__checkmark.setAttribute('viewBox', '0 0 24 24');
    	checkbox__background.appendChild(checkbox__checkmark);
    	
    	var checkbox__checkmark_path = document.createElementNS('http://www.w3.org/2000/svg', 'path');
    	checkbox__checkmark_path.classList.add('mdc-checkbox__checkmark-path');
    	checkbox__checkmark_path.setAttribute('fill', 'none');
    	checkbox__checkmark_path.setAttribute('stroke', 'white');
    	checkbox__checkmark_path.setAttribute('d', 'M1.73,12.91 8.1,19.28 22.79,4.59');
    	checkbox__checkmark.appendChild(checkbox__checkmark_path);
    	
    	var checkbox__mixedmark = document.createElement('div');
    	checkbox__mixedmark.classList.add('mdc-checkbox__mixedmark');
    	checkbox__background.appendChild(checkbox__mixedmark);
    	
    	return checkbox;
	}
	
	/**
	* Create a radio button for choice type
	*/
	function getRadio(item){		
		var radio = document.createElement('div');
    	radio.classList.add('mdc-radio');
    	radio.classList.add(action_class);
    	
    	var input = document.createElement('input');
    	input.classList.add('mdc-radio__native-control');
    	input.setAttribute('id', 'radio_' + item.id);
    	input.setAttribute('type', 'radio');
    	input.setAttribute('name', root.getAttribute('id'));
    	radio.appendChild(input);
    	
		var radio__background = document.createElement('div');
    	radio__background.classList.add('mdc-radio__background');
    	radio.appendChild(radio__background);
    	
		var radio__outer_circle = document.createElement('div');
    	radio__outer_circle.classList.add('mdc-radio__outer-circle');
    	radio__background.appendChild(radio__outer_circle);
    	
    	var radio__inner_circle = document.createElement('div');
    	radio__inner_circle.classList.add('mdc-radio__inner-circle');
    	radio__background.appendChild(radio__inner_circle);
    	
    	return radio;
	}
}


	
