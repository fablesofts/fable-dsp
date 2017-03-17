<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>任务历史流量</title>
<!-- 引入与ECharts相关的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/esl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/echarts.js"></script>
<%@ include file="/commons/meta.jsp" %>
</head>
<body>
	<!-- 任务总流速div，为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="taskFlow"
		style="width: 430px; height: 255px;margin-left:5px;">
	</div>
	<div style="margin-left:5px;">
		历史总流量: <input id="totalTaskFlow" style="border:0;" readonly="readonly"/>
	</div>
	<!-- 任务历史流量窗口 -->
	<!-- <div id="taskHistoryFlow-window">
		<table id="taskHistoryFlow-table"></table>
	</div> -->
	<script type="text/javascript">
		var seriesData = [];//系列数据数组
		var xAxisData = [];//图例数据数组
		var totalTaskFlow = 0;//所有任务的总历史流量
		// 路径配置
		require.config({
			paths : {
				'echarts' : './js/echarts',
				'echarts/chart/bar' : './js/echarts',
			}
		});
		// 使用
		require([ 
		          'echarts', 
		          'echarts/chart/bar' // 使用柱形图就加载bar模块，按需加载
		], 
		function(echarts) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = echarts.init(document.getElementById('taskFlow'));
			var option = {
				tooltip : {
					trigger : 'axis',
					formatter : "{a} <br/>任务名：{b} <br/>历史总流量：{c}kb"
				},
				grid : {
					x : 80,
					y : 30,
					x2: 45,
					y2: 60,
				},
				dataZoom : {
					show : true,
					orient : 'vertical',
					realtime : true,
					start : 0,
					end : 100
				},
				xAxis : [ {
					name : '任务名',
					type : 'category',
					splitLine : {show : true},
					data : xAxisData,
	                axisLabel : {
	                	show : true,
	                	rotate : -60,
	                	margin : 5
	                },
				} ],
				yAxis : [ {
					name : '历史总流量(kb)',
					type : 'value',
					splitArea : {show : true}
				} ],
				calculable : true,
				series : [ {
					name : '任务历史总流量',
					type : 'bar',
					barWidth : 20,
      	            barGap : 10,
					smooth : true,
					data : seriesData
				} ]
			};
			// 从后台获取数据，并为echarts对象加载数据 
			getTaskFlowData();
			// 为echarts对象加载数据  
			myChart.setOption(option,true);
			//设置总流量数据
			$('#totalTaskFlow').val(totalTaskFlow+"kb");

			timeTicket = setInterval(function() {
				seriesData = [];//清空系列数据
				xAxisData = [];//
				totalTaskFlow = 0;//
				//从后台获取数据，并设置图例和系列数据
				getTaskFlowData();
				option = myChart.getOption();
				option.xAxis[0].data = xAxisData;
				option.series[0].data = seriesData;
				// 为echarts对象加载数据  
				myChart.setOption(option,true);
				myChart.refresh();
				//设置总流量数据
				$('#totalTaskFlow').val(totalTaskFlow+"kb");
			}, 3000);
		});
		
		/* 获取要显示的数据 */
		function getTaskFlowData() {
			//从后台加载要显示的数据  
			$.ajax({
						async : false,
						type : "post",
						url : '${pageContext.request.contextPath}/dashboard/taskFlow/findTaskFlowList',
						data : {},
						dataType : "json",
						success : function(results) {
							for (var i = 0; i < results.length; i++) {
								seriesData.push(results[i].loadRate);
								xAxisData.push(results[i].taskName);
								totalTaskFlow += results[i].loadRate;
							}
						}
					});
		}
	</script>
</body>
</html>