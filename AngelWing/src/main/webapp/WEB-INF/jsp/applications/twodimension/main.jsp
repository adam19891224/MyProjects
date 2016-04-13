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
        <div class="left-frist">
            <div class="frist-title">
                <span>请输入要生成二维码的内容</span>
            </div>
            <div class="frist-input">
                <input id="getText" class="easyui-textbox" style="width:80%;height:32px;margin-top: 20px;"/>
            </div>
            <div class="frist-button">
                <a href="javascript:" id="showButton" style="outline: none;" class="easyui-linkbutton">
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

        $("#showButton").click(function () {
            //清空二维码图片div
            $("#showQRcode").empty();
            //得到输入的内容
            var text = $("#getText").val();
            if (text == "" || text == null || text.match(" ")) {
                $("#dlg").dialog("open");
                return false;
            }
            //调用qrcode生成二维码
            $("#showQRcode").qrcode(text);
        });
    });

</script>
</body>
</html>
