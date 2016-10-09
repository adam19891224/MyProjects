require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "lazyload": "/base/js/jquery.lazyload.min",
        "common": "/base/js/common",
        "jTempletes": "/blogs/js/templates",
        "jTempletesUn": "/blogs/js/templates_uncompressed",
        "hots": "/base/js/hots",
        "page": "/base/js/page",
        "tarCloud": "/base/js/tar-cloud.min"
    },
    shim: {
        "lazyload": ["jquery"],
        "jTempletes": ["jquery"],
        "jTempletesUn": ["jquery"],
        "page": ["jquery"]
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
    "page",
    "tarCloud"
], function ($) {

    var common = new Common();
    common.searchButtonMouseListener();
    common.showNavigatorBar(2);

    var categorys = new CategoryUtils();
    categorys.init($("#type-body"));

    var hotsUtils = new HotsUtils();
    hotsUtils.getHots();

    var searchUtils = new SearchUtils();
    searchUtils.lazyLoading();
    searchUtils.createPage();
});

function SearchUtils() {

    /**
     * 图片懒加载
     */
    this.lazyLoading = function () {
        $("#search-main img").lazyload({
            effect: "fadeIn",
            threshold: 200,
            failure_limit: 1
        });
    };

    this.createPage = function () {
        $(function () {
            var page = $("#kkpager");
            kkpager.generPageHtml({
                pno: page.attr("current"),
                //总页码
                total: page.attr("total"),
                isShowTotalPage: false,
                isShowCurrPage: false,
                isShowTotalRecords: false,
                isGoPage: false,
                mode: 'click',
                lang: {
                    firstPageText: '首页',
                    firstPageTipText: '首页',
                    lastPageText: '尾页',
                    lastPageTipText: '尾页',
                    prePageText: '上一页',
                    prePageTipText: '上一页',
                    nextPageText: '下一页',
                    nextPageTipText: '下一页'
                },
                click: function (n) {
                    window.location = "/search/keyword/" + page.attr("key").replace(/\//g, '') + "/" + n + ".html";
                }
            });
        });
    }
}