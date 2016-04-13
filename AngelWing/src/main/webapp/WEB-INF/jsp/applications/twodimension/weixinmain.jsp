<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/include.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="<%=path%>/resources/applications/twodimension/css/main.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
</head>
<body>
<div class="main-body">
    <div class="body-left">
        <div class="left-second">
            <div class="second-title">
                <span>生成微信信息二维码</span>
            </div>
            <div class="second-input">
                <ul>
                    <li>
                        <span>姓名：</span><input id="getWXname" class="easyui-textbox" style="width:50%;height:32px;"/>
                    </li>
                    <li>
                        <span>电话：</span><input id="getWXphone" class="easyui-textbox" style="width:50%;height:32px;"/>
                    </li>
                    <li>
                        <span>个人邮箱：</span><input id="getWXemail" class="easyui-textbox" style="width:50%;height:32px;"/>
                    </li>
                    <li>
                        <span>所属组织：</span><input id="getWXgroup" class="easyui-textbox" style="width:50%;height:32px;"/>
                    </li>
                    <li>
                        <span>所在地：</span><input id="getWXarea" class="easyui-textbox" style="width:50%;height:32px;"/>
                    </li>
                    <li>
                        <span>职业：</span><input id="getWXjob" class="easyui-textbox" style="width:50%;height:32px;"/>
                    </li>
                    <li>
                        <span>个人博客：</span><input id="getWXblog" class="easyui-textbox" style="width:50%;height:32px;"/>
                    </li>
                    <li>
                        <span>备注：</span><input id="getWXremark" class="easyui-textbox" style="width:50%;height:32px;"/>
                    </li>
                </ul>
            </div>
            <div class="second-button">
                <a href="javascript:" id="showWXbutton" style="outline: none;" class="easyui-linkbutton">
                    生成二维码
                </a>
            </div>
        </div>
    </div>
    <div class="body-right">
        <div id="showQRcode" class="right-body"></div>
    </div>

    <div id="dlg" class="easyui-dialog" title="警告" data-options="
                    iconCls:'icon-no',
                    buttons: [{
                        text:'确定',
                        iconCls:'icon-ok',
                        handler:function(){
                            $('#dlg').dialog('close');
                        }
					}]" style="width:400px;height:auto;padding:10px;">
        内容不能为空
    </div>
</div>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/resources/applications/twodimension/js/jquery.qrcode.min.js"></script>
<script type="text/javascript">
    $().ready(function () {
        $("#dlg").dialog("close");

        $("#showWXbutton").click(function () {
            $("#showQRcode").empty();
            //得到各项数据
            var name = $("#getWXname").val();
            var phone = $("#getWXphone").val();
            var email = $("#getWXemail").val();
            var group = $("#getWXgroup").val();
            var area = $("#getWXarea").val();
            var job = $("#getWXjob").val();
            var blog = $("#getWXblog").val();
            var remark = $("#getWXremark").val();
            //拼接微信信息的字符串
            var info = "BEGIN:VCARD\n" +
                    "VERSION:3.0\n" +
                    "N:" + name + "\n" +
                    "EMAIL:" + email + "\n" +
                    "TEL:" + phone + "\n" +
                    "ADR:" + group + "\n" +
                    "ORG:" + area + "\n" +
                    "TITLE:" + job + "\n" +
                    "URL:" + blog + "\n" +
                    "NOTE:" + remark + "\n" +
                    "END:VCARD\n";

            info = toUtf8(info);
            //调用qrcode生成二维码
            $("#showQRcode").qrcode(info);
        });
    });

    //    var a = "BEGIN:VCARD\n" +
    //            "VERSION:3.0\n" +
    //            "N:李德伟\n" +
    //            "EMAIL:1606841559@qq.\n" +
    //            "TEL:12345678912\n" +
    //            "TEL;CELL:12345678912\n" +
    //            "ADR:山东济南齐鲁软件园\n" +
    //            "ORG:济南\n" +
    //            "TITLE:软件工程师\n" +
    //            "URL:http://blog.csdn.net/lidew521\n" +
    //            "NOTE:呼呼测试下吧。。。\n" +
    //            "END:VCARD\n";

    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for (i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    }
</script>
</body>
</html>
