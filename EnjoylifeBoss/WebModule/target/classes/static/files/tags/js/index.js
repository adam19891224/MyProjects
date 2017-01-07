$(function () {

    $("#submitButton").click(function () {

        var tagName = $("#tagName").val();
        if(!applications.isNotNull(tagName)){
            alert("请填写标签名称");
            return false;
        }

        var obj = {};
        obj.tagName = tagName;

        $.ajax({
            url: "/tags/save.html",
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