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
        window.location = locations;
    });
});