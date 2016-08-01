<!DOCTYPE html>
<html>
    <head>
        <title>月下竹影---Web开发交流频道</title>
        <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏"/>
        <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。"/>
        [#include "../layout/head-setting.ftl"]
        <link rel="stylesheet" href="../eyes/css/eyes.css?t=[@time /]">
        <script src="../eyes/js/eyes.js?t=[@time /]"></script>
    </head>
    <body>
        [#--顶部--]
        [#include "../layout/header.ftl"]

        [#--中间--]
        <main id="main" class="main">
            <div class="main-div">
                <div class="div-left">
                    <h3>
                        <i class="tips"></i>
                        文章一览
                    </h3>
                    <div class="line"></div>
                    <div class="left-time">

                    </div>
                    <div class="left-main">
                        <ul class="eyes-main">
                            <li>
                                <div class="eyes-top">
                                    2015-05-01
                                </div>
                            </li>
                            <li>
                                <div class="eyes-top">
                                    2015-05-02
                                </div>
                            </li>
                            <li>
                                <div class="eyes-top">
                                    2015-05-03
                                </div>
                            </li>
                            <li>
                                <div class="eyes-top">
                                    2015-05-04
                                </div>
                            </li>
                            <li>
                                <div class="eyes-top">
                                    2015-05-05
                                </div>
                            </li>
                            <li>
                                <div class="eyes-top">
                                    2015-05-06
                                </div>
                            </li>
                            <li>
                                <div class="eyes-top">
                                    2015-05-07
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="div-right">
                    [#--热门搜索--]
                    [#include "../common/hot-div.ftl" ]

                    [#--标签--]
                    [#include "../common/cloud-div.ftl" ]
                </div>
            </div>
        </main>

        [#--热门文章模板--]
        [#include "../index/templates/hot-article-li.ftl"]

        [#--底部--]
        [#include "../layout/footer.ftl"]

    </body>
</html>