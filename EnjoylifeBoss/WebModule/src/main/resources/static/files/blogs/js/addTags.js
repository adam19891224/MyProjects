$(function () {

    $("#submit-blog-button").click(function () {
         var list = $("#tag-div").find("input[type='checkbox']:checked");
        if(list.length < 1){
            alert("请至少选择一个标签");
            return false;
        }
        var aid = $("#tag-div").attr("aid");
        var putObj = {};
        putObj.articleId = aid;
        var arr = [];
        var temp;
        list.each(function () {
            temp = $(this).val();
            arr.push(temp);
        });
        putObj.tagIds = arr;
        var json = JSON.stringify(putObj);
        $.ajax({
            url: "/blogs/saveTags.html",
            type: "post",
            data: {
                "json": json
            },
            success: function (res) {
                if(res == "success"){
                    alert("完成打标签");
                }
                window.location = location;
            }
        })
    });

});