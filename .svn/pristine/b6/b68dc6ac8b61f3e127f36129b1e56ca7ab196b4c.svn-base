<%@ page language="java" pageEncoding="utf-8"%>

<html>
<head>
<title>资源列表</title>
<script type="text/javascript">
	$(function() {
		//将win隐藏，并设为全局变量
		window.win = $('#win').window({
			closed : true
		});
	});
</script>
<script type="text/javascript">
	$(function() {
		window.p = $('#resourceInfo-table')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/resourceInfo/findResourceInfoList',
							title : '资源列表',
							singleSelect : true,
							fitColumns : true,
							autoRowHeight : true,
							loadMsg : '资源正在加载,请稍后 !',
							striped : true,
							autoRowHeight : true,
							pagination : true,
							rownumbers : true,
							resizeHandle : 'both',
							pagePosition : 'top',
							scrollbarSize : 10,
							queryParams : {
								name1 : 'easyui',
								subject1 : 'datagrid'
							},
							frozenColumns : [ [ {
								field : 'id',
								hidden : true
							}, ] ],
							columns : [ [ {
								field : 'name',
								title : '资源名',
								width : 80,
								align : 'center'

							}, {
								field : 'url',
								title : '资源映射',
								width : 80,
								align : 'center'
							}, {
								field : 'type',
								width : 80,
								title : '资源类型',
								align : 'center'
							}, {
								field : 'enabled',
								title : '状态',
								width : 80,
								align : 'center'
							} ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler :null
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : null
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : null
							}, '-', {
								text : '修改密码',
								iconCls : 'icon-edit',
								handler : null
							} ]
						});
	});
	window.p = $('#resourceInfo-table').datagrid('getPager');
	$(window.p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为10  
		pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
</script>
<script type="text/javascript">
/* function newResourceInfo() {
	win.window('open');
	initDepartments();
	form.form('clear');
	form.url = '${pageContext.request.contextPath}/employee/saveEmployee';
}
function editResourceInfo() {
	var row = grid.datagrid('getSelected');
	if (row) {
		win.window('open');
		initDepartments(row.department.id);
		form.form('load',
				'${pageContext.request.contextPath}/employee/getEmployeeById?id='
						+ row.id);
		window.setTimeout(function() {
			if (row.sex) {
				$(form[0].sex[0]).attr("checked", "true");
			} else {
				$(form[0].sex[1]).attr("checked", "true");
			}
		}, 50);
		form.url = '${pageContext.request.contextPath}/employee/updateEmployee?id='
				+ row.id;
	} else {
		$.messager.show({
			title : '警告',
			msg : '请先选择员工。'
		});
	}
}
 */function saveResourceInfo() {
	var birth = form[0].birth.value;
	if (new Date(birth) == 'Invalid Date') {
		alert('日期格式不正确');
		return;
	}
	if ($("#departments").val() == null) {
		alert('请选择部门');
		return;
	}
	form.form('submit', {
		url : form.url,
		success : function(data) {
			eval('data=' + data);
			if (data.dealFlag) {
				grid.datagrid('reload');
				win.window('close');
				$.messager.show({
					title : '提示',
					msg : data.msg
				});
			} else {
				$.messager.alert('错误', data.msg, 'error');
			}
		}
	});

}
function removeResourceInfo() {
	var selected = grid.datagrid('getSelected');
	if (selected) {
		$.messager
				.confirm(
						'warning',
						'确认删除么?',
						function(id) {
							if (id) {
								id = selected.id;
								$
										.ajax({
											type : "POST",
											url : "${pageContext.request.contextPath}/employee/deleteEmployee",
											data : "id=" + id,
											dataType : "json",
											success : function callback(
													data) {
												if (!data.dealFlag) {
													$.messager.show({
														title : '警告',
														msg : '删除失败。'
													});
												} else {
													$.messager.show({
														title : '提示',
														msg : '删除成功'
													});
												}
											}
										});
								grid.datagrid('reload');
							}
						});
	} else {
		$.messager.show({
			title : '警告',
			msg : '请先选择兴趣。'
		});
	}
}
</script>
</head>
<body>
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
			<li class="locationName locationName_4">资源列表</li>
		</ul>
	</div>


	<!-- 展示页gird -->
	<table id="resourceInfo-table" style="height: 500px"></table>

	<div id="win" title="资源信息维护" style="width: 420px; height: 300px;"
		class="easyui-dialog">
		<div style="padding: 20px 20px 40px 80px;">
			<form method="post">
				<table>
					<tr>
						<td>资源名称：</td>
						<td><input name="name" /></td>
					</tr>
					<tr>
						<td>资源映射：</td>
						<td><input name="url" /></td>
					</tr>
					<tr>
						<td>资源类型：</td>
						<td><input name="type" /></td>
					</tr>
					<tr>
						<td>资源描述：</td>
						<td><input name="description" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align: center; padding: 5px;">
			<a href="null" id="javascript:window.win.window('close')" icon="icon-save">保存</a> <a
				href="javascript:void(0)" onclick="javascript:window.win.window('close')"
				icon="icon-cancel">取消</a>
		</div>
	</div>

</body>
</html>