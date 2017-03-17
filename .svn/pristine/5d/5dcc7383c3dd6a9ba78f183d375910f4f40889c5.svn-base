<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 引入与ECharts相关的js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/esl.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts/echarts.js"></script>
<%@ include file="/commons/meta.jsp" %>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div style="width: 500px; height: 250px;">
		<div id="dsp-cpu-mem-div" style="width: 70%; height: 250px;float:left;">
		</div>
		<div id="dsp-disk-div" style="width: 30%; height: 250px;float:left;">
		</div>
	</div>
	<script type="text/javascript">
		var seriesData = [];//系列数据数组	
		// 路径配置
		require.config({
			paths : {
				'echarts' : './js/echarts',
				'echarts/chart/line' : './js/echarts',
				'echarts/chart/pie' : './js/echarts'
			}
		});
		// 使用
		require([ 
		          'echarts', 'echarts/chart/line', // 使用柱形图就加载line模块，按需加载
					'echarts/chart/pie' 
				], function(echarts) {
			// 基于准备好的dom，初始化echarts图表
			var dspCpuMemChart = echarts.init(document.getElementById('dsp-cpu-mem-div'));
			var dspDiskChart = echarts.init(document.getElementById('dsp-disk-div'));
			
			//cpu mem效率图
			var dspCpuMemOption = {
				title : {
					x : 'center',
				},
				tooltip : {
					trigger : 'axis',
					formatter : "时间：{b0}<br/>cpu使用率：{c0}%<br/>内存使用率：{c1}%",
					showDelay: 20,
				},
				grid : {
					x : 50,
					y : 40,
					x2: 30,
					y2: 50,
				},
				legend: {
					 orient : 'horizontal',
				     x : 'center',
				     y : 'top',
			        data:['cpu', '内存'],
			        padding : 20
			    },
				toolbox : {
					show : false,
					feature : {
						dataView : {
							show : true,
							readOnly : true
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				xAxis : [ {
					name : '时间',
					type : 'category',
					boundaryGap : false,
					data : (function() {
						var res = [];
						var len = 5;
						while (len--) {
							res.push('');
						}
						return res;
					})()
				} ],
				yAxis : [ {
					name : '使用率',
					type : 'value',
					scale : true,
					precision : 2,
					min : 0,
					max : 100,
					splitArea : {
						show : true
					}
				} ],
				series : [ 
					{
						name : 'cpu',
						type : 'line',
						itemStyle : {
							normal : {
								// areaStyle: {type: 'default'},
								lineStyle : {
									shadowColor : 'rgba(0,0,0,0.4)'
								}
							}
						},
						data : (function() {
							var res = [];
							var len = 5;
							while (len--) {
								res.push('');
							}
							return res;
						})()
					},
				    {
						name : '内存',
						type : 'line',
						itemStyle : {
							normal : {
								// areaStyle: {type: 'default'},
								lineStyle : {
									shadowColor : 'rgba(135,206,205,0.4)'
								}
							}
						},
						data : (function() {
							var res = [];
							var len = 5;
							while (len--) {
								res.push('');
							}
							return res;
						})()
					} 
				]
			};
			
			var dspDiskOption = {
					title : {
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c}%",
				        showDelay: 20, 
				    },
				    toolbox: {
				        show : false,
				        feature : {
				            mark : {show: false},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    series : [
				        {
				            name:'硬盘使用率',
				            type:'pie',
				            radius : '80%',
				            center: ['50%', '50%'],
				            data: seriesData
				        }
				    ]
			};
			dspCpuMemChart.setOption(dspCpuMemOption);
			dspDiskChart.setOption(dspDiskOption);

			timeTicket = setInterval(function() {
				seriesData = [];
				getDspDiskData();
				dspDiskOption = dspDiskChart.getOption();
				dspDiskOption.series[0].data = seriesData;
				dspDiskChart.setOption(dspDiskOption);
				dspDiskChart.refresh();
				
				var axisData = (new Date()).toLocaleTimeString().replace(
						/^\D*/, '');
				dspCpuMemChart.addData([ 
					[ 	
					  	0, // 系列索引       
						getDspCpuInfo(), // 新增数据
						false, // 新增数据是否从队列头部插入
						false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					] ,
					[ 
					  	1, 	// 系列索引
					    getDspMemInfo(), // 新增数据
						false, // 新增数据是否从队列头部插入
						false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						axisData // 坐标轴标签
					]
				]);
			}, 2000);
		});
		
		function getDspDiskData(){
			dspDiskRate = getDspDiskInfo();
			seriesData.push({value : dspDiskRate,name : '已使用'});
			seriesData.push({value : 100 - dspDiskRate,name : '空闲'});
		}
		
		function getDspCpuInfo(){
			 $.ajax({
		   			type : "post",
		        	url : '${pageContext.request.contextPath}/dashboard/dspinfo/findDspCpuInfo',
		        	success : function(result) {
		        		dspcpuinfo = result;
		   			}
		        });
			 return dspcpuinfo;
		}
		
		function getDspMemInfo(){
			 $.ajax({
		   			type : "post",
		        	url : '${pageContext.request.contextPath}/dashboard/dspinfo/findDspMemInfo',
		        	success : function(result) {
		        		dspmeminfo = result;
		   			}
		        });
			 return dspmeminfo;
		}
		
		function getDspDiskInfo(){
			 $.ajax({
		   			type : "post",
		        	url : '${pageContext.request.contextPath}/dashboard/dspinfo/findDspDiskInfo',
		        	success : function(result) {
		        		dspdiskinfo = result;
		   			}
		        });
			 return dspdiskinfo;
		}
	</script>
</body>
</html>