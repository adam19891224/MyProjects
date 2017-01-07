var isLoadingComment = false;
var loaddiv = "<tr><td colspan=\"7\"><img src=\"/files/base/images/loading-bar.gif\" />加载中...</td></tr>";
var table = $("#data-table");
function loadDatas(page, isCreate){
    getDatas(page, isCreate);
}

function getDatas(page, isCreate) {
    var param = createParam(page);
    table.empty();
    table.append(loaddiv);
    $.ajax({
        url: "/blogs/datas.html",
        type: "post",
        data: param,
        success: function (data) {
            table.empty();
            data = eval("(" + data + ")");
            var tds = "";
            var totalPages = 0;
            if(data.code == "1"){
                totalPages = data.totalPages;
                var list = data.datas;
                tds = castList2Tds(list);
                if(isCreate){
                    createPagation(page, totalPages);
                }
            }else{
                tds = "<tr class=\"table-datas\">" +
                        "<td colspan=\"7\">没有数据。。。</td>" +
                      "</tr>";
            }
            isLoadingComment = false;
            table.append(tds);
            new Blazy({
                container: '#data-table',
                error: function(ele, msg){
                    // if(msg === 'missing'){
                    //     console.log("加载丢失");
                    // }else if(msg === 'invalid'){
                    //     console.log("加载失败");
                    // }
                    ele.src = "/files/base/images/failed.png";
                }
            });
        }
    })
}

function createPagation(page, totalPage) {
    $("#page-div").empty();
    $("#page-div").createPage({
        pageCount: totalPage,
        current: page,
        backFn: function(page, eve, obj){
            if(!isLoadingComment){
                isLoadingComment = true;
                eve.fillHtml(obj, {"current": page, "pageCount": totalPage});
                loadDatas(page, false);
            }
        }
    });
}

function castList2Tds(list) {
    var tds = "", typeName="";
    var temp;
    for(var a = 0, b = list.length; a < b; a++){
        temp = list[a];
        if(temp.typeName){
            typeName = temp.typeName;
        }
        tds += "<tr class=\"table-datas\" sid=\"" + temp.articleSid + "\">" +
                    "<td>" + temp.articleSid + "</td>" +
                    "<td title=\"" + temp.articleTitle + "\">" +
                        temp.articleTitle +
                    "</td>" +
                    "<td>" +
                        temp.articleDescription +
                    "</td>" +
                    "<td>" +
                        "<img src=\"/files/base/images/image-loading.gif\" class=\"b-lazy\" data-src=\"" + temp.articleImg + "\" alt=\"" + temp.articleTitle + "\" />" +
                    "</td>" +
                    "<td>" +
                        typeName +
                    "</td>" +
                    "<td>" +
                            new Date(temp.createDate).Format("yyyy-MM-dd") +
                    "</td>" +
                    "<td>" +
                        "<span class='update'>修改</span>" +
                        "<span class='tags'>打标签</span>" +
                        "<span class='remove'>删除</span>" +
                    "</td>" +
                "</tr>";
    }
    return tds;
}

function createParam(page) {
    var obj = {};
    obj.page = page;
    return obj;
}

$(function () {
   loadDatas(1, true);

    $("#data-table").on("click", ".update", function () {
        var sid = $(this).parents("tr").attr("sid");
        window.open("/blogs/update.html?id=" + sid, "updateWindow");
    });

    $("#data-table").on("click", ".tags", function () {
        var sid = $(this).parents("tr").attr("sid");
        window.open("/blogs/addTags.html?id=" + sid, "updateWindow");
    });

    $("#data-table").on("click", ".remove", function () {
        var sid = $(this).parents("tr").attr("sid");

        $.ajax({
            url: "/blogs/delete.html",
            data: {
                "sid": sid
            },
            type: "post",
            success: function (res) {
                if(res == "null"){
                    alert("没有找到文章信息");
                }
                if(res == "success"){
                    alert("删除成功");
                }
                window.location = location;
            }
        })
    });
});