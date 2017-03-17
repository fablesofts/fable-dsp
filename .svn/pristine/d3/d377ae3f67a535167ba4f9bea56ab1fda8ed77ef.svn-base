<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>用户操作日志管理</title>
<!-- 验证脚本 -->
<script type="text/javascript">
	$(function() {

		//初始化展示页grid数据
		grid = $('#user-table')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/userOperationLog/findUsersOperationLog',
// 							title : '用户操作日志管理',
							fit : true,
							border : 0,
							singleSelect : true,
							fitColumns : true,
							autoRowHeight : true,
							loadMsg : '用户操作日志正在加载,请稍后 !',
							striped : true,
							autoRowHeight : true,
							pagination : true,
							rownumbers : true,
							resizeHandle : 'both',
							pagePosition : 'bottom',
							scrollbarSize : 10,
							frozenColumns : [ [ {
								field : 'id',
								hidden : true
							}, ] ],
							columns : [ [ {
								field : 'targetName',
								title : '操作关键字',
								width : 80,
								align : 'center'
							},{
								field : 'operationUser',
								title : '操作人',
								width : 80,
								align : 'center'
							},{
								field : 'operationTime',
								title : '操作时间',
								width : 80,
								align : 'center',
								formatter : function(value, rec) {
									return format(value, 'yyyy-MM-dd HH:mm:ss');
								}
							}, {
								field : 'operationDescribe',
								title : '描述',
								width : 80,
								align : 'center'
							} ] ]
						});

		//设置分页控件  
		var p = $('#user-table').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	});
	//设置需要的全局变量
	var grid;
	
	//时间戳转换为date
	var format = function(time, format){
	    var t = new Date(time);
	    var tf = function(i){return (i < 10 ? '0' : '') + i};
	    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
	        switch(a){
	            case 'yyyy':
	                return tf(t.getFullYear());
	                break;
	            case 'MM':
	                return tf(t.getMonth() + 1);
	                break;
	            case 'mm':
	                return tf(t.getMinutes());
	                break;
	            case 'dd':
	                return tf(t.getDate());
	                break;
	            case 'HH':
	                return tf(t.getHours());
	                break;
	            case 'ss':
	                return tf(t.getSeconds());
	                break;
	        }
	    });
	};
	
</script>
</head>
<body>

	<!-- begin .location --> <!--
	<div class="location">
		<span><img
			src="${pageContext.request.contextPath}/style/images/main/home.gif" /></span>
		<ul>
			<li class="part"><img
				src="${pageContext.request.contextPath}/style/images/main/line--.gif"
				align="absmiddle" /></li>
			<li class="locationName locationName_4">系统服务</li>
			<li class="part"><img
				src="${pageContext.request.contextPath}/style/images/main/line--.gif"
				align="absmiddle" /></li>
			<li class="locationName locationName_4">用户日志</li>
		</ul>
	</div>
	<!-- end .location -->
	
	<!-- 展示页gird -->
	<table id="user-table" style="height: 500px"></table>
</body>
</html>