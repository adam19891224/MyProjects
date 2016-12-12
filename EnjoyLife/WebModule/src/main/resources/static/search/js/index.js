var categorys = new CategoryUtils();

$(function () {
    categorys.init($("#type-body"));

    var isClick = true;

    $("#page-div").createPage({
        pageCount: applications.castStr2Num($("#page-div").attr("data-pages")),
        current: applications.castStr2Num($("#page-div").attr("data-current")),
        backFn: function(page){
            if(isClick){
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