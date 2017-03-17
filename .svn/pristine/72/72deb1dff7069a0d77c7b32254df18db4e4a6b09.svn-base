<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>资源管理信息</title>
<style type="text/css">
input {
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
<script type="text/javascript">
	//构造表格
	$(function() {
		grid = $('#source-table')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/dataSource/listDataSource',
							//title : '数据资源配置列表',
							border : false,
							fit : true,
							singleSelect : true,
							fitColumns : true,
							autoRowHeight : true,
							loadMsg : '数据源正在加载,请稍后 !',
							striped : true,
							pagination : true,
							rownumbers : true,
							onRowContextMenu : onRowContextMenu,
							resizeHandle : 'both',
							pagePosition : 'bottom',
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
								title : '数据源名称',
								width : 80,
								align : 'center'
							}, {
								field : 'server_ip',
								title : 'IP',
								width : 40,
								align : 'center'
							}, {
								field : 'port',
								title : '端口',
								width : 30,
								align : 'center',
							}, {
								field : 'username',
								title : '用户名',
								width : 80,
								align : 'center'
							}, {
								field : 'db_type',
								title : '传输类型',
								width : 40,
								align : 'center',
								formatter : function(value, rec) {
									if (value == "o") {
										return "Oracle";
									} else if (value == "m") {
										return "MySQL";
									} else if (value == "f") {
										return "FTP"
									} else if (value == "s") {
										return "SqlServer";
									} else if (value == "d") {
										return "DM6";
									} else if (value == "e") {
										return "DM7";
									} else {
										return "";
									}
								}

							} ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'oper_add',
								handler : newDataSource
							}, '-', {
								text : '修改',
								iconCls : 'icon-edit',
								handler : editDataSource
							}, '-', {
								text : '删除',
								iconCls : 'oper_remove',
								handler : removeDataSource
							} ]
						});
		$('#btn-save,#btn-cancel').linkbutton();

		//把数据源信息维护窗口
		win = $('#source-window').dialog({
			title : '数据源信息维护',
			width : 420,
			height : 380,
			modal : true,
			closed : true,
			draggable : false,
			buttons : '#dlg-buttons'
		});
		form = win.find('form');
		//设置分页控件  
		var p = $('#source-table').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});

	});

	var grid;
	var win;
	var win_detail;
	var form;
	var form_detail;
	var isAdd = true;
	function newDataSource() {
		win.window('open');
		form.form('clear');
		verification();
		isAdd = false;
		//添加数据源时，默认为文件类型
		selectInput('1');
		$("#device_type").get(0).selectedIndex = 0;
		isNewAdd = 'insert';
	}

	//显示数据源详细信息
	function view() {
		var row = grid.datagrid('getSelected');
		isNewAdd = 'view';
		if (row != null) {
			$('#detail-window').show();
			//根据数据源类型创建大小不同的对话框
			if (row.source_type == "0") {
				//数据源类型为数据库，数据源详细信息对话框
				win_detail = $('#detail-window').dialog({
					title : '数据源详细信息',
					width : 420,
					height : 340,
					modal : true,
					closed : true,
					resizable : false,
					draggable : false
				});
			} else {
				//数据源类型为文件，数据源详细信息对话框
				win_detail = $('#detail-window').dialog({
					title : '数据源详细信息',
					width : 420,
					height : 250,
					modal : true,
					closed : true,
					resizable : false,
					draggable : false
				});
			}
			form_detail = win_detail.find('form');
			win_detail.window('open');
			//先根据数据源类型确定表单中要显示哪些项，然后加载数据
			selectInput(row.source_type);
			form_detail.form('load', {
				name : row.name,
				//source_type : row.source_type,
				//db_type : row.db_type,
				server_ip : row.server_ip,
				//device_type : row.device_type,
				port : row.port,
				username : row.username,
				db_name : row.db_name,
				connect_url : row.connect_url,
				root_path : row.root_path
			});
			//填写数据源类型
			if (row.source_type == "0")
				$('#detail_source_type').val('数据库');
			else
				$('#detail_source_type').val('文件');
			//填写数据库类型
			var value = row.db_type;
			if (value == "o") {
				$('#detail_db_type').val('Oracle');
			} else if (value == "m") {
				$('#detail_db_type').val('MySQL');
			} else if (value == "f") {
				$('#detail_db_type').val('FTP');
			} else if (value == "s") {
				$('#detail_db_type').val('SqlServer');
			} else if (value == "d") {
				$('#detail_db_type').val('DM6');
			} else if (value == "e") {
				$('#detail_db_type').val('DM7');
			} else {
				$('#detail_db_type').val('');
			}
			////填写数据库类型
			if (row.device_type == "0")
				$('#detail_device_type').val('内网设备');
			else
				$('#detail_device_type').val('外网设备');
			//修改input元素的显示样式
			$('#detail_table').find('input').css({
				'border' : '0',
				'width' : '200'
			});
		}
	}

	//修改数据源
	function editDataSource() {
		var row = grid.datagrid('getSelected');
		isAdd = true;
		isNewAdd = 'update';
		if (row != null) {
			win.window('open');
			form.form('clear');
			verification();
			//先根据数据源类型确定表单中要显示哪些项，然后加载数据
			selectInput(row.source_type);
			form.form('load', {
				name : row.name,
				source_type : row.source_type,
				db_type : row.db_type,
				server_ip : row.server_ip,
				device_type : row.device_type,
				port : row.port,
				username : row.username,
				password : "c3d4=%",
				db_name : row.db_name,
				connect_url : row.connect_url,
				root_path : row.root_path
			});
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择数据源。'
			});
		}
		$('#root_path').attr("readonly", "readonly");
	}

	//保存数据源 
	function saveDataSource() {
		verification();
		db_url(); //在保存时，最后在拼接一次URL，保证URL是正确的
		//判断是新增数据源还是修改数据源
		if (isNewAdd == 'insert') {
			form.url = '${pageContext.request.contextPath}/dataSource/saveDataSource';
		} else {
			var row = grid.datagrid('getSelected');
			form.url = '${pageContext.request.contextPath}/dataSource/updateDataSource?id='
					+ row.id;
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
					$.messager.show({
						title : '错误',
						msg : data.msg
					});
				}
			}
		});
	}

	//删除数据源(逻辑删除)
	function removeDataSource() {
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
												url : "${pageContext.request.contextPath}/dataSource/deleteDataSource",
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
														grid.datagrid('reload');
														$.messager.show({
															title : '提示',
															msg : data.msg
														});
													}
												}
											});
								}
							});
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择数据源。'
			});
		}
	}

	var isTrue;

	//选择数据类型下拉菜单，将发生改变
	function selectInput(s) {
		var source_type = s;
		//文件
		if (source_type == "1") {
			$('.root_path_id').show();
			$('.db_type_id').hide();
			$('.db_connect_url').hide();
			$('.db_name_id').hide();
			$('#port').val('21');
			$('#root_path').val('/');
			$('#root_path').attr("disabled", "disabled");
			$("#source_type").get(0).selectedIndex = 0;
			//当显示详细信息是不需要验证
			if (isNewAdd != 'view')
				validateFile();
			//数据库
		} else if (source_type == "0") {
			$('.db_type_id').show();
			$('.db_connect_url').show();
			$('.db_name_id').show();
			$('.root_path_id').hide();
			if (!isAdd || $('#db_type').get(0).selectedIndex == -1)
				$("#db_type").get(0).selectedIndex = 0;
			showPort();//设置端口号
			$("#source_type").get(0).selectedIndex = 1;
			//当显示详细信息是不需要验证
			if (isNewAdd != 'view')
				validateDb();
		}
	}

	function showPort() {
		var source = $("#db_type").val();
		if (source == "o") {
			$('#port').val('1521');
		} else if (source == "m") {
			$('#port').val('3306');
		} else if (source == "s") {
			$('#port').val('1433');
		} else if (source == "d") {
			$('#port').val('12345');
		} else if (source == "e") {
			$('#port').val('5236');
		} else {
			$('#port').val('');
		}
		db_url();
	}

	//自动生成URL
	function db_url() {
		//获取数据源类型的值
		var type = $("#source_type").val();
		if (type == '0') {//数据源类型为数据库
			var source = $("#db_type").val();
			var server_ip = $('#server_ip').val();
			var port = $('#port').val();
			var db_name = $('#db_name').val();
			if (source == "o") {
				$("#connect_url").val(
						'jdbc:oracle:thin:@' + server_ip + ':' + port + ':'
								+ db_name);
			} else if (source == "m") {
				$("#connect_url")
						.val(
								'jdbc:mysql://'
										+ server_ip
										+ ':'
										+ port
										+ '/'
										+ db_name
										+ '?user=root&password=&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false');
			} else if (source == "s") {
				$("#connect_url").val(
						'jdbc:sqlserver://' + server_ip + ':' + port
								+ '; DatabaseName=' + db_name);
			} else if (source == "d") {
				$("#connect_url").val(
						'jdbc:dm6://' + server_ip + ':' + port + '/' + db_name);
			} else if (source == "e") {
				$("#connect_url").val(
						'jdbc:dm://' + server_ip + ':' + port + '/' + db_name);
			} else {
				$("#connect_url").val('');
			}
		}
	}

	//通用的表单验证
	function verification() {

		$('#name').validatebox({
			required : true,
			missingMessage : '请填写数据源名称!'
		});
		$('#server_ip').validatebox({
			required : true,
			missingMessage : '请填写IP地址!'
		});
		$('#port').validatebox({
			required : true,
			missingMessage : '请填写端口!'
		});
		$('#username').validatebox({
			required : true,
			missingMessage : '请填写用户名!',
			validType : 'length[0,50]',
			invalidMessage : '不能超过50个字符！'
		});
		$('#password').validatebox({
			required : true,
			missingMessage : '请填写密码!',
			validType : 'length[0,50]',
			invalidMessage : '不能超过50个字符！'
		});

		if ($('#name').val() == '') {
			document.getElementById('name').focus();
			isTrue = 1;
		} else if ($('#server_ip').val() == '') {
			document.getElementById('server_ip').focus();
			isTrue = 1;
		} else if ($('#port').val() == '') {
			document.getElementById('port').focus();
			isTrue = 1;
		} else if ($('#username').val() == '') {
			document.getElementById('username').focus();
			isTrue = 1;
		} else if ($('#password').val() == '') {
			document.getElementById('password').focus();
			isTrue = 1;
		} else {
			isTrue = 0;
		}
	}

	//文件部分的表单验证，只需验证文件路径
	function validateFile() {
		$('#root_path').validatebox({
			required : true,
			missingMessage : '请填写文件路径!'
		});
		if ($('#root_path').val() == '') {
			document.getElementById('root_path').focus();
			isTrue = 1;
		}
	}

	//数据库部分的表单验证，只需验证数据库类型、数据库名称和URL地址
	function validateDb() {
		$('#connect_url').validatebox({
			required : true,
			missingMessage : '请填写URL地址!'
		});
		$('#db_name').validatebox({
			required : true,
			missingMessage : '请填写数据库名称!',
			validType : 'length[0,50]',
			invalidMessage : '不能超过50个字符！'
		});
		$('#db_type').validatebox({
			required : true,
			missingMessage : '请选择数据类型!'
		});
		if ($('#db_name').val() == '') {
			document.getElementById('db_name').focus();
			isTrue = 1;
		}
	}

	$
			.extend(
					$.fn.validatebox.defaults.rules,
					{
						ip : {
							validator : function(value, param) {
								return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
										.test(value);
							},
							message : 'IP格式不正确'
						},
						testport : {
							validator : function(value, param) {
								return /^[0-9]{1,}$/.test(value);
							},
							message : '只能输入数字'
						},
						samename : {
							validator : function(value) {
								var row = grid.datagrid('getSelected');
								var isTrue = false;
								if (row == null) {
									$
											.ajax({
												async : false,
												type : "POST",
												url : "${pageContext.request.contextPath}/dataSource/getSameSourceName",
												data : {
													name : value
												},
												success : function(data) {
													if (data.dealFlag) {
														isTrue = false;
													} else {
														isTrue = true;
													}
												}
											});
								} else {
									$
											.ajax({
												async : false,
												type : "POST",
												url : "${pageContext.request.contextPath}/dataSource/getSameSourceName",
												data : {
													name : value,
													id : row.id
												},
												success : function(data) {
													if (data.dealFlag) {
														isTrue = false;
													} else {
														isTrue = true;
													}
												}
											});
								}

								return isTrue;
							},
							message : '已有重名的数据源'
						}
					});

	function getDeviceType() {

		$('#port').validatebox({
			required : false,
			missingMessage : '请填写端口号!'
		});

		$('#connect_url').validatebox({
			required : false,
			missingMessage : '请填写URL地址!'
		});

		$('#name').validatebox({
			required : false
		});

		$('#username').validatebox({
			required : false,
			missingMessage : '请填写用户名!'
		});

		$('#password').validatebox({
			required : false,
			missingMessage : '请填写密码!'
		});

		$('#db_name').validatebox({
			required : false,
			missingMessage : '请填写数据库名!'
		});

		$('#connect_url').validatebox({
			required : false,
			missingMessage : '请填写URL地址!'
		});

		$('#db_name').validatebox({
			required : false,
			missingMessage : '请填写URL地址!'
		});
		$('#db_type').validatebox({
			required : false
		});
		$('#db_name').validatebox({
			required : false
		});

		form
				.form(
						'submit',
						{
							url : '${pageContext.request.contextPath}/dataSource/getDeviceType',
							success : function(data) {
								eval('data=' + data);
								if (data.dealFlag) {
									if (data.deviceType == 'i') {
										$("#device_type").get(0).selectedIndex = 0;
									} else if (data.deviceType == 'o') {
										$("#device_type").get(0).selectedIndex = 1;
									}
								} else {
									$.messager.alert('错误', data.msg, 'error');
								}
							}
						});
		db_url();
	}

	var isNewAdd;
	//测试网络
	function testNetwork() {
		//判断是新增数据源或是修改数据源
		if (isNewAdd == 'insert') {
			form.url = '${pageContext.request.contextPath}/dataSource/testNetwork?datatype='
					+ isNewAdd;
		} else {
			var row = grid.datagrid('getSelected');
			form.url = '${pageContext.request.contextPath}/dataSource/testNetwork?id='
					+ row.id + '&datatype=' + isNewAdd;
		}
		form.form('submit', {
			url : form.url,
			success : function(data) {
				eval('data=' + data);
				if (data.dealFlag) {
					$.messager.show({
						title : '提示',
						msg : data.isConn
					});
				} else {
					$.messager.alert('错误', data.msg, 'error');
				}
			}
		});
	}

	//关闭窗口
	function closeWindow() {
		win.window('close');
		win_detail.window('close');
	}

	//添加右击菜单内容
	function onRowContextMenu(e, rowIndex, rowData) {
		e.preventDefault();
		//先选中一行
		grid.datagrid('selectRow', rowIndex);
		/* var selected=$("#test").datagrid('getRows');  //获取所有行集合对象    
		selected[rowIndex].id; //index为当前右键行的索引，指向当前行对象    */
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	}
</script>
</head>
<body>

	<table id="source-table"></table>

	<div id="source-window" title="数据源信息维护"
		data-options="resizable:false,draggable:false,maximizable:false,minimizable:false,collapsible:false,modal:true">
		<div style="padding: 10px;">
			<form method="post">
				<table>
					<tr>
						<td style="text-align: right;">数据源名称：</td>
						<td><input id="name" name="name" validType="samename" /> <font
							color="red"><b>*</b></font></td>
					</tr>
					<tr>
						<td style="text-align: right;">数据源类型：</td>
						<td><select id="source_type" class="source_type"
							name="source_type" onchange="selectInput(this.value)"
							style="width: 150px;">
								<option value="1" selected="selected">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文件</option>
								<option value="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数据库</option>
						</select></td>
					</tr>
					<tr id="db_type_id" class="db_type_id">
						<td style="text-align: right;">数据库：</td>
						<td><select id="db_type" class="db_type" name="db_type"
							onchange="showPort()" style="width: 150px;">
								<option value="" selected="selected">&nbsp;&nbsp;&nbsp;&nbsp;--请选择--</option>
								<option value="o">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Oracle</option>
								<option value="m">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MySQL</option>
								<option value="s">&nbsp;&nbsp;&nbsp;&nbsp;SqlServer</option>
								<option value="d">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;达梦6</option>
								<option value="e">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;达梦7</option>
						</select></td>
					</tr>
					<tr>
						<td style="text-align: right;">IP：</td>
						<td><input id="server_ip" name="server_ip" validType="ip"
							onblur="getDeviceType()"></input> <font color="red"><b>*</b></font>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">设备：</td>
						<td><select id="device_type" name=device_type
							style="width: 150px;">
								<option value="0" selected="selected">&nbsp;&nbsp;&nbsp;--
									内网设备 --</option>
								<option value="1">&nbsp;&nbsp;&nbsp;-- 外网设备 --</option>
						</select> <font color="red"><b>*</b></font></td>
					</tr>
					<tr>
						<td style="text-align: right;">端口：</td>
						<td><input id="port" name="port" validType="testport"
							onblur="db_url()"></input> <font color="red"><b>*</b></font></td>
					</tr>

					<tr>
						<td style="text-align: right;">用户名：</td>
						<td><input id="username" name="username"></input> <font
							color="red"><b>*</b></font></td>
					</tr>
					<tr>
						<td style="text-align: right;">密码：</td>
						<td><input type="password" id="password" value="123456"
							name="password"></input> <font color="red"><b>*</b></font></td>
					</tr>
					<tr id="db_name_id" class="db_name_id">
						<td style="text-align: right;">数据库名：</td>
						<td><input id="db_name" class="db_name" name="db_name"
							onblur="db_url()"></input> <font color="red"><b>*</b></font></td>
					</tr>
					<tr id="db_connect_url" class="db_connect_url">
						<td style="text-align: right;">URL：</td>
						<td><input id="connect_url" name="connect_url"></input> <font
							color="red"><b>*</b></font></td>
					</tr>
					<tr id="root_path_id" class="root_path_id">
						<td style="text-align: right;">文件路径：</td>
						<td><input id="root_path" class="root_path" name="root_path"
							value="/"></input></td>
					</tr>
				</table>
				<input type="hidden" name="del_flag" value="1" />
			</form>
		</div>
		<div id="dlg-buttons" style="text-align: center; padding: 5px;">
			<a href="javascript:void(0)" onclick="testNetwork()" id="btn-save"
				icon="oper_but_test">测试连接</a> <a href="javascript:void(0)"
				onclick="saveDataSource()" id="btn-save" icon="oper_but_save">保存</a> <a
				href="javascript:void(0)" onclick="closeWindow()" id="btn-cancel"
				icon="oper_but_cancel">取消</a>
		</div>
	</div>

	<div id="detail-window" title="数据源详细信息" style="display: none"
		data-options="draggable:false,maximizable:false,minimizable:false,collapsible:false">
		<div style="padding: 10px;">
			<form onkeydown="if(event.keyCode==8)return false;">
				<table id="detail_table">
					<tr>
						<td style="text-align: right;">数据源名称：</td>
						<td><input id="detail_name" name="name" readonly="readonly" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">数据源类型：</td>
						<td><input id="detail_source_type" class="source_type"
							name="source_type" readonly="readonly" /></td>
					</tr>
					<tr class="db_type_id">
						<td style="text-align: right;">数据库：</td>
						<td><input id="detail_db_type" class="db_type" name="db_type"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">IP：</td>
						<td><input id="detail_server_ip" name="server_ip"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">设备：</td>
						<td><input id="detail_device_type" name="device_type"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">端口：</td>
						<td><input id="detail_port" name="port" readonly="readonly" /></td>
					</tr>

					<tr>
						<td style="text-align: right;">用户名：</td>
						<td><input id="detail_username" name="username"
							readonly="readonly" /></td>
					</tr>
					<tr class="db_name_id">
						<td style="text-align: right;">数据库名：</td>
						<td><input id="detail_db_name" class="db_name" name="db_name"
							readonly="readonly" /></td>
					</tr>
					<tr class="db_connect_url">
						<td style="text-align: right; padding-top: 3px;" valign="top">URL：</td>
						<td><textarea name="connect_url" readonly="readonly"
								style="resize: none; font-size: 12px; width: 320px; height: 60px; border: 0; overflow: hidden"></textarea>
						</td>
					</tr>
					<tr class="root_path_id">
						<td style="text-align: right;">文件路径：</td>
						<td><input id="detail_root_path" name="root_path"
							class="root_path" value="/" readonly="readonly" /></td>
					</tr>
				</table>
				<input type="hidden" name="del_flag" value="1" />
			</form>
		</div>
	</div>


	<!-- 右键菜单 -->
	<div id="mm" class="easyui-menu" style="width: 120px;">
		<div onClick="view()" data-options="iconCls:'icon-search'">详细信息</div>
		<div class="menu-sep"></div>
		<div onClick="newDataSource()" data-options="iconCls:'oper_add'">新增</div>
		<div onClick="editDataSource()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onClick="removeDataSource()" data-options="iconCls:'oper_remove'">删除</div>
	</div>
</body>
</html>