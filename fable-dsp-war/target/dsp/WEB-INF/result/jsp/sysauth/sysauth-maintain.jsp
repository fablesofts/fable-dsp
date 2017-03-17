<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>授权管理页面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/css/commonstyle.easyui.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui.extend.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="tt" class="easyui-tabs"
		style="height: 200px; margin: 10px 10px;">
		<div title="系统特征文件" data-options="closable:false"
			style="overflow: auto; padding: 20px; display: yes;">
				<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;"> 
					特征文件:&nbsp;包含所使用设备的特征信息。 </span>
				</p>
				<div style="position:relative;width:340px; margin: 10px 0px">
				<input type="button" href="javascript:void(0)" style="background-color:#FFF; 
							border:1px solid #CDCDCD; color: #555555; height:22px; width:70px;" 
							onclick="downloadCharacterFile();" value="&nbsp;下&nbsp;载&nbsp;"/>
				</div>
		</div>
		<div title="系统授权文件" data-options="closable:false"
			style="overflow: auto; padding: 20px; display: yes;">
			<form id="genLicenceForm" method="post" enctype="multipart/form-data">
				<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;"> 授权文件:&nbsp;包含所使用设备的授权信息。</p>
				<div style="position:relative;width:340px; margin: 10px 0px">
					<input type='text' name='textfield' id='textfield' style='height:20px; 
						border:1px solid #cdcdcd; width:180px;' />  
 					<input type='button' style='background-color:#FFF; border:1px solid #CDCDCD;
 						color: #555555; height:22px; width:70px;' value='浏览...' />
    				<input type="file" name="fileField" id="fileField" style="position:absolute; 
    					top:0; right:80px; height:20px; filter:alpha(opacity:0);opacity: 0; width:260px" 
    					size="28" onchange="document.getElementById('textfield').value=this.value" />
    				<input type="button" href="javascript:void(0)" style="background-color:#FFF; 
						color: #555555; border:1px solid #CDCDCD;height:22px; width:70px;" 
						onclick="uploadLicenceFile();" value="&nbsp;上&nbsp;传&nbsp;"/>
				</div> 
			</form>
		</div>
	</div>

	<div style="height: 220px; margin: 10px 18px;" class="fableInfo">
		<ul>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">
				数据交换系统是一套支持多网域数据安全交换的产品，能实现数据在不同网络、
				系统、数据源之间自动、快速、安全的交换。产品由内网交换设备、外网交换
				设备、网闸三部分组成，使用J2EE技术开发，能安全的完成跨网域的各种数据
				交换要求。数据交换系统支持同网域、跨网域数据的实时传输和交换。支持同
				构数据源和异构数据源间的双向交换和内容的转换、清洗、合并、分发等操作。
				数据交换系统在保障全程安全的同时，充分考虑了数据交换业务的复杂性和需
				求不断变化的突出特点，提供基于工作流和功能组件模式的业务规则定制，允
				许用户在可视化界面中配置交换业务规则，达到迅速开展业务和最大限度减轻
				用户工作量的目的。
			</P>
			<p style="font-family: 微软雅黑; color: #444444; font-size: 24px;">
				系统特性
			</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">内网主控:
				所有业务流程都由内网发起，对外没有任何监听，没有可被攻击端口。
				外网源数据由内网发起抽取，外网目的数据由内网发起加载。保证交换系统安全。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">标准模板:
				提供多种标准配置模板，解决数据库同步和文件同步等常见业务。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">按需组合:
				实现对数据库到文件、文件到数据库、不同数据库、不同类型文件间的任意交换。
				实现对被交换数据的协议剥离、格式检查和内容过滤。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">病毒过滤:
				实现内存实时过滤病毒与木马。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">负载均衡:
				支持自负载均衡，无需外置负载均衡器，支持多组集群的负载均衡。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">优先级:
				支持优先级管理，确保高优先级的核心业务优先运行。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">分发合并:
				支持数据分发和合并。可实现一份源数据向多个要求各异的目标加载数据；
				将多个各异源数据织合成一份目的数据的功能。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">调度策略:
				实现多种业务调度策略：事件触发、时间触发、消息触发；全量、增量等方式。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">详细日志:
				实现详细的数据交换日志管理，可查询本系统的交换调度日志和交换数据行级日志。</p>
			<p style="font-family: 微软雅黑; color: #555555; font-size: 14px;">智能流控:
				实现自动数据流速控制，避免当交换两端数据流速不匹配时，发生大量数据积压。</p>
		</ul>
	</div>

	<script type="text/javascript">
		function downloadCharacterFile() {
			download_file("${pageContext.request.contextPath}/sysauth/bulidCharacter");
		}

		function download_file(url) {
			if (typeof (download_file.iframe) == "undefined") {
				var iframe = document.createElement("iframe");
				download_file.iframe = iframe;
				document.body.appendChild(download_file.iframe);
			}
			download_file.iframe.src = url;
			download_file.iframe.style.display = "none";
		}

		function uploadLicenceFile() {
			$("#genLicenceForm").form("submit",({
								type : "POST",
								url : "${pageContext.request.contextPath}/sysauth/uploadLicence",
								dataType : "json",
								success : function callback(data) {
									var dataObj = eval("(" + data + ")");
									if (!dataObj.dealFlag) {
										$.messager.show({
											title : '警告',
											msg : dataObj.msg
										});
									} else {
										$.messager.show({
											title : '提示',
											msg : '上传授权文件成功，系统会在3秒后跳转到登录页面...'
										});
										function jump(count) {
											window.setTimeout(function() {
																count--;
																if (count > 0) {
																	jump(count);
																} else {
																	location.href = "${pageContext.request.contextPath}/login.jsp"; 
																}
															}, 1000);
										}
										jump(3);
									}
								}
							}));
		}
	</script>
</body>
</html>