$(function () {

    $("#login-button").click(function () {
        doLogin();
    });

    $("input").keypress(function (e) {
        if (e.keyCode == 13){
            doLogin();
        }
    });

});

function doLogin() {
    var login = $("#login-body");
    var lock = login.data("lock");
    if (lock == "1") {
        return false;
    }
    login.data("lock", "1");

    $("#loading-div").show();
    $.ajax({
        url: "/doLogin.html",
        data: {
            "userName": login.find("input").first().val(),
            "password": login.find("input").last().val()
        },
        type: "post",
        success: function (res) {
            if (res == "success") {
                window.location = "/index.html";
                return false;
            }
            login.data("lock", "");
            $("#loading-div").hide();
            if (res == "notBlank") {
                alert("请填写用户名和密码");
                return false;
            }
            alert(res);
        }
    });
}