<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>网络配置</title>
        <style type="text/css">
            fieldset{
                display: block;
                float: left;
            }
            form{
                display: block;
            }
            form div{
                clear: both;
            }
            form fieldset p{
                margin-top: 10px;
            }
            form fieldset div{
                padding-top: 10px;
                padding-bottom: 10px;
            }
            form fieldset div{
                margin:0 auto;
            }
            form fieldset div a{
                margin:0 auto;
                display: block;
            }
            form fieldset p span{
                width: 60px;
                display: inline-block;
                text-align: center;
                margin-right: 10px;
            }
            #netconfigTop * {
                float: left;
                width: 23%;
                height: 40%;
                margin-top: 15%;
                margin-left: 5%;
                cursor: pointer;
            }
            #netconfigTop {
                position: relative;
                width: 750px;
                height: 400px;
                margin-top: -50px;
                margin-left: auto;
                margin-right: auto;
                background-image: url(easyui/img.jpg);
            }
        </style>
    </head>
    <body>
        <!-- 该页面整个dom都依赖的js -->
        <script type="text/javascript">
            // 给String的对象添加一个去空格的方法
            String.prototype.trim = function() {
                return this.replace(/(^\s*)|(\s*$)/g, '');
            };
            //FormValidator类，用来验证表单。
            //_url提交地址
            //_frm，form元素
            function FormValidator(_url, _frm) {
                this.url = _url;
                this.frm = _frm;
            }
            ;
            //获取根据_valName这个表单元素的value。
            FormValidator.prototype.getVal = function(_valName) {
                return this.frm[0][_valName].value;
            };
            //验证IP地址的方法
            //_val是元素的name
            //_label是元素的说明(只包含名称)
            //如果验证通过，返回空字符串(空字符串会自动转为true)。如果不通过，返回错误信息，同时将url情况，防止提交到后台。
            FormValidator.prototype.isIp = function(_val, _label) {
                var _err = "";
                _val = this.getVal(_val);
                if (_val) {
                    if (!FormValidator.ipPattern.test(_val.trim())) {
                        this.url = "";
                        _err += (_label + "不是合法的IP    ");
                    }
                } else {
                    this.url = "";
                    _err += (_label + "不能为空    ");
                }
                return _err;
            };
            //验证是否为空的方法
            FormValidator.prototype.isNotEmpty = function(_val, _label) {
                var _err = "";
                _val = this.getVal(_val);
                if (_val && _val !== '请选择' && _val != -1) {
                    if (_val.trim()) {
                    } else {
                        this.url = "";
                        _err += (_label + "不能为空    ");
                    }
                } else {
                    this.url = "";
                    _err += (_label + "不能为空    ");
                }
                return _err;
            };
            //IP地址正则表达式
            FormValidator.ipPattern = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
            //相当于main方法
            onload = function() {
                //初始化id=netconfigTop按钮，让它能保存
                NetConfigNav.init();
                //隐藏网闸的div

                $('#exchangeServerContext').hide();
                //隐藏内服务器的div
                //初始化内服务器对象，使上面的功能可以运行
                $('#outServerContext').hide();
                $('#inServerContext').hide();
            };</script>
        <!-- 导航部分(事件源部分) -->
        <div id="netconfigTop">
            <script type="text/javascript">
                //NetConfigNav对象(导航模块)封装和id为netconfigTop有关的细节
                var NetConfigNav = {};
                //隐藏所有图片对应的div
                NetConfigNav.hideAll = function() {
                    $('#outServerContext').hide();
                    $('#exchangeServerContext').hide();
                    $('#inServerContext').hide();
                };
                //让这个对象上的所有动作都可以保存
                //所有init方法只在刷新的时候调用
                NetConfigNav.init = function() {
                    NetConfigNav.addClickListener();
                    $('#netconfigTop div').css({'border': '0px', 'z-index': '-999', 'opacity': '0.1'});
                };
                //给导航器绑定事件。
                NetConfigNav.addClickListener = function() {
                    $('#netconfigTop div').bind({
                        //event封装了事件的信息
                        click: function(event) {
                            //如果该浏览器不支持event，就是window.event。
                            //该写法相当于三元运算符，是js内行的风格。
                            //_e=event?event:window.event。第一个event转为布尔类型，
                            var _e = (event || window.event);
                            //e.target表示事件源。
                            //将js对象转为jquery对象。
                            var source = $(_e.target);
                            //source对象触发事件后，引起target对象发生一些动作，如显示，隐藏。
                            var target = $('#' + source.attr('id') + 'Context');
                            //点击source对象之后，如果target对象是显示的，则隐藏。如果是隐藏的，就显示
                            if (target.is(':visible')) {
                                NetConfigNav.hideAll();
                            } else {
                                NetConfigNav.hideAll();
                                target.show();
                                ExchangeServer.init();
                                InServer.init();
                                OutServer.init();
                            }
                        },
                        //鼠标悬浮和移走，都会改变透明度等样式
                        mouseover: function(event) {
                            var _e = (event || window.event);
                            var source = $(_e.target);
                            source.css({'border': '3px solid grey', 'z-index': '999', 'opacity': '0.3'});
                        },
                        mouseout: function(event) {
                            var _e = (event || window.event);
                            var source = $(_e.target);
                            source.css({'border': '0px', 'z-index': '-999', 'opacity': '0.1'});
                            source.html('');
                        }
                    });
                };</script>
            <!-- 这三个div作为click等事件的触发器(按钮)，注意命名，通过命名和下面的div联系起来，才可以互动。 -->
            <div id="outServer" style="background-color: maroon;opacity:0.1"></div>
            <div id="exchangeServer" style="background-color: orange;opacity:0.1"></div>
            <div id="inServer" style="background-color: blue;opacity:0.1"></div>
        </div>

        <!-- 外服务器事件源的响应事件在这个div中 ，风格是easy ui的tab-->
        <div id="outServerContext" class="easyui-tabs" >
            <script type="text/javascript">
                //该对象封装了与inServerContext相关的操作
                var OutServer = {cardList: [], dmo: {}};
                OutServer.loadCardList = function() {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/networkCfg/getCardListFromRmi',
                        async: false,
                        type: 'get',
                        success: function(_data) {
                            OutServer.cardList = _data;
                        },
                        dataType: 'json'
                    });
                };
                OutServer.loadServerFromDB = function() {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/networkCfg/getOutServerFromDB',
                        async: false,
                        type: 'get',
                        success: function(_data) {
                            OutServer.dmo = _data;
                        },
                        dataType: 'json'
                    });
                };
                OutServer.initCombox = function() {
                    var $inCard = $('#outServerSelectedGapCardName');
                    $inCard.html('');
                    $inCard.append('<option value="-1">请选择</option>');
                    for (var i = 0; i < OutServer.cardList.length; i++) {
                        $inCard.append('<option value=' + OutServer.cardList[i].name + '>' + OutServer.cardList[i].name + '</option>');
                    }
                    var $outCard = $('#outServerSelectedOutCardName');
                    $outCard.html('');
                    $outCard.append('<option value="-1">请选择</option>');
                    for (var i = 0; i < OutServer.cardList.length; i++) {
                        $outCard.append('<option value=' + OutServer.cardList[i].name + '>' + OutServer.cardList[i].name + '</option>');
                    }
                };
                OutServer.bindCombox = function() {
                    var _inCombox = $('#outServerSelectedGapCardName');
                    _inCombox.change(function() {
                        for (var i = 0; i < OutServer.cardList.length; i++) {
                            var card = OutServer.cardList[i];
                            if (card.name === _inCombox.val()) {
                                $('#outServerGapIp').val(card.ip);
                                $('#outServerGapMask').val(card.mask);
                                $('#outServerGapGateway').val(card.gateway);
                                return;
                            }
                        }
                        $('#outServerGapIp').val('');
                        $('#outServerGapMask').val('');
                        $('#outServerGapGateway').val('');
                    });
                    var _outCombox = $('#outServerSelectedOutCardName');
                    _outCombox.change(function() {
                        for (var i = 0; i < OutServer.cardList.length; i++) {
                            var card = OutServer.cardList[i];
                            if (card.name === _outCombox.val()) {
                                $('#outServerOutIp').val(card.ip);
                                $('#outServerOutMask').val(card.mask);
                                $('#outServerOutGateway').val(card.gateway);
                                return;
                            }
                        }
                        $('#outServerOutIp').val('');
                        $('#outServerOutMask').val('');
                        $('#outServerOutGateway').val('');
                    });
                };
                OutServer.loadForFrm = function() {
                    for (var i = 0; i < OutServer.cardList.length; i++) {
                        var card = OutServer.cardList[i];
                        if (card.name === OutServer.dmo.selectedGapCardName) {
                            $('#outServerSelectedGapCardName').val(card.name);
                            $('#outServerGapIp').val(OutServer.dmo.gapIp);
                            $('#outServerGapMask').val(OutServer.dmo.gapMask);
                            $('#outServerGapGateway').val(OutServer.dmo.gapGateway);
                        }
                        if (card.name === OutServer.dmo.selectedOutCardName) {
                            $('#outServerSelectedOutCardName').val(card.name);
                            $('#outServerOutIp').val(OutServer.dmo.outIp);
                            $('#outServerOutMask').val(OutServer.dmo.outMask);
                            $('#outServerOutGateway').val(OutServer.dmo.outGateway);
                        }
                    }
                    $('#outServerNetName').val(OutServer.dmo.netName);
                    $('#outServerProxyInPort').val(OutServer.dmo.proxyInPort);
                    $('#outServerProxyOutPort').val(OutServer.dmo.proxyOutPort);
                    $('#outServerServicePort').val(OutServer.dmo.servicePort);
                };
                OutServer.updateGapCard = function() {
                    var validator = new FormValidator('${pageContext.request.contextPath}/networkCfg/updateOutGapCard', $('#form_outer'));
                    var error = validator.isNotEmpty('selectedGapCardName', '对内网卡') + validator.isIp('gapIp', '对网闸IP') + validator.isIp('gapMask', '对网闸掩码');
                    if (error) {
                        $.messager.show({
                            title: '提示信息',
                            msg: error,
                            showType: 'show'
                        });
                        return;
                    }
                    var dto = {name: validator.getVal('selectedGapCardName'), ip: validator.getVal('gapIp'), mask: validator.getVal('gapMask'), gateway: validator.getVal('gapGateway')};

                    $.ajax({
                        url: validator.url,
                        type: 'post',
                        async: false,
                        dataType: 'json',
                        data: dto
                    });
                    $.messager.show({
                        title: '提示信息',
                        msg: '更新成功',
                        showType: 'show'
                    });
                };
                OutServer.updateServiceCard = function() {
                    var validator = new FormValidator('${pageContext.request.contextPath}/networkCfg/updateOutServiceCard', $('#form_outer'));
                    var error = validator.isNotEmpty('selectedOutCardName', '对外网卡') + validator.isIp('outIp', '对外IP') + validator.isIp('outMask', '对外掩码');
                    if (error) {
                        $.messager.show({
                            title: '提示信息',
                            msg: error,
                            showType: 'show'
                        });
                        return;
                    }
                    var dto = {name: validator.getVal('selectedOutCardName'), ip: validator.getVal('outIp'), mask: validator.getVal('outMask'), gateway: validator.getVal('outGateway')};
                    $.ajax({
                        url: validator.url,
                        type: 'post',
                        async: false,
                        dataType: 'json',
                        data: dto
                    });
                    $.messager.show({
                        title: '提示信息',
                        msg: '更新成功',
                        showType: 'show'
                    });
                };
                OutServer.updatePort = function() {
                    var validator = new FormValidator('${pageContext.request.contextPath}/networkCfg/updateOutPort', $('#form_outer'));
                    var error = validator.isNotEmpty('selectedGapCardName', '对网闸网卡') + validator.isIp('gapIp', '对网闸IP') + validator.isIp('gapMask', '对网闸掩码');
                    error = error + validator.isNotEmpty('selectedOutCardName', '对外网卡') + validator.isIp('outIp', '对外IP') + validator.isIp('outMask', '对外掩码');
                    error = error + validator.isNotEmpty('netName', '机器名') + validator.isNotEmpty('proxyInPort', '内通道端口') + validator.isNotEmpty('proxyOutPort', '外通道端口') + validator.isNotEmpty('servicePort', '业务端口');
                    if (error) {
                        $.messager.show({
                            title: '提示信息',
                            msg: error,
                            showType: 'show'
                        });
                        return;
                    }
                    $.ajax({
                        url: validator.url,
                        async: false,
                        type: 'post',
                        dataType: 'json',
                        data: $('#form_outer').serializeArray()
                    });
                    $.messager.show({
                        title: '提示信息',
                        msg: '更新成功',
                        showType: 'show'
                    });
                };
                OutServer.init = function() {
                    OutServer.loadCardList();
                    OutServer.loadServerFromDB();
                    OutServer.initCombox();
                    OutServer.bindCombox();
                    OutServer.loadForFrm();
                };</script>
            <div title="外服务器配置" data-options="iconCls:'icon-edit',fit:true">
                <form id="form_outer" name="form_outer">
                    <fieldset>
                        <legend>网卡(内)</legend>
                        <p>
                            <span>网卡</span>
                            <select name="selectedGapCardName" id="outServerSelectedGapCardName"></select><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>IP</span>
                            <input name="gapIp" id="outServerGapIp"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>掩码</span>
                            <input name="gapMask" id="outServerGapMask"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>网关</span>
                            <input name="gapGateway" id="outServerGapGateway"/>
                        </p>
                        <div>
                            <a href="javascript:OutServer.updateGapCard()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" >保存</a>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>网卡(外)</legend>
                        <p>
                            <span>网卡</span>
                            <select name="selectedOutCardName" id="outServerSelectedOutCardName"></select><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>IP</span>
                            <input name="outIp" id="outServerOutIp"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>掩码</span>
                            <input name="outMask" id="outServerOutMask"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>网关</span>
                            <input name="outGateway" id="outServerOutGateway"/>
                        </p>
                        <div>
                            <a href="javascript:OutServer.updateServiceCard()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" >保存</a>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>端口</legend>
                        <p>
                            <span>机器名</span>
                            <input name="netName" id="outServerNetName"/>
                        </p>
                        <p>
                            <span>内通道端口</span>
                            <input name="proxyInPort" id="outServerProxyInPort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>外通道端口</span>
                            <input name="proxyOutPort" id="outServerProxyOutPort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>业务端口</span>
                            <input name="servicePort" id="outServerServicePort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <div>
                            <a href="javascript:OutServer.updatePort()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" >保存</a>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>

        <!-- 网闸 事件源的响应事件在这个div中 ，风格是easy ui的tab -->
        <div id="exchangeServerContext" class="easyui-tabs" >
            <script type="text/javascript">
                //定义一个对象
                var ExchangeServer = {};
                //定义对象的方法
                ExchangeServer.init = function() {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/networkCfg/getGapInfoFromDB',
                        async: false,
                        type: 'get',
                        success: function(_data) {
                            $('#form_exchangeServer').form('load', _data);
                        },
                        dataType: 'json'
                    });
                };
                ExchangeServer.updateIp = function() {
                    var validator = new FormValidator('${pageContext.request.contextPath}/networkCfg/updateNetGapIp', $('#form_exchangeServer'));
                    var error = validator.isNotEmpty('inName', '对内网卡') + validator.isIp('inIp', '对内IP') + validator.isNotEmpty('outName', '对外网卡') + validator.isIp('outIp', '对外IP');
                    if (error) {
                        $.messager.show({
                            title: '提示信息',
                            msg: error,
                            showType: 'show'
                        });
                        return;
                    }
                    $.ajax({
                        url: validator.url,
                        type: 'post',
                        async: false,
                        dataType: 'json',
                        data: $('#form_exchangeServer').serializeArray()
                    });
                    $.messager.show({
                        title: '提示信息',
                        msg: '保存成功.',
                        showType: 'show'
                    });
                };
                ExchangeServer.updatePort = function() {
                    var validator = new FormValidator('${pageContext.request.contextPath}/networkCfg/updateNetGapPort', $('#form_exchangeServer'));
                    var error = validator.isNotEmpty('proxyOutPort', '外通道端口') + validator.isNotEmpty('proxyInPort', '内通道端口') + validator.isNotEmpty('servicePort', '业务端口');
                    if (error) {
                        $.messager.show({
                            title: '提示信息',
                            msg: error,
                            showType: 'show'
                        });
                        return;
                    }
                    $.ajax({
                        url: validator.url,
                        type: 'post',
                        dataType: 'json',
                        async: false,
                        data: $('#form_exchangeServer').serializeArray()
                    });
                    $.messager.show({
                        title: '提示信息',
                        msg: '保存成功.',
                        showType: 'show'
                    });
                };</script>
            <div title="网闸配置" data-options="iconCls:'icon-edit',fit:true">
                <form id="form_exchangeServer" name="form_exchangeServer">
                    <fieldset>
                        <legend>网卡</legend>
                        <p>
                            <span>对内网卡</span>
                            <input name="inName" /><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>对内IP</span>
                            <input name="inIp" /><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>对外网卡</span>
                            <input name="outName" /><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>对外IP</span>
                            <input name="outIp" /><font color="red"><b>*</b></font>
                        </p>
                        <div>
                            <a href="javascript:ExchangeServer.updateIp()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" >保存</a>    
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>端口</legend>
                        <p>
                            <span>外通道端口</span>
                            <input name="proxyOutPort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>内通道端口</span>
                            <input name="proxyInPort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>业务端口</span>
                            <input name="servicePort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <div>
                            <a href="javascript:ExchangeServer.updatePort()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" >保存</a>    
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>

        <!-- 内服务器事件源的响应事件在这个div中 ，风格是easy ui的tab -->
        <div id="inServerContext" class="easyui-tabs" >
            <script type="text/javascript">
                //该对象封装了与inServerContext相关的操作
                var InServer = {};
                InServer.cardList = [];
                InServer.dmo = {};
                InServer.loadCardList = function() {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/networkCfg/getCardListFromServer',
                        async: false,
                        type: 'get',
                        success: function(_data) {
                            InServer.cardList = _data.list;
                        },
                        dataType: 'json'
                    });
                };
                InServer.loadInServerFromDB = function() {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/networkCfg/getInServerFromDB',
                        async: false,
                        type: 'get',
                        success: function(_data) {
                            InServer.dmo = _data;
                        },
                        dataType: 'json'
                    });
                };
                InServer.initCombox = function() {
                    var $inCard = $('#inServerInCardName');
                    $inCard.html('');
                    $inCard.append('<option value="-1">请选择</option>');
                    for (var i = 0; i < InServer.cardList.length; i++) {
                        $inCard.append('<option value=' + InServer.cardList[i].name + '>' + InServer.cardList[i].name + '</option>');
                    }
                    $inCard = $('#inServerOutCardName');
                    $inCard.html('');
                    $inCard.append('<option value="-1">请选择</option>');
                    for (var i = 0; i < InServer.cardList.length; i++) {
                        $inCard.append('<option value=' + InServer.cardList[i].name + '>' + InServer.cardList[i].name + '</option>');
                    }
                };
                InServer.bindCombox = function() {
                    var _inCombox = $('#inServerInCardName');
                    _inCombox.change(function() {
                        for (var i = 0; i < InServer.cardList.length; i++) {
                            var card = InServer.cardList[i];
                            if (card.name === _inCombox.val()) {
                                $('#inServerServiceIp').val(card.ip);
                                $('#inServerServiceMask').val(card.mask);
                                $('#inServerServiceGateway').val(card.gateway);
                                return;
                            }
                        }
                        $('#inServerServiceIp').val('');
                        $('#inServerServiceMask').val('');
                        $('#inServerServiceGateway').val('');
                    });
                    var _outCombox = $('#inServerOutCardName');
                    _outCombox.change(function() {
                        for (var i = 0; i < InServer.cardList.length; i++) {
                            var card = InServer.cardList[i];
                            if (card.name === _outCombox.val()) {
                                $('#inServerGapIp').val(card.ip);
                                $('#inServerGapMask').val(card.mask);
                                $('#inServerGapGateway').val(card.gateway);
                                return;
                            }
                        }
                        $('#inServerGapIp').val('');
                        $('#inServerGapMask').val('');
                        $('#inServerGapGateway').val('');
                    });
                };
                InServer.loadForFrm = function() {
                    for (var i = 0; i < InServer.cardList.length; i++) {
                        var card = InServer.cardList[i];
                        if (card.name === InServer.dmo.inCardName) {
                            $('#inServerInCardName').val(card.name);
                            $('#inServerServiceIp').val(InServer.dmo.serviceIp);
                            $('#inServerServiceMask').val(InServer.dmo.serviceMask);
                            $('#inServerServiceGateway').val(InServer.dmo.serviceGateway);
                        }
                        if (card.name === InServer.dmo.outCardName) {
                            $('#inServerOutCardName').val(card.name);
                            $('#inServerGapIp').val(InServer.dmo.gapIp);
                            $('#inServerGapMask').val(InServer.dmo.gapMask);
                            $('#inServerGapGateway').val(InServer.dmo.gapGateway);
                        }
                    }
                    $('#inServerHostName').val(InServer.dmo.hostName);
                    $('#inServerProxyInPort').val(InServer.dmo.proxyInPort);
                    $('#inServerProxyOutPort').val(InServer.dmo.proxyOutPort);
                    $('#inServerServiceInPort').val(InServer.dmo.serviceInPort);
                    $('#inServerServiceOutPort').val(InServer.dmo.serviceOutPort);
                };
                InServer.updateInServiceCard = function() {
                    var validator = new FormValidator('${pageContext.request.contextPath}/networkCfg/updateInServiceCard', $('#form_in'));
                    var error = validator.isNotEmpty('inCardName', '对内网卡') + validator.isIp('serviceIp', '对内IP') + validator.isIp('serviceMask', '对内掩码');
                    if (error) {
                        $.messager.show({
                            title: '提示信息',
                            msg: error,
                            showType: 'show'
                        });
                        return;
                    }
                    var dto = {name: validator.getVal('inCardName'), ip: validator.getVal('serviceIp'), mask: validator.getVal('serviceMask'), gateway: validator.getVal('serviceGateway')};
                    $.ajax({
                        url: validator.url,
                        async: false,
                        type: 'post',
                        dataType: 'json',
                        data: dto
                    });
                    $.messager.show({
                        title: '提示信息',
                        msg: '更新成功',
                        showType: 'show'
                    });
                };
                InServer.updateInGapCard = function() {
                    var validator = new FormValidator('${pageContext.request.contextPath}/networkCfg/updateInGapCard', $('#form_in'));
                    var error = validator.isNotEmpty('outCardName', '对外网卡') + validator.isIp('gapIp', '对网闸IP') + validator.isIp('gapMask', '对网闸掩码');
                    if (error) {
                        $.messager.show({
                            title: '提示信息',
                            msg: error,
                            showType: 'show'
                        });
                        return;
                    }
                    var dto = {name: validator.getVal('outCardName'), ip: validator.getVal('gapIp'), mask: validator.getVal('gapMask'), gateway: validator.getVal('gapGateway')};
                    $.ajax({
                        url: validator.url,
                        type: 'post',
                        async: false,
                        dataType: 'json',
                        data: dto
                    });
                    $.messager.show({
                        title: '提示信息',
                        msg: '更新成功',
                        showType: 'show'
                    });
                };
                InServer.updatePort = function() {
                    var validator = new FormValidator('${pageContext.request.contextPath}/networkCfg/updateInPort', $('#form_in'));
                    var error = validator.isNotEmpty('outCardName', '对外网卡') + validator.isIp('gapIp', '对网闸IP') + validator.isIp('gapMask', '对网闸掩码');
                    error = error + validator.isNotEmpty('inCardName', '对内网卡') + validator.isIp('serviceIp', '对内IP') + validator.isIp('serviceMask', '对内掩码');
                    error = error + validator.isNotEmpty('hostName', '机器名') + validator.isNotEmpty('proxyInPort', '内通道端口') + validator.isNotEmpty('proxyOutPort', '外通道端口') + validator.isNotEmpty('serviceInPort', '内业务端口') + validator.isNotEmpty('serviceOutPort', '外业务端口');
                    if (error) {
                        $.messager.show({
                            title: '提示信息',
                            msg: error,
                            showType: 'show'
                        });
                        return;
                    }
                    $.ajax({
                        url: validator.url,
                        type: 'post',
                        async: false,
                        dataType: 'json',
                        data: $('#form_in').serializeArray()
                    });
                    $.messager.show({
                        title: '提示信息',
                        msg: '更新成功',
                        showType: 'show'
                    });
                };
                InServer.init = function() {
                    InServer.loadCardList();
                    InServer.loadInServerFromDB();
                    InServer.initCombox();
                    InServer.bindCombox();
                    InServer.loadForFrm();
                };

            </script>
            <div title="内服务器配置" data-options="iconCls:'icon-edit',fit:true">
                <form id="form_in" name="form_in">
                    <fieldset>
                        <legend>网卡(内)</legend>
                        <p>
                            <span>网卡</span>
                            <select name="inCardName" id="inServerInCardName"></select><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>IP</span>
                            <input name="serviceIp" id="inServerServiceIp"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>掩码</span>
                            <input name="serviceMask" id="inServerServiceMask"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>网关</span>
                            <input name="serviceGateway" id="inServerServiceGateway"/>
                        </p>
                        <div>
                            <a href="javascript:InServer.updateInServiceCard()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" >保存</a>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>网卡(外)</legend>
                        <p>
                            <span>网卡</span>
                            <select name="outCardName" id="inServerOutCardName"></select><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>IP</span>
                            <input name="gapIp" id="inServerGapIp"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>掩码</span>
                            <input name="gapMask" id="inServerGapMask"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>网关</span>
                            <input name="gapGateway" id="inServerGapGateway"/>
                        </p>
                        <div>
                            <a href="javascript:InServer.updateInGapCard()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" >保存</a>
                        </div>
                    </fieldset>
                    <fieldset>
                        <legend>端口</legend>
                        <p>
                            <span>机器名</span>
                            <input name="hostName" id="inServerHostName"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>内通道端口</span>
                            <input name="proxyInPort" id="inServerProxyInPort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>外通道端口</span>
                            <input name="proxyOutPort" id="inServerProxyOutPort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>内业务端口</span>
                            <input name="serviceInPort" id="inServerServiceInPort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <p>
                            <span>外业务端口</span>
                            <input name="serviceOutPort" id="inServerServiceOutPort" class="easyui-numberbox"/><font color="red"><b>*</b></font>
                        </p>
                        <div>
                            <a href="javascript:InServer.updatePort()" class="easyui-linkbutton" data-options="iconCls:'icon-save'" >保存</a>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </body>
</html>