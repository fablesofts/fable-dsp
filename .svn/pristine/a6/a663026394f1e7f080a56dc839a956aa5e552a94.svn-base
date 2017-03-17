<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 引入与ECharts相关的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/esl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/echarts.js"></script>
</head>
<body >
	<!-- 显示任务流量的窗口 -->
	<div id="taskFlow-window" ></div>
	<div id="taskSpeed-window"></div>
	<div id="dspinfo-window"></div>
	<div id="outerinfo-window"></div>
	<div id="taskstatus-window"></div>
	<script type="text/javascript">		
		// 浏览器窗口的高度和宽度（不包括工具栏/滚动条）
		var w=window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
		var h=window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
// 		alert("width: "+w +", height: "+ h);
		//初始化显示任务流量的窗口
		$('#taskFlow-window').dialog({
			title : '任务流量',
			width : 440,
			height : 310,
			left : 1,
			top : 30,
			closable : false,
			draggable : false,
			modal : true,
			inline : true,
			href : '${pageContext.request.contextPath}/dashboard/taskFlow/maintain',
			extractor : function(data){
				var pattern = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
				var matches = pattern.exec(data);
				if (matches){
					return matches[1];	// only extract body content
				} else {
					return data;
				}
			}
		});
		//初始化数据流速窗口
		$('#taskSpeed-window').dialog({
			title : '任务总流速',
			width : 400,
			height : 310,
			left : 442,
			top : 30,
			closable : false,
			draggable : false,
			modal : true,
			inline : true,
			href : '${pageContext.request.contextPath}/dashboard/taskSpeed/maintain',
			extractor : function(data){
				var pattern = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
				var matches = pattern.exec(data);
				if (matches){
					return matches[1];	// only extract body content
				} else {
					return data;
				}
			}
		});
		
		//系统信息窗口
		$('#dspinfo-window').dialog({
			title : '内交换系统状态',
			width : 510,
			height : 310,
			left : 843,
			top : 30,
			closable : false,
			draggable : false,
			modal : true,
			inline : true,
			href : '${pageContext.request.contextPath}/dashboard/dspinfo/maintain',
			extractor : function(data){
				var pattern = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
				var matches = pattern.exec(data);
				if (matches){
					return matches[1];	// only extract body content
				} else {
					return data;
				}
			}
		});  
		//外交换系统信息窗口
		$('#outerinfo-window').dialog({
			title : '外交换系统状态',
			width : 510,
			height : 308,
			left : 843,
			top : 342,
			closable : false,
			draggable : false,
			modal : true,
			inline : true,
			href : '${pageContext.request.contextPath}/dashboard/outerinfo/maintain',
			extractor : function(data){
				var pattern = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
				var matches = pattern.exec(data);
				if (matches){
					return matches[1];	// only extract body content
				} else {
					return data;
				}
			}
		});
		//
			$('#taskstatus-window').dialog({
			title : '任务状态信息',
			width : 841,
			height : 308,
			left : 1,
			top : 342,
			closable : false,
			draggable : false,
			modal : true,
			inline : true,
			href : '${pageContext.request.contextPath}/dashboard/jobRunInfo/maintain',
			extractor : function(data){
				var pattern = /<body[^>]*>((.|[\n\r])*)<\/body>/im;
				var matches = pattern.exec(data);
				if (matches){
					return matches[1];	// only extract body content
				} else {
					return data;
				}
			}
		});
	</script>
</body>
</html>