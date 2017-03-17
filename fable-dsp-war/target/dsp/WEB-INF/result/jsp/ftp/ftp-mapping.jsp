<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FTP用户映射管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ftp/ftpmapping.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui.extend.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/commonstyle.easyui.css" />
</head>
<body>
	<input id="pageContext" type="hidden" value="${pageContext.request.contextPath}"/>
	<table id="ftpmapping-table" class="h-500"></table>
	<!-- 新增表单 -->
	<div id="newftpmapping-window" class="easyui-dialog" title="新增FTP映射路径" data-options="closed:true">
		<div class="padding-10">
			<form method="post">
				<table>
					<tr>
						<td class="align-r">内交换用户名：</td>
						<td>
							<input  id="saveinnerusername" class="white" name="innerUserName" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">外交换用户名：</td>
						<td>
							<input  id="saveouterusername" class="white" name="outerUserName" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">内交换路径：</td>
						<td>
							<input  id="newinnerAddress" class="white" name="innerAddress" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">外交换路径：</td>
						<td>
							<input id="newouterAddress"  class="white" name="outerAddress" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">传输方向：</td>
						<td>
						<select id="transFlag" style="width: 150px" class="easyui-combobox"  name="userFlag" 
						data-options="panelHeight:60,panelWidth:150,editable:false">
							<option value="1" selected="selected">由内到外</option>
							<option value="0">由外到内</option>
						</select>
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
	
	<!-- 修改 -->
		<!-- 修改的div -->
	<div id="editftpmapping-window" title="修改FTP映射关系" class="easyui-dialog" data-options="closed:true">
		<div class="padding-10">
			<form method="post">
				<table >
					<tr>
						<td class="align-r">用户名：</td>
						<td>
						<input id="editcreateUser" name="createUser" type="hidden"/>
							<input id="editftpid" name="id"  type="hidden"/>
							<input id="editftpusername" name="innerUserName" class="white" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">外交换用户名：</td>
						<td>
							<input  id="editouterusername" name="outerUserName" class="white" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">内交换路径：</td>
						<td>
							<input  id="editinnerAddress" class="white" name="innerAddress" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">外交换路径：</td>
						<td>
							<input id="editouterAddress"  class="white" name="outerAddress" />&nbsp;
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="align-r">传输方向：</td>
						<td>
						<select id="transFlag" style="width: 150px" class="easyui-combobox"  name="userFlag" 
						data-options="panelHeight:60,panelWidth:150,editable:false">
							<option value="1">由内到外</option>
							<option value="0">由外到内</option>
						</select>
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
		<!-- 右键菜单  -->
		<div id="mm" class="easyui-menu" style="width: 120px;">
		<div onClick="view()" data-options="iconCls:'icon-search'">详细信息</div>
		<div class="menu-sep"></div>
		<div onClick="addFtpMapping()" data-options="iconCls:'oper_add'">新增</div>
		<div onClick="editFtpMapping()" data-options="iconCls:'icon-edit'">编辑</div>
		<div onClick="delFtpMapping()" data-options="iconCls:'oper_remove'">删除</div>
	</div>
	<!-- 详细信息 -->
	<div id ="detail-window" title="FTP映射详细信息" style="display: none" 
	data-options="draggable:false,maximizable:false,minimizable:false,collapsible:false">
		<div style="padding: 10px 10px 10px 10px;"> 
			<form onkeydown="if(event.keyCode==8)return false;">
				<table id="detail_table"  cellpadding="5" style="width:400px;">
					<tr>
						<td style="text-align: right;width: 50%">内交换用户名：</td>
						<td style="width: 50%"><input id="detail_name" name="innerUserName" readonly="readonly" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">外交换用户名：</td>
						<td><input id="detail_source_type"  name="outerUserName"  readonly="readonly" /></td>
					</tr>
					<tr class="db_type_id">
						<td style="text-align: right;">内交换映射目录：</td>
						<td><input id="detail_db_type"  name="innerAddress"  readonly="readonly" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">外交换映射目录：</td>
						<td><input id="detail_server_ip" name="outerAddress" readonly="readonly" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>