$(function() {
	var tableMappingInit = false;
	var columnMappings = {};
	var columnFilters = {};
	var columnVirusFilters = {};
	var columnTransfers = {};
	var current = {};
	var isEditSchedule = false;
	var pageContext;
	startTime();
	//初始化表映射关系
	function initTableMapping() {
		var sourceRows = $('#source-datasource-grid').datagrid('getRows');
		var source = sourceRows[0].name;
		var targetRows = $('#target-datasource-grid').datagrid('getRows');
		var sourceTables = sourceRows[0].tableName.split(",");
		var mapping = [];
		var index =0;
		for (var i=0; i< sourceTables.length; i++) {
			for (var j = 0; j < targetRows.length; j++) {
				var targetTables = targetRows[j].tableName.split(",");
				for (var k = 0; k < targetTables.length; k++) {
					if (sourceTables[i] == targetTables[k]) {
						mapping[index] = {sourceId: sourceRows[0].id,targetId: targetRows[j].id,id:source + "->" + targetRows[j].name, fromTable: sourceTables[i], toTable: targetTables[k]};
						index++;
						break;
					} else if (k == targetTables.length - 1) {
						mapping[index] = {id:source + "->" + targetRows[j].name, fromTable: sourceTables[i], toTable: ""};
						index++;
					}
				}
			}
		}
		$('#table-mapping-grid').datagrid("loadData", mapping);			
	}
	function loadColumnMappings() {
		
	}

	function loadColumnFilters() {
		
	}

	function loadColumnVirusFilters() {
		
	}

	function loadColumnTransfers() {
		
	}
});
//调度模块的时间钟
function startTime() {
	var curr_time = new Date();
	var year = curr_time.getFullYear();
	var month= curr_time.getMonth()+1;
	var day = curr_time.getDate();
    var h = curr_time.getHours();
    var m = curr_time.getMinutes();
    var s = curr_time.getSeconds();
    h = checkTime(h);
    m = checkTime(m);
    s = checkTime(s);
    var str1 = year + '/'+month + '/'+day;
    var str2 =  + h + ":" + m + ":" + s;
    $('#beginDate').datetimebox('setValue', str1 + str2);
}
//add a zero in front of numbers which<10 
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}
//立刻执行调度
function execute() {
    var selected = $("#task-table").datagrid('getSelected');
    if (selected) {
        selectedTask = selected.id;
        return true;
    } else {
        $.messager.show({
            title : '警告',
            msg : '请先选择任务。'
        });
        return false;
    }
}
/**
 * 运行
 */
function run() {
	
	pageContext=$("#pageContext").val();
	
    if (execute()) {
        $.messager
                .confirm(
                        '警告',
                        '确认要立刻执行么?',
                        function(id) {
                            if (id) {
                                id = selectedTask;
                                $
                                        .ajax({
                                        	type : "POST",
                                            async : false,
                                            url : pageContext+"/dataswitch/execute",
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
                                                    //可以调度
                                                    $.messager.show({
                                                        title : '提示',
                                                        msg : data.msg
                                                    });
                                                }
                                            }
                                        });
                                $("#task-table").datagrid('reload');
                            }
                        });
    }
}
//高级配置:新增
function newSchdule() {
	var selectedTask=getSelectedTask();
    if (selectedTask != -1) {
		//判断该任务是否有调度信息
		var flag = isExistsSchedule(selectedTask);
		if (flag) {
			//存在，回写数据
			querySchedule(selectedTask);
			isEditSchedule = true;
		} else {
			$("#schedul-model").val("0");
			$("#fm1").form('clear');
//			$('#beginDate').datetimebox('setValue',
//					new Date().format('yyyy-mm-dd hh:mm:ss'));
			startTime();
			$("input[type=radio][name='circleModel']")
					.attr("checked", 'byHour');
			$('#byHour').numberspinner('setValue', '1');
			$('#byMinute').numberspinner('setValue', '1');
			$('#bySecond').numberspinner('setValue', '1');
			$('#schedule-settings').window({
		        title : '新增调度信息'
		    });
			$('#schedule-settings').window('open');
			isEditSchedule = false;
		}
	}
}
//编辑指定任务下的调度信息
function querySchedule(selectedTask) {
	pageContext=$("#pageContext").val();
	var scheduleForm;
    //选择一项任务
    $
            .ajax({
                url : pageContext+"/dataswitch/queryScheduleByTask?id="
                        + selectedTask,
                cache : false,
                success : function(data) {
                	data  =eval('(' + data + ')');
                    if (data.runModel == "circle-execute") {
                    	scheduleForm = $("#schedule-settings").find("#fm1");
                        //周期模式
                    	$("#schedul-model").val("0");
                        //data是Object object
                        $("#fm1").show();
                        $("#fm2").hide();
                    } else if (data.runModel == "regular-execute") {
                    	//定期模式
                    	scheduleForm = $("#schedule-settings").find("#fm2");
                    	$("#schedul-model").val("1");
                    	//组件是否禁用
                    	if(data.model == "byweek") {
                    		 $("#hh1").attr('disabled','true');
                    	     $("#mmm1").combobox('disable');
                    	     $("#weekDay").removeAttr("disabled");
                    	     $("#hh2").removeAttr("disabled");
                    	     $("#mm2").combobox("enable");
                    	     $("#monthDay").attr('disabled','true');
                    	     $("#hh3").attr('disabled', 'true');
                    	     $("#mm3").combobox('disable');
                    	} else if(data.model == "bymonth") {
                    		$("#hh1").attr('disabled', 'true');
                            $("#mmm1").combobox('disable');
                            $("#weekDay").attr("disabled","disabled");
                            $("#hh2").attr('disabled','true');
                            $("#mm2").combobox('disable');
                            $("#monthDay").removeAttr("disabled");
                            $("#hh3").removeAttr("disabled");
                            $("#mm3").combobox("enable");
                    	} else if(data.model == "byday"){
                    		$("#hh1").removeAttr("disabled");
                            $("#mmm1").combobox("enable");	//下拉
                            $("#weekDay").attr("disabled","disabled");
                            $("#hh2").attr('disabled','true');
                            $("#mm2").combobox('disable');
                            $("#monthDay").attr('disabled','true');
                            $("#hh3").attr('disabled','true');
                            $("#mm3").combobox('disable');
                    	}
                        $("#fm2").show();
                        $("#fm1").hide();
                    }
                    scheduleForm.form('load', data);
                }
            });
    $('#schedule-settings').window({
        title : '编辑调度信息'
    });
    $('#schedule-settings').window('open');
    document.getElementById('schedule-settings').style.display = "block";

}
//判断该调度是否已经存在
function isExistsSchedule(selectedTask) {
	var flag=false;
	pageContext=$("#pageContext").val();
    $
            .ajax({
                type : "POST",
                cache : false,
                async : false,
                url :pageContext+"/dataswitch/isExistsSchedule?id="+selectedTask,
                success :function(data) {
                	data = eval('(' + data + ')');
                    if (data.dealFlag) {
                        flag=true;
                    } 
                }
            });
    return flag;
}
/**
 * 根据不同模式隐藏或显示相应表单
 */
function selectmodel() {
    var model = $("#schedul-model").val();
    if (model == "0") {
        $("#fm1").show();
        $("#fm2").hide();
    } else {
        $("#fm1").hide();
        $("#fm2").show();
      //按天
        $("#hh1").removeAttr("disabled");
        $("#mmm1").combobox("enable");	//下拉
        $("#mmm1").attr("value",'0');
        $("#weekDay").attr("disabled","disabled");
        $("#hh2").attr('disabled','true');
        $("#mm2").combobox('disable');

        $("#monthDay").attr('disabled','true');
        $("#hh3").attr('disabled','true');
        $("#mm3").combobox('disable');
    }
}
//切换选择的时候禁用相关的组件
function sel() {
    var item = $("input[type='radio'][name='model']:checked").val();
    if (item == "byday") {
    	//按天
        $("#hh1").removeAttr("disabled");
        $("#mmm1").combobox("enable");	//下拉
        $("#mmm1").attr("value",'0');
        $("#weekDay").attr("disabled","disabled");
        $("#hh2").attr('disabled','true');
        $("#mm2").combobox('disable');

        $("#monthDay").attr('disabled','true');
        $("#hh3").attr('disabled','true');
        $("#mm3").combobox('disable');
    } else if (item == "byweek") {
    	//按周
        $("#hh1").attr('disabled','true');
        $("#mmm1").combobox('disable');

        $("#weekDay").removeAttr("disabled");
        $("#weekDay").val("1");
        $("#hh2").removeAttr("disabled");
        $("#mm2").combobox("enable");
        $("#mm2").attr("value",'0');
        $("#monthDay").attr('disabled', 'true');
        $("#hh3").attr('disabled', 'true');
        $("#mm3").combobox('disable');
    } else {
        //bymonth
        $("#hh1").attr('disabled', 'true');
        $("#mmm1").combobox('disable');

        $("#weekDay").attr("disabled","disabled");
        $("#hh2").attr('disabled','true');
        $("#mm2").combobox('disable');

        $("#monthDay").removeAttr("disabled");
        $("#hh3").removeAttr("disabled");
        $("#mm3").combobox("enable");
        $("#mm3").attr("value",'0');
    }
}
/**
 * 新增调度信息
 */
function schedule() {
	var selectedTask=getSelectedTask();
	var model = $("#schedul-model").val();
	pageContext=$("#pageContext").val();
	var url;
	if(!isEditSchedule) {
		//新增
		url = pageContext+'/dataswitch/schedule';
	}else {
		url = pageContext+'/dataswitch/updateSchedule';
	}
	if (model == "0") {
		//周期模式
		scheduleForm = $("#schedule-settings").find("#fm1");
		scheduleForm.find("#runModel").val("circle-execute");
		scheduleForm.find("#taskId").val(selectedTask);
	        //
	    } else {
	    scheduleForm = $("#schedule-settings").find("#fm2");
	    scheduleForm.find("#runModel").val("regular-execute");
	    scheduleForm.find("#taskId").val(selectedTask);
	    }
	scheduleForm.form.url=url;
	var isExecuteimmediately=$("#executeimmediately").prop('checked');//是否立刻运行一次
	scheduleForm.find("#executeimmediately_hidden").val(isExecuteimmediately);
	//新增调度信息
	scheduleForm.form('submit', {
        url : url,
        success : function callback(data) {
        	data = eval('(' + data + ')');
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
            $('#schedule-settings').window('close');
            $("#task-table").datagrid('reload');
        }
    });
}
//关闭窗口
function reset() {
	$('#schedule-settings').window('close');
}
/**
 * 获取选中的任务编号
 * @returns
 */
function getSelectedTask(){
		var selected = $("#task-table").datagrid('getSelected');
		var selectedTask=-1;
	    if (selected) {
	        selectedTask = selected.id;
	    }else {
	        $.messager.show({
	            title : '警告',
	            msg : '请先选择任务'
	        });
	    }
	    return selectedTask;
}