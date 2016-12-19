var categorys = new CategoryUtils();

$(function () {
    categorys.init($("#type-body"));

    new Blazy({
        container: '.info-container',
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
                isClick = false;
                var location = window.location.href;
                if(page > 0){
                    if(applications.checkIsNum(page)){
                        location = location.substr(0, location.lastIndexOf("/") + 1);
                        location += page;
                    }
                }
                window.location = location;
            }
        }
    });
});