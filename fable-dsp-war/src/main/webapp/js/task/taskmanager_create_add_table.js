function createAddTable(id) {
//	var selected = $('#task-table').datagrid('getSelected');
//	if (selected) {
//		$.messager.confirm('警告','确认要创建增量表么?',function(flag) {
//	    	if (flag) {
//	        	id = selected.id;
	        	$.ajax({
	        		type : "POST",
	            	url : $("#pageContext").val()+"/dataswitch/createAddTable",
	            	data : "id=" + id,
	            	success : function callback(data) {
	                	if (!data.dealFlag) {
	                		$.messager.show({
		                    	title : '警告',
		                    	msg : '创建失败。'
	                		});
	                 	} else {
	                    	$.messager.show({
	                        	title : '提示',
	                        	msg : '创建成功'
	                        });
	                    }
	                }
	            });
//	        }
//	    });
//	} else {
//		$.messager.show({
//			title : '警告',
//			msg : '请先选择任务。'
//		});
//	}
}
	   