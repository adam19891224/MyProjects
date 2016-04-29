require.config({
    paths: {
        "jquery": "/common/js/jquery-1.12.3.min",
        "cookie": "/common/js/jquery.cookie",
        "lazyload": "/common/js/jquery.lazyload.min",
        "common": "/common/js/common",
        "home": "/home/js/home"
    },
    shim: {
        "lazyload" : ["jquery"]
    },
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    urlArgs: "t=" + (new Date()).getTime()
});

require([
            "jquery",
            "cookie",
            "lazyload",
            "common",
            "home"
        ], function ($){

    var home = new Home();
    home.init();

    var application = new Application();
    application.init();
    application.navBarAnimation();

});