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
    <link href="/blogs/css/index.css?t=[@time /]" rel="stylesheet" type="text/css" media="all">
    <script src="/base/js/jquery-2.2.3.min.js"></script>
    <script src="/base/js/tar-cloud.min.js"></script>
    <script src="/base/js/app.js"></script>
    <script src="/blogs/js/prism.js"></script>
    <script src="/ckeditor/ckeditor.js"></script>
</head>
<body>
[#--顶部--]
[#include "../base/layout/header.ftl"]

[#--中间--]
<main id="main" class="main">
    <div class="main-left">
        <h1>
            ${article.articleTitle}
        </h1>
        <div class="article-intro">
            <span class="intro-type">
                <i></i># <span>${type.typeName}</span>
            </span>
            <span class="intro-time">
                <i></i>${article.createDate?string("yyyy-MM-dd")}
            </span>
        </div>
        <div class="article-tags">
            [#if tags?? && tags?size > 0]
                [#list tags as tag]
                    <span tid="${tag.tagId}">${tag.tagName}</span>
                [/#list]
            [/#if]
        </div>
        <article id="article-body" class="article-body">
            ${article.articleBody}
        </article>
        <div class="article-line"></div>
        <div class="article-block"></div>

        [#--引入评论--]
        [#include "comment.ftl"/]
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
<script src="/blogs/js/index.js?t=[@time /]"></script>

</html>