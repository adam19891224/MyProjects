<!DOCTYPE>
<html>
    <head>
        <title>相信自己，一定能行</title>
        <link rel="stylesheet" type="text/css" href="/deploy/cropper/css/cropper.min.css">
        <link rel="stylesheet" type="text/css" href="/deploy/zTree/css/zTreeStyle/zTreeStyle.css">
        <script type="text/javascript" src="/common/js/require.js" defer async="true" data-main="/deploy/js/main.js"></script>
        <link rel="icon" href="/common/icon/favourite-icon_32X32.ico" mce_href="/resources/common/icon/favourite-icon_32X32.ico" type="image/x-icon">
    </head>
    <body>
        <div style="width: 100%">
            <textarea id="getMyEditorText" style="resize: none;" name="getMyEditor" title=""></textarea>
        </div>
        <div style="width: 100%; margin-top: 50px; text-align: center">
            <h3>标题</h3>
            <input type="text" id="blog-title" style="width: 80%; height: 50px;" title="标题框"/>
        </div>
        <div style="width: 100%; margin-top: 50px; text-align: center">
            <h3>描述</h3>
            <textarea title="描述框" id="blog-description" style="resize: none; width: 80%; height: 200px;" ></textarea>
        </div>
        <div style="width: 100%; margin-top: 50px; text-align: center">
            <h3>配图上传</h3>
            <input type="text" readonly="readonly" id="blog-imageUrl"  title=""/>
            <input type="button" id="showImageDialog" value="点击上传图片" style="width: 100px; height: 40px;" />
        </div>
        <div style="width: 100%; margin-top: 50px; text-align: center">
            <h3>选择类别</h3>
            <ul id="tree" class="ztree" style="display: inline-block; width: 30%"></ul>
            <h4>
                选择的节点是: <span id="tree-name" style="color: orange"></span>
                &nbsp;&nbsp;&nbsp;节点Id: <span id="tree-id" style="color: red"></span>
            </h4>
        </div>
        <div style="width: 100%; margin-top: 50px; text-align: center">
            <input type="button" id="submitForm" value="确认提交" style="width: 100px; height: 40px;" />
        </div>
    </body>
    <div id="image-upload-dialog" style="display:none; width: 600px; height: 600px; border: 2px solid red; background-color: #ebebeb; position: fixed; left: 50%; margin-left: -300px; z-index: 999;">
        <div id="closeImageDialog" style="float:left; width: 50px; height: 30px; line-height: 30px;">
            关闭
        </div>
        <div style="width: 100%; height: auto; overflow: hidden; float:left; text-align: center;">
            <input type="file" id="exampleInputFile" value="上传配图" />
            <div style="width: 100%; height: 400px; margin: 0 auto; margin-top: 20px; border: 1px solid sandybrown;">
                <table style="width: 99%; height: 100%;">
                    <tr>
                        <td align="center" style="width: 50%">
                            <img id="getImage" style="max-width: 100%" src="">
                        </td>
                        <td align="center" style="width: 50%">
                            <div id="image-preview" style="width: 102px; height: 102px; margin: 0 auto;border: 2px solid orange; overflow: hidden">
                                <img src="" id="getPreviewImage" style="max-width: 100%">
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="width: 80%; height: 30px; margin: 0 auto; margin-top: 20px;">
                <input type="button" id="dialogSubmitImg" value="开始上传" style="width: 100px; height: 100%;" />
            </div>
            <div style="width: 80%; height: 30px; margin: 0 auto; margin-top: 20px; border: 1px solid blueviolet;">
                <div id="image-bar" style="height: 100%; width: 0; background-color: #1E90FF">
                </div>
            </div>
        </div>
    </div>
</html>