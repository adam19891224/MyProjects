require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min"
    },
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    urlArgs: "t=" + (new Date()).getTime()
});

require([
    "jquery"
], function ($){

    $("#reloadImage").click(function(){
        //获取当前的时间作为参数，无具体意义
        var timenow = new Date().getTime();
        $(this).attr("src", "/validate/getImage.jpg?date=" + timenow);
    });

});