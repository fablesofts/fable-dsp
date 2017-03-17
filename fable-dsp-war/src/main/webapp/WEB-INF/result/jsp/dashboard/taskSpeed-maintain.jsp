<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<title>任务流速</title>
<!-- 引入与ECharts相关的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/esl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/echarts.js"></script>
<%@ include file="/commons/meta.jsp" %>
</head>
<body>
	<!-- 任务流速div，为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="flowSpeed"
		style="width: 390px; height: 250px;"></div>
	<!-- 子任务流速窗口 -->
	<div id="subTaskFlowSpeed-window">
		<table id="subTaskFlowSpeed-table"></table>
	</div>
	<script type="text/javascript">
		// 路径配置
		require.config({
			paths : {
				'echarts' : './js/echarts',
				'echarts/chart/line' : './js/echarts'
			}
		});
		// 使用
		require([ 
		          'echarts', 
		          'echarts/chart/line' // 使用柱形图就加载line模块，按需加载
		], function(echarts) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = echarts.init(document.getElementById('flowSpeed'));
			var option = {
				tooltip : {
					trigger : 'axis',
					formatter : "{a} <br/>时间：{b} <br/>流速：{c}kb/s"
				},
				grid : {
					x : 60,
					y : 30,
					x2: 35,
					y2: 50,
				},
				xAxis : [ {
					name : '时间',
					type : 'category',
					boundaryGap : false,
					data : (function() {
						var now = new Date();
						var res = [];
						var len = 6;
						while (len--) {
							res.unshift(now.toLocaleTimeString().replace(
									/^\D*/, ''));
							now = new Date(now - 5000);
						}
						return res;
					})()
				} ],
				yAxis : [ {
					name : '任务总流速(kb/s)',
					type : 'value',
					scale : true,
					precision : 2,
					splitArea : {
						show : true
					}
				} ],
				calculable : true,
				series : [ {
					name : '任务总流速',
					type : 'line',
					smooth : true,
					data : [ 0, 0, 0, 0, 0, getTaskSpeedData() ],
				} ]
			};
			// 从后台获取数据，并为echarts对象加载数据 
			myChart.setOption(option);
			//在折线图上绑定单击事件
			var ecConfig = require('echarts/config');
			myChart.on(ecConfig.EVENT.CLICK, taskSpeedHandler);

			timeTicket = setInterval(function() {
				var axisData = (new Date()).toLocaleTimeString().replace(
						/^\D*/, '');
				// 动态数据接口 addData
				myChart.addData([ [ 
				    0, // 系列索引
					getTaskSpeedData(), // 新增数据
					false, // 新增数据是否从队列头部插入
					false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					axisData // 坐标轴标签
				] ]);
			}, 5000);
		});

		//事件处理函数
		function taskSpeedHandler(param) {
			// type, seriesIndex, dataIndex
			if (typeof param.seriesIndex != "undefined") {
				if (param.type == "click") {
					$('#subTaskFlowSpeed-window').dialog('open');
					//查询当前子任务流速数据，并显示在数据表格中
					//获取options对象，设置url，然后重新加载数据
					var opts = $('#subTaskFlowSpeed-table').datagrid('options');
					opts.url = '${pageContext.request.contextPath}/dashboard/taskSpeed/findTaskSpeedList';
					$('#subTaskFlowSpeed-table').datagrid('reload');
				}
			}
		}

		/* 获取要显示的数据 */
		function getTaskSpeedData() {
			var taskTotalSpeed = 0;
			//从后台加载要显示的数据
			$.ajax({
				async : false,
				type : "post",
				url : '${pageContext.request.contextPath}/dashboard/taskSpeed/getTaskTotalSpeed',
				data : {},
				dataType : "json",
				success : function(results) {
				   taskTotalSpeed = results;
				}
			});
			return taskTotalSpeed; 	   
		}

		//声明显示任务流速的对话框
		$('#subTaskFlowSpeed-window').dialog({
			title : '任务流速列表',
			width : 600,
			height : 400,
			modal : true,
			closed : true,
			draggable : false
		});
		
		// 初始化显示任务流速的数据表格
		$('#subTaskFlowSpeed-table').datagrid({
			//title : '用户信息列表',
			border : false,
			singleSelect : true,
			fitColumns : true,
			autoRowHeight : true,
			loadMsg : '数据正在加载中,请稍后 !',
			striped : true,
			rownumbers : true,
			fit : true,
			resizeHandle : 'both',
			pagination : true,
			pagePosition : 'bottom',
			pageSize : 50,
			scrollbarSize : 10,
			frozenColumns : [ [ {
				field : 'id',
				hidden : true
			}, ] ],
			columns : [ [ {
				field : 'taskName',
				title : '任务名',
				width : 80,
				align : 'center'
			}, {
				field : 'loadRate',
				title : '流速(kb/s)',
				width : 80,
				align : 'center'
			} ] ]
		});

		//设置分页控件  
		var p = $('#subTaskFlowSpeed-table').datagrid('getPager');
		$(p).pagination({
			pageSize : 50,//每页显示的记录条数，默认为50  
			pageList : [ 50, 100, 200 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
	</script>
</body>
</html>