require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "lazyload": "/base/js/jquery.lazyload.min",
        "common": "/base/js/common",
        "jTempletes": "/blogs/js/templates1",
        "jTempletesUn": "/blogs/js/templates_uncompressed",
        "hots": "/base/js/hots",
        "tarCloud": "/base/js/tar-cloud.min"
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
    categorys.init($("#type-body"));

    var hotsUtils = new HotsUtils();
    hotsUtils.getHots();

    var eyesUtils = new EyesUtils();
    eyesUtils.timerYearClickListener();
    eyesUtils.timerMonthClickListener();
    eyesUtils.initLocation();
    eyesUtils.lazyLoading();

    var scrollTop;
    //时间监控
    $(window).scroll(function () {
        scrollTop = $(document).scrollTop();
        eyesUtils.timerListener(scrollTop);
        eyesUtils.timerLocationListener(scrollTop);
    });
});

function EyesUtils(){

    var eyes = this;
    var timeDoc = $("#time-year");
    var articleDoc = $("#eyes-main");
    var leftDoc = $("#div-left");
    var type = true;

    this.initLocation = function(){
        var scrollT = $(document).scrollTop();
        eyes.locationTime(scrollT);
        eyes.timerLocationListener(scrollT);
    };

    this.timerListener = function(scrollTop){
        eyes.locationTime(scrollTop);
    };

    /**
     * 根据滚动定位当前顶部展示的文章时间
     * @param scrollTop
     */
    this.locationTime = function(scrollTop){
        if(type){
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
                        //如果当前进入顶部300px范围内的文章，时间与时间轴的时间不一致，则修改时间轴
                        eyes.changeTime(date_Y, date_M);
                    }
                }
            });
        }
    };

    /**
     * 根据年和月改变当前时间轴的指向
     * @param Y
     * @param M
     */
    this.changeTime = function(Y, M){
        timeDoc.find("i").removeClass("show");
        var ul = timeDoc.find("ul[data-Y='" + Y + "']");
        if(ul.attr("is-show") == "false"){
            timeDoc.find("ul[is-show='true']").attr("is-show", "false").slideUp().end()
                .find(".up").removeClass("up").addClass("down");
            ul.attr("is-show", "true").slideDown().parent().find("h3").find("i").removeClass("down").addClass("up");
        }
        ul.find("h5[data-M='" + M + "']").find("i").addClass("show");
    };

    /**
     * 监听时间轴，当到顶部时改为浮动
     */
    this.timerLocationListener = function (scrollTop) {
        var timeHeight = timeDoc.offset().top - scrollTop;
        var leftHeight = leftDoc.offset().top - scrollTop;
        if(timeHeight <= 30){
            if(!timeDoc.hasClass("time-year-location"))
                timeDoc.addClass("time-year-location");
        }
        if(leftHeight >= -60){
            if(timeDoc.hasClass("time-year-location"))
                timeDoc.removeClass("time-year-location");
        }
    };

    /**
     * 监听年份点击
     */
    this.timerYearClickListener = function(){
        var _this, _thisUl;
        $("#time-year").on("click", "h3", function(){
            _this = $(this);
            _thisUl = _this.parent().find("ul");
            if(_thisUl.attr("is-show") == "false"){
                type = false;
                //得到当前点击的年份
                var Y = _thisUl.attr("data-Y");
                //获取它的第一个月份
                var M = _thisUl.find("li").first().find("h5").attr("data-M");
                //展示这个时间
                eyes.changeTime(Y, M);
                //滚动到这个位置
                eyes.getLiByYandM(Y, M);
            }
        });
    };

    /**
     * 监听月份点击
     */
    this.timerMonthClickListener = function () {
        _this = $(this);
        $("#time-year").on("click", "h5", function(){
            _this = $(this);
            if(!_this.find("i").hasClass("show")){
                type = false;
                //获取当前的月份
                var M = _this.attr("data-M");
                var Y = _this.parents("ul").attr("data-Y");
                //展示这个时间
                eyes.changeTime(Y, M);
                //滚动到这个位置
                eyes.getLiByYandM(Y, M);
            }
        });
    };

    /**
     * 滚动到指定地方
     */
    this.scrollTo = function (height) {
        $("body,html").animate({scrollTop: height}, 1000, function () {
            type = true;
        });
    };

    /**
     * 获取指定日期的li的第一个元素
     */
    this.getLiByYandM = function (Y, M) {
        var str = Y + "-" + M;
        var liDoc = $("#eyes-main").find("li[data-time-YM='" + str + "']").first();
        //滚动到这个Li的高度
        eyes.scrollTo(liDoc.offset().top - 50);
    };

    /**
     * 图片懒加载
     */
    this.lazyLoading = function () {
        $("#eyes-main img").lazyload({
            effect: "fadeIn",
            threshold : 200,
            failure_limit: 1
        });
    }
}