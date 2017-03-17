$(function(){
	function endEditing(target) {
		var option = $(target).datagrid("options");
		var editIndex = option.loadMsg;
	    if (editIndex == -1) {
	    	return true;
	    }
	    if ($(target).datagrid('validateRow', editIndex)) {
	        var ed = $(target).datagrid('getEditor', {
	    		index: editIndex,
	    		field: 'id'
	    	});
	    	if(ed) {
	    		var text = $(ed.target).combobox('getText');
	    		var row = $(target).datagrid('getRows')[editIndex];
	    		row.name = text;
	    	}
	    	$(target).datagrid('endEdit', editIndex);
	        editIndex = -1;
	        option.loadMsg = -1;
	        return true;
	    } else {
	        return true;
	    }
	}
	function onClickRow(index, target) {
		var option = $(target).datagrid("options");
		var editIndex = option.loadMsg;
	    if (editIndex != index) {
	        if (endEditing(target)) {
	            $(target).datagrid('selectRow', index).datagrid('beginEdit', index);
	            editIndex = index;
	            option.loadMsg = index;
	        } else {
	            $(target).datagrid('selectRow', editIndex);
	        }
	    }
	}
	function append(target,row) {
	    if (endEditing(target)) {
	        $(target).datagrid('appendRow',row);
	        var option = $(target).datagrid("options");
	        var editIndex = $(target).datagrid('getRows').length-1;
	        option.loadMsg = editIndex;
	        $(target).datagrid('selectRow', editIndex)
	                .datagrid('beginEdit', editIndex);
	    }
	}
	function remove(target) {
		var option = $(target).datagrid("options");
		var editIndex = option.loadMsg;
	    if (editIndex == undefined || editIndex == -1) {
	    	return;
	    }
	    $(target).datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
	    editIndex = -1;
	    option.loadMsg = -1;
	}
	function accept(target) {
	    if (endEditing(target)) {
	        $(target).datagrid('acceptChanges');
	    }
	}
	function reject(editIndex, target){
	    $(target).datagrid('rejectChanges');
	    editIndex = undefined;
	}
	function getChanges(target) {
	    var rows = $(target).datagrid('getChanges');
	    alert(rows.length+' rows are changed!');
	}
});
