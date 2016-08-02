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

});