$(function () {

    $("#login-button").click(function () {
        var login = $("#login-body");
        $.ajax({
            url: "/doLogin.html",
            data: {
                "userName": login.find("input").first().val(),
                "password": login.find("input").last().val()
            },
            type: "post",
            success: function (res) {
                if(res == "notBlank"){
                    alert("请填写用户名和密码");
                }
                if(res == "success"){
                    window.location = "/blogs/index.html";
                }
                alert(res);
            }
        });
    });

    var editor = CKEDITOR.replace(
        "blog-text-editor",
        { height: '500px', width: '100%' }
    );

    $("#submitButton").click(function () {
        var file = document.getElementById("uploadTagFile").files[0];
        if(file == undefined){
            alert("请先选择图片");
            return false;
        }
        doUpload(file);
    });

    $("#submit-blog-button").click(function () {
        var title = $("#getTitle").val();
        var url = $(this).data("tagUrl");
        var content = editor.getData();
        var desc = $("#getDescr").val();
        if(title == "" || title == null || title.length < 1){
            alert("请编辑标题");
            return false;
        }
        if(content == "" || content == null || content.length < 1){
            alert("请编辑内容");
            return false;
        }
        if(url == "" || url == null || url.length < 1){
            alert("请上传配图");
            return false;
        }
        if(desc == "" || desc == null || desc.length < 1){
            alert("请填写描述");
            return false;
        }
        var obj = {};
        obj.articleTitle = title;
        obj.articleBody = content;
        obj.articleImg = url;
        obj.articleDescription = desc;
        $.ajax({
            url: "/blogs/save.html",
            type: "post",
            data: obj,
            success: function (res) {
                
            }
        })
    });
});

var topx;
var topy;
var imgW;
var imgH;

function toScanImg() {

    var file = document.getElementById('uploadTagFile').files[0];
    if (file) {
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

function setImageCanvas(url) {
    $("#getView").attr("src", url);
    $("#getScanView").attr("src", url);
    $('#getView').cropper({
//            aspectRatio: 16 / 9,
        aspectRatio: 1,
        preview: '#preview-image',
        crop: function (e) {
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


function doUpload(file) {
    var fd = new FormData();
    if (!file) {
        alert("文件不能为空");
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
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    //打开目标Url
    xhr.open("POST", "/blogs/uploadTag.html");
    //发送数据
    xhr.send(fd);
}


//进度条事件
function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        //设置进度
        $('#process-bar').css('width', percentComplete + "%");
    } else {
        alert("err");
    }
}
//完成事件
function uploadComplete(evt) {
    var res = evt.target.responseText;
    if (res == "err") {
        return;
    }
    alert("上传完毕");
    $('#process-bar').css('width', 0);
    $("#submit-blog-button").data("tagUrl", res);
}
//失败事件
function uploadFailed(evt) {
    alert("There was an error attempting to upload the file.");
}
//取消事件
function uploadCanceled(evt) {
    alert("The upload has been canceled by the user or the browser dropped the connection.");
}