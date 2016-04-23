require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "cookie": "/base/js/jquery.cookie",
        "lazyload": "/base/js/jquery.lazyload.min",
        "common": "/base/js/common"
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
    "common"
], function ($){

    var common = new Common();
    common.searchButtonMouseListener();
    var indexUtils = new IndexUtils();
    indexUtils.init();
    indexUtils.getBlogsByPage();

    var win;
    var scrollTop, scrollHeight, windowHeight;
    $(window).scroll(function(){
        win = $(this);
        scrollTop = win.scrollTop();
        scrollHeight = $(document).height();
        windowHeight = win.height();
        if(scrollTop + windowHeight == scrollHeight){
            indexUtils.getBlogsByPage();
        }
    });

});

function IndexUtils(){

    this.init = function(){
        //初始化数据
        $("#main").data("page", 1);
        $("#main").data("isPage", true);
    };

    this.getBlogsByPage = function(){
        //判断加载数据的开关
        if($("#main").data("isPage")){
            showLoading();
            //如果允许加载，则让该开关进入关闭状态，避免重复加载
            $("#main").data("isPage", false);
            //正式获取数据
            loading();
        }
    };

    var loading = function(){
        var data = createData();
        $.ajax({
            url : "/blogs/getBlogs.html",
            type : "post",
            data : data,
            success : function(res){
                res = eval("(" + res + ")");
                //保存新的page
                $("#main").data("page", res.page);
                var list = res.resultList;
                if(list.length > 0){
                    combineBlogs(list);
                    //然后打开开关
                    $("#main").data("isPage", true);
                    //关闭进度条
                    hideLoading();
                }else{
                    //则提示没有数据
                    $("#loading-div").empty();
                    $("#loading-div").append("<span>没有数据了。。。</span>")
                }
            }
        })

    };

    var createData = function(){
        var data = {};
        data.page = $("#main").data("page");
        data.kw = $("#kw-text").val();
        return data;
    };

    var combineBlogs = function(list){
        var lis = "";
        var obj;
        var date;
        for(var a = 0, b = list.length; a < b; a++){
            obj = list[a];
            date = new Date(obj.createDate);
            lis += "<li>" +
                        "<div class=\"blog-tips\">" +
                            "<span>" + date.format("yyyy-MM-dd") + "</span><br>" +
                        "</div>" +
                        "<h2 title=\"" + obj.articleTitle + "\">" +
                            "<a href=\"/blogs/" + obj.articleSid + ".html\">" + obj.articleTitle + "</a>" +
                        "</h2>" +
                        "<a href=\"#\" class=\"li-a\">" +
                            "<img src=\"" + obj.articleImg + "\" class=\"lazy\" data-original=\"\" alt=\"" + obj.articleTitle + "\">" +
                        "</a>" +
                        "<span>" + obj.articleDescription + "</span>" +
                  "</li>";
        }
        $("#blog-main").append(lis);
    };

    var showLoading = function(){
        $("#loading-div").removeClass("loading-hidden");
    };

    var hideLoading = function(){
        $("#loading-div").addClass("loading-hidden");
    }
}