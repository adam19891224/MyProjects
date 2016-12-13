$(function () {

    var selectLi = $("#getSelect");
    var selectObj = selectLi.find("select");

    $("input[name='hasParent']").change(function () {
        var val = $(this).val();
        if(val == "Y"){
            selectLi.removeClass("select-li");

            var load = selectObj.attr("load");
            if(load == "0"){
                //加载数据
                getAllTypes(selectObj);
                selectObj.attr("load", "1");
            }
        }else{
            selectLi.addClass("select-li");
        }
    });

    $("#submitButton").click(function () {

        var typeName = $("#typeName").val();
        if(!applications.isNotNull(typeName)){
            alert("请填写类别名称");
            return false;
        }
        var isSelest = selectLi.hasClass("select-li");
        var parentId = "";
        if(!isSelest){
            parentId = selectObj.val();
            if(!applications.isNotNull(parentId)){
                alert("如果选择了有父类别，请选择父类别");
                return false;
            }
        }

        var obj = {};
        obj.typeName = typeName;
        obj.typeParentId = parentId;

        $.ajax({
            url: "/types/save.html",
            type: "post",
            data: obj,
            success: function (res) {
                if(res == "success"){
                    alert("保存成功");
                    window.close();
                }else{
                    alert("保存失败");
                }
            }
        })

    });

});

function getAllTypes(selectObj) {
    $.ajax({
        url: "/types/getTypes.html",
        type: "post",
        data: {
            "pagination": false
        },
        success: function (data) {
            data = eval("(" + data + ")");
            if(data.code == "1"){
                var list = data.datas;
                var temp, options = "";
                for(var a = 0, b = list.length; a < b; a++){
                    temp = list[a];
                    options += "<option value='" + temp.typeId + "'>" + temp.typeName + " (" + temp.articleCounts + ") 篇" + "</option>"
                }
                selectObj.append(options);
            }else{
                selectObj.empty().append("<option value=''>数据加载失败</option>");
            }
        }
    })
}