<!DOCTYPE html>
<html>
<head>
    <title>月下竹影---Web开发交流频道</title>
    <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏" />
    <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="/base/images/ico.ico" type="image/x-icon">
    <link rel="stylesheet" href="/base/css/common.css" type="text/css" media="all">
    <link rel="stylesheet" href="/base/css/intro.css" type="text/css" media="all">
    <link rel="stylesheet" href="/base/css/cloud.css" type="text/css" media="all">
    <link rel="stylesheet" href="/series/css/index.css?t=[@time /]" type="text/css" media="all">
    <script src="/base/js/jquery-2.2.3.min.js"></script>
    <script src="/base/js/app.js"></script>
    <script src="/base/js/tar-cloud.min.js"></script>
</head>
<body>
[#--顶部--]
[#include "../base/layout/header.ftl"]

[#--中间--]
<main id="main" class="main" data-pages="2">
    [#--左边--]
    <div class="main-left">
        [#--列表--]
        <div class="left-body">
            <ul class="series-main">
                [#if series?? && series?size > 0]
                    [#list series as seriesE]
                        <li>
                            <a href="#">
                                <h2>${seriesE.seriesName}</h2>
                            </a>
                            <span>${seriesE.counts} 篇</span>
                        </li>
                    [/#list]
                [#else]
                    <li>
                        <h5>敬请期待</h5>
                    </li>
                [/#if]
            </ul>
        </div>
    </div>
    [#--右边--]
    <div class="main-right">
        <div class="right-body">
            [#--引入介绍--]
            [#include "../base/common/intro.ftl"/]
        </div>
        <div class="right-body">
            [#--引入标签云--]
            [#include "../base/common/cloud.ftl"/]
        </div>
    </div>
</main>

[#--底部--]
[#include "../base/layout/footer.ftl"]
</body>
<script src="/series/js/index.js?t=[@time /]"></script>
</html>