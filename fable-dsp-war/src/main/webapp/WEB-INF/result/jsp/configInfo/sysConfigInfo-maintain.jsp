<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>系统配置管理</title>
<!-- 重写easyui validatebox样式 -->
<style type="text/css">
.white {
	background: #ffffff;
	border: 1px solid #7EBDD9;
	min-height: 20px;
}

.validatebox-invalid {
	background: #ffffff;
	border: 1px solid #7EBDD9;
	min-height: 20px;
}
</style>
<!-- 验证脚本 -->
<script type="text/javascript">
	$(function() {
		$('#sysConfigName,#editSysConfigName').validatebox({
			required : true,
			missingMessage : '请输入参数名!'
		});
		$('#sysConfigValue,#editSysConfigValue').validatebox({
			required : true,
			missingMessage : '请输入参数值!'
		});
		$('#category,#editCategory').validatebox({
			required : true,
			missingMessage : '请选择参数类别!'
		});
		$('#name').validatebox({
			required : true,
			missingMessage : '请输入参数别名!'
		});

		//初始化配置信息列表数据
		grid = $('#sysConfig-table')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/sysConfigInfo/findSysConfigInfoList',
							//title : '系统参数配置信息列表',
							border : false,
							singleSelect : true,
							fitColumns : true,
							autoRowHeight : true,
							loadMsg : '系统配置信息正在加载,请稍后 !',
							striped : true,
							nowrap : false,
							pagination : true,
							rownumbers : true,
							fit : true,
							resizeHandle : 'both',
							pagePosition : 'bottom',
							scrollbarSize : 10,
							onRowContextMenu : onRowContextMenu,
							frozenColumns : [ [ {
								field : 'id',
								hidden : true
							}, ] ],
							columns : [ [ {
								field : 'name',
								title : '参数别名',
								width : 80,
								align : 'center'
							}, {
								field : 'sysConfigName',
								title : '参数名',
								width : 80,
								align : 'center'
							}, {
								field : 'sysConfigValue',
								title : '参数值',
								width : 80,
								align : 'center'
							}, {
								field : 'category',
								title : '参数类别',
								width : 80,
								align : 'center',
								formatter : function(value, row, index) {
									if (value == "sys")
										return "系统";
									else if (value == "general")
										return "普通";
									else
										return "";
								}
							}, {
								field : 'description',
								title : '描述',
								width : 80,
								align : 'center'

							} ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'oper_add',
								handler : add
							}, '-', {
								text : '编辑',
								iconCls : 'icon-edit',
								handler : edit
							}, '-', {
								text : '删除',
								iconCls : 'oper_remove',
								handler : del
							} ]
						});
		//初始化按钮
		$('#btn-save,#btn-cancel').linkbutton();
		//定义新增配置参数对话框
		newWin = $('#newConfig-window').dialog({
			title : '新增配置参数',
			width : 420,
			height : 280,
			modal : true,
			closed : true,
			draggable : false,
			buttons : '#newConfig-buttons'
		});
		//定义修改配置参数对话框
		editWin = $('#editConfig-window').dialog({
			title : '修改配置参数',
			width : 420,
			height : 280,
			modal : true,
			closed : true,
			draggable : false,
			buttons : '#editConfig-buttons'
		});
		//定义显示配置参数详细信息对话框
		detailWin = $('#detail-window').dialog({
			title : '配置参数详细信息',
			width : 420,
			height : 200,
			modal : true,
			closed : true,
			draggable : false
		});
		//初始化form
		newForm = newWin.find('form');
		editForm = editWin.find('form');

		//设置分页控件  
		var p = $('#sysConfig-table').datagrid('getPager');
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
	var newWin;
	var newForm;
	var editWin;
	var editForm;
	var detailWin;
	//显示配置详细信息的方法
	function view() {
		var row = grid.datagrid('getSelected');
		if (row != null) {
			detailWin.window('open');
			var value = row.category;
			var category = "";
			if (value == "sys")
				category = "系统";
			else if (value == "general")
				category = "普通";
			//回显选中配置的详细信息数据
			$('#detail-window').find('form').form('load', {
				name : row.name,
				sysConfigName : row.sysConfigName,
				sysConfigValue : row.sysConfigValue,
				category : category,
				description : row.description
			});
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择配置参数。'
			});
		}
	}

	//打开新增窗口 清空form内容 设置form提交的url
	function add() {
		newWin.window('open');
		newForm.form('clear');
		//新增配置参数时，参数类别默认选择下拉框中的第一个
		$('#category').get(0).selectedIndex = 0;
		newForm.url = '${pageContext.request.contextPath}/sysConfigInfo/addSysConfigInfo';
	}
	//提交新增配置的方法
	function save() {
		newForm.form('submit', {
			url : newForm.url,
			success : function(data) {
				eval('data=' + data);
				if (data.dealFlag) {
					grid.datagrid('reload');
					newWin.window('close');
					$.messager.show({
						title : '提示',
						msg : data.msg,
						showType : 'slide',
						timeout : 2000
					});
				} else {
					$.messager.show({
						title : '提示',
						msg : data.msg,
						showType : 'slide',
						timeout : 2000
					});
				}
			}
		});
	}
	//打开修改窗口给form加载对应的回显数据内容 设置form提交的url
	function edit() {
		var row = grid.datagrid('getSelected');
		if (row != null) {
			var name= row.sysConfigName;
			if(name == "CHECK_CODE") {
				$('#editOtherValue,#editThemeValue').hide().removeAttr("name");
				$('#editCheckCodeValue').show().attr("name","sysConfigValue");
			} else if (name == "THEME") {
				$('#editOtherValue,#editCheckCodeValue').hide().removeAttr("name");
				$('#editThemeValue').show().attr("name","sysConfigValue");
			} else {
				$('#editThemeValue,#editCheckCodeValue').hide().removeAttr("name");
				$('#editOtherValue').show().attr("name","sysConfigValue");
			}
			editWin.window('open');
			editForm.form('load', {
				id : row.id,
				name : row.name,
				sysConfigName : row.sysConfigName,
				sysConfigValue : row.sysConfigValue,
				category : row.category,
				description : row.description
			});
			editForm.url = '${pageContext.request.contextPath}/sysConfigInfo/updateSysConfigInfo';
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择配置参数。'
			});
		}
	}
	//提交修改配置信息的方法
	function update() {
		editForm.form('submit', {
			url : editForm.url,
			success : function(data) {
				eval('data=' + data);
				if (data.dealFlag) {
					grid.datagrid('reload');
					editWin.window('close');
					$.messager.show({
						title : '提示',
						msg : data.msg,
						showType : 'slide',
						timeout : 2000
					});
				} else {
					{
						$.messager.show({
							title : '提示',
							msg : data.msg,
							showType : 'slide',
							timeout : 2000
						});
					}
				}
			}
		});
	}
	//提交删除配置的方法
	function del() {
		var selected = grid.datagrid('getSelected');
		if (selected) {
			$.messager
					.confirm(
							'警告',
							'确认删除么?',
							function(id) {
								if (id) {
									id = selected.id;
									$
											.ajax({
												type : "POST",
												url : "${pageContext.request.contextPath}/sysConfigInfo/deleteSysConfigInfo",
												data : "id=" + id,
												dataType : "json",
												success : function callback(
														data) {
													if (!data.dealFlag) {
														$.messager.show({
															title : '警告',
															msg : data.msg
														});
													} else {
														$.messager.show({
															title : '提示',
															msg : data.msg
														});
													}
													grid.datagrid('reload');
												}
											});
								}
							});
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择配置参数。'
			});
		}
	}
	//关闭window方法
	function closeWindow() {
		newWin.window('close');
		editWin.window('close');
		detailWin.window('close');//关闭配置参数详细信息窗口
	}

	//重写easyui 验证方法 
	$
			.extend(
					$.fn.validatebox.defaults.rules,
					{
						isSameConfigName : {
							validator : function(value, param) {
								var id = $('#id').val();
								var flag = "";
								$
										.ajax({
											async : false,
											type : "POST",
											url : "${pageContext.request.contextPath}/sysConfigInfo/isSameConfigName",
											data : {
												sysConfigName : value,
												id : id
											},
											dataType : "json",
											success : function(data) {
												if (data.dealFlag) {
													flag = true;
												} else {
													flag = false;
												}
											}
										});
								return flag;
							},
							message : '参数名已存在!'
						},

						name : {
							validator : function(value, param) {
								var reg = /^[\u4e00-\u9fa5]+$/;
								return reg.exec(value);
							},
							message : '请输入汉字名称!'
						},
						length : {
							validator : function(value, param) {
								return value.length < 50;
							},
							message : '请输入少于50字描述'
						},
						configName : {
							validator : function(value, param) {
								var reg = /^[a-zA-Z]\w*$/;
								return reg.test(value);
							},
							message : '参数名中只能出现字母、数字和下划线，且首字符必须为字母!'
						}
					});

	//添加右击菜单内容
	function onRowContextMenu(e, rowIndex, rowData) {
		e.preventDefault();
		//先选中一行
		grid.datagrid('selectRow', rowIndex);
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	}
</script>
</head>
<body>

	<!-- 展示页gird -->
	<table id="sysConfig-table" style="height: 500px"></table>

	<!-- 显示配置详细信息的div -->
	<div id="detail-window" title="配置参数详细信息" class="easyui-dialog">
		<div style="padding: 10px 10px 10px 10px;">
			<form onkeydown="if(event.keyCode==8)return false;">
				<table>
					<tr>
						<td style="text-align: right;" width="60">参数别名：</td>
						<td><input name="name" style="border: 0;"
							readonly="readonly" />&nbsp;</td>
					</tr>
					<tr>
						<td style="text-align: right;" width="60">参数名：</td>
						<td><input name="sysConfigName" style="border: 0;"
							readonly="readonly" />&nbsp;</td>
					</tr>
					<tr>
						<td style="text-align: right;">参数值：</td>
						<td><input name="sysConfigValue" style="border: 0;"
							readonly="readonly" />&nbsp;</td>
					</tr>
					<tr>
						<td style="text-align: right;">参数类别：</td>
						<td><input name="category" readonly="readonly"
							style="border: 0;" /></td>
					</tr>
					<tr>
						<td valign="top" style="text-align: right; padding-top: 3px;">描述：</td>
						<td><textarea name="description" readonly="readonly"
								style="resize: none; width: 180px; FONT-SIZE: 13px; height: 50px; border: 0; overflow: hidden"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<!-- 新增配置window  -->
	<div id="newConfig-window" title="新增配置" class="easyui-dialog">
		<div style="padding: 10px 10px 10px 10px;">
			<form method="post">
				<table>
					<tr>
						<td style="text-align: right;">参数别名：</td>
						<td><input id="name" name="name" class="white" />&nbsp;<font
							color="red">*</font></td>
					</tr>
					<tr>
						<td style="text-align: right;">参数名：</td>
						<td><input id="sysConfigName" name="sysConfigName" class="white" 
							data-option="validType:['isSameConfigName[]','configName[]']" />&nbsp;<font
							color="red">*</font></td>
					</tr>
					<tr>
						<td style="text-align: right;">参数值：</td>
						<td><input id="sysConfigValue" name="sysConfigValue"
							class="white" />&nbsp;<font color="red">*</font></td>
					</tr>
					<tr>
						<td style="text-align: right;">参数类别：</td>
						<td><select id="category" name="category" class="white">
								<option value="sys" selected="selected">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统</option>
								<option value="general">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;普通</option>
						</select>&nbsp;<font color="red">*</font></td>
					</tr>
					<tr>
						<td valign="top" style="text-align: right;">描述：</td>
						<td><textarea id="description" name="description"
								class="easyui-validatebox white"
								style="resize: none; width: 148px; FONT-SIZE: 13px; height: 65px;"
								validType="length[]"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="newConfig-buttons" style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" onclick="save()" id="btn-save"
			icon="oper_but_save">保存</a> <a href="javascript:void(0)"
			onclick="closeWindow()" id="btn-cancel" icon="oper_but_cancel">取消</a>
	</div>
	<!-- 修改的div -->
	<div id="editConfig-window" title="修改配置" class="easyui-dialog">
		<div style="padding: 10px 10px 10px 10px;">
			<form method="post">
				<table>
					<input id="id" name="id" type="hidden" />
					<!-- 要修改的配置参数的ID -->
					<tr>
						<td style="text-align: right;">参数别名：</td>
						<td><input name="name" class="white" />&nbsp;<font
							color="red">*</font></td>
					</tr>
					<tr>
						<td style="text-align: right;">参数名：</td>
						<td><input id="editSysConfigName" name="sysConfigName" class="white" 
							data-option="validType:['isSameConfigName[]','configName[]']" />&nbsp;<font
							color="red">*</font></td>
					</tr>
					<tr>
						<td style="text-align: right;">参数值：</td>
						<td>   
							<!-- <input id="editSysConfigValue" name="sysConfigValue" type="hidden"/> -->
							<input id="editOtherValue" class="white" />
							<select id="editCheckCodeValue" class="white" >
								<option value="on">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;启用</option>
								<option value="off">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;禁用</option>
							</select>
							<select id="editThemeValue" class="white" >   
								<option value="default">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;default</option>
								<option value="black">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;black</option>
								<option value="gray">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;gray</option>
								<option value="metro">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;metro</option>
								<option value="bootstrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;bootstrap</option>
							</select><font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">参数类别：</td>
						<td><select id="editCategory" name="category" class="white">
								<option value="sys">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统</option>
								<option value="general">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;普通</option>
						</select>&nbsp;<font color="red">*</font></td>
					</tr>
					<tr>
						<td valign="top" style="text-align: right;">描述：</td>
						<td><textarea name="description"
								class="easyui-validatebox white"
								style="resize: none; width: 148px; FONT-SIZE: 13px; height: 65px;"
								validType="length[]"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="editConfig-buttons" style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" onclick="update()" id="btn-save"
			icon="oper_but_save">保存</a> <a href="javascript:void(0)"
			onclick="closeWindow()" id="btn-cancel" icon="oper_but_cancel">取消</a>
	</div>

	<!-- 右键菜单 -->
	<div id="mm" class="easyui-menu" style="width: 120px;">
		<div onClick="view()" data-options="iconCls:'icon-search'">详细信息</div>
		<div class="menu-sep"></div>
		<div onClick="add()" data-options="iconCls:'oper_add'">新增</div>
		<div onClick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onClick="del()" data-options="iconCls:'oper_remove'">删除</div>
	</div>
</body>
</html>