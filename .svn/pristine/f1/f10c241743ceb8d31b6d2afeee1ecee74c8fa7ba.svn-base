<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>系统日志信息</title>
<script type="text/javascript">
	//构造表格
	$(function() {
		grid = $('#systemLog-table')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/systemLog/listSystemLogByExample',
							queryParams: {
								isAll:true
							},
							//title : '系统日志信息列表',
							singleSelect : true,
							fit : true,
							border : 0,
							fitColumns : true,
							// autoRowHeight : true,
							loadMsg : '系统日志正在加载,请稍后 !',
							striped : true,
							pagination : true,
							rownumbers : true,
							nowrap : true,
							resizeHandle : 'both',
							pagePosition : 'bottom',
							scrollbarSize : 10,
							frozenColumns : [ [ {
								field : 'taskId',
								hidden : true
							},{
								field : 'taskSerial',
								hidden : true
							} ] ],
							columns : [ [ {
								field : 'taskName',
								title : '任务名称',
								width : 40,
								align : 'center',
							}, {
								field : 'startTime',
								title : '开始时间',
								width : 60,
								align : 'center'
							}, {
								field : 'endTime',
								title : '结束时间',
								width : 60,
								align : 'center'
							}, {
								field : 'selectCount',
								title : '抽取条数',
								width : 30,
								align : 'center'
							}, {
								field : 'selectData',
								title : '抽取流量(byte)',
								width : 40,
								align : 'center',
							}, {
								field : 'loadCount',
								title : '加载条数',
								width : 30,
								align : 'center'
							}, {
								field : 'loadData',
								title : '加载流量(byte)',
								width : 40,
								align : 'center',
							}, {
								field : 'operationResults',
								title : '操作结果',
								width : 40,
								align : 'center',
								formatter : function(value, rec) {
									if (value == "0") {
										return "成功";
									} else if (value == "1") {
										return "失败";
									} else {
										return "";
									}
								}
							} ] ],
							view : detailview,
							detailFormatter : function(index, row) {
								return '<div style="padding:2px;"><table class="ddv"></table></div>';
							},
							onExpandRow : function(index, row) {
								var ddv = $(this).datagrid('getRowDetail',
										index).find('table.ddv');
								subgrid = ddv
										.datagrid({
											url : '${pageContext.request.contextPath}/systemLog/findSystemLogDetailInfo?taskId='
													+ row.taskId+"&taskSerial="+row.taskSerial,
											fitColumns : true,
											singleSelect : true,
											rownumbers : true,
											loadMsg : '',
											height : 'auto',
											frozenColumns : [ [ {
												field : 'taskId',
												hidden : true
											},{
												field : 'taskSerial',
												hidden : true
											} ] ],
											columns : [ [
													{
														field : 'operationType',
														title : '操作类型',
														width : 40,
														align : 'center',
														formatter : function(
																value, rec) {
															if (value == "0") {
																return "抽取";
															} else if (value == "1") {
																return "过滤";
															} else if (value == "2") {
																return "转换";
															} else {
																return "加载";
															}
														}
													},
													{
														field : 'operationResults',
														title : '操作结果',
														width : 40,
														align : 'center',
														formatter : function(
																value, rec) {
															if (value == "0") {
																return "成功";
															} else {
																return "失败";
															}
														}
													},
													{
														field : 'operationDetail',
														title : '操作详细',
														width : 300,
														align : 'center',
													} ] ],
											onResize : function() {
												$('#systemLog-table').datagrid(
														'fixDetailRowHeight',
														index);
											},
											onLoadSuccess : function() {
												setTimeout(
														function() {
															$(
																	'#systemLog-table')
																	.datagrid(
																			'fixDetailRowHeight',
																			index);
														}, 0);
											},
											onDblClickRow : findRowLevelLogs,
										});
								$('#systemLog-table').datagrid(
										'fixDetailRowHeight', index);
							}
						});

		//设置系统日志数据表格的分页控件  
		var p = $('#systemLog-table').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});

		//定义显示行级日志的对话框
		win = $('#rowLevelLogs-window').dialog({
			title : '系统日志详细',
			width : 600,
			height : 400,
			modal : true,
			closed : true,
			draggable : false
		});

	});

	var grid;
	var subgrid;
	var win;

	//双击日志详细时，查看对应的行级日志信息
	function findRowLevelLogs() {
		var row = subgrid.datagrid('getSelected');
		if (row != null) {
			win.window('open');
			win.find('form').form('clear');
			//加载选中的系统日志详细信息
			//显示操作类型
			if (row.operationType == '0')
				$('#operationType').val('抽取');
			else if (row.operationType == '1')
				$('#operationType').val('过滤');
			else if (row.operationType == '2')
				$('#operationType').val('转换');
			else if (row.operationType == '3')
				$('#operationType').val('加载');
			else
				$('#operationType').val('');
			//显示操作结果
			if (row.operationResults == '0')
				$('#operationResults').val('成功');
			else
				$('#operationResults').val('失败');
			//显示操作详细
			$('#operationDetail').val(row.operationDetail);

			//在数据表格中显示对应的行级日志
			$('#rowLevelLogs-table')
					.datagrid(
							{
								url : '${pageContext.request.contextPath}/systemLog/listRowLevelLog?taskId='
										+ row.taskId+"&taskSerial="+row.taskSerial,
								fitColumns : true,
								singleSelect : true,
								rownumbers : true,
								pagination : true,
								pagePosition : 'top',
								loadMsg : '',
								height : 'auto',
								frozenColumns : [ [ {
									field : 'id',
									hidden : true
								}, ] ],
								columns : [ [ {
									field : 'operationData',
									title : '操作数据',
									width : 100,
									align : 'center',
								} ] ]
							});
			//设置行级日志信息数据表格的分页控件  
			var p = $('#rowLevelLogs-table').datagrid('getPager');
			$(p).pagination({
				pageSize : 10,//每页显示的记录条数，默认为10  
				pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
				beforePageText : '第',//页数文本框前显示的汉字  
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});
		}
	}
</script>
</head>
<body>
	<style type="text/css">
	
		#systemLogSearch tr td{
			border: 1px solid black;
			padding: 5px;
			margin:0px auto;
			text-align: left;
		}
		#startTime{
			width:150px;
		}
		#endTime{
			width:150px;
		}
	</style>
	<!-- <div>
			<form id="sysLogSearchForm" >
			   任务名称
			 <input id='taskName' />
			    时间范围
			 <input class="easyui-datetimebox" id='startTime' name='startTime' />
			  -
			 <input class="easyui-datetimebox" id='endTime' name='endTime'/>
			   <br/>
			   操作结果
			 <select id="operationResults" class="easyui-combobox" name="operationResults" style="width:100px;">
					    <option value="0">成功</option>
					    <option value="1">失败</option>
			</select>		
			<input type="button" value='查询' onclick='SysLogSearch.searchByExample()'/>
			<input type="button" value='重置' onclick='javascript:SysLogSearch.searchAll()'/>
		</form>
		<script type="text/javascript">
			    	function SysLogSearch(){};
			    	SysLogSearch.searchAll=function(){
			    		grid.datagrid('reload',{
			    			isAll:true
			    		});
			    		$("input[type!='button']").val('');
			    	};
			    	SysLogSearch.searchByExample=function(){
			    		grid.datagrid('reload',{
			    			isAll:false,
		    				taskName:$('#taskName').val(),
			    				startTime:$('#startTime').datetimebox('getValue'),
			    				endTime:$('#endTime').datetimebox('getValue'),
			    				selectCount_start:$('#selectCount_start').val(),
			    				selectCount_end:$('#selectCount_end').val(),
			    				selectData_start:$('#selectData_start').val(),
			    				selectData_end:$('#selectData_end').val(),
			    				loadCount_start:$('#loadCount_start').val(),
			    				loadCount_end:$('#loadCount_end').val(),
			    				loadData_start:$('#loadData_start').val(),
			    				loadData_end:$('#loadData_end').val(),
			    				operationResults:$('#operationResults').combobox('getValue')
			    		});
			    	};
		</script>
	</div>
 -->
		
	<table id="systemLog-table"></table>

	<!-- 显示系统日志详细信息的模块 -->
	<div id="rowLevelLogs-window"
		data-options="iconCls:'icon-save',resizable:false,maximizable:false,minimizable:false,collapsible:false">
		<div style="padding: 10px 10px 10px 10px;"
			onkeydown="if(event.keyCode==8)return false;">
			<!-- 在窗口中显示系统日志详细信息 -->
			<form>
				<table>
					<tr>
						<td style="text-align: right" width="80px">操作类型：</td>
						<td><input id="operationType" name="operationType"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td style="text-align: right">操作结果：</td>
						<td><input id="operationResults" name="operationResults"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td valign="top" style="text-align: right">操作详情：</td>
						<td><textarea id="operationDetail" name="operationDetail"
								readonly="readonly"
								style="resize: none; width: 480px; height: 50px; border: 0; background-color: white; font-size: 12px;"></textarea>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="padding: 10px 10px 10px 10px;">
			<!-- 在数据表格中显示选中的系统日志详细对应的行级日志 -->
			<table id="rowLevelLogs-table"></table>
		</div>
	</div>

</body>
</html>