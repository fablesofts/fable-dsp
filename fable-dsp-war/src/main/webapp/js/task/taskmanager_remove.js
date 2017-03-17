function removeTask(){
	var record = $('#task-table').datagrid('getSelected');
	if(null != record) {
		$.messager.confirm('警告','确认删除么?',function(flag) {
	    	if (flag) {
	        	id = record.id;
	            $.ajax({type : "POST",
		            url : $("#pageContext").val()+"/dataswitch/deleteTask",
		        	data : "id=" + id,
		        	dataType : "json",
		        	success : function callback(data) {
		            	if (!data.dealFlag) {
		                	$.messager.show({
		                	title : '警告',
		                	msg : data.msg
		                	});
		                } else {
		                	$.messager.show({
		                		title : '提示',
		                		msg : '删除成功'
		                	});
		                	$('#task-table').datagrid('reload');
		                }
		        	}
	            });
	    	}
		});
	} else {
		$.messager.show({
			title : '警告',
			msg : '请先选择任务。'
		});
	}
}