
/* data is in flat array format like this:
[
  {
    id: 'abc',
    name: 'ABCDE',
    parent: null
  },
  {
    id: 'def',
    name: 'DEFGH',
    parent: 'abc'
  }
]
*/
function MDCTree(element) {

    var root = element;
    var data = null;
    var expand_class = 'mdc-tree--collapse';
    var expand_icon = 'expand_more';
    var collapse_class = 'mdc-tree--expand';
    var collapse_icon = 'expand_less';
       
    this.setData = function (data) {
    	MDCTree.data = data;
		const orphansArray = orphans();
  		if (orphansArray.length) {
    		const items = orphansArray.map(generateListItem),
        	ul = document.createElement('ul');
    		items.forEach(function(li) {
      			ul.appendChild(li);
    		});
    		root.appendChild(ul);
  		}
	}
	
	function orphans() {
  		return MDCTree.data.filter(function(item) {
    		return item.parent === null || item.parent === undefined;
  		});
	}
	
	function hasChildren(parentId) {
  		return MDCTree.data.some(function(item) {
    		return item.parent === parentId;
  		});
	}

	function getChildren(parentId) {
  		return MDCTree.data.filter(function(item) {
    		return item.parent === parentId;
  		});
	}

	function generateListItem(item) {  
  		const li = document.createElement('li');
  		const li_header = document.createElement('div');
  		li.id = 'item-' + item.id;
  		if (hasChildren(item.id)) {
    		const a = document.createElement('i');
    		a.href = '#';
    		a.textContent = expand_icon;
    		a.classList.add(expand_class);
    		a.addEventListener('click', expand);
    		li_header.appendChild(a);
  		}
  		const span = document.createElement('span');
  		span.textContent = item.name;
  		li_header.appendChild(span);
  		li.appendChild(li_header);
  		return li;
	}

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
}


	
