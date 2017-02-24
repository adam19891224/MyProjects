Date.prototype.Format = function (fmt) {
    let o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)){
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (let k in o){
        if (new RegExp("(" + k + ")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

function Applications() {

    let categorys;

    let EMAIL_REG = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
    let SITE_REG = /[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/;
    let NUM_REG = /^\+?[1-9][0-9]*$/;

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

    this.startCloud = function () {
        if(categorys == null){
            categorys = new CategoryUtils();
        }
        let typeObj = $("#type-body");
        if(typeObj.length > 0){
            categorys.init(typeObj);
        }
    };

    this.searchBind = function () {
        $("#search-button").click(function () {
            let val = $("#search-input").val();
            if(applications.isNotNull(val)){
                router.push({name: 'query', params: { searchName: val, p: 1 }});
            }else{
                router.push({name: 'eyes'});
            }
        });
    };

}
let applications = new Applications();


function Index() {

    let isClick;

    this.init = function () {
        isClick = true;
        this.initPage();
        this.pageClickListener();
    };

    this.initPage = function () {
        new Blazy({
            container: '.blog-body',
            error: function(ele, msg){
                ele.src = "/images/failed.png";
                console.log(msg);
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
                    if(page > 0){
                        if(applications.checkIsNum(page)){
                            router.push({name: 'index', params: { page: page }});
                        }
                    }
                    isClick = true;
                }
            }
        });
    }
}
let index = new Index();

function Eyes(){

    let timeDiv, blogDiv, scrollLeft;

    /**
     * 初始化
     */
    this.init = function () {
        timeDiv = $("#time-div");
        blogDiv = $("#container-title");
        this.getLeft();
        this.scrollBarListener();
        this.timeYearClickListener();
        this.timeMonthClickListener();
    };

    /**
     * 获取scrollLeft
     */
    this.getLeft = function () {
        let obj = $("#container-title");
        if(obj.length == 1){
            scrollLeft = obj.offset().left;
        }
    };

    /**
     * 页面滚动事件，动态根据文章标题改变时间轴
     */
    this.scrollBarListener = function(){
        let windowHeight;
        let _window = $(window);
        let firstDiv, nowY, nowM;
        let isLis = false;//是否监听的开关
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
                    let tempY = timeDiv.attr("n-y");
                    let tempM = timeDiv.attr("n-m");
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
        let _this, nowY, thisY, thisM;
        timeDiv.on("click", "span", function () {
            _this = $(this);
            thisY = _this.attr("time-year");
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
        let _this, nowY, nowM, thisY, thisM;
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
    let changeTimeBar = function (tarY, tarM) {
        //如果年份不同，则需要修改年份显示
        let bar_year = timeDiv.attr("n-y");
        let bar_month = timeDiv.attr("n-m");
        if(tarY != bar_year){
            //修改年份样式
            timeDiv.attr("n-y", tarY).find("i").removeClass("yead-front-up").end()
                .find("em").removeClass("now-year").end()
                .find("ul").slideUp(200);
            timeDiv.find("span[time-year='" + tarY + "']").find("i").addClass("yead-front-up").end()
                .find("em").addClass("now-year").end().parent().find("ul").slideDown(200);
            //修改月份样式
            timeDiv.attr("n-m", tarM).find("li").removeClass().end()
                .find("span[time-year='" + tarY + "']").parent().find("li[t-m='" + tarM + "']").addClass("now-month");
        }else if(tarM != bar_month){
            timeDiv.attr("n-m", tarM).find("li").removeClass().end()
                .find("span[time-year='" + tarY + "']").parent().find("li[t-m='" + tarM + "']").addClass("now-month");
        }
    };

    /**
     * 自动根据滚动的文章时间改变时间轴
     */
    let autoChangeTimeBar = function () {
        let year, month;
        let nowDom = document.elementFromPoint(scrollLeft, 0);
        nowDom = $(nowDom);
        let tarName = nowDom.prop("tagName");
        if(tarName == "H2"){
            let bar_year = timeDiv.attr("n-y");
            let bar_month = timeDiv.attr("n-m");
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
    let gotoScrollBlogLine = function (tarY, tarM) {
        //找到年份和月份的所有h2中的第一个
        let tarF = blogDiv.find("h2[data-year='" + tarY + "'][data-month='" + tarM + "']:first");
        //滚动到这个的高度
        doScrollByHeight(tarF.offset().top)
    };

    let doScrollByHeight = function (height) {
        $("body, html").animate({scrollTop: height}, 600);
    }

}
let eyes = new Eyes();

function Searchs() {

    let isClick;

    this.init = function () {
        isClick = true;
        this.pageBarLister();
    };

    this.pageBarLister = function () {
        $("#search-page-div").createPage({
            pageCount: applications.castStr2Num($("#search-page-div").attr("data-pages")),
            current: applications.castStr2Num($("#search-page-div").attr("data-current")),
            backFn: function(page){
                if(isClick){
                    isClick = false;
                    if(page > 0){
                        let location = window.location.href;
                        location = decodeURI(location);
                        let rmPageLocation = location.substr(0, location.lastIndexOf("/"));
                        let searchName = rmPageLocation.substr(rmPageLocation.lastIndexOf("/") + 1, rmPageLocation.length);
                        if(applications.checkIsNum(page)){
                            //从url中获取当前的typename
                            router.push({name: 'query', params: { searchName: searchName, p: page }});
                        }
                    }
                    isClick = true;
                }
            }
        });
    }
}
let search = new Searchs();

function Category(){

    let isClick;

    this.init = function () {
        isClick = true;
        this.imgLazyload();
        this.pageBarListener();
    };

    this.imgLazyload = function () {
        new Blazy({
            container: '.cate-info-container',
            error: function(ele, msg){
                ele.src = "images/failed.png";
                console.log(msg);
            }
        });
    };

    this.pageBarListener = function () {
        $("#cate-page-div").createPage({
            pageCount: applications.castStr2Num($("#cate-page-div").attr("data-pages")),
            current: applications.castStr2Num($("#cate-page-div").attr("data-current")),
            backFn: function(page){
                if(isClick){
                    isClick = false;
                    if(page > 0){
                        let location = window.location.href;
                        location = decodeURI(location);
                        let rmPageLocation = location.substr(0, location.lastIndexOf("/"));
                        let typeName = rmPageLocation.substr(rmPageLocation.lastIndexOf("/") + 1, rmPageLocation.length);
                        if(applications.checkIsNum(page)){
                            //从url中获取当前的typename
                            router.push({name: 'genre', params: { typeName: typeName, p: page }});
                        }
                    }
                    isClick = true;
                }
            }
        });
    }
}
let cate = new Category();


function Blogs(){

    let replyEidtorDiv, replyButtonDiv, commentEditor, replyEditor, isLoadingComment;

    this.init = function () {
        replyEidtorDiv = "<div id=\"reply-editor-div\" class=\"reply-editor\"><textarea id=\"reply-main-editor\" cols=\"1\" rows=\"1\" class='reply-main-editor' title=\"回复\"></textarea></div>";
        replyButtonDiv = "<div id=\"reply-buttons-div\" class=\"reply-buttons\"><span id=\"reply-error-msg\" class=\"error-message\"></span><a id='submit-reply-button' class=\"submit-reply-button\">回复</a></div>";
        isLoadingComment = false;
        this.initPage();
        this.commentReplyButtonClick();
        this.commentShowButtonClick();
        this.commentSubmitButtonClick();
        this.replySubmitButtonClick();
        this.dataSubmitClick();
        this.dataCancleClick();
        this.websiteClick();
        Prism.highlightAll();
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
        let _this, _thisL;
        $("#main").on("click", ".to-reply", function () {
            _this = $(this);
            let l = _this.attr("data-l");
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
        let _this, _thisL;
        $("#main").on("click", ".to-show", function () {
            _this = $(this);
            _thisL = _this.parents("li");
            let l = _this.attr("data-l");
            if(l == "0"){
                _this.attr("data-l", "1");
                let type = _this.data("type");
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
        let _this;
        $("#comment-submit").unbind("click").click(function () {
            _this = $(this);
            let lock = _this.data("lock");
            if(lock != "1"){
                _this.data("lock", "1");
                $("#comment-error-msg").text("");
                let content = commentEditor.document.getBody().getText();
                let data = commentEditor.getData();
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
        let _this;
        $("#main").on("click", "#submit-reply-button", function () {
            _this = $(this);
            let lock = _this.data("lock");
            if(lock != "1"){
                _this.data("lock", "1");
                $("#reply-error-msg").text("");
                let content = replyEditor.document.getBody().getText();
                let data = replyEditor.getData();
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
                    let _thisP = _this.parents("li");
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
            let name = $("#friend-name").val();
            let email = $("#friend-email").val();
            let type = $("#friend-sitetype").val();
            let site = $("#friend-website").val();
            if(!applications.isNotNull(name)){
                alert("请大牛输入昵称");
                return false;
            }
            if(!applications.isNotNull(email)){
                alert("请大牛输入邮箱");
                return false;
            }
            let obj = {};
            let isReply = $("#whole-div").data("isReply");
            let dataId = $("#whole-div").data("replyBody");
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
            $.post('/blogs/save', obj, function (text) {
                if(text != "success"){
                    if(text == "error"){
                        alert("提交评论失败");
                    }else{
                        alert("拒绝访问");
                    }
                }
                let aid = $("#article-body").attr("data-asid");
                router.go({ name: 'blog', params: { aid: aid } });
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
        let site = "";
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
    let loadingReplyCommentsByDiv = function (li, span) {
        //获取这个评论的id和文章id
        let lineDiv = li.find(".main-line");
        lineDiv.animate({width: "60%"}, 500, function () {
            lineDiv.animate({width: "70%"}, 1000, function () {
                let obj = {};
                obj.articleId = $("#article-body").attr("data-aid");
                obj.commentId = li.attr("data-id");
                obj.commentIsReply = 1;
                obj.pagination = false;
                $.post("/blogs/comments", obj, function (res) {
                    let result = res;
                    let isOk = result.isOk;
                    let lis = "";
                    if(isOk == "Y"){
                        let list = result.list;
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
    let loadingComment = function (page, init) {
        //清空现有的评论列表
        $("#articlt-comment-div").find(".comment-container").empty().append("<li class=\"comment-loading\"><i class=\"loading-bar\"></i></li>");
        let obj = {};
        obj.isReply = 0;
        obj.articleId = $("#article-body").attr("data-aid");
        obj.commentIsReply = 0;
        obj.page = page;

        $.ajax({
            url: "/blogs/comments",
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
                let result = text;
                let isOk = result.isOk;
                let lis = "";
                if(isOk == "Y"){
                    // let counts = result.totalCounts;
                    let pages = result.totalPages;
                    let current = result.current;
                    let list = result.list;
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
    let getCommentLis = function (list) {
        let tempO, lis = "", time = "", span = "";
        for(let a = 0, b = list.length; a < b; a++){
            tempO = list[a];
            time = new Date(tempO.createDate).Format("yyyy-MM-dd");
            let site = tempO.commentUserWebsite;
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

    let createPageLine = function (count, current) {
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

    let getReplysLis = function (list) {
        let lis = "", time = "";
        let tempO;
        let span;
        for (let a = 0, b = list.length; a < b; a++) {
            tempO = list[a];
            time = new Date(tempO.createDate).Format("yyyy-MM-dd");
            let site = tempO.commentUserWebsite;
            if (applications.isNotNull(site) && applications.checkIsSite(site)) {
                span = "<span class='has-website' data-w='" + site + "'>" + tempO.commentUser + "：</span><span>" + time + "</span>";
            } else {
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
let blog = new Blogs();

$(function () {
    applications.searchBind();
});

//获取文章列表dom据父元素左边的距离
window.onresize = function(){
    eyes.getLeft();
};