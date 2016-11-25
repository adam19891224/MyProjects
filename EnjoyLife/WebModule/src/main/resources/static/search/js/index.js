var categorys = new CategoryUtils();

$(function () {
    categorys.init($("#type-body"));

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