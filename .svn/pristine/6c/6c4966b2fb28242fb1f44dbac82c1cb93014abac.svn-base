			$.extend(
                    $.fn.validatebox.defaults.rules,
                    {
                		equals : {
							validator : function(value, param) {
								return value == $(param[0]).val();
							},
							message : '两次密码不匹配'
						},
                        sourceid : {
                               validator: function(value){   
                                   if(value.length==0){
                                       return false;
                                   }else{
                                       return true;
                                   }
                                },    
                                message: '请选择数据源'   
                        },
                        selectValueRequired: {  
                            validator: function (value, param) {  
                                if (value == "" || value.indexOf('选择') >= 0 || value.indexOf('全部') >= 0) { return false; }  
                                else { return true; }  
                            },  
                            message: '该下拉框为必选项'  
                        },
                        minute :{
                        	validator : function(value,param) {
                        		var reStr = /^[0-5]?[0-9]{1}$/;
                        		return value.match(new RegExp(reStr));
                        	},
                        	message:'请输入正确的分钟格式'
                        },
                        taskName : {
                            validator : function(value) {
                            	var rules = $.fn.validatebox.defaults.rules;
                                var flg = 0;
                                if (!updateFlag) {
                                    //如果不是编辑，是新增才需要判断
                                    $.ajax({
                                                type : "POST",
                                                async : false,
                                                url :  $("#pageContext").val()+"/dataswitch/checkTaskName",
                                                data : {
                                                    name : value
                                                },
                                                success : function(data) {
                                                	var obj =  eval('(' + data + ')');
                                                    if (obj.dealFlag) {
                                                        flg = 1;
                                                        rules.taskName.message = "任务名重名";
                                                    }
                                                }
                                            });
                                }
                                if (flg == 0) {
                                    return true;
                                } else {
                                    return false;
                                }
                            },
                            message : ""
                        },
                        location : {
                            validator : function(value) {
                                if (value == "") {
                                    return true;
                                } else {
                                    var reStr = '^([a-zA-Z]\:|\\\\[^\/\\:*?"<>|]+\\[^\/\\:*?"<>|]+)(\\[^\/\\:*?"<>|]+)+(\.[^\/\\:*?"<>|]+)$';
                                    var re = new RegExp(reStr);
                                   return true;
                                }
                            },
                            message : "文件路径不合法"
                        },
                        doesExistFtp : {
                            validator : function(value) {
                            	var rules = $.fn.validatebox.defaults.rules;
                                var flg = 0;
                                if (!updateFtpFlag) {
                                    //如果不是编辑，是新增才需要判断
                                    $.ajax({
                                                type : "POST",
                                                async : false,
                                                url :  $("#pageContext").val()+"/ftpservice/doesExist",
                                                data : {
                                                	ftpUsername : value
                                                },
                                                success : function(data) {
                                                	var obj =  eval('(' + data + ')');
                                                    if (obj.dealFlag) {
                                                        flg = 1;
                                                        rules.doesExistFtp.message = "FTP用户名重名";
                                                    }
                                                }
                                            });
                                }
                                if (flg == 0) {
                                    return true;
                                } else {
                                    return false;
                                }
                            },
							message : ""
						}
                    });
    /**  
     * 扩展两个方法  
     */  
    $.extend($.fn.datagrid.methods, {   
        /**
         * 开打提示功能  
         * @param {} jq  
         * @param {} params 提示消息框的样式  
         * @return {}  
         */  
        doCellTip : function(jq, params) {   
            function showTip(data, td, e) {   
                if ($(td).text() == "")   
                    return;   
                data.tooltip.text($(td).text()).css({   
                            top : (e.pageY + 10) + 'px',   
                            left : (e.pageX + 20) + 'px',   
                            'z-index' : $.fn.window.defaults.zIndex,   
                            display : 'block'   
                        });   
            };   
            return jq.each(function() {   
                var grid = $(this);   
                var options = $(this).data('datagrid');   
                if (!options.tooltip) {   
                    var panel = grid.datagrid('getPanel').panel('panel');   
                    var defaultCls = {   
                        'border' : '1px solid #333',   
                        'padding' : '1px',   
                        'color' : '#333',   
                        'background' : '#f7f5d1',   
                        'position' : 'absolute',   
                        'max-width' : '200px',   
                        'border-radius' : '4px',   
                        '-moz-border-radius' : '4px',   
                        '-webkit-border-radius' : '4px',   
                        'display' : 'none'   
                    }   
                    var tooltip = $("<div id='celltip'></div>").appendTo('body');   
                    tooltip.css($.extend({}, defaultCls, params.cls));   
                    options.tooltip = tooltip;   
                    panel.find('.datagrid-body').each(function() {   
                        var delegateEle = $(this).find('> div.datagrid-body-inner').length   
                                ? $(this).find('> div.datagrid-body-inner')[0]   
                                : this;   
                        $(delegateEle).undelegate('td', 'mouseover').undelegate(   
                                'td', 'mouseout').undelegate('td', 'mousemove')   
                                .delegate('td', {   
                                    'mouseover' : function(e) {   
                                        if (params.delay) {   
                                            if (options.tipDelayTime)   
                                                clearTimeout(options.tipDelayTime);   
                                            var that = this;   
                                            options.tipDelayTime = setTimeout(   
                                                    function() {   
                                                        showTip(options, that, e);   
                                                    }, params.delay);   
                                        } else {   
                                            showTip(options, this, e);   
                                        }   
      
                                    },   
                                    'mouseout' : function(e) {   
                                        if (options.tipDelayTime)   
                                            clearTimeout(options.tipDelayTime);   
                                        options.tooltip.css({   
                                                    'display' : 'none'   
                                                });   
                                    },   
                                    'mousemove' : function(e) {   
                                        var that = this;   
                                        if (options.tipDelayTime) {   
                                            clearTimeout(options.tipDelayTime);   
                                            options.tipDelayTime = setTimeout(   
                                                    function() {   
                                                        showTip(options, that, e);   
                                                    }, params.delay);   
                                        } else {   
                                            showTip(options, that, e);   
                                        }   
                                    }   
                                });   
                    });   
      
                }   
      
            });   
        },   
        /**
         * 关闭消息提示功能  
         * @param {} jq  
         * @return {}  
         */  
        cancelCellTip : function(jq) {   
            return jq.each(function() {   
                        var data = $(this).data('datagrid');   
                        if (data.tooltip) {   
                            data.tooltip.remove();   
                            data.tooltip = null;   
                            var panel = $(this).datagrid('getPanel').panel('panel');   
                            panel.find('.datagrid-body').undelegate('td',   
                                    'mouseover').undelegate('td', 'mouseout')   
                                    .undelegate('td', 'mousemove')   
                        }   
                        if (data.tipDelayTime) {   
                            clearTimeout(data.tipDelayTime);   
                            data.tipDelayTime = null;   
                        }   
                    });   
        }   
    }); 
    //重写combotree
    $.extend(jQuery.fn.datagrid.defaults.editors, {
        combotree : {
            init : function(container, options) {
                var editor = jQuery('<input type="text">').appendTo(
                        container);
                editor.combotree(options);
                return editor;
            },
            destroy : function(target) {
                jQuery(target).combotree('destroy');
            },
            getValue : function(target) {
                var temp = jQuery(target).combotree('getValues');
                //alert(temp);  
                //将取到的值用,分开
                return temp.join(',');
            },
            setValue : function(target, value) {
                //                  var temp = value.split(','); 
                //                  jQuery(target).combotree('setValues',temp);  
                var children = jQuery(target).tree("getChecked");
                for (var i = 0; i < children.length; i++) {
                    alert(children[i].text);
                }
                return jQuery(target).tree("getChecked");
            },
            resize : function(target, width) {
                jQuery(target).combotree('resize', width);
            }
        }
    });
    //继承了，datagrid里面才会有combogrid
    $.extend($.fn.datagrid.defaults.editors, {
        combogrid : {
            init : function(container, options) {
                var input = $(
                        '<input type="text" class="easyui-validatebox" >')
                        .appendTo(container);
                input.combogrid(options);
                return input;
            },
            validType : location
        }
    });
    $
    .extend(
            $.fn.datagrid.defaults.editors,
            {
                text : {
                    init : function(container, options) {
                        var input = $(
                                '<input type="text" class="datagrid-editable-input">')
                                .appendTo(container);
                        return input;
                    },
                    getValue : function(target) {
                        return $(target).val();
                    },
                    setValue : function(target, value) {
                        $(target).val(value);
                    },
                    resize : function(target, width) {
                        var input = $(target);
                        if ($.boxModel == true) {
                            input.width(width
                                    - (input.outerWidth() - input
                                            .width()));
                        } else {
                            input.width(width);
                        }
                    }
                }
            });
    //移除验证和还原验证
    $.extend($.fn.validatebox.methods, {  
    remove: function(jq, newposition){  
        return jq.each(function(){  
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');
        });  
    },
    reduce: function(jq, newposition){  
        return jq.each(function(){  
           var opt = $(this).data().validatebox.options;
           $(this).addClass("validatebox-text").validatebox(opt);
        });  
    }   
    }); 
    $.extend($.fn.datagrid.defaults.editors, {
        text: {
            init: function(container, options){
                var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);
                return input;
            },
            getValue: function(target){
                return $(target).val();
            },
            setValue: function(target, value){
                $(target).val(value);
            },
            resize: function(target, width){
                var input = $(target);
                if ($.boxModel == true){
                    input.width(width - (input.outerWidth() - input.width()));
                } else {
                    input.width(width);
                }
            }
        }
    });
    $.extend($.fn.datagrid.defaults.editors, {
        datebox: {
            init: function(container, options){
                var input = $('<input type="text">').appendTo(container);
                input.datebox(options);
                return input;
            },
            destroy: function(target){
                $(target).datebox('destroy');
            },
            getValue: function(target){
                return $(target).datebox('getValue');
            },
            setValue: function(target, value){
                $(target).datebox('setValue', value);
            },
            resize: function(target, width){
                $(target).datebox('resize', width);
            }
        },
        datetimebox: {//datetimebox就是你要自定义editor的名称  
            init: function(container, options){  
                var editor = $('<input />').appendTo(container);  
                editor.enableEdit = false;  
                editor.datetimebox(options);  
                return editor;  
            },  
            getValue: function(target){  
                console.info(target+"222222222");  
                console.info($(target).datetimebox('getValue')+"222222222");  
                var new_str = $(target).datetimebox('getValue').replace(/:/g,'-');  
                new_str = new_str.replace(/ /g,'-');  
                var arr = new_str.split("-");  
                var datum = new Date(Date.UTC(arr[0],arr[1]-1,arr[2],arr[3]-8,arr[4],arr[5]));  
                var timeStamp = datum.getTime();  
                console.info(datum);  
                console.info(datum.getTime());  
                return timeStamp;  
           },  
            setValue: function(target, value){  
                console.info(target+"1111111111");  
                console.info(value+"1111111111");  
                $(target).datetimebox('setValue',new Date(value).format("yyyy-MM-dd hh:mm:ss"));  
            },  
            resize: function(target, width){  
               $(target).datetimebox('resize',width);          
            },  
            destroy: function(target){  
                $(target).datetimebox('destroy');  
            }  
        }  

});
Date.prototype.format = function (format) 
    {
        var o = {
            "M+": this.getMonth() + 1, //month 
            "d+": this.getDate(),    //day 
            "h+": this.getHours(),   //hour 
            "m+": this.getMinutes(), //minute 
            "s+": this.getSeconds(), //second 
            "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
            "S": this.getMilliseconds() //millisecond 
        }
        if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o) if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1,
          RegExp.$1.length == 1 ? o[k] :
            ("00" + o[k]).substr(("" + o[k]).length));
        return format;
}
/**
 * 自定义格式化日期
 */
function formatDatebox(value) {
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