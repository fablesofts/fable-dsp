<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache"> 
<title>FTP用户管理</title>
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
var wordWidth = '14';
function setWidth()
{
	obj = event.srcElement;
	obj.style.width = obj.value.replace(/[^\x00-\xff]/g,"**").length*wordWidth/2+5;
}
</script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/ftp/ftpmanager.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/easyui.extend.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/commonstyle.easyui.css"></link>
</head>
<body>
	<!-- 测试启动按钮 -->
	<input id="pageContext" type="hidden" value="${pageContext.request.contextPath}"/>
	<input id="ftp-status" type="hidden"/>
	<input id="outerFtp-status" type="hidden"/>
	<!-- 测试老版本 -->
	<div id="tb" style="padding: 5px;height: auto">
	 <div style="margin-bottom:5px">
            <a id="addFtp" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addFtpUser()">新增</a>
            <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editFtpUser()">编辑</a>
            <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
            <a id="runFtp" class="easyui-linkbutton" iconCls="icon-runFtpServer" plain="true" onclick="runFtpServer()">&nbsp;</a>
            <a id="runOuterFtp" class="easyui-linkbutton" iconCls="icon-runFtpServer" plain="true" onclick="runOuterFtp()">&nbsp;</a>
            <a id="editPwd" class="easyui-linkbutton" data-options="iconCls:'oper_edit_password'" plain="true" onclick="updatepd()">修改密码</a>
       </div>
	</div>
	<!-- end .location -->
	<table id="ftp-table" class="easyui-datagrid" data-options="toolbar:'#tb'"></table>
	<!-- 新增用户window  -->
	<div id="newftpuser-window" class="easyui-dialog" title="新增FTP用户" data-options="closed:true">
		<div class="padding-10">
			<form method="post">
				<table>
					<tr>
						<td class="align-r">用户名：</td>
						<td>
							<input  id="newusername" class="easyui-validatebox white" name="ftpUsername" data-options="required:true,validType:'doesExistFtp'" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">密码：</td>
						<td>
							<input  id="newpwd" class="white" name="ftpPassword" type="password"/>&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">确认密码：</td>
						<td>
							<input id="renewpwd"  class="white" name="reftpPassword" type="password" validType="equals['#newpwd']"/>&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">用户目录：</td>
						<td>
							<input id="homeDirectory"  class="white" name="homeDirectory"/>&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
	</div>
	<div id="dlg-buttons" class="btn">
		<a id="btn-save" icon="icon-save" onclick="save()">保存</a>
		<a id="btn-cancel" icon="icon-cancel" onclick="closeSaveWindow()">取消</a>
	</div>
	<!-- 修改的div -->
	<div id="editftpuser-window" title="修改用户" class="easyui-dialog" data-options="closed:true">
		<div class="padding-10">
			<form method="post">
				<table>
					<tr>
						<td class="align-r">用户名：</td>
						<td>
							<input id="editusername" name="ftpUsername" class="white" readonly="readonly"/>&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">目录：</td>
						<td>
							<input id="edithomedirectory" name="homeDirectory" class="white" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="edituser-buttons" class="btn">
		<a id="btn-save" icon="icon-save" onclick="update()">保存</a> 
		<a id="btn-cancel" icon="icon-cancel" onclick="closeEditWindow()">取消</a>
	</div>
		<!-- 修改密码div -->
	<!-- class="easyui-window"  -->
	<div id="updatepd-window" title="修改密码" class="easyui-dialog" data-options="closed:true">
		<div class="padding-15">
			<form method="post">
				<table>
					<tr>
						<td>用户名：</td>
						<td><input id="usernameforup" name="usernameforup"
							class="white"  readonly="readonly"></input></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input id="editnewpwd" class="white" name="newpassword"
							type="password">&nbsp;<font color="red">*</font></td>
					</tr>
					<tr>
						<td>密码确认：</td>
						<td><input id="renewpwdforup" class="white" type="password"
							validType="equals['#editnewpwd']">&nbsp;<font color="red">*</font></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="updatepd-buttons" class="btn">
		<a href="javascript:void(0)" onclick="uppd()" id="btn-save"
			icon="icon-save">保存</a> <a href="javascript:void(0)"
			onclick="closeWindowforup()" id="btn-cancel" icon="icon-cancel">取消</a>
	</div>
	<!-- 详细信息 -->
	<div id ="detail-window" title="FTP用户信息详情页" style="display: none" 
	data-options="draggable:false,maximizable:false,minimizable:false,collapsible:false">
		<div style="padding: 30px 10px 10px 10px;"> 
			<form onkeydown="if(event.keyCode==8)return false;">
				<table id="detail_table" cellspacing="5" cellpadding="5"  style="width:400px">
					<tr>
						<td style="text-align: right; width: 40%"> FTP用户名：</td>
						<td>
							<input id="detail_ftpUserName" name="ftpUserName" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right; width: 60%">FTP用户目录 :</td>
						<td>
							<input id="detail_ftphomepath" name="ftpHomepath" style="width: 250px" readonly="readonly" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 右键菜单  -->
		<div id="mm" class="easyui-menu" style="width: 120px;">
		<div onClick="view()" data-options="iconCls:'icon-search'">详细信息</div>
		<div class="menu-sep"></div>
		<div onClick="addFtpUser()" data-options="iconCls:'oper_add'">新增</div>
		<div onClick="editFtpUser()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onClick="delFtpUser()" data-options="iconCls:'oper_remove'">删除</div>
	</div>
</body>
</html>