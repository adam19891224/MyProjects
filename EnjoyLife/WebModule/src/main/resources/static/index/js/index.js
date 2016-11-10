$(function () {
    //绑定懒加载
    $(".blog-body img").lazyload({
        effect: "fadeIn",
        threshold : 200,
        failure_limit: 1
    });

    $("#page-div").Page({
        totalPages: applications.castStr2Num($("#main").attr("data-pages")),
        liNums: 5,
        activeClass: 'activP',
        callBack : function(page){
            console.log(page);
        }
    });
});