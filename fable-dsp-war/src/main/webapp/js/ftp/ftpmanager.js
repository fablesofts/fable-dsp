var pageContext;
$(function() {
	//重写
	//重写easyui 验证方法 新增密码确认认证
		pageContext=$("#pageContext").val();
		$('#newusername').validatebox({
			required : true,
			missingMessage : '请输入FTP用户名!'
		});
		$('#newpwd').validatebox({
			required : true,
			missingMessage : '请输入密码!'
		});
		$('#renewpwd').validatebox({
			required : true,
			missingMessage : '请二次输入确认密码!'
		});
		$('#editusername').validatebox({
			required : true,
			missingMessage : '请输入FTP用户名!'
		});
		$('#editnewpwd').validatebox({
			required : true,
			missingMessage : '请输入密码!'
		});
		$('#renewpwdforup').validatebox({
			required : true,
			missingMessage : '请二次输入确认密码!'
		});
		
		$('#updatepd-window').dialog({
			title : '修改密码',
			width : 420,
			height : 200,
			closed : false,
			modal : true,
			closed : true,
			draggable : false,
			buttons : '#updatepd-buttons'
		});
	   $('#editftpuser-window').dialog({
			title : '修改用户',
			width : 420,
			height : 200,
			closed : false,
			modal : true,
			closed : true,
			draggable : false,
			buttons : '#edituser-buttons'
		});
		//初始化展示页grid数据
		$('#ftp-table').datagrid(
				{
					url : pageContext+'/ftpservice/findFtpUsersInfoList',
					singleSelect : true,
					border:0,
					fit : true,
					fitColumns : true,
					autoRowHeight : true,
					loadMsg : '用户资料正在加载,请稍后 !',
					striped : true,
					pagination : true,
					rownumbers : true,
					resizeHandle : 'both',
					pagePosition : 'bottom',
					onRowContextMenu : onRowContextMenu,
					scrollbarSize : 10,
					frozenColumns : [ [ {
						field : 'id',
						hidden : true
					}] ],
					columns : [[{
						field : 'ftpUsername',
						title : '用户名',
						width : $(this).width()*0.5,
						align : 'center'
						},
						{
							field : 'homedirectory',
							title : '对应目录',
							width : $(this).width()*0.5,
							align : 'center'
						}
					]]
		});
	   	//判断状态
		$.ajax({
			url: pageContext+"/ftpservice/getFtpServerStatus",
			cache: false,
			type: "POST",
			async: false,
			success: function(data) {
				$("#ftp-status").val(data.toString());
				$("#runFtp").find('span').find('span').html(data.toString()=="0"?"停止内交换FTP服务":"启动内交换FTP服务");
			}
		});
		$.ajax({
			url: pageContext+"/ftpservice/getOuterFtpServerStatus",
			cache: false,
			type: "POST",
			async:false,
			success:function(data) {
				$("#outerFtp-status").val(data.toString());
				$("#runOuterFtp").find('span').find('span').html(data.toString()=="0"?"停止外交换FTP服务":"启动外交换FTP服务");
			}
		});
		
		//初始化按钮
		$('#btn-save,#btn-cancel').linkbutton();
		$('#newftpuser-window').dialog({
			width : 420,
			height : 250,
			closed : false,
			modal : true,
			closed : true,
			draggable : false,
			buttons : '#dlg-buttons'
		});

		$('#editftpuser-window').dialog({
			title : '修改FTP用户',
			width : 420,
			height : 250,
			closed : false,
			modal : true,
			closed : true,
			draggable : false,
			buttons : '#edituser-buttons'
		});
		//初始化form
		//设置分页控件  
		var p = $('#ftp-table').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	});
	//关闭修改密码窗体
	function closeWindowforup() {
		$("#updatepd-window").window('close');
	}
	//打开新增窗口 清空form内容 设置form提交的url
	function addFtpUser() {
		updateFtpFlag = false; 
		$('#newftpuser-window').window('open');
		$('#newftpuser-window').find("form").form('clear');
		$('#newftpuser-window').find("form").url = pageContext+'/ftp/saveFtpUser';
	}
	//打开修改窗口给form加载对应的会写数据内容 设置form提交的url
	function editFtpUser() {
		var row = $('#ftp-table').datagrid('getSelected');
		if (row) {
			$('#editftpuser-window').window('open');
			var form = $('#editftpuser-window').find('form');
			form.form('clear');
			form.form('load',pageContext+'/ftpservice/findFtpUserInfo?ftpUsername='+ row.ftpUsername);
			form.url = pageContext+'/ftpservice/editFtpUser?ftpUsername='
			+ row.ftpUsername;
		} else {
			$.messager.show({
				title : '警告',
				msg : '请先选择FTP用户。'
			});
		}
	}
	//详情页
	function view() {
		var row = $('#ftp-table').datagrid('getSelected');
		if (row != null) {
			$("#detail-window").show();
			var win_detail = $('#detail-window').dialog({
				title : 'FTP用户信息详情页',
				width : 420,
				height :200,
				modal : true,
				closed : true,
				resizable : false,
				draggable : false
			});
		}
		var form_detail = win_detail.find('form');
		win_detail.window('open');
		form_detail.form('load',{
			ftpUserName :row.ftpUsername,
			ftpHomepath :row.homedirectory
		});
		//修改input元素的显示样式
		$('#detail_table').find('input').css({
			'border' : '0',
		});
	}
	//提交新增方法
	function save() {
		var isValidate = $('#newftpuser-window').find('form').form('validate');
		if (isValidate) {
			$('#newftpuser-window').find('form').form('submit', {
				url : pageContext + '/ftpservice/saveFtpUser',
				success : function(data) {
					var obj = eval('(' + data + ')');
					if (obj.dealFlag) {
						$.messager.show({
							title : '提示',
							msg : obj.msg
						});
					} else {
						$.messager.show({
							title : '警告',
							msg : data.msg
						});
					}
					$("#ftp-table").datagrid("reload");
					$("#newftpuser-window").window("close");
				}
			});
		}
	}
	
	//提交修改方法
	function update() {
		$('#editftpuser-window').find('form').form('submit', {
			url : pageContext+'/ftpservice/editFtpUser',
			success : function(data) {
				var obj = eval('(' +data+')');
				if (obj.dealFlag) {
					$('#ftp-table').datagrid('reload');
					$('#editftpuser-window').window('close');
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
	//启动FTP服务
	function runFtpServer() {
		var status = $("#ftp-status").val();
		var url = pageContext+"/ftpservice/suspendFtpServer";
		var change = '2';
		if ('1' == status) {	//STATUS_STOPED 
			url = pageContext+"/ftpservice/runFtpServer";
			change = '0';
		} else if('2' == status) {
			url = pageContext+"/ftpservice/resumeFtpServer";
			change = '0';
		}
		$.ajax({
			url: url,
			cache: false,
			type: "POST",
			success: function(data) {
				eval('data=' + data);
				if(data.dealFlag) {
					$("#ftp-status").val(change);
					$("#runftp").val(change=="0"?"停止FTP服务":"启动FTP服务");
					$("#runFtp").find('span').find('span').html(change=="0"?"停止内交换FTP服务":"启动内交换FTP服务");
				}else{
					 $.messager.show({
                         title : '警告',
                         msg : data.msg
                     });
				}
			}
		});
	}
	//启动外交换服务
	function runOuterFtp() {
		var outerStatus = $("#outerFtp-status").val();
		var url = pageContext+"/ftpservice/suspendOuterFtpServer";
		var change = "2";
		if("1"== outerStatus) {
			url = pageContext+"/ftpservice/runOuterFtpServer";
			change = "0";
		}else if("2" == outerStatus) {
			url = pageContext+"/ftpservice/resumeOuterFtpServer";
			change = "0";
		}
		$.ajax({
			url: url,
			cache: false,
			type: "POST",
			async: false,
			success: function(data) {
				eval('data=' + data);
				if(data.dealFlag) {
					$("#outerFtp-status").val(change);
					$("#runOuterFtp").find('span').find('span').html(change=="0"?"停止外交换FTP服务":"启动外交换FTP服务");
				}else{
					$.messager.show({
						title:'提示',
						msg:data.msg
					});
				}
			}
		});
	}
	
	//提交删除
	function delFtpUser() {
		var selected = $('#ftp-table').datagrid('getSelected');
		if (selected) {
			$.messager
					.confirm(
							'警告',
							'确认删除么?',
							function(ftpUsername) {
								if (ftpUsername) {
									ftpUsername = selected.ftpUsername;
									$
											.ajax({
												type : "POST",
												url : pageContext+"/ftpservice/deleteFtpUser",
												data : "ftpUsername=" + ftpUsername,
												success : function callback(
														data) {
													eval('data=' + data);
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
													$('#ftp-table').datagrid('reload');
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
	function closeSaveWindow() {
		$("#newftpuser-window").window('close');
	}
	//关闭window方法
	function closeEditWindow() {
		$("#editftpuser-window").window('close');
	}
	//修改密码的方法
	function updatepd() {
		$("#updatepd-window").window('open');
		var formupdatepd = $("#updatepd-window").find('form');
		formupdatepd.form('clear');
		var selected = $('#ftp-table').datagrid('getSelected');
		document.getElementById("usernameforup").value = selected.ftpUsername;
		formupdatepd.url = pageContext+"/ftpservice/updatepasswordbyadmin";
	}
	//设置修改密码提交方法 显示返回消息
	function uppd() {
		var formupdatepd = $("#updatepd-window").find('form');
		formupdatepd.form('submit', {
			url : pageContext+"/ftpservice/updatepasswordbyadmin",
			success : function(data) {
				var obj = eval('(' +data+')');
				if (obj.dealFlag) {
					$("#updatepd-window").window('close');
					$("#editftpuser-window").window('close');
					$.messager.show({
						title : '提示',
						msg : obj.msg
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
	//右键菜单
	function onRowContextMenu(e,rowIndex,rowData) {
		e.preventDefault();
		$('#ftpmapping-table').datagrid('selectRow',rowIndex);
		$("#mm").menu('show',{
			left:e.pageX,
			top:e.pageY
		});
	}
