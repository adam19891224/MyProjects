var categorys = new CategoryUtils();

$(function () {
    categorys.init($("#type-body"));

    var isClick = true;

    $("#page-div").createPage({
        pageCount: applications.castStr2Num($("#page-div").attr("data-pages")),
        current: applications.castStr2Num($("#page-div").attr("data-current")),
        backFn: function(page){
            if(isClick){
                var location = "/index/";
                if(page > 1){
                    if(applications.checkIsNum(page)){
                        location = "/index/" + page;
                    }
                }
                window.location = location;
            }
        }
    });
});