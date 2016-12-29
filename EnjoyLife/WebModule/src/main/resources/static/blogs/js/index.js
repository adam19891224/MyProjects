function Blogs(){

    var replyEidtorDiv = "<div id=\"reply-editor-div\" class=\"reply-editor\"><textarea id=\"reply-main-editor\" cols=\"1\" rows=\"1\" class='reply-main-editor' title=\"回复\"></textarea></div>";
    var replyButtonDiv = "<div id=\"reply-buttons-div\" class=\"reply-buttons\"><span id=\"reply-error-msg\" class=\"error-message\"></span><a id='submit-reply-button' class=\"submit-reply-button\">回复</a></div>";
    var commentEditor, replyEditor;
    var isLoadingComment = false;

    this.initPage = function () {
        //渲染评论编辑器
        if(commentEditor == null){
            commentEditor = CKEDITOR.replace("comment-main-editor", { height: "160px"});
        }
        loadingComment(1, true);
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
            _thisL = _this.parents("li");
            var l = _this.attr("data-l");
            if(l == "0"){
                _this.attr("data-l", "1");
                var type = _this.data("type");
                //如果type不是y，则加载数据
                if(type != "y"){
                    loadingReplyCommentsByDiv(_thisL, _this);
                }else{
                    //判断当前是否有样式, 如果没有，则说明是展开操作，则把颜色变为蓝色，字修改为收起
                    if(_this.hasClass("show-color")){
                        //有，则移除样式
                        _this.removeClass("show-color").text("展开");
                        _thisL.find(".reply-container").fadeOut("fast");
                    }else{
                        //没有，则展开，同时修改颜色
                        _this.addClass("show-color").text("收起");
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
                    alert("评论内容不能为空!");
                    $("#comment-error-msg").text("评论内容不能为空!");
                }else{
                    //将文本放入容器
                    $("#whole-div").data("comment", data);
                    //同时标注此条评论是主评论
                    $("#whole-div").data("isReply", 0);
                    $("#whole-div").data("replyUser", "");
                    $("#whole-div").data("replyBody", "");
                    $("#whole-div").show();
                }
                _this.data("lock", "0");
            }
        });
    };

    this.replySubmitButtonClick = function () {
        var _this;
        $("#articlt-comment-div").on("click", "#submit-reply-button", function () {
            _this = $(this);
            var lock = _this.data("lock");
            if(lock != "1"){
                _this.data("lock", "1");
                $("#reply-error-msg").text("");
                var content = replyEditor.document.getBody().getText();
                var data = replyEditor.getData();
                content = applications.replaceBlock(content);
                if(!applications.isNotNull(data) || !applications.isNotNull(content)){
                    alert("回复内容不能为空!");
                    $("#reply-error-msg").text("回复内容不能为空!");
                }else{
                    //将文本放入容器
                    $("#whole-div").data("comment", data);
                    //同时标注此条评论是回复评论
                    $("#whole-div").data("isReply", 1);
                    //同时填写回复人的名称和评论id
                    var _thisP = _this.parents("li");
                    $("#whole-div").data("replyUser", _thisP.find(".mian-name").find("span").first().text());
                    $("#whole-div").data("replyBody", _thisP.attr("data-id"));
                    $("#whole-div").show();
                }
                _this.data("lock", "0");
            }
        });
    };

    this.dataSubmitClick = function () {
        $("#to-submit").click(function () {
            var name = $("#friend-name").val();
            var email = $("#friend-email").val();
            var type = $("#friend-sitetype").val();
            var site = $("#friend-website").val();
            if(!applications.isNotNull(name)){
                alert("请大牛输入昵称");
                return false;
            }
            if(!applications.isNotNull(email)){
                alert("请大牛输入邮箱");
                return false;
            }
            var obj = {};
            var isReply = $("#whole-div").data("isReply");
            var dataId = $("#whole-div").data("replyBody");
            obj.commentUser = name;

            if(!applications.checkIsEmail(email)){
                alert("请大牛输入正确的邮箱地址");
                return false;
            }
            obj.commentEmail = email;

            if(applications.isNotNull(site) && !applications.checkIsSite(site)){
                alert("请大牛输入正确的网站地址");
                return false;
            }

            site = site.replace("http://", "");
            site = site.replace("https://", "");
            if(type == "http"){
                site = "http://" + site;
            }else if(type == "https"){
                site = "https://" + site;
            }

            obj.commentUserWebsite = site;

            $("#whole-div").hide();
            $("#loading-div").show();

            obj.articleId = $("#article-body").attr("data-aid");
            obj.commentBody = $("#whole-div").data("comment");
            obj.commentIsReply = isReply;
            obj.commentReplyUser = $("#whole-div").data("replyUser");
            obj.commentReplyBody = dataId;
            obj.ck = $("#articlt-comment-div").attr("ck");
            $.post('/blogs/postComment.html', obj, function (text) {
                if(text != "success"){
                    if(text == "error"){
                        alert("提交评论失败");
                    }else{
                        alert("拒绝访问");
                    }
                }
                window.location = location;
            });
        });
    };

    this.dataCancleClick = function () {
        $("#to-cancle").click(function () {
            $("#whole-div").data("comment", "").hide();
        });
    };

    this.websiteClick = function () {
        var site = "";
        $("#articlt-comment-div").on("click", ".has-website", function () {
            site = $(this).attr("data-w");
            if(site.indexOf("http://") >= 0 || site.indexOf("https://") >= 0){
                window.open(site);
            }else{
                window.open("http://" + site);
            }
        });
    };

    var loadingReplyCommentsByDiv = function (li, span) {
        //获取这个评论的id和文章id
        var lineDiv = li.find(".main-line");
        lineDiv.animate({width: "60%"}, 500, function () {
            lineDiv.animate({width: "70%"}, 1000, function () {
                var obj = {};
                obj.articleId = $("#article-body").attr("data-aid");
                obj.commentId = li.attr("data-id");
                obj.commentIsReply = 1;
                obj.pagination = false;
                $.post("/blogs/getComment.html", obj, function (res) {
                    var result = eval("(" + res + ")");
                    var isOk = result.isOk;
                    var lis = "";
                    if(isOk == "Y"){
                        var list = result.list;
                        lis = getReplysLis(list);
                    }else{
                        lis = "<li class='reply-no-datas'>没有回复数据</li>";
                    }
                    lineDiv.animate({width: "100%"}, function () {
                        li.find(".reply-container").empty().append(lis).fadeIn("fast");
                        span.data("type", "y").addClass("show-color").text("收起");
                        lineDiv.css("width", "0");
                    });
                });
            });
        });

    };

    var loadingComment = function (page, init) {
        //清空现有的评论列表
        $("#articlt-comment-div").find(".comment-container").empty().append("<li class=\"comment-loading\"><i class=\"loading-bar\"></i></li>");
        var obj = {};
        obj.isReply = 0;
        obj.articleId = $("#article-body").attr("data-aid");
        obj.commentIsReply = 0;
        obj.page = page;

        $.ajax({
            url: "/blogs/getComment.html",
            data: obj,
            type: "post",
            timeout: 5000,
            complete:function(XHR,TextStatus){
                if(TextStatus=='timeout'){
                    $("#articlt-comment-div").find(".comment-container").empty().append("<li class=\"comment-no-data\"><span>超时了。。。</span></li>");
                    isLoadingComment = false;
                }
            },
            success: function (text) {
                var result = eval("(" + text + ")");
                var isOk = result.isOk;
                var lis = "";
                if(isOk == "Y"){
                    // var counts = result.totalCounts;
                    var pages = result.totalPages;
                    var current = result.current;
                    var list = result.list;
                    lis = getCommentLis(list);
                    if(init && current < pages){
                        createPageLine(pages, current);
                    }
                }else{
                    lis = "<li class=\"comment-no-data\"><span>暂无评论~~~~(>_<)~~~~</span></li>";
                }
                $("#articlt-comment-div").find(".comment-container").empty().append(lis);
                isLoadingComment = false;
            }
        });
    };

    var getCommentLis = function (list) {
        var tempO, lis = "", time = "", span = "";
        for(var a = 0, b = list.length; a < b; a++){
            tempO = list[a];
            time = new Date(tempO.createDate).Format("yyyy-MM-dd");
            var site = tempO.commentUserWebsite;
            if(applications.isNotNull(site) && applications.checkIsSite(site)){
                span = "<span class='has-website' data-w='" + site + "'>" + tempO.commentUser + "：</span><span>" + time + "</span>";
            }else{
                span = "<span>" + tempO.commentUser + "：</span><span>" + time + "</span>";
            }
            lis += "<li data-id='" + tempO.commentId + "'>" +
                        "<div class='comment-left'>" +
                            "<div class='left-head'></div>" +
                        "</div>" +
                        "<div class='comment-right'>" +
                            "<div class='right-main'>" +
                                "<div class='mian-name'>" +
                                    span +
                                "</div>" +
                                "<div class='main-content'>" +
                                    tempO.commentBody +
                                "</div>" +
                                "<div class='main-reply'>" +
                                    "<span class='to-reply' data-l='0'>回复</span><span class='to-show' data-l='0'>展开</span>" +
                                "</div>" +
                                "<div class='main-line'></div>" +
                                "<div class='main-reply-container'>" +
                                    "<ul class='reply-container' load='0'>" +
                                    "</ul>" +
                                "</div>" +
                            "</div>" +
                        "</div>" +
                   "</li>";
        }
        return lis;
    };

    var createPageLine = function (count, current) {
        $("#comment-pages").empty();
        $("#comment-pages").createPage({
            pageCount: count,
            current: current,
            backFn: function(page, eve, obj){
                if(!isLoadingComment){
                    isLoadingComment = true;
                    eve.fillHtml(obj, {"current": page,"pageCount": count});
                    loadingComment(page, false);
                }
            }
        });
    };

    var getReplysLis = function (list) {
        var lis = "", time = "";
        var tempO;
        for(var a = 0, b = list.length; a < b; a++){
            tempO = list[a];
            time = new Date(tempO.createDate).Format("yyyy-MM-dd");
            var site = tempO.commentUserWebsite;
            if(applications.isNotNull(site) && applications.checkIsSite(site)){
                span = "<span class='has-website' data-w='" + site + "'>" + tempO.commentUser + "：</span><span>" + time + "</span>";
            }else{
                span = "<span>" + tempO.commentUser + "：</span><span>" + time + "</span>";
            }
            lis += "<li>" +
                        "<h5>" +
                            span +
                        "</h5>" +
                        "<div class=\"reply-content\">" +
                            tempO.commentBody +
                        "</div>" +
                   "</li>";
        }
        return lis;
    }

}

var blogs = new Blogs();

$(function () {
    initBlog();
});

function initBlog() {
    blogs.initPage();
    blogs.commentReplyButtonClick();
    blogs.commentShowButtonClick();
    blogs.commentSubmitButtonClick();
    blogs.replySubmitButtonClick();
    blogs.dataSubmitClick();
    blogs.dataCancleClick();
    blogs.websiteClick();

    $("#header-nav-ul li").eq(3).addClass("header-underline").siblings().removeClass("header-underline");
}