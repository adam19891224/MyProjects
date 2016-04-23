<!DOCTYPE html>
<html>
    <head>
        <title>月下竹影---Web开发交流频道</title>
        <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏" />
        <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。" />
        [#include "../layout/head-setting.ftl"]
        <link rel="stylesheet" href="../index/css/index.css">
        <script src="../index/js/index.js"></script>
    </head>
    <body>
        [#--顶部--]
        [#include "../layout/header.ftl"]

        [#--中间--]
        <main id="main" class="main">
            <div class="main-div">
                <div class="div-left">
                    <div class="left-top">
                        <span>敬请期待</span>
                    </div>
                    <div class="left-main">
                        <h3>
                            <i class="tips"></i>
                            技术交流
                        </h3>
                        <div class="line"></div>
                        <ul id="blog-main"></ul>
                        <div id="loading-div" class="loading-div loading-hidden">
                            <span>加载中。。。。。。</span>
                            <img src="../base/images/loading1.gif">
                        </div>
                    </div>
                </div>
                <div class="div-right">
                    <div class="hot-div">
                        <h3>
                            <i class="tips"></i>
                            热门搜索
                        </h3>
                        <div class="line"></div>
                        <ul>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                            <li>
                                <a href="#">热点11111</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </main>

        [#--底部--]
        [#include "../layout/footer.ftl"]

    </body>
</html>