function Applications() {

    var _this = this;
    var index = null;
    var eye = null;
    var cate = null;
    var search = null;
    var blog = null;
    var categorys = null;
    var bull = null;


    var EMAIL_REG = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    var SITE_REG = /[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/;
    var NUM_REG = /^\+?[1-9][0-9]*$/;

    this.castStr2Num = function(str){
        return parseInt(str);
    };

    this.isNotNull = function (str) {
        if(Object.prototype.toString.call(str) === "[object String]"){
            return (str != null && str != "" && str != undefined);
        }
        return (str != null && str != "" && str != undefined && str.length > 0);
    };

    this.replaceBlock = function (str) {
        return str.replace(/\s/g, '');
    };

    this.checkIsEmail = function (str) {
        return EMAIL_REG.test(str);
    };

    this.checkIsSite = function (str) {
        return SITE_REG.test(str);
    };

    this.checkIsNum = function (str) {
        return NUM_REG.test(str);
    };

    this.initIndex = function () {
        if(index == null){
            index = new Index();
        }
        index.init();
    };

    this.initEye = function () {
        if(eye == null){
            eye = new Eyes();
        }
        eye.init();
    };

    this.initGenre = function () {
        if(cate == null){
            cate = new Category();
        }
        cate.init();
    };

    this.initQuery = function () {
        if(search == null){
            search = new Searchs();
        }
        search.init();
    };

    this.initBlog = function () {
        if(blog == null){
            blog = new Blogs();
        }
        blog.init();
    };

    this.initBull = function () {
        if(bull == null){
            bull = new Bull();
        }
        bull.init();
    };

    this.startCloud = function () {
        if(categorys == null){
            categorys = new CategoryUtils();
        }
        var typeObj = $("#type-body");
        if(typeObj.length > 0){
            categorys.init(typeObj);
        }
    };

    this.bindEvent = function () {
        var href = window.location.href;
        if(href.indexOf("index") > 0){
            applications.initIndex();
        }
        if(href.indexOf("eyes") > 0){
            applications.initEye()
        }
        if(href.indexOf("query") > 0){
            applications.initQuery();
        }
        if(href.indexOf("genre") > 0){
            applications.initGenre();
        }
        if(href.indexOf("blogs") > 0){
            applications.initBlog();
        }
        if(href.indexOf("bullshit") > 0){
            applications.initBull();
        }
    }
}
var applications = new Applications();

Date.prototype.Format = function(fmt){
    var o = {
        "M+" : this.getMonth() + 1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};

$(function () {
    $("#search-button").click(function () {
        var input = $(this).prev();
        var locations = "/eyes/";
        var val = input.val();
        if(applications.isNotNull(val) && !val.match(/^\s+$/)){
            val = input.val().replace(/(^\s*)|(\s*$)/g, "").replace(/\\/g, "").replace(/\//g, "");
            locations = "/query/" + val + "/1";
        }
        $.pjax({url: locations, container: '#main'});
    });

    $(document).pjax('.link-head', '#main', {
        maxCacheLength:0,
        cache: true,
        storage: true,
        timeout: 10000//设置超时，默认pjax是1秒,
    });

    $(document).bind('pjax:send', function(){
        $("#loading-page-bar").animate({width: "70%"}, 1000);
    });

    $(document).bind('pjax:success', function(){
        $("#loading-page-bar").animate({width: "100%"}, 300, function () {
            $("#loading-page-bar").width("0");
        });

        applications.startCloud();
        applications.bindEvent();
    });

    $("#header-nav-ul li a").click(function () {
        $(this).parent().addClass("header-underline").siblings().removeClass("header-underline");
    });

    applications.startCloud();
    applications.bindEvent();
});

function Index() {

    var isClick = true;

    this.init = function () {
        this.initPage();
        this.pageClickListener();
    };

    this.initPage = function () {
        new Blazy({
            container: '.blog-body',
            error: function(ele, msg){
                // if(msg === 'missing'){
                //     console.log("加载丢失");
                // }else if(msg === 'invalid'){
                //     console.log("加载失败");
                // }
                ele.src = "/base/images/failed.png";
            }
        });
    };

    this.pageClickListener = function () {
        $("#page-div").createPage({
            pageCount: applications.castStr2Num($("#page-div").attr("data-pages")),
            current: applications.castStr2Num($("#page-div").attr("data-current")),
            backFn: function(page){
                if(isClick){
                    isClick = false;
                    var location = "/index/";
                    if(page > 1){
                        if(applications.checkIsNum(page)){
                            location = "/index/" + page;
                        }
                    }
                    $.pjax({url: location, container: '#main'});
                    isClick = true;
                }
            }
        });
    }
}

function Eyes(){

    var timeDiv = $("#time-div");
    var blogDiv = $("#container-title");
    var scrollLeft = $("#container-title").offset().left;

    /**
     * 初始化
     */
    this.init = function () {
        this.scrollBarListener();
        this.timeYearClickListener();
        this.timeMonthClickListener();
    };

    /**
     * 页面滚动事件，动态根据文章标题改变时间轴
     */
    this.scrollBarListener = function(){
        var windowHeight;
        var _window = $(window);
        var firstDiv, nowY, nowM;
        var isLis = false;//是否监听的开关
        _window.scroll(function(){
            windowHeight = _window.scrollTop();
            if(windowHeight > 100){
                //开始监测滚动的文章
                timeDiv.css("position", "fixed");
                isLis = true;
                autoChangeTimeBar();
            }else{
                if(isLis){
                    isLis = false;//关闭监听开关，防止在非文章区域滚动时，多次触发
                    timeDiv.css("position", "relative");
                    //此时说明已经超过了文章主体，则让第一个时间轴元素显示
                    firstDiv = timeDiv.find(".time-year").first();
                    var tempY = timeDiv.attr("n-y");
                    var tempM = timeDiv.attr("n-m");
                    nowY = firstDiv.find("em").text();
                    nowM = firstDiv.find("li:first").attr("t-m");
                    if(tempY == nowY && tempM == nowM){
                        return;
                    }
                    timeDiv.find("em").removeClass("now-year").end()
                        .find("i").removeClass("yead-front-up").end()
                        .find("li").removeClass("now-month");
                    firstDiv.find("em").addClass("now-year").end()
                        .find("i").addClass("yead-front-up").end()
                        .find("li:first").addClass("now-month");
                    timeDiv.attr("n-y", nowY).attr("n-m", nowM);

                    if(firstDiv.find("ul").css("display") == "none"){
                        timeDiv.find("ul").slideUp(200);
                        firstDiv.find("ul").slideDown(200);
                    }
                }
            }
        });
    };

    /**
     * 时间轴年份点击事件
     * 进入当前年份的最上面的月份区域
     */
    this.timeYearClickListener = function(){
        var _this, nowY, thisY, thisM;
        timeDiv.on("click", "span", function () {
            _this = $(this);
            thisY = _this.attr("class");
            nowY = timeDiv.attr("n-y");
            if(thisY != nowY){
                //获取点击的月份中的最上面的一个
                thisM = _this.parent().find("li:first").attr("t-m");
                gotoScrollBlogLine(thisY, thisM);
            }
        });
    };

    /**
     * 时间轴月份点击事件
     */
    this.timeMonthClickListener = function () {
        var _this, nowY, nowM, thisY, thisM;
        timeDiv.on("click", "li", function () {
            _this = $(this);
            thisY = _this.parents(".time-year").find("em").text();
            thisM = _this.attr("t-m");
            nowY = timeDiv.attr("n-y");
            nowM = timeDiv.attr("n-m");
            if(nowY == thisY && nowM != thisM){
                gotoScrollBlogLine(thisY, thisM);
            }
        });
    };

    /**
     * 根据传入的年份和月份改变时间轴的当前样式
     * @param tarY
     * @param tarM
     */
    var changeTimeBar = function (tarY, tarM) {
        //如果年份不同，则需要修改年份显示
        var bar_year = timeDiv.attr("n-y");
        var bar_month = timeDiv.attr("n-m");
        if(tarY != bar_year){
            //修改年份样式
            timeDiv.attr("n-y", tarY).find("i").removeClass("yead-front-up").end()
                .find("em").removeClass("now-year").end()
                .find("ul").slideUp(200);
            $("." + tarY).find("i").addClass("yead-front-up").end()
                .find("em").addClass("now-year").end().parent().find("ul").slideDown(200);
            //修改月份样式
            timeDiv.attr("n-m", tarM).find("li").removeClass().end()
                .find("." + tarY).parent().find("li[t-m='" + tarM + "']").addClass("now-month");
        }else if(tarM != bar_month){
            timeDiv.attr("n-m", tarM).find("li").removeClass().end()
                .find("." + tarY).parent().find("li[t-m='" + tarM + "']").addClass("now-month");
        }
    };

    /**
     * 自动根据滚动的文章时间改变时间轴
     */
    var autoChangeTimeBar = function () {
        var year, month;
        //获取文章列表dom据父元素左边的距离
        window.onresize = function(){
            scrollLeft = $("#container-title").offset().left;
        };
        var nowDom = document.elementFromPoint(scrollLeft, 0);
        nowDom = $(nowDom);
        var tarName = nowDom.prop("tagName");
        if(tarName == "H2"){
            var bar_year = timeDiv.attr("n-y");
            var bar_month = timeDiv.attr("n-m");
            year = nowDom.attr("data-year");
            month = nowDom.attr("data-month");
            if(bar_year != year || bar_month != month){
                changeTimeBar(year, month);
            }
        }
    };

    /**
     * 根据年份和月份自动滚动到指定的位置
     * @param tarY
     * @param tarM
     */
    var gotoScrollBlogLine = function (tarY, tarM) {
        //找到年份和月份的所有h2中的第一个
        var tarF = blogDiv.find("h2[data-year='" + tarY + "'][data-month='" + tarM + "']:first");
        //滚动到这个的高度
        doScrollByHeight(tarF.offset().top)
    };

    var doScrollByHeight = function (height) {
        $("body, html").animate({scrollTop: height}, 600);
    }

}

function Searchs() {

    var isClick = true;

    this.init = function () {
        this.pageBarLister();
        $("#header-nav-ul li").eq(2).addClass("header-underline").siblings().removeClass("header-underline");
    };

    this.pageBarLister = function () {
        $("#page-div").createPage({
            pageCount: applications.castStr2Num($("#page-div").attr("data-pages")),
            current: applications.castStr2Num($("#page-div").attr("data-current")),
            backFn: function(page){
                if(isClick){
                    isClick = false;
                    var location = window.location.href;
                    if(page > 0){
                        if(applications.checkIsNum(page)){
                            location = location.substr(0, location.lastIndexOf("/") + 1);
                            location += page;
                        }
                    }
                    $.pjax({url: location, container: '#main'});
                    isClick = true;
                }
            }
        });
    }
}

function Category(){

    var isClick = true;

    this.init = function () {
        this.imgLazyload();
        this.pageBarListener();
        $("#header-nav-ul li").eq(2).addClass("header-underline").siblings().removeClass("header-underline");
    };

    this.imgLazyload = function () {
        new Blazy({
            container: '.cate-info-container',
            error: function(ele, msg){
                // if(msg === 'missing'){
                //     console.log("加载丢失");
                // }else if(msg === 'invalid'){
                //     console.log("加载失败");
                // }
                ele.src = "/base/images/failed.png";
            }
        });
    };

    this.pageBarListener = function () {
        $("#page-div").createPage({
            pageCount: applications.castStr2Num($("#page-div").attr("data-pages")),
            current: applications.castStr2Num($("#page-div").attr("data-current")),
            backFn: function(page){
                if(isClick){
                    isClick = false;
                    var location = window.location.href;
                    if(page > 0){
                        if(applications.checkIsNum(page)){
                            location = location.substr(0, location.lastIndexOf("/") + 1);
                            location += page;
                        }
                    }
                    $.pjax({url: location, container: '#main'});
                    isClick = true;
                }
            }
        });
    }
}

function Bull(){

    var _this = this;

    this.init = function () {
        this.setSize();
    };

    this.setSize = function () {
        var aList = $("#main-left").find("a");
        var temp;
        aList.each(function () {
            temp = $(this);
            temp.css("font-size", _this.randomSize() + "px");
        })
    };

    this.randomSize = function () {
        return Math.round(Math.random() * 100 + 10);
    }
}

function Blogs(){

    var replyEidtorDiv = "<div id=\"reply-editor-div\" class=\"reply-editor\"><textarea id=\"reply-main-editor\" cols=\"1\" rows=\"1\" class='reply-main-editor' title=\"回复\"></textarea></div>";
    var replyButtonDiv = "<div id=\"reply-buttons-div\" class=\"reply-buttons\"><span id=\"reply-error-msg\" class=\"error-message\"></span><a id='submit-reply-button' class=\"submit-reply-button\">回复</a></div>";
    var commentEditor, replyEditor;
    var isLoadingComment = false;

    this.init = function () {
        this.initPage();
        this.commentReplyButtonClick();
        this.commentShowButtonClick();
        this.commentSubmitButtonClick();
        this.replySubmitButtonClick();
        this.dataSubmitClick();
        this.dataCancleClick();
        this.websiteClick();
        Prism.highlightAll();
        $("#header-nav-ul li").eq(3).addClass("header-underline").siblings().removeClass("header-underline");
    };

    /**
     * 初始化评论数据
     */
    this.initPage = function () {
        //渲染评论编辑器
        commentEditor = CKEDITOR.instances["comment-main-editor"];
        if (commentEditor) {
            commentEditor.destroy();
        }
        commentEditor = CKEDITOR.replace("comment-main-editor", { height: "160px"});
        loadingComment(1, true);
    };

    /**
     * 评论回复按钮 添加回复评论文本框
     */
    this.commentReplyButtonClick = function () {
        var _this, _thisL;
        $("#main").on("click", ".to-reply", function () {
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

    /**
     * 评论展开按钮，查看该主评论下的所有回复
     */
    this.commentShowButtonClick = function () {
        var _this, _thisL;
        $("#main").on("click", ".to-show", function () {
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
                    _this.attr("data-l", "0");
                }
            }
        });
    };

    /**
     * 主评论提交按钮，按下展示信息提交框
     */
    this.commentSubmitButtonClick = function () {
        var _this;
        $("#comment-submit").unbind("click").click(function () {
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

    /**
     * 回复评论提交按钮，按下展示信息提交框
     */
    this.replySubmitButtonClick = function () {
        var _this;
        $("#main").on("click", "#submit-reply-button", function () {
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

    /**
     * 信息提交框确认按钮，校验昵称，邮箱等信息，并提交数据
     */
    this.dataSubmitClick = function () {
        $("#to-submit").unbind("click").click(function () {
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

    /**
     * 信息提交框取消按钮，关闭信息框
     */
    this.dataCancleClick = function () {
        $("#to-cancle").unbind("click").click(function () {
            $("#whole-div").data("comment", "").hide();
        });
    };

    /**
     * 评论中回复人的名称点击事件，如果该回复者有个人网站，打开该网站
     */
    this.websiteClick = function () {
        var site = "";
        $("#articlt-comment-div").unbind("click").on("click", ".has-website", function () {
            site = $(this).attr("data-w");
            if(site.indexOf("http://") >= 0 || site.indexOf("https://") >= 0){
                window.open(site);
            }else{
                window.open("http://" + site);
            }
        });
    };

    /**
     * 加载回复评论方法，由评论回复按钮触发
     * @param li
     * @param span
     */
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
                        span.attr("data-l", "0");
                    });
                });
            });
        });

    };

    /**
     * 加载主评论方法，由页面初始化，分页按钮等方法触发
     * @param page
     * @param init
     */
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

    /**
     * 封装由后台传入的评论数据，变成li节点
     * @param list
     * @returns {string}
     */
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