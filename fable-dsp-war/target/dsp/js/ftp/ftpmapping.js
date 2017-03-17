var pageContext;
$(function() {
	pageContext=$("#pageContext").val();
	$('#saveinnerusername').validatebox({
		required : true,
		missingMessage : '请输入内交换用户名'
	});
	$('#saveouterusername').validatebox({
		required : true,
		missingMessage : '请输入外交换用户名'
	});
	$('#newinnerAddress').validatebox({
		required : true,
		missingMessage : '请输入内交换地址'
	});
	$('#newouterAddress').validatebox({
		required : true,
		missingMessage : '请输入外交换地址'
	});
	
	$('#editftpusername').validatebox({
		required : true,
		missingMessage : '请输入内交换用户名'
	});
	$('#editouterusername').validatebox({
		required : true,
		missingMessage : '请输入外交换用户名'
	});
	$('#editinnerAddress').validatebox({
		required : true,
		missingMessage : '请输入内交换地址'
	});
	$('#editouterAddress').validatebox({
		required : true,
		missingMessage : '请输入外交换地址'
	});
	
	//初始化展示页grid数据
	$('#ftpmapping-table').datagrid(
			{
				url : pageContext+'/ftp/findFtpMappingList',
				singleSelect : true,
				border:0,
				fit : true,
				fitColumns:true,
				onRowContextMenu : onRowContextMenu,
				autoRowHeight : true,
				loadMsg : '目录映射信息正在加载,请稍后 !',
				striped : true,
				pagination : true,
				rownumbers : true,
				resizeHandle : 'both',
				pagePosition : 'bottom',
				scrollbarSize : 10,
				frozenColumns : [ [ {
					field : 'id',
					hidden : true
				}]],
				columns : [[{
						field : 'innerUserName',
						title : '内交换用户名',
						width : $(this).width()*0.23,
						align : 'center'
					},
					{
						field : 'outerUserName',
						title : '外交换用户名',
						width : $(this).width()*0.23,
						align : 'center'
					},
					{
						field : 'innerAddress',
						title : '内交换路径',
						width : $(this).width()*0.23,
						align : 'center'
					},
					{
						field : 'outerAddress',
						title : '外交换路径',
						width : $(this).width()*0.23,
						align : 'center'
					}
				]],
				toolbar : [{
							text : '新增',
							iconCls : 'icon-add',
							handler : addFtpMapping
						}, '-', {
							text : '编辑',
							iconCls : 'icon-edit',
							handler : editFtpMapping
						}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : delFtpMapping
						}
						]
					});
	//初始化按钮
	$('#btn-save,#btn-cancel').linkbutton();
	$('#newftpmapping-window').dialog({
		title : '新增FTP映射关系',
		width : 420,
		height : 250,
		closed : false,
		modal : true,
		closed : true,
		draggable : false,
		buttons : '#dlg-buttons'
	});

	$('editftpmapping-window').dialog({
		title : '修改FTP映射关系',
		width : 420,
		height : 250,
		closed : false,
		modal : true,
		closed : true,
		draggable : false,
		buttons : '#edituser-buttons'
	});
	
	$('#editftpmapping-window').dialog({
		title : '修改FTP映射关系',
		width : 420,
		height : 250,
		closed : false,
		modal : true,
		closed : true,
		draggable : false,
		buttons : '#edituser-buttons'
	});
	
	var p = $('#ftpmapping-table').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,//每页显示的记录条数，默认为10  
		pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
		beforePageText : '第',//页数文本框前显示的汉字  
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
});	
	//打开保存页面
	function addFtpMapping() {
		$("#newftpmapping-window").window('open');
		var ftpusername = $("#saveinnerusername").val();
		$("#newftpmapping-window").find("form").form('clear');
		$("#saveinnerusername").val(ftpusername);
		$("#newinnerAddress").val("/");
		$("#newouterAddress").val("/");
		$("#transFlag").combobox("setValue","1");
		$("#newftpmapping-window").find('form').url=pageContext +'/ftp/saveFtpMapping';
	}
	//打开编辑页面
	function editFtpMapping() {
		var row = $('#ftpmapping-table').datagrid('getSelected');
		if (row) {
			$('#editftpmapping-window').window('open');
			var form = $('#editftpmapping-window').find('form');
			form.form('clear');
			form.form('load',pageContext+'/ftp/findFtpMappingInfo?id='+row.id);
			form.url = pageContext+'/ftp/updateFtpUserMapping?id='+row.id;
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择FTP用户。'
			});
		}
	}
	//弹出删除
	function delFtpMapping() {
		var selected = $('#ftpmapping-table').datagrid('getSelected');
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
												url : pageContext+"/ftp/deleteFtpMapping",
												async: false,
												data : "id=" + id,
												success : function callback(
														data) {
													data = eval('('+ data + ')');
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
													$('#ftpmapping-table').datagrid('reload');
												}
											});
								}
							});
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择用户。'
			});
		}
	}
	//提交新增方法
	function save() {
		var isValidate = $('#newftpmapping-window').find('form').form('validate');
		if (isValidate) {
			$('#newftpmapping-window').find('form').form('submit', {
				url : pageContext+'/ftp/saveFtpMapping',
				success : function(data) {
					var obj = eval('(' +data+')');
					if(obj.dealFlag) {
						$.messager.show({
							title:'提示',
							msg:obj.msg
						});
					}else{
						$.messager.alert('错误',obj.msg,'error');
					}
					$("#ftpmapping-table").datagrid("reload");
					$("#newftpmapping-window").window("close");
				}
			});
		}
	}
	
	
	function update() {
		$('#editftpmapping-window').find('form').form('submit', {
			url : pageContext+'/ftp/updateFtpUserMapping',
			success : function(data) {
				var obj = eval('(' +data+')');
				if (obj.dealFlag) {
					$('#ftpmapping-table').datagrid('reload');
					$('#editftpmapping-window').window('close');
					$.messager.show({
						title : '提示',
						msg :obj.msg

					});
				} else {
					{
						$.messager.show({
							title : '提示',
							msg : obj.msg,
							showType : 'slide',
							timeout : 2000
						});
					}
				}
			}
		});
	}
	
	function closeSaveWindow() {
		$("#newftpmapping-window").window('close');
	}
	//关闭window方法
	function closeEditWindow() {
		$("#editftpmapping-window").window('close');
	}
	//右键菜单
	function onRowContextMenu(e,rowIndex,rowData) {
		e.preventDefault();
		$('#ftpmapping-table').datagrid('selectRow',rowIndex);
		$("#mm").menu('show',{
			left:e.pageX,
			top:e.pageY
		});
	}
	//详情
	function view() {
		var row = $('#ftpmapping-table').datagrid('getSelected');
		if (row !=null) {
			$("#detail-window").show();
			var win_detail = $('#detail-window').dialog({
				title : 'FTP映射详细信息',
				width : 420,
				height : 200,
				modal : true,
				closed : true,
				resizable : false,
				draggable : false
			});
			var form_detail = win_detail.find('form');
			win_detail.window('open');
			form_detail.form('load',{
				innerUserName:row.innerUserName,
				outerUserName:row.outerUserName,
				innerAddress:row.innerAddress,
				outerAddress:row.outerAddress,
				userFlag:row.userFlag
			});
			//修改input元素的显示样式
			$('#detail_table').find('input').css({
				'border' : '0',
				'width' : '200'
			});
		}
	}
