var categorys = new CategoryUtils();

function EyesUtils(){

    var thisObj = this;

    this.scrollBarListener = function(){
        var windowHeight;
        var _window = $(window);
        var timeDiv = $("#time-div");
        var firstDiv, nowY, nowM;
        var isLis = false;//是否监听的开关
        _window.scroll(function(){
            windowHeight = _window.scrollTop();
            if(windowHeight > 100){
                //开始监测滚动的文章
                timeDiv.css("position", "fixed");
                isLis = true;
                getNowTimeArticle(timeDiv);
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

    this.changeTimeBar = function (timeDiv, tarY, tarM) {
        //如果年份不同，则需要修改年份显示
        var bar_year = timeDiv.attr("n-y");
        var bar_month = timeDiv.attr("n-m");
        if(tarY != bar_year){
            timeDiv.attr("n-y", tarY).find("i").removeClass("yead-front-up").end()
                   .find("em").removeClass("now-year").end()
                   .find("ul").slideUp(200);
            $("." + tarY).find("i").addClass("yead-front-up").end()
                         .find("em").addClass("now-year").end().parent().find("ul").slideDown(200);
        }
        if(tarM != bar_month){
            timeDiv.attr("n-m", tarM).find("." + tarY).parent().find("li").removeClass().end()
                   .find("li[t-m='" + tarM + "']").addClass("now-month")
        }
    };

    var getNowTimeArticle = function (timeDiv) {
        var year, month;
        var nowDom = document.elementFromPoint(500, 20);
        nowDom = $(nowDom);
        var parent = nowDom.parents("h2");
        if(parent.length > 0){
            var bar_year = timeDiv.attr("n-y");
            var bar_month = timeDiv.attr("n-m");
            year = parent.attr("data-year");
            month = parent.attr("data-month");
            if(bar_year != year || bar_month != month){
                thisObj.changeTimeBar(timeDiv, year, month);
            }
        }
    }

}
var eyes = new EyesUtils();

$(function () {
    categorys.init($("#type-body"));
    eyes.scrollBarListener();
});