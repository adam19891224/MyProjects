require.config({
    paths: {
        "jquery": "/common/js/jquery-1.12.0.min",
        "lazyload": "/common/js/jquery.lazyload.min",
        "common": "/common/js/common",
        "highlight": "/article/highlight/js/highlight.pack",
        "article": "/article/js/article",
        "textarea": "/article/js/textarea"
    },
    shim: {
        "lazyload" : ["jquery"]
    },
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    urlArgs: "t=" + (new Date()).getTime()
});

require([
    "jquery",
    "lazyload",
    "common",
    "highlight",
    "article",
    "textarea"
], function ($){

    var application = new Application();
    application.init();
    navBarMouseMoveListener();

    //初始化highlight控件
    hljs.initHighlighting();

    var article = new Article();
    article.init();

    var text = document.getElementById("article-comment-editor-text");
    autoTextarea(text);// 调用

});