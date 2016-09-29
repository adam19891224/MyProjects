require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "lazyload": "/base/js/jquery.lazyload.min",
        "common": "/base/js/common",
        "jTempletes": "/blogs/js/templates",
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

    var searchUtils = new SearchUtils();
    searchUtils.lazyLoading();
});

function SearchUtils(){

    /**
     * 图片懒加载
     */
    this.lazyLoading = function () {
        $("#search-main img").lazyload({
            effect: "fadeIn",
            threshold : 200,
            failure_limit: 1
        });
    }
}