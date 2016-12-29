function Applications() {

    var _this = this;

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
}
var applications = new Applications();

Date.prototype.Format = function(fmt){
    var o = {
        "M+" : this.getMonth()+1,                 //月份
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
        var href = window.location.href;
        if(href.indexOf("eyes") > 0){
            var eyes = new Eyes();
            eyes.init();
        }
        startCloud();
    });

    $("#header-nav-ul li a").click(function () {
        $(this).parent().addClass("header-underline").siblings().removeClass("header-underline");
    });

    startCloud();
});

function startCloud(){
    var categorys = new CategoryUtils();
    var typeObj = $("#type-body");
    if(typeObj.length > 0){
        categorys.init(typeObj);
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