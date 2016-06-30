require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "cookie": "/base/js/jquery.cookie",
        "lazyload": "/base/js/jquery.lazyload.min",
        "common": "/base/js/common",
        "jTempletes": "/blogs/js/jquery-jtemplates",
        "jTempletesUn": "/blogs/js/jquery-jtemplates_uncompressed",
        "ck": "/blogs/commentEditor/ckeditor",
        "hots": "/base/js/hots"
    },
    shim: {
        "lazyload" : ["jquery"],
        "cookie" : ["jquery"],
        "jTempletes" : ["jquery"],
        "jTempletesUn" : ["jquery"]
    },
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    urlArgs: "t=" + (new Date()).getTime()
});

require([
    "jquery",
    "cookie",
    "lazyload",
    "common",
    "jTempletes",
    "jTempletesUn",
    "ck",
    "hots"
], function ($){

    var common = new Common();
    common.searchButtonMouseListener();
    common.showNavigatorBar(2);

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

    var hotsUtils = new HotsUtils();
    hotsUtils.getHots();

});

function BolgUtils(){

    var that = this;
    var COOKIE_USER_NAME = "enjoy-life-customer-name";
    var COOKIE_USER_EMAIL = "enjoy-life-customer-email";
    var COOKIE_USER_WEB = "enjoy-life-customer-web";
    var COOKIE_TIME = 30;
    var CHECK_REG = /^(<[^><]+>|\s|&nbsp)+$/;
    var EMAIL_REG = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
    var IMAGE_REG = /<img\b[^>]*>/;

    this.init = function(){
        commentHelperListener();
        customerInfoButtonListener();
        submitCommentButtonClick();
        commentPaginatorListener();
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
                if(divHeight - scrollHeight <= 400){
                    thisObj.loadComment();
                }
            }
        });
    };

    this.loadComment = function(){
        var aO = $("#article");
        var allow = aO.attr("allow");
        var commentO = $("#article-comment");
        if(allow == "Y"){
            aO.attr("allow", "N");
            commentO.data("page", 1);
            this.loadCommentById(aO.attr("aid"), commentO);
        }
    };

    this.loadCommentById = function(aid, commentObj){
        data = {};
        data.articleId = aid;
        var clickPage = commentObj.data("page");
        data.page = clickPage;
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
                var page = res.page, newPage;
                newPage = page;

                if(page >= totalPage){
                    //最后一页
                    newPage = totalPage;
                    commentObj.find(".comment-paginatior").find("a").first().addClass("paginatior-none");
                }else{
                    commentObj.find(".comment-paginatior").find("a").first().removeClass("paginatior-none");
                }

                if(page <= 1){
                    newPage = 1;
                    commentObj.find(".comment-paginatior").find("a").last().addClass("paginatior-none");
                }else{
                    commentObj.find(".comment-paginatior").find("a").last().removeClass("paginatior-none");
                }
                commentObj.data("page", newPage);

                $("#comment-loading").hide();

                var array = [];
                if(list.length > 0){
                    array = addCommentLi(list);
                    commentObj.find("#comment-main-ul").show().empty().setTemplateElement("mainComment").processTemplate(array);
                    var thisSec;
                    $("#comment-main-ul").find(".comment-section").each(function(){
                        thisSec = $(this);
                        thisSec.html(thisSec.text());
                    });
                }else{
                    //则提示没有数据
                    li = "<li class='no-comments'><h3>目前还没有评论，快块来挽尊吧~~~~(>_<)~~~~</h3></li>";
                    commentObj.find("#comment-main-ul").show().empty().append(li);
                }
            },
            error : function(res){
                commentObj.data("page", clickPage);
                console.log(res);
            },
            complete : function(XMLHttpRequest, status){
                if(status=='timeout'){
                    commentObj.data("page", clickPage);
                    commentObj.find("#comment-main-ul").show().append("<li class='no-comments'><h3>网络超时了 ~~~~(>_<)~~~~</h3></li>");
                }
            }
        });

    };

    /**
     * 保存和获取cookie
     */
    var getCookieInfo = function(key){
        return $.cookie(key);
    };

    var setCookieInfo = function(key, value){
        $.cookie(key, value, { expires: COOKIE_TIME });
    };

    /**
     * 信息资料框按钮事件
     */
    var customerInfoButtonListener = function(){

        //点击取消，隐藏
        $("#cancle-info").click(function(){
            $(this).parents("#get-customer-info").hide();
        });
        //点击确定，提交
        $("#submit-info").click(function(){
            var obj = $("#get-customer-info");
            var name = obj.data("editorName");
            var data = {};
            //获取传递过来的值
            var isReply = obj.data("isReply");
            if(isReply){
                data.commentReplyUser = obj.data("replyUser");
                data.commentReplyBody = obj.data("replyComment");
            }
            data.commentIsReply = isReply;
            data.commentBody = obj.data("commentBody");

            var username = obj.find("#username").val();
            if(username == "" || CHECK_REG.test(username)){
                alert("请填写您的称呼");
                return false;
            }
            data.commentUser = username;
            setCookieInfo(COOKIE_USER_NAME, username);

            var useremail = obj.find("#useremail").val();
            if(useremail == "" || CHECK_REG.test(useremail)){
                alert("请填写您的邮箱，放心，本人不会公开该信息");
                return false;
            }
            if(!EMAIL_REG.test(useremail)){
                alert("请填写正确的邮箱格式");
                return false;
            }
            data.commentEmail = useremail;
            setCookieInfo(COOKIE_USER_EMAIL, useremail);

            var userWeb = obj.find("#usereweb").val();
            setCookieInfo(COOKIE_USER_WEB, userWeb);
            data.commentUserWebsite = userWeb;

            data.articleId = $("#article").attr("aid");

            //展示Loading
            obj.find(".get-customer-info-div").hide().end().find(".get-customer-info-div-img").show();

            $.ajax({
                url : "/blogs/saveComment.html",
                data : data,
                type : "post",
                timeout : 5000,
                success : function(res){
                    CKEDITOR.instances[name].setData("");
                    obj.find(".get-customer-info-div").show().end().find(".get-customer-info-div-img").hide().end().hide();
                    if(res == "success"){
                        showSubmitSuccessDiv("提交成功!");
                        that.loadCommentById($("#article").attr("aid"), $("#article-comment"));
                    }else{
                        showSubmitSuccessDiv("异常!");
                    }
                },
                error : function(res){
                    obj.find(".get-customer-info-div").show().end().find(".get-customer-info-div-img").hide().end().hide();
                    console.log(res);
                    showSubmitSuccessDiv("提交失败!");
                },
                complete : function(XMLHttpRequest, status){
                    if(status=='timeout'){
                        obj.find(".get-customer-info-div").show().end().find(".get-customer-info-div-img").hide().end().hide();
                        showSubmitSuccessDiv("网络超时!");
                    }
                }
            });
        });
    };

    /**
     * 评论间按钮事件
     */
    var commentHelperListener = function(){
        //回复按钮
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

        //展开按钮
        $("#comment-main-ul").on("click", ".open", function(){
            var _this = $(this);
            var li = _this.parents("li");
            var ul = li.find("ul");
            if(_this.attr("show") == "N"){
                ul.show();
                if(_this.attr("load") == "Y"){
                    var bar = li.find(".comment-reply-bar");
                    var commentId = _this.parent().attr("commentId");
                    //查询回复评论，则根据这条主评论查询，commentId 即为这条主评论的id
                    var data = {
                        commentId : commentId,
                        articleId : $("#article").attr("aid"),
                        commentIsReply : 1
                    };

                    bar.animate({width : "60%"}, function(){
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
                                        ul.empty().setTemplateElement("replyComment").processTemplate(replyLi);
                                        var thisSec;
                                        ul.find(".comment-reply-section").each(function(){
                                            thisSec = $(this);
                                            thisSec.html(thisSec.text());
                                        });
                                        bar.animate({width : "100%"}, function(){
                                            bar.css("width", "0%");
                                        });
                                    });
                                }else{
                                    bar.animate({width : "100%"}, function(){
                                        ul.append("<li class='no-comments'><h4>没有回复记录</h4></li>");
                                        bar.css("width", "0%");
                                    });
                                }
                                _this.attr("load","N").attr("show", "Y").text("收起");
                            },
                            error : function(res){
                                bar.animate({width : "100%"}, function(){
                                    _this.text("重新加载");
                                    console.log(res);
                                    bar.css("width", "0%");
                                });
                            },
                            complete : function(XMLHttpRequest, status){
                                if(status=='timeout'){
                                    bar.animate({width : "100%"}, function(){
                                        ul.append("<li class='no-comments'><h4>超时了，请重新加载</h4></li>");
                                        _this.text("重新加载");
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

        //回复评论提交按钮
        $("#comment-main-ul").on("click", ".submit-comment-reply", function(){
            var _this = $(this);
            var helper = _this.parents("li").find(".comment-helper");
            var commentSid = helper.attr("comment");
            var commentId = helper.attr("commentid");
            var owner = _this.attr("owner");
            var name = "text" + commentSid;
            var checkText = CKEDITOR.instances[name].document.getBody().getText();
            var text = CKEDITOR.instances[name].getData();
            if(checkText == "" || CHECK_REG.test(checkText)){
                if(!IMAGE_REG.test(text)){
                    _this.parent().find("span").show();
                    return false;
                }
            }
            _this.parent().find("span").hide();
            var obj = $("#get-customer-info");
            obj.data("isReply", 1);
            obj.data("replyComment", commentId);
            obj.data("commentBody", text);
            obj.data("replyUser", owner);
            obj.data("editorName", name);
            obj.find("#username").val(getCookieInfo(COOKIE_USER_NAME));
            obj.find("#useremail").val(getCookieInfo(COOKIE_USER_EMAIL));
            obj.find("#usereweb").val(getCookieInfo(COOKIE_USER_WEB));
            obj.show();
        });
    };

    /**
     * 主评论提交按钮事件
     */
    var submitCommentButtonClick = function(){
        $("#submit-comment").click(function(){
            var _this = $(this);
            var checkText = CKEDITOR.instances.commentEditor.document.getBody().getText();
            var text = CKEDITOR.instances.commentEditor.getData();
            if(checkText == "" || CHECK_REG.test(checkText)){
                if(!IMAGE_REG.test(text)){
                    _this.parent().find("span").show();
                    return false;
                }
            }
            _this.parent().find("span").hide();
            var obj = $("#get-customer-info");
            obj.data("isReply", 0);
            obj.data("commentBody", text);
            obj.data("editorName", "commentEditor");
            obj.find("#username").val(getCookieInfo(COOKIE_USER_NAME));
            obj.find("#useremail").val(getCookieInfo(COOKIE_USER_EMAIL));
            obj.find("#usereweb").val(getCookieInfo(COOKIE_USER_WEB));
            obj.show();
        });
    };

    /**
     * 评论分页按钮
     */
    var commentPaginatorListener = function(){
        $("#article-comment").find(".comment-paginatior").on("click", "a", function(){
            var _this = $(this);
            if(_this.hasClass("paginatior-none")){
                return false;
            }
            var aO = $("#article");
            var commentO = $("#article-comment");
            if(_this.attr("forward") == "p"){
                commentO.data("page", commentO.data("page") - 1);
            }else if(_this.attr("forward") == "n"){
                commentO.data("page", commentO.data("page") + 1);
            }
            that.loadCommentById(aO.attr("aid"), commentO);
        });
    };

    /**
     * 拼接主评论
     */
    var addCommentLi = function(list){
        var obj, temp, arr = [], date, clazz = "";
        for(var a = 0, b = list.length; a < b; a++){
            obj = list[a];
            var href = "javascript:";
            if(obj.commentUserWebsite != ""){
                href = obj.commentUserWebsite;
                clazz = "line";
            }
            date = new Date(obj.createDate);
            temp = {};
            temp.href = href;
            temp.clazz = clazz;
            temp.time = date.format("yyyy-MM-dd");
            temp.userName = obj.commentUser;
            temp.comment = obj.commentBody;
            temp.commentSid = obj.commentSid;
            temp.commentId = obj.commentId;
            arr.push(temp);
        }
        return arr;
    };

    /**
     * 拼接回复评论
     */
    var addReplyCommentLi = function(list){
        var obj, temp, array = [], clazz = "";
        for(var a = 0, b = list.length; a < b; a++){
            obj = list[a];
            var href = "javascript:";
            if(obj.commentUserWebsite != ""){
                href = obj.commentUserWebsite;
                clazz = "line";
            }
            temp = {};
            temp.href = href;
            temp.clazz = clazz;
            temp.userName = obj.commentUser;
            temp.comment = obj.commentBody;
            temp.replyUser = obj.commentReplyUser;
            array.push(temp);
        }
        return array;
    };

    /**
     * 提交后展示Div内容
     */
    var showSubmitSuccessDiv = function(str){
        $("#comment-subimt-success-div").show().find("span").text(str);
        setTimeout(hiddenSubmitSuccessDiv, 2000);
    };
    /**
     * 隐藏展示Div
     */
    var hiddenSubmitSuccessDiv = function(){
        $("#comment-subimt-success-div").hide();
    }
}