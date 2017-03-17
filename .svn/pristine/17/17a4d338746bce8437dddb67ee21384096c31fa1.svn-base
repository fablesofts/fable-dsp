function editTask(){
	updateFlag = true; 
	updatetimeFlag = true;
	var record = $('#task-table').datagrid('getSelected');
	if(null != record) {
		$.ajax({
			url: $("#pageContext").val()+'/dataswitch/editTaskForShow',
			type: 'POST',
			data:{taskId:record.id},
			error: function(){
				alert('Error loading');
			},
			success: function(result){
				$('#new-task').tabs("select",0);
				tableMappingInit = false;
				$("#new-task").window("open");
				var obj = eval('(' + result + ')');
				//回写form中的内容
				$("#taskId").val(obj.taskId);
				$("#task-name").val(obj.taskName);
				$("#task-type").combobox('select',obj.taskType);
//				//file-file
				if("1"==obj.taskType){
					$("#file-task-description").val(obj.taskDescription);
					if("true"==obj.rowLevelLog){
						$("#file-row-Level-Log").attr("checked",'true');
					}
					if("true"==obj.sysLogPrint){
						$("#file-sysLog-Print").attr("checked",'true');
					}
					$("#source-File").combobox('select',obj.sourceFile);
					$("#target-File").combobox('select',obj.targetFile);
					
				} else {//db-db
					$("#task-description").val(obj.taskDescription);
					if("true"==obj.deleteSourceData){
						$("#delete-source-data").attr("checked",'true');
					}
					if("true"==obj.rebuildTrigger){
						$("#rebuild-trigger").attr("checked",'true');
					}
					if("true"==obj.rowLevelLog){
						$("#row-Level-Log").attr("checked",'true');
					}
					if("true"==obj.sysLogPrint){
						$("#sysLog-Print").attr("checked",'true');
					}
					$('#task-trans-type').combotree('setValue',obj.synchroType);
					//timestamp
					if("4"==obj.synchroType){
						var timestamp =  eval('(' + obj.timestamps + ')');
						for(var i=0;i<timestamp.length;i++){
//						timestamp[i].timestamp="";
							timestamp[i].name=timestamp[i].columnNames;
						}
						$("#timestamp-grid").datagrid('loadData', timestamp);
						var timestamparr =  eval('(' + obj.timestamps + ')');
					}
				}
				
				//回写grid
				if (''!=obj.sourceDSInfo.location) {
					$('#source-datasource-grid').datagrid('hideColumn', 'tableName');
					$('#source-datasource-grid').datagrid('showColumn', 'location');
					$('#target-datasource-grid').datagrid('hideColumn', 'tableName');
					$('#target-datasource-grid').datagrid('showColumn', 'location');
				} 
//				
//				//手动给源端和目标的中隐藏的表达附上missing值 防止修改时无法保存
				var sourceInfo = obj.sourceDSInfo;
				var targetInfoarr = eval('(' + obj.targetDSInfo + ')');
				var newSourceInfo = new Array();
				var newTargetInfo = new Array(); 
				if(0==obj.taskType){
					newSourceInfo[0] = {
							name:sourceInfo.sourceName,
							id:sourceInfo.sourceId,
							entityid:sourceInfo.id,
							tableName:sourceInfo.tableName,
							location:"请选择文件路径"
					};
					for(var i = 0;i<targetInfoarr.length;i++){
						newTargetInfo[i] = {
							name:targetInfoarr[i].targetName,
							id:targetInfoarr[i].targetId,
							entityid:targetInfoarr[i].id,
							tableName:targetInfoarr[i].tableName,
							location:"请选择文件路径"
						};
					}
				} else if(1==obj.taskType){
					newSourceInfo[0] = {
							name:sourceInfo.sourceName,
							id:sourceInfo.sourceId,
							entityid:sourceInfo.id,
							tableName:"请选择表名",
							location:sourceInfo.location
					};
					for(var i = 0;i<targetInfoarr.length;i++){
						newTargetInfo[i] = {
							name:targetInfoarr[i].targetName,
							id:targetInfoarr[i].targetId,
							entityid:targetInfoarr[i].id,
							tableName:"请选择表名",
							location:targetInfoarr[i].location
						};
					}
				}
				
				var sourcestr = '{"total":1,"rows":';
                sourcestr += JSON.stringify(newSourceInfo);
                sourcestr += '}';
                var sourcedata = $.parseJSON(sourcestr);
                //判断对象是否有指定的属性
                //加载源
                $("#source-datasource-grid").datagrid('loadData', sourcedata);
                
                //回写端grid
                var targetstr = '{"total":1,"rows":';
                targetstr += JSON.stringify(newTargetInfo);
                targetstr += '}';
                var targetdata = $.parseJSON(targetstr);
                //判断是数据库类型还是文件类型,加载端
                $('#target-datasource-grid').datagrid('loadData', targetdata); 
//                
                var tableMapObj = eval('(' + obj.tableMappingInfo + ')');
                var columnMapObj = eval('(' + obj.columnMappingInfo + ')');
                var columnFilterObj = eval('(' + obj.columnFilterInfo + ')');
                var columnVirusFilterObj = eval('(' + obj.columnVirusFilterInfo + ')');
                var columnTransferObj = eval('(' + obj.columnTransferInfo + ')');
                
                columnMappings = {};
        		columnFilters = {};
        		columnVirusFilters = {};
        		columnTransfers = {};
                for(var x=0;x<tableMapObj.length;x++){
                	key = tableMapObj[x].targetId;
                	value = tableMapObj[x].fromTable+"->"+tableMapObj[x].toTable;
                	//字段映射
                	for(var i=0;i<columnMapObj.length;i++){
                		if (null == columnMappings[key] || undefined == columnMappings[key]) {
                			columnMappings[key] = {};
                		}
                		if (undefined == columnMappings[key][value] || null == columnMappings[key][value]) {
                			columnMappings[key][value] = {};
                			columnMappings[key][value].rows = new Array();
                		}
                		columnMappings[key][value].rows.push(targetInfoarr[i]);
                	}
                	//字段过滤
                	for(var i=0;i<columnFilterObj.length;i++){
                		if (null == columnFilters[key] || undefined == columnFilters[key]) {
                			columnFilters[key] = {};
                		}
                		if (undefined == columnFilters[key][value] || null == columnFilters[key][value]) {
                			columnFilters[key][value] = {};
                			columnFilters[key][value].rows = new Array();
                		}
                		columnFilters[key][value].rows.push(columnFilterObj[i]);
                	}
                	//字段病毒过滤
                	for(var i=0;i<columnVirusFilterObj.length;i++){
                		if (null == columnVirusFilters[key] || undefined == columnVirusFilters[key]) {
                			columnVirusFilters[key] = {};
                		}
                		if (undefined == columnVirusFilters[key][value] || null == columnVirusFilters[key][value]) {
                			columnVirusFilters[key][value] = {};
                			columnVirusFilters[key][value].rows = new Array();
                		}
                		columnVirusFilters[key][value].rows.push(columnVirusFilterObj[i]);
                	}
                	for(var i=0;i<columnTransferObj.length;i++){
                		if (null == columnTransfers[key] || undefined == columnTransfers[key]) {
                			columnTransfers[key] = {};
                		}
                		if (undefined == columnTransfers[key][value] || null == columnTransfers[key][value]) {
                			columnTransfers[key][value] = {};
                			columnTransfers[key][value].rows = new Array();
                		}
                		columnTransfers[key][value].rows.push(columnTransferObj[i]);
                	}
                }
			}
		});
	}
}