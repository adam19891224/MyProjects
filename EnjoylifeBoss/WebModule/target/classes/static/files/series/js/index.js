$(function () {

    $("#submitButton").click(function () {

        var serName = $("#seriesName").val();
        if(!applications.isNotNull(serName)){
            alert("请填写标签名称");
            return false;
        }

        var obj = {};
        obj.seriesName = serName;

        $.ajax({
            url: "/series/save.html",
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