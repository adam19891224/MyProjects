<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="<%=path%>/resources/applications/article/highlight/css/monokai_sublime.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/applications/article/css/article.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/resources/applications/article/cropper/css/cropper.min.css">
</head>
<body class="easyui-layout">
<div id="content" border="false" region="center">
    <div id="p" class="easyui-panel" title="写博客" style="width:100%;height:100%;padding:10px;">
        <div id="content-left">
            <!--标题-->
            <div id="left-title">
                <div class="title-left">
                    <span>标题</span>
                </div>
                <div class="title-right">
                    <input id="articleTitle" class="easyui-textbox" data-options="prompt:'请输入博文标题...'" style="width:95%;height:28px;">
                </div>
            </div>
            <!--描述-->
            <div id="left-description">
                <div class="title-left">
                    <span>主要描述</span>
                </div>
                <div class="title-right">
                    <input id="articleDescription" class="easyui-textbox" data-options="prompt:'请输入博文描述...'" style="width:95%;height:28px;">
                </div>
            </div>
            <div id="left-main">
                <div class="title-left">
                    <span>主要内容</span>
                </div>
                <%--<div class="title-right">--%>
                <%--<input class="easyui-textbox" body="true" data-options="multiline:true" style="width:95%;height:400px">--%>
                <%--</div>--%>
                <script type="text/javascript"
                        src="<%=path%>/resources/applications/article/ckeditor/ckeditor.js"></script>
                <div class="title-right">
                    <textarea id="getMyEditorText" rows="30" cols="65" style="resize: none;"
                              name="getMyEditor"></textarea>
                </div>
            </div>
            <div id="left-button">
                <a id="submitButton" href="javascript:" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交保存</a>
            </div>
        </div>
        <div id="content-right" style="color: black;">
            <article></article>
        </div>
    </div>
</div>
<div id="saveDialog" class="easyui-dialog" title="保存文章" style="width:800px;height:auto;"
     data-options="iconCls:'icon-save',resizable:true,modal:true">
    <div class="typeContent">
        <div class="typeContent-left">
            <span>选择类别</span>
        </div>
        <div class="typeContent-right">
            <select id="typeSelect" class="easyui-combotree" style="width:200px;">
            </select>
        </div>
    </div>
    <div class="typeContent">
        <div class="typeContent-left">
            <span>选择配图</span>
        </div>
        <div class="typeContent-right">
            <input type="file" name="file" onchange="fileSelected();" id="exampleInputFile">
        </div>
    </div>
    <div class="typeImage">
        <div class="image-title">
            &nbsp;&nbsp;
            图片名称： <span id="image-name" style="color: red;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图片大小： <span
                id="image-size" style="color: orange;"></span>
            <a id="uploadFile" href="javascript:" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交图片</a>
        </div>
        <div class="image-canvas">
            <table style="width: 99%; height: 100%;">
                <tr>
                    <td align="center" style="width: 50%">
                        <img id="getImage" style="max-width: 100%">
                    </td>
                    <td align="center" style="width: 50%">
                        <div id="image-preview" style="width: 102px; height: 102px; margin: 0 auto;border: 2px solid orange; overflow: hidden">
                            <img id="getPreviewImage" style="max-width: 100%">
                        </div>
                    </td>
                </tr>
            </table>
            <div id="getImageBar" class="easyui-progressbar" data-options="value:0" style="width:400px; height: 30px; margin: 50px auto 0; display: none;"></div>
        </div>
        <div class="image-submit">
            <a id="submitBolg" href="javascript:" style="margin-right: 50px; float:right;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存博客</a>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/easyui-formatter.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/easyui-formatter.js"></script>
<script type="text/javascript" src="<%=path%>/resources/applications/article/highlight/js/highlight.pack.js"></script>
<script type="text/javascript" src="<%=path%>/resources/applications/article/markdown/js/marked.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/applications/article/cropper/js/cropper.min.js"></script>
<script type="text/javascript">

    //初始化highlight控件
    hljs.initHighlightingOnLoad();

    function reback() {
        window.location = "<%=path%>/applications/article/list.html";
    }

    //初始化ckeditor
    var editor = CKEDITOR.replace("getMyEditor", {
        toolbar: [
            ['Styles', 'Format'],
            ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Image', 'Link', '-', 'insertcode', '-', 'saveData', 'About']
        ],
        uiColor: '#3d3d3d',
        height: "300px",
        width: "470px"
    });

    CKEDITOR.on('instanceReady', function (e) {
        if (e.editor.document.$.addEventListener)
            e.editor.document.$.addEventListener('keyup', keydown, false);
        else if (e.editor.document.$.attachEvent)
            e.editor.document.$.attachEvent('onkeyup', function (e) {
                keydown(e)
            });
//        if(e.editor.document.$.addEventListener)
//            e.editor.document.$.addEventListener('keyup',keydown, false);
    });

    function keydown(e) {
        var content = editor.getData();
//        content = marked(content);
        $("#content-right").find("article").html(content);
        //调用渲染highlight的方法
        showHighlight();
    }

    function showHighlight() {
        $("pre code").each(function (i, block) {
            hljs.highlightBlock(block);
        });
    }

    $(function () {
        $("#saveDialog").dialog("close");

        $("#submitButton").click(function () {
            $("#saveDialog").dialog("open");
            $('#typeSelect').combotree({
                url: '<%=path%>/applications/article/getType.html',
                required: true
            });
            var file = $("#saveDialog").find("#exampleInputFile");
            file.after(file.clone().val(""));
            file.remove();
        });

        $("#uploadFile").click(function(){
            //上传图片
            uploadFile();
        });

        //点击保存按钮
        $("#submitBolg").click(function(){

            //得到标题
            var title = $("#articleTitle").textbox("getValue");
            if(title == ""){
                $.messager.alert('信息','标题不能为空!','error');
                return false;
            }
            //得到描述
            var description = $("#articleDescription").textbox("getValue");
            if(description == ""){
                $.messager.alert('信息','描述不能为空!','error');
                return false;
            }
            //得到内容
            var content = editor.getData();
            if(content == ""){
                $.messager.alert('信息','内容不能为空!','error');
                return false;
            }
            //得到选择类型
            var t = $('#typeSelect').combotree('tree');	// get the tree object
            var n = t.tree('getSelected');
            if(!n){
                $.messager.alert('信息','文章类型不能为空!','error');
                return false;
            }
            var typeId = n.id;

            subimtObject.articleTitle = title;
            subimtObject.articleDesc = description;
            subimtObject.articleBody = content;
            subimtObject.typeID = typeId;
            if(!subimtObject.articleImage){
                $.messager.alert('信息','文章配图不能为空!','error');
                return false;
            }

            $.post(
                    "<%=path%>/applications/article/save.html",
                    subimtObject,
                    function(result){
                        if(result == "success"){
                            $.messager.alert('信息','添加博文成功!','info');
                            window.location = "<%=path%>/applications/article/list.html";
                        }
                    }
                );

        });

    });


    // 文件检测
    function fileSelected() {
        var file = document.getElementById('exampleInputFile').files[0];
        if (file) {
            var fileSize = 0;
            var fileName = "";
            if (file.size > 1024 * 1024) {
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100);
                if (fileSize > 30) {
                    alert("上传图片大于10MB,请压缩后再上传");
                    return false;
                }
            } else {
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
                fileName = file.name;
                $("#image-name").text(fileName);
                $("#image-size").text(fileSize);
            }
            //采用Html5来制作裁剪
            var reader = new FileReader();
            reader.onload = function () {
                // 通过 reader.result 来访问生成的 DataURL
                var url = reader.result;
                setImageCanvas(url);
            };
            reader.readAsDataURL(file);
        }
    }

    var topx;
    var topy;
    var imgW;
    var imgH;

    function setImageCanvas(url) {
        $("#getImage").attr("src", url);
        $("#getPreviewImage").attr("src", url);
        $('#getImage').cropper({
//            aspectRatio: 16 / 9,
            aspectRatio: 1,
            preview: '#image-preview',
            crop: function(e) {
                // Output the result data for cropping image.
                topx = e.x;
                topy = e.y;
                imgW = e.width;
                imgH = e.height;
//                console.log(e.x);
//                console.log(e.y);
//                console.log(e.width);
//                console.log(e.height);
//                console.log(e.rotate);
//                console.log(e.scaleX);
//                console.log(e.scaleY);
            }
        });
    }


    /**
     * 裁剪
     * @param topx 图片裁剪起始坐标x
     * @param topy 图片裁剪起始坐标Y
     * @param imgW 裁剪图片范围匡的宽
     * @param imgH 裁剪图片范围匡的高
     * @returns {boolean}
     */
    function uploadFile(){
        var fd = new FormData();
        var file = document.getElementById('exampleInputFile').files[0];
        if(!file){
            $.messager.alert('信息','文件不能为空!','error');
            return false;
        }
        fd.append("file", file);
        fd.append("topx", topx);
        fd.append("topy", topy);
        fd.append("imgW", imgW);
        fd.append("imgH", imgH);
        //创建XMLHttpRequest对象， 支持XMLHttpRequest 2.0规则
        var xhr = new XMLHttpRequest();
        //分别用XMLHttpRequest对象监听开始事件，进度事件， 完成事件， 失败事件与取消事件
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("loadstart", uploadStart, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        //打开目标Url
        xhr.open("POST", "<%=path%>/applications/article/uploadFile.html");
        //发送数据
        xhr.send(fd);
    }

    //开始事件
    function uploadStart(evt){
        //typeImage的table 隐藏
        $(".typeImage").find(".image-canvas").find("table").css("display", "none");
        //typeImage的进度条显示
        $("#getImageBar").show();
    }

    //进度条事件
    function uploadProgress(evt) {
        if (evt.lengthComputable) {
            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
            //设置进度
            $('#getImageBar').progressbar('setValue', percentComplete);
        }else {
            alert("err");
        }
    }
    //完成事件
    function uploadComplete(evt) {
        var res = eval("(" + evt.target.responseText + ")");
        if(res == "err"){
            return;
        }
        //将filepath传递给subimtObject对象
        subimtObject.articleImage = res;
        alert(res);
    }
    //失败事件
    function uploadFailed(evt) {
        alert("There was an error attempting to upload the file.");
    }
    //取消事件
    function uploadCanceled(evt) {
        alert("The upload has been canceled by the user or the browser dropped the connection.");
    }


    var subimtObject = new Object();

</script>
</body>
</html>


<%--<%@ page language="java" contentType="text/html; charset=utf-8"--%>
<%--pageEncoding="utf-8" %>--%>
<%--<%@ include file="/include.jsp" %>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8">--%>
<%--<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">--%>
<%--<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">--%>
<%--<link rel="stylesheet" type="text/css" href="<%=path%>/resources/applications/article/highlight/css/monokai_sublime.css">--%>
<%--<link rel="stylesheet" type="text/css" href="<%=path%>/resources/applications/article/css/article.css">--%>
<%--</head>--%>
<%--<body class="easyui-layout">--%>
<%--<div id="content" border="false" region="center">--%>
<%--<div id="p" class="easyui-panel" tools="#tt" title="写博客" style="width:100%;height:100%;padding:10px;">--%>
<%--<div id="content-left">--%>
<%--<!--标题-->--%>
<%--<div id="left-title">--%>
<%--<div class="title-left">--%>
<%--<span>标题</span>--%>
<%--</div>--%>
<%--<div class="title-right">--%>
<%--<input class="easyui-textbox" data-options="prompt:'请输入博文标题...'" style="width:95%;height:28px;">--%>
<%--</div>--%>
<%--</div>--%>
<%--<!--描述-->--%>
<%--<div id="left-description">--%>
<%--<div class="title-left">--%>
<%--<span>主要描述</span>--%>
<%--</div>--%>
<%--<div class="title-right">--%>
<%--<input class="easyui-textbox" data-options="prompt:'请输入博文描述...'" style="width:95%;height:28px;">--%>
<%--</div>--%>
<%--</div>--%>
<%--<div id="left-main">--%>
<%--<div class="title-left">--%>
<%--<span>主要内容</span>--%>
<%--</div>--%>
<%--<div class="title-right">--%>
<%--<input class="easyui-textbox" body="true" data-options="multiline:true" style="width:95%;height:400px">--%>
<%--</div>--%>
<%--&lt;%&ndash;<script type="text/javascript" src="<%=path%>/resources/applications/article/ckeditor/ckeditor.js"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<div class="title-right">&ndash;%&gt;--%>
<%--&lt;%&ndash;<textarea rows="25" cols="65" style="resize: none;" name="getMyEditor"></textarea>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script type="text/javascript">&ndash;%&gt;--%>
<%--&lt;%&ndash;CKEDITOR.replace("getMyEditor");&ndash;%&gt;--%>
<%--&lt;%&ndash;</script>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--</div>--%>
<%--<div id="left-button">--%>
<%--<a href="javascript:" style="outline: none;float: right; margin-right: 5%; margin-top: 10px;"--%>
<%--onclick="add();" class="easyui-linkbutton"--%>
<%--data-options="iconCls:'icon-save'">保存博文</a>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div id="content-right" style="color: black;">--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div id="tt">--%>
<%--<a href="javascript:void(0)" class="icon-back" onclick="reback();"></a>--%>
<%--<a href="javascript:void(0)" class="icon-save" onclick="javascript:alert('add')"></a>--%>
<%--</div>--%>
<%--<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>--%>
<%--<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>--%>
<%--<script type="text/javascript" src="<%=path%>/easyui/easyui-formatter.js"></script>--%>
<%--<script type="text/javascript" src="<%=path%>/easyui/easyui-formatter.js"></script>--%>
<%--<script type="text/javascript" src="<%=path%>/resources/applications/article/highlight/js/highlight.pack.js"></script>--%>
<%--<script type="text/javascript" src="<%=path%>/resources/applications/article/markdown/js/marked.min.js"></script>--%>
<%--<script type="text/javascript">--%>

<%--//初始化highlight控件--%>
<%--hljs.initHighlightingOnLoad();--%>

<%--function reback() {--%>
<%--window.location = "<%=path%>/applications/article/list.html";--%>
<%--}--%>

<%--$().ready(function(){--%>

<%--$(".textbox-text").keyup(function(){--%>
<%--var body = $(this).parents(".title-right").find(".easyui-textbox").attr("body");--%>
<%--if(body == "true"){--%>
<%--var content = $(this).val();--%>
<%--content = marked(content);--%>
<%--$("#content-right").html(content);--%>
<%--//调用渲染highlight的方法--%>
<%--showHighlight();--%>
<%--}--%>
<%--});--%>
<%--});--%>

<%--function showHighlight(){--%>
<%--$("pre code").each(function(i, block){--%>
<%--hljs.highlightBlock(block);--%>
<%--});--%>
<%--}--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>