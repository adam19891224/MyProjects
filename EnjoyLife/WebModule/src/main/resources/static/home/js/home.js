function Home(){

    this.init = function(){
        loadingData();
        loadingBarClickListener();
        $("#article-pages").data("pageN", 1);
        $("#article-pages").data("kw", "");
    };

    var loadingBarClickListener = function(){
        $("#loading-page-bar").click(function(){
            if(!checkIsLoad()){
                return false;
            }
            pageLoading();
            loadingData();
        });
    };

    var loadingData = function(){

        var page = {
            "page" : $("#article-pages").data("pageN"),
            "kw" : $("#article-pages").data("kw")
        };

        $.ajax({
            url : "/articles/list.html",
            type : "post",
            data : page,
            timeout : 5000,
            success : function(data){
                data = $.parseJSON(data);
                $("#article-pages").data("pageN", data.page);
                $("#article-pages").data("kw", data.kw);
                if(checkBowserIsIE()){
                    appendingDataIE(data.resultList);
                }else{
                    appendingData(data.resultList);
                }
            },
            complete : function(XMLHttpRequest, status){ //请求完成后最终执行参数
                if(status == 'timeout'){//超时,status还有success,error等值的情况
                    pageTimeout();
                }
            }
        });
    };

    var appendingData = function (list){
        if(list.length > 0){
            var lis = "";
            for(var a = 0, len = list.length; a < len; a++){
                var obj = list[a];
                var li = $("#articlr-li-display").clone();
                li.removeClass("dislpay-none").find("h2").find("a").text(obj.articleTitle).attr("title", obj.articleTitle).attr("href", "/articles/" + obj.articleSid + ".html").end().end()
                                              .find("a").attr("href", "/articles/" + obj.articleSid + ".html").end()
                                              .find("span").text(obj.articleDescription).end()
                                              .find("img").attr("data-original", obj.articleImg).attr("alt", "/articles/" + obj.articleSid + ".html");
                lis += "<li>" + li.html() + "</li>";
            }
            $("#article-pages").append(lis);
            lazyloadBinder();
            pageOK();
        }else {
            pageNoData();
        }
    };

    var appendingDataIE = function(list){
        if(list.length > 0){
            var lis = [];
            for(var a = 0, len = list.length; a < len; a++){
                var obj = list[a];
                var li = $("#articlr-li-display").clone();
                li.removeClass("dislpay-none").find("h2").find("a").text(obj.articleTitle).attr("title", obj.articleTitle).attr("href", "/articles/" + obj.articleSid + ".html").end().end()
                    .find("a").attr("href", "/articles/" + obj.articleSid + ".html").end()
                    .find("span").text(obj.articleDescription).end()
                    .find("img").attr("data-original", obj.articleImg).attr("alt", "/articles/" + obj.articleSid + ".html");
                lis[a] = "<li>" + li.html() + "</li>";
            }
            var res = lis.join("");
            lis = null;
            $("#article-pages").append(res);
            lazyloadBinder();
            pageOK();
        }else {
            pageNoData();
        }
    };

    var pageLoading = function(){
        changeLoadingBar("notR", "正在加载。。。", "/common/image/page-loading.gif");
    };

    var pageNoData = function(){
        changeLoadingBar("notR", "没有数据。。。", "/common/image/end.png");
    };

    var pageOK = function(){
        changeLoadingBar("R", "点击加载更多", "/common/image/front.png");
    };

    var pageTimeout = function(){
        changeLoadingBar("R", "超时，请重新点击加载", "/common/image/front.png");
    };

    var changeLoadingBar = function(follow, spanText, imgUrl){
        $("#loading-page-bar").attr("follow", follow)
            .find("span").text(spanText).end()
            .find("img").attr("src", imgUrl);
    };

    var checkIsLoad = function(){
        var isPage = $("#loading-page-bar").attr("follow");
        if(isPage == "R"){
            return true;
        }
    };

    var lazyloadBinder = function(){
        $(".lazy").lazyload({
            effect: "fadeIn"
        });
    };

    var checkBowserIsIE = function(){
         return !$.support.leadingWhitespace;
    }

}

