<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/commons/meta.jsp" %>
<title>任务状态信息</title>


</head>
<body>
	<div id="taskstatus-window" style="width:841px;height:276px;">
		<table id="taskstatus-table"></table>
	</div>
	<script type="text/javascript">
			$('#taskstatus-table')
			.datagrid({
				url :'${pageContext.request.contextPath}/dashboard/jobRunInfo/listJobRunInfo',
				border : false,
				width:630,
				height:300,
				fit :true,
				singleSelect :true,
				fitColumns :true,
				autoRowHeight : true,
				loadMsg : '任务状态信息正在加载,请稍后 !',
				striped : true,
				pagination : true,
				rownumbers : true,
				pagePosition : 'bottom',
				scrollbarSize : 10,
				queryParams : {
					name1 : 'easyui',
					subject1 : 'datagrid'
				},
				frozenColumns : [ [
				{
					field : 'id',
					hidden : true,
					align :'center'
				},{
					field:'batch',
					hidden : false,
					title :'批次',
					align :'center'
				},{
					field :'loadRate',
					title : '加载流量',
					hidden : false,
					align :'center'
				},{
					field :'selectRate',
					title : '查询流量',
					hidden : false,
					align :'center'
				}]],
				columns : [[
				    {
						field : 'taskEntity.name',
						title : '任务名称',
						width : $(this).width()*0.06,
						formatter:function(value,rowData,rowIndex) {
							return rowData.taskEntity.name;
						},
						align : 'center'
					},
					{
						field : 'startTime',
						title : '开始时间',
						width : $(this).width()*0.08,
						align : 'center'
					},
					{
						field : 'finishTime',
						title : '结束时间',
						width : $(this).width()*0.08,
						align : 'center'
					},
					{
						field : 'currentStatus',
						title :'当前状态',
						width : $(this).width()*0.06,
						formatter:function(value,rowData,rowIndex) {
							var currentStatus = '';
							switch(rowData.currentStatus) {
							case '0':
								currentStatus = '未开始';
								break;
							case '1':
								currentStatus = '运行中';
								break;
							case '2':
								currentStatus = '等待下一次运行';
								break;
							case '3':
								currentStatus = '等待中';
								break;
							case '4':
								currentStatus = '出现异常运行停止';
								break;
							case '5':
								currentStatus = '完成';
								break;
							default:
								currentStatus = '其他';
							}
							return currentStatus;
						},
						align : 'center'
					},
					{
						field : 'logPath',
						title :'日志路径',
						width : $(this).width()*0.12,
						align : 'center'
					}
				]],
			});
			//刷新表格
			var reloadGrid = function() {
				$('#taskstatus-table').datagrid('reload');
				setTimeout('reloadGrid()',5000);
			};
			$(function(){
				reloadGrid();
			});
</script>
</body>
</html>