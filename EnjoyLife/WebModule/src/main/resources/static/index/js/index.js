$(function () {
    new Blazy({
        container: '.blog-body',
        error: function(ele, msg){
            // if(msg === 'missing'){
            //     console.log("加载丢失");
            // }else if(msg === 'invalid'){
            //     console.log("加载失败");
            // }
            ele.src = "/base/images/failed.png";
        }
    });

    var isClick = true;

    $("#page-div").createPage({
        pageCount: applications.castStr2Num($("#page-div").attr("data-pages")),
        current: applications.castStr2Num($("#page-div").attr("data-current")),
        backFn: function(page){
            if(isClick){
                var location = "";
                if(page == 1){
                    location = "/index.html"
                }else{
                    location = "/page/" + page + ".html";
                }
                window.location = location;
            }
        }
    });

});