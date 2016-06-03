<!DOCTYPE HTML>
<html>
<head>
    <title>找不到资源了</title>
    [#include "../layout/head-setting.ftl"]
    <link rel="stylesheet" href="../error/css/error.css?t=[@time /]" type="text/css"/>
</head>
<body>

    [#--顶部--]
    [#include "../layout/header.ftl"]

    <main class="main">
        <div class="logo">
            <h1>404</h1>
            <p>资源错误。。。。。。</p>
            <div class="sub">
                <p>
                    <a href="/index.html">回到首页</a>
                </p>
            </div>
        </div>
    </main>

    [#--底部--]
    [#include "../layout/footer.ftl"]
</body>
</html>
