$(function () {

    $("#submitButton").click(function () {

        var friendName = $("#friendName").val();
        if(!applications.isNotNull(friendName)){
            alert("请填写友链名称");
            return false;
        }

        var friendTips = $("#friendTips").val();
        if(!applications.isNotNull(friendTips)){
            alert("请填写友链介绍");
            return false;
        }


        var friendValue = $("#friendValue").val();
        if(!applications.isNotNull(friendValue)){
            alert("请填写友链路径");
            return false;
        }

        var selectWebSite = $("#selectWebSite").val();

        var obj = {};
        obj.friendName = friendName;
        obj.friendTips = friendTips;
        obj.friendValue = selectWebSite + friendValue;

        $.ajax({
            url: "/friends/save.html",
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