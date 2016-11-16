<!DOCTYPE html>
<html>
<head>
    <title>月下竹影---Web开发交流频道</title>
    <meta name="keywords" content="Web技术，Web开发，Web交流，月下竹影，竹影，箫竹影，周禹宏" />
    <meta name="description" content="月下竹影，是一个作为Web技术交流平台的个人原创技术博客。期待大家的光临。" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link href="/base/images/ico.ico" rel="shortcut icon" type="image/x-icon">
    <link href="/base/css/page.css" rel="stylesheet" type="text/css" media="all">
    <link href="/index/css/index.css?t=[@time /]" rel="stylesheet" type="text/css" media="all">
    <script src="/base/js/jquery-2.2.3.min.js"></script>
    <script src="/base/js/blazy.min.js"></script>
    <script src="/base/js/app.js"></script>
    <script src="/base/js/page.js"></script>
</head>
<body>
    [#--顶部--]
    [#include "../base/layout/header.ftl"]

    [#--中间--]
    <main id="main" class="main">
        [#--左边--]
        <div class="main-left">
            [#--列表--]
            <div class="left-body">
                [#list result as blog]
                    <div class="main-blog">
                        <div class="blog-head">
                            <h2>
                                <a href="/blogs/${blog.articleSid}.html">${blog.articleTitle}</a>
                            </h2>
                        </div>
                        <div class="blog-intro">
                            <span>评论数：(${blog.comments})</span>
                            <span>by：${blog.createDate?string("yyyy-MM-dd")}</span>
                        </div>
                        <div class="blog-line"></div>
                        <div class="blog-type">
                            [#list blog.types as type]
                                <div class="type-body">${type}</div>
                            [/#list]
                        </div>
                        <div class="blog-body">
                            <a href="/blogs/${blog.articleSid}.html">
                                <img src="/base/images/image-loading.gif" class="b-lazy" data-src="${blog.articleImg}" alt="${blog.articleTitle}"/>
                            </a>
                            <span>
                                ${blog.articleDescription}
                            </span>
                        </div>
                    </div>
                [/#list]
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
        </div>
    </main>

    [#--底部--]
    [#include "../base/layout/footer.ftl"]
</body>
<script src="/index/js/index.js?t=[@time /]"></script>

</html>