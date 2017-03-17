<%@ page language="java" pageEncoding="utf-8"%>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/commonstyle.easyui.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/task/taskmanager_config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui.extend.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/task/taskmanager_init.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/task/taskmanager_action.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/task/taskmanager_action.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/task/taskmanager_edit.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/task/taskmanager_remove.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/task/taskmanager_create_add_table.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>任务信息</title>
</head>

<body class="easyui-layout" fit="true">
	<script type="text/javascript"> 
		var pageContext = $("#pageContext").val();
		//第一次加载需要表映射没有初始化
		var tableMappingInit = false;
		//存放不同表的字段用来给没有映射到的字段勾选
		var columnBeffer={};
		var columnBefferString="";
		
		var columnMappings = {};
		var columnFilters = {};
		var columnVirusFilters = {};
		var columnTransfers = {};
		var current = {};
		// insert or update task
		var updateFlag = false;
		var updatetimeFlag = false;
		//初始化表映射关系
		function initTableMapping() {
			var sourceRows = $('#source-datasource-grid').datagrid('getRows');
			var targetRows = $('#target-datasource-grid').datagrid('getRows');
			var source = sourceRows[0].name;
			
			//如果表名是object数组将其转化为string
			if("object"==typeof(sourceRows[0].tableName)){
	    		var tableNames = "";
	    		for(var i=0;i<(sourceRows[0].tableName).length;i++){
	    			tableNames += sourceRows[0].tableName[i]+",";
	    		}
	    		tableNames=tableNames.substring(0,tableNames.length-1);
	    		sourceRows[0].tableName = tableNames;
	    	}
			
			for(var i=0;i<targetRows.length;i++){
	    		if("object"==typeof(targetRows[i].tableName)){
	    			var tableNames = "";
		    		for(var j=0;j<(targetRows[i].tableName).length;j++){
		    			tableNames += targetRows[i].tableName[j]+",";
		    		}
		    		tableNames=tableNames.substring(0,tableNames.length-1);
		    		targetRows[i].tableName = tableNames;
	    		}
	    	}
			var sourceTables = sourceRows[0].tableName.split(",");
			var mapping = [];
			var index =0;
			//循环源
			for (var i=0; i< sourceTables.length; i++) {
				//循环端     mapping 的数目应是 源端 表数*端的数目
				for (var j = 0; j < targetRows.length; j++) {
					var targetTables = targetRows[j].tableName.split(",");
					for (var k = 0; k < targetTables.length; k++) {
						if (sourceTables[i] == targetTables[k]) {
							mapping[index] = {sourceId: sourceRows[0].id,targetId: targetRows[j].id,id:source + "->" + targetRows[j].name, fromTable: sourceTables[i], toTable: targetTables[k]};
							index++;
							break;
						} else if (k == targetTables.length - 1) {
							mapping[index] = {sourceId: sourceRows[0].id,targetId: targetRows[j].id,id:source + "->" + targetRows[j].name, fromTable: sourceTables[i], toTable: ""};
							index++;
						}
					}
				}
			}
			$('#table-mapping-grid').datagrid("loadData", mapping);			
		}
		//初始化时间戳类型
		function initTimeStamp() {
			var sourceRows = $('#source-datasource-grid').datagrid('getRows');
			//如果表名是object数组将其转化为string
			if("object"==typeof(sourceRows[0].tableName)){
	    		var tableNames = "";
	    		for(var i=0;i<(sourceRows[0].tableName).length;i++){
	    			tableNames += sourceRows[0].tableName[i]+",";
	    		}
	    		tableNames=tableNames.substring(0,tableNames.length-1);
	    		sourceRows[0].tableName = tableNames;
	    	}
			var sourceTables = sourceRows[0].tableName.split(",");
			var sourceid = sourceRows[0].id;	//数据源编号
			var mapping = [];
			for (var i = 0 ;i<sourceTables.length; i++) {
				mapping[i]= {tableName:sourceTables[i],columnNames:'',timestamp:new Date(),sourceid:sourceid};
			}
			$('#timestamp-grid').datagrid("loadData", mapping);		
		}
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
	        	if("#timestamp-grid"==target){
	        		var ed = $(target).datagrid('getEditor', {
		        		index: editIndex,
		        		field: 'columnNames'
		        	});
		        	if(ed) {
		        		var text = $(ed.target).combobox('getText');
		        		var row = $(target).datagrid('getRows')[editIndex];
		        		row.name = text;
		        	}
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
	                editIndex = index;
	                option.loadMsg = index;
	                $(target).datagrid('selectRow', index).datagrid('beginEdit', index);
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
	    	//var editIndex = option.loadMsg;
	        //if (editIndex == undefined || editIndex == -1) {
	        //	return;
	        //}
	        //$(target).datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
	        var record = $(target).datagrid('getSelected');
	        if (null == record) {
	        	return;
	        }
	        var index = $(target).datagrid('getRowIndex');
	        $(target).datagrid('deleteRow', index);
// 	        editIndex = -1;
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
    </script>

	<input id="pageContext" type="hidden" value="${pageContext.request.contextPath}" />

	<input id="ftp-status" type="hidden" value=""/>
	<!-- end .location -->
	<div class="easyui-layout"  style="width: 100%; height: 500px; overflow: hidden" fit="true">
		<div data-options="region:'center',iconCls:'icon-ok'" style="width: 100%">
		
			<div id="mmschedule" style="width: 150px;">
				<div data-options="iconCls:'icon-runschedule'" onclick="run()">运行</div>
				<div class="menu-sep"></div>
				<div data-options="iconCls:'icon-brench'" onclick="newSchdule()">选项</div>
			</div>
			
			<!--1.begin 任务管理模块 -->
			<table id="task-table" class="easyui-datagrid" border="false" toolbar="#tasktoolbar" fit="true" singleSelect="true" fitColumns="true">
				<thead>
					<tr>
						<th field="id" width="80" align="center" hidden="true">taskId</th>
						<th field="name" width="80" align="center">任务名称号</th>
						<th field="type" width="80" align="center">任务类型</th>
						<th field="drection" width="80" align="center">传输方向</th>
						<th field="priority" align="center" width="80">优先级</th>
					</tr>
				</thead>
			</table>
			
			<div id="tasktoolbar">
				<div>
					<a id="new-task-btn"  class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
					<a id="edit-task-btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTask()">编辑</a>
					<a id="dele-task-btn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeTask()">删除</a>
					<a id="run-task-btn"  class="easyui-menubutton" data-options="menu:'#mmschedule',iconCls:'icon-schedule'" onclick="execute()">调度</a>
<!-- 					<a id="inc-task-btn"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="createAddTable()">创建增量表</a> -->
				</div>
			</div>
		</div>
	</div>
	
	<!-- 2.新建任务窗口 -->
	<div id="new-task" class="easyui-tabs" title="新交换任务" open="true" collapsible='false' minimizable='false' maximizable='false' data-options="tabWidth:100,tabHeight:60" style="width:710px;height:550px;z-index:999">
        <div title="<span class='tt-inner'><img src='${pageContext.request.contextPath}/style/images/task/setting.png'/>基本配置</span>" style="padding:10px">
 	    
	 	    <div class="easyui-layout" data-options="fit:true">
	        	<div data-options="region:'north',border:false">
	        		<div id="totalDiv">
				        <form method="post" id="totalForm">
					        <table width="100%">
						        <tr>
							        <td align="left"><strong>任务名称：</strong></td>
							        <td>
								        <input id="taskId" name="taskId" type="hidden" />
								        <input id="task-name"  name="taskName" data-options="required:true,validType:'taskName'" class="easyui-validatebox  white"/>
								        <font color="red">*</font>
							        </td>
							        <td align="left"><strong>任务类型：</strong></td>
							        <td>
							        	<input id="task-type" name="taskType" data-options="required:true" style="width:157px"/>
							        	<font color="red">*</font>
							        </td>
						        </tr>
						        <tr id="il">
							        <td align="left"><strong>同步类型：</strong></td>
							        <td align="left">
							        	<input id="task-trans-type" name="synchroType" data-options="required:true" style="width:157px"/>
							        	<font color="red">*</font>
							        </td>
							        <td align="left"><strong>任务描述：</strong></td>
							        <td align="left">
							        	<textarea id="task-description" class="easyui-validatebox white" name="taskDescrption"></textarea>
							        </td>
						        </tr>
						        <tr id="db" height="25px" >
						        	<td align="left">
							        	<div id="delete-source-data-div">
									        <input id="delete-source-data" name="deleteSourceData" type="checkbox" />
									        <span>删除源数据</span>
								        </div>
								    </td>
						        	<td align="center" >
						        		<div id="row-Level-Log-div">
									        <input id="row-Level-Log" name="rowLevelLog" type="checkbox">
									        <span>关联行级日志</span>
								        </div>
						        	</td>
								    <td align="left" >
								        <div id="rebuild-trigger-div">
									        <input id="rebuild-trigger" name="rebuildTrigger" type="checkbox">
									        <span>重建触发器</span>
								        </div>
							        </td>
						        	<td align="center" >
						        		<div id="print-syslog-div">
									        <input id="sysLog-Print" name="sysLogPrint" type="checkbox">
									        <span>输出系统日志</span>
								        </div>
						        	</td>
						        </tr>
						        <!-- 文件    -->
						        <tr id="file" hidden="true">
									<td align="left"><strong>源文件：</strong></td>
							        <td>
								        <input id="source-File" name="sourceFile" data-options="required:true" style="width:157px"/>
								        <font color="red">*</font>
							        </td>
							        <td align="left"><strong>目标文件：</strong></td>
							        <td>
							        	<input id="target-File" name="targetFile" data-options="required:true" style="width:157px"/>
							        	<font color="red">*</font>
							        </td>
						        </tr>
						        <tr id="file-description">
						        	<td align="left" rowspan="2"><strong>任务描述：</strong></td>
							        <td align="left" rowspan="2">
							        	<textarea id="file-task-description" name="taskDescrption-file" style="width:157px" class="easyui-validatebox white" ></textarea>
							        </td>
							        <td>
						        		<div>
									        <input id="file-row-Level-Log" name="rowLevelLog" type="checkbox">
									        <span>关联行级日志</span>
								        </div>
						        	</td>
						        </tr>
						        <tr id="file-syslog">
						        	<td>
						        		<div>
									        <input id="file-sysLog-Print" name="sysLogPrint" type="checkbox">
									        <span>输出系统日志</span>
								        </div>
								    </td>
						        </tr>
					        </table>
				        </form>
	        		</div>
	        	</div>
	        
	        <!-- 4.begin -->
	        <div data-options="region:'center',border:false">
					<!--db -->
					<div id="source-datasource-div" class="datasource-css">
						<table id="source-datasource-grid"></table>
						<input id="source-id" type="hidden" />
					</div>
					<div id="target-datasource-div" class="datasource-css">
						<table id="target-datasource-grid"></table>
						<input id="target-id" type="hidden" />
					</div>
			<!-- 时间戳字段 -->
					<div id="timestamp-div" hidden="true" class="timestamp-css">
						<table id="timestamp-grid"></table>
					</div>
			</div>
			<!-- 直接完成 -->
			<div data-options="region:'south',border:false" style="text-align: right; padding: 20px 20px; height: 100px;">
			        <a id="btn-save">完成</a>
			        <a id="btn-cancel">取消</a>
		    </div>
				<!-- end 
		        <div data-options="region:'south',border:false" style="text-align: right; padding: 20px 20px; height: 100px;">
			        <a id="btn-save">下一步</a>
			        <a id="btn-cancel">取消</a>
		        </div>
		        -->
	       </div>
        </div>
        <div title="<span class='tt-inner'><img src='${pageContext.request.contextPath}/style/images/task/mapping.png'/>映射</span>" style="padding:10px">
        	<p class="notice-depends-on-notice"></p>
            <div id="mapping-file-div">
            	<input id="fileupload" type="file" name="files[]" data-url="server/php/" multiple>
            </div>
            <div id="mapping-database-div">
            	<input id="current-source-ds-id" hidden="true" />
            	<input id="current-target-ds-id" hidden="true" />
            	<input id="current-source-tb-name" hidden="true" />
            	<input id="current-target-tb-name" hidden="true" />
            	<input id="current-mapping-name" hidden="true" />
				<table id="table-mapping-grid" style="width:570px;height:200px;margin-top:20px"></table>
				<table id="column-mapping-grid" style="width:570px;height:200px;margin-top:20px"></table>
            </div>
        </div>
        <div id="filter-img" title="<span class='tt-inner'><img src='${pageContext.request.contextPath}/style/images/task/filter.png'/>过滤</span>" style="padding:10px">
        	<p class="notice-depends-on-notice"></p>
            <div id="filter-div">
            	<div id="filter-file-div" style="display:block;border:0px;">
	            	<div id="filter-filename" class="easyui-panel" title="文件名称过滤" style="height:70px;padding:5px;margin-bottom:10px">
	            		<input id="filter-filename-include" type="checkbox" /><span>包含</span><input id="filter-filename-inbox"/>
	            		<input id="filter-filename-exclude" type="checkbox" /><span>排除</span><input id="filter-filename-exbox"/>
	            	</div>
	            	<div id="filter-file-virus-div" class="easyui-panel" title="文件病毒过滤" style="height:70px;padding:5px;">
	            		<span>过滤文件:</span><input id="filter-file-virus" />
	            	</div>
	            	<div id="filter-file-content" style="margin-top:20px">
	            		<table id="filter-file-content-grid"></table>
	            		<div style="display:none">
		            		<div>
		            			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		            		</div>
		            		<span>过滤类型:</span><input id="filter-file-content-type">
		            		<span>文件名称:</span><input id="filter-filecontent-box"/>
		            		<span>敏感词句:</span><input id="filter-content-keyword" />
	            		</div>
	            	</div>
	            </div>
	            <div id="filter-column-div" style="display:block;border:0px;">
	            	<div id="filter-column-virus-div" style="margin-top:10px;margin-bottom:10px;">
	            		<table id="filter-column-virus-grid"></table>
	            		<div style="display:none">
		            		<span>表名:</span><input id="filter-file-virus-table" />
		            		<span>列名:</span><input id="filter-file-virus-column"/>
	            		</div>
	            	</div>
					<div id="filter-column-div">
						<table id="filter-column-grid"></table>
						<div style="display:none">
							<div>
		            			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		            		</div>
							<span>表名：</span><input id="filter-column-table" />
							<span>列名：</span><input id="filter-column" />
							<span>操作符:</span><input id="filter-column-operator" />
							<span>比较值:</span><input id="filter-column-value" />
						</div>
					</div>
				</div>
            </div>
        </div>
        <div id="node-transform" title="<span class='tt-inner'><img src='${pageContext.request.contextPath}/style/images/task/change.png'/>转换</span>" style="padding:10px">
        	<p class="notice-depends-on-notice"></p>
            <div id="transform-div">
            	<div id="transform-file-div">
            		<table id="transform-file-grid"></table>
            	</div>
            	<div id="transform-column-div">
            		<table id="transform-column-grid"></table>
            	</div>
            	<div data-options="region:'south',border:false" style="text-align: right; padding: 20px 20px; height: 100px;">
			        <a id="btn-save1">完成</a>
			        <a id="btn-cancel1">取消</a>
		        </div>
            </div>
        </div>
    </div>
	<!-- 2.end -->
	
	<!-- 3.任务详细信息 -->
	<div id="task-detail">
		<table id="task-detail-grid"></table>
	</div>
	<!-- 4.调度时间钟面板 -->
		<div id="schedule-settings" class="easyui-window" title="任务调度选项"
		closed="true"
		data-options="resizable:false,draggable:false,maximizable:false,minimizable:false,collapsible:false,modal:true"
		style="width: 420px; height: 340px; padding: 5px; overflow: hidden;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false"
				style="width: 420px; overflow: hidden;">
				<div>
					<table
						style="border-spacing: 15px; border-collapse: separate; margin: 4px; width: 350px;">
						<tr>
							<td>调度模式:</td>
							<td>
							<select
								style="background: #ffffff; width: 150px; border: 1px solid #7EBDD9; min-height: 20px;"
								id="schedul-model" onchange="selectmodel()">
								<option value="0">周期模式</option>
								<option value="1">定期模式</option>
								</select>
								<font color="red">*</font></td>
						</tr>
					</table>
					<!-- 添加一系列的隐藏域 -->
					<form method="post" id="fm1">
						<table id="circle-execute"
							style="border: 1px solid #CCCCCC; border-spacing: 18px; border-collapse: separate; margin: 10px auto; width: 350px;">
							<tr>
								<td>开始时间：</td>
								<td><input type="hidden" name="taskId" id="taskId" />
								 <input type="hidden" id="runModel" name="runModel" />
								 <input name="scheduleId" type="hidden" />
								 <input class="easyui-datetimebox" name="beginDate" id="beginDate" data-options="required:true,showSeconds:true,editable:false" value="3/4/2010 " style="width: 180px"></td>
								<td></td>
							</tr>
							<tr>
								<td colspan="3">间隔周期：</td>
							</tr>
							<tr>
								<td colspan="3">
								<input type="radio" name="circleModel"value="byHour" checked="checked" />
								<input id="byHour" name="byHour" class="easyui-numberspinner" style="width: 40px;" value="1" data-options="min:1,max:24,editable:false" >
								&nbsp;&nbsp;<label class="m_t_5">小时</label> &nbsp;&nbsp;
								<input type="radio" name="circleModel" value="byMinute" /> 
								<input id="byMinute" name="byMinute" class="easyui-numberspinner" style="width: 40px;"  value="1"
									data-options="min:1,max:60,editable:false" >
								&nbsp;&nbsp;<label class="m_t_5">分钟</label> &nbsp;&nbsp; 
								<input type="radio" name="circleModel" value="bySecond" />
								<input id="bySecond" name="bySecond" class="easyui-numberspinner" style="width: 40px;"
									 value="1" data-options="min:1,max:60,editable:false">
								&nbsp;&nbsp;<label class="m_t_5">秒</label>
								</td>
								<input id="executeimmediately_hidden" type="hidden" name="executeimmediately"/>
							</tr>
						</table>
					</form>
					<form id="fm2" method="post">
						<!-- 定期模式的表格 -->
						<table id="regular-execute"
							style="border: 1px solid #CCCCCC; border-spacing: 15px; border-collapse: separate; margin: 10px auto; width: 400px;">
							<tr>
								<td>
								<input id="taskId" type="hidden" name="taskId"  />
								<input id="runModel" type="hidden" name="runModel" /> 
								<input name="scheduleId" type="hidden" /> 
								<input type="radio" name="model" checked="checked" value="byday" onclick="sel()" />
								&nbsp;&nbsp;<label class="m_t_5">按天</label></td>
								<td>每天</td>
								<td>
								<input id="hh1" name="hourDay" class="easyui-numberspinner" style="width: 40px;" required="true" value="0"
									data-options="min:0,max:23,editable:false">
									&nbsp;&nbsp;<label class="m_t_5">时</label></td>
								<td>
								<input id="mmm1" name="minuteDay" class="easyui-combobox"
								data-options="required:'true',missingMessage:'请选择间隔分钟',validType:'minute'"
								style="background: #ffffff; width: 40px; border: 1px solid #7EBDD9; min-height: 20px;"
									/>
									&nbsp;<label class="m_t_5">分</label></td>
							</tr>
							<tr>
								<td>
								<input type="radio" name="model" value="byweek" onclick="sel()" />
								&nbsp;&nbsp;<label class="m_t_5">按周</label>
								</td>
								<td>
								<label class="m_t_5">每</label>
								<select id="weekDay" name="dayWeek" style="background: #ffffff; width: 80px; border: 1px solid #7EBDD9; min-height: 20px;">
									<option value="1" selected="selected">星期日</option>
									<option value="2">星期一</option>
									<option value="3">星期二</option>
									<option value="4">星期三</option>
									<option value="5">星期四</option>
									<option value="6">星期五</option>
									<option value="7">星期六</option>
								</select>
								</td>
								<td>
								<input id="hh2" name="hourWeek" class="easyui-numberspinner" style="width: 40px;" required="required" data-options="min:0,max:23,editable:false"
									value="0">
								&nbsp;&nbsp;<label class="m_t_5">时</label></td>
								<td>
								<input id="mm2" name="minuteWeek" class="easyui-combobox"
								data-options="required:'true',missingMessage:'请选择间隔分钟',validType:'minute'"
								style="background: #ffffff; width: 40px; border: 1px solid #7EBDD9; min-height: 20px;"/>
									&nbsp;<label class="m_t_5">分</label>
								</td>
							</tr>
							<tr>
								<td>
								<input type="radio" name="model" value="bymonth" onclick="sel()" />
								&nbsp;&nbsp;<label class="m_t_5">按月</label>
								</td>
								<td>
								<label class="m_t_5">每月</label>
								<input id="monthDay"name="dayMonth" class="easyui-numberspinner"
									style="width: 40px;" required="required" value="1"
									data-options="min:1,max:30,editable:false">
									&nbsp;&nbsp;<label class="m_t_5">日</label>
								</td>
								<td>
								<input id="hh3" value="1" name="hourMonth"
									class="easyui-numberspinner" style="width: 40px;"
									required="required" data-options="min:0,max:23,editable:false">
									&nbsp;&nbsp;<label class="m_t_5">时</label></td>
								<td>
								<input id="mm3" name="minuteMonth" class="easyui-combobox"
								data-options="required:'true',missingMessage:'请选择间隔分钟',validType:'minute'"
								style="background: #ffffff; width: 40px; border: 1px solid #7EBDD9; min-height: 20px;"/>
									&nbsp;<label class="m_t_5">分</label>
								</td>
								<input id="executeimmediately_hidden" type="hidden" name="executeimmediately"/>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div data-options="region:'south',border:false"
				style="text-align: right; padding: 5px 20px; height: 80px">
				<div style="float: right; clear: both; margin: 0px 0px; width: 300px">
					<input type="checkbox" id="executeimmediately" name="executeimmediately"/>
					&nbsp; <label style="margin-right: 5px; vertical-align: middle;">立刻执行一次</label>&nbsp;
				</div>
				<!-- 点击确定，上文本框赋值 -->
				<a id="btn-cancel" onclick="reset()" style="margin: 15px 5px 0px 5px; float: right; clear: both;" icon="icon-cancel">取消</a>
				<a id="btn-save" icon="icon-save" style="margin: 15px 5px 0px 5px; float: right;" onclick="schedule();"  >保存</a>
			</div>
		</div>
	</div>
	<!-- 调度模块设置结束 -->
</body>
</html>
