<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
  <head>
  <title>网络配置</title>
  <style type="text/css">


.table_css {
border-right: 1px solid #C1DAD7;
border-bottom: 1px solid #C1DAD7;
background: #fff;
font-size:11px;
padding: 6px 6px 6px 12px;

}




/*---------for IE 5.x bug*/
html>body td{ font-size:11px;}
body,td,th {

font-size: 16px;
}
</style>
  <script type="text/javascript">
  $(function() {
  		showNetwork();
		$('#btn-save,#btn-cancel').linkbutton();
		win = $('#source-window').window({
			closed : true
		});
		
		$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath}/networkCfg/listNetWork',
		data:'pageType="aaaaaaaaa"',
		success:function(result){
			$('#form_inner').form('load',result.net_inner_config);
			$('#form_outer').form('load',result.net_outer_config);
			$('#form_gap').form('load',result.net_gap_config);
			$('#cc').combobox('setValue',result.inn_card);
			$('#dd').combobox('setValue',result.out_card);
			$('#ee').combobox('setValue',result.gap_card);
			$('#ff').combobox('setValue',result.outer_card);
			removeLoad();
		}
		});
		
		$('#inner_save').attr("disabled","disabled");
		$('#outer_save').attr("disabled","disabled");
	});
	
	
  	var grid;
	var win;
	var form;
	
	//双击事件
	function editConfigNetwork(form) {
		if(form.id==1){
			$('#net_name').removeAttr("readonly");
			$('#to_gap_ip').removeAttr("readonly");
			$('#to_gap_mask').removeAttr("readonly");
			$('#to_gap_gateway').removeAttr("readonly");
			$('#to_innernet_ip').removeAttr("readonly");
			$('#to_innernet_mask').removeAttr("readonly");
			$('#to_innernet_gateway').removeAttr("readonly");
			$('#cc').removeAttr("disabled");
			$('#dd').removeAttr("disabled");
			$('#inner_save').removeAttr("disabled");
		}else if(form.id==2){
			$('#gap_name').removeAttr("readonly");
			$('#inner_ip').removeAttr("readonly");
			$('#outer_ip').removeAttr("readonly");
			$('#gap_save').removeAttr("disabled");
		}else if(form.id==3){
			$('#net_name_out').removeAttr("readonly");
			$('#to_gap_ip_out').removeAttr("readonly");
			$('#to_gap_mask_out').removeAttr("readonly");
			$('#to_outernet_ip').removeAttr("readonly");
			$('#to_outernet_mask').removeAttr("readonly");
			$('#to_outernet_gateway').removeAttr("readonly");2
			$('#outer_save').removeAttr("disabled");
		}
		//win.window('open');
		//form.form('clear');
		//form.url = '${pageContext.request.contextPath}/user/saveDataSource';
	}
	
	//保存内交换数据
	function saveNetWorkInner() {
		Load();
		$('#form_inner').form('submit', {
			url : '${pageContext.request.contextPath}/networkCfg/updateNetwork',
			success : function(data) {
				eval('data=' + data);
				if (data.dealFlag) {
					removeLoad();
					$('#inner_save').attr("disabled","disabled");
					$.messager.show({
						title : '提示',
						msg : data.msg
					});
				} else {
					$.messager.alert('错误', data.msg, 'error');
					removeLoad();
				}
			}
		});
	}
	
	//保存网闸数据
	function saveNetWorkGap() {
		$('#form_gap').form('submit', {
			url : '${pageContext.request.contextPath}/networkCfg/updateGapNetwork',
			success : function(data) {
				eval('data=' + data);
				if (data.dealFlag) {
					$('#gap_save').attr("disabled","disabled");
					$('#gap_name').attr("readonly","readonly");
					$('#inner_ip').attr("readonly","readonly");
					$('#outer_ip').attr("readonly","readonly");
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
	
	//保存外交换数据
	function saveNetWorkOut() {
		Load();
		$('#form_outer').form('submit', {
			url : '${pageContext.request.contextPath}/networkCfg/updateNetworkOut',
			success : function(data) {
				eval('data=' + data);
				if (data.dealFlag) {
				removeLoad();
				$('#outer_save').attr("disabled","disabled");
					$.messager.show({
						title : '提示',
						msg : data.msg
					});
				} else {
					$.messager.alert('错误', data.msg, 'error');
					removeLoad();
				}
			}
		});
	}
	
	//调用IP及时生效
	function innerIpVaild(){
		$('#form_inner').form('submit', {
			url : '${pageContext.request.contextPath}/networkCfg/getInneripValid',
			success : function(data) {
				eval('data=' + data);
				if (data.dealFlag) {
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
	
	function Load() {  
    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在配置网卡，请稍候...").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
	}  
	
	function showNetwork() {  
    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在获取网卡信息，请稍候...").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
	}  
	
	
	//hidden Load  
	function removeLoad() {  
	    $(".datagrid-mask").remove();  
	    $(".datagrid-mask-msg").remove();  
	}
	
  </script>
  </head>
  
  <body>
  		
		<table>
			<tr>
				<td>
					<form id="form_inner" name="form_inner" method="post">
					<table class="table_css" ondblclick="editConfigNetwork(this)" id="1">
			    		<tr>
			    			<td>机器名</td>
			    			<td>
			    				<input type="text" id="net_name" name="net_name" value="内交换" readonly="readonly" />
			    				<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>内交换IP（内口）</td>
			    			<td>
			    				<input type="text" id="to_innernet_ip" name="to_innernet_ip" width="12" readonly="readonly" />
			    				<input id="cc" name="now_inn_card" class="easyui-combobox" style="width: 120px;" data-options="valueField:'cardname',textField:'cardname',url:'${pageContext.request.contextPath}/user/showNetCard'" />
			    				<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>子网掩码（内口）</td>
			    			<td>
			    			<input type="text" id="to_innernet_mask" name="to_innernet_mask" readonly="readonly" />
			    			<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>内交换网关（内口）</td>
			    			<td><input type="text" id="to_innernet_gateway" name="to_innernet_gateway" readonly="readonly" /></td>
			    		</tr>
			    		<tr>
			    			<td>内交换IP（外口）</td>
			    			<td>
			    			<input type="text" id="to_gap_ip" name="to_gap_ip" id="ip" readonly="readonly" />
			    			<input id="dd" name="now_out_card"  class="easyui-combobox" style="width: 120px;" data-options="valueField:'cardname',textField:'cardname',url:'${pageContext.request.contextPath}/user/showNetCard'" />
			    			<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>子网掩码（外口）</td>
			    			<td><input type="text" id="to_gap_mask" name="to_gap_mask" readonly="readonly" />
			    			<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>内交换网关（外口）</td>
			    			<td><input type="text" id="to_gap_gateway" name="to_gap_gateway" readonly="readonly" /></td>
			    		</tr>
			    		<tr>
			    			<td colspan="2" align="center"><input type="button" id="inner_save" value="确定" onclick="saveNetWorkInner()" disabled="disabled"  /></td>
			    		</tr>
		    		 </table>
		    		<input type="hidden" id="id1" name="id" />
		    		</form>
				</td>
				<td>
					<form id="form_gap" name="form_gap" method="post">
					<table class="table_css" ondblclick="editConfigNetwork(this)" id="2">
			    		<tr>
			    			<td>机器名</td>
			    			<td>
			    				<input type="text" id="gap_name" name="gap_name" value="网闸" readonly="readonly" />
			    				<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>网闸IP（内口）</td>
			    			<td>
			    				<input type="text" id="inner_ip" name="inner_ip" readonly="readonly" />
			    				<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>网闸IP（外口）</td>
			    			<td>
			    			<input type="text" id="outer_ip" name="outer_ip" readonly="readonly" />
			    			<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td colspan="2" align="center"><input type="button" id="gap_save" value="确定" onclick="saveNetWorkGap()" disabled="disabled"  /></td>
			    		</tr>
		    		 </table>
		    		<input type="hidden" id="id2" name="id" />
		    		</form>
				</td>
				<td>
					<form id="form_outer" name="form_outer" method="post">
					<table class="table_css" ondblclick="editConfigNetwork(this)" id="3">
			    		<tr>
			    			<td>机器名</td>
			    			<td>
			    				<input type="text" id="net_name_out" name="net_name" value="外交换" readonly="readonly" />
			    				<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>外交换IP（内口）</td>
			    			<td>
			    				<input type="text" id="to_gap_ip_out" name="to_gap_ip" readonly="readonly" />
			    				<input id="ee" name="now_gap_card" class="easyui-combobox" style="width: 120px;" data-options="valueField:'cardname',textField:'cardname',url:'${pageContext.request.contextPath}/user/showOutNetCard'" />
			    				<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>子网掩码（内口）</td>
			    			<td><input type="text" id="to_gap_mask_out" name="to_gap_mask" readonly="readonly" />
			    			<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>外交换网关（内口）</td>
			    			<td><input type="text" id="to_gap_gateway" name="to_gap_gateway" readonly="readonly" /></td>
			    		</tr>
			    		<tr>
			    			<td>外交换IP（外口）</td>
			    			<td><input type="text" id="to_outernet_ip" name="to_outernet_ip" id="ip" readonly="readonly" />
			    				<input id="ff" name="now_outer_card" class="easyui-combobox" style="width: 120px;" data-options="valueField:'cardname',textField:'cardname',url:'${pageContext.request.contextPath}/user/showOutNetCard'" />
			    				<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>子网掩码（外口）</td>
			    			<td><input type="text" id="to_outernet_mask" name="to_outernet_mask" readonly="readonly" />
			    			<font color="red"><b>*</b></font>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>外交换网关（外口）</td>
			    			<td><input type="text" id="to_outernet_gateway" name="to_outernet_gateway" readonly="readonly" /></td>
			    		</tr>
			    		<tr>
			    			<td colspan="2" align="center"><input type="button" id="outer_save" value="确定" onclick="saveNetWorkOut()" disabled="disabled"  /></td>
			    		</tr>
		    		 </table>
		    		<input type="hidden" id="id3" name="id" />
		    		</form>
				</td>
			</tr>
		</table>
  </body>
</html>
