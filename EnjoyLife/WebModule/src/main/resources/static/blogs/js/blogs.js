require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
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
    "common"
], function ($){

    var common = new Common();
    common.searchButtonMouseListener();
    common.showNavigatorBar(1);

});