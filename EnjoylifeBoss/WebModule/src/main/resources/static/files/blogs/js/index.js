$(function () {

    $("#login-button").click(function () {
        var login = $("#login-body");
        $.ajax({
            url: "/doLogin.html",
            data: {
                "userName": login.find("input").first().val(),
                "password": login.find("input").last().val()
            },
            type: "post",
            success: function (res) {
                if(res == "notBlank"){
                    alert("请填写用户名和密码");
                }
                if(res == "success"){
                    window.location = "/blogs/index.html";
                }
                alert(res);
            }
        });
    });

    var editor = CKEDITOR.replace("blog-text-editor",  { height: '500px', width: '100%' });

});