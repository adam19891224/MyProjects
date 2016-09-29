require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "common": "/base/js/common",
        "jTempletes": "/blogs/js/templates",
        "jTempletesUn": "/blogs/js/templates_uncompressed",
        "hots": "/base/js/hots",
        "tarCloud": "/base/js/tar-cloud.min"
    },
    shim: {
        "jTempletes" : ["jquery"],
        "jTempletesUn" : ["jquery"]
    }
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    // urlArgs: "t=" + (new Date()).getTime()
});

require([
    "jquery",
    "common",
    "jTempletes",
    "jTempletesUn",
    "hots",
    "tarCloud"
], function ($){

    var common = new Common();
    common.searchButtonMouseListener();
    common.showNavigatorBar(1);

    var categorys = new CategoryUtils();
    categorys.init($("#type-body"));

    var hotsUtils = new HotsUtils();
    hotsUtils.getHots();

});