var isLoadingComment = false;
var loaddiv = "<tr><td colspan=\"4\"><img src=\"/files/base/images/loading-bar.gif\" />加载中...</td></tr>";

var table = $("#data-table");
function loadDatas(page, isCreate){
    getDatas(page, isCreate);
}

function getDatas(page, isCreate) {
    var param = createParam(page);
    table.empty();
    table.append(loaddiv);
    $.ajax({
        url: "/series/datas.html",
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
                    "<td colspan=\"5\">没有数据。。。</td>" +
                    "</tr>";
            }
            isLoadingComment = false;
            table.append(tds);
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
    var tds = "";
    var temp;
    for(var a = 0, b = list.length; a < b; a++){
        temp = list[a];
        tds += "<tr class=\"table-datas\" sid=\"" + temp.seriesSid + "\">" +
                "<td>" + temp.seriesSid + "</td>" +
                "<td title=\"" + temp.seriesName + "\">" +
                    temp.seriesName +
                "</td>" +
                "<td>" +
                    temp.articleCounts +
                "</td>" +
                "<td>" +
                    new Date(temp.createTime).Format("yyyy-MM-dd") +
                "</td>" +
                "<td>" +
                    "<span type='manager'>管理</span>" +
                    "<span type='delete'>删除</span>" +
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

    $("#data-table").on("click", "span", function () {
        var obj = $(this);
        var type = obj.attr("type");
        var id = obj.parents("tr").attr("sid");
        if(type == "manager"){
            window.open("/series/manager.html?id=" + id, "newWindow");
        }
        if(type == "delete"){
            $.ajax({
                url: "/series/delete.html",
                data: {
                    "id": id
                },
                type: "post",
                success: function (res) {
                    if(res == "success"){
                        alert("删除成功");
                    }
                    window.location = location;
                }
            })
        }
    });
});