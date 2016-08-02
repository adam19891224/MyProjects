require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "cookie": "/base/js/jquery.cookie",
        "lazyload": "/base/js/jquery.lazyload.min",
        "common": "/base/js/common",
        "jTempletes": "/blogs/js/templates",
        "jTempletesUn": "/blogs/js/templates_uncompressed",
        "hots": "/base/js/hots",
        "tarCloud": "/base/js/tar-cloud"
    },
    shim: {
        "lazyload" : ["jquery"],
        "cookie" : ["jquery"],
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
    "common",
    "jTempletes",
    "jTempletesUn",
    "hots",
    "tarCloud"
], function ($){

    var common = new Common();
    common.searchButtonMouseListener();
    common.showNavigatorBar(2);

    var categorys = new CategoryUtils();
    categorys.init(0, document.getElementById("type-body"));

    var hotsUtils = new HotsUtils();
    hotsUtils.getHots();

    var eyesUtils = new EyesUtils();


    var scrollTop;
    //时间监控
    $(window).scroll(function () {
        scrollTop = $(document).scrollTop();
        eyesUtils.timerListener(scrollTop);
    });
});

function EyesUtils(){

    var eyes = this;
    var timeDoc = $("#time-year");
    var articleDoc = $("#eyes-main");

    this.timerListener = function(scrollTop){
        eyes.locationTime(scrollTop);
    };

    this.locationTime = function(scrollTop){
        var _this, articleIn, date_Y, date_M, time_Now, time_M, time_Y;

        articleDoc.find("li").each(function(){
            _this = $(this);
            articleIn = _this.offset().top - scrollTop;
            if(articleIn <= 300 && articleIn >= 0){
                //得到当前li的date
                date_Y = _this.attr("data-time-Y");
                date_M = _this.attr("data-time-M");
                time_Now = timeDoc.find(".show");
                time_M = time_Now.parent().attr("data-M");
                time_Y = time_Now.parents("ul").attr("data-Y");
                if(date_Y != time_Y || date_M != time_M){
                    eyes.changeTime(date_Y, date_M);
                }
            }
        });
    };

    this.changeTime = function(Y, M){
        timeDoc.find("i").removeClass("show");
        var ul = timeDoc.find("ul[data-Y='" + Y + "']");
        if(ul.attr("is-show") == "false"){
            timeDoc.find("ul[is-show='true']").attr("is-show", "false").slideUp();
            ul.attr("is-show", "true").slideDown();
        }
        ul.find("h5[data-M='" + M + "']").find("i").addClass("show");
    }
}