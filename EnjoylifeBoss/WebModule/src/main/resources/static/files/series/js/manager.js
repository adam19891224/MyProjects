var whole = $("#whole-div");
var blog = $("#blog-main");

$(function () {

    $("#title-close").click(function () {
        $("#whole-div").hide();
    });

    $("#add-blog").click(function () {
        whole.show();
        var load = blog.attr("load");
        if(load == "0"){
            blog.attr("load", "1").empty().append("<div class=\"blog-entity\"><span>正在加载...</span><a class=\"chose-button\"></a></div>");
            //获取当前的类别id
            var aid = $("#container").attr("aid");
            $.ajax({
                url: "/series/getArticles.html",
                type: "post",
                data: {
                    "id": aid
                },
                success: function (data) {
                    data = eval("(" + data + ")");
                    if(data.code == "1"){
                        var list = data.datas;
                        var div = createArticleDiv(list);
                        blog.empty().append(div);
                    }else{
                        blog.empty().append("<div class=\"blog-entity\"><span>没有数据...</span><a class=\"chose-button\"></a></div>");
                    }
                }
            })
        }
    });

    blog.on("click", ".chose-button", function () {
        var temp = $(this);
        var aid = temp.parent().attr("aid");
        var asid = $("#container").attr("aid");
        var obj = {};
        obj.articleId = aid;
        obj.seriesId = asid;
        $.ajax({
            url: "/series/addArticle.html",
            type: "post",
            data: obj,
            success: function (res) {
                if(res == "null"){
                    alert("没有传入文章Id或者类别id");
                }
                if(res == "error"){
                    alert("保存时发生错误");
                }
                if(res == "success"){
                    alert("保存成功");
                    window.location = location;
                }
            }
        })

    });

    $("#container").on("click", ".remove", function () {
        var asid = $("#container").attr("aid");
        var aid = $(this).parent().attr("aid");
        var obj = {};
        obj.articleId = aid;
        obj.seriesId = asid;
        $.ajax({
            url: "/series/deleteArticle.html",
            type: "post",
            data: obj,
            success: function (res) {
                if(res == "null"){
                    alert("没有传入文章Id或者类别id");
                }
                if(res == "error"){
                    alert("移除时发生错误");
                }
                if(res == "success"){
                    alert("移除成功");
                    window.location = location;
                }
            }
        })
    });

});

function createArticleDiv(list){
    var temp, divs = "";
    for(var a = 0, b = list.length; a < b; a++){
        temp = list[a];
        divs += "<div class=\"blog-entity\" aid='" + temp.articleId + "'><span>" + temp.articleTitle + "</span><a class=\"chose-button\">选择</a></div>"
    }
    return divs;
}