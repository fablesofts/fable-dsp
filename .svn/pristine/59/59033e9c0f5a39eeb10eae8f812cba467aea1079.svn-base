$(function(){
			
			$("#current-source-ds-id").hide();
			$("#current-target-ds-id").hide();
			$("#current-source-tb-name").hide();
			$("#current-target-tb-name").hide();
			$("#current-mapping-name").hide();
			
			var pageContext=$("#pageContext").val();
			//1.任务列表初始化
			$('#task-table').datagrid(
	        {
	            url: pageContext+'/dataswitch/listTaskDto',
	            fitColumns: true,
	            autoRowHeight: false,
	            loadMsg: '任务信息正在加载,请稍后 !',
	            striped: true,
	            pagination: true,
	            rownumbers: true,
	            resizeHandle: 'both',
				fit : true,
				pagePosition : 'bottom',
	            scrollbarSize: 10,
	            frozenColumns: [[{
	                field : 'id',
	                hidden : true
	            }]],
	            onDblClickRow : function(rowIndex, rowData) {	//双击事件，查看详情页
	                $('#task-detail-grid').propertygrid({
	                    url:pageContext+'/dataswitch/getTaskDetail?id='+rowData.id
	                });
	                $("#task-detail-grid").datagrid("reload");
	                $("#task-detail").window('open');

	            },
	            onLoadError:function() {
	                $.messager.show({
	                    title : '警告',
	                    msg : '加载任务列表出现异常'
	                });
	            }
	        });
			
			//2.任务列表分页控件初始化
			$('#task-table').datagrid('getPager').pagination({
		        pageSize : 10,//每页显示的记录条数，默认为10
		        pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表
		        beforePageText : '第',//页数文本框前显示的汉字
		        afterPageText : '页    共 {pages} 页',
		        displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		    });
			
			//3.新任务弹框初始化
			$('#new-task').window({
		        modal : true,
		        closed : true,
		        shadow : false
		    });
			
			//初始化任务类型控件
			$('#task-type').combobox({
				validType:'selectValueRequired',
			    missingMessage:"请选择交换类型",
				editable:false,
		        panelHeight:'auto',
		        valueField: 'id',
		        textField: 'text',
		        data: taskType,
		        onChange:function(newValue,oldValue){//联动变换同步类型和文件grid
		        	changeTaskType(newValue);
		        }
			});
			
			//初始化原文件处理方式
			$('#source-File').combobox({
				validType:'selectValueRequired',
			    missingMessage:"请选择原文件处理方式",
				editable:false,
		        panelHeight:'auto',
		        valueField: 'id',
		        textField: 'text',
		        data: sourceFile
			});
			
			//初始化目标文件处理方式
			$('#target-File').combobox({
				validType:'selectValueRequired',
			    missingMessage:"请选择目标文件处理方式",
				editable:false,
		        panelHeight:'auto',
		        valueField: 'id',
		        textField: 'text',
		        data: targetFile
			});
			
			//调度
//			$("#schedul-model").combobox({
//				validType:'selectValueRequired',
//				missingMessage:"请选择调度模式",
//				editable:false,
//				panelHeight:'auto',
//				valueField: 'id',
//				textField: 'text',
//				data:schedulModel,
//				onChange:function(newValue,oldValue){
//				    selectmodel();
//				}
//			});
			$("#mmm1").combobox({
				 panelHeight:100,
			     valueField: 'id',
			     textField: 'text',
			     data:timeSchedul,
			     onLoadSuccess:function(){
			     	$(this).combobox('select','0');
			     }
			});
			$("#mm2").combobox({
				panelHeight:100,
		        valueField: 'id',
		        textField: 'text',
		        data:timeSchedul,
		        onLoadSuccess:function(){
		            $(this).combobox('select','0');
		        }
			});
			$("#mm3").combobox({
				panelHeight:100,
		        valueField: 'id',
		        textField: 'text',
		        data:timeSchedul,
		        onLoadSuccess:function(){
		            $(this).combobox('select','0');
		        }
			});
//			$("#weekDay").combobox({
//				panelHeight:'auto',
//				valueField: 'id',
//			    textField: 'text',
//			    data:weekDay,
//			    onLoadSuccess:function(){
//		            $(this).combobox('select','1');
//		        }
//			});
			
			
			$('#new-task').tabs({
				onSelect: function(title,index) {
					
					
					var type = $('#task-type').combobox('getValue');
					if (undefined == type || '' == type) {
						type = -1;
					}
					if (0==type) {//数据库--数据库
						$('#filter-file-div,#mapping-file-div,#transform-file-div').hide();
						$('#filter-column-div,#transform-column-div,#mapping-database-div').show();
						$('.notice-depends-on-notice').html("");
						if (!tableMappingInit) {
							initTableMapping();
							tableMappingInit = true;
						}
						var targetId = current.targetId;
						var sourceTableName =  current.sourceTableName;
						var targetTableName = current.targetTableName;
						var key = sourceTableName + '->' + targetTableName;
					} else if (1==type) {//文件---文件
						$('#filter-column-div,#transform-column-div,#mapping-database-div').hide();
						$('#filter-file-div,#mapping-file-div,#transform-file-div').show();
						$('.notice-depends-on-notice').html("");
					} else if (2==type) {//文件---数据库
						$('#transform-file-div,#mapping-file-div,#filter-file-div,#mapping-database-div,#transform-column-div,#filter-column-div').hide();
						$('.notice-depends-on-notice').html("<font color='red'>功能暂未支持！</font>");
					} else if (3==type) {//数据库--文件
						$('#transform-file-div,#mapping-file-div,#filter-file-div,#mapping-database-div,#transform-column-div,#filter-column-div').hide();
						$('.notice-depends-on-notice').html("<font color='red'>功能暂未支持！</font>");
					} else {
						$('#transform-file-div,#mapping-file-div,#filter-file-div,#mapping-database-div,#transform-column-div,#filter-column-div').hide();
						$('.notice-depends-on-notice').html("<font color='red'>请选择任务类型！</font>");
					}
				}	
			});
			
			//4.添加新增按钮click事件
			$('#new-task-btn').on('click', function(){
				tableMappingInit = false;
				updateFlag = false; 
				updatetimeFlag = false;
				$("#timestamp-div").hide();
				$("#file").hide();
				$("#file-syslog").hide();
				$("#file-description").hide();
				$('#totalForm').form('clear');
				$('#new-task').tabs("select",0);
				$('#source-datasource-grid').datagrid('loadData', {
			        total : 1,
			        rows : [ {
			            id:"",
			            name:"请选择数据源",
			            tableName:"请选择表名",
			            location:"请填写文件路径"
			        } ]
			    }); 
				$('#target-datasource-grid').datagrid('loadData', {
			        total : 1,
			        rows : [ {
			            id:"",
			            name:"请选择数据源",
			            tableName:"请选择表名",
			            location:"请填写文件路径"
			        } ]
			    });
				$('#source-id').val('');
				$('#target-id').val('');
				$('#new-task').window('open');
			});
			
			//5.初始化任务详细信息
			$('#task-detail').window({
				resizable:false,
				draggable:true,
				maximizable:false,
				minimizable:false,
				collapsible:false,
				modal:true
				,closed:true,title:'交换任务详细信息'
			});
			$('#task-detail-grid').propertygrid({
                showGroup: true,
                scrollbarSize:0,
                showHeader:false
            });
			
			
			
			
			//7.初始化同步类型控件
			$('#task-trans-type').combotree({
				validType:'selectValueRequired',
				missingMessage:"请选择同步类型",
				panelHeight:'auto',
		        valueField: 'id',
		        textField: 'text',
		        editable:false,
		        data: synchroType,
		        onSelect: function(rec){
		        	if("4"==rec.id){
		        		initTimeStamp();
		        		$("#timestamp-div").show();
		        	} else {
		        		$("#timestamp-div").hide();
		        	}
		        }	
			});
			
			//8.初始化保存任务按钮和取消任务按钮
			$('#btn-save,#btn-cancel,#btn-save1,#btn-cancel1').linkbutton();
			
			
			//9.初始化源交换实体信息
			$('#source-datasource-grid').datagrid({
				loadMsg: -1,//作为editIndex使用
				columns:[[{
					
							field: 'sourceId',
							hidden: true
						  },
				          {
				        	  field: 'id',
				        	  title: '数据源',
				        	  width: 275,
				        	  formatter: function(value, row, index) {
	                            	return row.name;
	                            },
	                          editor: {
	                            type: 'combobox',
	                            options: {
	                            	editable:false,
	                                valueField: 'id',
	                                textField: 'name',
	                                data: [{"id":"", "name":"请选择数据源"}],
	                                required: true,
	                                missingMessage:'请选择数据源!',
	                                url: pageContext + '/dataswitch/listDataSource?source_type=0',
	                                onSelect: function(record) {
	                                	var sourceRows = $('#source-datasource-grid').datagrid('getRows');
	                                	sourceRows[0].id = record.id;
	                                	$('#source-id').val(record.id);
	                                },
	                                onShowPanel: function() {
	                                	var ed = $("#source-datasource-grid").datagrid('getEditor', {
	                                        index : 0,
	                                        field : 'id'
	                                    });
	                                	var url = '';
	                                	if(0==$("#task-type").combobox('getValue')) {
	                                		url = pageContext + '/dataswitch/listDataSource?source_type=0';
	                                	}else if(1==$("#task-type").combobox('getValue')){
	                                		url = pageContext + '/dataswitch/listDataSource?source_type=1';
	                                	}
	                                	$(ed.target).combobox('clear');
	                                	$(ed.target).combobox('reload', url);
                              		}
	                            }
	                      	 }
	                      },
				          {
	                        	field: 'tableName',
	                        	title:'数据表',
	                        	width: 275,
	                        	hidden: false,
	                        	editor: {
	                        		type: 'combotree',
	                                options: {
	                                 	multiple:true,
	                                 	checkbox:true,
	                                 	editable:false,
	                                 	cascadeCheck:true,
	                                 	required:false,
	                                 	missingMessage:'请选择数据表',
	                                 	onShowPanel: function() {
	                                 		//var ed = $('#source-datasource-grid').datagrid('getEditor', {index: 0,field:'id'});
	                              			var et = $('#source-datasource-grid').datagrid('getEditor', {index: 0,field:'tableName'});
	                              			var id = $('#source-id').val();
	                              			var url= pageContext + '/dataswitch/getSourceById?id='+id;
	                              			$(et.target).combotree('reload', url);
	                              		}
	                           		}
	                       		}
	                        },
				          	{
	                        	field:'location',
	                        	title:'文件路径',
	                        	width: 275,
	                        	hidden: true,
	                        	editor: {
	                        		type:'validatebox',
	                        		options: {
	                        			required:true,
	                        			validType:'location', 
	                        			missingMessage:'请填写文件路径'
	                        			}
	                        	}
	                        }
				      ]],
				title: '源设置',
				singleSelect: true,
				fitColumns: true,
				method: 'get',
				data: [{"id":"","name":"请选择数据源","tableName":"请选择表名","location": "请填写文件路径"}],
				onDblClickRow: function(rowIndex, rowData) {
					onClickRow(rowIndex, "#source-datasource-grid");
				},
				
				onAfterEdit: function(index, row, changes) {
					row.editing = false;
				},
				hidden: false,
				rowStyler: function(index,row) {
					return 'background:white;';
				},
				toolbar: [{
                    text:'保存',
                    handler:function() {
                    	accept("#source-datasource-grid");
                    	var typeId = $('#task-trans-type').combotree('getValue');
                    	if("4"==typeId){
    		        		initTimeStamp();
    		        		$("#timestamp-div").show();
    		        	} else {
    		        		$("#timestamp-div").hide();
    		        	}
                    	tableMappingInit = false;
                    	
                    	/*当用户重新保存源端后 可能会出现部分映射、过滤、转换的配置的失效和错误
                    	 *定义4个阶段的临时变量用于保存有用的数据
                    	 */
                    	var temporarycolumnMappings ={};
                		var temporarycolumnFilters = {};
                		var temporarycolumnVirusFilters = {};
                		var temporarycolumnTransfers = {};
                		
            			var sourceRows = $('#source-datasource-grid').datagrid('getRows');
            			var targetRows = $('#target-datasource-grid').datagrid('getRows');
            			
            			//将字符串转化为数组
            			if("string"==typeof(sourceRows[0].tableName)){
            				sourceRows[0].tableName = sourceRows[0].tableName.split(","); 
            			}
            			for(var i=0;i<targetRows.length;i++){
            				if("string"==typeof(targetRows[i].tableName)){
            					targetRows[i].tableName = targetRows[i].tableName.split(","); 
            				}
            			}
            			
            	    	for(var i=0;i<(sourceRows[0].tableName).length;i++){
            	    		for(var j=0;j<targetRows.length;j++){
            	    			for(var x=0;x<(targetRows[j].tableName).length;x++){
            	    				var val = sourceRows[0].tableName[i] + '->' + targetRows[j].tableName[x];
            	    				if(!(null==columnMappings[targetRows[j].id] ||
            	    						undefined==columnMappings[targetRows[j].id])){
            	    					if(!(null==columnMappings[targetRows[j].id][val] ||
            	    							undefined==columnMappings[targetRows[j].id][val])){
            	    						temporarycolumnMappings[targetRows[j].id] ={};
            	    						temporarycolumnMappings[targetRows[j].id][val]=columnMappings[targetRows[j].id][val];
            	    					}
            	    				}
            	    				if(!(null==columnFilters[targetRows[j].id] ||
            	    						undefined==columnFilters[targetRows[j].id])){
            	    					if(!(null==columnFilters[targetRows[j].id][val] ||
            	    							undefined==columnFilters[targetRows[j].id][val])){
            	    						temporarycolumnFilters[targetRows[j].id] ={};
            	    						temporarycolumnFilters[targetRows[j].id][val]=columnFilters[targetRows[j].id][val];
            	    					}
            	    				}
            	    				if(!(null==columnVirusFilters[targetRows[j].id] ||
            	    						undefined==columnVirusFilters[targetRows[j].id])){
            	    					if(!(null==columnVirusFilters[targetRows[j].id][val] ||
            	    							undefined==columnVirusFilters[targetRows[j].id][val])){
            	    						temporarycolumnVirusFilters[targetRows[j].id] ={};
            	    						temporarycolumnVirusFilters[targetRows[j].id][val]=columnVirusFilters[targetRows[j].id][val];
            	    					}
            	    				}
            	    				if(!(null==columnTransfers[targetRows[j].id] ||
            	    						undefined==columnMappings[targetRows[j].id])){
            	    					if(!(null==columnTransfers[targetRows[j].id][val] ||
            	    							undefined==columnTransfers[targetRows[j].id][val])){
            	    						temporarycolumnTransfers[targetRows[j].id] ={};
            	    						temporarycolumnTransfers[targetRows[j].id][val]=columnTransfers[targetRows[j].id][val];
            	    					}
            	    				}
            	    			}
            	    		}
            	    	}
            	    	columnMappings = temporarycolumnMappings;
            			columnFilters = temporarycolumnFilters;
            			columnVirusFilters = temporarycolumnVirusFilters;
            			columnTransfers = temporarycolumnTransfers;
    	            }
	            }],
				formatter : function(value, row, index) {
					if (row.datasource) {
						return row.datasource.name;
					} else {
						return value;
					}
				}
			});
			
			//10.初始化目标交换实体信息
			$('#target-datasource-grid').datagrid({
				loadMsg: -1,//作editIndex使用
				columns:[[
				          {
				        	  field: 'targetId',
				        	  hidden: true
				          },
				          {
				        	  field: 'id',
				        	  title: '数据源',
				        	  width: 275,
				        	  formatter: function(value,row) {
	                            return row.name;
	                          },
	                          editor: {
	                            type: 'combobox',
	                            options: {
	                            	editable:false,
	                                valueField: 'id',
	                                textField: 'name',
	                                required: true,
	                                missingMessage:'请选择数据源',
	                                url:pageContext + '/dataswitch/listDataSource?source_type=0',
	                                onSelect: function(record) {
	                                	var index = $('#target-datasource-grid').datagrid('options').loadMsg;
	                                	var targetRows = $('#target-datasource-grid').datagrid('getRows');
	                                	targetRows[index].id = record.id;
	                                	//给隐藏表单赋值 用于查询联动下拉框
	                                	$('#target-id').val(record.id);
	                                },
	                                onShowPanel: function() {
	                                	var index = $('#target-datasource-grid').datagrid('options').loadMsg;
	                                	var ed = $("#target-datasource-grid").datagrid('getEditor', {
	                                        index : index,
	                                        field : 'id'
	                                    });
	                                	var url = '';
	                                	if(0==$("#task-type").combobox('getValue')) {
	                                		url = pageContext + '/dataswitch/listDataSource?source_type=0';
	                                	}else if(1==$("#task-type").combobox('getValue')){
	                                		url = pageContext + '/dataswitch/listDataSource?source_type=1';
	                                	}
	                                	$(ed.target).combobox('clear');
	                                	$(ed.target).combobox('reload', url);
                              		}
	                            }
	                      	  }
	                      },
				          {
	                        	field: 'tableName',
	                        	title:'数据表',
	                        	hidden: false,
	                        	width: 275,
	                        	editor: {
	                                type:'combotree',
	                                options: {
	                                 	multiple:true,
	                                 	checkbox:true,
	                                 	editable:false,
	                                 	cascadeCheck:true,
	                                 	required: false,
	                                 	missingMessage:'请选择数据表',
	                                 	onShowPanel: function() {
	                                 		//var ed = $('#target-datasource-grid').datagrid('getEditor', {index: 0,field:'id'});
	                                 		var option = $('#target-datasource-grid').datagrid("options");
	                                 		var index = option.loadMsg;
	                              			var et = $('#target-datasource-grid').datagrid('getEditor', {index: index,field:'tableName'});
	                              			var id = $('#target-id').val();
	                              			var url= pageContext + '/dataswitch/getSourceById?id='+id;
	                              			$(et.target).combotree('reload', url);
	                              		},
	                              		onCheck: function(node) {
	                              			var option = $('#target-datasource-grid').datagrid("options");
	                                 		var index = option.loadMsg;
	                                 		$('#target-datasource-grid').datagrid('getRows', index)['tableName'] = node.id;
	                              		}
	                           		}
	                       		}
	                        },
				          	{
	                        	field:'location',
	                        	title:'文件路径',
	                        	width: 275,
	                        	hidden: true,
	                        	editor: {
	                        		type:'validatebox',
	                        		options: {
	                        			required:true,
	                        			validType:'location', 
	                        			missingMessage:'请填写文件路径'
	                        			}
	                        	}
	                        }
				      ]],
				title: '目标设置',
                singleSelect: true,
                fitColumns: true,
                toolbar: [{
                    text:'新增',
                    handler:function() {
                    	append('#target-datasource-grid', {id:"",name:"...",tableName:"...",location:"..."});
    	                }
	                },
               		{
                  	  text:'删除',
                  	  handler:function(){
                  		remove('#target-datasource-grid');
                   	 }
                	},{
                        text:'保存',
                        handler:function() {
                        	accept("#target-datasource-grid");
                        	tableMappingInit = false;
                        	/*当用户重新保存源端后 可能会出现部分映射、过滤、转换的配置的失效和错误
                        	 *定义4个阶段的临时变量用于保存有用的数据
                        	 */
                        	var temporarycolumnMappings ={};
                    		var temporarycolumnFilters = {};
                    		var temporarycolumnVirusFilters = {};
                    		var temporarycolumnTransfers = {};
                    		
                			var sourceRows = $('#source-datasource-grid').datagrid('getRows');
                			var targetRows = $('#target-datasource-grid').datagrid('getRows');
                			
                			//将字符串转化为数组
                			if("string"==typeof(sourceRows[0].tableName)){
                				sourceRows[0].tableName = sourceRows[0].tableName.split(","); 
                			}
                			for(var i=0;i<targetRows.length;i++){
                				if("string"==typeof(targetRows[i].tableName)){
                					targetRows[i].tableName = targetRows[i].tableName.split(","); 
                				}
                			}
                			
                	    	for(var i=0;i<(sourceRows[0].tableName).length;i++){
                	    		for(var j=0;j<targetRows.length;j++){
                	    			for(var x=0;x<(targetRows[j].tableName).length;x++){
                	    				var val = sourceRows[0].tableName[i] + '->' + targetRows[j].tableName[x];
                	    				if(!(null==columnMappings[targetRows[j].id] ||
                	    						undefined==columnMappings[targetRows[j].id])){
                	    					if(!(null==columnMappings[targetRows[j].id][val] ||
                	    							undefined==columnMappings[targetRows[j].id][val])){
                	    						temporarycolumnMappings[targetRows[j].id] ={};
                	    						temporarycolumnMappings[targetRows[j].id][val]=columnMappings[targetRows[j].id][val];
                	    					}
                	    				}
                	    				if(!(null==columnFilters[targetRows[j].id] ||
                	    						undefined==columnFilters[targetRows[j].id])){
                	    					if(!(null==columnFilters[targetRows[j].id][val] ||
                	    							undefined==columnFilters[targetRows[j].id][val])){
                	    						temporarycolumnFilters[targetRows[j].id] ={};
                	    						temporarycolumnFilters[targetRows[j].id][val]=columnFilters[targetRows[j].id][val];
                	    					}
                	    				}
                	    				if(!(null==columnVirusFilters[targetRows[j].id] ||
                	    						undefined==columnVirusFilters[targetRows[j].id])){
                	    					if(!(null==columnVirusFilters[targetRows[j].id][val] ||
                	    							undefined==columnVirusFilters[targetRows[j].id][val])){
                	    						temporarycolumnVirusFilters[targetRows[j].id] ={};
                	    						temporarycolumnVirusFilters[targetRows[j].id][val]=columnVirusFilters[targetRows[j].id][val];
                	    					}
                	    				}
                	    				if(!(null==columnTransfers[targetRows[j].id] ||
                	    						undefined==columnMappings[targetRows[j].id])){
                	    					if(!(null==columnTransfers[targetRows[j].id][val] ||
                	    							undefined==columnTransfers[targetRows[j].id][val])){
                	    						temporarycolumnTransfers[targetRows[j].id] ={};
                	    						temporarycolumnTransfers[targetRows[j].id][val]=columnTransfers[targetRows[j].id][val];
                	    					}
                	    				}
                	    			}
                	    		}
                	    	}
                	    	columnMappings = temporarycolumnMappings;
                			columnFilters = temporarycolumnFilters;
                			columnVirusFilters = temporarycolumnVirusFilters;
                			columnTransfers = temporarycolumnTransfers;
        	        	}
    	            }],
                method: 'get',
                data: [{"id":"","name":"请选择数据源","tableName":"请选择表名","location": "请填写文件路径"}],
                onDblClickRow: function(rowIndex, rowData) {
					onClickRow(rowIndex, "#target-datasource-grid");
				}
			});
			
			//11.mapping
			$('#table-mapping-grid').datagrid({
				columns:[[
				          {
				        	  field: 'id',
				        	  title: '数据源映射关系',
				        	  formatter: function(value,row) {
	                            return value;
	                            },
	                          width:220
	                      },
				          {
	                        	field: 'fromTable',
	                        	title:'源表',
	                        	width:171
	                        },
				          	{
	                        	field:'toTable',
	                        	title:'目标',
	                        	width:171,
	                        	hidden: false,
	                        	editor: {
	                        		type:'combobox',
	                        		options: {
		                            	editable:false,
		                                valueField: 'id',
		                                textField: 'name',
		                                required: true,
		                                missingMessage:'数据源映射关系',
		                                onShowPanel: function() {
		                                	var index = $('#table-mapping-grid').datagrid('options').loadMsg;
		                                	var ed = $("#table-mapping-grid").datagrid('getEditor', {
		                                        index : index,
		                                        field : 'toTable'
		                                    });
		                                	var sourceTableNames = $('#source-datasource-grid').datagrid('getRows')[0].tableName;
		                                	var sourceTableArr = sourceTableNames.split(",");
		                                	//获取这是第几个端的表  为没有匹配到的表名的下拉框赋值
		                                	var targetNum = (Math.ceil((index+1)/sourceTableArr.length));
		                                	var targetTableNames = $('#target-datasource-grid').datagrid('getRows')[targetNum-1].tableName;
		                                	var targetTableArr = targetTableNames.split(",");
		                                	var targetStringForJson = "[";
		                                	for(var i=0;i<targetTableArr.length;i++){
		                                		targetStringForJson += "{\"id\":\""+targetTableArr[i]+"\",\"name\":\""+targetTableArr[i]+"\"},";
		                                	}
		                                	targetStringForJson = targetStringForJson.substring(0,targetStringForJson.length-1);
		                                	targetStringForJson = targetStringForJson+"]";
		                                	var obj = $.parseJSON(targetStringForJson);
		                                	$(ed.target).combobox('loadData', obj);
	                              		}
		                            }
	                        	}
	                        },
	                        {
	                        	field: 'sourceId',
	                        	title: 'sourceId',
	                        	hidden: true
	                        },
	                        {
								field: 'targetId',
								title: 'targetId',
								hidden: true
	                        }
				      ]],
				title: '数据源映射关系',
                singleSelect: true,
                fitColumns: true,
                onDblClickRow: function(rowIndex, rowData) {
					onClickRow(rowIndex, "#table-mapping-grid");
				},
				onClickRow: function(rowIndex, rowData) {
					var sourceId = rowData.sourceId;
					var targetId = rowData.targetId;
					var fromTable = rowData.fromTable;
					var toTable = rowData.toTable;
					var id = rowData.id;
					if (undefined == sourceId || "" == sourceId 
							|| undefined == targetId || "" == targetId 
							|| undefined == fromTable || "" == fromTable 
							|| undefined == toTable || "" == toTable) {
						$('#column-mapping-grid').datagrid('loadData', { total: 0, rows: [] });
						return;
					}
					
					$('#current-source-ds-id').val(sourceId);
					$('#current-target-ds-id').val(targetId);
					$('#current-source-tb-name').val(fromTable);
					$('#current-target-tb-name').val(toTable);
					$('#current-mapping-name').val(id);
					current.sourceId = sourceId;
					current.targetId = targetId;
					current.sourceTableName = fromTable;
					current.targetTableName = toTable;
					current.dsMappingName = id;
					
					var key = fromTable + '->' + toTable;
					if (null == columnMappings[targetId] || undefined == columnMappings[targetId]) {
						columnMappings[targetId] = {};
					}
					if (undefined == columnMappings[targetId][key] || null == columnMappings[targetId][key]) {
						columnMappings[targetId][key] = {};
					}
					if (null == columnBeffer[targetId] || undefined == columnBeffer[targetId]) {
						columnBeffer[targetId] = {};
					}
					if (undefined == columnBeffer[targetId][key] || null == columnBeffer[targetId][key]) {
						columnBeffer[targetId][key] = {};
					}
					var rows = columnMappings[targetId][key].rows;
					if (undefined == rows || rows.length == 0) {
						$.ajax({
	                        type : "POST",
	                        async : false,
	                        url : pageContext+"/dataswitch/listColumnDto",
	                        data : {
	                            source:fromTable,
	                            sourceid:sourceId,
	                            target:toTable,
	                            targetid:targetId
	                        },
	                        success : function(data) {
	                        	var loadData = JSON.parse(data);
	                        	$('#column-mapping-grid').datagrid('loadData', loadData);
	                        	columnMappings[targetId][key] = loadData;
	                        	columnString = "[";
	                        	for(var  i=0;i<loadData.rows.length;i++){
	                        		columnString += "{\"id\":\""+ loadData.rows[i].sourceColumnName + "\",\"name\":\""
	                        		+ loadData.rows[i].sourceColumnName +"\"},";
	                        	}
	                        	columnString = columnString.substring(0,columnString.length-1);
	                        	columnString = columnString+"]";
	                        	columnBeffer[targetId][key] = columnString;
	                        	
	                        }
	                     });
					} else {
						$('#column-mapping-grid').datagrid('loadData', columnMappings[targetId][key]);
					}
					columnBefferString = columnBeffer[targetId][key];
				},
                toolbar: [{
                    text:'新增',
                    handler:function() {
                    		append('#table-mapping-grid', {});
    	                }
	                },
	                {
	                    text:'删除',
	                    handler:function() {
	                    	remove('#table-mapping-grid');
	    	            }
		            },
               		{
                  	  text:'保存',
                  	  handler:function() {
                  		accept("#table-mapping-grid");
                   	 }
                	}]
			});
			
			//时间戳表格
			$('#timestamp-grid').datagrid({
				loadMsg: -1,//作为editIndex使用
				width:580,
				autoRowHeight:true,
				columns:[[
				          {
				        	  field: 'tableName',
				        	  title: '表名',
				        	  width: 180,
				        	  align:'center',
				        	  formatter:function(value,row) {
				        	  	return value;
				        	  }
				          },
				          {
	                        	field: 'columnNames',
	                        	title:'表字段',
	                        	width: 180,
	                        	align:'center',
	                        	formatter: function(value,row) {
	                                return row.name;
	                              },
	                        	editor: {
	                                type:'combobox',
	                                options: {
	                                	editable:false,
		                                valueField: 'name',
		                                textField: 'name',
		                                required: true,
	                                 	missingMessage:'列名',
	                                 	onShowPanel: function() {
	                                 		var index = $('#timestamp-grid').datagrid('options').loadMsg;	//当前选中行
	                                 		var tableName = $('#timestamp-grid').datagrid("getRows")[index].tableName;//表名
	                                 		var sourceid = $('#timestamp-grid').datagrid("getRows")[index].sourceid;
	                              			var et = $('#timestamp-grid').datagrid('getEditor', {index: index,field:'columnNames'});
	                              			$.ajax({
	                              				url:pageContext + '/dataswitch/dateColumnName',
	                              				cache : false,
	                              				type : "post",
	                              				data : {source:tableName,sourceid:sourceid},
	                              				success:function(data){
	                              					var array = JSON.parse(data);
	                              				  	$(et.target).combobox('loadData', array);
	                              				  }
	                              			});
	                              		}
	                           		}
	                       		},
	                      },
	                      {
	                        	field:'timestamp',
	                        	title:'时间戳',
	                        	align:'center',
	                        	formatter:function(value,row) {
	                        		if(!updatetimeFlag){
	                        			if (value == null || value == '') {
	                        		        return '';
	                        		    }
	                        		var dt;
	                        		if (value instanceof Date) {
	                        		    dt = value;
	                        		}
	                        		else {
	                        		    dt = new Date(value);
	                        		    if (isNaN(dt)) {
	                        		        value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
	                        		        dt = new Date();
	                        		        dt.setTime(value);
	                        		    }
	                        		}
	                        		return dt.format("yyyy-MM-dd hh:mm:ss");   //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
	                        		}
	                        		return value;
	                        	},
	                        	width:180,
	                        	editor:'datetimebox'
	                        },
	                        {
	                    		field:'sourceid',
	                    		hidden:true
	                    	}
				      ]],
				title: '时间戳列表',
				singleSelect: true,
				onDblClickRow: function(rowIndex, rowData) {
					onClickRow(rowIndex, "#timestamp-grid");
				},
				toolbar: [{
                    text:'保存',
                    handler:function() {
                    	updatetimeFlag=false;
                    	accept("#timestamp-grid");
    	                }
	                }],
				formatter : function(value, row, index) {
					if (row.datasource) {
						return row.datasource.name;
					} else {
						return value;
					}
				}
			});
			
			$('#column-mapping-grid').datagrid({
				columns:[[
				          {
				        	  field: 'sourceId',
				        	  hidden: true
				          },
				          {
				        	  field: 'targetId',
				        	  hidden: true
				          },
				          {
				        	  field: 'sourceColumnName',
				        	  title: '源表字段名称',
	                          width:142.5,
	                      },
	                      {
	                    	  field: 'sourceColumnType',
	                    	  title: '源表字段类型',
	                    	  width:142.5
	                      },
	                      {
				        	  field: 'targetColumnName',
				        	  title: '目标字段名称',
	                          width:142.5,
	                          editor: {
	                        		type: 'combobox',
	                        		options: {
	                        			editable:false,
	                        			valueField: 'id',
	                        			textField: 'name',
	                        			required: true,
	                        			missingMessage:'数据源映射关系',
	                        			onShowPanel: function() {
	                        				var index = $('#column-mapping-grid').datagrid('options').loadMsg;
	                        				var obj = $.parseJSON(columnBefferString);
	                        				var et = $('#column-mapping-grid').datagrid('getEditor', {index: index,field:'targetColumnName'});
	                        				$(et.target).combobox('loadData', obj);
	                        			}
	                        		}
	                          }
	                      },
	                      {
	                    	  field: 'targetColumnType',
	                    	  title: '目标字段类型',
	                    	  width: 142.5,
	                    	  editor: {
		                            type: 'combobox',
		                            options: {
		                            	editable:false,
		                                valueField: 'id',
		                                textField: 'name',
		                                required: true,
		                                missingMessage:'数据源映射关系',
		                            	}
		                      	 	}
	                      }
				      ]],
				onDblClickRow: function(rowIndex, rowData) {
					onClickRow(rowIndex, "#column-mapping-grid");
				},
                toolbar: [{
		                    text:'病毒过滤',
		                    iconCls:'icon-filter',
		                    handler:function() {
		                    	var row = $('#column-mapping-grid').datagrid('getSelected');
		                    	var targetId = $('#current-target-ds-id').val();
		                    	var sourceTableName = $('#current-source-tb-name').val();
		                    	var data = {
			                    		sourceId: $('#current-source-ds-id').val(),
			                    		targetId: targetId,
		                  		  		sourceTableName: sourceTableName,
		                  		  		sourceColumnName: row.sourceColumnName,
		                  		  	};
		                    	
		                    	if (null == columnVirusFilters[targetId] || undefined == columnVirusFilters[targetId]) {
		                    		columnVirusFilters[targetId] = {};
		                    	}
		                    	if (undefined == columnVirusFilters[targetId][sourceTableName] || null == columnVirusFilters[targetId][sourceTableName]) {
		                    		columnVirusFilters[targetId][sourceTableName] = {};
		                    	}
		                    	var load = $('#filter-column-virus-grid').datagrid('getData');
		                    	if (undefined == load || undefined == load.rows || load.rows == 0) {
			                    	$('#filter-column-virus-grid').datagrid('appendRow',data);
		                    	} else {
		                    		$('#filter-column-virus-grid').datagrid('loadData', load);
		                    		$('#filter-column-virus-grid').datagrid('appendRow', data);
		                    	}
		                    	columnVirusFilters[targetId][sourceTableName] = $('#filter-column-virus-grid').datagrid('getData');
		                    	$('#new-task').tabs('select', 2);
		    	              }
			                },
		               		{
		                  	  text:'列值过滤',
		                  	  iconCls:'icon-filter',
		                  	  handler:function() {
		                  		  	var row = $('#column-mapping-grid').datagrid('getSelected');
		                  		  	var targetId = $('#current-target-ds-id').val();
		                  		  	var sourceTableName = $('#current-source-tb-name').val();
		                  		  	var targetTableName = $('#current-target-tb-name').val();
		                  		  	var key = sourceTableName + '->' + targetTableName;
		                  		  	var data = {
			                  		  		sourceId: $('#current-source-ds-id').val(),
			                  		  		targetId: $('#current-target-ds-id').val(),
			                  		  		dsMappingName: $('#current-mapping-name').val(),
			                  		  		sourceTableName: sourceTableName,
			                  		  		targetTableName: targetTableName,
			                  		  		sourceColumnName: row.sourceColumnName,
			                  		  		operator: '',
			                  		  		columnValue: ''
			                  		  	};
		                  		  	if (undefined == columnFilters[targetId] || null == columnFilters[targetId]) {
		                  		  		columnFilters[targetId] = {};
		                  		  	}
		                  		  	if (undefined == columnFilters[targetId][key] || null == columnFilters[targetId][key]) {
		                  		  		columnFilters[targetId][key] = {};
		                  		  	}
		                  		  	
		                  		  	var load = $('#filter-column-grid').datagrid('getData');
		                  		  	if (undefined == load || undefined == load.rows || load.rows == 0) {
		                  		  		$('#filter-column-grid').datagrid('appendRow',data);
		                  		  	} else {
			                  		  	$('#filter-column-grid').datagrid('loadData', load);
			                    		$('#filter-column-grid').datagrid('appendRow', data);
		                  		  	}
		                  		  	columnFilters[targetId][key] = $('#filter-column-grid').datagrid('getData');
		                  		  	$('#new-task').tabs('select', 2);
		                   	 	}
			                 },
		                   	 {
			                  	  text:'列值转换',
			                  	  iconCls:'icon-filter',
			                  	  handler:function() {
			                  		  	var row = $('#column-mapping-grid').datagrid('getSelected');
			                  		  	var targetId = $('#current-target-ds-id').val();
			                  		  	var sourceTableName = $('#current-source-tb-name').val();
			                  		  	var targetTableName = $('#current-target-tb-name').val();
			                  		  	var key = sourceTableName + '->' + targetTableName;
			                  		  	var data = {
				                  		  		sourceId: $('#current-sources-ds-id').val(),
				                  		  		targetId: targetId,
				                  		  		dsMappingName: $('#current-mapping-name').val(),
				                  		  		sourceTableName: sourceTableName,
				                  		  		targetTableName: targetTableName,
				                  		  		sourceColumnName: row.sourceColumnName,
				                  		  		originals:"",
				                  		  		replacement:""
				                  		  	};
			                  		  	if (undefined == columnTransfers[targetId] || null == columnTransfers[targetId]) {
			                  		  		columnTransfers[targetId] = {};
			                  		  		
			                  		  	}
			                  		  	if (undefined == columnTransfers[targetId][key] || null == columnTransfers[targetId][key]) {
			                  		  		columnTransfers[targetId][key] = {};
			                  		  	}
			                  		  	
			                  		  	var load = $('#transform-column-grid').datagrid('getData');
			                  		  	if (undefined == load || undefined == load.rows || load.rows == 0) {
			                  		  		$('#transform-column-grid').datagrid('appendRow',data);
			                  		  	} else {
				                  		  	$('#transform-column-grid').datagrid('loadData', load);
				                    		$('#transform-column-grid').datagrid('appendRow', data);
			                  		  	}
			                  		  	columnTransfers[targetId][key] = $('#transform-column-grid').datagrid('getData');
			                  		  	$('#new-task').tabs('select', 3);
			                   	 }
			                },
			                {
			                    text:'删除',
			                    handler:function() {
			                    	remove('#column-mapping-grid');
			                    	var targetId = $('#current-target-ds-id').val();
			                  		var key = $('#current-source-tb-name').val() + '->' + $('#current-target-tb-name').val();
			                  		columnMappings[targetId][key] = $('#column-mapping-grid').datagrid('getData');
			    	            }
				            },
		               		{
		                  	  text:'保存',
		                  	  handler:function() {
		                  		accept("#column-mapping-grid");
		                  		var targetId = $('#current-target-ds-id').val();
		                  		var key = $('#current-source-tb-name').val() + '->' + $('#current-target-tb-name').val();
		                  		columnMappings[targetId][key] = $('#column-mapping-grid').datagrid('getData');
		                   	 }
		                	}],
				title: "字段映射关系",
				singleSelect: true,
				fitColumns: true
			});
			
			
			$("#filter-filename-inbox").combobox({
				valueField:'id',
	            textField:'text',
	            data: fileType
			});
			
			$("#filter-filename-exbox").combobox({
				valueField:'id',
	            textField:'text',
	            data: fileType
			});
			
			$('#filter-file-content-type').combobox({
				valueField:'id',
	            textField:'text',
	            data: filterType
			});
			
			$('#filter-filecontent-box').combobox({
				valueField:'id',
	            textField:'text',
	            data: fileType
			});
			
			$('#filter-file-virus').combobox({
				valueField:'id',
	            textField:'text',
	            data: fileType
			});
			$('#filter-content-keyword').validatebox({
				required: true
			});
			$('#filter-file-virus-table').validatebox({
				required: true
			});
			$('#filter-file-virus-column').validatebox({
				required: true
			});
			$('#filter-column-table').validatebox({
				required: true
			});
			$('#filter-column').validatebox({
				required: true
			});
			$('#filter-column-operator').combobox({
				data: columnOperator,
				valueField:'id',
	            textField:'text'
			});
			$('#filter-column-value').validatebox({
				required: true
			});
			
			$('#filter-column-virus-grid').datagrid({
				columns:[[
				          {
				        	  field: 'sourceTableName',
				        	  title: '表名称',
	                          width:($(this).width()*0.5),
	                      },
	                      {
	                    	  field: 'sourceColumnName',
	                    	  title: '字段名称',
	                    	  width:($(this).width()*0.5)
	                      }
				      ]],
                toolbar: [{
		                    text:'删除',
		                    handler: function() {
		                    	remove('#filter-column-virus-grid');
		                    	var targetId = current.targetId;
		                  		var key = current.sourceTableName + '->' + current.targetTableName;
		                  		columnVirusFilters[targetId][key] = $('#filter-column-virus-grid').datagrid('getData');
		    	                }
			                },
		               		{
		                  	  text:'保存',
		                  	  handler: function() {
		                  		accept('#filter-column-virus-grid');
		                  		var targetId = current.targetId;
		                  		var key = current.sourceTableName + '->' + current.targetTableName;
		                  		columnVirusFilters[targetId][key] = $('#filter-column-virus-grid').datagrid('getData');
		                   	 }
		                	}],
				title: "病毒过滤",
				singleSelect: true,
				loadMsg: -1,
				fitColumns: true
			});
			
			$('#filter-column-grid').datagrid({
				columns:[[
				          {
				        	field: "dsMappingName",
				        	title: "数据源映射关系",
				        	width: ($(this).width()*0.25)
				          },
				          {
				        	  field: 'sourceTableName',
				        	  title: '源表名称',
	                          width:($(this).width()*0.2)
	                      },
	                      {
	                    	  field: 'targetTableName',
	                    	  title: '目标表名称',
	                    	  width:($(this).width()*0.2)
	                      },
	                      {
				        	  field: 'sourceColumnName',
				        	  title: '字段名称',
	                          width:($(this).width()*0.2)
	                      },
	                      {
	                    	  field: 'operator',
	                    	  title: '比较符',
	                    	  width:($(this).width()*0.2),
	                    	  editor: {
		                            type: 'combobox',
		                            options: {
		                            	editable:false,
		                                valueField: 'id',
		                                textField: 'text',
		                                required: true,
		                                missingMessage:'数据源映射关系',
		                                data: columnOperator
		                            }
		                      }
	                      },
	                      {
	                    	  field: 'columnValue',
	                    	  title: '比较值',
	                    	  width:($(this).width()*0.25),
	                    	  editor: {
		                            type: 'text'
	                    	  }
	                      }
				      ]],
				onDblClickRow: function(rowIndex, rowData) {
					onClickRow(rowIndex, "#filter-column-grid");
				},
                toolbar: [{
		                    text:'删除',
		                    handler:function() {
		                    	remove('#filter-column-grid');
		                    	var targetId = current.targetId;
		                  		var key = current.sourceTableName + '->' + current.targetTableName;
		                  		columnFilters[targetId][key] = $('#filter-column-grid').datagrid('getData');
		    	                }
			                },
		               		{
		                  	  text:'保存',
		                  	  handler:function() {
		                  		accept('#filter-column-grid');
		                  		var targetId = current.targetId;
		                  		var key = current.sourceTableName + '->' + current.targetTableName;
		                  		columnFilters[targetId][key] = $('#filter-column-grid').datagrid('getData');
		                   	 }
		                	}],
				title: "字段过滤",
				fitColumns: true
			});
			
			$('#filter-file-content-grid').datagrid({
				columns:[[
				          {
				        	  field: 'contentFilterType',
				        	  title: '过滤类型',
	                          width:($(this).width()*0.5)
	                      },
	                      {
	                    	  field: 'fileName',
	                    	  title: '文件名称',
	                    	  width:($(this).width()*0.5)
	                      },
	                      {
	                    	  field: 'filterContent',
	                    	  title: '过滤字句',
	                    	  width:($(this).width()*0.5)
	                      }
				      ]],
                toolbar: [{
		                    text:'增加',
		                    handler:function() {
		                    	alert('add');
		    	                }
			                },
		               		{
		                  	  text:'删除',
		                  	  handler:function(){
		                  		  	alert('cut');
		                   	 }
		                	}],
				title: "文件内容过滤",
				fitColumns: true
			});
			
			$('#transform-file-grid').datagrid({
				columns:[[
				          {
				        	  field: 'fileName',
				        	  title: '文件名称',
	                          width:($(this).width()*0.3)
	                      },
	                      {
	                    	  field: 'originals',
	                    	  title: '内容',
	                    	  width:($(this).width()*0.3)
	                      },
	                      {
	                    	  field: 'replacement',
	                    	  title: '替换为',
	                    	  width:($(this).width()*0.4)
	                      }
				      ]],
                toolbar: [{
		                    text:'增加',
		                    handler:function() {
		                    	alert('add');
		    	                }
			                },
		               		{
		                  	  text:'删除',
		                  	  handler:function(){
		                  		
		                   	 }
		                	}],
				title: "文件内容转换",
				fitColumns: true
			});
			
			//列值转换
			$('#transform-column-grid').datagrid({
				columns:[[
				          {
				        	  field: 'sourceId',
				        	  hidden: true
				          },
				          {
				        	  field: 'targetId',
				        	  hidden: true
				          },
				          {
				        	field: "dsMappingName",
				        	title: "数据源映射关系",
				        	width: ($(this).width()*0.25)
				          },
				          {
							  field: 'sourceTableName',
							  title: '源表名称',
							  width: ($(this).width()*0.15)
						  },
						  {
							field: 'targetTableName',
							title: '目标表名称',
							width: ($(this).width()*0.15)
						  },
				          {
				        	  field: 'sourceColumnName',
				        	  title: '源表列名称',
	                          width:($(this).width()*0.15)
	                      },
	                      {
	                    	  field: 'originals',
	                    	  title: '内容',
	                    	  width:($(this).width()*0.15),
	                    	  editor: {
	                        		type:'validatebox',
	                        		options: {
	                        			required:true,
	                        			missingMessage:'请填写内容'
	                        		}
	                        	}
	                      },
	                      {
	                    	  field: 'replacement',
	                    	  title: '替换为',
	                    	  width:($(this).width()*0.15),
	                    	  editor: {
	                        		type:'validatebox',
	                        		options: {
	                        			required:true,
	                        			missingMessage:'请填写替换内容'
	                        		}
	                          }
	                      }
				      ]],
                toolbar: [{
		                	text:'保存',
		                	handler:function() {
		                		accept('#transform-column-grid');
		                  		var targetId = current.targetId;
		                  		var key = current.sourceTableName + '->' + current.targetTableName;
		                  		columnTransfers[targetId][key] = $('transform-column-grid').datagrid('getData');
		                   	 }
	                	},{
		                	text:'删除',
		                  	handler:function() {
		                  		remove('#transform-column-grid');
		                  		var targetId = current.targetId;
		                  		var key = current.sourceTableName + '->' + current.targetTableName;
		                  		columnTransfers[targetId][key] = $('transform-column-grid').datagrid('getData');
		                   	}
		                }],
		        title: "列值转换",
		        onDblClickRow: function(rowIndex, rowData) {
					onClickRow(rowIndex, "#transform-column-grid");
				},
//				
//				onAfterEdit: function(index, row, changes) {
//					row.editing = false;
//				},
				fitColumns: true
			});
			
			//取消按钮
			$('#btn-cancel,#btn-cancel1').on('click', function(){
				$('#totalForm').form('clear');
				$('#new-task').tabs("select",0);
				$('#new-task').window('close');
				
			});
			
			//完成按钮
			$('#btn-save,#btn-save1').on('click', function(){
		        var isValidate = $("#totalForm").form('validate');
				if(isValidate){
					var sourceDSRows = $('#source-datasource-grid').datagrid('getRows')[0];
			    	var etl = FullEtlArrange();
			    	
			    	//源端回写后修改如果没有通过保存则要将object数组转换为字符串 用于json的反序列化
			    	if("object"==typeof(sourceDSRows.tableName)){
			    		var tableNames = "";
			    		for(var i=0;i<(sourceDSRows.tableName).length;i++){
			    			tableNames += sourceDSRows.tableName[i]+",";
			    		}
			    		tableNames=tableNames.substring(0,tableNames.length-1);
			    		sourceDSRows.tableName = tableNames;
			    	}
		    		if("object"==typeof(sourceDSRows.location)){
		    			var location = sourceDSRows.location[0];
		    			sourceDSRows.location = location;
		    		}
			    	var datasource= {
							sourceDSInfo : {
								sourceId: sourceDSRows.id,
								tableName: sourceDSRows.tableName,
								location: sourceDSRows.location
							}
			    	};
			    	//目标端回写后修改如果没有通过保存则要将object数组转换为字符串 用于json的反序列化
			    	var tarArr = $('#target-datasource-grid').datagrid('getRows');
			    	for(var i=0;i<tarArr.length;i++){
			    		if("object"==typeof(tarArr[i].tableName)){
			    			var tableNames = "";
				    		for(var j=0;j<(tarArr[i].tableName).length;j++){
				    			tableNames += tarArr[i].tableName[j]+",";
				    		}
				    		tableNames=tableNames.substring(0,tableNames.length-1);
				    		tarArr[i].tableName = tableNames;
			    		}
			    		if("object"==typeof(tarArr[i].location)){
			    			var location = tarArr[i].location[0];
			    			tarArrp[i].location = location;
			    		}
			    	}
			    	//发送ajax请求判断选中的表中是否包含完整的主从表关系
			    	var taskdata ="";
			    	var taskid="";
			    	var url = pageContext+"/dataswitch/addTask";
			    	if(updateFlag){
			    		taskid=$("#taskId").val();
			    		url = pageContext+"/dataswitch/editTask";
			    	}
			    	
			    	
			    	//如果是数据库类型则要判断主从表关系
			    	if(0==$('#task-type').combotree('getValue')){
			    		$.ajax({
				            type : "POST",
				            async : false,
				            //dataType: "json",
				            //contentType: "application/json",
				            url : pageContext+"/dataswitch/forAssocication",
				            data : JSON.stringify(datasource),
				            success : function(data) {
				            	eval('data=' + data);
				            	//没有主从关系
								if (0==data.isAssociation) {
									taskdata = {
											taskId:taskid,
							    			taskName: $("#task-name").val(),
							    			taskType: $('#task-type').combobox('getValue'),
							    			synchroType: $('#task-trans-type').combotree('getValue'),
							    			taskDescription: $('#task-description').val(),
							    			deleteSourceData: $('#delete-source-data').prop('checked'),
											rebuildTrigger: $('#rebuild-trigger').prop('checked'),
											sysLogPrint:$('#sysLog-Print').prop('checked'),
											association:0,
											rowLevelLog:$('#row-Level-Log').prop('checked'),
											sourceDSInfo : {
												sourceId: sourceDSRows.id+"",
												tableName: sourceDSRows.tableName,
												location: sourceDSRows.location
											},
											targetDSInfo : $('#target-datasource-grid').datagrid('getRows'),
											timestamps:$('#timestamp-grid').datagrid('getRows'),
											tableMappingInfo: $('#table-mapping-grid').datagrid('getRows'),
											columnMappingInfo: etl.columnMappings,
											columnVirusFilterInfo: etl.columnVirusFilters,
											columnFilterInfo: etl.columnFilters,
											columnTransferInfo: etl.columnTransfers
							    	};
								} else if(1==data.isAssociation){//主从表关系不健全
									taskdata = {
											taskId:taskid,
							    			taskName: $("#task-name").val(),
							    			taskType: $('#task-type').combobox('getValue'),
							    			synchroType: $('#task-trans-type').combotree('getValue'),
							    			taskDescription: $('#task-description').val(),
							    			deleteSourceData: $('#delete-source-data').prop('checked'),
											rebuildTrigger: $('#rebuild-trigger').prop('checked'),
											association:1,
											sysLogPrint:$('#sysLog-Print').prop('checked'),
											rowLevelLog:$('#row-Level-Log').prop('checked'),
											sourceDSInfo : {
												sourceId: sourceDSRows.id,
												tableName: sourceDSRows.tableName,
												location: sourceDSRows.location
											},
											targetDSInfo : $('#target-datasource-grid').datagrid('getRows'),
											timestamps:$('#timestamp-grid').datagrid('getRows'),
											tableMappingInfo: $('#table-mapping-grid').datagrid('getRows'),
											columnMappingInfo: etl.columnMappings,
											columnVirusFilterInfo: etl.columnVirusFilters,
											columnFilterInfo: etl.columnFilters,
											columnTransferInfo: etl.columnTransfers
							    	};
									$.messager.confirm('警告', '选中源端表中包含不健全的主从表关系，如果继续可能会造成数据紊乱，是否要继续该操作', function(r){
										 if (!r){
											 return;
										 }
									});
								} else {//有健全的主从表关系
									taskdata = {
											taskId:taskid,
							    			taskName: $("#task-name").val(),
							    			taskType: $('#task-type').combobox('getValue'),
							    			synchroType: $('#task-trans-type').combotree('getValue'),
							    			taskDescription: $('#task-description').val(),
							    			deleteSourceData: $('#delete-source-data').prop('checked'),
											rebuildTrigger: $('#rebuild-trigger').prop('checked'),
											association:1,
											sysLogPrint:$('#sysLog-Print').prop('checked'),
											rowLevelLog:$('#row-Level-Log').prop('checked'),
											sourceDSInfo : {
												sourceId: sourceDSRows.id,
												tableName: sourceDSRows.tableName,
												location: sourceDSRows.location
											},
											targetDSInfo : $('#target-datasource-grid').datagrid('getRows'),
											timestamps:$('#timestamp-grid').datagrid('getRows'),
											tableMappingInfo: $('#table-mapping-grid').datagrid('getRows'),
											columnMappingInfo: etl.columnMappings,
											columnVirusFilterInfo: etl.columnVirusFilters,
											columnFilterInfo: etl.columnFilters,
											columnTransferInfo: etl.columnTransfers
							    	};
								}
				            }
				    	});
			    	} else {//文件类型 taskdata 赋值
			    		taskdata = {
			    				taskId:taskid,
				    			taskName: $("#task-name").val(),
				    			taskType: $('#task-type').combobox('getValue'),
				    			taskDescription: $('#file-task-description').val(),
								sourceFile:$('#source-File').combobox('getValue'),
								targetFile:$('#target-File').combobox('getValue'),
								sysLogPrint:$('#file-sysLog-Print').prop('checked'),
								rowLevelLog:$('#file-row-Level-Log').prop('checked'),
								sourceDSInfo : {
									sourceId: sourceDSRows.id,
									tableName: sourceDSRows.tableName,
									location: sourceDSRows.location
								},
								targetDSInfo : $('#target-datasource-grid').datagrid('getRows'),
								tableMappingInfo: $('#table-mapping-grid').datagrid('getRows'),
								columnMappingInfo: etl.columnMappings,
								columnVirusFilterInfo: etl.columnVirusFilters,
								columnFilterInfo: etl.columnFilters,
								columnTransferInfo: etl.columnTransfers
				    	};
			    	}
			    	$.ajax({
			            type : "POST",
			            async : false,
			            //dataType: "json",
			            //contentType: "application/json",
			            url : url,
			            data : JSON.stringify(taskdata),
			            success : function(data) {
			            	eval('data=' + data);
							if (data.dealFlag) {
								$('#task-table').datagrid('reload');
								$('#new-task').window('close');
								$.messager.show({
									title : '提示',
									msg : data.msg
								});
								if("3"==$('#task-trans-type').combotree('getValue')||
												"4"==$('#task-trans-type').combotree('getValue')){
									createAddTable(data.id);
								}
								
							} else {
								$.messager.show({
									title : '提示',
									msg : data.msg,
									showType : 'slide',
									timeout : 2000
								});
							}	
			            }
			         });
				}
		    });
			//任务类型联动
			function changeTaskType(val){
				if(0==val){
					$("#db").show();
					$("#il").show();
					$("#delete-source-data-div").show();
					$("#file").hide();
					$("#file-syslog").hide();
					$("#file-description").hide();
					 	var sdg = $('#source-datasource-grid').datagrid('options').columns;
		                for (var i = 0; i < sdg[0].length; i++) {
		                    if (sdg[0][i].field == "id") {
		                        var url = pageContext+'/dataswitch/listDataSource?source_type=0';
		                        sdg[0][i].editor.options['url'] = encodeURI(url);
		                        break;
		                    }
		                }
		                var tdg = $('#target-datasource-grid').datagrid('options').columns;
		                for (var i = 0; i < tdg[0].length; i++) {
		                    if (tdg[0][i].field == "id") {
		                        var url = pageContext+'/dataswitch/listDataSource?source_type=0';
		                        //重新给下拉框的url加载
		                        tdg[0][i].editor.options['url'] = encodeURI(url);
		                        break;
		                    }
		                }
		                if(!tableMappingInit){
			                //数据库类型
			                $('#target-datasource-grid').datagrid('hideColumn', 'location');
			                $("#target-datasource-grid").datagrid('showColumn', 'tableName');
			                $('#source-datasource-grid').datagrid('hideColumn', 'location');
			                $("#source-datasource-grid").datagrid('showColumn', 'tableName');
		                }
				}else if(1==val){
					$("#il").hide();
					$("#db").hide();
					$("#delete-source-data-div").hide();
					$("#file").show();
					$("#file-syslog").show();
					$("#file-description").show();
					var sdg = $('#source-datasource-grid').datagrid('options').columns;
	                for (var i = 0; i < sdg[0].length; i++) {
	                    if (sdg[0][i].field == "id") {
	                        var url = pageContext+'/dataswitch/listDataSource?source_type=1';
	                        sdg[0][i].editor.options['url'] = encodeURI(url);
	                        break;
	                    }
	                }
	                var tdg = $('#target-datasource-grid').datagrid('options').columns;
	                for (var i = 0; i < tdg[0].length; i++) {
	                    if (tdg[0][i].field == "id") {
	                        var url = pageContext+'/dataswitch/listDataSource?source_type=1';
	                        //重新给下拉框的url加载
	                        tdg[0][i].editor.options['url'] = encodeURI(url);
	                        break;
	                    }
	                }
	                $('#target-datasource-grid').datagrid('hideColumn', 'tableName');
	                $('#target-datasource-grid').datagrid('showColumn', 'location');
	                $('#source-datasource-grid').datagrid('hideColumn', 'tableName');
	                $('#source-datasource-grid').datagrid('showColumn', 'location');
				}
			}
			
			//将多个子任务的etl策略何必为一个传到后台
			function FullEtlArrange(){
				var temporarycolumnMappings = new Array();
        		var temporarycolumnFilters = new Array();
        		var temporarycolumnVirusFilters = new Array();
        		var temporarycolumnTransfers = new Array();
        		
    			var sourceRows = $('#source-datasource-grid').datagrid('getRows');
    			var targetRows = $('#target-datasource-grid').datagrid('getRows');
    			
    			//将字符串转化为数组
    			if("string"==typeof(sourceRows[0].tableName)){
    				sourceRows[0].tableName = sourceRows[0].tableName.split(","); 
    			}
    			for(var i=0;i<targetRows.length;i++){
    				if("string"==typeof(targetRows[i].tableName)){
    					targetRows[i].tableName = targetRows[i].tableName.split(","); 
    				}
    			}
    			
    	    	for(var i=0;i<(sourceRows[0].tableName).length;i++){
    	    		for(var j=0;j<targetRows.length;j++){
    	    			for(var x=0;x<(targetRows[j].tableName).length;x++){
    	    				var val = sourceRows[0].tableName[i] + '->' + targetRows[j].tableName[x];
    	    				if(!(null==columnMappings[targetRows[j].id] ||
    	    						undefined==columnMappings[targetRows[j].id])){
    	    					if(!(null==columnMappings[targetRows[j].id][val] ||
    	    							undefined==columnMappings[targetRows[j].id][val])){
    	    						for(var y=0;y<columnMappings[targetRows[j].id][val].rows.length;y++){
    	    							temporarycolumnMappings.push(columnMappings[targetRows[j].id][val].rows[y]);
    	    						}
    	    					}
    	    				}
    	    				if(!(null==columnFilters[targetRows[j].id] ||
    	    						undefined==columnFilters[targetRows[j].id])){
    	    					if(!(null==columnFilters[targetRows[j].id][val] ||
    	    							undefined==columnFilters[targetRows[j].id][val])){
    	    						for(var y=0;y<columnFilters[targetRows[j].id][val].rows.length;y++){
    	    							temporarycolumnFilters.push(columnFilters[targetRows[j].id][val].rows[y]);
    	    						}
    	    					}
    	    				}
    	    				if(!(null==columnVirusFilters[targetRows[j].id] ||
    	    						undefined==columnVirusFilters[targetRows[j].id])){
    	    					if(!(null==columnVirusFilters[targetRows[j].id][val] ||
    	    							undefined==columnVirusFilters[targetRows[j].id][val])){
    	    						for(var y=0;y<columnVirusFilters[targetRows[j].id][val].rows.length;y++){
    	    							temporarycolumnVirusFilters.push(columnVirusFilters[targetRows[j].id][val].rows[y]);
    	    						}
    	    					}
    	    				}
    	    				if(!(null==columnTransfers[targetRows[j].id] ||
    	    						undefined==columnMappings[targetRows[j].id])){
    	    					if(!(null==columnTransfers[targetRows[j].id][val] ||
    	    							undefined==columnTransfers[targetRows[j].id][val])){
    	    						for(var y=0;y<columnTransfers[targetRows[j].id][val].rows.length;y++){
    	    							temporarycolumnTransfers.push(columnTransfers[targetRows[j].id][val].rows[y]);
    	    						}
    	    					}
    	    				}
    	    			}
    	    		}
    	    	}
    	    	var etl={
    	    			columnMappings:temporarycolumnMappings,
    	    			columnFilters : temporarycolumnFilters,
    	    			columnVirusFilters : temporarycolumnVirusFilters,
    	    			columnTransfers : temporarycolumnTransfers
    	    	};
    	    	return etl;
			}
			
			
			
});