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
    <link rel="stylesheet" href="/base/css/page.css" type="text/css" media="all">
    <link rel="stylesheet" href="/category/css/index.css?t=[@time /]" type="text/css" media="all">
    <script src="/base/js/jquery-2.2.3.min.js"></script>
    <script src="/base/js/app.js"></script>
    <script src="/base/js/tar-cloud.min.js"></script>
    <script src="/base/js/blazy.min.js"></script>
    <script src="/base/js/page.js"></script>
</head>
<body>
[#--顶部--]
[#include "../base/layout/header.ftl"]

[#--中间--]
<main id="main" class="main">
[#--左边--]
    <div class="main-left">
        <div class="left-body">
        [#if result?? && result?size > 0]
            [#list result as info]
                <div class="info-container">
                    <div class="container-left">
                        <img src="/base/images/image-loading.gif" class="b-lazy" data-src="${info.articleImg}" alt="${info.articleTitle}" />
                    </div>
                    <div class="container-right">
                        <div class="right-title">
                            <a href="/blogs/${info.articleSid}">
                                ${info.articleTitle?default("")}
                            </a>
                        </div>
                        <div class="right-desc">
                            ${info.articleDescription}
                        </div>
                        <div class="right-tag">
                            <span>
                                <i class="show"></i> <em class="show-text">个人博客</em>
                            </span>
                            <span>
                                <i class="time"></i> <em class="time-text">${info.createDate?default("")?string("yyyy-MM-dd")}</em>
                            </span>
                        </div>
                    </div>
                </div>
            [/#list]
        [#else]
            <div class="info-container">
                <h2 class="not-data">
                    没有找到数据。。。
                </h2>
            </div>
        [/#if]
        </div>
    [#--分页--]
        <div class="left-page">
            <div id="page-div" class="page-div" data-pages="${totalPages}" data-current="${page}"></div>
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
<script src="/category/js/index.js?t=[@time /]"></script>
</html>