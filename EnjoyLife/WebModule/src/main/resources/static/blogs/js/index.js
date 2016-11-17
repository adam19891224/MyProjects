var categorys = new CategoryUtils();

$(function () {
    categorys.init($("#type-body"));

    blogs.initPage();
    blogs.commentReplyButtonClick();
    blogs.commentShowButtonClick();
    blogs.commentSubmitButtonClick();
    blogs.dataSubmitClick();
    blogs.dataCancleClick();
});

function Blogs(){

    var replyEidtorDiv = "<div id=\"reply-editor-div\" class=\"reply-editor\"><textarea id=\"reply-main-editor\" cols=\"1\" rows=\"1\" title=\"回复\"></textarea></div>";
    var replyButtonDiv = "<div id=\"reply-buttons-div\" class=\"reply-buttons\"><a class=\"submit-reply-button\">回复</a></div>";
    var commentEditor, replyEditor;

    this.initPage = function () {
        //渲染评论编辑器
        commentEditor = CKEDITOR.replace("comment-main-editor", { height: "160px"});
    };

    this.commentReplyButtonClick = function () {
        var _this, _thisL;
        $("#articlt-comment-div").on("click", ".to-reply", function () {
            _this = $(this);
            var l = _this.attr("data-l");
            if(l == "0"){
                _this.attr("data-l", "1");
                _thisL = _this.parents("li");
                if(_thisL.find("#reply-editor-div").length == 0 &&
                    _thisL.find("#reply-buttons-div").length == 0){
                    //移除其他地方的编辑器和按钮层
                    $("#reply-editor-div").remove();
                    $("#reply-buttons-div").remove();
                    //移除蓝色颜色字体
                    $(".reply-color").removeClass("reply-color");
                    _this.addClass("reply-color");
                    //在当前点击的评论中添加编辑器
                    _thisL.find(".main-reply-container").append(replyEidtorDiv).append(replyButtonDiv);
                    //创建编辑器
                    replyEditor = CKEDITOR.replace("reply-main-editor", { height: "100px"});
                }
                _this.attr("data-l", "0");
            }
        });
    };

    this.commentShowButtonClick = function () {
        var _this, _thisL;
        $("#articlt-comment-div").on("click", ".to-show", function () {
            _this = $(this);
            var l = _this.attr("data-l");
            if(l == "0"){
                _this.attr("data-l", "1");
                var type = _this.data("type");
                //如果type不是y，则加载数据
                if(type != "y"){
                    _thisL = _this.parents("li");
                    loadReplyCommentsByDiv(_thisL, _this);
                }else{
                    //判断当前是否有样式, 如果没有，则说明是展开操作，则把颜色变为蓝色，字修改为收起
                    if(_this.hasClass("reply-color")){
                        //有，则移除样式
                        _this.removeClass("reply-color").text("展开");
                        _thisL.find(".reply-container").fadeOut("fast");
                    }else{
                        //没有，则展开，同时修改颜色
                        _this.addClass("reply-color").text("收起");
                        _thisL.find(".reply-container").fadeIn("fast");
                    }
                }
                _this.attr("data-l", "0");
            }
        });
    };

    this.commentSubmitButtonClick = function () {
        var _this;
        $("#comment-submit").click(function () {
             _this = $(this);
            var lock = _this.data("lock");
            if(lock != "1"){
                _this.data("lock", "1");
                $("#comment-error-msg").text("");
                var content = commentEditor.document.getBody().getText();
                var data = commentEditor.getData();
                content = applications.replaceBlock(content);
                if(!applications.isNotNull(data) || !applications.isNotNull(content)){
                    alert("回复内容不能为空!");
                    $("#comment-error-msg").text("回复内容不能为空!");
                }else{
                    //将文本放入容器
                    $("#whole-div").data("comment", data);
                    $("#whole-div").show();
                }
                _this.data("lock", "0");
            }
        });
    };

    this.dataSubmitClick = function () {
        $("#to-submit").click(function () {
            $("#whole-div").hide();
            $("#loading-div").show();
            var name = $("#firden-name").val();
            var email = $("#firden-email").val();
            var site = $("#firden-website").val();
            if(!applications.isNotNull(name)){
                alert("请大牛输入昵称");
                return false;
            }
            if(!applications.isNotNull(email)){
                alert("请大牛输入邮箱");
                return false;
            }
            var obj = {};
            obj.commentUser = name;
            obj.commentEmail = email;
            obj.commentUserWebsite = site;
            obj.articleId = $("#article-body").attr("data-aid");
            obj.commentBody = $("#whole-div").data("comment");
            obj.commentIsReply = 0;
            $.post('/blogs/postComment.html', obj, function (text) {
                if(text == "success"){

                }else{
                    alert(text);
                }
                $("#loading-div").hide();
            });
        });
    };

    this.dataCancleClick = function () {
        $("#to-cancle").click(function () {
            $("#whole-div").data("comment", "").hide();
        });
    };

    var loadReplyCommentsByDiv = function (li, span) {
        span.data("type", "y").addClass("reply-color").text("收起");
    };

    var loadingComment = function (page) {
        var obj = {};
        obj.isReply = 0;
        obj.articleId = "";
    };

}

var blogs = new Blogs();