function Common(){

    this.searchButtonMouseListener = function(){

        $("#search").mouseover(function(){
            $(this).find("img").attr("src", "/base/images/mirror-hover.png");
        });

        $("#search").mouseout(function(){
            $(this).find("img").attr("src", "/base/images/mirror.png");
        });

        $("#search").click(function(){
            var kw = $(this).parent().find("input").val().replace(/\//g, '');
            if(isNotNull(kw)){
                window.location = "/search/keyword/" + kw + "/1.html";
            }else{
                window.location = "/eyes/index.html";
            }
        });

    };

    this.showNavigatorBar = function(index){
        $("#index-navigator li a").eq(index).css("color", "white");
    };

}

Date.prototype.format = function(fmt) {
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
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function isNotNull(res) {
    if(Object.prototype.toString.call(res) === "[object String]"){
        return (res != null && res != "" && res != undefined);
    }
    return (res != null && res != "" && res != undefined && res.length > 0);
}