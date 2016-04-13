<!DOCTYPE>
<html>
<head>
    <title>相信自己，一定能行</title>
    <script type="text/javascript" src="/common/js/require.js" defer async="true" data-main="/deploy/js/login.js"></script>
    <link rel="icon" href="/common/icon/favourite-icon_32X32.ico" mce_href="/resources/common/icon/favourite-icon_32X32.ico" type="image/x-icon">
</head>
<body>
    <form action="/deploy/main.html" method="post">
        <div style="position: fixed; width: 600px; height: 400px; top: 50%; margin-top: -200px; left: 50%; margin-left: -300px; border: 2px solid crimson;">
            <ul style="margin: 0 auto; padding: 0; width: 96%; height: 100%; list-style-type: none;">
                <li style="height: 80px; width: 100%; float:left;">
                    <div style="width: 20%; height: 100%; text-align: center; line-height: 80px; float: left;">
                        用户名
                    </div>
                    <div style="width: 80%; height: 100%; text-align: center;float:left; position: relative">
                        <input type="text" name="name" style="width: 80%; height: 30px; position: absolute; top: 50%; margin-top: -15px; left: 0;" title="" />
                    </div>
                </li>
                <li style="height: 80px; width: 100%; float:left;">
                    <div style="width: 20%; height: 100%; text-align: center; line-height: 80px; float: left;">
                        密码
                    </div>
                    <div style="width: 80%; height: 100%; text-align: center;float:left; position: relative">
                        <input type="password" name="password" style="width: 80%; height: 30px; position: absolute; top: 50%; margin-top: -15px; left: 0;" title="" />
                    </div>
                </li>
                <li style="height: 80px; width: 100%; float:left;">
                    <div style="width: 20%; height: 100%; text-align: center; line-height: 80px; float: left;">
                        验证码
                    </div>
                    <div style="width: 40%; height: 100%; text-align: center;float:left; position: relative">
                        <img src="/validate/getImage.jpg" style="width: 100%; height: 100%;" id="reloadImage" title="点击图片刷新验证码"/>
                    </div>
                    <div style="width: 40%; height: 100%; text-align: center;float:left; position: relative">
                        <input type="text" name="code" style="width: 80%; height: 30px; position: absolute; top: 50%; margin-top: -15px; left: 0;" title="" />
                    </div>
                </li>
                <li style="height: 80px; width: 100%; float:left;">
                    <div style="width: 80%; height: 100%; text-align: center; margin: 0 auto;">
                        <input type="submit" value="登陆" style="width: 100px; height: 40px; margin-top: 20px;" />
                    </div>
                </li>
            </ul>
        </div>
    </form>
</body>
</html>