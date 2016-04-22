require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "copper": "/deploy/cropper/js/cropper.min",
        "ckeditor": "/deploy/ckeditor/ckeditor",
        "ztree" : "/deploy/zTree/js/jquery.ztree.core-3.5.min"
    },
    shim: {
        "copper" : ["jquery"],
        "ztree" : ["jquery"]
    },
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    urlArgs: "t=" + (new Date()).getTime()
});

require([
    "jquery",
    "copper",
    "ckeditor",
    "ztree"
], function ($){

    //初始化ckeditor
    var editor = CKEDITOR.replace("getMyEditor", {
        toolbar: [
            ['Styles', 'Format'],
            ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Image', 'Link', '-', 'insertcode', '-', 'saveData', 'About']
        ],
        uiColor: '#3d3d3d',
        height: "600px",
        width: "100%"
    });

    //设置ztree
    var setting = {
        view: {
            //dblClickExpand: false,
            expandSpeed: 100 //设置树展开的动画速度
        },
        //获取json数据
        async : {
            enable : true,
            url : "/deploy/getType.html", // Ajax 获取数据的 URL 地址
            autoParam : [ "id", "name" ] //ajax提交的时候，传的是id值
        },
        data:{ // 必须使用data
            simpleData : {
                enable : true,
                idKey : "id", // id编号命名
                pIdKey : "parentId", // 父id编号命名
                rootPId : 0
            }
        },
        // 回调函数
        callback : {
            onClick : function(event, treeId, treeNode) {
                if(treeNode.id == "111"){
                    alert("对不起，不允许选择根节点作为类型参数");
                    return false;
                }
                $("#tree-name").text(treeNode.name);
                $("#tree-id").text(treeNode.id);
            },
            //捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
            onAsyncSuccess : function(event, treeId, treeNode, msg){
            }
        }
    };

    $(function(){
        $.fn.zTree.init($("#tree"), setting);

        $("#submitForm").click(function(){

            var title = $("#blog-title").val();
            var description = $("#blog-description").val();
            var image = $("#blog-imageUrl").val();
            var typeId = $("#tree-id").text();

            var content = editor.getData();

            if(title == "" || description == "" || image == "" || typeId == "" || content == ""){
                alert("没有填写完，不允许发布!");
                return false;
            }
            if(typeId == "111"){
                alert("类别选择错误！");
                return false;
            }

            var formData = new FormData();
            formData.append("articleTitle", title);
            formData.append("articleDescription", description);
            formData.append("articleImg", image);
            formData.append("typeId", typeId);
            formData.append("articleBody", content);

            var xtr = new XMLHttpRequest();
            xtr.addEventListener("load", saveArticleSuccess, false);
            xtr.open("POST", "/deploy/save.html");
            //发送数据
            xtr.send(formData);

        });

        //打开图片框
        $("#showImageDialog").click(function(){
            showImageDialog();
        });

        //关闭对话框
        $("#closeImageDialog").click(function(){
            closeImageDialog();
        });

        //file框改变事件
        $("#exampleInputFile").change(function(){
            fileSelected();
        });

        //对话框图片上传
        $("#dialogSubmitImg").click(function(){
            submitImg();
        });
    });

    function saveArticleSuccess(event){
        var res = event.target.responseText;
        if(res == "success"){
            window.location = "/index.html";
        }else{
            alert("文章提交失败。");
        }
    }

    function showImageDialog(){
        $("#image-upload-dialog").show();
        $("#image-upload-dialog").css("top", "50%");
        $("#image-upload-dialog").css("margin-top", "-275px");
    }

    function closeImageDialog(){
        $("#image-upload-dialog").hide();
    }

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

    function setImageCanvas(url) {
        $("#getImage").attr("src", url);
        $("#getPreviewImage").attr("src", url);
        $('#getImage').cropper({
            aspectRatio: 1,
            preview: '#image-preview',
            crop: function(e) {
                $("body").data("topx", e.x);
                $("body").data("topy", e.y);
                $("body").data("imgW", e.width);
                $("body").data("imgH", e.height);
            }
        });
    }

    function submitImg(){
        var fd = new FormData();
        var file = document.getElementById('exampleInputFile').files[0];
        if(!file){
            alert('文件不能为空!');
            return false;
        }
        fd.append("upload", file);
        fd.append("topx", $("body").data("topx"));
        fd.append("topy", $("body").data("topy"));
        fd.append("imgW", $("body").data("imgW"));
        fd.append("imgH", $("body").data("imgH"));
        //创建XMLHttpRequest对象， 支持XMLHttpRequest 2.0规则
        var xhr = new XMLHttpRequest();
        //分别用XMLHttpRequest对象监听开始事件，进度事件， 完成事件， 失败事件与取消事件
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        //打开目标Url
        xhr.open("POST", "/deploy/upload.html");
        //发送数据
        xhr.send(fd);
    }

    //进度条事件
    function uploadProgress(evt) {
        if (evt.lengthComputable) {
            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
            //设置进度
            $('#image-bar').css("width", percentComplete + "%");
        }else {
            alert("err");
        }
    }
    //完成事件
    function uploadComplete(evt) {
        var res = evt.target.responseText;
        if(res == "err"){
            return false;
        }
        $("#blog-imageUrl").val(res);
        closeImageDialog();
    }
    //失败事件
    function uploadFailed(evt) {
        alert("不知什么原因，上传文件发生错误.");
    }

});