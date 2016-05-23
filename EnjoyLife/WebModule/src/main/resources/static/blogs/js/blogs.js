require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "cookie": "/base/js/jquery.cookie",
        "lazyload": "/base/js/jquery.lazyload.min",
        "common": "/base/js/common",
        "ck": "/blogs/ckeditor/ckeditor"
    },
    shim: {
        "lazyload" : ["jquery"]
    },
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    urlArgs: "t=" + (new Date()).getTime()
});

require([
    "jquery",
    "cookie",
    "lazyload",
    "common",
    "ck"
], function ($){

    var common = new Common();
    common.searchButtonMouseListener();
    common.showNavigatorBar(1);

    //创建ck
    CKEDITOR.replace(
        "commentEditor",
        {
            height: "120px",
            resize_enabled: false,
            toolbar:
                [
                    [ "Undo", "Smiley" ]
                ],
            removePlugins: "elementspath"
        }
    );

    var blogUtils = new BolgUtils();
    blogUtils.init();
    blogUtils.offsetScroll();
});

function BolgUtils(){

    this.init = function(){
        commentHelperListener();
        customerInfoButtonListener();
        submitCommentButtonClick();
    };

    this.offsetScroll = function(){
        var scrollHeight, divHeight, documentHeight = $(window).height(), win;
        var commentObj = $("#article-comment");
        var thisObj = this;
        $(window).scroll(function(){
            win = $(this);
            divHeight = commentObj.offset().top;
            if(divHeight < documentHeight){
                thisObj.loadComment();
            }else {
                scrollHeight = win.scrollTop();
                if(divHeight - scrollHeight < 0){
                    thisObj.loadComment();
                }
            }
        });
    };

    this.loadComment = function(){
        var aO = $("#article");
        var allow = aO.attr("allow");
        if(allow == "Y"){
            aO.attr("allow", "N");
            $("#article-comment").data("page", 1);
            this.loadCommentById(aO.attr("aid"));
        }
    };

    this.loadCommentById = function(aid){
        data = {};
        data.articleId = aid;
        data.pageSize = 10;
        data.page = $("#article-comment").data("page");
        $("#comment-loading").show();
        $.ajax({
            url : "/blogs/getComment.html",
            type : "post",
            data : data,
            timeout : 5000,
            success : function(res){
                res = eval("(" + res + ")");
                //保存新的page
                var list = res.resultList;
                var totalPage = res.totalPages;
                var page = res.page;
                $("#main").data("page", page);
                if(page >= totalPage){
                    //最后一页
                    $("#main").data("page", totalPage);
                }else{

                }
                if(page <= 1){
                    $("#main").data("page", 1);
                }else{

                }
                $("#comment-loading").hide();

                var li = "";
                if(list.length > 0){
                    li = addCommentLi(list);
                }else{
                    //则提示没有数据
                    li = "<li><h3>目前还没有评论，快块来挽尊吧~~~~(>_<)~~~~</h3></li>";
                }
                $("#comment-main-ul").show().append(li);
            },
            error : function(res){
                console.log(res);
            },
            complete : function(XMLHttpRequest, status){
                if(status=='timeout'){

                }
            }
        });

    };

    /**
     * 信息资料框按钮事件
     */
    var customerInfoButtonListener = function(){
        $("#cancle-info").click(function(){
            $(this).parents("#get-customer-info").hide();
        });
        $("#submit-info").click(function(){
            var obj = $("#get-customer-info");
            var data = {};
            //获取传递过来的值
            var isReply = obj.data("isReply");
            if(isReply){
                data.commentReplyUser = obj.data("replyUser");
            }
            data.commentIsReply = isReply;
            data.commentBody = obj.data("commentBody");

            var reg = /^(<[^><]+>|\s|&nbsp)+$/;
            var username = obj.find("#username").val();
            if(username == "" || reg.test(username)){
                alert("请填写您的称呼");
                return false;
            }
            data.commentUser = username;
            var useremail = obj.find("#useremail").val();

            if(useremail == "" || reg.test(useremail)){
                alert("请填写您的邮箱，放心，本人不会公开该信息");
                return false;
            }
            var emailReg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
            if(!emailReg.test(useremail)){
                alert("请填写正确的邮箱格式");
                return false;
            }
            data.commentEmail = useremail;

            data.commentUserWebsite = obj.find("#usereweb").val();

            data.articleId = $("#article").attr("aid");

            $.ajax({
                url : "/blogs/saveComment.html",
                data : data,
                type : "post",
                timeout : 5000,
                success : function(res){
                    res = eval("(" + res + ")");
                    if(res == "success"){

                    }
                },
                error : function(res){
                },
                complete : function(XMLHttpRequest, status){
                    if(status=='timeout'){

                    }
                }
            });
        });
    };

    /**
     * 评论间按钮事件
     */
    var commentHelperListener = function(){
        $("#comment-main-ul").on("click", ".reply", function(){
            var _this = $(this);
            var name = "text" + _this.parent().attr("comment");
            if(_this.attr("show") == "N"){
                _this.attr("show", "Y").text("取消").parents("li").find(".comment-reply-div").show().find("textarea").attr("name", name);
                CKEDITOR.replace(
                    name,
                    {
                        height: "80px",
                        resize_enabled: false,
                        toolbar:
                            [
                                [ "Smiley" ]
                            ],
                        removePlugins: "elementspath"
                    }
                );
            }else if(_this.attr("show") == "Y"){
                _this.attr("show", "N").text("回复").parents("li").find(".comment-reply-div").hide();
            }
        });

        $("#comment-main-ul").on("click", ".open", function(){
            var _this = $(this);
            var li = _this.parents("li");
            var ul = li.find("ul");
            if(_this.attr("show") == "N"){
                ul.show();
                if(_this.attr("load") == "Y"){
                    var bar = li.find(".comment-reply-bar");
                    var commentId = _this.parent().attr("commentId");
                    var data = {
                        commentId : commentId,
                        articleId : $("#article").attr("aid"),
                        commentIsReply : 1
                    };

                    bar.animate({width : "60%"}, function(){
                        //ajax
                        $.ajax({
                            url : "/blogs/getReplyComment.html",
                            data : data,
                            type : "post",
                            timeout : 5000,
                            success : function(res){
                                res = eval("(" + res + ")");
                                var list = res.resultList;
                                if(list.length > 0){
                                    bar.animate({width : "80%"}, function(){
                                        var replyLi = addReplyCommentLi(list);
                                        ul.append(replyLi);
                                    });
                                }else{
                                    bar.animate({width : "100%"}, function(){
                                        ul.append("<li><h5>没有回复记录</h5></li>");
                                        bar.css("width", "0%");
                                    });
                                }
                                _this.attr("load","N").attr("show", "Y").text("收起");
                            },
                            error : function(res){
                                bar.animate({width : "100%"}, function(){
                                    bar.css("width", "0%");
                                });
                            },
                            complete : function(XMLHttpRequest, status){
                                if(status=='timeout'){
                                    bar.animate({width : "100%"}, function(){
                                        ul.append("<li><h5>超时了，请重新加载</h5></li>");
                                        bar.css("width", "0%");
                                    });
                                }
                            }
                        });
                    });
                }else{
                    _this.attr("load","N").attr("show", "Y").text("收起");
                }
            }else if(_this.attr("show") == "Y"){
                ul.hide();
                _this.attr("show", "N").text("展开");
            }
        });

        $("#comment-main-ul").on("click", ".submit-comment-reply", function(){
            var _this = $(this);
            var commentSid = _this.parents("li").find(".comment-helper").attr("comment");
            var owner = _this.attr("owner");
            var name = "text" + commentSid;
            var text = CKEDITOR.instances[name].document.getBody().getText();
            var reg = /^(<[^><]+>|\s|&nbsp)+$/;
            if(text == "" || reg.test(text)){
                _this.parent().find("span").show();
                return false;
            }else{
                _this.parent().find("span").hide();
            }
            var obj = $("#get-customer-info");
            obj.data("isReply", true);
            obj.data("commentBody", text);
            obj.data("replyUser", owner);
            obj.show();
        });
    };

    /**
     * 回复文章按钮事件
     */
    var submitCommentButtonClick = function(){
        $("#submit-comment").click(function(){
            var _this = $(this);
            var text = CKEDITOR.instances.commentEditor.document.getBody().getText();
            var reg = /^(<[^><]+>|\s|&nbsp)+$/;
            if(text == "" || reg.test(text)){
                _this.parent().find("span").show();
                return false;
            }else{
                _this.parent().find("span").hide();
            }
            var obj = $("#get-customer-info");
            obj.data("isReply", false);
            obj.data("commentBody", text);
            obj.show();
        });
    };

    /**
     * 拼接评论
     */
    var addCommentLi = function(list){
        var obj, li = "", date;
        for(var a = 0, b = list.length; a < b; a++){
            obj = list[a];
            var href = "javascript:";
            var clazz = "";
            if(obj.commentUserWebsite != ""){
                href = obj.commentUserWebsite;
                clazz = "line"
            }
            date = new Date(obj.createDate);
            li += "<li>" +
                    "<h6>" +
                        "<a href='" + href + "' class='" + clazz + "'>" + obj.commentUser + "</a>&nbsp;说：<span>" + date.format("yyyy-MM-dd") + "</span>" +
                    "</h6>" +
                    "<p>" +
                        obj.commentBody +
                    "</p>" +
                    "<h6 class=\"comment-helper\" comment=\"" + obj.commentSid + "\" commentId='" + obj.commentId + "'>" +
                        "<a class=\"reply\" show=\"N\">回复</a>" +
                        "<a class=\"open\" show=\"N\" load=\"Y\">展开</a>" +
                    "</h6>" +
                    "<div class=\"comment-reply-bar\"></div>" +
                    "<ul class=\"comment-reply-ul\"></ul>" +
                    "<div class=\"comment-reply-div\">" +
                        "<div class=\"comment-reply-editor\">" +
                            "<textarea name=\"comment-reply-editor\" title=\"回复文章\"></textarea>" +
                        "</div>" +
                        "<div class=\"comment-reply-submit\">" +
                            "<span>" +
                                "对不起，文本内容不能为空！" +
                            "</span>" +
                            "<a class=\"submit-comment-reply\" owner=\"123123\">" +
                                "提交" +
                            "</a>" +
                        "</div>" +
                    "</div>" +
                  "</li>";
        }
        return li;
    };

    /**
     * 拼接回复评论
     */
    var addReplyCommentLi = function(list){
        var obj, li = "", date;
        for(var a = 0, b = list.length; a < b; a++){
            obj = list[a];
            li += "<li>" +
                    "<h6>" +
                        "<span>SSSS</span><span>回复</span><span>XXXX</span>" +
                    "</h6>" +
                    "<p>" +
                        "按时大大大哇的" +
                    "</p>" +
                   "</li>";
        }
        return li;
    };
}