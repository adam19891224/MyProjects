require.config({
    paths: {
        "jquery": "/base/js/jquery-2.2.3.min",
        "common": "/base/js/common",
        "ck": "/blogs/ckeditor/ckeditor"
    },
    //require.js添加统一的url后缀参数方法，这里添加一个后缀时间戳，防止缓存
    urlArgs: "t=" + (new Date()).getTime()
});

require([
    "jquery",
    "common",
    "ck"
], function ($){

    var common = new Common();
    common.searchButtonMouseListener();
    common.showNavigatorBar(1);

    //创建ck
    CKEDITOR.replace(
        "comment-editor",
        {
            height: "120px",
            resize_enabled: false,
            toolbar:
                [
                    [ "Undo", "Smiley" ]
                ],
            removePlugins: "elementspath"
        }
    );

    CKEDITOR.replace(
        "comment-reply-editor",
        {
            height: "80px",
            resize_enabled: false,
            toolbar:
                [
                    [ "Smiley" ]
                ],
            removePlugins: "elementspath"
        }
    );

});