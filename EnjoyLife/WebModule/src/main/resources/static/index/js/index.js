require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "cookie": "/base/js/jquery.cookie",
        "lazyload": "/base/js/jquery.lazyload.min",
        "jTempletes": "/blogs/js/templates",
        "jTempletesUn": "/blogs/js/templates_uncompressed",
        "common": "/base/js/common",
        "hots": "/base/js/hots"
    },
    shim: {
        "lazyload" : ["jquery"],
        "jTempletes" : ["jquery"],
        "jTempletesUn" : ["jquery"]
    }
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    // urlArgs: "t=" + (new Date()).getTime()
});

require([
    "jquery",
    "cookie",
    "lazyload",
    "jTempletes",
    "jTempletesUn",
    "common",
    "hots"
], function ($){

    var common = new Common();
    common.searchButtonMouseListener();
    common.showNavigatorBar(0);

    var indexUtils = new IndexUtils();
    indexUtils.init();
    indexUtils.paginationClickListener();

    var hotsUtils = new HotsUtils();
    hotsUtils.getHots();
});

function IndexUtils(){

    this.init = function(){
        //初始化数据
        $("#main").data("page", 1);
        $("#main").data("isPage", true);
        getBlogsByPage();
    };

    this.paginationClickListener = function(){
        $("#pagination-div").on("click", "a", function(){
            var obj = $(this);
            if(obj.hasClass("none")){
                return false;
            }
            var page = $("#main").data("page");
            if(obj.attr("forward") == "p"){
                getBlogsByPage(page - 1);
            }else if(obj.attr("forward") == "n"){
                getBlogsByPage(page + 1);
            }
        })
    };

    var getBlogsByPage = function(page){
        //判断加载数据的开关
        if($("#main").data("isPage")){
            showLoading();
            //如果允许加载，则让该开关进入关闭状态，避免重复加载
            $("#main").data("isPage", false);
            //正式获取数据
            loading(page);
        }
    };

    var loading = function(page){
        var data = createData(page);
        $.ajax({
            url : "/blogs/getBlogs.html",
            type : "post",
            timeout : 5000,
            data : data,
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
                    $("#pagination-div a:last").addClass("none");
                }else{
                    $("#pagination-div a:last").removeClass("none");
                }
                if(page <= 1){
                    newPage = 1;
                    $("#pagination-div a:first").addClass("none");
                }else{
                    $("#pagination-div a:first").removeClass("none");
                }
                $("#main").data("page", newPage);

                $("#blog-main").empty();
                if(list.length > 0){
                    combineBlogs(list);
                    lazyloading();
                    //然后打开开关
                    $("#main").data("isPage", true);
                    //关闭进度条
                    hideLoading();
                }else{
                    //则提示没有数据
                    $("#loading-div").empty();
                    $("#loading-div").append("<span>没有数据了。。。</span>");
                }
            },
            error : function(){
                //则提示没有数据
                $("#loading-div").empty();
                $("#loading-div").append("<span>请求发生异常。。。</span>");
                //然后打开开关
                $("#main").data("isPage", true);
            },
            complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
                if(status=='timeout'){//超时,status还有success,error等值的情况
                    $("#loading-div").empty();
                    $("#loading-div").append("<span>请求超时，请稍后再来。。。</span>");
                    //然后打开开关
                    $("#main").data("isPage", true);
                }
            }
        })

    };

    var createData = function(page){
        var data = {};
        data.page = page;
        data.kw = $("#kw-text").val();
        return data;
    };

    var combineBlogs = function(list){
        $("#blog-main").setTemplateElement("indexArticle").processTemplate(list);
    };

    var showLoading = function(){
        $("#loading-div").removeClass("hidden");
    };

    var hideLoading = function(){
        $("#loading-div").addClass("hidden");
    };

    var lazyloading = function(){
        //绑定懒加载
        $("#blog-main img").lazyload({
            effect: "fadeIn",
            threshold : 200,
            failure_limit: 1
        });
    }
}