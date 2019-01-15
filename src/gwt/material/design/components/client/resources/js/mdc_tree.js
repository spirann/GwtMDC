
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
function MDCTree(element) {

    var root = element;
    var data = null;
    var expand_class = 'mdc-tree--collapse';
    var expand_icon = 'chevron_right';
    var collapse_class = 'mdc-tree--expand';
    var collapse_icon = 'expand_more';
    var name_class ='mdc-tree--name';
    var action_class ='mdc-tree--action';
    var last_selected_item;
       
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
  		
  		// Open first level
  		// Do not open all levels, because it`s slow 
  		$(root).find('.' + expand_class).click();
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
  		
  		if(item.action) {
  			
  			var action;
  			
  			// Create action element
  			if(item.action === 'filter')
  				action = getCheckbox(item);
  			else if(item.action === 'choice') 
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
  					var selected = this.checked;
  					
  					// Update the item
  					var item_index = jQuery.inArray(item, MDCTree.data);
  					MDCTree.data[item_index].selected = this.checked;
  					 	
  					// Throw the event 	
  					if(item.onSelect && selected) 
  						item.onSelect();  							
  					else if	(item.onUnselect && !selected) 
  						item.onUnselect();	
  							
					// Add events if action is a checkbox  							
  					if(item.action === 'filter') {  						
  							  						
  						// //////////////////////////////////////////////
  						// Update all itens and throws the events	
  						// //////////////////////////////////////////////
  						// Update all drawed children	
  						$(li).find('ul input').prop('checked', item.selected);
  						getAllChildren(item.id).forEach(function(child){  							
  							// Update the itens								
  							var child_index = jQuery.inArray(child, MDCTree.data);
							MDCTree.data[child_index].selected = selected;
							// Throw the events
							if(child.onSelect && selected) 
  								child.onSelect();  							
  							else if(child.onUnselect && !selected) 
  								child.onUnselect();									
						});
						
						// //////////////////////////////////////////////
						// Update all parents	
						// //////////////////////////////////////////////
  						getAllParents(item.parent).forEach(function(parent){  							
  							// Update the itens								
  							var parent_index = jQuery.inArray(parent, MDCTree.data);
							MDCTree.data[parent_index].selected = selected;		
							// Throws the events
							if(parent.onSelect && selected) 
  								parent.onSelect();  							
  							else if(parent.onUnselect && !selected) 
  								parent.onUnselect();					
							// Update drawed parent
							// To select the parent, the parent can not has unslected children
							$('#item-' + parent.id + ' > div input').prop('checked', item.selected && !hasUnselectedChildren(parent.id));
																					
						});
  						
  							
  					} 
  					// Add events if action is a radio button
  					else if(item.action === 'choice') {
  						// Notify last selected item that who is unselected
  						
  						if(MDCTree.last_selected_item && MDCTree.last_selected_item.onUnselect)
  							MDCTree.last_selected_item.onUnselect();  								
  						
  						MDCTree.last_selected_item = item;
  					}  						  							
  				});  				
  					  				
				// Set type of action, it's necessary because of css file				  					  					
  				action.setAttribute('type', item.action);
  				// Select the item if it's selected
  				$(li_header).find('input').prop('checked', item.selected);	 
  			
  			}
  		}
  		
  		li_header.appendChild(span);  		
  		li.appendChild(li_header);
  		return li;
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


	
